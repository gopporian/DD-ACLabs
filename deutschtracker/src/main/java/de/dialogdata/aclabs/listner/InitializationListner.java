package de.dialogdata.aclabs.listner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.dialogdata.aclabs.entities.AttendanceBE;
import de.dialogdata.aclabs.entities.ClassBE;
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
		AttendanceBE attendanceBE = new AttendanceBE();
		
		Date d = new Date();
		attendanceBE.setDate(d);
		
		ClassBE curs = new ClassBE();
		attendanceBE.setCurs(curs );
		
		List<UserBE> users = new ArrayList<UserBE>();
		users.add(anUser);
		users.add(adminUser);
		attendanceBE.setUsers(users);
		
		
	}
	

}
