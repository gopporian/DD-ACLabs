package de.dialogdata.aclabs.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import de.dialogdata.aclabs.entities.AttendanceBE;
import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.entities.UserBE;
import de.dialogdata.aclabs.service.IScheduleService;
import de.dialogdata.aclabs.service.IUserService;

@NamedQuery(name = RaportBean.FIND_BY_DATE, query = "Select e from AttendanceBE e where e.date BETWEEN "+RaportBean.FIND_BY_DATE_START_PARAM 
													+"AND"+	RaportBean.FIND_BY_DATE_END_PARAM)
@ManagedBean(name="raportBean")
public class RaportBean {
		
	private CalendarBean calendarbean;
	private EntityManager entityManager;
	@EJB
	private IScheduleService iScheduleService;
	@EJB
	private IUserService iUserService;
	public final static String FIND_BY_DATE = "AttendanceBE.FIND_BY_DATE";
	public final static String FIND_BY_DATE_START_PARAM ="start";
	public final static String FIND_BY_DATE_END_PARAM = "end";
	private int [] attendanceVector = new int[100];
	private int nrOfClassesAttended;
	private List<UserBE> usersPerAttendance;
	public String generate()
	{
		
		List<AttendanceBE> allAttendance = iScheduleService.findAttendanceUsers(calendarbean.getStartDate(),calendarbean.getEndDate());
		for (int i = 0; i <allAttendance.size(); i++) {
			AttendanceBE eachAttendance = allAttendance.get(i);
			//long idCurs = eachAttendance.getCurs().getId();
			//int idCursInt = (int)idCurs;
			usersPerAttendance = eachAttendance.getUsers();
		}
			/*for(int j=0;j< usersPerAttendance.size();j++)
			{
				UserBE eachUser = usersPerAttendance.get(j);
				long idUser = eachUser.getId();
				int idUserInt = (int)idUser;
				attendanceVector[idUserInt] ++;
				
			}
			*/
			//for(int c = 0; c< iUserService.findAll().size(); c++)
			//nrOfClassesAttended = attendanceVector[iUserService.findAll().get(index)]
		//	eachAttendance.getUsers();
			//int hoursSpent = (eachAttendance.getCurs().getEndHour()-eachAttendance.getCurs().getBeginHour()) * ;
		//}
		
		//}
		return null;
		
	}
	
	public List<UserBE> getUsers()
	{
		return usersPerAttendance;
	}
	

}
