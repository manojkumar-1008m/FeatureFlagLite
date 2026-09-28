package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.AuditLog;
import com.featureflaglite.api.entity.FeatureFlag;
import com.featureflaglite.api.entity.FlagEnvironmentState;

import java.math.BigDecimal;
import java.util.List;

public abstract class AuditService {

    public abstract void logChange(
            FeatureFlag featureFlag,
            FlagEnvironmentState.Environment environment,
            String action,
            Boolean oldEnabled,
            Boolean newEnabled,
            BigDecimal oldRollout,
            BigDecimal newRollout,
            String changedBy);

    public abstract List<AuditLog> getAll();

    public abstract List<AuditLog> getByFeature(
            Long featureId);

    public abstract List<AuditLog> getByEnvironment(
            FlagEnvironmentState.Environment environment);
}