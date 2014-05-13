package de.dialogdata.aclabs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import de.dialogdata.aclabs.enums.DayOfWeek;

@Entity
@XmlRootElement
public class ClassBE implements Serializable {
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

	// @Override
	// public String toString() {
	// String result = getClass().getSimpleName() + " ";
	// if (day != null)
	// result += "day : " + day;
	// if (group != null)
	// result += "group : " + group;
	// if (beginHour != 0 && endHour != 0)
	// result += " ( " + beginHour + " to " + endHour + " ) ";
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object that) {
	// if (this == that) {
	// return true;
	// }
	// if (that == null) {
	// return false;
	// }
	// if (getClass() != that.getClass()) {
	// return false;
	// }
	// if (id != null) {
	// return id.equals(((ClassBE) that).id);
	// }
	// return super.equals(that);
	// }
	//
	// @Override
	// public int hashCode() {
	// if (id != null) {
	// return id.hashCode();
	// }
	// return super.hashCode();
	// }

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