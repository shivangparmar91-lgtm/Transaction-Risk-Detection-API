package com.Project.Transaction_Risk_API.Service;

import com.Project.Transaction_Risk_API.DTO.DashboardResponse;
import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import com.Project.Transaction_Risk_API.DTO.TransactionResponse;
import com.Project.Transaction_Risk_API.Exception.TransactionNotFoundException;
import com.Project.Transaction_Risk_API.Model.Enumm.RiskLevel;
import com.Project.Transaction_Risk_API.Model.Enumm.TransactionStatus;
import com.Project.Transaction_Risk_API.Model.Transaction;
import com.Project.Transaction_Risk_API.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private RiskEngineService res;

    public TransactionResponse createtransaction(TransactionRequest request)
    {
        Transaction transaction = new Transaction();

        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setCountry(request.getCountry());
        transaction.setSenderAccount(request.getSenderAccount());
        transaction.setReceiverAccount(request.getReceiverAccount());
        transaction.setDeviceId(request.getDeviceId());
        transaction.setTransactionTime(request.getTransactionTime());

        transaction.setRiskScore(res.calculatedRisk(request));

        transaction.setRiskLevel(res.getRiskLevel(transaction.getRiskScore()));

        transaction.setStatus(res.getStatus(transaction.getRiskLevel()));

        Transaction savedTransaction = transactionRepo.save(transaction);

        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setId(savedTransaction.getId());
        transactionResponse.setRiskScore(savedTransaction.getRiskScore());
        transactionResponse.setRiskLevel(savedTransaction.getRiskLevel());
        transactionResponse.setStatus(savedTransaction.getStatus());


        return transactionResponse;

    }


    public List<TransactionResponse> getAllTransaction() {

        List<Transaction> FromDb = transactionRepo.findAll();

        List<TransactionResponse> responses = new ArrayList<>();

        for(Transaction t : FromDb)
        {
            TransactionResponse Tr = new TransactionResponse(t.getId(),t.getRiskScore(),t.getRiskLevel(),t.getStatus());
            responses.add(Tr);
        }

        return responses;
    }

    public TransactionResponse getTransactionById(long id) {

        Transaction transaction = transactionRepo.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction Not Found With Id : " + id));

        TransactionResponse response = new TransactionResponse();

        response.setId(transaction.getId());
        response.setRiskScore(transaction.getRiskScore());
        response.setRiskLevel(transaction.getRiskLevel());
        response.setStatus(transaction.getStatus());

        return response;

    }

    public List<TransactionResponse> getTransactionRisk(TransactionStatus status) {

        List<Transaction> transactions = transactionRepo.findByStatus(status);

        List<TransactionResponse> responses = new ArrayList<>();

        for(Transaction t : transactions)
        {
            TransactionResponse TR = new TransactionResponse(t.getId(),t.getRiskScore(),t.getRiskLevel(),t.getStatus());
            responses.add(TR);
        }
        return responses;
    }

    public Page<TransactionResponse> getAllTransaction(int page, int size,String sortBy,String direction)
    {

        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Transaction> transactions = transactionRepo.findAll(pageable);

        Page<TransactionResponse> responses = transactions.map(transaction -> {

            TransactionResponse dto = new TransactionResponse();
            dto.setId(transaction.getId());
            dto.setRiskScore(transaction.getRiskScore());
            dto.setRiskLevel(transaction.getRiskLevel());
            dto.setStatus(transaction.getStatus());

            return dto;

        });

        return responses;
    }

    public DashboardResponse getDashbord()
    {
        long total = transactionRepo.count();

        long approved = transactionRepo.countByStatus(TransactionStatus.APPROVED);

        long blocked = transactionRepo.countByStatus(TransactionStatus.BLOCKED);

        long review = transactionRepo.countByStatus(TransactionStatus.REVIEW);

        long highRisk = transactionRepo.countByRiskLevel(RiskLevel.HIGH);

        double avgRisk = transactionRepo.getAvgRisk();

        DashboardResponse DR = new DashboardResponse();

        DR.setTotalTransactions(total);
        DR.setApprovedTransactions(approved);
        DR.setBlockedTransactions(blocked);
        DR.setReviewTransactions(review);
        DR.setHighRiskTransactions(highRisk);
        DR.setAverageRiskScore(avgRisk);

        return DR;
    }
}

