package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.FeatureFlag;

import java.util.List;

public abstract class FeatureFlagService {

    public abstract FeatureFlag create(
            FeatureFlag featureFlag);

    public abstract List<FeatureFlag> getAll();

    public abstract FeatureFlag getById(Long id);

    public abstract FeatureFlag update(
            Long id,
            FeatureFlag featureFlag);

    public abstract void delete(Long id);
}