<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Admin Panel</title>
		
		<!-- bootstrap cdn -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		
	   <!-- external style sheet link -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles_editAdmin.css">
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
		<!-- This is where the fun begins -->


		<div class="allContentSection">
			<div class="mainHeader">
				<h2>Admin Panel</h2>
			</div>

			<!-- Stats section -->
			<div class="stats-section">
				<div class="stats-header">
					<h2>Stats</h2>
				</div>
				<div class="stats-content">
					<ul class="all-stats">
						<li>Total Users: </li>
						<li>Verfied Users: </li>
						<li>Submitted Users: </li>
						<div class="stats-br">
							<br>
						</div>
						
						<li>Admitted: </li>
						<li>Confirmed: </li>
						<li>Checked In: </li>
					</ul>
				</div>
			</div>

			<!-- User panel, this is gonna be bad -->
			<div class="user-panel-main">
				<div class="user-panel-main-content">
					<div class="row">
						<div class="col-3">
							<!-- This is where all the buttons will go -->
							<div class="search-users-button">
								<form class="form-inline md-form mr-auto mb-4">
									<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
									<button class="btn btn-success btn-rounded btn-sm my-0" type="submit">Search</button>
								</form>
							</div>
							<div>
								<h5>Page Numbers:</h5>
							</div>
							<div class="admin-controls">
								<h5>Admin Settings:</h5>
								<!-- Accept, Deny, Delete -->
								<form action="submit">
									<input type="text" placeholder="UserID" class="rounded">
									<div style="margin-bottom: 3px;">

									</div>
									<button class="btn btn-success btn rounded btn-sm">Accept</button>
									<button class="btn btn-success btn rounded btn-sm">Deny</button>
									<button class="btn btn-success btn rounded btn-sm">Delete</button>
								</form>

							</div>
							
						</div>
						<div class="col-9">
							<table class="table table-sm table-dark table-striped">
								<thead>
									<tr>
										<th scope="col">UserID</th>
										<th scope="col">First</th>
										<th scope="col">Last</th>
										<th scope="col">Email</th>
										<th scope="col">School</th>
										<th scope="col">Registered</th>
										<th scope="col">Access ID</th>
									</tr>
									<c:forEach items="${allUsers}" var="user">	
										<tr>
											<td>${user.userID}</td>
											<td>${user.firstName}</td>
											<td>${user.lastName}</td>
											<td>${user.email}</td>
											<td>${user.university}</td>
											<td>${user.isRegToString}</td>
											<td>${user.accessID}</td>
										</tr>
									</c:forEach>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>

		</div>


	</body>
</html>