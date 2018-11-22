<%--
  Created by IntelliJ IDEA.
  User: 黄建智
  Date: 2018/10/12
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <jsp:include page="../back/headstyle.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body table-responsive">
    <table>
        <table class="table table-hover">
            <tbody>
            <tr>
                <th>标题</th>
                <td>${newsInfo.name}</td>
            </tr>
            <tr>
                <th>详细内容</th>
                <td>
                    <div  style="overflow: scroll;height: 500px;width: 500px">${newsInfo.detail}</div>

                </td>
            </tr>
            </tbody>
        </table>
    </table>
</div>
<jsp:include page="../back/footstyle.jsp"/>
</body>
</html>