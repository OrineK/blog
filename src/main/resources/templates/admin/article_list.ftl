<#include "/admin/common/head.ftl">
<#include "/admin/common/header.ftl">
<#include "/admin/common/sidebar.ftl">

<div class="layui-body">
    <table class="layui-table" lay-data="{height: 'full-200', cellMinWidth: 80, page: true, limit:30, url:'/admin/articleListJson'}">
        <thead>
        <tr>
            <th lay-data="{type:'checkbox'}">ID</th>
            <th lay-data="{field:'id', width:100, sort: true}">ID</th>
            <th lay-data="{field:'title', width:100}">标题</th>
        </tr>
        </thead>
    </table>
</div>
<#include "/admin/common/footer.ftl">
<script type="text/javascript">
    layui.use('table', function(){
        var table = layui.table;
    });
</script>