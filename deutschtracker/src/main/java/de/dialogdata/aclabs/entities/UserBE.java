package de.dialogdata.aclabs.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import de.dialogdata.aclabs.enums.Profiles;

@Entity
@XmlRootElement

@NamedQueries({ @NamedQuery(name = UserBE.FIND_BY_GROUP, query = "Select e from UserBE e where e.group.id = :"+UserBE.FIND_BY_GROUP_GROUP_ID_PARAM),
		 		@NamedQuery(name = UserBE.FIND_BY_USERNAME, query = "Select e from UserBE e where e.userName = :"+UserBE.FIND_BY_USERNAME_PARAM) })
public class UserBE implements Serializable {

	private static final long serialVersionUID = 2518182912578595495L;

	public final static String FIND_BY_GROUP = "UserBE.FIND_BY_GROUP";
	public final static String FIND_BY_GROUP_GROUP_ID_PARAM = "groupid";
	public final static String FIND_BY_USERNAME_PARAM ="username";
	public final static String FIND_BY_USERNAME ="UserBE.FIND_BY_USERNAME";
	public static final String FIND_BY_FIRST_NAME = "UserBE.FIND_BY_FIRST_NAME";
	public static final String FIND_BY_FIRST_NAME_PARAM = "firstName";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;
	@Version
	@Column(name = "version")
	private int version = 0;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String userName;

	@Column
	private String password;
	
	@Column
	private Profiles profile;

	@Temporal(TemporalType.DATE)
	private Date lastLogin;

	@ManyToOne(fetch = FetchType.EAGER)
	private GroupBE group;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(final Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public GroupBE getGroup() {
		return this.group;
	}

	public void setGroup(final GroupBE group) {
		this.group = group;
	}

	public Profiles getProfile() {
		return profile;
	}

	public void setProfile(Profiles profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBE other = (UserBE) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profile != other.profile)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBE [id=" + id + ", version=" + version + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", profile=" + profile
				+ ", lastLogin=" + lastLogin + ", group=" + group + "]";
	}
	
	
}