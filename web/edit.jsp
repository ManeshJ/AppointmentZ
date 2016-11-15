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
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/bootstrap-theme.css" rel="stylesheet">
		<link href="css/bootstrap-theme.min.css" rel="stylesheet">
		<link href="./css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
		<link href="css/bootstrap-toggle.css" rel="stylesheet">

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
                        <button class="btn btn-primary" style='color:white' onClick="window.location.assign('sessions.jsp')">Sessions
                </button>
                <div id="demo" class="collapse">
                <form action="./logout" method="post">
                        <input class="btn btn-primary" style="color:red" type="submit" value="Logout" />
                    </form>
                </div>
            </div>
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>APPointmentZ</strong> Update Session</h1>
                            <div class="description">
                            	<!--<p>
	                            	Why wait in queues. Do something you like. We will notify you. <a href=""><strong>APPointmentZ.lk</strong></a>, Join with us
                            	</p>-->
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left" style="width:500px">
                        			<p>This session id should be used to follow doctors in through ISYD platform.</p>
                            		<p>In case of any change in counter/room change it from edit section.</p>
                        		</div>
                        		
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="./updateSession" method="post" class="login-form">
			                    	<div class="form-group">
										<div class="dropdown">
											<select class="selectpicker" id='room_id' name="room_id" style="width:145px; height:50px;" readonly>
											  <%
                                                                                                try 
                                                                                                {
                                                                                                Class.forName("com.mysql.jdbc.Driver");
                                                                                                connectToDB con = new connectToDB();
                                                                                                    if(con.connect()){
                                                                                                        Connection connection = con.getConnection();
                                                                                                        Statement stmt = connection.createStatement( );
                                                                                                        String SQL = "select room_number,room_id from appointmentz.room";
                                                                                                        ResultSet rs = stmt.executeQuery( SQL );

                                                                                                        while(rs.next( )){
                                                                                                            String room_number = rs.getString("room_number");
                                                                                                            String room_id = rs.getString("room_id");
                                                                                                            if(room_id.equals(request.getParameter("room_id"))){
                                                                                                                out.println("<option selected=\"selected\" value=\""+room_id+"\">"+room_number+"</option>");
                                                                                                            }
                                                                                                            else{
                                                                                                                out.println("<option value=\""+room_id+"\">"+room_number+"</option>");
                                                                                                            }
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
											<button type="submit" class="btn" style="width:200px; background-color:gray;" disabled> Update Room of Doctor</button>
											<select class="selectpicker" style="width:145px; height:50px;">
											  <option><%=request.getParameter("name")%></option>
											</select>
										</div>
			                    		<div class="form-group">
											<div class="form-group">
												<center>
												<p style="color:white">Date</p>
												<div>
													<input style="width:200px" class="form-control" size="16" type="text" value="<%=request.getParameter("date")%>" readonly>
												</div>
												<input type="hidden" id="dtp_input2" value="" />
												</center>
											</div>
											<div class="form-group">
												<center>
												<p style="color:white">Start time</p>
												<div>
													<input style="width:200px" class="form-control" size="16" name="start_time" type="text" value="<%=request.getParameter("start_time")%>" readonly>
												</div>
												<input type="hidden" name="session_id" value="<%=request.getParameter("session_id") %>" />
												</center>
											</div>
							
											<div>
											<center>
											<p style="color:white">SessionId</p>
                                                                                        <button class="btn" style="width:200px; background-color:green;" disabled> <%=request.getParameter("session_id") %></button>
											</center>
											</div>
								
										</div>
										<center>
                                                                                    
                                                                                    <button class="btn" type="submit" style="width:100px" onClick="return confirm('Do you wish to update the room from: '.concat('<%=request.getParameter("room_number")%>',' -> to: ',''+document.getElementById('room_id').options[document.getElementById('room_id').selectedIndex].text));">Change</button>
										</center>
			                        </div>
                        			</form>
                        		</div>
                            </div>
			                        </div>
			                        
			                    
		                    </div>
                        </div>
                    </div>
                    <div class="">
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
		<script type="text/javascript" src="./js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
		<script src="js/bootstrap-toggle.js"></script>
		<script type="text/javascript" src="./js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
		<script type="text/javascript">
			$('.form_datetime').datetimepicker({
				//language:  'fr',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
				showMeridian: 1
			});
			$('.form_date').datetimepicker({
				language:  'fr',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
			});
			$('.form_time').datetimepicker({
				language:  'fr',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 1,
				minView: 0,
				maxView: 1,
				forceParse: 0
			});
		</script>
    </body>

</html>
