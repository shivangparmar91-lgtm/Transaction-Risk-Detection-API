package com.Project.Transaction_Risk_API.Repository;

import com.Project.Transaction_Risk_API.Model.Enumm.RiskLevel;
import com.Project.Transaction_Risk_API.Model.Enumm.TransactionStatus;
import com.Project.Transaction_Risk_API.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    public long countBySenderAccountAndTransactionTimeGreaterThanEqual(String senderAccount, LocalDateTime transactionTime);

    @Query(" SELECT COUNT(DISTINCT t.senderAccount) FROM Transaction t WHERE t.deviceId = :deviceId ")
    long countDistinctSenderByDeviceId(@Param("deviceId") String deviceId);


    public List<Transaction> findByStatus(TransactionStatus status);

    long countByStatus(TransactionStatus transactionStatus);

    long countByRiskLevel(RiskLevel riskLevel);

    @Query("SELECT AVG(t.riskScore) FROM Transaction t")
    Double getAvgRisk();
}
