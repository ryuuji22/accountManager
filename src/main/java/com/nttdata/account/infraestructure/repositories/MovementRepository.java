/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.account.infraestructure.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.account.application.dtos.responses.MovementReport;
import com.nttdata.account.domain.entities.Movement;
import com.nttdata.account.domain.interfaces.repositories.IMovementRepository;
import com.nttdata.account.infraestructure.models.AccountModel;
import com.nttdata.account.infraestructure.models.ClientModel;
import com.nttdata.account.infraestructure.models.MovementModel;
import com.nttdata.account.infraestructure.persistance.mappers.MovementMapper;
import com.nttdata.account.infraestructure.persistance.mappers.MovementReportMapper;
import com.nttdata.account.infraestructure.repositories.jpa.IJpaMovementRepository;

/**
 *
 * @author diego
 */
@Repository
public class MovementRepository implements IMovementRepository {

	@Autowired
	private IJpaMovementRepository jpaMovementRepository;

	@Autowired
	private MovementMapper mapper;
	
	@Autowired
	private MovementReportMapper movementReportMapper;

	@Autowired
	private EntityManager entityManager;

	@Override
	public String create(Movement movement) {
		MovementModel movementModel = mapper.toMovementModel(movement);
		return jpaMovementRepository.save(movementModel).getId();
	}

	private List<MovementModel> getMovementCriteriaQuery(String identification, LocalDate dateBefore,
			LocalDate dateAfter) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MovementModel> criteriaQuery = criteriaBuilder.createQuery(MovementModel.class);

		Root<MovementModel> root = criteriaQuery.from(MovementModel.class);
		Join<AccountModel, MovementModel> joinAccount = root.join("account", JoinType.LEFT);
		Join<AccountModel, ClientModel> joinClient = joinAccount.join("client", JoinType.LEFT);

		List<Predicate> predicates = new ArrayList<>();

		Predicate enabledCondition = criteriaBuilder.isTrue(joinAccount.get("enabled"));
		predicates.add(enabledCondition);

		List<Order> orderList = new ArrayList<>();
		orderList.add(criteriaBuilder.asc(root.get("movementDate")));
		orderList.add(criteriaBuilder.asc(joinAccount.get("accountNumber")));

		if (identification != null) {
			Predicate identificationCondition = criteriaBuilder.equal(joinClient.get("identification"), identification);
			predicates.add(identificationCondition);
		}

		if (dateBefore != null && dateAfter != null) {
			Predicate dateCondition = criteriaBuilder.between(root.get("movementDate"), dateBefore, dateAfter);
			predicates.add(dateCondition);
		}

		if (!predicates.isEmpty()) {
			predicates.forEach(
					p -> criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).orderBy(orderList));

		}

		TypedQuery<MovementModel> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@Override
	public List<MovementReport> getByClientIdentificationAndStartDateAndEndDate(String identification, LocalDate dateBefore,
			LocalDate dateAfter) {
		return movementReportMapper.toMovementReports(this.getMovementCriteriaQuery(identification, dateBefore, dateAfter));
	}

}
