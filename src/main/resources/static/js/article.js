layui.use(['form', 'jquery', 'flow'], function(){
    var form = layui.form;
    var $ = layui.jquery;
    var flow = layui.flow;
    form.on('submit(searchForm)', function(data){
        var keywords=$("#keywords").val();
        if(keywords==null || keywords==""){
            layer.msg('请输入要搜索的关键字');
            return false;
        }
        search();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    categories();

    flow.load({
        elem: "#parentArticleList"
        ,isLazyimg:true
        ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get('/article/list?page='+page, function(res){
                //假设你的列表返回在data集合中
                layui.each(res.data.content, function(index, item){
                    lis.push(article_item(item));
                });
                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < res.data.totalPages);
            });
        }
    });
});



$(function(){
    $(".fa-file-text").parent().parent().addClass("layui-this");
    var keywords=$("#keywords").val();
    $("#keywords").keydown(function (event) {
        if (event.keyCode == 13) {
            var keyword=$("#keywords").val();
            if(keyword==null || keyword==""){
                layer.msg('请输入要搜索的关键字');
                return false;
            }
            search();
        }
    });
});

function search() {
	layer.msg('功能要自己写哦！');
}

function classifyList(id) {
	layer.msg('功能要自己写哦！');
}


function article_item(json) {
    var time = new Date(json.createTime).Format("yyyy-MM-dd hh:mm:ss");
    var html = '<div class="article shadow animated zoomIn">'+
        '<div class="article-left ">' +
        '<a href="/article/detail/'+json.id+'">'+
        '<img class="cover-img" src="'+json.coverImg+'" alt="'+json.title+'">' +
        '</a></div>'+
        '<div class="article-right">'+
        '<div class="article-title">'+
        '<span class="article_is_yc">'+json.type+'</span>&nbsp;'+
        '<a href="/article/detail/'+json.id+'">'+json.title+'</a>'+
        '</div>'+
        '<div class="article-abstract">'+json.summary+'</div>'+
        '</div>'+
        '<div class="clear"></div>'+
        '<div class="article-footer">'+
        '<span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;'+time+'</span>'+
        '<span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;'+json.author+'</span>'+
        '<span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="javascript:classifyList(5);"> '+json.category.name+'</a></span>'+
        '<span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;'+json.clickNum+'</span>'+
        '<span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;'+json.startNum+'</span>'+
        '</div>'+
        '</div>';
    return html;
}

function categories() {
    $.get("/categories", function (res) {
        var data = res.data;
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += '<a href="javascript:classifyList('+data[i].id+');">'+data[i].name+'</a>';
        }
        html += '<div class="clear"></div>';
        $(".categoryOut").append(html);
    })
}