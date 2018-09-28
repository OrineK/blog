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

<nav id="h" class="blog-nav layui-header">
    <div class="blog-container">

        <a class="blog-logo" href="/">杂食程序员</a>

        <a class="blog-navicon" href="javascript:;">
            <i class="fa fa-navicon"></i>
        </a>
    </div>
</nav>

<div class="blog-body">
    <div style="margin: 0 auto; width: 320px; text-align: center;">
        <h2>管理员初始化设置</h2>
        <form action="" class="layui-form layui-form-pane" style="margin-top: 10px;">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required|password" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="re_password" lay-verify="required|password" placeholder="请输入确认密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                    <button class="layui-btn" lay-submit="" lay-filter="go">立即提交</button>
            </div>
        </form>
    </div>
</div>

<#include "/view/common/footer.ftl">
</body>
<script type="text/javascript">
    layui.use('form', function(){
        var form = layui.form
                ,layer = layui.layer
                ,$ = layui.$;

        //自定义验证规则
        form.verify({
            password: [/(.+){6,12}$/, '密码必须6到12位']
        });

        //监听提交
        form.on('submit(go)', function(data){
            if (data.field.password != data.field.re_password) {
                layer.msg("两次密码不一致，请重新输入", {time: 2000});
                return false;
            }
            $.post("/initAdmin",data.field,function(res){
                if(res.code == 200){
                    layer.msg(res.message, {time: 2000});
                    location.reload();
                }else{
                    layer.msg(res.message, {time: 2000});
                }
            },'json');
            return false;
        });
    })
</script>
</html>