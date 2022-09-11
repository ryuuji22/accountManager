package com.nttdata.account.infraestructure.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntityModel  {

    @Id
    private String id;

    private Boolean enabled;

	@CreatedDate
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = new Date(System.currentTimeMillis());
        this.id = UUID.randomUUID().toString();
        this.enabled = Boolean.TRUE;
    }

}
