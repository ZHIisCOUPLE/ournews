<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

    <title>${NEWSTYPE_BY_ID.name}新闻</title>
</head>
<body>
<!-- Header -->
<jsp:include page="../pages/Header.jsp" />
<!-- END header -->
<!-- header slider -->
<section>
    <!-- top news -->
    <c:if test="${not empty BIGNEWSINFOS || not empty MINNEWSINFOS}">
        <div class="wrap wrap_white">
            <div class="container title">
                <h2 class="title__h1 underscore">
                        ${MINNEWSINFOS[0].type}
                </h2>
            </div>
        </div>
        <%--${TAGNEWSINFO}--%>
        <div class="wrap wrap_gray pt20" >
            <div class="container">
                <div class="row">
                    <%--大新闻为空就不放出来--%>
                    <c:if test="${not empty BIGNEWSINFOS }">
                        <div class="col-sm-6">
                            <div class="thumbnail thumbnail_big">
                                <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[0].id}">
                                    <img src="${pageContext.request.contextPath}/picShow?fileName=${BIGNEWSINFOS[0].picture}" style=" height:350px; width:560px" alt="News">
                                </a>
                                <div class="caption thumbnail__caption">
                                    <div class="news caption__news">
                                        <p class="news__category yellow-line">${BIGNEWSINFOS[0].type}</p>
                                        <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[0].id}"  class="news__head" >${BIGNEWSINFOS[0].name}</a>
                                    </div>
                                    <div class="posted">
                                        <span class="posted__date"><fmt:formatDate value="${BIGNEWSINFOS[0].updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                        <ul class="posted__icon">
                                            <li>
                                            <span>

                                                 <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[0].id}">
                                                    <i class="icon-comment-empty"></i>
                                                    <%--评论数量--%>
                                                    ${BIGNEWSINFOS[0].commentCount}
                                                </a>
                                            </span>

                                    </li>
                                    <li>
                                            <span>
                                                <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[0].id}">
                                                    <i class="icon-eye"></i>
                                                    <%--阅读/浏览数量--%>
                                                    ${BIGNEWSINFOS[0].readCount}
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
                    </c:if>
                    <c:if test="${not empty MINNEWSINFOS }">
                        <c:forEach items="${MINNEWSINFOS}" var="minNews"  varStatus="index">
                            <c:if test="${index.count < 9}">
                            <div class="col-sm-3">
                                <div class="thumbnail thumbnail_small">
                                    <a href="${pageContext.request.contextPath}/news?id=${minNews.id}" class="thumbnail__link">
                                        <img src="${pageContext.request.contextPath}/picShow?fileName=${minNews.picture}" style="width: 270px; height: 160px;" alt="News">
                                    </a>
                                    <div class="caption thumbnail__caption">
                                        <div class="news caption__news">
                                            <p class="news__category yellow-line">${minNews.type}</p>
                                            <a href="${pageContext.request.contextPath}/news?id=${minNews.id}" class="news__link">
                                                <p class="news__text">
                                                        ${minNews.name}
                                                </p>
                                            </a>
                                        </div>
                                        <div class="posted">
                                            <span class="posted__date"><fmt:formatDate value="${minNews.updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                            <ul class="posted__icon">
                                                <li>
                                                                <span>
                                                                <i class="icon-comment-empty"></i>
                                                                    <%--评论数量--%>
                                                                    ${minNews.commentCount}
                                                                </span>
                                                </li>
                                                <li>
                                                                <span>
                                                                <i class="icon-eye"></i>
                                                                    <%--阅读/浏览数量--%>
                                                                    ${minNews.readCount}
                                                                </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        </c:forEach>
                        <div class="col-sm-6 p0">
                            <c:forEach items="${MINNEWSINFOS}" var="minNews"  varStatus="index">
                                <c:if test="${index.count > 8 && index.count < 12}">
                                    <div class="col-sm-6">
                                        <div class="thumbnail thumbnail_small">
                                            <a href="${pageContext.request.contextPath}/news?id=${minNews.id}" class="thumbnail__link">
                                                <img src="${pageContext.request.contextPath}/picShow?fileName=${minNews.picture}" height="153" width="270" alt="News">
                                            </a>
                                            <div class="caption thumbnail__caption">
                                                <div class="news caption__news">
                                                    <p class="news__category yellow-line">${minNews.type}</p>
                                                    <a href="${pageContext.request.contextPath}/news?id=${minNews.id}" class="news__link">
                                                        <p class="news__text">
                                                                ${minNews.name}
                                                        </p>
                                                    </a>
                                                </div>
                                                <div class="posted">
                                                    <span class="posted__date"><fmt:formatDate value="${minNews.updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                                    <ul class="posted__icon">
                                                        <li>
                                                            <span>
                                                            <i class="icon-comment-empty"></i>
                                                                <%--评论数量--%>
                                                                ${minNews.commentCount}
                                                            </span>
                                                        </li>
                                                        <li>
                                                            <span>
                                                            <i class="icon-eye"></i>
                                                                <%--阅读/浏览数量--%>
                                                                ${minNews.readCount}
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
                    </c:if>
                    <%--大新闻为空就不放出来--%>
                    <c:if test="${not empty BIGNEWSINFOS[1] }">
                        <div class="col-sm-6">
                            <div class="thumbnail thumbnail_big">
                                <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[1].id}">
                                    <img src="${pageContext.request.contextPath}/picShow?fileName=${BIGNEWSINFOS[1].picture}"  height="350" width="560"  alt="News">
                                </a>
                                <div class="caption thumbnail__caption">
                                    <div class="news caption__news">
                                        <p class="news__category yellow-line">${BIGNEWSINFOS[0].type}</p>
                                        <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[1].id}"  class="news__head" >${BIGNEWSINFOS[1].name}</a>

                            </div>
                            <div class="posted">
                                <span class="posted__date"><fmt:formatDate value="${BIGNEWSINFOS[1].updated}" pattern="MM月dd日 HH:mm:ss" /></span>
                                <ul class="posted__icon">
                                    <li>
                                            <span>

                                                 <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[1].id}">
                                                    <i class="icon-comment-empty"></i>
                                                    <%--评论数量--%>
                                                    ${BIGNEWSINFOS[1].commentCount}
                                                </a>
                                            </span>

                                    </li>
                                    <li>
                                            <span>
                                                <a href="${pageContext.request.contextPath}/news?id=${BIGNEWSINFOS[1].id}">
                                                    <i class="icon-eye"></i>
                                                    <%--阅读/浏览数量--%>
                                                    ${BIGNEWSINFOS[1].readCount}
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
                    </c:if>
                </div>
            </div>
        </div>
        <!-- /container-->
    </c:if>


</section>

<%--404页面--%>
<c:if test="${empty BIGNEWSINFOS && empty MINNEWSINFOS}">
    <jsp:include page="../pages/404.jsp"></jsp:include>
</c:if>
<jsp:include page="../pages/footer.jsp"/>
<!-- END Footer -->

<jsp:include page="../pages/javascript.jsp" />
</body>
</html>