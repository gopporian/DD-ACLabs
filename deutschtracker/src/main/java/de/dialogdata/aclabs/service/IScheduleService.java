package de.dialogdata.aclabs.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import de.dialogdata.aclabs.entities.AttendanceBE;
import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.enums.DayOfWeek;

@Local
public interface IScheduleService extends Serializable {

	public List<ClassBE> findAllClasses();

	public List<ClassBE> findClassesByDay(DayOfWeek day);
	
	public List<AttendanceBE> findAttendanceUsers(Date start, Date end);

}
