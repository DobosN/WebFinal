package servlet;

import java.io.IOException;

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

@WebServlet({"/index","/"})
public class IndexServlet extends HttpServlet{
	
	private static final String LOGIN_ERROR_PARAM = "auth_error";
	public static final String USER_PARAM = "user";
	public static final String USERNAME_PARAM = "userName";
	public static final String PASSWORD_PARAM = "password";
	public static final String FIRST_NAME_PARAM = "firstName";
	public static final String LAST_NAME_PARAM = "lastName";
	public static final String EMAIL_PARAM = "email";
	
	
	private AttendanceService service;
	
	public IndexServlet() {
		this.service = new AttendanceService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
	
		context.setVariable(LOGIN_ERROR_PARAM,request.getParameter(LOGIN_ERROR_PARAM));
		engine.process("index.html", context, response.getWriter());
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter(USERNAME_PARAM);
		String pass = request.getParameter(PASSWORD_PARAM);
		System.out.println("LOGIN " +userName + "  " + pass);
		Users user = service.login(userName, pass);
		
		String redirectUrl = "";
		if (user != null && user.isActive()) {
			HttpSession session = request.getSession(true);
			session.setAttribute(USER_PARAM, user);
			session.setMaxInactiveInterval(6000);
			
			if (user.hasRole("employe")) {
				redirectUrl = "/WebFinal/employeMenu";
			}else if (user.hasRole("leader")) {
				redirectUrl = "/WebFinal/leaderMenu";
			}else if (user.hasRole("admin")) {
				redirectUrl = "/WebFinal/admin";
			}
			
		} else {
			redirectUrl = "/WebFinal/index?" + LOGIN_ERROR_PARAM + "=true";
		}
		response.sendRedirect(redirectUrl);
	}
		
	

}
