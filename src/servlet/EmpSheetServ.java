package servlet;

import java.io.IOException;
import java.util.Collections;
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

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import config.TemplateEngineUtil;
import entities.Attendance;
import entities.Users;
import service.AttendanceService;

@WebServlet("/empsheet")
public class EmpSheetServ extends HttpServlet{
	
	private AttendanceService service;
	
	public EmpSheetServ() {
		this.service = new AttendanceService();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
	
		
		HttpSession session = request.getSession(false);
		
		Users userIn = Optional.ofNullable(session).map(s -> (Users) session.getAttribute(IndexServlet.USER_PARAM))
				.orElse(null);
		if (userIn == null || !userIn.hasRole("leader")) {
		response.sendRedirect("/WebFinal/index");
		return;
		}
		
		Attendance at = null;
		String typeParam = request.getParameter("type");
		String attendanceParam = request.getParameter("attendanceID");
		String userIDParam = request.getParameter("userID");
		
		if (StringUtils.isNotBlank(attendanceParam)) {
			switch (typeParam) {
			case "AMOD":
				at = service.findAttendanceByID(Integer.parseInt(attendanceParam));
				userIDParam = at.getUserID().toString();
				typeParam = "List";
				break;
				
			default:
				break;
			}
		}
		
		if (StringUtils.isNotBlank(userIDParam) && "List".equalsIgnoreCase(typeParam)) {			
			context.setVariable("attendance",service.findAttendanceByUser(Integer.parseInt(userIDParam)));
		}
		
		if (at == null) {
			at = new Attendance();
		}

		
		context.setVariable("at", at);
		engine.process("empsheet.html", context, response.getWriter());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("POST empsheet");
		
		Integer attendanceID = Integer.parseInt(request.getParameter("attendanceID"));
		Integer userID = Integer.parseInt(request.getParameter("userID"));
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer day = Integer.parseInt(request.getParameter("day"));
		String  started = request.getParameter("started");
		String  startOfBReak = request.getParameter("startOfBReak");
		String  finishOfBreak = request.getParameter("finishOfBreak");
		String  finished = request.getParameter("finished");
		String  daytypes = request.getParameter("daytypes");
		
		
		Attendance at = new Attendance();
		String atID = request.getParameter("attendanceID");
		if (StringUtils.isNotBlank(atID)) {
			at.setAttendanceID(attendanceID);
		}
		
		at.setUserID(userID);
		at.setYear(year);
		at.setMonth(month);
		at.setDay(day);
		at.setStarted(started);
		at.setStartOfBReak(startOfBReak);
		at.setFinishOfBreak(finishOfBreak);
		at.setFinished(finished);
		at.setTypeOfDay(daytypes);
		
		if (Objects.nonNull(at.getAttendanceID())) {
			service.updateAttendance(at);
		}
		
		response.sendRedirect("/WebFinal/empsheet");
	}
	
	
}
