<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/bootstrap.min.js"></script>

<style>
body {
	background-image: url("../images/background1.jpg");
	background-color: #cccccc;
}

#id1 {
	position: aboslute;
	top: 40%;
}

#cont {
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

	function load() {
		var msg = '<%= session.getAttribute("messageReg") %>';
		if (msg == 'yes'){
			alert("User with this username already exists!");
		}
	}

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
		if (form.password2.value == "")
		{
			alert("Error! Repeated password field is empty.");
			form.password2.focus();
			return false;
		}
		if (form.password.value != form.password2.value ){
			alert("Error! Passwords must match.");
			form.password2.focus();
			return false;
		}
		if (form.firstName.value == "")
		{
			alert("Error! First name field is empty.");
			form.firstName.focus();
			return false;
		}
		if (form.lastName.value == "")
		{
			alert("Error! Last name field is empty.");
			form.lastName.focus();
			return false;
		}
		if (form.telephoneNum.value == "")
		{
			alert("Error! Telephone number field is empty.");
			form.telephoneNum.focus();
			return false;
		}
			
		var phoneno = /^\(?([0-9]{3})\)?[-]([0-9]{3})[-]([0-9]{4})$/;
		if(form.telephoneNum.value.match(phoneno)) {
			return true;
		}
		else {
			alert("Error! Phone number should look like : XXX-XXX-XXXX");
		   	return false;
		}
		
		if (form.address.value == "")
		{
			alert("Error! Address field is empty.");
			form.address.focus();
			return false;
		}
		
	}	

</script>

</head>
<body onload="load()">

	
    <div class="error" style="color : red"><% session.getAttribute("message"); %></div>
  
	
	<div id="id1" class="container" align="center">
		<div class="my-panel vertical-center" id="cont">
			<form name="form1" action="http://localhost:8080/Project/RegistrationServlet"
			 method="POST" onsubmit="return validate(this)" style="white-space: nowrap;">
			<fieldset>
				<legend>Registration</legend>
					<div class="form-group">
						<label class="control-label col-sm-3" for="username">Username:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="text" name="username" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="password">Password:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="password" name="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="password">Password:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="password" name="password2" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="firstName">First Name:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="text" name="firstName" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="lastName">Last Name:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="text" name="lastName" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="telephoneNum">Telephone Num:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="text" name="telephoneNum" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="email">Email:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="email" name="email" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="address">Address:</label>
						<div class="col-sm-9">
							<input class="form-control" style="width: 200px;" type="text" name="address" />
						</div>
					</div>
					<div class="form-group">
						<label class="field" for="register"> </label>
						<div class="col-sm-9">
							<input class="btn btn-default" type="submit" value="Register"/>
						</div>
					</div>
			</fieldset>
		</form>
	</div>
	</div>
</body>
</html>