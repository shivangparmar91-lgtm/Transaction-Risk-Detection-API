package com.Project.Transaction_Risk_API.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardResponse {

    private long totalTransactions;

    private long approvedTransactions;

    private long reviewTransactions;

    private long blockedTransactions;

    private long highRiskTransactions;

    private double averageRiskScore;
}
