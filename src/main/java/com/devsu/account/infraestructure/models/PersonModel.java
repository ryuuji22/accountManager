/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.account.infraestructure.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class PersonModel extends BaseEntityModel {

	@Column(nullable = false)
	private String identification;

	@Column(nullable = false)
	private String names;

	private String email;

	private String address;

	@Column(nullable = false)
	private String phone;

	private Integer age;
	

}
