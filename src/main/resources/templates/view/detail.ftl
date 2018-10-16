<!DOCTYPE html>
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
		<link rel="stylesheet" href="/editor.md/css/editormd.css">
	</head>

	<style>
        .editormd-preview-container, .editormd-html-preview {
            padding: 0;
		}
	</style>

	<body>

		<#include "/view/common/nav.ftl">

		<div class="blog-body">
            <input type="hidden" id="editorType" value="${article.editorType}">
			<input type="hidden" id="articleId" value="${article.id}">
			<div class="blog-container">
				<div class="blog-main">
					<blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow" style="visibility: visible;">
						<a href="/" title="网站首页">网站首页<span class="layui-box">&gt;</span></a>
						<a href="article.ftl" title="文章专栏">文章专栏<span class="layui-box">&gt;</span></a>
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
									<#if article.editorType?? && article.editorType == "MdEditor">
                                        <textarea style="display: none;">${article.content}</textarea>
										<#else>
										${article.content}
									</#if>
								</div>
							</div>

							<#if before?? || after??>
							<div class="blog-module shadow">
								<#if before??>
									<div style="padding: 5px;">
                                        上一篇：<a href="/article/detail/${before.id}">${before.title}</a>
									</div>
								</#if>
								<#if after?? >
									<div style="padding: 5px;">
                                        下一篇：<a href="/article/detail/${after.id}">${after.title}</a>
									</div>
								</#if>
							</div>
							</#if>

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
</div>
</div>
<div class="clear "></div>
</div>
</div>
</div>

<#include "/view/common/footer.ftl">
<#if article.editorType?? && article.editorType == "MdEditor">
<script src="/editor.md/lib/marked.min.js"></script>
<script src="/editor.md/lib/prettify.min.js"></script>
<script src="/editor.md/lib/raphael.min.js"></script>
<script src="/editor.md/lib/underscore.min.js"></script>
<script src="/editor.md/lib/sequence-diagram.min.js"></script>
<script src="/editor.md/lib/flowchart.min.js"></script>
<script src="/editor.md/lib/jquery.flowchart.min.js"></script>
<script src="/editor.md/editormd.js"></script>
</#if>
<script src="/js/about.js "></script>
<script src="/js/detail.js "></script>

</body>
</html>