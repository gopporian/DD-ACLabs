package de.dialogdata.aclabs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import de.dialogdata.aclabs.enums.DayOfWeek;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = ClassBE.FIND_BY_NAME, query = "Select e from ClassBE e where e.name = :"+ClassBE.FIND_BY_NAME_PARAM)})
public class ClassBE implements Serializable {

	public static final String FIND_BY_GROUP = "ClassBE.FIND_BY_GROUP";
	public static final String FIND_BY_GROUP_PARAM = "group";
	public static final String FIND_BY_DAY = "ClassBE.FIND_BY_DAY";
	public static final String FIND_BY_DAY_PARAM = "day";
	public static final String FIND_BY_NAME = "ClassBE.FIND_BY_NAME";
	public static final String FIND_BY_NAME_PARAM = "name";

	@Column
	private String name; // name = primary key?? vezi implementare
							// findClassesByDay(DayOfWeek day) din
							// ScheduleService
	@Column
	private DayOfWeek day;

	@ManyToOne(fetch = FetchType.EAGER)
	private GroupBE group;
	@Column
	private int beginHour;
	@Column
	private int endHour;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	private static final long serialVersionUID = 1989853529759603544L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public GroupBE getGroup() {
		return group;
	}

	public void setGroup(GroupBE group) {
		this.group = group;
	}

	public int getBeginHour() {
		return beginHour;
	}

	public void setBeginHour(int beginHour) {
		this.beginHour = beginHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}