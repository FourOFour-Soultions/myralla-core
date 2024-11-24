package com.myralla.loyalty.Models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenants")
@Getter
@Setter
public class Tenants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name = "org_key", unique = true, nullable = false)
    private String orgKey;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
    
    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_secret")
    private String apiSecret;
}
