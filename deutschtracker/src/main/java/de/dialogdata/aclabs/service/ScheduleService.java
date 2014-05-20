package de.dialogdata.aclabs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import de.dialogdata.aclabs.entities.AttendanceBE;
import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.enums.DayOfWeek;
import de.dialogdata.aclabs.view.RaportBean;

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
	
	public List<AttendanceBE> findAttendanceUsers(Date start, Date end){
		
		List<AttendanceBE> listUsers = new ArrayList<AttendanceBE>();
		TypedQuery<AttendanceBE> query =  entityManager.createNamedQuery(RaportBean.FIND_BY_DATE,AttendanceBE.class);
		query.setParameter(RaportBean.FIND_BY_DATE_START_PARAM, start);
		query.setParameter(RaportBean.FIND_BY_DATE_END_PARAM, end);
		if(query!=null)
			System.out.println("fdsfsdfsdf");
		List<AttendanceBE> result = query.getResultList();
		if (result.iterator().hasNext())
		{
			listUsers.add(result.iterator().next());
		}
		return null;
	
}

}
