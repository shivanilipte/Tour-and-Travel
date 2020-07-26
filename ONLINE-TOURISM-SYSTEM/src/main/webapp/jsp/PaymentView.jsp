
<%@page import="in.co.online.tourism.sys.controller.BookingCtl"%>
<%@ include file="Header.jsp" %>
<nav aria-label="breadcrumb" style="margin-top: 7px;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="<%=OTSView.WELCOME_CTL%>">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Payment</li>
  </ol>
</nav>
<div class="container" style="border: ridge;margin-top: 20px;margin-left: 50px">
<div style="margin-top: 18px">
  <h2>Payment</h2>
 </div>
 

<form style="width: 60%;margin-top: 25px" action="<%=OTSView.BOOKING_CTL%>" method="post">

				
  
  <div class="form-group">
    <label for="inputAddress">Card No</label>
    <input type="text" class="form-control" name="oldPassword"  placeholder="Enter Card No..." >
  </div>
  
  <div class="form-group">
    <label for="inputAddress">Card Holder Name</label>
    <input type="text" class="form-control" name="oldPassword"  placeholder="Enter Card Holder Name..." >
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Month:</label>
    
      <input type="password" class="form-control" name="newPassword"  placeholder="Enter Month..."  >
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Year:</label>
       <input type="password" class="form-control" name="confirmPassword"  placeholder="Enter Year..." >
    </div>
   
  </div>
   <div class="form-group">
    <label for="inputAddress">CVV</label>
    <input type="text" class="form-control" name="oldPassword"  placeholder="Enter CVV..." >
  </div>

    <input type="submit" name="operation" class="btn btn-primary"
					value="<%=BookingCtl.OP_PAY%>"> 
					
    <br><br>
</form>
</div>
<br><br>
<%@ include file="Footer.jsp" %>