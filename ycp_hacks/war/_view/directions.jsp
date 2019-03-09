<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<title>Directions</title>
		
		<!-- bootstrap cdn -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<!-- external style sheet link -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles.css">
	</head>
	
	<body>
	
	
	<!--  COPY UNDER FOR NAVBAR -->
		<nav class="navbar navbar-expand-lg navbar-light bg-hacks fixed-top">
	  		<div class="barLogo">
	  			<a href="index">
	  				<img src="${pageContext.request.contextPath}/_view/resources/newLogo.png" height="53" width="239.25">
	  			</a>
	  		</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    		<span class="navbar-toggler-icon"></span>
	  		</button>
	
			<!--  aligned left navBar contents-->
	  		<div class="collapse navbar-collapse" id="navbarSupportedContent">
	    		<ul class="navbar-nav mr-auto">
	      			<li class="nav-item">
	        			<a class="nav-link" href="index">Home</a>
	      			</li>
	      			<li class="nav-item">
	        			<a class="nav-link" href="about">About Us</a>
	      			</li>
	      			<li class="nav-item">
	        			<a class="nav-link" href="registration">Registration</a>
	      			</li>
	      			<li class="nav-item">
	      				<a class="nav-link" href="directions">Directions</a>
	      			</li>
	      			<li class="nav-item">
	      				<a class="nav-link" href="schedule">Schedule</a>
	      			</li>
	      		</ul>
	      		
	      		<!--  aligned right navBar contents -->
	      		<ul class="navbar-nav ml-auto">
				    <li class="nav-item dropleft" id="profileDropLeft">
				       	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          prof_name
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          	<a class="dropdown-item" href="profile_page">Your Profile</a>
				          	<a class="dropdown-item" href="#">View Registration Status</a>
				          	<div class="dropdown-divider"></div>
				          	<a class="dropdown-item" href="#">Sign Out</a>
				        </div>
				 	</li>
				 </ul>
	  		</div>
		</nav>
		<!--  COPY ABOVE FOR NAVBAR -->

		<div class="makePageScroll">
			I go under the navBar!
		</div>
		
		<div class="makePageScroll2">
		</div>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>