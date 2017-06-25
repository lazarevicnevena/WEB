<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Searched Users</title>

<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style type="text/css">

	body{
		background-image: url("../images/background1.jpg");
    	background-color: white;
    	opacity: 0.9;
   	}
   	
	#allUsers{
		background-color: white;
		opacity: 0.9;
		margin-left: auto;
		margin-right: auto;
		position: absolute;
		top: 65px;
		width: 99%;
		
	}
	
	#button1{
		position: absolute;
		top: 20px; 
		left: 1%;
	}
	
	#tableUsers{
		display: table;
		table-layout: fixed;
		word-wrap:break-word;
	}
	
	
</style>

<script type="text/javascript">

	function load(){
		var role = '<%= session.getAttribute("role") %>';
		var a = document.getElementById('button1');
		
		if (role == 'guest'){
			a.href = "/Project/jsp_files/guestHomePage.jsp";
		}else if(role == 'ordinary'){
			a.href = "/Project/jsp_files/homePage.jsp";
		}else{
			a.href = "/Project/jsp_files/adminHomePage.jsp";
		}
	}
	

	
</script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body onload="load()">
	
	<a class="btn .btn-primary" type="button" id="button1" title="Back to Home Page">Back</a>
	

	<div class="content" id="allUsers"  align="center" >
		<div class="scrollable">
			<table class="table table-striped" id="tableUsers" >
				
				<thead id="thead">
					<tr style="text-align:left;">
					    <th width="75px">Username</th>
					    <th width="100px">First Name</th>
					    <th width="100px">Last Name</th>
					    <th width="50px">Role</th>
					    <th width="80px">Telephone</th>
					    <th width="140px">Email</th>
					    <th width="100px">Address</th>
					    <th width="50px">Avatar</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${sessionScope.searchedUsers}" var="item">
						<tr style="text-align:left">
					  		<td width="75px">
					  			<c:out value="${item.username}" />
					  		</td>
					      	<td width="100px">
								<c:out value="${item.firstName}" />
							</td> 
							<td width="100px"> 
								<c:out value="${item.lastName}" />
							</td>
							<td width="50px"> 
								<c:out value="${item.role}" />
							</td>
							
							<td width="80px"> 
								<c:out value="${item.telephoneNum}" />
							</td>
							<td width="140px"> 
								<c:out value="${item.email}" />
							</td>
							<td width="100px"> 
								<c:out value="${item.address}" />
							</td>
							
							<td style="display: inline;"> 
								<c:if test="${ sessionScope.loggedUser.role == 'ordinary' || sessionScope.loggedUser.role == 'guest'}">
									<c:if test="${item.picture == 'null'}">
									<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
								</c:if>
								<c:if test="${item.picture != 'null'}">
									<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item.picture}" alt="User" align="middle" />
								</c:if>
								</c:if>
								<c:if test="${ sessionScope.loggedUser.role == 'admin'}">
									<c:if test="${ item.picture == 'null'}">
										<c:if test="${not item.blocked}" >
											<c:if test="${item.role ==  'ordinary'}">
												<a class="btn btn-default" href="/Project/BlockUserServlet/${item.username}" title="Block User">
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}"	 alt="User" align="middle" />
												</a>
											</c:if>
											<c:if test="${item.role ==  'guest'}">
												<a class="btn btn-default" onclick="return false;" href="/" title="You cannot block guests">
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
												</a>
											</c:if>
											<c:if test="${item.role == 'admin'}">
												<a class="btn btn-default" >
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}"	 alt="User" align="middle" />
												</a>
											</c:if>
										</c:if>
										<c:if test="${item.blocked}" >
											<c:if test="${item.role ==  'ordinary'}">
												<a class="btn btn-default" href="/Project/BlockUserServlet/${item.username}" title="Unblock User">
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
												</a>
											</c:if>
											<c:if test="${item.role ==  'guest'}">
												<a class="btn btn-default" onclick="return false;" href="/" title="You cannot block guests">
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
												</a>
											</c:if>
											<c:if test="${item.role == 'admin'}">
												<a class="btn btn-default" >
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}"	 alt="User" align="middle" />
												</a>
											</c:if>
										</c:if>
									</c:if>
									<c:if test="${ item.picture != 'null'}">
										<c:if test="${not item.blocked}" >
											<c:if test="${item.role ==  'ordinary'}">
												<a class="btn btn-default" href="/Project/BlockUserServlet/${item.username}" title="Block User">
													<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item.picture}" alt="User" align="middle" />
												</a>
											</c:if>
										<c:if test="${item.role == 'admin'}">
											<a class="btn btn-default" >
												<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item.picture}"	 alt="User" align="middle" />
											</a>
										</c:if>
									</c:if>
									<c:if test="${item.blocked}" >
										<c:if test="${item.role ==  'ordinary'}">
											<a class="btn btn-default" href="/Project/BlockUserServlet/${item.username}" title="Unblock User">
												<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item.picture}" alt="User" align="middle" />
											</a>
										</c:if>
										<c:if test="${item.role == 'admin'}">
											<a class="btn btn-default" >
												<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item.picture}"	 alt="User" align="middle" />
											</a>
										</c:if>
									</c:if>
																	
																	
								</c:if>
							</c:if>
															
						</td>
				    	</tr>
			    	</c:forEach>
		    	</tbody>
			</table>
		</div>
	</div>

</body>
</html>