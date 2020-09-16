
<!DOCTYPE html>
<html>
<head>

<title> Update student </title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>
<body>

   <div id="wrapper">
     <div id="header">
        <h2> Foobar university</h2>
     </div>
     
     
     <div id="container">
        <h3> Update Student</h3>
        
        <form action="StudentControllerServlet" method="GET">
         
           <input type="hidden" name="command" value="UPDATE">
           
            <input type="hidden" name="studentId" value="${THE_STUDENT.id }"> <!-- exact name(jo yha form mein likha) se access krna hai student servlet mein ni toh error -->
           
             <table>
               <tbody>
                  <tr>
                    <td> <label> First name:</label> </td>
                    <td> <input type="text" name="firstName" value="${THE_STUDENT.first_name }"> </td>
                   </tr>
                    <tr>
                    <td> <label> Last name:</label> </td>
                    <td> <input type="text" name="lastName" value="${THE_STUDENT.last_name }"> </td>
                   </tr>
                    <tr>
                    <td> <label> Email:</label> </td>
                    <td> <input type="text" name="email" value="${THE_STUDENT.email}"> </td>  <!-- value will call internal getemail method define student class -->
                   </tr>
                   
                    <tr>
                    <td> <label> </label> </td>
                    <td> <input type="submit" value="save" class="save"> </td>
                   </tr>
               
               
               </tbody>
             
             </table>
          
        </form>
        
        <div style="clear:both;"> </div>
          <p>
            <a href="StudentControllerServlet"> back to list </a>
            </p>
            
            </div>
 
</body>
</html>