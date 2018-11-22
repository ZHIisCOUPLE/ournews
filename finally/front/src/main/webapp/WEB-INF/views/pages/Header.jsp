<%-- boby 文章头部--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                        <li class="navbar-right hidden-xs hidden-sm hidden-md">
                            <form class="navbar-form" role="search" method="post" action="${pageContext.request.contextPath}/seeknews" id="seeknews">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="输入你感兴趣的新闻" name="seek" id="seek" maxlength="10">
                                </div>
                                <button type="submit" class="btn btn-search">
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
