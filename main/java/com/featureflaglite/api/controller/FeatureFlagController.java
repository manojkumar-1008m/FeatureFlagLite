package com.featureflaglite.api.controller;

import com.featureflaglite.api.entity.FeatureFlag;
import com.featureflaglite.api.service.FeatureFlagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureFlagController {

    private final FeatureFlagService service;

    public FeatureFlagController(
            FeatureFlagService service) {

        this.service = service;
    }

    @PostMapping
    public FeatureFlag create(
            @RequestBody FeatureFlag featureFlag) {

        return service.create(featureFlag);
    }

    @GetMapping
    public List<FeatureFlag> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public FeatureFlag getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PutMapping("/{id}")
    public FeatureFlag update(
            @PathVariable Long id,
            @RequestBody FeatureFlag featureFlag) {

        return service.update(
                id,
                featureFlag);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        service.delete(id);

        return "Feature flag deleted successfully";
    }
}