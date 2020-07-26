
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.tourism.sys.bean.CategoryBean"%>
<%@page import="in.co.online.tourism.sys.controller.CategoryListCtl"%>
<%@page import="in.co.online.tourism.sys.util.ServletUtility"%>
<%@ include file="Header.jsp"%>
<nav aria-label="breadcrumb" style="margin-top: 3px;">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Category 
			List</li>
	</ol>
</nav>
<h4>Category List</h4>
<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
<hr>
<form action="<%=OTSView.CATEGORY_LIST_CTL%>" method="post">
	<div style="margin-left: 5px" class="form-row">
		<div class="col-md-4 mb-3">
			<input type="text" class="form-control" name="name"
				placeholder="Search By Name"
				value="<%=ServletUtility.getParameter("name", request)%>">
		</div>
		
		<div class="col-md-4 mb-3">
			<input type="submit" name="operation" class="btn btn-primary"
				value="<%=CategoryListCtl.OP_SEARCH%>">or <input type="submit"
				name="operation" class="btn btn-primary"
				value="<%=CategoryListCtl.OP_RESET%>">
		</div>

	</div>

	<table class="table">
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
				<th scope="col">Name</th>
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
					CategoryBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<CategoryBean> iterator = list.iterator();
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
					<td><img height="150px" src="/ONLINE-TOURISM-SYSTEM/image/<%=bean.getImages()%>"></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getDescription()%></td>
				<%
					if(userBean.getRoleId()==1){
				%>
				<td><a class="btn btn-primary"
					href="<%=OTSView.CATEGORY_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
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
				class="btn btn-primary" value="<%=CategoryListCtl.OP_PREVIOUS%>"
				<%=(pageNo == 1) ? "disabled" : ""%>></td>

		<%
			if(userBean.getRoleId()==1){
		%>
			<td><input type="submit" name="operation"
				class="btn btn-primary" value="<%=CategoryListCtl.OP_NEW%>"></td>
			<td><input type="submit" name="operation"
				value="<%=CategoryListCtl.OP_DELETE%>" class="btn btn-primary"
				<%=(list.size() == 0) ? "disabled" : ""%>></td>
			<%
				}
			%>
			<td align="right"><input type="submit" name="operation"
				class="btn btn-primary" value="<%=CategoryListCtl.OP_NEXT%>"
				<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></td>

		</tr>
	</table>
	<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
		type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<hr>
<%@ include file="Footer.jsp"%>