package de.dialogdata.aclabs.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.entities.GroupBE;
import de.dialogdata.aclabs.enums.CrudOperation;
import de.dialogdata.aclabs.enums.DayOfWeek;
import de.dialogdata.aclabs.exceptions.ClassExistsException;

@Stateless
@NamedQueries({ @NamedQuery(name = ClassBE.FIND_BY_GROUP, query = "Select e from ClassBE e where e.class.group = :"+ClassBE.FIND_BY_GROUP_PARAM) , 
                @NamedQuery(name = ClassBE.FIND_BY_DAY, query = "Select e from ClassBE e where e.class.day = :"+ClassBE.FIND_BY_DAY_PARAM) })
public class ClassService implements IClassService {

	private static final long serialVersionUID = 6537784655524040506L;

	public final static int PAGE_SIZE = 10;

	@EJB
	private IClassService classService;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ClassBE findClass(Long id) {
		ClassBE found = entityManager.find(ClassBE.class, id);
		return found;
	}
	
	@Override
	public ClassBE findClass(GroupBE group) {
		ClassBE found = entityManager.find(ClassBE.class, group);
		return found;
	}
	
	@Override
	public ClassBE findClass(DayOfWeek day) {
		ClassBE found = entityManager.find(ClassBE.class, day);
		return found;
	}

	@Override
	public List<ClassBE> paginate(int page, ClassBE searchParameters) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		// Populate count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<ClassBE> root = countCriteria.from(ClassBE.class);
		countCriteria = countCriteria.select(builder.count(root)).where(getSearchPredicates(searchParameters, root));

		// Populate pageItems

		CriteriaQuery<ClassBE> criteria = builder.createQuery(ClassBE.class);
		root = criteria.from(ClassBE.class);
		TypedQuery<ClassBE> query = entityManager.createQuery(criteria.select(root).where(
				getSearchPredicates(searchParameters, root)));
		query.setFirstResult(page * PAGE_SIZE).setMaxResults(PAGE_SIZE);
		List<ClassBE> items = query.getResultList();
		return items;
	}

	@Override
	public List<ClassBE> findAll() {
		CriteriaQuery<ClassBE> criteria = this.entityManager.getCriteriaBuilder().createQuery(ClassBE.class);
		List<ClassBE> result = entityManager.createQuery(criteria.select(criteria.from(ClassBE.class))).getResultList();
		return result;
	}

	@Override
	public CrudOperation createOrUpdate(ClassBE aClass) throws ClassExistsException {
		CrudOperation operation;
		if (aClass.getId() != null) {
			entityManager.merge(aClass);
			operation = CrudOperation.UPDATE;
		} else {
            assert aClass != null;
			ClassBE found = entityManager.find(ClassBE.class, aClass.getId ( ));
			if ( found != null ) throw new ClassExistsException ( "This Class already exists. " );
			entityManager.persist(aClass);
			operation = CrudOperation.CREATE;
		}
		return operation;
	}

	@Override
	public void deleteClass(Long id) {
		ClassBE deletableEntity = findClass(id);
		/*List<GroupBE> groups = classService.findAll();
		for (GroupBE group : groups) {
			group.setClass(null);
		}*/
		entityManager.remove(deletableEntity);
		entityManager.flush();
	}

	private Predicate[] getSearchPredicates(ClassBE searchParameters, Root<ClassBE> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		GroupBE group = searchParameters.getGroup();
		if (group != null) {
			predicatesList.add(builder.equal(root.<GroupBE> get("group"), group ));
		}
		DayOfWeek day = searchParameters.getDay();
		if (day != null) {
			predicatesList.add(builder.equal(root.get("day"), day));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

}