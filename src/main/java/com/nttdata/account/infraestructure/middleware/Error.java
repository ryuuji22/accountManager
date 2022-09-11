package com.nttdata.account.infraestructure.middleware;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error implements Serializable {

	private static final long serialVersionUID = -8313092981322430433L;

	private String message;
	private List<String> errors;
}