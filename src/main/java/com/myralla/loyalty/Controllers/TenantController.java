package com.myralla.loyalty.Controllers;

import com.myralla.loyalty.Services.TenantService;
import com.myralla.loyalty.dto.TenantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/v1/api/tenant")
@RestController
public class TenantController {
    @Autowired
    private TenantService tenantService;

//    registers or create a new tenant
    @PostMapping(path = "registerTenant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerTenant(@RequestBody TenantDTO tenantDTO) throws Exception{
        return tenantService.createTenant(tenantDTO);
    }
}
