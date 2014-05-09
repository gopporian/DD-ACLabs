package de.dialogdata.aclabs.common;

import java.io.Serializable;

import javax.inject.Named;

import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.enums.Profiles;
import de.dialogdata.aclabs.utils.SecurityUtils;

/**
 * Class which checks the permissions of an user
 * 
 * Current permissions :
 * 
 * read | create | delete | update | student X | - | - | - | teacher X | X | X |
 * X |
 */

@Named
public class AbstractBEBean implements Serializable {
	
	public boolean canRead() {
		return true;
	}

	public boolean canDelete() {
		return isTeacher();
	}

	public boolean getCanCreate() {
		return isTeacher();
	}

	public boolean getCanUpdate() {
		return isTeacher();
	}

	private boolean isTeacher() {
		UserBE logedinUser = (UserBE) SecurityUtils.getSession().getAttribute("user");
		return logedinUser.getProfile().equals(Profiles.teacher);
	}

	private static final long serialVersionUID = 7699425363999956376L;

}