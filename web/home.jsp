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
                <div id="demo" class="collapse">
                <a href="#">Logout</a>
                </div>
            </div>
            <div class="inner-bg" style="padding-top: 10px">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>APPointmentZ</strong></h1>
                            <div class="description">
                            	<p>
	                            	Why wait in queues. Do something you like. We will notify you. <a href=""><strong>APPointmentZ.lk</strong></a>, Join with us
                            	</p>
                            </div>
                        </div>
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Add devices</h3>
                            		<p>Add Dbuttons or RPIs from here:</p>
                        		</div>
                        		<div class="form-top-right">
                        		</div>
                            </div>
                            <div class="form-bottom">
                                            <form role="form" action="./addDoctor" method="post" class="login-form">
			                        <button type="submit" class="btn"><strong>Add Doctor</strong></button>
			                    </form>
								<br>
                                            <form role="form" action="./addRoom" method="post" class="login-form">
			                        <button type="submit" class="btn"><strong>Add Room</strong></button>
			                    </form>
								<br>
			                    <form role="form" action="./addButton" method="post" class="login-form">
			                        <button type="submit" class="btn"><strong>Add Button</strong></button>
			                    </form>
								<br>
								<form role="form" action="./addRPI" method="post" class="login-form">
			                        <button type="submit" class="btn"><strong>Add RPI</strong></button>
			                    </form>
								<br>
								<form role="form" action="./map" method="post" class="login-form">
			                        <button type="submit" class="btn"><strong>Map</strong></button>
			                    </form>
								<br>
								<form role="form" action="./sessions" method="post" class="login-form">
			                        <button type="submit" class="btn"><strong>Sessions</strong></button>
			                    </form>
								<br>
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
