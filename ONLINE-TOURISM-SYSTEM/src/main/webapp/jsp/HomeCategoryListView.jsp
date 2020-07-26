<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.tourism.sys.bean.CategoryBean"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@ include file="Header.jsp"%>

<nav aria-label="breadcrumb" style="margin-top: 3px;">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Category</li>
	</ol>
</nav>
<div class="row row-cols-1 row-cols-md-2">
	<%
	
	CategoryBean bean = null;
	List list = ServletUtility.getList(request);
	Iterator<CategoryBean> iterator = list.iterator();
	while (iterator.hasNext()) {
	bean = iterator.next();

%>

	<div class="col mb-4">
		<div class="card">
			<a href="/ONLINE-TOURISM-SYSTEM/HomePackageListCtl?cId=<%=bean.getId()%>" ><img src="/ONLINE-TOURISM-SYSTEM/image/<%=bean.getImages()%>" class="card-img-top" alt="..."></a>
			<div class="card-body">
				<h5 class="card-title"><%=bean.getName() %></h5>
				<p class="card-text"><%=bean.getDescription()%></p>
			</div>
		</div>
	</div>
<%} %>


</div>
<%@ include file="Footer.jsp"%>