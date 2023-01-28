/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.infraestructure.models;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devsu.account.domain.enums.AccountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "ACCOUNTS", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class AccountModel extends BaseEntityModel {

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "opening_balance", nullable = false)
    private BigDecimal openingBalance;

    @Column(nullable = false)
    private BigDecimal actualBalance;

    @Column(name = "daily_withdrawal_limit", nullable = false)
    private BigDecimal dailyWithdrawalLimit;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientModel client;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Collection<MovementModel> movements;

}
