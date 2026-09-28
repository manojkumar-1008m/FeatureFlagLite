package com.featureflaglite.api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

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
    private FlagEnvironmentState.Environment environment;

    @Column(nullable = false, length = 50)
    private String action;

    @Column(name = "old_enabled")
    private Boolean oldEnabled;

    @Column(name = "new_enabled")
    private Boolean newEnabled;

    @Column(name = "old_rollout_percentage")
    private BigDecimal oldRolloutPercentage;

    @Column(name = "new_rollout_percentage")
    private BigDecimal newRolloutPercentage;

    @Column(name = "changed_by", nullable = false, length = 100)
    private String changedBy;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    @PrePersist
    public void prePersist() {
        if (changedAt == null) {
            changedAt = LocalDateTime.now();
        }
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

    public FlagEnvironmentState.Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(
            FlagEnvironmentState.Environment environment) {
        this.environment = environment;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getOldEnabled() {
        return oldEnabled;
    }

    public void setOldEnabled(Boolean oldEnabled) {
        this.oldEnabled = oldEnabled;
    }

    public Boolean getNewEnabled() {
        return newEnabled;
    }

    public void setNewEnabled(Boolean newEnabled) {
        this.newEnabled = newEnabled;
    }

    public BigDecimal getOldRolloutPercentage() {
        return oldRolloutPercentage;
    }

    public void setOldRolloutPercentage(
            BigDecimal oldRolloutPercentage) {
        this.oldRolloutPercentage = oldRolloutPercentage;
    }

    public BigDecimal getNewRolloutPercentage() {
        return newRolloutPercentage;
    }

    public void setNewRolloutPercentage(
            BigDecimal newRolloutPercentage) {
        this.newRolloutPercentage = newRolloutPercentage;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }
}