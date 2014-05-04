package de.dialogdata.aclabs.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import de.dialogdata.aclabs.entities.GroupBE;
import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.enums.CrudOperation;
import de.dialogdata.aclabs.exceptions.GroupExistsException;

@Local
public interface IGroupService extends Serializable {

	public GroupBE findGroup(Long id);

	public List<GroupBE> paginate(int page, GroupBE searchParameters);

	public List<GroupBE> findAll();

	public CrudOperation createOrUpdate(GroupBE group) throws GroupExistsException;

	public void deleteGroup(Long id);
	
	public List<GroupBE> getGroupsForClass(Long classId);

}
