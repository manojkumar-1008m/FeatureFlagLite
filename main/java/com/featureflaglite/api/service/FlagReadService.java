package com.featureflaglite.api.service;

import com.featureflaglite.api.entity.FlagEnvironmentState;

import java.util.Map;

public abstract class FlagReadService {

    public abstract Map<String, Boolean> getFlagsForEnvironment(
            FlagEnvironmentState.Environment environment);
}