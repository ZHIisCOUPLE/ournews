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
    <title>OurNwes</title>
</head>
<body>
    <!-- Header -->
    <jsp:include page="pages/Header.jsp" />
    <!-- END header -->
    <!-- 轮播 -->
    <jsp:include page="pages/navigation.jsp" />
    <%--<!-- END 轮播 -->--%>
    <!-- top news  主要修改的内容-->
    <section>
        <!-- top news -->
        <div class="wrap wrap_gray pt20">
            <div class="container">
                <%--首页大新闻 以及右侧小新闻--%>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="thumbnail thumbnail_big">
                            <a href="${pageContext.request.contextPath}/news?id=${BIGNEWS.id}">
                                <img src="${pageContext.request.contextPath}/picShow?fileName=${BIGNEWS.picture}" height="350" width="560"  alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">
                                        <c:forEach items="${ALLNEWSTYPE}" var="newstype">
                                            ${newstype.name == BIGNEWS.type ? newstype.name : ''}
                                        </c:forEach>
                                    </p>
                                    <a href="${pageContext.request.contextPath}/news?id=${BIGNEWS.id}"  class="news__head" >
                                        ${BIGNEWS.name}
                                    </a>
                                </div>

                                <div class="posted">
                                    <span class="posted__date"><fmt:formatDate value="${BIGNEWS.updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                    <ul class="posted__icon">
                                        <li>
                                            <span>

                                                 <a href="${pageContext.request.contextPath}/news?id=${minNewsinfo.id}">
                                                    <i class="icon-comment-empty"></i>
                                                    <%--评论数量--%>
                                                    ${BIGNEWS.commentCount}
                                                </a>
                                            </span>

                                        </li>
                                        <li>
                                            <span>
                                                <a href="${pageContext.request.contextPath}/news?id=${minNewsinfo.id}">
                                                    <i class="icon-eye"></i>
                                                    <%--阅读/浏览数量--%>
                                                    ${BIGNEWS.readCount}
                                                </a>
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/" style="font-size: 20px;" >
                            <div style="width: 100%; height: 80px; margin-top: 30px; color: #ffffff; text-align: center; background-color: #303d4a; border-radius: 2% 2%; line-height: 32px ">
                                <img src="${pageContext.request.contextPath}/static/img/png-sprite/logo.png" ><br>
                                广告位-------招租
                            </div>
                        </a>
                    </div>
                    <c:forEach items="${RIGHTMINNEWS}" var="minNewsinfo">
                        <div class="col-sm-3">
                        <div class="thumbnail thumbnail_small">
                            <a href="${pageContext.request.contextPath}/news?id=${minNewsinfo.id}" class="thumbnail__link">
                                <img src="${pageContext.request.contextPath}/picShow?fileName=${minNewsinfo.picture}" style="width: 270px; height: 160px;" alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">
                                        <c:forEach items="${ALLNEWSTYPE}" var="newstype">
                                            ${newstype.name == minNewsinfo.type ? newstype.name : ''}
                                        </c:forEach>
                                    </p>
                                    <a href="${pageContext.request.contextPath}/news?id=${minNewsinfo.id}" class="news__link">
                                        <p class="news__text">
                                                ${minNewsinfo.name}
                                        </p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date"><fmt:formatDate value="${minNewsinfo.updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                    <ul class="posted__icon">
                                        <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>
                                                    <%--评论数量--%>
                                                    ${minNewsinfo.commentCount}
                                            </span>
                                        </li>
                                        <li>
                                                <span>
                                                <i class="icon-eye"></i>
                                                    <%--阅读/浏览数量--%>
                                                    ${minNewsinfo.readCount}
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>

                    <%--首页大新闻下面的展示新闻--%>
                <div class="row">
                    <c:forEach items="${FOOTMINNEWS}" var="showNewsinfo" varStatus="index">
                        <c:if test="${index.count < 13}" >
                            <div class="col-sm-3">
                                <div class="thumbnail thumbnail_small">
                                    <a href="${pageContext.request.contextPath}/news?id=${showNewsinfo.id}" class="thumbnail__link">
                                        <img src="${pageContext.request.contextPath}/picShow?fileName=${showNewsinfo.picture}" style="width: 270px; height: 160px;" alt="News">
                                    </a>
                                    <div class="caption thumbnail__caption">
                                        <div class="news caption__news">
                                            <p class="news__category yellow-line">
                                                <c:forEach items="${ALLNEWSTYPE}" var="newstype">
                                                    ${newstype.name == showNewsinfo.type ? newstype.name : ''}
                                                </c:forEach>
                                            </p>
                                            <a href="${pageContext.request.contextPath}/news?id=${showNewsinfo.id}" class="news__link">
                                                <p class="news__text">
                                                        ${showNewsinfo.name}
                                                </p>
                                            </a>
                                        </div>
                                        <div class="posted">
                                            <span class="posted__date"><fmt:formatDate value="${showNewsinfo.updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                            <ul class="posted__icon">
                                                <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>
                                                    <%--评论数量--%>
                                                    ${showNewsinfo.commentCount}
                                            </span>
                                                </li>
                                                <li>
                                                <span>
                                                <i class="icon-eye"></i>
                                                    <%--阅读/浏览数量--%>
                                                    ${showNewsinfo.readCount}
                                            </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- /container-->
    </section>
    <!-- /top news -->
    <%-- Footer --%>
    <jsp:include page="pages/footer.jsp" />
    <!-- END Footer -->
    <jsp:include page="pages/javascript.jsp" />

</body>
<%--主页 --%>
<script type="text/javascript" >

    var timer = null;

    $(function () {
        var slideLi =$("ul[class='news-tabs__nav nav nav-tabs shadow_text'] li");
        //获取滚动新闻的内容放到左边大框
        var oneText = slideLi.first().find("a").find("span[class='contentNews']").html();
        var imgUrl = slideLi.first().find("input[class='picture']").val();
        var newId = slideLi.first().find("input[class='bigNewsId']").val();
        $("img[class='tab-pane__img']").attr("src","${pageContext.request.contextPath}/picShow?fileName="+imgUrl);
        $("#bigNewsHref").attr("href", "${pageContext.request.contextPath}/news?id="+newId);
        $("p[class='tab-pane__text']").html(oneText);
        //=============================
        slideLi.each(function () {
            var tagLi = $(this);
            //第一个function表示悬停做的事情 第二个我表示离开后做的事情
            tagLi.find("a").hover(function () {
                clearInterval(timer);
                var oneText = $(this).find("span[class='contentNews']").html();
                var imgUrl = tagLi.find("input[class='picture']").val();
                var newId = tagLi.find("input[class='bigNewsId']").val();
                $("img[class='tab-pane__img']").attr("src","${pageContext.request.contextPath}/picShow?fileName="+imgUrl);
                $("#bigNewsHref").attr("href", "${pageContext.request.contextPath}/news?id="+newId);
                $("p[class='tab-pane__text']").html(oneText);
                tagLi.addClass("active").siblings().removeClass("active");
                $(this).siblings().css("color","red");
                //i为轮播序号  tagLi.index() 表示轮播到哪个li
                i = tagLi.index();
            }, function () {
                slideNews();
            });
        });
        //=============================
        //轮播
        var i = 0;
        function slideNews() {
            timer = setInterval(function() {
                i++;
                if (i == 4){
                    i = 0;
                }
                var oneText = slideLi.eq(i).find("span[class='contentNews']").html();
                var imgUrl = slideLi.eq(i).find("input[class='picture']").val();
                var newId = slideLi.eq(i).find("input[class='bigNewsId']").val();
                slideLi.eq(i).addClass("active").siblings().removeClass("active");
                $("img[class='tab-pane__img']").attr("src","${pageContext.request.contextPath}/picShow?fileName="+imgUrl);
                $("#bigNewsHref").attr("href", "${pageContext.request.contextPath}/news?id="+newId);
                $("p[class='tab-pane__text']").html(oneText);
            },4000);
        }
        slideNews();


    })
</script>
<%--主页 end 。。。 --%>
</html>