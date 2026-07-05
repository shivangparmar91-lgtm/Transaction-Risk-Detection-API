package com.Project.Transaction_Risk_API.Service.Rules;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import com.Project.Transaction_Risk_API.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FrequentTransactionRisk implements RiskEngineRules{

    @Autowired
    private TransactionRepo repo;

    @Override
    public int calculatedRisk(TransactionRequest request) {

        LocalDateTime fiveMinutesAgo = request.getTransactionTime().minusMinutes(5);
        long count = repo.countBySenderAccountAndTransactionTimeGreaterThanEqual(request.getSenderAccount(),fiveMinutesAgo);
        if(count + 1 >= 3)
        {
            return  30;
        }
        return 0;
    }
}
