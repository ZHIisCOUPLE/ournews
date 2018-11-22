
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>发布新闻</title>
    <jsp:include page="../back/headstyle.jsp"/>
    <%--图片上传--%>
    <link href="${pageContext.request.contextPath}/static/assetsaddnews/plugins/dropzone/min/dropzone.min.css" rel="stylesheet" type="text/css" >
    <%--富文本编辑器--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assetsaddnews/plugins/wangEditor/wangEditor.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assetsaddnews/bower_components/select2/dist/css/select2.min.css">
</head>
<body style="background: #eeeeee;">
        <div class="header__top" style="background: #303d4a;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3 col-md-2"  style="margin-top: 10px !important;">
                        <div class="wrap-logo">
                            <a href="${pageContext.request.contextPath}/" title="返回首页" ><img src="${pageContext.request.contextPath}/static/img/png-sprite/logo.png"></a>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-md-offset-3 col-sm-7 col-md-7 hidden-xs">
                        <div class="col-xs-offset-5 col-sm-offset-4 col-xs-3 col-sm-4">
                            <div class="weather" style=" width: 200px !important; margin: 7% 0% 0% 3% !important;">
                                <div class="weather__city" >

                                    <em style=" font-size: 24px !important;color: #ffffff">
                                        欢迎登陆&nbsp;&nbsp;<span style="color: gold">${USER_LOGIN.username}</span></em>
                                    </em>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-offset-1 col-sm-offset-1 col-xs-3 col-sm-3  hidden-xs hidden-sm" >
                            <p class="exchange__name" style="font-size: 16px !important; margin-top: 6px!important; color: #ffffff">北京时间：</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Main content*********************************** -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${message!=null}">
                        <div class="alert alert-danger alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${message}
                        </div>
                    </c:if>
                    <div class="box" style="background: #eeeeee; border: 0;">
                        <div class="box-header with-border" style="margin-left: 12%; width: 132px; background: #303d4a; color: #ffffff; text-align: center">
                            <h3 class="box-title">发布我的新闻</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal"  method="post" action="${pageContext.request.contextPath}/addnews" modelAttribute="newsInfo">
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

                                                <form:option value="" label="请选择"/>

                                            <form:options items="${newsTypes}" />
                                        </form:select>
                                    </div>
                                </div>

                        <%--//图片上传--%>
                                <div class="form-group">
                                    <label for="picture1" class="col-sm-2 control-label">新闻图片</label>
                                    <input type="hidden" readonly="readonly"   value="${content.picture1}"  class="form-control" id="picture1" name="picture">
                                    <div class="col-sm-8">
                                    <div class="dropzone" id="pic1upload"></div>

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
                            <div class="box-footer"  style="background: #eeeeee; border: 0;">
                                <div class="row">

                                    <div class="col-sm-1 col-sm-offset-8">
                                        <button type="button" class="btn btn-default" style="width: 90%">返回</button>
                                    </div>
                                    <div class="col-sm-1" >
                                        <button id="btnSubmit" type="submit" class="btn btn-info pull-right" style="width: 90%">提交</button>
                                    </div>


                                </div>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
        <div class="header__top" style="background: #303d4a;height: 85px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <p class="copyright" style="color: #ffffff; text-align: center; line-height: 80px; font-weight: bolder;">Copyright &copy; 2018.广州千峰项目小组</p>
                    </div>
                </div>
            </div>
        </div>
<jsp:include page="../back/footstyle.jsp"/>
<%--<jsp:include page="pages/footer.jsp" />--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assetsaddnews/plugins/dropzone/min/dropzone.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assetsaddnews/plugins/wangEditor/wangEditor.min.js"></script>
<script type="text/javascript">
    //-----------------------------------------------------------------------------------
    $(function () {
        initWangEditor();//在初始化完成后初始化wangeditor

        setInterval(function() {
            var showDate = new Date();
            var week = new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
            var seconds = showDate.getSeconds();
            if (seconds < 10){
                seconds = 0+""+seconds;
            }
            var minutes = showDate.getMinutes();
            if (minutes < 10){
                minutes = 0+""+minutes;
            }
            var hours = showDate.getHours();
            if (hours < 10){
                hours = 0+""+hours;
            }
            var time = showDate.getFullYear()+"年"+(showDate.getMonth()+1)
                +"月"+showDate.getDate()+"日 <br /> <span >" + hours
                +":"+ minutes+":"+seconds+ " "+ week[showDate.getDay()] + "</span>";
            $("p[class='exchange__name']").html(time);
        },1000);

    })
    var defaultDropzoneOpts = {
        url: "",
        paramName: "dropFile",
        maxFiles: 1, // 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1, // 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传 1 个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    };


    var opts={
        //id指的放我们 上传控件的那个div的id
        id: "#pic1upload",
        //上传的路径
        url: "${pageContext.request.contextPath}/news/upload",
        paramName: "dropFile",//指的是上传到后台，用来获取这个上传文件的表单名
        init: function () {
            //这是当上传完成后回调这个函数
            this.on("success", function (file, data) {
                // $("#pic").val(data.fileName);
                $("#picture1").val(data.path);
            });
        }
    }

    // 关闭 Dropzone 的自动发现功能
    Dropzone.autoDiscover = false;
    // 继承
    //$.extend(）jquery内部的函数，将后面的（opts）覆盖到前面的(defaultDropzoneOpts)
    $.extend(defaultDropzoneOpts, opts);
    //创建一个上传控件
    new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);


    function initWangEditor () {
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/news/upload';//上传图片的url
        editor.customConfig.uploadFileName = 'dropFile';//上传图片的时候，用表单域的名称
        editor.create();

        $("#btnSubmit").bind("click", function() {
            var contentHtml = editor.txt.html();
            $("#detail").val(contentHtml);
        });
    }



</script>

</body>
</html>