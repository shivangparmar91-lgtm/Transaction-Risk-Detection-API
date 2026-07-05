package com.Project.Transaction_Risk_API.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount Must Be a Positive")
    private Double amount;

    @NotBlank(message = "currency is required")
    private String currency;

    @NotBlank(message = "senderAccount is required")
    private String senderAccount;

    @NotBlank(message = "receiverAccount is required")
    private String receiverAccount;

    @NotBlank(message = "country is required")
    private String country;

    @NotBlank(message = "deviceId is required")
    private String deviceId;

    @NotNull(message = "transactionTime is required")
    @CreationTimestamp
    private LocalDateTime transactionTime;

}
