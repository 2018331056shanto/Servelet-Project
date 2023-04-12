package com.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.Dao.CourseDao;
import com.Dao.RegistrationDao;
import com.Dao.StudentDao;
import com.Dao.TeacherDao;
import com.Entity.Course;
import com.Entity.Student;
import com.Entity.Teacher;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CourseDao courseDao=new CourseDao();
		TeacherDao teacherDao=new TeacherDao();
		StudentDao studentDao=new StudentDao();
		RegistrationDao registrationDao=new RegistrationDao();
		
		try {
			List<Course> list=courseDao.getCourses();
			List<Teacher> lisTeachers=teacherDao.getTeachers();
			List<Student> lisStudents=studentDao.getStudents(); 
			Course course= registrationDao.getHighestTakenCourse();
//			String s=courseDao.course("abc");
			request.setAttribute("totalcourse", list.size());
			request.setAttribute("totalteacher", lisTeachers.size());
			request.setAttribute("totalstudent", lisStudents.size());
			request.setAttribute("highest", course.getName().toUpperCase());
			request.setAttribute("bestteacher", course.getTeacher().getName());


			RequestDispatcher dispatcher=request.getRequestDispatcher("/pages/AdminPage.jsp");
			dispatcher.forward(request, response);

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
//		response.getWriter().append("Served at: admin ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
