package de.dialogdata.aclabs.filter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.service.IUserService;
import de.dialogdata.aclabs.utils.SecurityUtils;

@SessionScoped
@ManagedBean(name="auth")
public class Authorization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EJB
	private IUserService iUserService;
	
	
	private String user,password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String logIn()
	{
		HttpSession session = SecurityUtils.getSession();
//        session.setAttribute("user", user);
//        session.setAttribute("profile", password);
        
        UserBE userfound = iUserService.findUserByUsername(this.user);
		
        if(userfound!=null && userfound.getPassword().equals(password)){
        	return "/index.xhtml";
        }
        else   	
        {
        	FacesMessage msg = new FacesMessage("Login error! Try again", "ERROR MSG");
        	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        	FacesContext.getCurrentInstance().addMessage(null, msg);
        	return "/login.xhtml";
        }
	}
	
	 public String logout() {
	    HttpSession session = SecurityUtils.getSession();
	    session.invalidate();
	    
	    return "/login.xhtml";
	   }
	public boolean isLoggedIn() {
		
		return false;
	}

	
}
