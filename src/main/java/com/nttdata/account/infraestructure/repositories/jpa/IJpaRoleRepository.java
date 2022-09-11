/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.account.infraestructure.repositories.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.account.infraestructure.models.RoleModel;

/**
 *
 * @author diego
 */
public interface IJpaRoleRepository extends JpaRepository<RoleModel, String> {
    public Optional<RoleModel> findByName(String name);

}
