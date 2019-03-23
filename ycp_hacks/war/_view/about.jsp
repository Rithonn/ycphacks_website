<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>About</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_about.css" />
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
	
	<style>
	
	
	</style>
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
                 <c:if test="${empty currentUser}">
                	<li><a class="active" href="profile_page">Log In</a></li>
                </c:if>
                <!--  if a user is logged in, display email, link to edit_profile page...
                MUST MAKE! -->
                 <c:if test="${! empty currentUser}">
                 	<li><a class="active" href="#">${currentUser.email}</a></li>
                 </c:if>
            </ul>
        </nav>
        <section class="aboutSection">
                    <div class="aboutInfo">
                        <p>Information about YCPHACKs is going to go here this is just a placeholder for right now</p>
                        
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
			                sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			                
                		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
			                sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			                
                    </div>
                    <div class="aboutInfo">
                        <p>FAQ's about YCPHACKS</p>
                        
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
			                sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			                
                		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
			                sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			                
                    </div>
                     <div class="aboutInfo">
                        <p>Video's and pictures to be put in later</p>
                        
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
			                sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			                
                		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
			                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
			                sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
			                
                    </div>

        </section>
    </div>
		
		
		
	</body>
</html>