package de.dialogdata.aclabs.listner;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.exceptions.UserExistException;
import de.dialogdata.aclabs.service.IUserService;
import de.dialogdata.aclabs.utils.SecurityUtils;

@WebListener
public class InitializationListner implements ServletContextListener{

	private static final String ADMIN = "Admin";
	@EJB
	private IUserService iUserService;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		UserBE adminUser = new UserBE();
		adminUser.setFirstName(ADMIN);
		adminUser.setUserName(ADMIN);
		adminUser.setPassword(SecurityUtils.encryptString(ADMIN));
		try {
			iUserService. createOrUpdate(adminUser);
		} catch (UserExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
