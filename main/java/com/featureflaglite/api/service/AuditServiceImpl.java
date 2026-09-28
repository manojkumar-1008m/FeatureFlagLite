package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.AuditLog;
import com.featureflaglite.api.entity.FeatureFlag;
import com.featureflaglite.api.entity.FlagEnvironmentState;
import com.featureflaglite.api.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AuditServiceImpl
        extends AuditService {

    private final AuditLogRepository repository;

    public AuditServiceImpl(
            AuditLogRepository repository) {

        this.repository = repository;
    }

    @Override
    public void logChange(
            FeatureFlag featureFlag,
            FlagEnvironmentState.Environment environment,
            String action,
            Boolean oldEnabled,
            Boolean newEnabled,
            BigDecimal oldRollout,
            BigDecimal newRollout,
            String changedBy) {

        AuditLog log = new AuditLog();

        log.setFeatureFlag(featureFlag);

        log.setEnvironment(environment);

        log.setAction(action);

        log.setOldEnabled(oldEnabled);

        log.setNewEnabled(newEnabled);

        log.setOldRolloutPercentage(
                oldRollout);

        log.setNewRolloutPercentage(
                newRollout);

        log.setChangedBy(changedBy);

        repository.save(log);
    }

    @Override
    public List<AuditLog> getAll() {

        return repository
                .findAllByOrderByChangedAtDesc();
    }

    @Override
    public List<AuditLog> getByFeature(
            Long featureId) {

        return repository
                .findByFeatureFlagIdOrderByChangedAtDesc(
                        featureId);
    }

    @Override
    public List<AuditLog> getByEnvironment(
            FlagEnvironmentState.Environment environment) {

        return repository
                .findByEnvironmentOrderByChangedAtDesc(
                        environment);
    }
}