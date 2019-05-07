<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<title>YCP Hacks</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_index.css" />
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
	<!-- Temp style tags to make changes in -->
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
        <video autoplay="" muted="" loop="" id="backgroundVideo">
                        <source src="https://i.imgur.com/DNqTLS5.mp4" type="video/mp4">
                 </video> 
        <section class="firstSection">

                 
                    <div class="contentInfo">
                        <a href="index" class="stationary"><img class="logo_contentInfo" src="${pageContext.request.contextPath}/_view/css/old_logo.PNG" href="#" alt="Logo" height="120px"></a>
                        <p>2019 Date To Be Announced</p>
                        <p>York College of Pennsylvania</p>
                        <button class="button btn btn-success" type="button" onclick="location.href='registration'">Register Now!</button> 
                    </div>

        </section>
        <!-- This section will be for the sponsosrs -->
        <section class="secondSection">
            <div class="sponsorSection">
                <div class="sponsorHeader">
                    <h1>Sponsors!</h1>
                    <h3>We thank you!</h3>
                </div>
                <div class="sponsorBlock">
                    <a href="https://www.ycp.edu/about-us/offices-and-departments/jd-brown-center-for-entrepreneurship/">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                    <a href="https://www.dataforma.com/">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                    <a href="http://hammercreekllc.com/index.html">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                    <a href="https://www.ycp.edu/academics/graham-school-of-business/">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                    <a href="">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                    <a href="http://www.pennwaste.com/">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                    <a href="https://www.bd.com/en-us">
                        <img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="">
                    </a>
                </div>
            </div>
        </section>
    </div>
		
		
	</body>
</html>
