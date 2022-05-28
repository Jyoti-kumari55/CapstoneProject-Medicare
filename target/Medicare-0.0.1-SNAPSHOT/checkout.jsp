<%@page import="com.example.entities.User"%>
<%
    User user = (User) session.getAttribute("current-user");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! Login first to access checkout page");
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout Page</title>
</head>
<body>
 <%@include file="component/common-css.jsp" %>

    </head>
    <body style="background-color:#E4F3E1;">
        <%@include  file="component/navbar.jsp" %>

        <div class="container">
            <div class="row mt-5">                
                <div class="col-md-6">
                    <!--card-->
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center mb-5">Your selected items</h3>
                            <div class="cart-body">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
          <!--form details-->
          <!--card-->
         <div class="card">              
          <div class="card-body">
          <h3 class="text-center mb-5">Your details for order</h3>
            <form action="#!">
            <div class="form-group">
              <label for="exampleInputEmail1">Email address</label>
       <input value="<%= user.getUserEmail()%>" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>                               </div>
             <div class="form-group">
      <label for="name">Your name</label>
        <input value="<%= user.getUserName()%>" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter name">
      </div>

<div class="form-group">
  <label for="name">Your contact</label>
    <input value="<%= user.getUserPhone() %>" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter contact number">
</div>

<div class="form-group">
   <label for="exampleFormControlTextarea1" >Your shipping address</label>
    <textarea value="<%= user.getUserAddress()%>" class="form-control" id="exampleFormControlTextarea1" placeholder="Enter your address" rows="3"></textarea>
  </div>
 <div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
  <label class="form-check-label" for="inlineCheckbox1">Cash on Delivery</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2">
  <label class="form-check-label" for="inlineCheckbox2">Wallet</label>
</div>
</form>    

                                <div class="container text-center">
                                    <button class="btn btn-success" onClick="payment()">Order Now</button>
                                    <button class="btn btn-outline-primary" onClick="indexPage()">Continue Shopping</button>
                                </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include  file="component/common-modals.jsp" %>
</body>
</html>