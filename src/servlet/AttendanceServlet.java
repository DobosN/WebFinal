package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import config.TemplateEngineUtil;
import entities.Attendance;
import entities.Month;
import entities.Users;
import service.AttendanceService;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet{
	
	private static final String WARRNING_SAVE_ATTENDANCE = null;
	private AttendanceService service;
	
	public AttendanceServlet() {
		this.service = new AttendanceService();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		
		HttpSession session = request.getSession(false);
		
		Users userIn = Optional.ofNullable(session).map(s -> (Users) session.getAttribute(IndexServlet.USER_PARAM))
				.orElse(null);
		
		if (userIn == null || !userIn.hasRole("employe")) {
		response.sendRedirect("/WebFinal/index");
		return;
		}
		
		Attendance at = null;
		String typeParam = request.getParameter("type");
		String attendanceParam = request.getParameter("attendanceID");
		
		
		if (StringUtils.isNotBlank(attendanceParam)) {
			switch (typeParam) {
			case "AMOD":
				at = service.findAttendanceByID(Integer.parseInt(attendanceParam));	
				break;
			default:
				break;
			}
		}
		
		if (at == null) {
			at = new Attendance();
		}
		
		LocalDate ld = LocalDate.now();
		LocalDate dt = LocalDate.now();
		Integer y = dt.getYear();
		Integer m = dt.getMonthValue();
		String year = y.toString();
		String month ="";
		
		List<Month> mlist = service.findAllMonth();
		
		for (Month mvalue : mlist) {
			if (mvalue.getMonthID().equals(m)) {
				month = mvalue.getNameOfMonth();
			}
		}
		
		
		context.setVariable("year", year);
		context.setVariable("month", month);
		context.setVariable("attendance",service.findAttendanceByDate(userIn.getUserID()));
		context.setVariable("String", service.sumHoursOfMonth(userIn.getUserID()));
//		context.setVariable("daytypes", service.findAllDayTypes());
//		System.out.println(service.findAllDayTypes());
		context.setVariable("at", at);
		engine.process("attendance.html", context, response.getWriter());
		System.out.println("End of GET "+userIn);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("POST attendance");
		
		Integer attendanceID = Integer.parseInt(request.getParameter("attendanceID"));
		Integer userID = Integer.parseInt(request.getParameter("userID"));
		String  started = request.getParameter("started");
		String  startOfBReak = request.getParameter("startOfBReak");
		String  finishOfBreak = request.getParameter("finishOfBreak");
		String  finished = request.getParameter("finished");
		String  hoursOfDay = request.getParameter("hoursOfDay");
		String  daytypes = request.getParameter("daytype");
		
		
		Attendance at = new Attendance();
		String atID = request.getParameter("attendanceID");
		if (StringUtils.isNotBlank(atID)) {
			at.setAttendanceID(attendanceID);
		}
		
		at.setUserID(userID);
		at.setStarted(started);
		at.setStartOfBReak(startOfBReak);
		at.setFinishOfBreak(finishOfBreak);
		at.setFinished(finished);
		at.setTypeOfDay(daytypes);
		
		System.out.println("POST" +at);
		if (Objects.nonNull(at.getAttendanceID())) {
			service.updateAttendance(at);
		}
		
		response.sendRedirect("/WebFinal/attendance");
	}
	
	
}
