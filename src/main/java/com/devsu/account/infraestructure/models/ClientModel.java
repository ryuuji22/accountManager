/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.account.infraestructure.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "CLIENTS", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ClientModel extends PersonModel {

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserModel user;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Collection<AccountModel> accounts;

	

}
