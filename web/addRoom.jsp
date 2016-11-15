<%@ page session="true" %>
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
                            <h1><strong>APPointmentZ</strong> Add Rooms</h1>
                            <div class="description">
                            	<!--<p>
	                            	Why wait in queues. Do something you like. We will notify you. <a href=""><strong>APPointmentZ.lk</strong></a>, Join with us
                            	</p>-->
                            </div>
                        </div>
                    
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Use this form to add Rooms</h3>
                            		<p>Enter the information of Room to the system:</p>
                        		</div>
                        		<div class="form-top-right">
                        			
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="./addR" method="post" class="login-form">
									<div class="form-group">
			                    		<label class="sr-only" for="form-username"></label>
										<p>Room Number:</p>
			                        	<input type="text" name="room_number" placeholder="Room Number" class="form-username form-control" id="">
			                        </div>
			                    	<div class="form-group">
			                    		<input type="hidden" name="hospital_id" id="" value="<%=session.getAttribute("hospital_id")%>">
			                        </div>
									
			                        <button type="submit" class="btn">Add Room</button>
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
