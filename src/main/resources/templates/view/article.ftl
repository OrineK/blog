<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; Charset=utf-8" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>杂食程序猿-文章专栏</title>
		<link rel="shortcut icon" href="/images/logo.png" type="image/x-icon" />
		<!--Layui-->
        <link href="/layui/css/layui.css" rel="stylesheet" />
		<!--font-awesome-->
		<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式表-->
		<link href="/css/global.css" rel="stylesheet" />
		<link href="/css/animate.min.css" rel="stylesheet" />
		<!-- 本页样式表 -->
		<link href="/css/article.css" rel="stylesheet" />
		<link href="/css/blog.css" rel="stylesheet" />
	</head>

	<body>

		<#include "/view/common/nav.ftl">

		<div class="blog-body">

			<div class="blog-container">
				<blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow" style="visibility: visible;">
					<a href="index.html" title="网站首页">网站首页<span class="layui-box">&gt;</span></a>
					<a><cite>文章专栏</cite></a>
				</blockquote>
				<div class="blog-main">

					<div id="parentArticleList" class="blog-main-left animated slideInLeft">
						<div class="flow-default" id="articleList">

						</div>
					</div>

					<div class="blog-main-right">

						<div class="blog-search animated fadeInRight">
							<div class="layui-form-item">
								<div class="search-keywords  shadow">
									<input type="text" id="keywords" name="keywords" lay-filter="searchInput" lay-verify="required" placeholder="输入关键词搜索" autocomplete="off" class="layui-input">
								</div>
								<div class="search-submit  shadow">
									<a class="search-btn" lay-submit="searchForm" lay-filter="searchForm"><i class="fa fa-search"></i></a>
								</div>
							</div>
						</div>

						<div class="article-category shadow categoryOut">
							<div class="article-category-title">分类导航</div>
						</div>

						<div class="category-toggle"><i class="fa fa-chevron-left"></i></div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>

		<#include "/view/common/footer.ftl">

		<div class="blog-mask animated layui-hide"></div>

		<script src="/js/jquery.min.js"></script>

		<script src="/layui/layui.js"></script>

		<script src="/js/global.js"></script>
		<script src="/js/article.js"></script>
	</body>

</html>