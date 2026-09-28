package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.FeatureFlag;
import com.featureflaglite.api.repository.FeatureFlagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureFlagServiceImpl
        extends FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagServiceImpl(
            FeatureFlagRepository repository) {

        this.repository = repository;
    }

    @Override
    public FeatureFlag create(
            FeatureFlag featureFlag) {

        if (repository.existsByName(
                featureFlag.getName())) {

            throw new RuntimeException(
                    "Feature flag already exists");
        }

        if (featureFlag.getDefaultState() == null) {
            featureFlag.setDefaultState(false);
        }

        return repository.save(featureFlag);
    }

    @Override
    public List<FeatureFlag> getAll() {
        return repository.findAll();
    }

    @Override
    public FeatureFlag getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Feature flag not found: " + id));
    }

    @Override
    public FeatureFlag update(
            Long id,
            FeatureFlag updatedFlag) {

        FeatureFlag existing = getById(id);

        if (!existing.getName()
                .equals(updatedFlag.getName())
                && repository.existsByName(
                        updatedFlag.getName())) {

            throw new RuntimeException(
                    "Feature flag name already exists");
        }

        existing.setName(updatedFlag.getName());

        existing.setDescription(
                updatedFlag.getDescription());

        existing.setDefaultState(
                updatedFlag.getDefaultState());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {

        FeatureFlag existing = getById(id);

        repository.delete(existing);
    }
}