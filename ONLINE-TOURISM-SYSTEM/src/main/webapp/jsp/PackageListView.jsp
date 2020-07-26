
<%@page import="in.co.online.tourism.sys.util.DataUtility"%>
<%@page import="in.co.online.tourism.sys.bean.PackageBean"%>
<%@page import="in.co.online.tourism.sys.controller.PackageListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@ include file="Header.jsp"%>
<nav aria-label="breadcrumb" style="margin-top: 3px;">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Package 
			List</li>
	</ol>
</nav>
<h4>Package List</h4>
<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
<hr>
<form action="<%=OTSView.PACKAGE_LIST_CTL%>" method="post">
	<div style="margin-left: 5px" class="form-row">
		<div class="col-md-4 mb-3">
			<input type="text" class="form-control" name="catName"
				placeholder="Search By Category Name"
				value="<%=ServletUtility.getParameter("catName", request)%>">
		</div>
		<div class="col-md-4 mb-3">
			<input type="text" class="form-control" name="name"
				placeholder="Search By  Name"
				value="<%=ServletUtility.getParameter("name", request)%>">
		</div>
		<div class="col-md-4 mb-3">
			<input type="submit" name="operation" class="btn btn-primary"
				value="<%=PackageListCtl.OP_SEARCH%>">or <input type="submit"
				name="operation" class="btn btn-primary"
				value="<%=PackageListCtl.OP_RESET%>">
		</div>

	</div>

	<table class="table table-striped">
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<%
					if(userBean.getRoleId()==1){
				%>
				<th scope="col"><input type="checkbox" id="selectall">Select
					All</th>
					<%
						}
					%>
				<th scope="col">Image</th>
				<th scope="col">Category Name</th>
				<th scope="col">Name</th>
				<th scope="col">Destination</th>
				<th scope="col">Country</th>
				<th scope="col">State</th>
				<th scope="col">City</th>
				<th scope="col">Distance</th>
				<th scope="col">Price</th>
				<th scope="col">Arrival Date</th>
				<th scope="col">Departure Date</th>
				<th scope="col">Description</th>
				<%
					if(userBean.getRoleId()==1){
				%>
				<th scope="col">Action</th>
				<%
					}
				%>
			</tr>
		</thead>

		<tbody>
			<%
				int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					int size=ServletUtility.getSize(request);
					PackageBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<PackageBean> iterator = list.iterator();
					while (iterator.hasNext()) {
						bean = iterator.next();
			%>
			<tr>
				<th scope="row"><%=index++%></th>
				<%
					if(userBean.getRoleId()==1){
				%>
				<td><input type="checkbox" class="case" name="ids"
					value="<%=bean.getId()%>"></td>
					<%
						}
					%>
					<td><img height="150px" width="150px" src="/ONLINE-TOURISM-SYSTEM/image/<%=bean.getImage()%>"></td>
					<td><%=bean.getCategoryName()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDestination()%></td>
				<td><%=bean.getCountry()%></td>
				<td><%=bean.getState()%></td>
				<td><%=bean.getCity()%></td>
				<td><%=bean.getDistance()%></td>
				<td><%=bean.getPrice()%></td>
				<td><%=DataUtility.getDateString(bean.getArrivalDate())%></td>
				<td><%=DataUtility.getDateString(bean.getDepartureDate())%></td>
				<td><%=bean.getDescription()%></td>
				<%
					if(userBean.getRoleId()==1){
				%>
				<td><a class="btn btn-primary"
					href="<%=OTSView.PACKAGE_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<table width="99%">
		<tr>
			<td><input type="submit" name="operation"
				class="btn btn-primary" value="<%=PackageListCtl.OP_PREVIOUS%>"
				<%=(pageNo == 1) ? "disabled" : ""%>></td>

		<%
			if(userBean.getRoleId()==1){
		%>
			<td><input type="submit" name="operation"
				class="btn btn-primary" value="<%=PackageListCtl.OP_NEW%>"></td>
			<td><input type="submit" name="operation"
				value="<%=PackageListCtl.OP_DELETE%>" class="btn btn-primary"
				<%=(list.size() == 0) ? "disabled" : ""%>></td>
			<%
				}
			%>
			<td align="right"><input type="submit" name="operation"
				class="btn btn-primary" value="<%=PackageListCtl.OP_NEXT%>"
				<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></td>

		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<hr>
<%@ include file="Footer.jsp"%>