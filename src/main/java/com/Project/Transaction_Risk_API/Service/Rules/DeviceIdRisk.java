package com.Project.Transaction_Risk_API.Service.Rules;

import com.Project.Transaction_Risk_API.DTO.TransactionRequest;
import com.Project.Transaction_Risk_API.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceIdRisk implements RiskEngineRules{

    @Autowired
    private TransactionRepo repo;

    @Override
    public int calculatedRisk(TransactionRequest request) {

        long AccCount = repo.countDistinctSenderByDeviceId(request.getDeviceId());
        if(AccCount >= 3)
        {
            return  30;
        }
        return 0;
    }
}
