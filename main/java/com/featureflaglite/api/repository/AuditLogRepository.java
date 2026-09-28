package com.featureflaglite.api.repository;

import com.featureflaglite.api.entity.AuditLog;
import com.featureflaglite.api.entity.FlagEnvironmentState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findAllByOrderByChangedAtDesc();

    List<AuditLog> findByFeatureFlagIdOrderByChangedAtDesc(
            Long featureFlagId
    );

    List<AuditLog> findByEnvironmentOrderByChangedAtDesc(
            FlagEnvironmentState.Environment environment
    );
}