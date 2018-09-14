<#include "/admin/common/head.ftl">
<#include "/admin/common/header.ftl">
<#include "/admin/common/sidebar.ftl">

<div class="layui-body">
    <blockquote class="layui-elem-quote layui-text">
        说明：显示顺序数字越大，表示首先显示项。
    </blockquote>

    <div style="float: right; margin: 10px;">
        <button id="addNew" class="layui-btn layui-btn-normal layui-btn-radius"><i class="layui-icon"></i>新增</button>
    </div>

    <table class="layui-table" id="list" lay-filter="list"></table>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<#include "/admin/common/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript">
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#list',
            url : '/notice/list',
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
                {field: 'content', title: '内容', align:"center"},
                {field: 'showOrders', title: '显示顺序', align:"center"},
                {field: 'updateTime', title: '更新时间', align:"center", sort: 'true',
                    templet:function (row) {
                        return new Date(row.updateTime).Format("yyyy-MM-dd hh:mm:ss");
                    }},
                {fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(list)', function(obj) {
            var data = obj.data;  //当前行的数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent == 'detail') {
                window.open('/article/detail/'+data.id)
            }else if (layEvent == 'edit') {
                location.href = '/admin/editArticle/'+data.id
            }else if (layEvent == 'del') {
                layer.confirm('确认删除此文章？'
                        , { btn: [ '确定', '取消' ] }
                        ,function(index){
                            $.post("/admin/delArticle/"+data.id,function(res){
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

        $("#addNew").on("click", function () {
            layer.prompt({title: '输入通告信息', formType: 2}, function(text, index){
                if (text == '') {
                    layer.msg("通告信息必须填写");
                    return false;
                }
                $.post("/admin/notice/add", {content : text}, function (res) {
                    if (res.code == 200) {
                        layer.msg("添加成功", {time: 2000}, function () {
                            location.reload()
                        })
                    }else {
                        layer.msg(res.message);
                    }

                })
            });
        })
    });
</script>