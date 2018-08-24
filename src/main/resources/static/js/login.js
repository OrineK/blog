layui.use('form', function(){
    var form = layui.form
        ,layer = layui.layer
        ,$ = layui.$;

    //自定义验证规则
    form.verify({
        password: [/(.+){6,12}$/, '密码必须6到12位']
        ,content: function(value){
            layedit.sync(editIndex);
        }
    });

    //监听提交
    form.on('submit(go)', function(data){
        console.log(data.field)
        $.post("/auth/login",data.field,function(res){
            if(res.code == 200){
                layer.msg(res.message, {time: 2000});
                setTimeout(window.location.href="/admin/index",2000);
            }else{
                layer.msg(res.message, {time: 2000});
            }
        },'json');
        return false;
    });
})