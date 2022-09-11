/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.infraestructure.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.nttdata.account.domain.enums.MovementType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "MOVEMENTS", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class MovementModel {

	@Id
	private String id;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private MovementType type;

	@Column(name = "movement_value", nullable = false)
	private BigDecimal movementValue;

	@Column(nullable = false)
	private BigDecimal balance;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private AccountModel account;

	@CreatedDate
	@Column(name = "movement_date", columnDefinition = "DATE")
	private LocalDate movementDate;

	@PrePersist
	public void prePersist() {
		id = UUID.randomUUID().toString();
		movementDate = LocalDate.now();
	}

}
