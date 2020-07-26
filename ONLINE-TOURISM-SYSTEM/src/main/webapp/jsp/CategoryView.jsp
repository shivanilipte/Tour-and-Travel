<%@page import="in.co.online.tourism.sys.controller.CategoryCtl"%>
<%@page import="in.co.online.tourism.sys.controller.UserRegistrationCtl"%>
<%@page import="in.co.online.tourism.sys.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.tourism.sys.util.DataUtility"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@ include file="Header.jsp" %>
<nav aria-label="breadcrumb" style="margin-top: 7px;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Category</li>
  </ol>
</nav>
<div class="container" style="border: ridge;margin-top: 20px;margin-left: 50px">
<div style="margin-top: 18px">
  <h2>Category</h2>
 </div>
 
  <jsp:useBean id="bean" class="in.co.online.tourism.sys.bean.CategoryBean"
		scope="request"></jsp:useBean>
 <b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
 <b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
<form style="width: 60%;margin-top: 25px" action="<%=OTSView.CATEGORY_CTL%>" method="post" enctype="multipart/form-data">

<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				

  <div class="form-group">
    <label for="inputAddress">Name</label>
    <input type="text" class="form-control" name="name"  placeholder="Enter Name..." value="<%=DataUtility.getStringData(bean.getName())%>" >
    	  <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("name", request)%></font>
  </div>
  
  
  <div class="form-group">
    <label for="inputAddress">Image</label>
    <input type="file" class="form-control" name="photo"  placeholder="Enter Image..."  >
    	  <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("photo", request)%></font>
  </div>
  
		

 <div class="form-group">
    <label for="inputAddress">Description</label>
    <textarea type="text" class="form-control" rows="4" cols="5" name="description"  placeholder="Enter Description..."  ><%=DataUtility.getStringData(bean.getDescription())%></textarea>
    	  <font color="red" style="font-size: 13px"><%=ServletUtility.getErrorMessage("description", request)%></font>
  </div>		
			
	
    <input type="submit" name="operation" class="btn btn-primary"
					value="<%=CategoryCtl.OP_SAVE%>"> or <input type="submit" name="operation" class="btn btn-primary"
					value="<%=CategoryCtl.OP_RESET%>">
					
    <br><br>
</form>
</div>
<br><br>
<%@ include file="Footer.jsp" %>