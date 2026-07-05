package com.Project.Transaction_Risk_API.Controller;

import com.Project.Transaction_Risk_API.DTO.DashboardResponse;
import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import com.Project.Transaction_Risk_API.DTO.TransactionResponse;
import com.Project.Transaction_Risk_API.Model.Enumm.TransactionStatus;
import com.Project.Transaction_Risk_API.Model.Transaction;
import com.Project.Transaction_Risk_API.Service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(
        name = "Transaction API",
        description = "Operations related to transaction management and risk analysis"
)
@SecurityRequirement(name = "Bearer Authentication")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Operation(
            summary = "Create a new transaction",
            description = "Creates a transaction, calculates the risk score, determines the risk level, and stores it in the database."
    )
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request)
    {
        TransactionResponse  transactionRes = transactionService.createtransaction(request);

        return new ResponseEntity<>(transactionRes, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get all transactions",
            description = "Returns a list of all transactions."
    )
    public ResponseEntity<List<TransactionResponse>> getAllTransaction()
    {
        List<TransactionResponse> responses = transactionService.getAllTransaction();

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get transaction by ID",
            description = "Returns a transaction using its unique ID."
    )
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable long id)
    {
        TransactionResponse responseById = transactionService.getTransactionById(id);

        return new ResponseEntity<>(responseById,HttpStatus.OK);
    }

    @GetMapping("/risk/{status}")
    @Operation(
            summary = "Get transaction by its Status",
            description = "Returns a transaction using its Status."
    )
    public ResponseEntity<List<TransactionResponse>> getTransactionRisk(@PathVariable TransactionStatus status)
    {
        List<TransactionResponse> responses = transactionService.getTransactionRisk(status);

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TransactionResponse>> getAllTransaction(@RequestParam int page,@RequestParam int size,@RequestParam String sortBy,@RequestParam String direction)
    {
        Page<TransactionResponse> response =
                transactionService.getAllTransaction(page, size,sortBy,direction);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/dashbord")
    @Operation(
            summary = "Get dashboard statistics",
            description = "Returns transaction analytics including totals, blocked transactions, and average risk score."
    )
    public ResponseEntity<DashboardResponse> getDashbord()
    {
        DashboardResponse response = transactionService.getDashbord();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
