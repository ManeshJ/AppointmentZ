<%@ page session="true" %>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.intelligentz.appointmentz.database.connectToDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>APPointmentZ</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
            <div style="position:absolute; right:30px; top:20px;">
                <button class="btn btn-primary dropdown-toggle" data-target="#demo" data-toggle="collapse" style='color:white'><% out.println(session.getAttribute("hospital_name")); %>
                        </button>
                        <button class="btn btn-primary" style='color:white' onClick="window.location.assign('home.jsp')">Home
                </button>
                <div id="demo" class="collapse">
                <form action="./logout" method="post">
                        <input class="btn btn-primary" style="color:red" type="submit" value="Logout" />
                    </form>
                </div>
            </div>
            <div class="inner-bg" style="padding-top: 10px">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>APPointmentZ</strong> Add Dbutton</h1>
                            <div class="description">
                            	<p>
	                            	Why wait in queues. Do something you like. We will notify you. <a href=""><strong>APPointmentZ.lk</strong></a>, Join with us
                            	</p>
                            </div>
                        </div>
                    
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Use this form to add D-buttons</h3>
                            		<p>Enter the information of D-button to subscribe to the service:</p>
                        		</div>
                        		<div class="form-top-right">
                        			
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="./addBut" method="post" class="login-form">
						<div class="form-group">
			                    		<label class="sr-only" for="form-username"></label>
										<p>Doctor Name:</p>
			                        	<!--<input type="text" name="form-username" placeholder="Button user id... or Doctor Name" class="form-username form-control" id="form-username">-->
                                                    <div class="dropdown">
                                                        <select class="selectpicker" name="doctor_id" style="width:400px; height:50px;">
                                                            <%
                                                                try 
                                                                {
                                                                Class.forName("com.mysql.jdbc.Driver");
                                                                connectToDB con = new connectToDB();
                                                                    if(con.connect()){
                                                                        Connection connection = con.getConnection();
                                                                        Statement stmt = connection.createStatement( );
                                                                        String SQL = "select name,doctor_id from appointmentz.doctor";
                                                                        ResultSet rs = stmt.executeQuery( SQL );

                                                                        while(rs.next( )){
                                                                            String name = rs.getString("name");
                                                                            String doctor_id = rs.getString("doctor_id");
                                                                            out.println("<option value=\""+doctor_id+"\">"+name+"</option>");
                                                                        }
                                                                    }
                                                                    else{
                                                                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                                                                        response.setHeader("Location", "error.jsp?error=MYSQL connection failed!"); 
                                                                    }
                                                                } catch (SQLException e) {
                                                                //throw new IllegalStateException
                                                                out.println("Cannot connect the database!");

                                                                }
                                                                    %>

                                                        </select>
                                                    </div>
                                                </div>
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username"></label>
										<p>Serial Number:</p>
			                        	<input type="text" name="serial" placeholder="Serial Number..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password"></label>
										<p>Auth code:</p>
			                        	<input type="text" name="auth" placeholder="Auth code..." class="form-password form-control" id="form-password">
			                        </div>
									
			                        <button type="submit" class="btn">Add D-button</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	<h3>Get notified before your appointment</h3>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>
