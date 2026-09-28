package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.FeatureFlag;
import com.featureflaglite.api.entity.FlagEnvironmentState;
import com.featureflaglite.api.repository.FeatureFlagRepository;
import com.featureflaglite.api.repository.FlagStateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlagStateServiceImpl
        extends FlagStateService {

    private final FlagStateRepository stateRepository;

    private final FeatureFlagRepository featureRepository;

    private final AuditService auditService;

    public FlagStateServiceImpl(
            FlagStateRepository stateRepository,
            FeatureFlagRepository featureRepository,
            AuditService auditService) {

        this.stateRepository = stateRepository;
        this.featureRepository = featureRepository;
        this.auditService = auditService;
    }

    @Override
    public FlagEnvironmentState setState(
            Long featureId,
            FlagEnvironmentState.Environment environment,
            Boolean enabled,
            BigDecimal rolloutPercentage,
            String changedBy) {

        FeatureFlag feature =
                featureRepository.findById(featureId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Feature flag not found"));

        if (enabled == null) {
            throw new RuntimeException(
                    "Enabled state is required");
        }

        if (rolloutPercentage == null) {
            rolloutPercentage = BigDecimal.ZERO;
        }

        if (rolloutPercentage.compareTo(
                BigDecimal.ZERO) < 0
                ||
                rolloutPercentage.compareTo(
                        new BigDecimal("100")) > 0) {

            throw new RuntimeException(
                    "Rollout percentage must be between 0 and 100");
        }

        FlagEnvironmentState state =
                stateRepository
                        .findByFeatureFlagIdAndEnvironment(
                                featureId,
                                environment)
                        .orElse(null);

        Boolean oldEnabled = null;

        BigDecimal oldRollout = null;

        if (state == null) {

            state = new FlagEnvironmentState();

            state.setFeatureFlag(feature);

            state.setEnvironment(environment);

        } else {

            oldEnabled = state.getEnabled();

            oldRollout =
                    state.getRolloutPercentage();
        }

        state.setEnabled(enabled);

        state.setRolloutPercentage(
                rolloutPercentage);

        state.setUpdatedBy(changedBy);

        FlagEnvironmentState saved =
                stateRepository.save(state);

        auditService.logChange(
                feature,
                environment,
                "UPDATE",
                oldEnabled,
                enabled,
                oldRollout,
                rolloutPercentage,
                changedBy);

        return saved;
    }

    @Override
    public List<FlagEnvironmentState> getByFeature(
            Long featureId) {

        return stateRepository
                .findByFeatureFlagId(featureId);
    }

    @Override
    public List<FlagEnvironmentState> getByEnvironment(
            FlagEnvironmentState.Environment environment) {

        return stateRepository
                .findByEnvironment(environment);
    }

    @Override
    public FlagEnvironmentState getState(
            Long featureId,
            FlagEnvironmentState.Environment environment) {

        return stateRepository
                .findByFeatureFlagIdAndEnvironment(
                        featureId,
                        environment)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Environment state not found"));
    }
}