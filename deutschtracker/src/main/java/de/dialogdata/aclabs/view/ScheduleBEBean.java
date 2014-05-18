package de.dialogdata.aclabs.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import de.dialogdata.aclabs.entities.ClassBE;
import de.dialogdata.aclabs.enums.DayOfWeek;
import de.dialogdata.aclabs.service.IScheduleService;

@Named
@SessionScoped
public class ScheduleBEBean implements Serializable {

	private ScheduleModel eventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();

	@EJB
	private IScheduleService scheduleService;

	public ScheduleBEBean() {
		eventModel = new DefaultScheduleModel();
		Calendar calendar = Calendar.getInstance();

		loadEvents(eventModel, calendar);

		// eventModel.addEvent(new
		// DefaultScheduleEvent("Champions League Match",
		// previousDay8Pm(), previousDay11Pm()));
		// eventModel.addEvent(new DefaultScheduleEvent("Birthday Party",
		// today1Pm(), today6Pm()));
		// eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys",
		// nextDay9Am(), nextDay11Am()));
		// eventModel.addEvent(new DefaultScheduleEvent(
		// "Plant the new garden stuff", theDayAfter3Pm(),
		// fourDaysLater3pm()));

	}

	private void loadEvents(ScheduleModel eventModel, Calendar calendar) {

		for (ClassBE c : scheduleService.findAllClasses()) {

			if (c.getDay().equals(DayOfWeek.Monday)) {

				Date start = setBeginDate(c.getBeginHour(), calendar);
				Date end = setEndDate(c.getEndHour(), calendar);
				eventModel.addEvent(new DefaultScheduleEvent(c.getName(),
						start, end));

			} else if (c.getDay().equals(DayOfWeek.Tuesday)) {

				Date start = setBeginDate(c.getBeginHour(), calendar);
				Date end = setEndDate(c.getEndHour(), calendar);
				eventModel.addEvent(new DefaultScheduleEvent(c.getName(),
						start, end));

			} else if (c.getDay().equals(DayOfWeek.Wednesday)) {

				Date start = setBeginDate(c.getBeginHour(), calendar);
				Date end = setEndDate(c.getEndHour(), calendar);
				eventModel.addEvent(new DefaultScheduleEvent(c.getName(),
						start, end));

			} else if (c.getDay().equals(DayOfWeek.Thursday)) {

				Date start = setBeginDate(c.getBeginHour(), calendar);
				Date end = setEndDate(c.getEndHour(), calendar);
				eventModel.addEvent(new DefaultScheduleEvent(c.getName(),
						start, end));

			} else if (c.getDay().equals(DayOfWeek.Friday)) {
				Date start = setBeginDate(c.getBeginHour(), calendar);
				Date end = setEndDate(c.getEndHour(), calendar);
				eventModel.addEvent(new DefaultScheduleEvent(c.getName(),
						start, end));
			}
		}
	}

	private Date setBeginDate(int start, Calendar calendar) {

		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DATE, 2);
		calendar.set(Calendar.HOUR_OF_DAY, start);

		return calendar.getTime();
	}

	private Date setEndDate(int end, Calendar calendar) {
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, end);

		return calendar.getTime();
	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
		// day of
		// month

		return date.getTime();
	}

	public Date getInitialDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY,
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar.getTime();
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	private Date previousDay8Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);

		return t.getTime();
	}

	//
	// private Date previousDay11Pm() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.AM_PM, Calendar.PM);
	// t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
	// t.set(Calendar.HOUR, 11);
	//
	// return t.getTime();
	// }
	//
	// private Date today1Pm() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.AM_PM, Calendar.PM);
	// t.set(Calendar.HOUR, 1);
	//
	// return t.getTime();
	// }
	//
	// private Date theDayAfter3Pm() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
	// t.set(Calendar.AM_PM, Calendar.PM);
	// t.set(Calendar.HOUR, 3);
	//
	// return t.getTime();
	// }
	//
	// private Date today6Pm() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.AM_PM, Calendar.PM);
	// t.set(Calendar.HOUR, 6);
	//
	// return t.getTime();
	// }
	//
	// private Date nextDay9Am() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.AM_PM, Calendar.AM);
	// t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
	// t.set(Calendar.HOUR, 9);
	//
	// return t.getTime();
	// }
	//
	// private Date nextDay11Am() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.AM_PM, Calendar.AM);
	// t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
	// t.set(Calendar.HOUR, 11);
	//
	// return t.getTime();
	// }
	//
	// private Date fourDaysLater3pm() {
	// Calendar t = (Calendar) today().clone();
	// t.set(Calendar.AM_PM, Calendar.PM);
	// t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
	// t.set(Calendar.HOUR, 3);
	//
	// return t.getTime();
	// }

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
