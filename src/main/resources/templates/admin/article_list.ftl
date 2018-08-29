<#include "/admin/common/head.ftl">
<#include "/admin/common/header.ftl">
<#include "/admin/common/sidebar.ftl">

<div class="layui-body">
    <table class="layui-table" id="articleList" lay-filter="articleList"></table>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<#include "/admin/common/footer.ftl">
<script type="text/javascript">
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#articleList',
            url : '/admin/articleListJson',
            page : true,
            limits : [10,15,20,25],
            limit : 10,
            id : "articleListTable",
            request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                statusCode: 200,
                msgName: 'message',
                cateName: 'data.category.name'
            },
            cols : [[
                {type: "checkbox", fixed:"left"},
                {field: 'title', title: '标题', align:"center"},
                {field: 'author', title: '作者', align:"center"},
                {field: 'cateName', title: '分类', align:"center", templet:'<div>{{d.category.name}}</div>'},
                {field: 'clickNum', title: '点击数', align:"center"},
                {field: 'startNum', title: '点赞数', align:"center"},
                {field: 'createTime', title: '发布时间', align:"center", sort: 'true'},
                {fixed: 'right', title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(articleList)', function(obj) {
            var data = obj.data;  //当前行的数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent == 'detail') {
                //TODO
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
    });
</script>