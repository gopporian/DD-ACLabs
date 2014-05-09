package de.dialogdata.aclabs.listner;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.enums.Profiles;
import de.dialogdata.aclabs.exceptions.UserExistsException;
import de.dialogdata.aclabs.service.IUserService;
import de.dialogdata.aclabs.utils.SecurityUtils;

@WebListener
public class InitializationListner implements ServletContextListener{

	private static final String ADMIN = "admin";
	private static final String USER = "user";
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
		adminUser.setProfile(Profiles.teacher);
		adminUser.setPassword(SecurityUtils.encryptString(ADMIN));
		try {
			iUserService. createOrUpdate(adminUser);
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserBE anUser = new UserBE ( );
		anUser.setFirstName(USER);
		anUser.setUserName(USER);
		anUser.setProfile(Profiles.student);
		anUser.setPassword(SecurityUtils.encryptString(USER));
		try {
			iUserService. createOrUpdate(anUser);
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
