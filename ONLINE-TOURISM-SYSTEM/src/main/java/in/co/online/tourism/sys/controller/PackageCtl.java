package in.co.online.tourism.sys.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.online.tourism.sys.bean.BaseBean;
import in.co.online.tourism.sys.bean.PackageBean;
import in.co.online.tourism.sys.exception.ApplicationException;
import in.co.online.tourism.sys.exception.DuplicateRecordException;
import in.co.online.tourism.sys.model.CategoryModel;
import in.co.online.tourism.sys.model.PackageModel;
import in.co.online.tourism.sys.util.DataUtility;
import in.co.online.tourism.sys.util.DataValidator;
import in.co.online.tourism.sys.util.PropertyReader;
import in.co.online.tourism.sys.util.ServletUtility;

@WebServlet(name = "PackageCtl", urlPatterns = { "/ctl/PackageCtl" })
@MultipartConfig(maxFileSize = 169999999)
public class PackageCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(PackageCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("PackageCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} 
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("categoryId"))) {
			request.setAttribute("categoryId", PropertyReader.getValue("error.require", "Category Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("dest"))) {
			request.setAttribute("dest", PropertyReader.getValue("error.require", "Destination"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("country"))) {
			request.setAttribute("country", PropertyReader.getValue("error.require", "Country"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("distance"))) {
			request.setAttribute("distance", PropertyReader.getValue("error.require", "distance"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("arrivalDate"))) {
			request.setAttribute("arrivalDate", PropertyReader.getValue("error.require", "Arrival Date"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("depDate"))) {
			request.setAttribute("depDate", PropertyReader.getValue("error.require", "Departure Date"));
			pass = false;
		}
		

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		
		Part part = null;
		try {
			part = request.getPart("photo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

		if (DataValidator.isNull(fileName)) {
			request.setAttribute("photo", PropertyReader.getValue("error.require", "Image"));
			pass = false;
		}
		

		log.debug("PackageCtl validate method end");
		return pass;
	}
	
	
	

	@Override
	protected void preload(HttpServletRequest request) {
		CategoryModel model=new CategoryModel();
		try {
			List list= model.list();
			request.setAttribute("catList", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PackageCtl populateBean method start");
		PackageBean bean = new PackageBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCategoryId(DataUtility.getLong(request.getParameter("categoryId")));
		bean.setDestination(DataUtility.getString(request.getParameter("dest")));
		bean.setCountry(DataUtility.getString(request.getParameter("country")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setDistance(DataUtility.getString(request.getParameter("distance")));
		bean.setPrice(DataUtility.getString(request.getParameter("price")));
		bean.setArrivalDate(DataUtility.getDate(request.getParameter("arrivalDate")));
		bean.setDepartureDate(DataUtility.getDate(request.getParameter("depDate")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("PackageCtl populateBean method end");
		return bean;
	}

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
		log.debug("PackageCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		PackageModel model = new PackageModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			PackageBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("PackageCtl doGet method end");
	}

	/**
	 * Contains submit logic
	 */
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("PackageCtl doPost method start");
		
		
		
		String op = DataUtility.getString(request.getParameter("operation"));
		PackageModel model = new PackageModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {

			PackageBean bean = (PackageBean) populateBean(request);
			
			
			
			try {

				bean.setImage(ServletUtility.subImage(request, response, bean.getName()));
				
				if (id > 0) {

					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);

				} else {
					long pk = model.add(bean);
					// bean.setId(id);
					ServletUtility.setSuccessMessage("Data is successfully Saved", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(OTSView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			PackageBean bean = (PackageBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OTSView.PACKAGE_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTSView.PACKAGE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTSView.PACKAGE_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("PackageCtl doPost method end");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTSView.PACKAGE_VIEW;
	}
}