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
@WebServlet(name="HomeCategoryListCtl",urlPatterns= {"/HomeCategoryListCtl"})
public class HomeCategoryListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	 private static Logger log = Logger.getLogger(HomeCategoryListCtl.class);
		
		@Override
		protected BaseBean populateBean(HttpServletRequest request) {
			log.debug("HomeCategoryListCtl populateBean method start");
			CategoryBean bean = new CategoryBean();
			bean.setName(DataUtility.getString(request.getParameter("name")));
			log.debug("HomeCategoryListCtl populateBean method end");
			return bean;
		}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("HomeCategoryListCtl doGet method start");
		List list = null;
		
		CategoryModel model = new CategoryModel();
		CategoryBean bean = (CategoryBean) populateBean(request);
		try {
			/*
			 * UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			 * if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 */
			list = model.search(bean);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setSize(model.search(bean).size(), request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("HomeCategoryListCtl doGet method end");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("HomeCategoryListCtl doPost method start");
		List list = null;
		

		CategoryBean bean = (CategoryBean) populateBean(request);

		CategoryModel model = new CategoryModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		
		try {
			/*
			 * UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			 * if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 */
			list = model.search(bean);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			
			ServletUtility.setSize(model.search(bean).size(), request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("HomeCategoryListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.HOME_CATEGORY_LIST_VIEW;
	}

}
