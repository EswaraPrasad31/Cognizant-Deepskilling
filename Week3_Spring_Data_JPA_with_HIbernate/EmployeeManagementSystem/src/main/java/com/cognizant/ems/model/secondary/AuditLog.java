package com.cognizant.ems.model.secondary;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @Column(name = "log_action", nullable = false)
    private String action;

    @Column(name = "log_detail", nullable = false)
    private String detail;

    @Column(name = "log_timestamp")
    private LocalDateTime timestamp;

    public AuditLog() {
    }

    public AuditLog(String action, String detail) {
        this.action = action;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AuditLog [id=" + id + ", action=" + action + ", detail=" + detail + ", timestamp=" + timestamp + "]";
    }
}
