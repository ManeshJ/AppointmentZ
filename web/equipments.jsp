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
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>APPointmentZ</strong></h1>
                            <div class="description">
                            	<!--<p>
	                            	Why wait in queues. Do something you like. We will notify you. <a href=""><strong>APPointmentZ.lk</strong></a>, Join with us
                            	</p>-->
                                <p>Info about equipment</p>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="color:white">
					<center>
                                            <p><strong>Rooms</strong></p>
                        <table class="table table-bordered table-inverse">
								<tr>
                                                                        <th>Room Number</th>
									<th>Room Id</th>
									<th></th>
								</tr>
                                                                <%
                                                                try 
                                                                {
                                                                Class.forName("com.mysql.jdbc.Driver");
                                                                connectToDB con = new connectToDB();
                                                                    if(con.connect()){
                                                                        Connection connection = con.getConnection();
                                                                        Statement stmt = connection.createStatement( );
                                                                        String SQL = "select * from db_bro.room";
                                                                        ResultSet rs = stmt.executeQuery( SQL );

                                                                        while(rs.next( )){
                                                                            String room_id = rs.getString("room_id");
                                                                            String room_number = rs.getString("room_number");
                                                                            out.println("<tr>");
                                                                            out.println("<form action='./deleteRoom' method='post'>");
                                                                            out.println("<td>"+room_number+"</td>");
                                                                            out.println("<input type='hidden' name='room_number' value='"+room_number+"'>");
                                                                            out.println("<td>"+room_id+"</td>");
                                                                            out.println("<input type='hidden' name='room_id' value='"+room_id+"'>");
                                                                            out.println("<td><button type=\"submit\" onClick=\"return confirm('Do you wish to delete the Room. Ref: RoomId = "+room_id+", Room number = "+room_number+" ');\" style='color:red'>delete</button></td>");
                                                                            out.println("</form>");
                                                                            out.println("</tr>");
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
								
							
						</table>
					</center>
                    </div>
                                                                <div class="row" style="color:white">
					<center>
                        <p><strong>Doctors</strong></p>
                        <table class="table table-bordered table-inverse">
								<tr>
									<th>Doctor</th>
									<th>Doctor Id</th>
									<th></th>
								</tr>
                                                                <%
                                                                try 
                                                                {
                                                                Class.forName("com.mysql.jdbc.Driver");
                                                                connectToDB con = new connectToDB();
                                                                    if(con.connect()){
                                                                        Connection connection = con.getConnection();
                                                                        Statement stmt = connection.createStatement( );
                                                                        String SQL = "select * from db_bro.doctor";
                                                                        ResultSet rs = stmt.executeQuery( SQL );

                                                                        while(rs.next( )){
                                                                            String doctor_id = rs.getString("doctor_id");
                                                                            String name = rs.getString("name");
                                                                            out.println("<tr>");
                                                                            out.println("<form action='./deleteDoctor' method='post'>");
                                                                            out.println("<td>"+name+"</td>");
                                                                            out.println("<input type='hidden' name='name' value='"+name+"'>");
                                                                            out.println("<td>"+doctor_id+"</td>");
                                                                            out.println("<input type='hidden' name='doctor_id' value='"+doctor_id+"'>");
                                                                            out.println("<td><button type=\"submit\" onClick=\"return confirm('Do you wish to delete the Doctor. Ref: DoctorId = "+doctor_id+", Doctor Name = "+name+" ');\" style='color:red'>delete</button></td>");
                                                                            out.println("</form>");
                                                                            out.println("</tr>");
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
								
							
						</table>
					</center>
                    </div>
                                                                <div class="row" style="color:white">
					<center>
                                <p><strong>RPI</strong></p>
                        <table class="table table-bordered table-inverse">
								<tr>
									<th>Auth</th>
									<th>Serial</th>
									<th>Room Number</th>
									<th>Room Id</th>
									<th></th>
								</tr>
                                                                <%
                                                                try 
                                                                {
                                                                Class.forName("com.mysql.jdbc.Driver");
                                                                connectToDB con = new connectToDB();
                                                                    if(con.connect()){
                                                                        Connection connection = con.getConnection();
                                                                        Statement stmt = connection.createStatement( );
                                                                        String SQL = "select * from db_bro.rpi natural join db_bro.room";
                                                                        ResultSet rs = stmt.executeQuery( SQL );

                                                                        while(rs.next( )){
                                                                            String auth = rs.getString("auth");
                                                                            String serial = rs.getString("serial");
                                                                            String room_id = rs.getString("room_id");
                                                                            String room_number = rs.getString("room_number");
                                                                            out.println("<tr>");
                                                                            out.println("<form action='./deleteRPI' method='post'>");
                                                                            out.println("<td>"+auth+"</td>");
                                                                            out.println("<input type='hidden' name='auth' value='"+auth+"'>");
                                                                            out.println("<td>"+serial+"</td>");
                                                                            out.println("<input type='hidden' name='serial' value='"+serial+"'>");
                                                                            out.println("<td>"+room_number+"</td>");
                                                                            out.println("<input type='hidden' name='room_number' value='"+room_number+"'>");
                                                                            out.println("<td>"+room_id+"</td>");
                                                                            out.println("<input type='hidden' name='room_id' value='"+room_id+"'>");
                                                                            out.println("<td><button type=\"submit\" onClick=\"return confirm('Do you wish to delete the RPI. Ref: Serial = "+serial+", rel: Room: "+room_number+" ');\" style='color:red'>delete</button></td>");
                                                                            out.println("</form>");
                                                                            out.println("</tr>");
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
								
							
						</table>
					</center>
                    </div>
                                                                <div class="row" style="color:white">
					<center>
                                        <p><strong>Buttons</strong></p>
                        <table class="table table-bordered table-inverse">
								<tr>
									<th>Auth</th>
									<th>Serial</th>
									<th>Doctor</th>
									<th>Doctor Id</th>
									<th></th>
								</tr>
                                                                <%
                                                                try 
                                                                {
                                                                Class.forName("com.mysql.jdbc.Driver");
                                                                connectToDB con = new connectToDB();
                                                                    if(con.connect()){
                                                                        Connection connection = con.getConnection();
                                                                        Statement stmt = connection.createStatement( );
                                                                        String SQL = "select * from db_bro.button natural join db_bro.doctor";
                                                                        ResultSet rs = stmt.executeQuery( SQL );

                                                                        while(rs.next( )){
                                                                            String auth = rs.getString("auth");
                                                                            String serial = rs.getString("serial");
                                                                            String doctor_id = rs.getString("doctor_id");
                                                                            String name = rs.getString("name");
                                                                            out.println("<tr>");
                                                                            out.println("<form action='./deleteButton' method='post'>");
                                                                            out.println("<td>"+auth+"</td>");
                                                                            out.println("<input type='hidden' name='auth' value='"+auth+"'>");
                                                                            out.println("<td>"+serial+"</td>");
                                                                            out.println("<input type='hidden' name='serial' value='"+serial+"'>");
                                                                            out.println("<td>"+name+"</td>");
                                                                            out.println("<input type='hidden' name='name' value='"+name+"'>");
                                                                            out.println("<td>"+doctor_id+"</td>");
                                                                            out.println("<input type='hidden' name='doctor_id' value='"+doctor_id+"'>");
                                                                            out.println("<td><button type=\"submit\" onClick=\"return confirm('Do you wish to delete the Button. Ref: Serial = "+serial+" Rel: Doctor Name = "+name+"');\" style='color:red'>delete</button></td>");
                                                                            out.println("</form>");
                                                                            out.println("</tr>");
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
								
							
						</table>
					</center>
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

