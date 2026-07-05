package com.Project.Transaction_Risk_API.Service.Rules;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class NightRisk implements RiskEngineRules{
    @Override
    public int calculatedRisk(TransactionRequest request) {

        int hour = request.getTransactionTime().getHour();
        if (hour >= 0 && hour <= 5) {
            return  20;
        }
        return 0;
    }
}
