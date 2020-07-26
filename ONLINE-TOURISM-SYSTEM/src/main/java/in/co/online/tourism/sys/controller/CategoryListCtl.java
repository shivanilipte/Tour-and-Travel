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
import in.co.online.tourism.sys.bean.CategoryBean;
import in.co.online.tourism.sys.bean.UserBean;
import in.co.online.tourism.sys.exception.ApplicationException;
import in.co.online.tourism.sys.model.CategoryModel;
import in.co.online.tourism.sys.util.DataUtility;
import in.co.online.tourism.sys.util.PropertyReader;
import in.co.online.tourism.sys.util.ServletUtility;



/**
 * Servlet implementation class CategoryListCtl
 */
@WebServlet(name="CategoryListCtl",urlPatterns= {"/ctl/CategoryListCtl"})
public class CategoryListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	 private static Logger log = Logger.getLogger(CategoryListCtl.class);
		
		@Override
		protected BaseBean populateBean(HttpServletRequest request) {
			log.debug("CategoryListCtl populateBean method start");
			CategoryBean bean = new CategoryBean();
			bean.setName(DataUtility.getString(request.getParameter("name")));
			log.debug("CategoryListCtl populateBean method end");
			return bean;
		}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CategoryListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		CategoryModel model = new CategoryModel();
		CategoryBean bean = (CategoryBean) populateBean(request);
		try {
			
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
		log.debug("CategoryListCtl doGet method end");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CategoryListCtl doPost method start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		CategoryBean bean = (CategoryBean) populateBean(request);

		CategoryModel model = new CategoryModel();
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
			ServletUtility.redirect(OTSView.CATEGORY_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				CategoryBean deletebean = new CategoryBean();
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
			ServletUtility.redirect(OTSView.CATEGORY_LIST_CTL, request, response);
			return;
		}
		try {
			
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
		log.debug("CategoryListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.CATEGORY_LIST_VIEW;
	}

}
