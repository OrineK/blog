<#include "/admin/common/head.ftl">
<#include "/admin/common/header.ftl">
<#include "/admin/common/sidebar.ftl">

<div class="layui-body">
    <div style="padding: 15px;">
        <form class="layui-form layui-form-pane">
            <input type="hidden" id="Author" name="Author" value="jp.orine">
            <div class="layui-form-item">
                <label class="layui-form-label">分类</label>
                <div class="layui-input-inline">
                    <select name="categoryId" lay-filter="categoryId" id="categoryId">
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
                    <input type="text" name="title" id="title" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">摘要</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入摘要" lay-verify="required" id="summary" name="summary" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" lay-verify="content" class="layui-textarea" name="content" id="content" style="display: none"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">标签</label>
                <div class="layui-input-inline">
                    <input type="text" id="tagNames" name="tagNames" lay-verify="required" placeholder="Java,PHP" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否原创</label>
                <div class="layui-input-inline">
                    <select name="type" lay-filter="type" id="type">
                        <option value="ORIGINAL">原创</option>
                        <option value="REPRINT">转载</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="upload">上传封面图</button>
                    <div class="layui-upload-list">
                        <input type="hidden" lay-verify="required" name="coverImg" id="coverImg" value="">
                        <img class="layui-upload-img" id="cover_img">
                        <p id="demoText"></p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center">
                <button class="layui-btn" lay-submit="" lay-filter="go">提交发布</button>
            </div>
        </form>
    </div>
</div>
<#include "/admin/common/footer.ftl">
<script type="text/javascript">
    layui.use(['form', 'layedit', 'upload'], function() {

        var form = layui.form
            ,layer = layui.layer
            ,upload = layui.upload
            ,layedit = layui.layedit;

        layedit.set({
            uploadImage: {
                url: '/uploadArtImg' //接口url
                ,type: 'post'
            }
        })
        var index = layedit.build('content');

        var uploadInst = upload.render({
            elem: '#upload'
            ,url: '/uploadImg'
            ,acceptMime: 'image/*'
            ,accept: 'images'
            ,method: 'POST'
            ,data: {'dirname':'coverImg',"enctype":"multipart/form-data"}
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#cover_img').attr('src', result); //图片链接（base64）
                    $('#cover_img').attr({'width':'100px', 'height':'100px'});
                });
            }
            ,done: function(res){
                console.log(res)
                //如果上传失败
                if(!res.data){
                    return layer.msg(res.message);
                }
                $("#coverImg").val(res.data);
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });

        form.verify({
            content: function(value) {
                return layedit.sync(index);
            }
        });

        //监听提交
        form.on('submit(go)', function(data){
            // layedit.sync(editIndex);
            $.post("/admin/addArticle",data.field,function(res){
                if(res.code == 200){
                    layer.alert(res.message, function () {
                        setTimeout(window.location.href="/admin/articleList",2000);
                    });
                }else{
                    layer.alert(res.message, {time: 2000});
                }
            },'json');
            return false;
        });
    })

</script>