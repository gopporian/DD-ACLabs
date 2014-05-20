package de.dialogdata.aclabs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class AttendanceBE implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "date", updatable = true, nullable = false)
	private Date date;

	@OneToOne(fetch = FetchType.EAGER)
	private ClassBE curs;

	@OneToMany(fetch = FetchType.EAGER)
//	@Column
	private List<UserBE> users;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ClassBE getCurs() {
		return curs;
	}

	public void setCurs(ClassBE curs) {
		this.curs = curs;
	}

	public List<UserBE> getUsers() {
		return users;
	}

	public void setUsers(List<UserBE> users) {
		this.users = users;
	}

}
