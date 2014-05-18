package de.dialogdata.aclabs.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.enums.DayOfWeek;

@Stateless
public class ScheduleService implements IScheduleService {

	@EJB
	private IScheduleService scheduleService;

	@PersistenceContext
	private EntityManager entityManager;

	// @Override
	// public List<ClassBE> findClassesByMonth() {
	// CriteriaQuery<ClassBE> criteria = this.entityManager
	// .getCriteriaBuilder().createQuery(ClassBE.class);
	// List<ClassBE> result = entityManager.createQuery(
	// criteria.select(criteria.from(ClassBE.class))).getResultList();
	// return result;
	//
	// }
	// @Override
	// public List<ClassBE> findClassesByWeek() {
	// CriteriaQuery<ClassBE> criteria = this.entityManager
	// .getCriteriaBuilder().createQuery(ClassBE.class);
	// List<ClassBE> result = entityManager.createQuery(
	// criteria.select(criteria.from(ClassBE.class))).getResultList();
	// return result;
	// }
	@Override
	public List<ClassBE> findClassesByDay(DayOfWeek day) {
		// CriteriaQuery<ClassBE> criteria = this.entityManager
		// .getCriteriaBuilder().createQuery(ClassBE.class);
		// List<ClassBE> result = entityManager.createQuery(
		// criteria.select(criteria.from(ClassBE.class))).getResultList();
		// return result;

		TypedQuery<ClassBE> query = entityManager.createNamedQuery(
				ClassBE.FIND_BY_DAY, ClassBE.class);
		query.setParameter(ClassBE.FIND_BY_DAY_PARAM, day);

		return query.getResultList();
	}

	@Override
	public List<ClassBE> findAllClasses() {
		CriteriaQuery<ClassBE> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(ClassBE.class);
		List<ClassBE> result = entityManager.createQuery(
				criteria.select(criteria.from(ClassBE.class))).getResultList();
		return result;
	}

}
