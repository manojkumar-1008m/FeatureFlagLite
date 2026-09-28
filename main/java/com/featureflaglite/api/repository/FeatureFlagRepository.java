package com.featureflaglite.api.repository;

import com.featureflaglite.api.entity.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureFlagRepository
        extends JpaRepository<FeatureFlag, Long> {

    boolean existsByName(String name);

    Optional<FeatureFlag> findByName(String name);
}