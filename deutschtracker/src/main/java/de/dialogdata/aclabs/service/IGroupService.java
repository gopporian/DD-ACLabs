package de.dialogdata.aclabs.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import de.dialogdata.aclabs.entities.GroupBE;
import de.dialogdata.aclabs.enums.CrudOperation;

@Local
public interface IGroupService extends Serializable {

	public GroupBE findGroup(Long id);

	public List<GroupBE> paginate(int page, GroupBE searchParameters);

	public List<GroupBE> findAll();

	public CrudOperation createOrUpdate(GroupBE group) throws GroupExistException;

	public void deleteGroup(Long id);

}
