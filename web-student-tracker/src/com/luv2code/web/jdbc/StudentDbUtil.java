package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class StudentDbUtil {
  private DataSource dataSource;

  public StudentDbUtil(DataSource theDataSource) {
	
	dataSource = theDataSource;
  }
  
  public List<Student> getStudents() throws Exception
  {
	  List<Student> students= new ArrayList<>();
	  
	  Connection myConn=null;
	  Statement myStmt=null;
	  ResultSet myRs=null;
	  
	  
	  try {
	
		  myConn = dataSource.getConnection();
		  
			// Step 3:  Create a SQL statements
			String sql = "select * from student order by id asc";
			myStmt = myConn.createStatement();
			
			// Step 4:  Execute SQL query
			myRs = myStmt.executeQuery(sql);
			
			// Step 5:  Process the result set
			while (myRs.next()) {
				int id=myRs.getInt("id");
				String firstName=myRs.getString("first_name");
				String lastName=myRs.getString("last_name");
				String email=myRs.getString("email");
				
				Student tempstud=new Student(id,firstName,lastName,email);
				
				students.add(tempstud);
			}
		  
		  return students;
	  }
	 finally {
		 //close jdbc objects
		 close(myConn,myStmt,myRs);
		 
	 }
  }

private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
	// TODO Auto-generated method stub
	 try {
		 if(myRs !=null)
		 {
			 myRs.close();
		 }
		 if(myStmt !=null)
		 {
			 myStmt.close();
		 }
		 if(myConn !=null)
		 {
			 myConn.close();
		 }
	 }
	 catch (Exception ex)
	 {
		 ex.printStackTrace();
	 }
}

public void addStudent(Student thestud) throws Exception {
	
	Connection myconn=null;
	PreparedStatement mystmt=null;
	
	try {
		//get db connctn
		myconn=dataSource.getConnection();
		
	//create sql query for insert
	String sql="insert into student"
			     + "(first_name, last_name, email)"
			     + "values(?, ?, ?)" ;  //? is used as placeholder fill it autom with value remember space after,
	
	mystmt=myconn.prepareStatement(sql);
	
	//set the param values for students
	mystmt.setString(1, thestud.getFirst_name());  //here it starts with 1 not 0
	mystmt.setString(2, thestud.getLast_name());
	mystmt.setString(3, thestud.getEmail());
	
	//execute sql insert
       mystmt.execute();
	}
	
	finally {
	//clean up the jdbc object
		close(myconn,mystmt,null);
	}
}

public Student getStudent(String thestudId) throws Exception{
	
	Student thestud=null;
	Connection myconn=null;
	PreparedStatement mystmt=null;
	ResultSet myRes=null;
	int studentId;
	
	try {
		//convert studentid to int;
		 studentId=Integer.parseInt(thestudId);
		 
		 //get connc to db
		 myconn=dataSource.getConnection();
		 
		 //create sql to get selected dtatement
		 String sql="Select * from student where id=?";
		 
		 //create a prepared statement
		 mystmt=myconn.prepareStatement(sql);
			
		 //set param
		 mystmt.setInt(1, studentId);
		 
		 //exe stat
		 myRes=mystmt.executeQuery();
		 
		 //retrieve data from result set row
		 if(myRes.next()) {
				
				String firstName=myRes.getString("first_name");
				String lastName=myRes.getString("last_name");
				String email=myRes.getString("email");
				
				//use the studentid for constructing new obj
				thestud= new Student(studentId,firstName,lastName,email);
		 }
		 else
		 {
		     throw new Exception("couldn't find" + studentId);
		 }
		 
				return thestud;
	}
	finally {
		close(myconn,mystmt,myRes);
	}

  }

public void updateStudent(Student thestud) throws Exception {
	
	Connection myconn=null;
	PreparedStatement mystmt=null;
	
	try {
	 myconn=dataSource.getConnection();
	 
	
	 String sql="update student "+ "set first_name=?, last_name=?, email=? "+ "where id=?";
	 
	 //create a prepared statement
	 mystmt=myconn.prepareStatement(sql);
	
	    mystmt.setString(1, thestud.getFirst_name());  //here it starts with 1 not 0
		mystmt.setString(2, thestud.getLast_name());
		mystmt.setString(3, thestud.getEmail());
		mystmt.setInt(4, thestud.getId());
		 
		mystmt.execute();
	}
	finally {
		close(myconn,mystmt,null);
	}
	
}

public void deleteStudent(String thestudId) throws Exception {
	 
	Connection myconn=null;
	PreparedStatement mystmt=null;
	
	try {
		int studentId=Integer.parseInt(thestudId);
	 myconn=dataSource.getConnection();
	 
	
	 String sql="delete from student where id=?";
	 
	 //create a prepared statement
	 mystmt=myconn.prepareStatement(sql);
	
	   
		mystmt.setInt(1, studentId);
		 
		mystmt.execute();
	}
	finally {
		close(myconn,mystmt,null);
	}
	
}
	
}	





  
  
  

