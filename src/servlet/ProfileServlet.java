package servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import config.TemplateEngineUtil;
import entities.Users;
import service.AttendanceService;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{

	private static final String MISSING_FIELDS = null;
	
	private AttendanceService service;
	
	public ProfileServlet() {
		this.service = new AttendanceService();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		
		HttpSession session = request.getSession(false);
		
		Users userIN = Optional.ofNullable(session).map(s -> (Users) session.getAttribute(IndexServlet.USER_PARAM))
					.orElse(null);
		if (userIN == null || !userIN.hasRole("employe")) {
			response.sendRedirect("/WebFinal/index");
			return;
		}
		
		Users user = null;
		String typeParam = request.getParameter("type");
		String userParam = request.getParameter("userID");
		
		if (StringUtils.isNotBlank(userParam)) {
			switch (typeParam) {
			case "UMOD":
				System.out.println(userParam);
				user = service.findUserById(Integer.parseInt(userParam));
				break;
				
			default:
				break;
			}
		}
		
		if (user == null) {
			user = new Users();
		} 
		
		System.out.println("mod: \n" +user);
		context.setVariable("users", service.findUserById(userIN.getUserID()));
		context.setVariable("user", user);
		engine.process("profile.html", context, response.getWriter());
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("POST");
		
		String username = request.getParameter(IndexServlet.USERNAME_PARAM);
		String firstname = request.getParameter(IndexServlet.FIRST_NAME_PARAM);
		String lastname = request.getParameter(IndexServlet.LAST_NAME_PARAM);
		String email = request.getParameter(IndexServlet.EMAIL_PARAM);
		String pass = request.getParameter(IndexServlet.PASSWORD_PARAM);

		System.out.println(username + "\n" + firstname + "\n" + lastname + "\n" + email + "\n" + pass + "\n");

		if (StringUtils.isBlank(username) || StringUtils.isBlank(firstname) || StringUtils.isBlank(lastname)
				|| StringUtils.isBlank(pass)) {
			response.sendRedirect("/WebFinal/profile?" + MISSING_FIELDS + "=true");
			return;
		}

		Users user = new Users();
		String userID = request.getParameter("userID");
		if (StringUtils.isNotBlank(userID)) {
			user.setUserID(Integer.parseInt(userID));
		}
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(pass);
		user.setEmail(email);
		user.setUserName(username);
		user.setActive(BooleanUtils.toBoolean(request.getParameter("active")));
		service.updateUser(user);
		
		response.sendRedirect("/WebFinal/profile");
		
		
	}
	
	
	
	
}
