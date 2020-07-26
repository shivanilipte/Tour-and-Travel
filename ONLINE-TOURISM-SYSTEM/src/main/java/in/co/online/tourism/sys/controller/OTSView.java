package in.co.online.tourism.sys.controller;

public interface OTSView {
	
	public String APP_CONTEXT = "/ONLINE-TOURISM-SYSTEM";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp"; 
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	
	public String CATEGORY_VIEW = PAGE_FOLDER + "/CategoryView.jsp"; 
	public String CATEGORY_LIST_VIEW = PAGE_FOLDER + "/CategoryListView.jsp";
	
	public String HOME_CATEGORY_VIEW = PAGE_FOLDER + "/CategoryView.jsp"; 
	public String HOME_CATEGORY_LIST_VIEW = PAGE_FOLDER + "/HomeCategoryListView.jsp";
	
	
	public String PACKAGE_VIEW = PAGE_FOLDER + "/PackageView.jsp"; 
	public String PACKAGE_LIST_VIEW = PAGE_FOLDER + "/PackageListView.jsp";
	
	public String BOOKING_VIEW = PAGE_FOLDER + "/BookingView.jsp"; 
	public String BOOKING_LIST_VIEW = PAGE_FOLDER + "/BookingListView.jsp";
	
	
	public String PAYMENT_VIEW = PAGE_FOLDER + "/PaymentView.jsp"; 
	
	public String SUCCESS_VIEW = PAGE_FOLDER + "/SuccessView.jsp"; 
		
	public String HOME_PACKAGE_LIST_VIEW = PAGE_FOLDER + "/HomePackageListView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String CATEGORY_CTL = APP_CONTEXT + "/ctl/CategoryCtl";
	public String CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/CategoryListCtl";
	
	
	public String BOOKING_CTL = APP_CONTEXT + "/ctl/BookingCtl";
	public String BOOKING_LIST_CTL = APP_CONTEXT + "/ctl/BookingListCtl";
	
	public String HOME_CATEGORY_CTL = APP_CONTEXT + "/ctl/HomeCategoryCtl";
	public String HOME_CATEGORY_LIST_CTL = APP_CONTEXT +"/HomeCategoryListCtl";
	
	public String PACKAGE_CTL = APP_CONTEXT + "/ctl/PackageCtl";
	public String PACKAGE_LIST_CTL = APP_CONTEXT + "/ctl/PackageListCtl";
	
	
	public String HOME_PACKAGE_LIST_CTL = APP_CONTEXT + "/HomePackageListCtl";
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
