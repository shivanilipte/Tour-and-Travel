<%@page import="in.co.online.tourism.sys.controller.ChangePasswordCtl"%>
<%@page import="in.co.online.tourism.sys.controller.UserRegistrationCtl"%>
<%@page import="in.co.online.tourism.sys.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.tourism.sys.util.DataUtility"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@ include file="Header.jsp" %>
<nav aria-label="breadcrumb" style="margin-top: 7px;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Change Password</li>
  </ol>
</nav>
<div class="container" style="border: ridge;margin-top: 20px;margin-left: 50px">
<div style="margin-top: 18px">
  <h2>Change Password</h2>
 </div>
 
  <jsp:useBean id="bean" class="in.co.online.tourism.sys.bean.UserBean"
		scope="request"></jsp:useBean>
 <b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
 <b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
<form style="width: 60%;margin-top: 25px" action="<%=OTSView.CHANGE_PASSWORD_CTL%>" method="post">

<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				
  
  <div class="form-group">
    <label for="inputAddress">Old Password</label>
    <input type="password" class="form-control" name="oldPassword"  placeholder="Enter Old Password..." value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
    	  <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">New Password:</label>
    
      <input type="password" class="form-control" name="newPassword"  placeholder="Enter New Password..." value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%> >
    	  <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("New Password", request)%></font>
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Confirm Password:</label>
       <input type="password" class="form-control" name="confirmPassword"  placeholder="Enter Confirm Password" value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%> >
    	  <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
    </div>
   
  </div>
          

    <input type="submit" name="operation" class="btn btn-primary"
					value="<%=ChangePasswordCtl.OP_SAVE%>"> 
					
    <br><br>
</form>
</div>
<br><br>
<%@ include file="Footer.jsp" %>