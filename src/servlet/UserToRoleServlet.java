package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import service.AttendanceService;



@WebServlet("/userToRole")
public class UserToRoleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String WARRNING_SAVE_PRODUCT = "warrning_save_products";
	private AttendanceService service;

	public UserToRoleServlet() {
		this.service = new AttendanceService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String name = request.getParameter("name");

		if (StringUtils.isBlank(userName) && StringUtils.isBlank(name)) {
			response.sendRedirect("/WebFinal/leader?" + WARRNING_SAVE_PRODUCT + "=true");
			return;
		}
		service.userToRole(userName, name);

		response.sendRedirect("/WebFinal/leader");

	}
}