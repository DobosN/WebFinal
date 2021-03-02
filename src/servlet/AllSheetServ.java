package servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import config.TemplateEngineUtil;
import entities.Users;
import service.AttendanceService;

@WebServlet("/allsheet")
public class AllSheetServ extends HttpServlet {

	private AttendanceService service;
	
	public AllSheetServ() {
		this.service = new AttendanceService();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		
		HttpSession session = request.getSession(false);
		
		Users user = Optional.ofNullable(session).map(s -> (Users) session.getAttribute(IndexServlet.USER_PARAM))
				.orElse(null);
	if (user == null || !user.hasRole("employe")) {
		response.sendRedirect("/WebFinal/index");
		return;
	}
		
		context.setVariable("attendance",service.findAttendanceByUser(user.getUserID()));
		engine.process("allsheet.html", context, response.getWriter());
	}
	
	
	
	
	
	
	
}
