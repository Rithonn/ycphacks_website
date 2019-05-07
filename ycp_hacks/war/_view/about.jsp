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
        <section class="aboutSection">
                    <div class="aboutInfo">
                        <p>To York and Beyond</p>
                        
                        <p>Conveniently nestled in the middle of rural Pennsylvania, YCP Hacks is a college level hackathon with small town charm.
						An hour north of Baltimore, an hour and a half from Philadelphia, and 2 hours from Penn State places it right in the
						middle of Pennsylvania Dutch Country (presumably made of Funnel Cake). The event is set on the charming York College of
						Pennsylvania campus, with all the facilities, people and camaraderie that comes with it. Our mission is to promote
						technical knowledge, innovation, and entrepreneurship in York and beyond, and we just can't wait to see what you do
						next.</p>
			                
                		
			                
                    </div>
                    <div class="aboutInfo">
                        <h2>FAQ</h2>
                        
						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or be
						a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>
							
						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>
						
						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>

						<h4>What's a hackathon?</h4>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or
							be
							a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be
							attending.</p>
						
			                
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