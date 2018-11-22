<%--
  轮播
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="slate_gray" >
    <div class="container">
        <div class="row header_news_panel">
            <!-- Tab panes -->
            <div class="col-sm-8 tab-content tab-content_mob-p0">
                <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <a href="${pageContext.request.contextPath}/news?id=${BIGNEWS.id}" id="bigNewsHref">
                        <img src="${pageContext.request.contextPath}/static/img/content/slide1.jpg" height="466" width="791" alt="main img" class="tab-pane__img">
                        <div class=" tab-pane__block">
                            <p class="tab-pane__text" style="color: #ffffff; font-size: 20px; font-weight: bolder;"></p>
                        </div>
                    </a>
                </div>
            </div>
            <!-- END Tab panes -->

            <!-- Nav tabs -->
            <div class="col-sm-4 news-tabs" style="color: black !important;">
                <p class="news-tabs__title h2">要闻</p>
                <ul class="news-tabs__nav nav nav-tabs shadow_text" role="tablist" >
                    <c:forEach items="${SLIDESHOWNEWS}" var="news" varStatus="index" >
                        <c:choose>
                            <%--  index.count 为下标  从1开始 第一个为已选 --%>
                            <c:when test="${index.count == 1}" >
                                <li role="presentation" class="active" >
                                    <a href="#home" role="tab" data-toggle="tab" id="slideNews${news.id}">
                                        <span class="contentNews"  >${news.name}</span>
                                        <span class="time author">
                                                    ${news.user.username}&nbsp;于
                                                    <fmt:formatDate value="${news.updated}" pattern="yyyy年MM月dd日 HH:mm:ss" />更新
                                                </span>
                                    </a>
                                    <input type="hidden" class="picture" value="${news.picture}"/>
                                    <input type="hidden" class="bigNewsId" value="${news.id}"/>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li role="presentation" >
                                    <a href="#home" role="tab" data-toggle="tab" id="slideNews${news.id}">
                                        <span class="contentNews">${news.name}</span>
                                        <span class="time" >
                                                    ${news.user.username}&nbsp;于
                                                    <fmt:formatDate value="${news.updated}" pattern="yyyy年MM月dd日 HH:mm:ss" />更新
                                        </span>
                                    </a>
                                    <input type="hidden" class="picture" value="${news.picture}"/>
                                    <input type="hidden" class="bigNewsId" value="${news.id}"/>
                                </li>

                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </ul>
            </div>
            <!-- END Nav tabs -->
        </div>
    </div>
</div>
