/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsu.account.domain.entities.Account;
import com.devsu.account.domain.interfaces.repositories.IAccountRepository;
import com.devsu.account.infraestructure.persistance.mappers.AccountMapper;
import com.devsu.account.infraestructure.repositories.jpa.IJpaAccountRepository;

/**
 *
 * @author diego
 */
@Repository
public class AccountRepository implements IAccountRepository {

	@Autowired
	private IJpaAccountRepository jpaAccountRepository;

	@Autowired
	private AccountMapper mapper;

	@Override
	public String create(Account account) {
		var accountModel = mapper.toAccountModel(account);
		return jpaAccountRepository.save(accountModel).getId();
	}

	@Override
	public void delete(Account account) {
		account.setEnabled(Boolean.FALSE);
		this.jpaAccountRepository.save(mapper.toAccountModel(account));
	}

	@Override
	public void update(Account account) {
		this.jpaAccountRepository.save(mapper.toAccountModel(account));
	}

	@Override
	public Optional<Account> findByAccountNumber(String accountNumber) {
		return this.jpaAccountRepository.findByAccountNumberAndEnabled(
				accountNumber, Boolean.TRUE).map(mapper::toAccount);
	}

	@Override
	public Optional<Account> findByClientIdentificationAndAccountNumber(String identification, String accountNumber) {
		return this.jpaAccountRepository.findByClientIdentificationAndAccountNumberAndEnabled(identification,
				accountNumber, Boolean.TRUE).map(mapper::toAccount);
	}

	@Override
	public Optional<List<Account>> findByClientIdentification(String identification) {
		return this.jpaAccountRepository.findByClientIdentificationAndEnabled(identification,
				Boolean.TRUE).map(mapper::toAccounts);
	}

}
