package com.Project.Transaction_Risk_API.Model;

import com.Project.Transaction_Risk_API.Model.Enumm.RiskLevel;
import com.Project.Transaction_Risk_API.Model.Enumm.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double amount;

    private String currency;

    private String senderAccount;

    private String receiverAccount;

    private String country;

    private String deviceId;

    private LocalDateTime transactionTime;

    private Integer riskScore;


    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
