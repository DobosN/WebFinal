package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Attendance;
import entities.DayTypes;
import entities.Hours;
import entities.Minutes;
import entities.Month;
import entities.Role;
import entities.Users;
import repository.AttendanceRepository;

public class AttendanceService {

	AttendanceRepository repo;
	
	
	
	public AttendanceService() {
		this.repo = new AttendanceRepository();
	}

	public Users login(String userName, String password) {
		return repo.login(userName, password);
	}

	public List<Users> findAllUsers() {
		return repo.findAllUsers();
	}

	public Users findUserById(int userId) {
		return repo.findUserById(userId);
	}
	
	public List<Users> findUserByLeader(int userId) {
		return repo.findUserByLeader(userId);
	}
	
	public void saveUser(Users user) {
		repo.saveUser(user);
	}
	
	public void updateUser(Users user) {
		repo.updateUser(user);;
	}
	
	public void userToRole(String userName, String name) {
		repo.userToRole(userName, name);
	}
	
	public void employeToLeader(String userName, String name) {
		repo.employeToLeader(userName, name);
	}
	
	public List<Attendance> findAttendanceByUser(int userId) {
		return repo.findAttendanceByUser(userId);
	}
	
	public List<Attendance> findAttendanceByDate(int userId) {
		
		
		List<Attendance> atL = repo.findAttendanceByUser(userId);
		List<Attendance> atLbyD = new ArrayList<>();
		
		LocalDate dt = LocalDate.now();
		Integer y = dt.getYear();
		Integer m = dt.getMonthValue();	
		
		atL.forEach(at->{if (at.getYear().equals(y)&& at.getMonth().equals(m)) {
			atLbyD.add(at);
		}});
		
		return atLbyD;
	}
	
	
	public Attendance findAttendanceByID(int atId) {
		return repo.findAttendanceByID(atId);
	}
	
	public void saveAttendance(int userID) {
		
//		List<Attendance> atL = repo.findAttendanceByUser(userID);
//		
//		LocalDate dt = LocalDate.now();
//		Integer y = dt.getYear();
//		Integer m = dt.getMonthValue();
//		Integer d = dt.getDayOfMonth();
//		
//		if (atL != null) {
//			atL.forEach(at->{if 
//				(at.getYear().equals(y) && at.getMonth().equals(m) && !at.getDay().equals(d)) {
//				repo.saveAttendance(userID);
//			}});
//		}
		repo.saveAttendance(userID);
		
	}
	
	public void updateAttendance(Attendance at) {
		repo.updateAttendance(at);
	}
	
	public List<Month> findAllMonth() {
		return repo.findAllMonth();		
	}
	
	public List<Hours> findAllHours() {
		return repo.findAllHours();
	}
	
	public List<Minutes> findAllMinutes() {
		return repo.findAllMinutes();
	}
	public List<Role> findAllRole() {
		return repo.findAllRole();
	}
	
	public List<DayTypes> findAllDayTypes() {
		return repo.findAllDayTypes();
	}
	
	public String getTime() {
		return repo.getTime();
	}
	
	public String sumHoursOfMonth(Integer userId) {
		return repo.sumHoursOfMonth(userId);
	}
	
	
}
