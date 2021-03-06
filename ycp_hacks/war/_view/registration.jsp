<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Registration</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
     	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_registration.css" />
    	
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    	<script>
    	
    		$(window).on('scroll', function(){
    			if($(window).scrollTop()){
        			$('nav').addClass('black');
        			$('a').removeClass('stationary');
    			}else{
         			$('nav').removeClass('black');
         			$('a').addClass('stationary');    
   				 }
    
			})
			
			//Function to check the password lenght to make sure it is 6 digits
			function checkPass()
				{
				    var pass1 = document.getElementById('pass1');
				    var pass2 = document.getElementById('pass2');
				    var message = document.getElementById('error-nwl');
				    var goodColor = "#66cc66";
				    var badColor = "#ff6666";
				 	
				    if(pass1.value.length > 5)
				    {
				        pass1.style.backgroundColor = goodColor;
				        message.style.color = goodColor;
				        message.innerHTML = "character number ok!"
				    }
				    else
				    {
				        pass1.style.backgroundColor = badColor;
				        message.style.color = badColor;
				        message.innerHTML = " you have to enter at least 6 digit!"
				        return;
				    }
				  
				    if(pass1.value == pass2.value)
				    {
				        pass2.style.backgroundColor = goodColor;
				        message.style.color = goodColor;
				        message.innerHTML = "ok!"
				    }
					else
				    {
				        pass2.style.backgroundColor = badColor;
				        message.style.color = badColor;
				        message.innerHTML = " These passwords don't match"
				    }
				}  
			
    	</script>
        <!-- bootstrap cdn -->
	</head>
	
	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<!-- Make sure when only wanting to redirect you have to remove method  -->
		<!-- If method is not moved then the action will still occur like the program ran-->
		<!-- It can still work for the first two but guessing game crashes -->
		<div class="wrapper">
            <nav>
            <div class="logo"><img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="" height="50px"></div>
            <ul>
                <li><a class="stationary" href="home">Home</a></li>
                <li><a class="stationary" href="about">About</a></li>
                <li><a class="stationary" href="directions">Directions</a></li>
                <li><a class="stationary" href="registration">Registration</a></li>
                <li><a class="stationary" href="schedule">Schedule</a></li>
                <c:if test="${empty currentUser}">
                	<li><a class="active" href="login">Log In</a></li>
                </c:if>
                <!--  if a user is logged in, display email, link to edit_profile page...
                MUST MAKE! -->
                 <c:if test="${! empty currentUser}">
                 	<li><a class="active" href="edit_profile">${currentUser.email}</a></li>
                 </c:if>
            </ul>
            </nav>
        </div>
        
        <div class='form_wrap form_text'>
            <p class=''>Please fill out this form.</p>
        		<form action="${pageContext.servletContext.contextPath}/registration" method="post">
		            <table class="form_table">
		                
						<tr class="form_element">
							<td><input class='form-control' required="required" type="text" name="firstname"  value="${firstname}" placeholder="First Name"/></td>
						</tr>
		                
		                <tr class="form_element">
							<td><input class='form-control' required="required" type="text" name="lastname"  value="${lastname}" placeholder="Last Name"/></td>
						</tr>
		                
						<tr class="form_element">
							<td><input class='form-control' required="required" type="text" name="email"  value="${email}" placeholder="Email"/></td>
						</tr>
						
						<tr class="form_element">
							<td><input class='form-control' required="required" type="text" name="email2"  value="${email2}" placeholder="Confirm Email"/></td>
						</tr>
		                
		                <tr class="form_element">
							<td><input class='form-control' required="required" type="text" name="age"  value="${age}" placeholder="Age"/></td>
						</tr>
		                
		                <tr class="form_element">
							<td><input class='form-control' required="required" type="text" name="uni" value="${uni}" placeholder="University"/></td>
						</tr>
		                
		                <!-- Make sure to add the button func down here to check password strength -->
		                <tr class="form_element">
							<td><input class='form-control' required="required" type="password" name="password1"  id="pass1" value="${password1}" placeholder="Password"/></td>
						</tr>
		                
		                <tr class="form_element">
							<td><input class='form-control' required="required" type="password" name="password2" id="pass2"  value="${password2}" placeholder="Confirm Password"/></td>
						</tr>
		                <!--
		                <tr class="form_element">
							<td><input type="text" name="about" size="24" value="${about}" placeholder="Tell us about yourself"/></td>
						</tr>
		                <tr class="form_element">
							<td><input type="text" name="accomodations" size="24" value="${accomodations}" placeholder="Special Accomodation / Food Allergies"/></td>
		                </tr>
		                -->
				</table>
	            <div class="form_button">
				 <input type="submit" name="submit" value="Submit" class='btn btn-success'>
	            </div>
		            
	            <p class="regCheck" >${reg}</p>     
        	</form>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
	</body>
</html>