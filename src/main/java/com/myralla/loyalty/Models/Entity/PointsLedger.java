package com.myralla.loyalty.Models.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "points_ledger")
@Getter
@Setter
public class PointsLedger {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "points")
    private Double points;

    @Column(name = "points_balance")
    private String pointsBalance;

    @Column(name = "expires_at")
    private String expiresAt;
    
    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}
