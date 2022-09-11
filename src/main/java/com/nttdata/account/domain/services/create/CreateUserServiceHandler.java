package com.nttdata.account.domain.services.create;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nttdata.account.domain.entities.Role;
import com.nttdata.account.domain.entities.User;
import com.nttdata.account.domain.exceptions.ApplicationDomainException;
import com.nttdata.account.domain.interfaces.repositories.IRoleRepository;

import io.jkratz.mediator.core.RequestHandler;
import lombok.SneakyThrows;

@Component
public class CreateUserServiceHandler implements RequestHandler<CreateUserService, User> {

	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	@SneakyThrows
	public User handle(CreateUserService command) {
		String encodedPass = passwordEncoder.encode(command.getPassword());
		User newUser = new User();
		newUser.setPassword(encodedPass);
		newUser.setIdentification(command.getIdentification());
		if (command.getRoles() != null)
			newUser.setRoles(this.getRoles(command.getRoles()));
		return newUser;

	}

	private Role validateRole(String name) {
		return roleRepository.getByName(name)
				.orElseThrow(() -> new ApplicationDomainException("The Role : " + name + " doesn't exist."));
	}

	private List<Role> getRoles(List<String> roles) {
		List<Role> rolesList = new ArrayList<>();
		for (String name : roles) {
			Role role = this.validateRole(name);
			rolesList.add(role);
		}
		return rolesList;
	}
}
