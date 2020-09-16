package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MarksDBUtil {

	private DataSource dataSource;

	  public MarksDBUtil(DataSource theDataSource) {
		
		dataSource = theDataSource;
	  }
	  
	  public List<Marks> getMarks() throws Exception
	  {
		  List<Marks> marks= new ArrayList<Marks>();
		  
		  Connection myConn=null;
		  Statement myStmt=null;
		  ResultSet myRs=null;
		  
		  
		  try {
		
			  myConn = dataSource.getConnection();
			  
				// Step 3:  Create a SQL statements
				String sql = "select * from marks order by first_name";
				myStmt = myConn.createStatement();
				
				// Step 4:  Execute SQL query
				myRs = myStmt.executeQuery(sql);
				
				// Step 5:  Process the result set
				while (myRs.next()) {
					int id=myRs.getInt("id");
					String first_name=myRs.getString("first_name");
					int eng=myRs.getInt("eng");
					int hindi=myRs.getInt("hindi");
					int science=myRs.getInt("science");
					
					Marks tempmarks=new Marks(id,first_name,eng,hindi,science);
					
					marks.add(tempmarks);
				}
			  
				
			  return marks;
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
	
	public Marks getMark(String thestudId) throws Exception{
		
		Marks themarks=null;
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
			 String sql="Select * from marks where id=?";
			 
			 //create a prepared statement
			 mystmt=myconn.prepareStatement(sql);
				
			 //set param
			 mystmt.setInt(1, studentId);
			 
			 //exe stat
			 myRes=mystmt.executeQuery();
			 
			 //retrieve data from result set row
			 if(myRes.next()) {
					
					String firstName=myRes.getString("first_name");
					int eng=myRes.getInt("eng");
					int hindi=myRes.getInt("hindi");
					int science=myRes.getInt("science");
					
					themarks= new Marks(studentId,firstName,eng,hindi,science);
					String msg="Your child report card: " +firstName  + " Marks in eng: " + eng + " Marks in Hindi: "
							+hindi + " Marks in science: " + science;
					
					Sendemaill.sendd(msg);
			 }
			 else
			 {
			     throw new Exception("couldn't find" + studentId);
			 }
			 
					return themarks;
		}
		finally {
			close(myconn,mystmt,myRes);
		}

	  }
	
}
