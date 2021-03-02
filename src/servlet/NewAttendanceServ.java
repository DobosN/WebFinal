package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import service.AttendanceService;

@WebServlet("/newAttendance")
public class NewAttendanceServ extends HttpServlet {
	
	private AttendanceService service;

	public NewAttendanceServ() {
		this.service = new AttendanceService();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userIdparam = request.getParameter("userID");
		Integer userID = null;
		if (StringUtils.isNotBlank(userIdparam)) {
			userID = Integer.parseInt(userIdparam);
		}else {
			return;
		}
		
		
		service.saveAttendance(userID);

		response.sendRedirect("/WebFinal/attendance");

	}
	
	
}
