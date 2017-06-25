<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html/css;charset=UTF-8">
<title>Login</title>

<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/bootstrap.min.js"></script>

<style>
	
	
	body{
		background-image: url("../images/background1.jpg");
    	background-color: #cccccc;
    
	}
	
	
	
	#a1{
		position: relative;
		left: 10%;
	}
	
	#id1{
		position: aboslute;
		top: 40%;
	}
	
	#cont{
		position: aboslute;
		top: 30%;
		left: 33%;
		margin: auto;
		width: 35%;
    	border: 3px solid black;
    	padding: 10px;
		background-color: white;
		opacity: 0.9;
	}
	
	
	
	

</style>

<script>

function validate(form)
{
	if (form.username.value == "")
	{
		alert("Error! Username field is empty.");
		form.username.focus();
		return false;
	}
	if (form.password.value == "")
	{
		alert("Error! Password field is empty.");
		form.password.focus();
		return false;
	}
}
	
</script>


</head>

<body>
	
  
    <div class="error" style="color : red"> <%session.getAttribute("message");%> </div>
   
	<div id="id1" class="container" align="center">
		<div class="my-panel vertical-center" id="cont">
		<form  name="form1" class="form-horizontal" action="/Project/LoginServlet" method="POST" onsubmit="return validate(this)">
			<fieldset>
					<div class="form-group">
						<p> <label class="control-label col-sm-3" for="username">Username:</label> </p>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="text" name="username" /><br />
						</div>
					</div>
					<div class="form-group">
						<p> <label class="control-label col-sm-3" for="password">Password:</label> </p>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="password" name="password" /><br />
						</div>
					</div>
					<div class="form-group">
						<p> <label class="control-label col-sm-3" for="login"> </label> </p> <br />
						<div class="col-sm-9">
							<input class="btn btn-default" type="submit" value="Login"/>
						</div>
					</div>
					<a href="/Project/jsp_files/registration.jsp">Not registered?</a>
					
					
					<a id="a1" href="/Project/LoginGuestServlet">Continue as guest</a>
			</fieldset>
		</form>
		</div>
	</div>
</body>
</html>