package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.FlagEnvironmentState;

import java.math.BigDecimal;
import java.util.List;

public abstract class FlagStateService {

    public abstract FlagEnvironmentState setState(
            Long featureId,
            FlagEnvironmentState.Environment environment,
            Boolean enabled,
            BigDecimal rolloutPercentage,
            String changedBy);

    public abstract List<FlagEnvironmentState> getByFeature(
            Long featureId);

    public abstract List<FlagEnvironmentState> getByEnvironment(
            FlagEnvironmentState.Environment environment);

    public abstract FlagEnvironmentState getState(
            Long featureId,
            FlagEnvironmentState.Environment environment);
}