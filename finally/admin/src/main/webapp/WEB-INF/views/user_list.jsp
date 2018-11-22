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
    <title>news | 用户列表</title>
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
                <div class="box box-info box-info-search" style="display: none">
                    <div class="box-header with-border">
                        <h3 class="box-title">高级搜索</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->
                    <div class="box-body">
                        <div class="row form-horizontal">
                        <div class="col-xs-12 col-md-3">
                            <div class="form-group">
                                <label for="username" class="col-xs-12 col-sm-4 control-label">用户名</label>
                                <div class="col-xs-10 col-sm-8">
                                    <input class="form-control" id="username" placeholder="用户名">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-3">
                            <div class="form-group">
                                <label for="email" class="col-xs-12 col-sm-4 control-label">邮箱</label>
                                <div class="col-xs-10 col-sm-8">
                                    <input class="form-control" id="email" placeholder="邮箱">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-3">
                            <div class="form-group">
                                <label for="phone" class="col-xs-12 col-sm-4 control-label">手机号</label>
                                <div class="col-xs-10 col-sm-8">
                                    <input class="form-control" id="phone" placeholder="手机号">
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-info pull-right" onclick="search()">搜索</button>
                    </div>
                    <!-- /.box-footer -->
                </div>
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
                            <h3 class="box-title">用户列表</h3>
                        </div>
                            <div class="box-body">
                                <button class="btn btn-default btn-sm" onclick="App.deleteMulti('${pageContext.request.contextPath}/user/delete')"><i class="fa fa-trash-o"></i>&nbsp;删除</button>&nbsp;
                                <button class="btn btn-default btn-sm" onclick="depol()"><i class="fa fa-download"></i>&nbsp;导入</button>&nbsp;
                                <a href="${pageContext.request.contextPath}/excel/down_excel?name=user" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i>&nbsp;导出</a>&nbsp;
                                <button class="btn btn-primary btn-sm" onclick="$('.box-info-search').css('display')=='none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa fa-search"></i>&nbsp;搜索</button>
                            </div>
                            <form id="upFileForm" action="${pageContext.request.contextPath}/excel/user" method="post" enctype="multipart/form-data">
                                <input type="file" name="file" id="upFile" style="display:none;" accept=".xls"/>
                            </form>
                            <script>
                                function depol(){
                                    $("#upFile").bind("change",function () {
                                        $("#upFileForm").submit();
                                    });
                                    $("#upFile").click();
                                }
                            </script>
                            <!-- /.box-header -->
                            <div class="box-body table-responsive no-padding">
                                <table class="table table-hover" id="table_id_example">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" class="minimal iCheck_master"></th>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>电话</th>
                                        <th>邮箱</th>
                                        <th>创建时间</th>
                                        <th>更新时间</th>
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
                    return '<input id="'+row.id+'" type="checkbox" class="minimal">'
                }},
                { "data": "id" },
                { "data": "username" },
                { "data": "phone" },
                { "data": "email" },
                { "data": function ( row, type, val, meta ){
                    return DateTime.format(row.created,"yyyy-MM-dd");
                }},
                { "data": function ( row, type, val, meta ){
                        return DateTime.format(row.updated,"yyyy-MM-dd");
                }},
                {"data":function ( row, type, val, meta ){

                        var statusurl="${pageContext.request.contextPath}/user/status?id="+row.id +"&isdelete="+row.isdelete;
                        var deleteurl="${pageContext.request.contextPath}/user/delete?id="+row.id;
                        var btnClass =row.isdelete == 1 ? "btn-success" : "btn-danger";
                        var fontStatus = row.isdelete == 1 ? "正常" : "冻结";
                        return '<button class="btn '+btnClass+' btn-sm"  onclick="App.changeStatus(\''+statusurl+'\')">'+fontStatus+'</button>'+
                            '<button class="btn btn-danger btn-sm" onclick="App.singleDelete(\''+deleteurl+'\')">删除</button>';
                }}
            ];
        _dataTable=App.initDataTables("${pageContext.request.contextPath}/user/page",columns);

    });
    function search() {
        var username=$("#username").val();
        var phone=$("#phone").val();
        var email=$("#email").val();
        var param={
            "username":username,
            "phone":phone,
            "email":email
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
</body>
</html>
