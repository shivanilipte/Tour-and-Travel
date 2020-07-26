<%@page import="in.co.online.tourism.sys.model.PackageModel"%>
<%@page import="in.co.online.tourism.sys.bean.BookingBean"%>
<%@page import="in.co.online.tourism.sys.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.tourism.sys.bean.PackageBean"%>
<%@ include file="Header.jsp"%>
<nav aria-label="breadcrumb" style="margin-top: 3px;">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Success
			</li>
	</ol>
</nav>


	<% BookingBean bBean=(BookingBean)session.getAttribute("boBean"); 
		
		PackageModel pModel=new PackageModel();
		PackageBean pBean=pModel.findByPK(bBean.getPackageId());
		
		
%>
<div class="card mb-3" style="max-width: 840px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img width="200px" height="160px" src="/ONLINE-TOURISM-SYSTEM/image/<%=pBean.getImage()%>" class="card-img" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title"><%=bBean.getPackageName()%></h5>
        <h6 style="color: green" class="card-title">Payment Successfully Done!!!</h6>
        <p class="card-text">Name : <%=bBean.getFirstName()+" "+bBean.getLastName()%></p>
        <p class="card-text">Email Id:<small class="text-muted"><%=bBean.getEmailId()%></small>
        |Total Amount :<small class="text-muted"><%=bBean.getTotalPrice()%></small>
        |Contact No :<small class="text-muted"><%=bBean.getContactNo()%></small>
        |Booking Date :<small class="text-muted"><%=DataUtility.getDateString(bBean.getBookingDate())%></small></p>
        
       
      </div>
    </div>
  </div>
</div>

<%@ include file="Footer.jsp"%>