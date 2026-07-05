package com.Project.Transaction_Risk_API.Service.Rules;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class AmountRisk implements RiskEngineRules{
    @Override
    public int calculatedRisk(TransactionRequest request) {

        if(request.getAmount() >= 100000)
        {
            return 40;
        }
        return 0;
    }
}
