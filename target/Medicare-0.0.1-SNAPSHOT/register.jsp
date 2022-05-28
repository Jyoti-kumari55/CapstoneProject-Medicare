<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
   <%@include file="component/common-css.jsp" %>
    </head>
    <body style="background-color:#B2FDF6;">

        <%@include  file="component/navbar.jsp" %>
        <div class="container-fluid">
            <div class="row mt-3">
                <div class="col-md-4 offset-md-4">                  
                    <div class="card">                        
                        <%@include  file="component/message.jsp" %>                        
                        <div class="card-body px-5">
                            <div class="container text-center">
                                <img src="images/add-people.jpg"  style="max-width: 100px;" class="img-fluid" alt="">
                            </div>


                            <h3 class="text-center my-3">Sign up here !!</h3>

                            <form action="RegistrationServlet" method="post">

                                <div class="form-group">
                                    <label for="name">User Name</label>
                                    <input name="user_name" type="text" class="form-control" id="name" placeholder="Enter name" aria-describedby="emailHelp">
                                </div>

                                <div class="form-group">
                                    <label for="email">User Email</label>
                                    <input name="user_email" type="email" class="form-control" id="email" placeholder="Enter email" aria-describedby="emailHelp">
                                </div>

                                <div class="form-group">
                                    <label for="password">User password</label>
                                    <input name="user_password" type="password" class="form-control" id="password" placeholder="Enter password" aria-describedby="emailHelp">
                                </div>

                                <div class="form-group">
                                    <label for="phone">User phone</label>
                                    <input name="user_phone" type="number" class="form-control" id="phone" placeholder="Enter Mobile number" aria-describedby="emailHelp">
                                </div>

                                  <div class="container text-center"> 
                                    <button class="btn btn-danger">Register</button>
                                    <button class="btn btn-warning">Reset</button>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</body>
</html>