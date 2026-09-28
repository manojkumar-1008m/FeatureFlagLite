package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.FeatureFlag;
import com.featureflaglite.api.entity.FlagEnvironmentState;
import com.featureflaglite.api.repository.FeatureFlagRepository;
import com.featureflaglite.api.repository.FlagStateRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlagReadServiceImpl
        extends FlagReadService {

    private final FeatureFlagRepository featureRepository;

    private final FlagStateRepository stateRepository;

    public FlagReadServiceImpl(
            FeatureFlagRepository featureRepository,
            FlagStateRepository stateRepository) {

        this.featureRepository = featureRepository;

        this.stateRepository = stateRepository;
    }

    @Override
    public Map<String, Boolean> getFlagsForEnvironment(
            FlagEnvironmentState.Environment environment) {

        List<FeatureFlag> features =
                featureRepository.findAll();

        Map<String, Boolean> result =
                new HashMap<>();

        for (FeatureFlag feature : features) {

            FlagEnvironmentState state =
                    stateRepository
                            .findByFeatureFlagIdAndEnvironment(
                                    feature.getId(),
                                    environment)
                            .orElse(null);

            if (state != null) {

                result.put(
                        feature.getName(),
                        state.getEnabled());

            } else {

                result.put(
                        feature.getName(),
                        feature.getDefaultState());
            }
        }

        return result;
    }
}