package com.devsu.account.domain.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import com.devsu.account.domain.entities.Account;

public interface IAccountRepository {

	String create(Account account);

	void delete(Account account);

	void update(Account account);

	Optional<Account> findByAccountNumber(String accountNumber);
	
	Optional<List<Account>> findByClientIdentification(String identification);
	
	Optional<Account> findByClientIdentificationAndAccountNumber(String identification, String accountNumber);

}
