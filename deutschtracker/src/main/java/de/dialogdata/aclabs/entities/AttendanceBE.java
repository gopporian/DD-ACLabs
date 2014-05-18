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

	@Column
	private List<UserBE> users;

}
