<#include "/admin/common/head.ftl">
<#include "/admin/common/header.ftl">
<#include "/admin/common/sidebar.ftl">

<div class="layui-body">
    <table class="layui-table" id="list" lay-filter="list"></table>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<#include "/admin/common/footer.ftl">
<script type="text/javascript">
    layui.use('table', function() {
        var table = layui.table;

        table.render({
            elem: '#list',
            url : '/categories',
            page : true,
            limits : [10,15,20,25],
            limit : 10,
            id : "listTable",
            request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                statusCode: 200,
                msgName: 'message'
            },
            cols : [[
                {type: "checkbox", fixed:"left"},
                {field: 'id', title: 'ID', align:"center"},
                {field: 'name', title: '分类', align:"center"},
                {fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(list)', function(obj) {
            var data = obj.data;  //当前行的数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent == 'edit') {
                //prompt层
                layer.prompt({name: '输入更改的分类名，并确认', formType: 3}, function(text, index){
                    if (text == "") {
                        layer.msg("请输入新的分类名", {time: 2000});
                        return false;
                    }
                    $.get("/admin/editCategory/"+data.id,
                            {name : text}, function (res) {
                                if(res.code == 200){
                                    layer.alert("修改成功", function () {
                                        location.reload()
                                    });
                                }else{
                                    layer.msg(res.message, {time: 2000});
                                }
                            })
                });
            }else if (layEvent == 'del') {
                layer.confirm('确认删除此分类？'
                        , { btn: [ '确定', '取消' ] }
                        ,function(index){
                            $.post("/admin/delCategory/"+data.id,function(res){
                                if(res.code == 200){
                                    layer.alert("删除成功", function () {
                                        location.reload()
                                    });
                                }else{
                                    layer.msg(res.message, {time: 2000});
                                }
                            },'json');
                        });
            }
        })
    });
</script>