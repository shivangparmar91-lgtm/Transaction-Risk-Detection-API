package com.Project.Transaction_Risk_API.Service.Rules;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class CountryRisk implements RiskEngineRules{
    @Override
    public int calculatedRisk(TransactionRequest request) {

        if (request.getCountry().equalsIgnoreCase("Russia")
                || request.getCountry().equalsIgnoreCase("Nigeria")) {
            return 25;
        }
        return 0;
    }
}
