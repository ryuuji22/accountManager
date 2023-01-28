/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.account.domain.entities;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Setter
@Getter
public class User {

    private String id;
    private String identification;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean enabled;
    private Date createdAt;
    private Collection<Role> roles;

}
