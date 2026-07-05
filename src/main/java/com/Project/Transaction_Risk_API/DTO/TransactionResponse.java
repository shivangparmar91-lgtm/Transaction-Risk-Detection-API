package com.Project.Transaction_Risk_API.DTO;

import com.Project.Transaction_Risk_API.Model.Enumm.RiskLevel;
import com.Project.Transaction_Risk_API.Model.Enumm.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TransactionResponse {

    private long id;

    private Integer riskScore;

    private RiskLevel riskLevel;

    private TransactionStatus status;
}
