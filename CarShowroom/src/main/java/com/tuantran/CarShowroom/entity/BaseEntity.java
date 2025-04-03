package com.tuantran.CarShowroom.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
@DynamicInsert // All SQL insert will be INSERT columns that are not NULL
@DynamicUpdate // All SQL update will be UPDATE columns that are not NULL
@EntityListeners(AuditingEntityListener.class) // JPA Auditing (It's used for create_date, update_date)
@ToString
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false, unique = true, updatable = false)
    private String externalId;

    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name = "updated_date", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    // @ColumnDefault("true") // why this doesn't work
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @PrePersist
    protected void generateDefaultValue() {
        if (this.externalId == null) {
            this.externalId = UUID.randomUUID().toString();
        }

        if (this.active == null) {
            this.active = true;
        }
    }
}
