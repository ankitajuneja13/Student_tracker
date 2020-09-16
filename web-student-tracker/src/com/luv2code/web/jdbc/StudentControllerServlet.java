package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentDbUtil studentDbUtil;
	private MarksDBUtil marksDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	
	
	
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create dbutil and pass connection 
		try {
		studentDbUtil= new StudentDbUtil(dataSource);
		marksDbUtil= new MarksDBUtil(dataSource);
		
		}
		catch (Exception ex)
		{
			throw new ServletException(ex);
		}
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String thecmd=request.getParameter("command");
			
			if(thecmd==null)
			{
				thecmd="list";
			}
			
			switch(thecmd)
			{
			case "list":
				listStudents(request,response);
				break;
				
			case "MARKS":
				Mark(request,response);
		//	  marksStudents(request,response);
				break;
				
			case "ADD":
				addStudent(request,response);
				break;
				
			case "LOAD":
				loadStudent(request,response);
				break;
				
			case "UPDATE":
				updateStudent(request,response);
				break;
				
			case "DELETE":
				 deleteStudent(request,response);
				 break;
				
				default:
					listStudents(request,response);
				
			}
		
		}
		catch (Exception ex)
		{
			throw new ServletException(ex);
		}
	}

	private void marksStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
        List<Marks> marks=marksDbUtil.getMarks();
		

		request.setAttribute("Marks_list", marks);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/marks-students.jsp");
		
		dispatcher.forward(request, response);
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
         String thestudId=request.getParameter("studentId");
		
		//get student from database (dbutil)
		 studentDbUtil.deleteStudent(thestudId);
		 
		 listStudents(request,response);
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read student info from form data
		int id=Integer.parseInt(request.getParameter("studentId"));
		String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
	
        //create a new stu obj
        Student thestud=new Student(id,firstName,lastName,email);
        
        //perform update on db
        studentDbUtil.updateStudent(thestud);
        
        //send them back to list student page
        listStudents(request,response);
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read studentid from form data(by link we have set it conatins studid and load cmd)
		String thestudId=request.getParameter("studentId");
		
		//get student from database (dbutil)
		Student thestud= studentDbUtil.getStudent(thestudId);
		
		//place student into request attribute
		request.setAttribute("THE_STUDENT", thestud);

		//send to jsp page:update-student
		RequestDispatcher dispatcher= request.getRequestDispatcher("/update-student.jsp");
		dispatcher.forward(request,response);
		
		
	}
	
private void Mark(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//read studentid from form data(by link we have set it conatins studid and load cmd)
		String thestudId=request.getParameter("studentId");
		
		//get student from database (dbutil)
		Marks thestud= marksDbUtil.getMark(thestudId);
		
		//place student into request attribute
		request.setAttribute("Marks_list", thestud);

		//send to jsp page:update-student
		RequestDispatcher dispatcher= request.getRequestDispatcher("/marks-students.jsp");
		dispatcher.forward(request,response);
		
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read student info from from data 
		String firstName=request.getParameter("firstName");
          String lastName=request.getParameter("lastName");
          String email=request.getParameter("email");
	
          //create stud obj
            Student thestud=new Student(firstName,lastName,email);
           
            //add stud to db
            studentDbUtil.addStudent(thestud);
            
            //send back to main page(studentlist)
            listStudents(request,response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Student> studs=studentDbUtil.getStudents();
		
		request.setAttribute("Student_list", studs);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-students.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
