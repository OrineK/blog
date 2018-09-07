﻿<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; Charset=utf-8" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title th:text="longwang博客文章专栏'"></title>
		<link rel="shortcut icon" href="images/logo.png" type="image/x-icon" />
		<!--Layui-->
		<link href="/layui/css/layui.css" rel="stylesheet" />
		<!--font-awesome-->
		<link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!--全局样式表-->
		<link href="/css/global.css" rel="stylesheet" />
		<link href="/css/animate.min.css" rel="stylesheet" />
		<!-- 本页样式表 -->
		<link href="/css/detail.css" rel="stylesheet" />
		<link href="/css/blog.css" rel="stylesheet" />
		<!-- jquery -->
		<script src="/js/jquery.min.js"></script>
	</head>

	<body>

		<#include "/view/common/nav.ftl">

		<div class="blog-body">

			<div class="blog-container">
				<div class="blog-main">
					<blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow" style="visibility: visible;">
						<a href="/" title="网站首页">网站首页<span class="layui-box">&gt;</span></a>
						<a href="article.html" title="文章专栏">文章专栏<span class="layui-box">&gt;</span></a>
						<a><cite class="title">${article.title}</cite></a>
					</blockquote>
					<div class="blog-main">

						<div id="parentArticleList" class="blog-main-left animated slideInLeft">

							<div class="article-detail shadow">
								<div class="article-detail-title title">${article.title}</div>
								<div class="article-detail-info">
									<span>编辑时间：${article.updateTime}</span>
									<span>作者：${article.author}</span>
									<span>浏览量：${article.clickNum}</span>
								</div>
								<div id="articleContent" style="overflow: hidden;" class="article-detail-content">
									${article.content}
								</div>
							</div>

							<div class="blog-module shadow" style="box-shadow: 0 1px 8px #a6a6a6;">
								<fieldset class="layui-elem-field layui-field-title" style="margin-bottom:0">
									<legend>来说两句吧</legend>
									<div class="layui-field-box">
										<form class="layui-form blog-editor" action="">
											<input type="hidden" id="user" name="user" lay-verify="userId" value="">
											<input type="hidden" id="article" name="article" value="26">
											<div class="layui-form-item">
												<textarea name="content" lay-verify="content" id="remarkEditor" placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
												<div class="layui-layedit">
													<div class="layui-unselect layui-layedit-tool"><i class="layui-icon layedit-tool-face" title="表情" layedit-event="face" "=" "></i><span class="layedit-tool-mid "></span><i class="layui-icon layedit-tool-left " title="左对齐 " lay-command="justifyLeft " layedit-event="left " "=""></i><i class="layui-icon layedit-tool-center" title="居中对齐" lay-command="justifyCenter" layedit-event="center" "=" "></i><i class="layui-icon layedit-tool-right " title="右对齐 " lay-command="justifyRight " layedit-event="right " "=""></i><span class="layedit-tool-mid"></span><i class="layui-icon layedit-tool-link" title="插入链接" layedit-event="link" "=" "></i></div><div class="layui-layedit-iframe "><iframe id="LAY_layedit_1 " name="LAY_layedit_1 " textarea="remarkEditor " frameborder="0 " style="height: 150px; "></iframe></div></div>
</div>
<div class="layui-form-item ">
<button class="layui-btn " lay-submit="formLeaveMessage " lay-filter="formLeaveMessage ">提交评论</button>
</div>
</form>
</div>
</fieldset>
<div class="blog-module-title ">最新评论</div>
<ul class="blog-comment " id="commentList "></ul>
</div>
</div>

<div class="blog-main-right ">

<div class="category-toggle "><i class="fa fa-chevron-left "></i></div>

 <div class="article-category shadow ">
<div class="article-category-title ">分类导航</div>
	 <#list categories as cate>
		<a href="javascript:classifyList(${cate.id}); ">${cate.name}</a>
	 </#list>
<#--<a href="javascript:classifyList(1); ">Java基础</a><a href="javascript:classifyList(2); ">web开发</a><a href="javascript:classifyList(3); ">开发工具</a><a href="javascript:classifyList(4); ">心情日记</a><a href="javascript:classifyList(5); ">SpringBoot</a><a href="javascript:classifyList(6); ">Dubbo</a><a href="javascript:classifyList(7); ">Redis</a><a href="javascript:classifyList(8); ">Maven</a><a href="javascript:classifyList(9); ">Centos</a><a href="javascript:classifyList(10); ">Tomcat</a><a href="javascript:classifyList(11); ">技术交流</a>-->
<div class="clear "></div>
</div>
<div class="blog-module shadow ">
<div class="blog-module-title ">相似文章</div>
<ul class="blog-module-ul ">
<li>
<span>
<i class="layui-badge-rim layui-bg-red ">1</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring Boot 表单验证@Valid</a>
</li>
<li>
<span>
<i class="layui-badge-rim layui-bg-orange ">2</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring boot 使用 JSP</a>
</li>
<li>
<span>
<i class="layui-badge-rim layui-bg-green ">3</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring boot 下的 Spring mvc</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">4</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring boot 自定义配置</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">5</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring boot 的核心配置文件</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">6</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">第一个 Spring boot 程序及解析</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">7</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring boot 开发环境</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">8</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">SpringBoot简介</a>
</li>
</ul>
</div>
<div class="blog-module shadow ">
<div class="blog-module-title ">随便看看</div>
<ul class="blog-module-ul ">
<li>
<span>
<i class="layui-badge-rim layui-bg-red ">1</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Centos7安装和配置Tomcat8</a>
</li>
<li>
<span>
<i class="layui-badge-rim layui-bg-orange ">2</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">支付宝开发者入驻</a>
</li>
<li>
<span>
<i class="layui-badge-rim layui-bg-green ">3</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">redis requires Ruby version &gt;= 2.2.2问题</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">4</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Spring boot 开发环境</a>
</li>
 <li>
<span>
<i class="layui-badge-rim ">5</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Tomcat优化性能，JVM优化</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">6</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Centos7安装和配置jre1.8</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">7</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">怎么关闭eclipse对js xml的验证?</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">8</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">centos7对外开放端口</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">9</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">Java设计模式-单例模式</a>
</li>
<li>
<span>
<i class="layui-badge-rim ">10</i>
</span>
&nbsp;&nbsp;<a href="detail.html ">解决Tomcat中项目Url带中文参数乱码</a>
</li>
</ul>
</div>
</div>
</div>
<div class="clear "></div>
</div>
</div>
</div>

<#include "/view/common/footer.ftl">

<script src="/js/about.js "></script>
<script src="/js/detail.js "></script>

</body>
</html>