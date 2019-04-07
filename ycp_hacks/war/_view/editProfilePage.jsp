<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Your Profile</title>
		
		<!-- bootstrap cdn -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		
	   <!-- external style sheet link -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_editProfile.css">
		<!--  navBar info -->
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
		<nav>
            <div class="logo"><img src="${pageContext.request.contextPath}/_view/css/newLogo.png" alt="" height="50px"></div>
            <ul>
                <li><a class="stationary" href="index">Home</a></li>
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
		<div class="editingAboutSection">
			<div class="aboutFormEdit">
				<div class="container">
					<h1>Edit Profile</h1>
					<hr>
						<!-- Edit form column -->
						<div class="">
							<h3>Personal info</h3>
				
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="">
										<label class="">First name:</label>
										<input class="form-control" type="text" value="${currentUser.firstName}">
									</div>
								</div>
								<div class="form-group">
									<label class="">Last name:</label>
									<div class="">
										<input class="form-control" type="text" value="${currentUser.lastName}">
									</div>
								</div>
								<div class="form-group">
									<label class="">Email:</label>
									<div class="">
										<input class="form-control" type="text" value="${currentUser.email}">
									</div>
								</div>
								<div class="form-group">
									<label class="">Age:</label>
									<div class="">
										<input class="form-control" type="text" value="${currentUser.age}">
									</div>
								</div>
								<div class="form-group">
									<label class="">University:</label>
									<div class="">
										<input class="form-control" type="text" value="${currentUser.university}">
									</div>
								</div>
								<div class="form-group">
									<label class="">New Password:</label>
									<div class="">
										<input class="form-control" type="password" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="">Confirm password:</label>
									<div class="">
										<input class="form-control" type="password" value="">
									</div>
								</div>
								<div class="form-group">
									<label class=""></label>
									<div class="">
										<input type="button" class="btn btn-primary" value="Save Changes">
										<span></span>
										<input type="reset" class="btn btn-primary" value="Delete Profile">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<hr>
				<form action="${pageContext.servletContext.contextPath}/edit_profile" method="post">
					<div class="logOutButton">
						<button type="submit" class="btn btn-success btn-block" value="false" name="signOutButton"
							onclick="change()">Sign Out</button>
					</div>
				</form>
			</div>
		</div>

		
		
		<script>
	        function change(){
	        	document.getElementsByName("signOutButton")[0].setAttribute("value", "true");
			}
		</script>
    </body>
</html>