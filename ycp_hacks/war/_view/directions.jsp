<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Directions</title>
		
		<!-- bootstrap cdn -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<!-- external style sheet link -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_directions.css">
	
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
	
	

		<div class="directionsContent">
			<div id="directionsGoogleMaps"></div>
			<div class="directionsAddress">
				<p>441 Country Club Rd, York PA, 17403</p>
				<p>@ the Willman Besiness Center</p>
				
				<p>For a more detailed map please go <a target="_blank" href="https://map.ycp.edu/?id=802#!ct/24424,14561,14560,14555,13400,13363,13350,13349,13341,13338,13337,12105,12075,12072,13346,13394,13393,13392,13391,20466,13351,13397">here</a></p>
			</div>
		</div>
		
		
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
		<script>
			function myMap() { 
			var myLatlng = new google.maps.LatLng(39.945421, -76.729161);
			var willmanLatlng = new google.maps.LatLng(39.946891, -76.730237);
			var mapProp= {
			  center:myLatlng,
			  zoom:16,
			};
			var map = new google.maps.Map(document.getElementById("directionsGoogleMaps"),mapProp);
			
			
			var markerYCP = new google.maps.Marker({
			    position: myLatlng,
			    title:"York College of Pennsylvania",
			    label:"YCP Entrance"
			});
			markerYCP.setMap(map);
			
			var markerWillman = new google.maps.Marker({
			    position: willmanLatlng,
			    title:"Willman Business Center",
			    label:"Willman Business Center"
			});
			markerWillman.setMap(map);
			
			}
		</script>
		<!-- change KEY to GoogleAPIkey -->
		<script src="https://maps.googleapis.com/maps/api/js?key=KEY&callback=myMap"></script>
	
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
	</body>
</html>