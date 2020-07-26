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
import in.co.online.tourism.sys.bean.BookingBean;
import in.co.online.tourism.sys.bean.UserBean;
import in.co.online.tourism.sys.exception.ApplicationException;
import in.co.online.tourism.sys.model.BookingModel;
import in.co.online.tourism.sys.util.DataUtility;
import in.co.online.tourism.sys.util.PropertyReader;
import in.co.online.tourism.sys.util.ServletUtility;



/**
 * Servlet implementation class BookingListCtl
 */
@WebServlet(name="BookingListCtl",urlPatterns= {"/ctl/BookingListCtl"})
public class BookingListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	 private static Logger log = Logger.getLogger(BookingListCtl.class);
		
		@Override
		protected BaseBean populateBean(HttpServletRequest request) {
			log.debug("BookingListCtl populateBean method start");
			BookingBean bean = new BookingBean();
			bean.setFirstName(DataUtility.getString(request.getParameter("name")));
			bean.setEmailId(DataUtility.getString(request.getParameter("email")));
			log.debug("BookingListCtl populateBean method end");
			return bean;
		}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookingListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		BookingModel model = new BookingModel();
		BookingBean bean = (BookingBean) populateBean(request);
		try {
			
			  UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			  if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 
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
		log.debug("BookingListCtl doGet method end");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookingListCtl doPost method start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BookingBean bean = (BookingBean) populateBean(request);

		BookingModel model = new BookingModel();
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
			ServletUtility.redirect(OTSView.BOOKING_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				BookingBean deletebean = new BookingBean();
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
			ServletUtility.redirect(OTSView.BOOKING_LIST_CTL, request, response);
			return;
		}
		try {
			
			  UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			  if(uBean.getRoleId()==2) { bean.setUserId(uBean.getId()); }
			 
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
		log.debug("BookingListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.BOOKING_LIST_VIEW;
	}

}
