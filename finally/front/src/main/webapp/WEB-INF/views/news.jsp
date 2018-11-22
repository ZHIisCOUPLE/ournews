<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta content="" name="description">
    <meta content="" name="keywords">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">
    <meta content="telephone=no" name="format-detection">
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <title>OurNwes</title>

    <style type="text/css">
        .share-box-fixed {
            top: 0;
            z-index: 20;
        }
        .share-box{
            left: 81%;
            position: fixed;
        }
        .share-box-news{
            left: 61%;
            position: fixed;
            /*right_sidebar*/
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header id="header" class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3 col-md-2"  style="margin-top: -10px !important;">
                        <div class="wrap-logo">
                            <a href="${pageContext.request.contextPath}/" ><img src="${pageContext.request.contextPath}/static/img/png-sprite/logo.png"></a>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-md-offset-3 col-sm-7 col-md-7 hidden-xs">
                        <div class="col-xs-offset-5 col-sm-offset-4 col-xs-3 col-sm-4">
                            <div class="weather" style=" width: 200px !important; margin: 0% 0% 0% 3% !important;">
                                <div class="weather__city" >
                                    <em style=" font-size: 18px !important;">
                                        <c:choose>
                                        <c:when test="${USER_LOGIN != null}" >
                                        欢迎登陆，<span style="color: gold">${USER_LOGIN.username}</span></em>
                                    <div class="weather__city__list">
                                        <ul>
                                            <li id="userinfo">
                                                <a href="#">个人资料</a>
                                            </li>
                                            <li id="logout">
                                                <a href="#">注销</a>

                                            </li>
                                            <li id="addnews">
                                                <a href="#">发布新闻</a>


                                            </li>
                                        </ul>
                                    </div>
                                    </c:when>
                                    <c:otherwise>
                                        欢迎来到&nbsp;<span style="color: gold">OURNEWS</span>
                                        <div class="weather__city__list">
                                            <ul>
                                                <li id="login">
                                                    <a href="#" >登录</a>
                                                        <%-- ${pageContext.request.contextPath} 为上下文路径 --%>

                                                </li>
                                                <li id="toRegister">
                                                    <a href="#" >注册</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:otherwise>
                                    </c:choose>
                                    </em>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-offset-1 col-sm-offset-1 col-xs-3 col-sm-3  hidden-xs hidden-sm" >
                            <p class="exchange__name" style="font-size: 16px !important; margin-top: 6px!important;">北京时间：</p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wsmenucontent overlapblackbg"></div>
        <div class="wsmenuexpandermain slideRight">
            <a id="navToggle" class="animated-arrow slideLeft">
                <span></span>
            </a>
        </div>
        <div class="header_down">
            <div class="container">
                <div class="wrapper clearfix bigmegamenu">
                    <!--Main Menu HTML Code-->
                    <nav class="wsmenu slideLeft clearfix">
                        <ul class="mobile-sub wsmenu-list">
                            <li ${NEWSTYPE_BY_ID == null ? 'class="active"' : ''}>
                                <span class="wsmenu-click"></span>
                                <a href="${pageContext.request.contextPath}/">推荐</a>
                            </li>
                            <c:forEach  items="${NEWSTYPES}" var="newsType" varStatus="index" >
                                <c:if test="${index.count < 8}" >
                                    <li ${NEWSTYPE_BY_ID.id == newsType.id ? 'class="active"' : ''}>
                                        <span class="wsmenu-click"></span>
                                        <a href="${pageContext.request.contextPath}/toTypeNews?id=${newsType.id}">${newsType.name}</a>
                                    </li>

                                </c:if>
                            </c:forEach>
                            <li>
                                <span class="wsmenu-click"></span>
                                <a href="">其他
                                    <span class="arrow"></span>
                                </a>
                                <ul class="wsmenu-submenu " style="width: 8%; ">
                                    <c:forEach items="${NEWSTYPES}" var="other" varStatus="index">
                                        <c:if test="${index.count > 7}" >
                                            <li style="height: 40px" >
                                                <a style="text-align: center; height: 40px!important; line-height: 30px" href="${pageContext.request.contextPath}/toTypeNews?id=${other.id}">${other.name}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>


                            <li class="navbar-right hidden-xs hidden-sm hidden-md" style="height: 0;" >
                                <form class="navbar-form" role="search" method="post"  action="${pageContext.request.contextPath}/seeknews" id="seeknews" style="margin-top: 0!important;padding: 0!important;">
                                    <div class="form-group">
                                        <input type="text"  class="form-control"  placeholder="输入你感兴趣的新闻" name="seek" id="seek" maxlength="10">
                                    </div>
                                    <button type="submit" class="btn btn-search" >
                                        <i class="icon-search"></i>
                                        <br/>搜索
                                    </button>
                                </form>
                            </li>



                        </ul>
                    </nav>
                    <!--Menu HTML Code-->
                </div>
            </div>
        </div>
    </header>
    <!-- END header -->
    <!-- top news  主要修改的内容-->
    <section>
        <!-- top news -->
        <div class="wrapper">

            <article class="container articles">
                <div class="row">

                    <div class="col-sm-3 col-md-2 hidden-xs hidden-sm hidden-md share-box" >
                        <div class="news-tabs">

                            <ul class="news-tabs__nav nav nav-tabs shadow_text">
                                <%-- 待用   ${newsinfo.id==news.id?"class='active'":""}--%>
                                <li role="presentation">
                                    <a href="${pageContext.request.contextPath}/">
                                        <p class="news-tabs__title h2" style="color: #ea9836;">OURNEWS自媒体</p>
                                        广告招租，联系我们
                                    </a>
                                </li>
                            </ul>
                            <ul class="news-tabs__nav nav nav-tabs shadow_text">
                                <%-- 待用   ${newsinfo.id==news.id?"class='active'":""}--%>
                                <li role="presentation">
                                    <a href="${pageContext.request.contextPath}/">
                                        <p class="news-tabs__title h2" style="color: #ea9836;">OURNEWS自媒体</p>
                                        广告招租，联系我们
                                    </a>
                                </li>
                            </ul>
                            <ul class="news-tabs__nav nav nav-tabs shadow_text">
                                <%-- 待用   ${newsinfo.id==news.id?"class='active'":""}--%>
                                <li role="presentation">
                                    <a href="${pageContext.request.contextPath}/">
                                        <p class="news-tabs__title h2" style="color: #ea9836;">OURNEWS自媒体</p>
                                        广告招租，联系我们
                                    </a>
                                </li>
                            </ul>

                            <ul class="news-tabs__nav nav nav-tabs shadow_text">
                                <%-- 待用   ${newsinfo.id==news.id?"class='active'":""}--%>
                                <li role="presentation">
                                    <a href="${pageContext.request.contextPath}/">
                                        <p class="news-tabs__title h2" style="color: #ea9836;">OURNEWS自媒体</p>
                                        广告招租，联系我们
                                    </a>
                                </li>
                            </ul>

                            <ul class="news-tabs__nav nav nav-tabs shadow_text">
                                <%-- 待用   ${newsinfo.id==news.id?"class='active'":""}--%>
                                <li role="presentation">
                                    <a href="${pageContext.request.contextPath}/">
                                        <p class="news-tabs__title h2" style="color: #ea9836;">OURNEWS自媒体</p>
                                        广告招租，联系我们
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-sm-8 col-md-8 article_text">
                        <div class="current" style="color: #222; font-size: 16px; width: 700px; text-align: left;">
                            <h1 class="text-center yel_line current__title" style="text-align: left!important">${newsinfo.name}</h1>
                            ${newsinfo.detail}

                        </div>
                    </div>
                    <div class="col-sm-3 col-md-2 hidden-xs hidden-sm  hidden-md   share-box-news" >
                        <div class="news-tabs">

                            <p class="news-tabs__title h2">
                                <img src="${pageContext.request.contextPath}/static/img/content/circle.png" style="border: 1px solid #ea9836; border-radius:50% ">
                                ${newsinfo.user.username == null ? 'Admin' : newsinfo.user.username}
                            </p>
                            <ul class="news-tabs__nav nav nav-tabs shadow_text" >
                                <li role="presentation">
                                    <a href="#" >

                                        <span class="time"><fmt:formatDate value="${newsinfo.created}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
                                        <p class="news-tabs__title" style="color: #ea9836;">${newsinfo.name}</p>
                                    </a>
                                </li>
                            </ul>
                            <%--该小编还含有的新闻--%>
                            <c:forEach items="${allnews}" var="news" varStatus="sss">
                                <c:if test="${sss.count<6}">
                                <ul class="news-tabs__nav nav nav-tabs shadow_text">
     <%-- 待用   ${newsinfo.id==news.id?"class='active'":""}--%>
                                    <li role="presentation">
                                        <a href="${pageContext.request.contextPath}/news?id=${news.id}">
                                            <span class="time"><fmt:formatDate value="${news.created}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
                                                ${news.name}
                                        </a>
                                    </li>
                                </ul>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>



                    <div class="col-sm-12 col-md-12 col-lg-12  comments ">
                        <div class="tags">
                            <p>Tags:</p>
                            <ul >
                                <li>
                                    <a href="#" title="World" class="font">${newsinfo.type}</a>
                                </li>

                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-9 col-md-8 col-lg-6  comments">
                        <p class="comments__title">用户评论</p>

                        <div class="comments__form">
                            <form action="${pageContext.request.contextPath}/addComment" method="POST">
                                <%--//新闻ID--%>
                                <input type="hidden" name="id" value="${newsinfo.id}" />

                                <div class="form-group">
                                    <textarea name="text" id="input" class="form-control" rows="7" required="required"></textarea>
                                </div>
                                <button type="submit" class="btn btn-info btn-lg">添加评论</button>
                            </form>
                        </div>
                        <br/>
            <%--评论--%>
                        <c:forEach items="${newsinfo.comments}" var="comme">
                            <div class="comments__media">
                                <div class="media-middle">
                                    <i class="media-object" style="background-image: url('${pageContext.request.contextPath}/static/img/content/circle.png')"></i>
                                    <div class="comm_info">
                                        <h4 class="media-heading">${comme.user.username}</h4>


                                        <span class="time"><fmt:formatDate value="${comme.created}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
                                        <div class="row" style="padding-left: 300px">
                          <%--点赞--%>

                                            <button>
                                               <span  id="${comme.id}" class="glyphicon glyphicon-thumbs-up" style="font-size: 25px"></span>
                                                <%--glyphicon glyphicon-thumbs-up 点赞--%>
                                                <%--glyphicon glyphicon-thumbs-down 取消点赞--%>
                                            </button>
                                            <%--<input type="hidden" id="commeID" value="${comme.id}"/>--%>
                                            <span id="like${comme.id}">${comme.likes}</span>

                                        </div>
                                    </div>
                                </div>
                                <p  style="margin-left: 1% !important;">${comme.commentDetails}</p>
                            </div>
                        </c:forEach>




                    </div>
                </div>
            </article>
        </div>
        <!-- /container-->
    </section>
    <!-- /top news -->
    <%-- Footer --%>

    <jsp:include page="pages/javascript.jsp"/>
    <jsp:include page="pages/footer.jsp" />
    <!-- END Footer -->

</body>
<%-- news   --%>
<script type="text/javascript" >
    window.onscroll =()=>{
        if(window.scrollY >= 108){
            $(".share-box").addClass("share-box-fixed");
            $(".share-box-news").addClass("share-box-fixed");

        }else if(window.scrollY < 108){
            $(".share-box").removeClass("share-box-fixed");
            $(".share-box-news").removeClass("share-box-fixed");
        }
    }
</script>


<%-- news  end  --%>
</html>
<%--点赞--%>
<script>
    $(function () {
        //根据属性拿到所有的点赞标签
        $(".glyphicon.glyphicon-thumbs-up").each(function () {
            $(this).click(
                function () {

                    var mm = $(this).attr("style");
                    if (mm == "font-size: 25px") {

                        <%--评论ID--%>
                        var commeID = $(this).attr("id");
                        <%--点赞数量--%>
                        var likesFrest =$("#like"+commeID).text();
                        <%--点赞--%>
                        $("#like"+commeID).text(likesFrest*1+1);

                        var likeslast =$("#like"+commeID).text();

                        $(this).attr("style","font-size: 25px;color: red");
                        $("#like"+commeID).attr("style","color: red");

                        aj(commeID,likeslast);



                        //
                    }else {
                        <%--评论ID--%>
                        var commeID = $(this).attr("id");
                        <%--点赞数量--%>
                        var likesFrest =$("#like"+commeID).text();
                        <%--取消点赞--%>
                        $("#like"+commeID).text(likesFrest*1-1);

                        var likeslast =$("#like"+commeID).text();

                        $(this).attr("style","font-size: 25px");
                        $("#like"+commeID).attr("style","");

                        aj(commeID,likeslast);

                    }
                }
            )
        })
    })
    function aj(commeID,likes) {
       var cID=commeID==null?1:commeID;
       var like= likes==null?1:likes;

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/comNumber",
            data:{
                commeID:cID,
                likes:like
            },
            dataType: "text",//数据格式
            success: function (data) {

            }
        });
    }
</script>

