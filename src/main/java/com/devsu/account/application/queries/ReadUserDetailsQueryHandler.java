package com.devsu.account.application.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.devsu.account.domain.entities.User;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.domain.interfaces.repositories.IUserRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadUserDetailsQueryHandler implements RequestHandler<ReadUserDetailsQuery, User> {

	@Autowired
    private IUserRepository userRepository;

    @Override
    public User handle(ReadUserDetailsQuery query) {
    	 String identification = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return userRepository.getByIdentification(identification)
        		 .orElseThrow(() -> new ApplicationDomainException(
        					"User with identification " + identification + " doesn't exist."));
    }

}
