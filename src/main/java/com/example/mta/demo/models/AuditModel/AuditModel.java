package com.example.mta.demo.models.AuditModel;

import com.example.mta.demo.core.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"CreateAt", "updateAt"}, allowGetters = true)
public class AuditModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "create_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "update_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime  updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
