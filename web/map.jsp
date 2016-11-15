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
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>APPointmentZ</strong> Map</h1>
                            <div class="description">
                            	<p>
	                            	Why wait in queues. Do something you like. We will notify you. <a href=""><strong>APPointmentZ.lk</strong></a>, Join with us
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<p>Use this form to map Doctor with the Counter or Room</p>
                            		<p>Select the Doctor and related Counter/Room to map:</p>
                        		</div>
                        		
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="./addSession" method="POST" class="login-form">
			                    	<div class="form-group">
										<div class="dropdown">
											
                                                                                        <select class="selectpicker" name="room_id" style="width:150px; height:50px;">
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
                                                                                                            out.println("<option value=\""+room_id+"\">"+room_number+"</option>");
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
											<button class="btn" style="width:180px; background-color:gray;" disabled> Map Room & Doctor</button>
											<select class="selectpicker" name="doctor_id" style="width:150px; height:50px;">
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
                                                                                <div class="form-group">
											<div class="form-group">
												<center>
												<p style="color:white">Pick a date</p>
												<div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
													<input style="width:200px" class="form-control" name="date_picked" size="16" type="text" value="" readonly>
													<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
													<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
												<input type="hidden" id="dtp_input2" value="" />
												</center>
											</div>
											<div class="form-group">
												<center>
												<p style="color:white">Pick start time</p>
												<div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
													<input style="width:200px" class="form-control" name="start_time" size="16" type="text" value="" readonly>
													<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
													<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
												</div>
												<input type="hidden" id="dtp_input3" value="" />
												</center>
											</div>
											<!--<div class="form-group">
												<center>
												<p style="color:white">Pick End time</p>
												<div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
													<input style="width:200px" class="form-control" name="end_time" size="16" type="text" value="" readonly>
													<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
													<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
												</div>
												<input type="hidden" id="dtp_input3" value="" />
												</center>
											</div> -->
										</div>
										<button type="submit" class="btn">Map</button>										
                                                </div>
                        			</form>
                        		</div>
                        </div>
                        </div>
			                        
			                    
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
