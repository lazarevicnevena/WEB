
<%@page import="java_files.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>


<script src="../js/jquery-3.2.1.js"></script>
<script src="../js/bootstrap.js"></script>


<link rel="stylesheet" href="../css/bootstrap.css">


<!-- Date Picker -->
<link rel="stylesheet"type="text/css" href="../datetime/bootstrap-datepicker.min.css">
<script type="text/javascript" src="../datetime/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../datetime/bootstrap.min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">



function showDiv(id){
	
	var snippetsEl = document.getElementById('allSnippets');
	var filterEl = document.getElementById('divFilter');
	if (id != 'searchSnippets' && id != 'searchUsers'){
		snippetsEl.style.display = 'none';
		filterEl.style.display = 'none';
	}
	
	if (id == 'updateProfile'){
		document.getElementById(id).style.display = 'block';
		document.getElementById('snippetCreate').style.display = 'none';
		document.getElementById('programLAdd').style.display = 'none';
		document.getElementById('searchSnippets').style.display = 'none';
		document.getElementById('allSnippets').style.display = 'none';
		document.getElementById('searchUsers').style.display = 'none';
		
	}else if(id == 'snippetCreate'){
		document.getElementById(id).style.display = 'block';
		document.getElementById('updateProfile').style.display = 'none';
		document.getElementById('programLAdd').style.display = 'none';
		document.getElementById('searchSnippets').style.display = 'none';
		document.getElementById('allSnippets').style.display = 'none';
		document.getElementById('searchUsers').style.display = 'none';
	}else if(id == 'programLAdd'){
		document.getElementById(id).style.display = 'block';
		document.getElementById('updateProfile').style.display = 'none';
		document.getElementById('snippetCreate').style.display = 'none';
		document.getElementById('searchSnippets').style.display = 'none';
		document.getElementById('allSnippets').style.display = 'none';
		document.getElementById('searchUsers').style.display = 'none';
	}else if(id == 'searchSnippets'){
		document.getElementById(id).style.display = 'block';
		document.getElementById('updateProfile').style.display = 'none';
		document.getElementById('snippetCreate').style.display = 'none';
		document.getElementById('programLAdd').style.display = 'none';
		document.getElementById('allSnippets').style.display = 'block';
		document.getElementById('searchUsers').style.display = 'none';
	}else if(id == 'allSnippets'){
		document.getElementById(id).style.display = 'block';
		document.getElementById('updateProfile').style.display = 'none';
		document.getElementById('snippetCreate').style.display = 'none';
		document.getElementById('programLAdd').style.display = 'none';
		document.getElementById('searchSnippets').style.display = 'none';
		document.getElementById('searchUsers').style.display = 'none';
	}else if(id == 'searchUsers'){
		document.getElementById(id).style.display = 'block';
		document.getElementById('updateProfile').style.display = 'none';
		document.getElementById('snippetCreate').style.display = 'none';
		document.getElementById('programLAdd').style.display = 'none';
		document.getElementById('searchSnippets').style.display = 'none';
		document.getElementById('allSnippets').style.display = 'block';
	}
	
	
}
	
	function load(id){
		
		var show = '<%= session.getAttribute("divShown") %>';
		
		if (show == 'programLAdd'){
			document.getElementById(show).style.display = 'block';
			var alert1 = <%= session.getAttribute("messageProgr") %>;
			if (alert1 == true){
				alert("Programming language already exists!");
				var snippetsEl = document.getElementById('divFilter');
				snippetsEl.style.display = 'none';
				var snippetsEl1 = document.getElementById('allSnippets');
				snippetsEl1.style.display = 'none';
			}
		}else{
			var snippetsEl = document.getElementById('allSnippets');
			snippetsEl.style.display = 'block';
			document.getElementById('updateProfile').style.display = 'none';
			document.getElementById('snippetCreate').style.display = 'none';
			document.getElementById('programLAdd').style.display = 'none';
			document.getElementById('searchSnippets').style.display = 'none';
			document.getElementById('searchUsers').style.display = 'none';
		}
		
		
		var Msg ='<%=session.getAttribute("errorMsg")%>';
		if (Msg != ' ') {
			alert(Msg);
			session.setAttribute("errorMsg", " ");
		}
		
		var img1 = '<%= session.getAttribute("pic")%>';
		if (img1 == 'yes'){
			image.src = "../images/user.png";
		}
		
		
	}
	
	function fun(){
		document.getElementById('formDate').submit();
	}
	
	function function1(){
		document.getElementById('formLang').submit();
		document.getElementById('filterLang').style.display = 'none';
	}

	function fun2(){
		document.getElementById('filterLang').style.display = 'block';
	}
	
	function validateSnippets(form)
	{
		if (form.description.value == "")
		{
			alert("Error! Description field is empty.");
			form.description.focus();
			return false;
		}
		if (form.snippetCode.value == "")
		{
			alert("Error! Snippet content field is empty.");
			form.snippetCode.focus();
			return false;
		}
		if (form.urlAddr.value == "")
		{
			alert("Error! Url address field is empty.");
			form.urlAddr.focus();
			return false;
		}
	}
	
	function validateLang(form)
	{
		if (form.language.value == "")
		{
			alert("Error! Language field is empty.");
			form.language.focus();
			return false;
		}
		
	}
		
	

<% ArrayList<String> languages = (ArrayList<String>)request.getSession().getAttribute("languages"); 
	%>

</script>

<style>
	
	
	body{
		background-image: url("../images/background1.jpg");
    	background-color: white;
    	opacity: 0.9;
   	}
	
	ul {
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	    background-color: #002699;
	}
	
	li {
	    float: left;
	}
	
	li a {
	    display: inline-block;
	    color: white;
	    text-align: center;
	    padding: 14px 16px;
	    text-decoration: none;
	}
	
	li a:hover {
	    background-color: #ff4d4d;
	}
	
	#snippetCreate{
		background-color: white;
		opacity: 0.9;
		position: absolute;
		top: 60px;
		width: 99%;
		height: 100%;
	}
	
	#updateProfile{
		background-color: white;
		opacity: 0.9;
		position: absolute;
		top: 60px;
		width: 99%;
		height: 100%;
	}
	
	#programLAdd{
		background-color: white;
		opacity: 0.9;
		position: absolute;
		top: 60px;
		width: 99%;
		height: 100%;
	}
	
	#tableSnippets{
		display: table;
		table-layout: fixed;
		word-wrap:break-word;
	}
	
	#allSnippets{
		background-color: white;
		opacity: 1;
		margin-left: auto;
		margin-right: auto;
		position: absolute;
		top: 170px;
		width: 99%;
		
	}
	
	#divFilter{
		background-color: white;
		opacity: 1;
		position: absolute;
		width: 99%;
		height: 70px;
		top: 100px;
		border: 0;
		
	}

	#likesDiv{
		position: absolute;
		right: 2%;
		top: 1%;
	}
	
	#date1{
		position: absolute;
		left: 1%;
		top: 10%;
		float:left;
	}
	
	#picLang{
		position: absolute;
		left: 5%;
	}
	
	#filterLang{
		position: absolute;
		left: 10%;
		top: 5%;
	}
	
	#tbody1{
		height: 250px;
		overflow-y: auto; 
	}
	
	
	#searchSnippets{
		position: absolute;
		top: 60px;
		left: 1%;
	}
	
	#searchUsers{
		position: absolute;
		top: 60px;
		left: 1%;
	}
	
	#pic{
		position: absolute;
		left:15%;
		top: 20%;
	}
	
	#upload{
		position: absolute;
		left:50%;
		top: 10%;
	}
	
	#image {
	  position: absolute;
	  left : 2%;
	  top : 25%;
	  width: 150px;
	  height: 150px;
	 
	}
	
</style>
</head>
<body onload="load()">
		 <ul>
			  <li><a href="#" onclick="showDiv('updateProfile');"> Profile Settings </a></li>
			  <li><a href="/Project/SnippetServlet" onclick="showDiv('allSnippets');"> Show All Snippet </a></li>
			  <li><a href="#" onclick="showDiv('snippetCreate');"> Create Snippet </a></li>
			  <li><a href="#" onclick="showDiv('programLAdd');"> Add Programming Language </a></li>
			  <li><a href="#" onclick="showDiv('searchSnippets');">Search Snippets</a></li>
			  <li><a href="#" onclick="showDiv('searchUsers');">Search Users</a></li>
			  <li><a href="http://localhost:8080/Project/LogoutServlet" >Log Out</a></li>
			</ul>
	
	<div id="searchSnippets" style="display: none;">
		<form class="form-search" action="http://localhost:8080/Project/SearchSnippets" method="post">
			<div class="input-group">
				<input class="form-control" name="filter" id="searchId1" type="text"  
					style="text-align: left;background-color: #eee;width: 150px;height: 27px;
					font-size: 12px;border: 1px solid #ddd;"
					title="Search snippets by description" />
				<span class="input-group-addon" style="height: 27px; width:45px;">
					<button type="submit" style="background-color: #eee;padding-bottom:23px; padding-left:0px;
						 border: 0px;height: 25px; width: 30px;padding-right: 23px;">
						<img class="img-rounded" id="image3" style="width: 30px;height: 25px;" src="../images/find.png" alt="Search" align="middle" />
					</button>
				</span>
				
			</div>
		</form>
				
		<script>
			$("#searchId1").click(function(){
			    $("#searchId1").animate({ width: "250px" }, 500);
			});
			$("#searchId1").blur(function(){
	    		$("#searchId1").animate({ width: "150px"}, 500);
			});
		</script>
	</div>
	
	<div id="searchUsers" style="display: none;">
		<form class="form-search" action="/Project/SearchUsersServlet" method="post">
			<div class="input-group">
				<input class="form-control" name="filter" id="searchId2" type="text"  
					style="text-align: left;background-color: #eee;width: 150px;height: 27px;
					font-size: 12px;border: 1px solid #ddd;"
					title="Search users by username" />
				<span class="input-group-addon" style="height: 27px; width:45px;">
					<button type="submit" style="background-color: #eee;padding-bottom:23px; padding-left:0px;
						 border: 0px;height: 25px; width: 30px;padding-right: 23px;">
						<img class="img-rounded" id="image3" style="width: 30px;height: 25px;" src="../images/find.png" alt="Search" align="middle" />
					</button>
				</span>
			</div>
		</form>
				
		<script>
			$("#searchId2").click(function(){
			    $("#searchId2").animate({ width: "250px" }, 500);
			});
			$("#searchId2").blur(function(){
	    		$("#searchId2").animate({ width: "150px"}, 500);
			});
		</script>
	</div>
	
		 
	<div id="divFilter">
		
			 
			<div class="input-group" id="date1">
			 <form id="formDate" method="POST" class="form-inline" action="/Project/FilterDateServlet">
			 	<div >
		        	<div class="input-group date" data-provide="datepicker">
				    	<span class="input-group-addon" style="height: 35px; width: 35px; background-color: white; opacity: 1; border: 0;">
							<img style="height: 35px; width: 35px;" title="Filter snippets by chosen date" class="manImg" src="../images/filter-date.png"></img>
						</span>
				    	<input class="form-control" onchange="fun()" style="visibility: hidden;" name="date" id="date" type="text"  readonly="readonly"/>
						    
							   
					</div>
					<script>
				 		$.fn.datepicker.defaults.format = "dd/mm/yyyy";
				 		$.fn.datepicker.defaults.autoclose = true;
				 				
						$('.datepicker').datepicker({
							showButtonPanel: true,
							autoclose : true
						});
					</script>
		         </div>
				</form>	    
			  </div>
			  
			  
			  <div class="form-group" id="picLang" style="width: 35px;height: 35px;">
				<a class="btn btn-default" >
					<img class="img-rounded" id="image1" title="Filter snippets by chosen Programming Language" 
					style="width: 35px;height: 35px;" onclick="fun2()"
					src="../images/lang2.png" alt="Filter" align="middle" />
				</a>
			  </div>
			  
			  <div class="dropdown" id="filterLang" style="display: none;">
			  	
			  	<form id="formLang" method="POST" action="/Project/FilterLangServlet">
				    <select onchange="function1()" class="form-control" id="lang" name="language"
				     style=" background-image:url('../images/filter-lang.png');">
				       <option disabled selected value>choose one</option>
				       <option value="Undefined"> Undefined</option>
						<% for (String p: languages) { %>
							<option value="<%=p %>"> <%=p %></option>
				  		<% } %>
							
					</select>
				</form>
			  </div>
			  
			  
			  <div style="width: 35px;height: 35px;" class="form-group" id="likesDiv">
					<a class="btn btn-default" href="/Project/SortComments" title="Show best rated comments first">
						<img class="img-rounded" id="image1" style="width: 35px;height: 35px;" src="../images/filter.png" alt="Filter" align="middle" />
					</a>
				
			</div>
				
	 </div>
	
	<div class="content" id="allSnippets" >
	
		
		<div class="scrollable" align="center">
	
			<table class="table table-striped" id="tableSnippets">
				
				<thead id="thead">
					<tr style="text-align:left;">
					    <th width="300px">Content</th>
					    <th width="150px">Description</th>
					    <th width="75px">Language</th>
					    <th width="170px">URL Address</th>
					    <th width="120px">Date</th>
					    <th width="40px"></th>
					    <th width="60px"></th>
					</tr>
					<tr></tr>
					<tr></tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${sessionScope.snippets}" var="item">
						<tr style="text-align:left">
					  		<td width="300px">
					  			<textarea readonly="readonly" name="content" style="width:300px;height:120px;resize: none;font-size: 12px;"><c:out value="${item.content}" />  </textarea>
					  		</td>
					      	<td width="150px">
								<c:out value="${item.description}" />
							</td> 
							<td width="75px"> 
								<c:out value="${item.programmingLanguage}" />
							</td>
							<td width="170px"> 
								<a href="${item.urlAddress}">${item.urlAddress} </a>
							</td>
							<td width="120px"> 
								<fmt:formatDate type = "both" 
         								pattern="HH:mm ' - 'dd.MM.yyyy"
         								value = "${item.date}" />
							</td>
							<td width="40px">
								<c:if test="${item.canBeCommented}">
								
									<form style="display:inline" action="/Project/DisableCommentingServlet/${item.id}" method="post">
										<input style="display:inline"  title="Click to disable commenting for this snippet" type="submit" value="&#128275;">
									</form>
								</c:if>
								<c:if test="${not item.canBeCommented}">
									 <form style="display:inline" action="/Project/DisableCommentingServlet/${item.id}" method="post">
										<input style="display:inline"  title="Click to enable commenting for this snippet" type="submit" value="&#128274;">
									</form>
								</c:if>
							</td>
							
							<td width="60px">	
						        <form style="display:inline" action="/Project/DeleteSnippetServlet/${item.id}" method="post">
									<input style="display:inline"  title="Click to delete this snippet" type="submit" value="&#10060;">
								</form>
							</td>							
					        
				    	</tr>
				    	
				    	<tr align="center">
				    		<td>
					    		<c:if test="${not empty sessionScope.comments}">
						    		<table align="center" class="table table-striped" id="comments" style="white-space: nowrap;margin:auto;border:solid; width:50%">
										
										<tbody  id="tbody1">
											<c:forEach items="${sessionScope.comments}" var="item1">
												<c:if test="${item.id == item1.snippet.id }">
													<tr>
												      	<td>
															<c:out value="${item1.text}" />
														</td> 
														<td width="120px"> 
															<fmt:formatDate type = "both" 
							         							pattern="HH:mm ' - 'dd.MM.yyyy"
							         							value = "${item1.date}" />
														</td>
															<c:if test="${not empty sessionScope.userMarks}">
															
															<td style="display: inline;"> 
																<c:out value="${item1.rate.positiveRates}" />
																<c:if test="${sessionScope.loggedUser.username != item1.user.username }">
																	<form style="display:inline" action="/Project/PositiveMarkServlet/${item1.id}" method="post">
																		<input style="display:inline" title="Click to rate this comment" type="submit" value="&#128077;">
																	</form>
																</c:if>
																<c:if test="${sessionScope.loggedUser.username == item1.user.username }">
																	<form style="display:inline" action="/Project/PositiveMarkServlet/${item1.id}" method="post">
																		<input style="display:inline" disabled="disabled" title="You cannot rate your own comment" type="submit" value="&#128077;">
																	</form>
																</c:if>
															</td>
															
															<td style="display: inline;"> 
																<c:out value="${item1.rate.negativeRates}" />
																<c:if test="${sessionScope.loggedUser.username != item1.user.username }">
																	<form style="display:inline" action="/Project/NegativeMarkServlet/${item1.id}" method="post">
																		<input style="display:inline" title="Click to rate this comment" type="submit" value="&#128078;">
																	</form>
																</c:if>
																<c:if test="${sessionScope.loggedUser.username == item1.user.username }">
																	<input style="display:inline" disabled="disabled" title="You cannot rate your own comment" type="submit" value="&#128078;">
																</c:if>
															</td>
														</c:if>
														<c:if test="${empty sessionScope.userMarks}">
															<td style="display: inline;"> 
																<c:out value="${item1.rate.positiveRates}" />
																<c:if test="${sessionScope.loggedUser.username != item1.user.username }">
																	<form style="display:inline" action="/Project/PositiveMarkServlet/${item1.id}" method="post">
																		<input style="display:inline;"  title="Click to rate this comment" type="submit" value="&#128077;">
																	</form>
																</c:if>
																<c:if test="${sessionScope.loggedUser.username == item1.user.username }">
																	<form style="display:inline" action="/Project/PositiveMarkServlet/${item1.id}" method="post">
																		<input style="display:inline" disabled="disabled" title="You cannot rate your own comment" type="submit" value="&#128077;">
																	</form>
																</c:if>
															</td>
																		
																				
															<td style="display: inline;"> 
																<c:out value="${item1.rate.negativeRates}" />
																<c:if test="${sessionScope.loggedUser.username != item1.user.username }">
																	<form style="display:inline" action="/Project/NegativeMarkServlet/${item1.id}" method="post">
																		<input id="button1" style="display:inline"  title="Click to rate this comment" type="submit" value="&#128078;">
																	</form>
																</c:if>
																<c:if test="${sessionScope.loggedUser.username == item1.user.username }">
																	<input style="display:inline" disabled="disabled" title="You cannot rate your own comment" type="submit" value="&#128078;">
																</c:if>
															</td>
														</c:if>
															
														<td > 
															<c:out value="${item1.user.username}" />
														</td>
														
														
														
														<td style="display: inline;"> 
															<c:if test="${ sessionScope.loggedUser.role == 'admin'}">
																<c:if test="${ item1.user.picture == 'null'}">
																	<c:if test="${not item1.user.blocked}" >
																		<c:if test="${item1.user.role ==  'ordinary'}">
																			<a class="btn btn-default" href="/Project/BlockUserServlet/${item1.user.username}" title="Block User">
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}"	 alt="User" align="middle" />
																			</a>
																		</c:if>
																		<c:if test="${item1.user.role ==  'guest'}">
																			<a class="btn btn-default" onclick="return false;" href="/" title="You cannot block guests">
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
																			</a>
																		</c:if>
																		<c:if test="${item1.user.role == 'admin'}">
																			<a class="btn btn-default" >
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}"	 alt="User" align="middle" />
																			</a>
																		</c:if>
																	</c:if>
																	<c:if test="${item1.user.blocked}" >
																		<c:if test="${item1.user.role ==  'ordinary'}">
																			<a class="btn btn-default" href="/Project/BlockUserServlet/${item1.user.username}" title="Unblock User">
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
																			</a>
																		</c:if>
																		<c:if test="${item1.user.role ==  'guest'}">
																			<a class="btn btn-default" onclick="return false;" href="/" title="You cannot block guests">
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
																			</a>
																		</c:if>
																		<c:if test="${item1.user.role == 'admin'}">
																			<a class="btn btn-default" >
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}"	 alt="User" align="middle" />
																			</a>
																		</c:if>
																	</c:if>
																</c:if>
																<c:if test="${ item1.user.picture != 'null'}">
																	<c:if test="${not item1.user.blocked}" >
																		<c:if test="${item1.user.role ==  'ordinary'}">
																			<a class="btn btn-default" href="/Project/BlockUserServlet/${item1.user.username}" title="Block User">
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item1.user.picture}"	 alt="User" align="middle" />
																			</a>
																		</c:if>
																		<c:if test="${item1.user.role == 'admin'}">
																			<a class="btn btn-default" >
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item1.user.picture}"	 alt="User" align="middle" />
																			</a>
																		</c:if>
																	</c:if>
																	<c:if test="${item1.user.blocked}" >
																		<c:if test="${item1.user.role ==  'ordinary'}">
																			<a class="btn btn-default" href="/Project/BlockUserServlet/${item1.user.username}" title="Unblock User">
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item1.user.picture}" alt="User" align="middle" />
																			</a>
																		</c:if>
																		<c:if test="${item1.user.role == 'admin'}">
																			<a class="btn btn-default" >
																				<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item1.user.picture}"	 alt="User" align="middle" />
																			</a>
																		</c:if>
																	</c:if>
																	
																	
																</c:if>
															</c:if>
															
														</td>
														<td>	
																
															<form  action="/Project/DeleteCommentServlet/${item1.id}" method="post">
																<input style="display:inline"  title="Click to delete this comment" type="submit" value="&#10060;">
															</form>
																
												        </td>
													        
											    	</tr>
											    </c:if>
							    			</c:forEach>
								    	
						    			</tbody>
					    			</table>
							    		
							  	</c:if>
					    	</td>	
				    	</tr>
				    			
				    	
				    	<tr>
					        <c:if test="${item.canBeCommented}">
						        <td >					        	
						        	<form id="form3" action="http://localhost:8080/Project/AddCommentServlet/${item.id}" method="post">
						            	<input type="text" name="txtComment" width="100px"> 
						            	<input type="submit" value="Add Comment"> 
						            </form>
						        </td>
					        </c:if>
					                            
					    </tr>
				    	
					      	
			    	</c:forEach>
		    	</tbody>
			</table>
		</div>
	</div>
	
	
	<div id="programLAdd" class="container" style="display: none">
		<form name="form2" action="/Project/ProgrammingLanguageServlet" method="POST" onsubmit="return validateLang(this);">
			<fieldset>
				<legend>Adding Programming Language</legend>
					<label class="control-label col-sm-3" for="name">Name of Programming Language:</label>
					<div class="col-sm-9">	
						<input class="form-control" style="width:200px;" type="text" name="language" />
					</div>
					<label class="control-label col-sm-3"  for="Add"> </label>
					<div class="col-sm-9">	
						<input class="btn btn-primary" type="submit" value="Add"/>
					</div>
			</fieldset>
		</form>
	</div>
	
	<div id="snippetCreate" class="container" style="display: none">
		<form name="form1" action="http://localhost:8080/Project/SnippetCreationServlet" method="POST" onsubmit="return validateSnippets(this)">
			<fieldset>
				<legend>Snippet creation</legend>
					<label class="control-label col-sm-3" for="description">Short description:</label>
					<div class="col-sm-9">	
						<textarea class="form-control" name="description" style="width:300px;height:75px;"></textarea>
					</div>
					<label class="control-label col-sm-3">Snippet code:</label>
					<div class="col-sm-9">	
						<textarea class="form-control" onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}" name="snippetCode" style="width:300px;height:120px;"></textarea>
					</div>
					<label class="control-label col-sm-3" for="urlAddr">URL Address:</label>
					<div class="col-sm-9">	
						<input class="form-control" style="width:300px;" type="url" name="urlAddr" />
					</div>
					<label class="control-label col-sm-3" for="language">Programming language: </label>
					<div class="col-sm-9">	
						<select style="width: 200px;" class="form-control" name="language">
							<option value="Undefined"> Undefined</option>
							<% for (String p: languages) { %>
								<option value="<%=p %>"> <%=p %></option>
					  		<% } %>
						</select>
					</div>
					
					<label class="control-label col-sm-3" for="Create"> </label>
					<div class="col-sm-9">	
						<input class="btn .btn-primary" type="submit" value="Create"/>
					</div>
			</fieldset>
		</form>
	</div>

	<div id="updateProfile" class="container" style="display: none">
	
		
		<img class="img-rounded" id="image" src="${pageContext.request.contextPath}${loggedUser.picture}" alt="User" align="middle" />
		
		<div class="my-panel vertical-center" id="pic">
			<form name="form2" class="form-horizontal" method="POST" action="/Project/UploadServlet" enctype="multipart/form-data">
				<label class="control-label col-sm-3" for="file">Picture:</label>
				<div class="col-sm-9">	
					<input class="form-control" id="file" type="file" name="file" accept="image/*" value="Change Profile Picture" onchange="changed()" />
				</div>
				<div class="form-group">
					<input class="form-control"  id="hidden" type="text" name="hidden" hidden="hidden" />
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="update"> </label>
					<div class="col-sm-9">
						<input class="btn btn-default" type="submit" value="Change Picture"/>
					</div>
				</div>
			</form>
		</div>
		
		<div class="container">
			<div class="my-panel vertical-center" id="upload">
				<form style="white-space: nowrap;" name="form1" action="http://localhost:8080/Project/UpdateServlet" method="POST" onsubmit="return validate(this)">
					<fieldset>
						<legend>Update</legend>
							
							<div class="form-group">
								<label class="control-label col-sm-3" for="firstName">First Name:</label> 
								<div class="col-sm-9">
									<input class="form-control" style="width: 200px;" type="text" name="firstName" value="<%= ((User)request.getSession().getAttribute("loggedUser")).getFirstName() %>" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="lastName">Last Name:</label> 
								<div class="col-sm-9">
									<input class="form-control" style="width: 200px;" type="text" name="lastName" value="<%= ((User)request.getSession().getAttribute("loggedUser")).getLastName() %>" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="telephoneNum">Telephone Num:</label>
								<div class="col-sm-9">
									<input class="form-control" style="width: 200px;" type="text" name="telephoneNum" value="<%= ((User)request.getSession().getAttribute("loggedUser")).getTelephoneNum() %>" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="email">Email:</label> 
								<div class="col-sm-9">
									<input class="form-control" style="width: 200px;" type="email" name="email" value="<%= ((User)request.getSession().getAttribute("loggedUser")).getEmail() %>" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="address">Address:</label> 
								<div class="col-sm-9">
									<input class="form-control" style="width: 200px;" type="text" name="address" value="<%= ((User)request.getSession().getAttribute("loggedUser")).getAddress() %>" /><br />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="register"> </label>
								<div class="col-sm-9">
									<input class="btn btn-primary" type="submit" value="Update"/>
								</div>
							</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>