package com.featureflaglite.api.controller;

import com.featureflaglite.api.entity.FlagEnvironmentState;
import com.featureflaglite.api.service.FlagReadService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/flags")
public class FlagReadController {

    private final FlagReadService service;

    public FlagReadController(
            FlagReadService service) {

        this.service = service;
    }

    @GetMapping
    public Map<String, Boolean> getFlags(
            @RequestParam
            FlagEnvironmentState.Environment environment) {

        return service.getFlagsForEnvironment(
                environment);
    }
}