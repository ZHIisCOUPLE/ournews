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


    <title>搜索新闻</title>

</head>

<body>
<div class="wrapper-sticky-footer">
    <div class="content-sticky">
        <!-- Header -->
        <jsp:include page="../pages/Header.jsp"></jsp:include>
        <!-- END header -->
        <section >
            <!-- top news -->
            <c:if test="${not empty  infos}">

                <%--${TAGNEWSINFO}--%>
                <div class="wrap wrap_gray pt20"  >
                    <div class="container">
                        <div class="row">

                            <c:if test="${not empty infos }">
                                <c:forEach items="${infos}" var="minNews"  varStatus="index">

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

                        </div>
                    </div>

                </div>
                <!-- /container-->
            </c:if>


        </section>
        <c:if test="${empty infos}">
            <jsp:include page="../pages/404.jsp"></jsp:include>
        </c:if>
    </div>

    <jsp:include page="../pages/javascript.jsp"/>
    <!-- Footer -->
   <jsp:include page="../pages/footer.jsp"></jsp:include>
</div>
<!-- END Footer -->


</body>

</html>

