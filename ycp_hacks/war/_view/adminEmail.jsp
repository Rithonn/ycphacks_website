<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Mass Email</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
     	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_adminEmail.css" />
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    	
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
        
        <div class="emailSection">
        	<form action="${pageContext.servletContext.contextPath}/adminEmail" method="post">
	        	<p align="center">${error}</p>
	        	<p align="center" class="success">${success}</p>
	        	<h2>Recipients:</h2>
	        	<div class="checkboxDiv">
	        		All Users: <input type="checkbox" name="allUsersButton">
	        		Specific Users: <input type="checkbox" name="specificUsersButton" onclick="specificUsers()">
	        	</div>
	        	<div class="specificUserEntries">
	        		<input class="form-control" type="text" placeholder="Enter emails seperated by a comma here:" name="emails">
	        	</div>
	        	<div class="subject">
	        		<h2>
	        			Subject:
	        		</h2>
	        		<input class="form-control" type="text" placeholder="Enter subject text here:" name="subject" value="${subject}">
	        	</div>
	        	<div class="message">
	        		<h2>
	        			Message:
	        		</h2>
	        		<textarea class="form-control" placeholder="Enter message text here:" name="message">${message}</textarea>
	        	</div>
	        	<div class="sendEmailSection">
	        		<button type="submit" class="btn btn-success btn-block" name="sendEmailButton">Send Email</button>
	        	</div>
	        </form>
        </div>
        
        <hr>
        
        
        <script>
	        function specificUsers(){
	        	var checkbox = document.getElementsByName("specificUsersButton")[0];
				var div = document.getElementsByClassName("specificUserEntries")[0];
				
				if(checkbox.checked == true){
					div.style.display = "block";
				}else{
					div.style.display = "none";
				}
			}
        </script>
        
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
	</body>
</html>