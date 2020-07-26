package in.co.online.tourism.sys.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.tourism.sys.bean.BookingBean;
import in.co.online.tourism.sys.bean.UserBean;
import in.co.online.tourism.sys.exception.ApplicationException;
import in.co.online.tourism.sys.exception.DuplicateRecordException;
import in.co.online.tourism.sys.model.BookingModel;
import in.co.online.tourism.sys.model.PackageModel;
import in.co.online.tourism.sys.util.DataUtility;
import in.co.online.tourism.sys.util.ServletUtility;

@WebServlet(name = "BookingCtl", urlPatterns = { "/ctl/BookingCtl" })
public class BookingCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BookingCtl.class);
	/**
	 * Contains display logic
	 */
	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RoleCtl doGet method start");
	
		long pid=DataUtility.getLong(request.getParameter("pId"));
		if(pid>0) {
			request.getSession().setAttribute("pkId",pid);
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("RoleCtl doGet method end");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RoleCtl doPost method start");
		
		HttpSession session=request.getSession();
		
		String op = DataUtility.getString(request.getParameter("operation"));
		BookingModel model = new BookingModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_PAY.equalsIgnoreCase(op)) {
			try {
				UserBean uBean=(UserBean)session.getAttribute("user");
				long pid=(long)session.getAttribute("pkId");
				BookingBean bean=new BookingBean();
				bean.setPackageId(pid);
				bean.setFirstName(uBean.getFirstName());
				bean.setLastName(uBean.getLastName());
				bean.setEmailId(uBean.getEmailId());
				bean.setContactNo(uBean.getMobileNo());
				bean.setUserId(uBean.getId());
				bean.setBookingDate(DataUtility.getCurrentTimestamp());
				bean.setTotalPrice(new PackageModel().findByPK(pid).getPrice());
				long bid=model.add(bean);
				session.setAttribute("boBean",bean);
				ServletUtility.forward(OTSView.SUCCESS_VIEW, request, response);
				return;
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		log.debug("RoleCtl doPost method end");
	}

	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.PAYMENT_VIEW;
	}
}