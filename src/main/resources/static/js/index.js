layui.use(['jquery','carousel','flow','layer'], function () {
    var $ = layui.jquery;
    var flow = layui.flow;
    var layer = layui.layer;

    var width = width || window.innerWeight || document.documentElement.clientWidth || document.body.clientWidth;
    width = width>1200 ? 1170 : (width > 992 ? 962 : width);
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
      elem: '#carousel'
      ,width: width+'px' //设置容器宽度
      ,height:'360px'
      ,indicator: 'inside'
      ,arrow: 'always' //始终显示箭头
      ,anim: 'default' //切换动画方式
      
    });

    flow.load({
        elem: "#parentArticleList"
        ,isLazyimg:true
        ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get('/article/list?page='+page, function(res){
                console.log(res)
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

    $("#gwxx").mouseover(function(){
        layer.tips('Email', this,{
            tips: 1
        });
    });
    $("#htgl").mouseover(function(){
        layer.tips('后台管理', this,{
            tips: 1
        });
    });
    
    $(function () {
        $.get("/notice/list", function (res) {
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    var html = '<span style="color: #1E9FFF; cursor: pointer; display: block;">';
                    html += '<p><span>';
                    html += res.data[i].content;
                    html += '</span></p></span>';
                    $("#out_notice").append(html);
                }
            }
            $(".fa-home").parent().parent().addClass("layui-this");
            //播放公告
            playAnnouncement(3000);
        });
    });
    
    function playAnnouncement(interval) {
        var index = 0;
        var $announcement = $('.home-tips-container>span');
        //自动轮换
        setInterval(function () {
            index++;    //下标更新
            if (index >= $announcement.length) {
                index = 0;
            }
            $announcement.eq(index).stop(true, true).fadeIn().siblings('span').fadeOut();  //下标对应的图片显示，同辈元素隐藏
        }, interval);
    }
});

function classifyList(id){
    	layer.msg('功能要自己写');
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



