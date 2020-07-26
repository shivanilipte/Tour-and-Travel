<%@page import="in.co.online.tourism.sys.controller.LoginCtl"%>
<%@page import="in.co.online.tourism.sys.util.DataUtility"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@page import="in.co.online.tourism.sys.controller.OTSView"%>
<%@ include file="Header.jsp"%>
<nav aria-label="breadcrumb" style="margin-top: 7px;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Login</li>
  </ol>
</nav>

<div class="container1">
  <div class="row">
    <div class="col-md">
   <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" style="margin-left: 10px">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/ONLINE-TOURISM-SYSTEM/image/124935.jpg" class="d-block w-100" alt="..." height="303px">
    </div>
    <div class="carousel-item">
      <img src="/ONLINE-TOURISM-SYSTEM/image/79-797454_jammu-and-kashmir-tourism.jpg" height="303px" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/ONLINE-TOURISM-SYSTEM/image/wp2649813.jpg" class="d-block w-100" height="303px" alt="...">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
    </div>
    <div class="col-md">
   <div class="container1">
 <div style="margin-top: 3px">
  <h2>Login</h2>
 </div>
 <jsp:useBean id="bean" class="in.co.online.tourism.sys.bean.UserBean"
		scope="request"></jsp:useBean>
 <b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
 <b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
  <form method="post" action="<%=OTSView.LOGIN_CTL%>" style="width: 68%">
               <%
					String uri = (String) request.getAttribute("uri");
				%>

				<input type="hidden" name="uri" value="<%=uri%>"> <input
					type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
    <div class="form-group">
      <label for="uname">Login Id:</label>
      <input type="text" class="form-control"  name="login" placeholder="Enter Login Id"  value="<%=DataUtility.getStringData(bean.getLogin()) %>" >
     <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("login", request)%></font>
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control"  placeholder="Enter password" name="password" value="<%=DataUtility.getStringData(bean.getPassword())%>">
      <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("password", request)%></font>
    </div>
    
    <input type="submit" name="operation" class="btn btn-primary"
					value="<%=LoginCtl.OP_SIGN_IN%>"> or <input type="submit" name="operation" class="btn btn-primary"
					value="<%=LoginCtl.OP_SIGN_UP%>">
					<br><br>
					<!-- <a href="#" >Forget Password ?</a>  -->
    <br><br>
  </form>
</div>
    </div>
   
  </div>
</div>
<div style="margin-top: 39px">
<%@ include file="Footer.jsp"%>
</div>