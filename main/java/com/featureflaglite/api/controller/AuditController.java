package com.featureflaglite.api.controller;

import com.featureflaglite.api.entity.AuditLog;
import com.featureflaglite.api.entity.FlagEnvironmentState;
import com.featureflaglite.api.service.AuditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService service;

    public AuditController(
            AuditService service) {

        this.service = service;
    }

    @GetMapping
    public List<AuditLog> getAll() {

        return service.getAll();
    }

    @GetMapping("/feature/{featureId}")
    public List<AuditLog> getByFeature(
            @PathVariable Long featureId) {

        return service.getByFeature(
                featureId);
    }

    @GetMapping("/environment/{environment}")
    public List<AuditLog> getByEnvironment(
            @PathVariable
            FlagEnvironmentState.Environment environment) {

        return service.getByEnvironment(
                environment);
    }
}