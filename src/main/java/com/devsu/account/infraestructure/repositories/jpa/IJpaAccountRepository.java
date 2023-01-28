package com.devsu.account.infraestructure.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.account.infraestructure.models.AccountModel;

/**
 *
 * @author diego
 */
public interface IJpaAccountRepository extends JpaRepository<AccountModel, String> {
	public Optional<AccountModel> findByAccountNumberAndEnabled(String accountNumber, Boolean enabled);

	public Optional<AccountModel> findByClientIdentificationAndAccountNumberAndEnabled(String identification,
			String accountNumber, Boolean enabled);
	
	public Optional<List<AccountModel>> findByClientIdentificationAndEnabled(String identification, Boolean enabled);

}
