package com.featureflaglite.api.controller;

import com.featureflaglite.api.entity.FlagEnvironmentState;
import com.featureflaglite.api.service.FlagStateService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/flag-states")
public class FlagStateController {

    private final FlagStateService service;

    public FlagStateController(
            FlagStateService service) {

        this.service = service;
    }

    @PutMapping("/{featureId}/{environment}")
    public FlagEnvironmentState setState(
            @PathVariable Long featureId,
            @PathVariable
            FlagEnvironmentState.Environment environment,
            @RequestParam Boolean enabled,
            @RequestParam(
                    defaultValue = "0")
            BigDecimal rolloutPercentage,
            @RequestParam(
                    defaultValue = "admin")
            String changedBy) {

        return service.setState(
                featureId,
                environment,
                enabled,
                rolloutPercentage,
                changedBy);
    }

    @GetMapping("/feature/{featureId}")
    public List<FlagEnvironmentState> getByFeature(
            @PathVariable Long featureId) {

        return service.getByFeature(featureId);
    }

    @GetMapping("/environment/{environment}")
    public List<FlagEnvironmentState> getByEnvironment(
            @PathVariable
            FlagEnvironmentState.Environment environment) {

        return service.getByEnvironment(
                environment);
    }

    @GetMapping("/{featureId}/{environment}")
    public FlagEnvironmentState getState(
            @PathVariable Long featureId,
            @PathVariable
            FlagEnvironmentState.Environment environment) {

        return service.getState(
                featureId,
                environment);
    }
}