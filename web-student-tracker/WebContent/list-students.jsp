
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<title>
   web student
</title>

<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<body>



  <div id="wrapper">
     <div id="header">
        <h2> Foobar university</h2>
     </div>
     
     
     <div id="container">
        <div id="content">
           
           <input type="button" value="Add Student"  onclick="window.location.href='add-student.jsp'; return false;"
            class="add-student-button"/>  <!-- add student button is in css file just for styling -->
            
              <form action="StudentControllerServlet" method="Get">
                <input type="hidden" name="command" value="MARKS">
             <input type="button" value="Upload Marks"  onclick="window.location.href='marks-students.jsp'; return false;"
            /> 
           </form>
           
           
           <table>
           
             <tr>
                <th> FirstName </th>
                <th> LastName </th>
                <th> email </th>
                <th> Action</th>
             </tr>
           
          <c:forEach var="tempStud" items="${Student_list }">
          
          <!-- set up a link we will use jstl feature -> url -->
          <c:url var ="templink" value="StudentControllerServlet">
          
             <c:param name="command" value="LOAD" />  <!-- to send value to servlet we use this and student id -->
             <c:param name="studentId" value="${tempStud.id}" />
           </c:url>
          
          <!-- set up a link for delete we will use jstl feature -> url -->
          <c:url var ="deletelink" value="StudentControllerServlet">
          
             <c:param name="command" value="DELETE" />  <!-- to send value to servlet we use this and student id -->
             <c:param name="studentId" value="${tempStud.id}" />
          </c:url>
          
           <c:url var ="uploadlink" value="StudentControllerServlet">
          
             <c:param name="command" value="MARKS" />  <!-- to send value to servlet we use this and student id -->
             <c:param name="studentId" value="${tempStud.id}" />
          
          </c:url>
          
          <tr>
            
              <td> ${tempStud.first_name}  </td>
              <td> ${tempStud.last_name}  </td>
              <td> ${tempStud.email} </td>
              <td> <a href="${templink}">update </a>  <!--  templink as obtained from above -->
               |
               <a href="${deletelink}" onclick="if(!(confirm('Are u sur you want to delete this student?'))) return false">
                delete </a> 
              |
               <a href="${uploadlink }"> upload </a>
              </td> 
              
           </tr>
           
           </c:forEach>
           </table>
           
           
          
           
        </div>
     </div>  
</body>

</html>