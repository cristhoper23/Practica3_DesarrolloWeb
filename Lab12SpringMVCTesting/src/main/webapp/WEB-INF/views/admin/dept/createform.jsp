<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Spring MVC CRUD</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-6">
				<h4 class="text-center">Create Department</h4>
				<hr>
				<form:form method="post" action="create">
					<div class="form-group">
						<label for="name">Name: </label>
						<form:input path="name" class="form-control" />
					</div>
					<div class="form-group">
						<label for="description">Description: </label>
						<form:input path="description" class="form-control" />
					</div>
					<div class="form-group">
						<label for="city">City: </label>
						<form:input path="city" class="form-control" />
					</div>
					<div class="form-group">
						<input type="submit" value="Create" class="btn btn-success" />
						<a href="<%=request.getContextPath()%>/admin/dept/list_dept" class="btn btn-danger">Cancel</a>
					</div>
				</form:form>
                              <font color="red">${message}</font>
			</div>
		</div>
	</div>


</body>
</html>