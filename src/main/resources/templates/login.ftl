<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/layui/css/modules/layer/default/layer.css">
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>

<div class="login-box">
    <form action="" class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="/layui/layui.all.js" />
<script type="text/javascript" src="/layui/lay/modules/layer.js" />
<script type="text/javascript" src="/layui/lay/modules/form.js" />
</body>
</html>