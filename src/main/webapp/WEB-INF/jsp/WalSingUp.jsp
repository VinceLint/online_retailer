<%--
  Created by IntelliJ IDEA.
  User: janzes
  Date: 2019/7/23
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 钱包注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Official Signup Form Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- fonts -->
    <link href="//fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900" rel="stylesheet" charset="utf-8">
    <link href="//fonts.googleapis.com/css?family=Monoton" rel="stylesheet" charset="utf-8">
    <!-- /fonts -->
    <!-- css -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all" charset="utf-8" />
    <link href="css/style.css" rel='stylesheet' type='text/css' media="all"  charset="utf-8"/>
    <!-- /css -->
</head>
<body>
<h1 class="w3ls">钱包注册</h1>
<div class="content-w3ls">
    <div class="content-agile1">
        <h2 class="agileits1">钱包</h2>
        <center>
            <div id='one'>
                <img id='two' src="img/wallet.jpg" class='play'>

            </div>

        </center>
        <p class="agileits2">用于充值、提现、支付以及收款</p>
    </div>
    <div class="content-agile2">
        <form action="${pageContext.request.contextPath}/toAddWal" method="post">
            <div class="form-control w3layouts">
                <input type="text" id="firstname" name="walId" placeholder="ID" title="Please enter your First Name" required="">
            </div>

            <div class="form-control w3layouts">
                <input type="email" id="email" name="walEmail" placeholder="mail@example.com" title="Please enter a valid email" required="">
            </div>

            <div class="form-control agileinfo">
                <input type="password" class="lock" name="walPassword" placeholder="Password" id="password1" required="">
            </div>

            <div class="form-control agileinfo">
                <input type="password" class="lock" name="confirm-password" placeholder="Confirm Password" id="password2" required="">
            </div>

            <input type="submit" class="register" value="立即注册">
        </form>
        <script type="text/javascript">
            window.onload = function () {
                document.getElementById("password1").onchange = validatePassword;
                document.getElementById("password2").onchange = validatePassword;
            }
            function validatePassword(){
                var pass2=document.getElementById("password2").value;
                var pass1=document.getElementById("password1").value;
                if(pass1!=pass2)
                    document.getElementById("password2").setCustomValidity("Passwords Don't Match");
                else
                    document.getElementById("password2").setCustomValidity('');
                //empty string means no validation error
            }
        </script>
        <ul class="social-agileinfo wthree2">
            <li><a href="#"><i class="fa fa-chevron-circle-left ">返回</i></a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>
<p class="copyright w3l">© 2017 Official Signup Form. All Rights Reserved | Design by <a href="#" target="_blank">庄志宏</a></p>
</body>
</html>
