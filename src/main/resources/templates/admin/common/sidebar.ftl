<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item <#if sidebar == 'index'>layui-this</#if>"><a href="/admin/index">控制台</a></li>
            <li class="layui-nav-item <#if sidebar == 'user'>layui-this</#if>"><a href="">用户管理</a></li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;" lay-tips="文章管理" lay-direction="2">
                    <cite>文章管理</cite>
                    <span class="layui-nav-more"></span></a>
                <dl class="layui-nav-child">
                    <dd class="<#if sidebar == 'article_add'>layui-this</#if>"><a href="/admin/addArticle">新增文章</a></dd>
                    <dd class="<#if sidebar == 'article_list'>layui-this</#if>"><a href="/admin/articleList">文章列表</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item <#if sidebar == 'category'>layui-this</#if>"><a href="/admin/categories">分类管理</a></li>
            <li class="layui-nav-item <#if sidebar == 'tag'>layui-this</#if>"><a href="">标签管理</a></li>
            <li class="layui-nav-item <#if sidebar == 'notice'>layui-this</#if>"><a href="/admin/notice">通知管理</a></li>
            <li class="layui-nav-item <#if sidebar == 'system'>layui-this</#if>"><a href="">系统管理</a></li>
        </ul>
    </div>
</div>