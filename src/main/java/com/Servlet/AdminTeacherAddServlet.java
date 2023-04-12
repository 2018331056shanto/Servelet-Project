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
import com.Dao.TeacherDao;
import com.Entity.Course;
import com.Entity.Teacher;

/**
 * Servlet implementation class AdminTeacherAddServlet
 */
public class AdminTeacherAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTeacherAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] url=request.getRequestURI().split("/");
		String id=url[url.length-1];
//		System.out.println(id);
		CourseDao courseDao=new CourseDao();
		try {
			
			List<Course> courses= courseDao.getCourseByTeacher(id);
			
			List<Course> notCourses=courseDao.getCourseNotMatchWithTeacher(id);
			request.setAttribute("notcourses", notCourses);
			request.setAttribute("courses", courses);
			request.setAttribute("id", id);
			if(courses.size()!=0)
			{
				for (Course course : courses) {
					
					request.setAttribute("name", "Assigned Courses To "+course.getTeacher().getName());
					
					break;
				}
				
			}
			else
			{
				request.setAttribute("name", "No Courses Assigned Yet");

			}
			RequestDispatcher view=request.getRequestDispatcher("/pages/AdminTeacherAssign.jsp");
			view.forward(request, response);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		response.getWriter().append("Served at admin : ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] url=request.getRequestURI().split("/");
		String id=url[url.length-1];
		String courseName=request.getParameter("coursename");
		String [] courseNameId=courseName.split(":");
		String courseId=courseNameId[1];
//		System.out.println(courseName +" : ");
		TeacherDao teacherDao=new TeacherDao();
		CourseDao courseDao=new CourseDao();
		try {
			Teacher teacher=teacherDao.getTeacherById(id);
			courseDao.UpdateCourseTeacher(courseId, teacher);
			
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		doGet(request, response);
	}

}
