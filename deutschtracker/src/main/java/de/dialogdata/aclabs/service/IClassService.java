package de.dialogdata.aclabs.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.entities.GroupBE;
import de.dialogdata.aclabs.enums.CrudOperation;
import de.dialogdata.aclabs.enums.DayOfWeek;
import de.dialogdata.aclabs.exceptions.ClassExistsException;

@Local
public interface IClassService extends Serializable {

	public ClassBE findClass(Long id);
	
	public ClassBE findClass ( String name );
	
	public ClassBE findClass(GroupBE group);
	
	public ClassBE findClass(DayOfWeek day);

	public List<ClassBE> paginate(int page, ClassBE searchParameters);

	public List<ClassBE> findAll();

	public CrudOperation createOrUpdate(ClassBE group) throws ClassExistsException;

	public void deleteClass(Long id);

}
