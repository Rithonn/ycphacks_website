<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Registration</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
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
    	</script>
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
                <li><a class="stationary" href="index">Home</a></li>
                <li><a class="stationary" href="about">About</a></li>
                <li><a class="stationary" href="directions">Directions</a></li>
                <li><a class="stationary" href="registration">Registration</a></li>
                <li><a class="stationary" href="schedule">Schedule</a></li>
                <li><a class="active" href="profile_page">Profile</a></li>
            </ul>
        </nav>
        </div>
        <div class='form_wrap'>
        <form>
            <p class='form_text'>Please fill out this form.</p>
            
            <table>
				<tr class="form_element">
					
					<td><input type="text" name="name" size="24" value="${name}" placeholder="First and Last Name"/></td>
				</tr>
				<tr class="form_element">
					<td><input type="text" name="email" size="24" value="${email}" placeholder="email"/></td>
				</tr>
                <tr class="form_element">
					<td><input type="text" name="age" size="6" value="${age}" placeholder="Age"/></td>
				</tr>
                <tr class="form_element">
					<td><input type="text" name="uni" size="24" value="${uni}" placeholder="University"/></td>
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
			<input type="Submit" name="submit" value="Submit">
        </form>
        </div>
	</body>
</html>