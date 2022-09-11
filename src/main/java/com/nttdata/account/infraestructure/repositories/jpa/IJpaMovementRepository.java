package com.nttdata.account.infraestructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.account.infraestructure.models.MovementModel;

/**
 *
 * @author diego
 */
public interface IJpaMovementRepository extends JpaRepository<MovementModel, String> {

}
