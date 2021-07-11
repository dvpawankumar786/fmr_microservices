<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Stocks</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 
    <link rel="stylesheet" type="text/css"
          href="/css/styles.css"/>
          
</head>
<body>
<div class="hr"></div>
<div id="formidren" class="login-wrap">

	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		<form class="login-form" id="login-form">
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label">Username</label>
					<br>
					<input id="user" type="text" class="input">
				</div>
				<br>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<br>
					<input id="pass" type="password" class="input" data-type="password">
				</div>
				<br>
				<div class="group">
					<input id="check" type="checkbox" class="check" checked>
					<label for="check"><span class="icon"></span> Keep me Signed in</label>
				</div>
				<div class="group">
					<input type="submit" id="submit2" class="button" value="Sign In">
				</div>
				<div class="hr"></div>
				<div class="foot-lnk">
					<a href="#forgot">Forgot Password?</a>
				</div>
			</div>
			<div class="sign-up-htm">
				
			<div class="group">
					<label for="user1" class="label">Username</label>
					<input id="user1" type="text" class="input">
				</div>
				<div class="group">
					<label for="user" class="label">Role</label>
					<input type="radio" name="course" id="role" value="ADMIN">ROLE_ADMIN</input>
				    <input type="radio" name="course" id="role" value="USER">ROLE_USER</input>
					
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Repeat Password</label>
					<input id="pass" type="password" class="input" data-type="password">
				</div>
				<div class="group">
					<label for="age" class="label">Age</label>
					<input id="age" type="text" class="input">
				</div>
				<div><br></div>
				<div class="group">
					<input type="submit" id="register" class="button" value="Register">
				</div>
				<div class="hr"></div>
				<div class="foot-lnk">
					<label for="tab-1">Already Registered???</a>
				</div>
			</div>
			
		</form>
	</div>
</div>

 <div id="feedback"></div>
<script type="text/javascript"
        src="/js/jquery-1.11.1.min.js"></script>
 
<script type="text/javascript" src="/js/scripts.js"></script>
 
</body>
</html>