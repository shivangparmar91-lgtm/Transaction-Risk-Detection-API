package com.Project.Transaction_Risk_API.Service.Rules;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public interface RiskEngineRules {
    public int calculatedRisk(TransactionRequest request);
}
