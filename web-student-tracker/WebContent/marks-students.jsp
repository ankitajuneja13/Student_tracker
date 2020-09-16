
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
          
       
        <form action="StudentControllerServlet" method="GET">
         
           <input type="hidden" name="command" value="MARKS">
           
             <input type="hidden" name="tempStud" value="${Marks_list.id }"> 
             <table> 
          
           
           
             <tr>
             <th> id </th>
                <th> FirstName </th>
                <th> eng </th>
                <th> hindi </th>
                <th> science</th>
             </tr>
           
      
          
          
          
          <tr>
               
               <td> ${Marks_list.id}</td>
              <td> ${Marks_list.first_name}  </td>
              <td> ${Marks_list.eng}  </td>
              <td> ${Marks_list.hindi} </td>
               <td> ${Marks_list.science} </td>
              
           </tr>
           
          
           </table>
           </form>
        
           
           
        </div>
     </div>  
</body>

</html>