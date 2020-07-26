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
import in.co.online.tourism.sys.bean.UserBean;
import in.co.online.tourism.sys.exception.ApplicationException;
import in.co.online.tourism.sys.model.PackageModel;
import in.co.online.tourism.sys.util.DataUtility;
import in.co.online.tourism.sys.util.PropertyReader;
import in.co.online.tourism.sys.util.ServletUtility;



/**
 * Servlet implementation class PackageListCtl
 */
@WebServlet(name="HomePackageListCtl",urlPatterns= {"/HomePackageListCtl"})
public class HomePackageListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	 private static Logger log = Logger.getLogger(HomePackageListCtl.class);
		
		@Override
		protected BaseBean populateBean(HttpServletRequest request) {
			log.debug("HomePackageListCtl populateBean method start");
			PackageBean bean = new PackageBean();
			bean.setName(DataUtility.getString(request.getParameter("name")));
			log.debug("HomePackageListCtl populateBean method end");
			return bean;
		}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("HomePackageListCtl doGet method start");
		List list = null;
		
		PackageModel model = new PackageModel();
		PackageBean bean = (PackageBean) populateBean(request);
		long cId=DataUtility.getLong(request.getParameter("cId"));
		try {
			/*
			 * UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			 * if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 */
			
			if(cId>0) {
				bean.setCategoryId(cId);
			}
			
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
		log.debug("HomePackageListCtl doGet method end");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("HomePackageListCtl doPost method start");
		List list = null;
		
		PackageBean bean = (PackageBean) populateBean(request);

		PackageModel model = new PackageModel();
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
		log.debug("HomePackageListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.HOME_PACKAGE_LIST_VIEW;
	}

}
