<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index view</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/css/styles.css" />
	</head>
	
	
	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<!-- Make sure when only wanting to redirect you have to remove method  -->
		<!-- If method is not moved then the action will still occur like the program ran-->
		<!-- It can still work for the first two but guessing game crashes -->
		<div class="addNumbersDiv">
			<form action="${pageContext.servletContext.contextPath}/addNumbers">
				<input type="submit" name="submit" value="Add Numbers!">
			</form>
		</div>
		
		<div>
			<form action="${pageContext.servletContext.contextPath}/multiplyNumbers">
				<input type="submit" name="submit" value="Multiply Numbers!">
			</form>
		</div>
	</body>
</html>
