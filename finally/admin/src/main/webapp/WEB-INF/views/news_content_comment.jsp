<%--
  Created by IntelliJ IDEA.
  User: 黄建智
  Date: 2018/10/9
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>
    <title>news | 评论列表</title>
    <jsp:include page="../back/headstyle.jsp"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/skins/_all-skins.min.css">--%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../back/head.jsp"/>
    <jsp:include page="../back/left.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户信息
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">评论列表</h3>
                        </div>
                        <div class="box-body">
                            <button class="btn btn-default btn-sm" onclick="App.deleteMulti('${pageContext.request.contextPath}/user/delete')"><i class="fa fa-trash-o"></i>&nbsp;删除</button>&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i>&nbsp;导入</a>&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i>&nbsp;导出</a>&nbsp;
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover" id="table_id_example">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal iCheck_master"></th>
                                    <th>评论内容</th>
                                    <th>发表时间</th>
                                    <th>点赞量</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../back/copyright.jsp"/>
</div>
<jsp:include page="../back/footstyle.jsp"/>
<sys:model/>
<script>
    var _dataTable;
    $(function () {
        var columns= [
            {"data":function ( row, type, val, meta ){
                    return '<input id="'+row.id+'" type="checkbox" class="minimal">';
            }},
            { "data": function ( row, type, val, meta ){
                    return '<p style="width:550px;overflow:hidden;text-overflow:ellipsis;">'+row.commentDetails+'</p>';
            }},
            { "data": function ( row, type, val, meta ){
                    return DateTime.format(row.created,"yyyy-MM-dd");
            }},
            { "data": "likes" },
            {"data":function ( row, type, val, meta ){
                    var deleteurl="${pageContext.request.contextPath}/comment/delete?id="+row.id;
                    return '<button class="btn btn-danger btn-sm" onclick="App.singleDelete(\''+deleteurl+'\')">删除</button>';
                }}
        ];
        _dataTable=App.initDataTables("${pageContext.request.contextPath}/comment/page?newInfo_id=${idModel}",columns);
    });
</script>
</body>
</html>
