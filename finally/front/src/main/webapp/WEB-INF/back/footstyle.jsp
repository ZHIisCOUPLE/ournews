<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--底部js样式--%>
<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/js/adminlte.min.js"></script>
<%--jQuery Validation Plugin - v1.14.0 --%>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/plugins/jquery-validation/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/plugins/jquery-validation/js/localization/messages_zh.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/plugins/iCheck/icheck.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<%--App--%>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/app/validation.js"></script>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/app/app.js"></script>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/app/datetime.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->