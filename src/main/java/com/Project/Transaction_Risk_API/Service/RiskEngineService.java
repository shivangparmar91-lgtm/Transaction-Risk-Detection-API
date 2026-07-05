package com.Project.Transaction_Risk_API.Service;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import com.Project.Transaction_Risk_API.Model.Enumm.RiskLevel;
import com.Project.Transaction_Risk_API.Model.Enumm.TransactionStatus;
import com.Project.Transaction_Risk_API.Repository.TransactionRepo;
import com.Project.Transaction_Risk_API.Service.Rules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RiskEngineService {

    @Autowired
    private TransactionRepo repo;

    @Autowired
    private AmountRisk amountRisk;

    @Autowired
    private CountryRisk countryRisk;

    @Autowired
    private DeviceIdRisk deviceIdRisk;

    @Autowired
    private NightRisk nightRisk;

    @Autowired
    private FrequentTransactionRisk frequentTransactionRisk;

    public int calculatedRisk(TransactionRequest request)
    {
        int riskScore = 0;

        riskScore += amountRisk.calculatedRisk(request);
        riskScore += countryRisk.calculatedRisk(request);
        riskScore += deviceIdRisk.calculatedRisk(request);
        riskScore += nightRisk.calculatedRisk(request);
        riskScore += frequentTransactionRisk.calculatedRisk(request);

        return riskScore;
    }

    public RiskLevel getRiskLevel(int score) {

        if (score <= 30) {
            return RiskLevel.LOW;
        } else if (score <= 70) {
            return RiskLevel.MEDIUM;
        } else {
            return RiskLevel.HIGH;
        }
    }
    public TransactionStatus getStatus(RiskLevel level) {

        if (level == RiskLevel.HIGH) {
            return TransactionStatus.BLOCKED;
        } else if (level == RiskLevel.MEDIUM) {
            return TransactionStatus.REVIEW;
        } else {
            return TransactionStatus.APPROVED;
        }
    }
}
