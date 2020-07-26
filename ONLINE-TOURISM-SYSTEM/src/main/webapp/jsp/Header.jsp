<%@page import="in.co.online.tourism.sys.controller.LoginCtl"%>
<%@page import="in.co.online.tourism.sys.bean.UserBean"%>
<%@page import="in.co.online.tourism.sys.controller.OTSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/ONLINE-TOURISM-SYSTEM/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/ONLINE-TOURISM-SYSTEM/css/style.css" >
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  $( function() {
	    $( "#datepicker1" ).datepicker({
	      changeMonth: true,
	      changeYear: true
	    });
	  } );
  </script>
  
  <script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
<%
		UserBean userBean = (UserBean) session.getAttribute("user");

		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hi, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";
		}
	%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
   <a class="navbar-brand" href="#">
    <img src="/ONLINE-TOURISM-SYSTEM/image/blue-circular-abstract-logo_1043-69.jpg" width="45px" height="45px" class="d-inline-block align-top" alt="">
    Online National Tourism System  
  </a>
</nav>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
       <li class="nav-item active">
        <a class="nav-link" href="<%=OTSView.HOME_CATEGORY_LIST_CTL%>">Home <span class="sr-only">(current)</span></a>
      </li>
    <%
		if (userLoggedIn) {
	%>

			<%
				if (userBean.getRoleId() == 1) {
			%>
     
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           Category
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="nav-link" href="<%=OTSView.CATEGORY_CTL%>">Add Category</a>
             <a class="nav-link" href="<%=OTSView.CATEGORY_LIST_CTL%>">Category List</a>
          
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           Package
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         <a class="nav-link" href="<%=OTSView.PACKAGE_CTL%>">Add Package</a>
          <a class="nav-link" href="<%=OTSView.PACKAGE_LIST_CTL%>">Package List</a>
        </div>
      </li>
      
         <li class="nav-item active">
        <a class="nav-link" href="<%=OTSView.BOOKING_LIST_CTL%>">Booking List</a>
      </li>
     	
      
      
      <%}else if (userBean.getRoleId() == 2) {%>
     
      <li class="nav-item active">
        <a class="nav-link" href="<%=OTSView.HOME_CATEGORY_LIST_CTL%>">Category</a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="<%=OTSView.HOME_PACKAGE_LIST_CTL%>">Package</a>
      </li>

       <li class="nav-item active">
        <a class="nav-link" href="<%=OTSView.BOOKING_LIST_CTL%>">Booking List</a>
      </li>
      
      
      <%}}else{%>
      <li class="nav-item">
        <a class="nav-link" href="<%=OTSView.LOGIN_CTL%>">SignIn</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=OTSView.USER_REGISTRATION_CTL%>">SignUp</a>
      </li>
      	
      <%} %>
    </ul>
    <form class="form-inline my-2 my-lg-0">
    <%
	if (userLoggedIn) {
		%>
    <ul class="navbar-nav mr-auto">
    <li class="nav-item">
        <a class="nav-link" href="<%=OTSView.MY_PROFILE_CTL%>">My Profile</a>
        
      </li>
     <li class="nav-item">
        <a class="nav-link" href="<%=OTSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
        
      </li>
      <li class="nav-item">
      <a class="nav-link" href="<%=OTSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
      </li>
</ul>   
<%} %>
 </form>
  </div>
</nav>