<%--
  Created by IntelliJ IDEA.
  User: 黄建智
  Date: 2018/10/12
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <title>news | 新闻管理</title>
    <jsp:include page="../back/headstyle.jsp"/>
    <%--富文本编辑器--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/wangEditor/wangEditor.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/bower_components/select2/dist/css/select2.min.css">
    <%--dropzone--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/dropzone/min/dropzone.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/plugins/dropzone/min/basic.min.css">
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
                新闻管理
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${NewsInfo.id==null?"新增":"编辑"}新闻</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal"  method="post" action="${pageContext.request.contextPath}/content/save" modelAttribute="newsInfo">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-8">
                                        <form:input cssClass="form-control required" path="name" placeholder="标题"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">类型</label>
                                    <div class="col-sm-8">
                                        <form:select path="type" class="form-control select2">
                                            <c:if test="${newsInfo.type !=null}">
                                            <form:option value="${newsInfo.type}" label="${newsInfo.type}"/>
                                            </c:if>
                                            <c:if test="${newsInfo.type == null}">
                                                <form:option value="" label="请选择"/>
                                            </c:if>
                                            <form:options items="${NewsType}" />
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">新闻图片</label>
                                    <div class="col-sm-8">
                                        <form:input path="picture" class="form-control required" placeholder="图片"  readonly="true" />
                                        <div id="dropz" class="dropzone">
                                            <c:if test="${newsInfo.picture != null}">
                                                <div class="dz-preview dz-processing dz-image-preview dz-success dz-complete">
                                                    <div class="dz-image">
                                                        <img data-dz-thumbnail alt="图片" src="${pageContext.request.contextPath}/picShow?fileName=${newsInfo.picture}" style="width: 120px;height:120px;"/>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="detail" class="col-sm-2 control-label">内容</label>
                                    <div class="col-sm-8">
                                        <form:hidden path="detail"/>
                                            <%--<form:password cssClass="form-control required" path="content" placeholder="内容"/>--%>
                                        <div id="editor">${newsInfo.detail}</div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../back/copyright.jsp"/>
</div>
<jsp:include page="../back/footstyle.jsp"/>
<script src="${pageContext.request.contextPath}/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script>
    $(function () {
        var E = window.wangEditor
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/upload';
        editor.customConfig.uploadFileName = 'editorFile';
        editor.create();
        
        $("#btnSubmit").bind("click",function () {
               var contentHtml=editor.txt.html();
            $("#detail").val(contentHtml);
        });
    });

    var myDropzone = new Dropzone("#dropz", {
        url: "${pageContext.request.contextPath}/upload",
        dictDefaultMessage: "${newsInfo.picture == null ? '拖动文件至此或者点击上传' : ''}", // 设置默认的提示语句
        paramName: "dropFile", // 传到后台的参数名称
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#picture").val(data.fileName);
            });
        }
    });
</script>
</body>
</html>