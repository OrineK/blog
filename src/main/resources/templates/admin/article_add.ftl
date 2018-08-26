<#include "/admin/common/head.ftl">
<#include "/admin/common/header.ftl">
<#include "/admin/common/sidebar.ftl">

<div class="layui-body">
    <div style="padding: 15px;">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">分类</label>
                <div class="layui-input-inline">
                    <select name="interest" lay-filter="category">
                        <option value="" selected=""></option>
                        <#if categories?has_content>
                            <#list categories as cate>
                                <option value="${cate.id}">${cate.name}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block" style="width: 60%">
                    <input type="text" name="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                </div>
            </div>
            <input type="hidden" name="coverImg" id="coverImg" value="">

        </form>
        <div class="layui-form-item">
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="upload">上传封面图</button>
                <form id="video_upload" method="POST" style="top: -30px; position: relative; opacity: 1;">
                    <input type="hidden" name="nid" value="cover_img"/>
                    <input id="pfile_upload" name="file" onchange="dopic_upload()" class="weui-uploader__input" type="file" accept="image/*" multiple="">
                </form>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="cover_img">
                    <p id="demoText"></p>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/admin/common/footer.ftl">
<script type="text/javascript">
    layui.use(['form', 'layedit', 'upload'], function() {

        var form = layui.form
            ,layer = layui.layer
            ,upload = layui.upload
            ,$ = layui.$;


    })

    function dopic_upload() {
        var form = $("#pic_upload")
        var data = new FormData(form[0]);
        $.ajax({
            url: "/img/uploadifySave",
            type: 'POST',
            data: data,
            dataType: 'JSON',
            cache: false,
            processData: false,
            contentType: false
        }).done(function(res){
            console.log(res);
            $("#cover_img").attr("src", res.data[0]);
            $("#coverImg").val(res.data[0]);
        });
    };
</script>