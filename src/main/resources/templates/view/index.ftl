<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; Charset=utf-8" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>杂食程序员</title>
		<link rel="shortcut icon" href="images/logo.png" type="image/x-icon" />
		<!--Layui-->
		<link href="layui/css/layui.css" rel="stylesheet" />
		<!--font-awesome-->
		<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式表-->
		<link href="css/global.css" rel="stylesheet" />
		<link href="css/animate.min.css" rel="stylesheet" />
		<!-- 本页样式表 -->
		<link href="css/index.css" rel="stylesheet" />
		<link href="css/blog.css" rel="stylesheet" />
	</head>

	<body>

		<#include "/view/common/nav.ftl">

		<div class="blog-user"></div>

		<div class="blog-body">
			<div class="layui-carousel blog-bg" id="carousel" lay-anim="default" lay-indicator="inside" lay-arrow="always" style="width: 1170px; height: 360px;">
				<div carousel-item="">
					<div class="bg bg_a layui-this"></div>
					<div class="bg bg_b"></div>
					<div class="bg bg_c"></div>
					<div class="bg bg_d"></div>
				</div>
				<div class="layui-carousel-ind">
					<ul>
						<li class="layui-this"></li>
						<li class=""></li>
						<li class=""></li>
						<li class=""></li>
					</ul>
				</div><button class="layui-icon layui-carousel-arrow" lay-type="sub"></button><button class="layui-icon layui-carousel-arrow" lay-type="add"></button></div>
			<div class="blog-container">
				<div class="blog-main">

					<div class="home-tips shadow">
						<i style="float:left;line-height:17px;" class="fa fa-volume-up"></i>
						<div class="home-tips-container" id="out_notice">
						</div>
					</div>

					<div class="blog-main-left animated slideInLeft">
						<div class="flow-default" id="parentArticleList">

						</div>
					</div>

					<div class="blog-main-right">

						<div class="blogerinfo shadow animated fadeInRight">
							<div class="blogerinfo-figure">
								<img src="img/admin.jpg" alt="杂食程序猿">
							</div>
							<p class="blogerinfo-nickname">杂食程序猿</p>
							<p class="blogerinfo-introduce">佛系人生</p>
							<hr>
							<div class="blogerinfo-contact">
								<a target="_blank" id="gwxx" title="Email" href="mailto:1161662883@qq.com"><i class="fa fa-envelope fa-2x"></i></a>
								<a target="_blank" id="htgl" title="后台管理" href="/auth/login"><i class="fa fa-database fa-2x"></i></a>
							</div>
						</div>

                        <div class="blog-module shadow animated fadeInRight">
                            <div class="blog-module-title"><i class="fa fa-tint"></i>&nbsp;鉴语</div>
                            <p>天行健，君子以自强不息</p>
                        </div>

					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>

		<#include "/view/common/footer.ftl">

		<script src="js/jquery.min.js"></script>

		<script src="js/index.js"></script>
	</body>

</html>