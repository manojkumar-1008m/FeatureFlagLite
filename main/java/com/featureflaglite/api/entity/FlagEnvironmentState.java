package com.featureflaglite.api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "flag_environment_state",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_flag_environment",
            columnNames = {"feature_flag_id", "environment"}
        )
    }
)
public class FlagEnvironmentState {

    public enum Environment {
        DEV,
        TEST,
        PROD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "feature_flag_id",
        nullable = false
    )
    private FeatureFlag featureFlag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    @Column(nullable = false)
    private Boolean enabled = false;

    @Column(name = "rollout_percentage", nullable = false)
    private BigDecimal rolloutPercentage = BigDecimal.ZERO;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public FeatureFlag getFeatureFlag() {
        return featureFlag;
    }

    public void setFeatureFlag(FeatureFlag featureFlag) {
        this.featureFlag = featureFlag;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getRolloutPercentage() {
        return rolloutPercentage;
    }

    public void setRolloutPercentage(BigDecimal rolloutPercentage) {
        this.rolloutPercentage = rolloutPercentage;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}