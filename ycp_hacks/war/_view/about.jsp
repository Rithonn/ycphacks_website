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
                    <div class="aboutInfoToYork aboutInfo">
                        <h3>To York and Beyond</h3>
                        
                        <p>Conveniently nestled in the middle of rural Pennsylvania, YCP Hacks is a college level hackathon with small town charm.
						An hour north of Baltimore, an hour and a half from Philadelphia, and 2 hours from Penn State places it right in the
						middle of Pennsylvania Dutch Country (presumably made of Funnel Cake). The event is set on the charming York College of
						Pennsylvania campus, with all the facilities, people and camaraderie that comes with it. Our mission is to promote
						technical knowledge, innovation, and entrepreneurship in York and beyond, and we just can't wait to see what you do
						next.</p>
			                
                		
			                
                    </div>
                    <div class="aboutInfoFAQ aboutInfo">
                        <h2>FAQ</h2>
                        
						<h3>What's a hackathon?</h3>
						<p>A 36 hour event where you work together with a team to make something awesome. You don't need to know how to code or be
						a supreme hacker, just how to be creative! You can also get help from our mentors and speakers who will be attending.</p>

						<h3>Can I come?</h3>
						<p>We'll take any college student from anywhere on earth! We sadly cannot accept students under the age of 18.</p>
							
						<h3>Where is this going to be?</h3>
						<p>The Willman Business Center of York College! There will be free parking in the adjacent lot for the entire weekend.</p>
						
						<h3>When will this be?</h3>
						<p>It is to be determined!</p>

						<h3>Why?</h3>
						<p>Learn new things, meet new people, and tons of free food and swag.</p>

						<h3>Will you reimburse travel?</h3>
						<p>If we get enough sponsors this season, we may offer it closer to the event date. At this time we cannot confirm it
						though. Apply and we'll email our applicants with more information as it becomes available.</p>

						<h3>I don't have a team so....</h3>
						<p>Well you're in luck! We have tons of team building activities if you don't have a team to come with you. You can also
						work alone if you'd like.</p>

						<h3>But I can't code!</h3>
						<p>We'll have tons of great workshops and mentors to help you learn and build something amazing!</p>

						<h3>How much will it cost?</h3>
						<p>Nothing! Registration is free and food will be provided for you. And of course, swag is free too.</p>

						<h3>Will there be hardware available?</h3>
						<p>Yes! Hardware will be provided.</p>

						<h3>What should I bring?</h3>
						<p>ID, Clothing, sleeping bag, and any necessary tech (and chargers). And deodorant. Please.</p>

						<h3>Do I keep my Intellectual Property?</h3>
						<p>Yes! Participants retain 100% of the intellectual property that they create during the event.</p>

						<h3>Are there any rules?</h3>
						<p>You can find our code of conduct <a href="https://hackcodeofconduct.org/">here</a>.</p>

						<h3>You did not answer my question!</h3>
						<p>Send it to us at info@ycphacks.io</p>
						
			                
                    </div>
                    
                    <div class ="submissionsSection aboutInfo">
                    	<h2>The Experience</h2>
                    	
                    	<c:forEach items="${allSubmissions}" var="submission">
								<h3>${submission.userFirstName} said: </h3>
                    			<p>${submission.message}</p>
						</c:forEach>
                    	
                    	
                    	<c:if test="${! empty currentUser}">
	                    	<button type="button" class="btn btn-success btn-block" value="" name="addSubmission" onclick="test()">
									Add your own experience!
							</button>
                 		</c:if>   	
                 
                    	<div class="enterSubmission">
                    		<h4>**Your first name will be displayed if your experience gets approved!**</h4>
                    		<form class="form-horizontal" role="form" action="${pageContext.servletContext.contextPath}/about" method="post">
                    			<textarea placeholder="Enter your experience text here:" name="experienceToAdd"></textarea>
	                    		<button type="submit" class="btn btn-success btn-block" value="" name="addSubmission" onclick="test()">
										Submit!
								</button>
							</form>
                    	</div>
                    	
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
        <div class="ticker_div">
            <marquee class="scroll-text">
                <c:if test="${!empty ongoing}">
                    <span> || Currently Happening: </span> 
                    <c:forEach items="${ongoing}" var="event">
                        ${event.name}, 
                    </c:forEach>
                </c:if>
                <span> || Coming up: </span>
                ${upcoming.name}
            </marquee>
        </div>
		
	<script>
	function test(){
		var x = document.getElementsByClassName("enterSubmission")[0];
		x.style.display = "block";
	}
	</script>
	
	</body>
</html>