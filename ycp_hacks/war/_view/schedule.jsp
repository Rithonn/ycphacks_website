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
                    <!-- This will need to be hidden when not admin -->
                    <c:if test="${currentUser.accessID == 2}">
                        <th scope="col">Event ID</th>
                    </c:if>
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
                            if (eventHour == 0){
                                eventHour = 12;
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
                        <c:if test="${currentUser.accessID == 2}">
                            <td>${event.eventId}</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        <!-- Hide from non admin users -->
        <c:if test="${currentUser.accessID == 2}">
        <div class="form-wrappers">
            <!--Delete Event Button & Text Box-->
        <div class="DeleteWrapper">
            <p>Delete Event</p>
            <form action="${pageContext.servletContext.contextPath}/schedule" method="post">
                <table class="form_table">
                
				    <tr class="form_element">
					   <td><input class='form-control' type="text" name="delEventId"  value="${delEventId}" placeholder="Enter Event ID to delete"/></td>
				    </tr>

			     </table>
                <div class="form_button">
			     <button type="submit" class="btn btn-success btn-block" value="" name="delEventButton" onclick="changeDelEventButton()">Delete Event</button>
				<span></span>
                </div>
            </form>
        </div>
        
        <div class="AddWrapper">
            <p>Add Event</p>
            <form action="${pageContext.servletContext.contextPath}/schedule" method="post">
                <table class="form_table">
                
				    <tr class="form_element">
					   <td><input class='form-control' type="text" name="addEventName"  value="${addEventName}" placeholder="Enter event name"/></td>
				    </tr>
                    
                    <tr class="form_element">
					   <td><input class='form-control' type="text" name="addEventLocation"  value="${addEventLocation}" placeholder="Enter event location"/></td>
				    </tr>
                    
                    <tr class="form_element">
					   <td><input class='form-control' type="text" name="addEventDescription"  value="${addEventLocation}" placeholder="Enter a brief description"/></td>
				    </tr>
                    <tr class="form_element">
					    <td><input class='form-control' type="text" name="addEventYear"  value="${addEventYear}" placeholder="Enter year"/></td>
				    </tr>
                    <tr class="form_element">
					    <td><input class='form-control' type="text" name="addEventMonth"  value="${addEventMonth}" placeholder="Enter month (1-12)"/></td>
				    </tr>
                    <tr class="form_element">
					    <td><input class='form-control' type="text" name="addEventDay"  value="${addEventDay}" placeholder="Enter day of month"/></td>
				    </tr>
                    <tr class="form_element">
					    <td><input class='form-control' type="text" name="addEventTime"  value="${addEventTime}" placeholder="Enter time (HH:MM)"/></td>
				    </tr>
                    <tr class="form_element">
					    <td><div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="AM">
                                <label class="form-check-label" for="inlineRadio1">AM</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="PM">
                                <label class="form-check-label" for="inlineRadio2">PM</label>
                            </div>
                        </td>
				    </tr>
			     </table>
                <div class="form_button">
			     <button type="submit" class="btn btn-success btn-block" value="" name="addEventButton" onclick="changeAddEventButton()">Add Event</button>
				<span></span>
                </div>
            </form>
        </div>
        </div>
        </c:if>
        <script>
	        function changeDelEventButton(){
	        	document.getElementsByName("delEventButton")[0].setAttribute("value", "true");
			}
	        
	        function changeAddEventButton(){
	        	document.getElementsByName("addEventButton")[0].setAttribute("value", "true");
			}
	        
	        
		</script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>