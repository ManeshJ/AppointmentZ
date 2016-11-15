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
                            <h1><strong>APPointmentZ</strong> SessionId</h1>
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
                        		<div class="form-top-left" style="width:500px">
                        			<p>This session id should be used to follow doctors in through ISYD platform.</p>
                            		<p>In case of any change in counter/room change it from edit section.</p>
                        		</div>
                        		
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="home.php" method="post" class="login-form">
			                    	<div class="form-group">
										<div class="dropdown">
											<select class="selectpicker" style="width:145px; height:50px;" readonly>
											  <option><?php echo $_POST['counter'];?></option>
											</select>
											<button type="submit" class="btn" style="width:200px; background-color:gray;" disabled> Maped Doctor and Counter</button>
											<select class="selectpicker" style="width:145px; height:50px;">
											  <option><?php echo $_POST['doctor'];?></option>
											</select>
										</div>
			                    		<div class="form-group">
											<div class="form-group">
												<center>
												<p style="color:white">Date</p>
												<div>
													<input style="width:200px" class="form-control" size="16" type="text" value="<?php echo $_POST['date_picked'];?>" readonly>
												</div>
												<input type="hidden" id="dtp_input2" value="" />
												</center>
											</div>
											<div class="form-group">
												<center>
												<p style="color:white">Start time</p>
												<div>
													<input style="width:200px" class="form-control" size="16" type="text" value="<?php echo $_POST['start_time'];?>" readonly>
												</div>
												<input type="hidden" id="dtp_input3" value="" />
												</center>
											</div>
											<div class="form-group">
												<center>
												<p style="color:white">End time</p>
												<div>
													<input style="width:200px" class="form-control" size="16" type="text" value="<?php echo $_POST['end_time'];?>" readonly>
												</div>
												<input type="hidden" id="dtp_input4" value="" />
												</center>
											</div>
											<div>
											<center>
											<p style="color:white">SessionId</p>
											<button class="btn" style="width:200px; background-color:green;" disabled> 12345</button>
											</center>
											</div>
								
										</div>
										<center>
										<button type="submit" class="btn" style="width:100px">Ok</button>										
										</center>
			                        </div>
			                    </form>
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
