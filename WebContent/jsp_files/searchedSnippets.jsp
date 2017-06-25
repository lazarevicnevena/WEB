<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Searched Snippets</title>

<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<style type="text/css">

	body{
		background-image: url("../images/background1.jpg");
    	background-color: white;
    	opacity: 0.9;
   	}

	#allSnippets{
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
	
	#tableSnippets{
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
	

	<div class="content" id="allSnippets"  align="center" >
		<div class="scrollable">
			<table class="table table-striped" id="tableSnippets">
				
				<thead id="thead">
					<tr style="text-align:left;">
					    <th width="300px">Content</th>
					    <th width="150px">Description</th>
					    <th width="75px">Language</th>
					    <th width="170px">URL Address</th>
					    <th width="120px">Date</th>
					</tr>
					<tr></tr>
					<tr></tr>
				</thead>
				<tbody id="tbody">
					<c:forEach items="${sessionScope.searchedSnippets}" var="item">
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
														<td> 
															<c:out value="${item1.date}" />
														</td>
														
														<c:if test="${not empty sessionScope.userMarks}">
															
															<td style="display: inline;"> 
																<c:out value="${item1.rate.positiveRates}" />
																<input style="display:inline" disabled="disabled" title="You cannot rate comment as long as you are a guest" type="submit" value="&#128077;">
															</td>
															
															<td style="display: inline;"> 
																<c:out value="${item1.rate.negativeRates}" />
																<input style="display:inline" disabled="disabled" title="You cannot rate comment as long as you are a guest" type="submit" value="&#128078;">
															</td>
														</c:if>
														<c:if test="${empty sessionScope.userMarks}">
															<td style="display: inline;"> 
																<c:out value="${item1.rate.positiveRates}" />
																<input style="display:inline" disabled="disabled" title="You cannot rate comment as long as you are a guest" type="submit" value="&#128077;">
															</td>
																		
																				
															<td style="display: inline;"> 
																<c:out value="${item1.rate.negativeRates}" />
																<input style="display:inline" disabled="disabled" title="You cannot rate comment as long as you are a guest" type="submit" value="&#128078;">
															</td>
														</c:if>
														<td > 
															<c:out value="${item1.user.username}" />
														</td>
														
														
														
														<td style="display: inline;"> 
															
																<c:if test="${item1.user.picture == 'null'}">
																	<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${sessionScope.altPic}" alt="User" align="middle" />
																</c:if>
																<c:if test="${item1.user.picture != 'null'}">
																	<img class="img-rounded" id="image1" style="width: 50px;height: 50px;" src="${pageContext.request.contextPath}${item1.user.picture}" alt="User" align="middle" />
																</c:if>
														</td>
														
													        
											    	</tr>
											    </c:if>
							    			</c:forEach>
								    	
						    			</tbody>
					    			</table>
							    		
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