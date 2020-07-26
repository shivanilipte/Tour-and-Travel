package in.co.online.tourism.sys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.tourism.sys.bean.BaseBean;
import in.co.online.tourism.sys.bean.PackageBean;
import in.co.online.tourism.sys.exception.ApplicationException;
import in.co.online.tourism.sys.model.PackageModel;
import in.co.online.tourism.sys.util.DataUtility;
import in.co.online.tourism.sys.util.PropertyReader;
import in.co.online.tourism.sys.util.ServletUtility;



/**
 * Servlet implementation class PackageListCtl
 */
@WebServlet(name="PackageListCtl",urlPatterns= {"/ctl/PackageListCtl"})
public class PackageListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	 private static Logger log = Logger.getLogger(PackageListCtl.class);
		
		@Override
		protected BaseBean populateBean(HttpServletRequest request) {
			log.debug("PackageListCtl populateBean method start");
			PackageBean bean = new PackageBean();
			bean.setName(DataUtility.getString(request.getParameter("name")));
			bean.setCategoryName(DataUtility.getString(request.getParameter("catName")));
			log.debug("PackageListCtl populateBean method end");
			return bean;
		}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PackageListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		PackageModel model = new PackageModel();
		PackageBean bean = (PackageBean) populateBean(request);
		try {
			/*
			 * UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			 * if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 */
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setSize(model.search(bean).size(), request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("PackageListCtl doGet method end");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PackageListCtl doPost method start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		PackageBean bean = (PackageBean) populateBean(request);

		PackageModel model = new PackageModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {
			if (OP_SEARCH.equalsIgnoreCase(op)) {
				pageNo = 1;
			} else if (OP_NEXT.equalsIgnoreCase(op)) {
				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTSView.PACKAGE_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				PackageBean deletebean = new PackageBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTSView.PACKAGE_LIST_CTL, request, response);
			return;
		}
		try {
			/*
			 * UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			 * if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 */
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setSize(model.search(bean).size(), request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("PackageListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.PACKAGE_LIST_VIEW;
	}

}
