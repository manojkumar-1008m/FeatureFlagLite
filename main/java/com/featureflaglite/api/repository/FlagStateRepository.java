package com.featureflaglite.api.repository;

import com.featureflaglite.api.entity.FlagEnvironmentState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlagStateRepository
        extends JpaRepository<FlagEnvironmentState, Long> {

    Optional<FlagEnvironmentState> findByFeatureFlagIdAndEnvironment(
            Long featureFlagId,
            FlagEnvironmentState.Environment environment
    );

    List<FlagEnvironmentState> findByEnvironment(
            FlagEnvironmentState.Environment environment
    );

    List<FlagEnvironmentState> findByFeatureFlagId(
            Long featureFlagId
    );
}