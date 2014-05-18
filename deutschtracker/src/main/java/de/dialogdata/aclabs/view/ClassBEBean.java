package de.dialogdata.aclabs.view;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import de.dialogdata.aclabs.common.AbstractBEBean;
import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.entities.GroupBE;
import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.enums.CrudOperation;
import de.dialogdata.aclabs.exceptions.ClassExistsException;
import de.dialogdata.aclabs.service.GroupService;
import de.dialogdata.aclabs.service.IClassService;
import de.dialogdata.aclabs.service.IGroupService;

/**
 * Backing bean for ClassBE entities.
 * <p>
 * This class provides CRUD functionality for all ClassBE entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@SessionScoped
public class ClassBEBean extends AbstractBEBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private IGroupService groupService;
	
	@EJB
	private IClassService classService;

	private Long id;

	private ClassBE classBE;

	private ClassBE example = new ClassBE();

	private int page;
	private long count;
	private List<ClassBE> pageItems;

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return GroupService.PAGE_SIZE;
	}

	public ClassBE getExample() {
		return this.example;
	}

	public void setExample(ClassBE example) {
		this.example = example;
	}

	public void search() {
		this.page = 0;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClassBE getClassBE() {
		return this.classBE;
	}

	
	public void setClassBE(ClassBE classBE) {
		this.classBE = classBE;
	}

	public String create() {
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}
		if (this.id == null) {
			this.classBE = this.example;
		} else {
			this.classBE = classService.findClass(id);
			if (classBE == null) {
				classBE = new ClassBE();
				example = new ClassBE();
			}
		}
	}

	public ClassBE findById(Long id) {
		return classService.findClass(getId());
	}

	/*
	 * Support updating and deleting GroupBE entities
	 */

	@SuppressWarnings("incomplete-switch")
	public String update() {
		try {
			CrudOperation result = classService.createOrUpdate(classBE);
			switch (result) {
			case CREATE:
				this.example = new ClassBE ( );
				return "search?faces-redirect=true";
			case UPDATE:
				return "view?faces-redirect=true&id=" + classBE.getId();
			}
		} catch ( ClassExistsException e )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));	
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String delete() {
		try {
			classService.deleteClass(getId());
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public void paginate() {
		pageItems = classService.paginate(page, example);
		count = pageItems.size();
		example = new ClassBE();
		id = null;
	}

	public List<ClassBE> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	public List<ClassBE> getAll() {
		return classService.findAll();
	}
	
	public List<GroupBE> getGroups(){
		return groupService.getGroupsForClass(getId());
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String value) {

				return classService.findClass(Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context, UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((ClassBE) value).getId());
			}
		};
	}

}