/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.account.infraestructure.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.account.infraestructure.models.UserModel;

/**
 *
 * @author diego
 */
public interface IJpaUserRepository extends JpaRepository<UserModel, String> {
    public Optional<UserModel> findByIdentificationAndEnabled(String identification,Boolean enabled);
    public Optional<List<UserModel>> findByEnabled(Boolean enabled);

}
