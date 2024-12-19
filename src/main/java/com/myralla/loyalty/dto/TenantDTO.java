package com.myralla.loyalty.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantDTO {
    private String orgKey;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    public TenantDTO() {
        this.description = "No Description Provided Upon Registration";
    }
}