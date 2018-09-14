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
							<#--<span style="color: rgb(0, 150, 136); cursor: pointer; display: none;">-->
								<#--<p><span>网站处于试运行过程，如遇到bug，可以及时与我联系哦，首页左侧可以联系我，或者给我留言。</span></p>-->
							<#--</span>-->
							<#--<span style="color: rgb(0, 150, 136); cursor: pointer; display: block;">-->
								<#--<p><span>Long博客&nbsp; —— &nbsp;一个Java程序员的个人博客，网站采用Layui为前端框架，记录技术交流，工作心得体会！</span></p>-->
							<#--</span>-->
							<#--<span style="color: rgb(0, 150, 136); cursor: pointer; display: none;">感谢您发现本站，由于某些原因导致数据库丢失，但本站内容会持续更新的。</span>-->
						</div>
					</div>

					<div class="blog-main-left animated slideInLeft">
						<div class="flow-default" id="parentArticleList">

						</div>
					</div>

					<div class="blog-main-right">

						<div class="blogerinfo shadow animated fadeInRight">
							<div class="blogerinfo-figure">
								<img src="img/admin.jpg" alt="竹林听雨">
							</div>
							<p class="blogerinfo-nickname">竹林听雨</p>
							<p class="blogerinfo-introduce">有个被嘲笑的梦想万一有天实现了呢？up up~</p>
							<p class="blogerinfo-location"><i class="fa fa-location-arrow"></i>&nbsp;<span>湖南-湘潭</span></p>
							<hr>
							<div class="blogerinfo-contact">
								<a target="_blank" id="QQjl" title="QQ交流" href="http://shang.qq.com/email/stop/email_stop.html?qq=2896543728"><i class="fa fa-qq fa-2x"></i></a>
								<a target="_blank" id="gwxx" title="给我写信" href="mailto:2896543728@qq.com"><i class="fa fa-envelope fa-2x"></i></a>
								<a target="_blank" id="xlwb" title="新浪微博" href="https://weibo.com/p/1005055534423586"><i class="fa fa-weibo fa-2x"></i></a>
								<a target="_blank" id="htgl" title="后台管理" href="/toLogin"><i class="fa fa-database fa-2x"></i></a>
							</div>
						</div>

						<div class="layui-tab layui-tab-brief shadow animated fadeInRight" lay-filter="docDemoTabBrief">
							<ul class="layui-tab-title">
								<li class="layui-this">点击排行</li>
								<li>站长推荐</li>
							</ul>
							<div class="layui-tab-content">
								<div class="layui-tab-item layui-show">
									<ul class="blog-module-ul">
										<li>
											<span>
<i class="layui-badge-rim layui-bg-red">1</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">Tomcat优化性能，JVM优化</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim layui-bg-orange">2</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">Redis安装</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim layui-bg-green">3</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">Spring boot 使用 JSP</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">4</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">Spring Boot 表单验证@Valid</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">5</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">Spring boot 的核心配置文件</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">6</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">Centos7安装和配置jre1.8</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">7</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">centos7对外开放端口</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">8</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">怎么关闭eclipse对js xml的验证?</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">9</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">向百度主动推送以加快网页的收录速度！</a>
										</li>
										<li>
											<span>
<i class="layui-badge-rim">10</i>
</span> &nbsp;&nbsp;
											<a href="detail.html">支付宝开发者入驻</a>
										</li>
									</ul>
								</div>
								<div class="layui-tab-item">
									<ul class="blog-module-ul">
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">Tomcat优化性能，JVM优化</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">Redis安装</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">Spring boot 使用 JSP</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">Spring Boot 表单验证@Valid</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">Spring boot 的核心配置文件</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">Centos7安装和配置jre1.8</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">centos7对外开放端口</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">怎么关闭eclipse对js xml的验证?</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">向百度主动推送以加快网页的收录速度！</a>
										</li>
										<li>
											<span class="article_is_yc">原创</span> &nbsp;&nbsp;
											<a href="detail.html">支付宝开发者入驻</a>
										</li>
									</ul>
								</div>
							</div>
						</div>

						<div class="blog-module shadow animated fadeInRight">
							<div class="blog-module-title"><i class="fa fa-file-text-o"></i>&nbsp;博主介绍</div>
							<p>Longwang，一枚90后的java程序员，好奇心比较强，目前是一个码农，从事Java方向的研究和开发。-- Longwang</p>
						</div>
						<div class="blog-module shadow animated fadeInRight fadeInUp">
							<div class="blog-module-title"><i class="layui-icon"></i>&nbsp;热评用户</div>
							<ul class="hotusers-list">
								<li class="hotusers-list-item">
									<div class="hotusers-top hotusers-num">1</div>
									<div class="hotusers-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/B5D5212D0429E4491D932EEEF814FE99/100" width="45" height="45">
									</div>
									<div>
										<div class="hotusers-nick">Mr.Long</div>
										<span class="hotusers-level" title="等级1" style="background-image: url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv01.png);">
<i style=" background-image:url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv01-04.png);">潜水</i>
</span>
										<span class="hotusers-totalcmt">本站评论数：4</span>
									</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div class="hotusers-top hotusers-num">2</div>
									<div class="hotusers-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/142350DC6080D761759CA72042447829/100" width="45" height="45">
									</div>
									<div>
										<div class="hotusers-nick">十七</div>
										<span class="hotusers-level" title="等级2" style="background-image: url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv02.png);">
<i style=" background-image:url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv01-04.png);">潜水</i>
</span>
										<span class="hotusers-totalcmt">本站评论数：2</span>
									</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div class="hotusers-top hotusers-num">3</div>
									<div class="hotusers-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/436C863FE45FEBC7D279B0C22E9A62FD/100" width="45" height="45">
									</div>
									<div>
										<div class="hotusers-nick">默〃</div>
										<span class="hotusers-level" title="等级3" style="background-image: url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv03.png);">
<i style=" background-image:url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv01-04.png);">潜水</i>
</span>
										<span class="hotusers-totalcmt">本站评论数：2</span>
									</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div class="hotusers-top hotusers-num">4</div>
									<div class="hotusers-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/2F1EDDE252859E5FF645F959893C6863/100" width="45" height="45">
									</div>
									<div>
										<div class="hotusers-nick">Single</div>
										<span class="hotusers-level" title="等级4" style="background-image: url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv04.png);">
<i style=" background-image:url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv01-04.png);">潜水</i>
</span>
										<span class="hotusers-totalcmt">本站评论数：1</span>
									</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div class="hotusers-top hotusers-num">5</div>
									<div class="hotusers-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/0ABFEA14AAF50D3C07B6489440FEE081/100" width="45" height="45">
									</div>
									<div>
										<div class="hotusers-nick">一念之间</div>
										<span class="hotusers-level" title="等级5" style="background-image: url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv05.png);">
<i style=" background-image:url(https://changyan.itc.cn/v2/asset/scs/imgs/p-lv01-04.png);">潜水</i>
</span>
										<span class="hotusers-totalcmt">本站评论数：1</span>
									</div>
									<span class="hotusers-icons"></span>
								</li>
							</ul>
						</div>
						<div class="blog-module shadow animated fadeInRight fadeInUp">
							<div class="blog-module-title"><i class="layui-icon"></i>&nbsp;最近评论</div>
							<ul class="recent-list">
								<li class="hotusers-list-item">
									<div data-name="那么平凡。" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/194135EDCD4659E5550C71D6D665F3CB/100" width="45" height="45">
									</div>
									<div class="remark-content">博主威武</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="开始了就别停下来" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/436973EA919FEE0B67860BEC897ECC70/100" width="45" height="45">
									</div>
									<div class="remark-content">uiu</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="【阿龙】" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/6B925BE6DA65C32DCBE03772E56AA6C6/100" width="45" height="45">
									</div>
									<div class="remark-content">111</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="Mr.Long" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/B5D5212D0429E4491D932EEEF814FE99/100" width="45" height="45">
									</div>
									<div class="remark-content">还不错</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="清风" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/B4901BFB60F8DEE83F01692F2544E612/100" width="45" height="45">
									</div>
									<div class="remark-content">
										<p>网站开源吗</p>
										<p><br></p>
									</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="Mr.Long" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/B5D5212D0429E4491D932EEEF814FE99/100" width="45" height="45">
									</div>
									<div class="remark-content">。。。。。。。。。。</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="默〃" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/436C863FE45FEBC7D279B0C22E9A62FD/100" width="45" height="45">
									</div>
									<div class="remark-content">
										<p>旧时光博客博主&nbsp;https://www.zqfirst.top 来过</p>
									</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="默〃" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/436C863FE45FEBC7D279B0C22E9A62FD/100" width="45" height="45">
									</div>
									<div class="remark-content">网站不错，继续加油。</div>
									<span class="hotusers-icons"></span>
								</li>
								<li class="hotusers-list-item">
									<div data-name="十七" class="remark-user-avator">
										<img src="http://qzapp.qlogo.cn/qzapp/101477629/142350DC6080D761759CA72042447829/100" width="45" height="45">
									</div>
									<div class="remark-content">网站不错，学到东西了</div>
									<span class="hotusers-icons"></span>
								</li>
							</ul>
						</div>

						<div class="blog-module shadow animated fadeInRight">
							<div class="blog-module-title"><i class="fa fa-link"></i>&nbsp;友情链接</div>
							<ul class="blogroll">
								<li>
									<a target="_blank" href="http://wurao.xin" title="勿扰博客">勿扰博客</a>
								</li>
								<li>
									<a target="_blank" href="http://blog.itcodai.com" title="ITcodai博客">ITcodai博客</a>
								</li>
								<li>
									<a target="_blank" href="https://www.zqfirst.top" title="旧时光">旧时光</a>
								</li>
							</ul>
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