<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Schedule</title>
        <meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- bootstrap cdn -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_schedule.css" />
         
		
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
        
        <div class="tbl_wrap">
        <table class="table table-dark">
            <thead>
                <tr>
                    <th scope="col">When</th>
                    <th scope="col">What</th>
                    <th scope="col">Where</th>
                    <th scope="col">Description</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${schedule}" var="event">
                    <tr>
                        <td>
                            <script>
                            // Script to display the event time as HH:MM am/pm
                            var eventHour = ${event.date.hour};
                            var eventMins = ${event.date.minute};
                            var amOrPm;
                            //Hour is stored in 24hr format
                            //If the hour is greater than 12, subtract 12 and make it pm
                            if (eventHour > 12) {
                                    amOrPm = "pm";
                                    eventHour = eventHour - 12; 
                            //Otherwise it is in the am
                            }else{
                                amOrPm = "am";
                            }
                            
                            //Minutes are stored as a raw into so if it is less than 10, it will display as a single digit
                            //If it is less than 10, prepend a 0
                            if (eventMins < 10) {
                                eventMins = '0' + eventMins;
                            }
                            
                            //Put the hour, mins, and am/pm together into one string
                            document.write(eventHour, ":", eventMins, " ", amOrPm);
                            </script>
                        </td>
                        <td>${event.name}</td>
                        <td>${event.location}</td>
                        <td>${event.description}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>