package de.dialogdata.aclabs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import de.dialogdata.aclabs.enums.Level;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = GroupBE.FIND_BY_NAME, query = "Select e from GroupBE e where e.name = :"+GroupBE.FIND_BY_NAME_PARAM)})
public class GroupBE implements Serializable {

	private static final long serialVersionUID = -420738800030250028L;
	
	public static final String FIND_BY_NAME = "GroupBE.FIND_BY_NAME";
	public static final String FIND_BY_NAME_PARAM = "group";
	public static final String FIND_BY_CLASS = "GroupBE.FIND_BY_CLASS";
	public static final String FIND_BY_CLASS_PARAM = "aClass";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;
	@Version
	@Column(name = "version")
	private int version = 0;

	@Column
	private String name;

	@Column
	private Level level;

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

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		if (id != null) {
			return id.equals(((GroupBE) that).id);
		}
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Level getLevel() {
		return this.level;
	}

	public void setLevel(final Level level) {
		this.level = level;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "name: " + name;
		return result;
	}

}