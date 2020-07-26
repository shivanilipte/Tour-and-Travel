<%@page import="in.co.online.tourism.sys.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.tourism.sys.bean.PackageBean"%>
<%@ include file="Header.jsp"%>
<nav aria-label="breadcrumb" style="margin-top: 3px;">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Package
			List</li>
	</ol>
</nav>

<%
	
	PackageBean bean = null;
	List list = ServletUtility.getList(request);
	Iterator<PackageBean> iterator = list.iterator();
	while (iterator.hasNext()) {
	bean = iterator.next();

%>

<div class="card mb-3" style="max-width: 840px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img width="200px" height="160px" src="/ONLINE-TOURISM-SYSTEM/image/<%=bean.getImage()%>" class="card-img" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title"><%=bean.getName()%></h5>
        <p class="card-text"><%=bean.getDescription() %></p>
        <p class="card-text">Destination :<small class="text-muted"><%=bean.getDestination()%></small>
        |Price :<small class="text-muted"><%=bean.getPrice()%></small>
        |Arrival Date :<small class="text-muted"><%=DataUtility.getDateString(bean.getArrivalDate())%></small>
        |Departure Date :<small class="text-muted"><%=DataUtility.getDateString(bean.getDepartureDate())%></small></p>
        <%if(userLoggedIn){%>
        <p class="card-text"><a href="/ONLINE-TOURISM-SYSTEM/ctl/BookingCtl?pId=<%=bean.getId()%>" class="btn btn-primary">Book</a></p>
      	<%}else{ %>
      	<p class="card-text"><a href="/ONLINE-TOURISM-SYSTEM/LoginCtl?pId=<%=bean.getId()%>" class="btn btn-primary">Book</a></p>
      	<%} %>
      </div>
    </div>
  </div>
</div>
<%} %>
<%@ include file="Footer.jsp"%>