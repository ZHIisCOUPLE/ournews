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


    <title>用户注册</title>

</head>
<style>
    .jz{
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
        padding: 10px;
    }
</style>
<body>
<div class="wrapper-sticky-footer">
    <div class="content-sticky">
        <!-- Header -->
        <jsp:include page="../pages/Header.jsp"></jsp:include>
        <!-- END header -->
        <!-- Content-->
        <div >
            <div style="margin:0 auto">
                <div style="padding-left: 1px;text-align: center" ><br/>
                    <h1>用户注册</h1>

                    <span id="verify" style="color: red">${message == null?"<br/>":message}</span>

                    <form action="${pageContext.request.contextPath}/register" method="post" id="myform">

                        <div class="col-xs-2 jz">
                            <input type="email" name="email" id="email" placeholder="注册邮箱"  class="form-control">
                        </div>
                        <div  class="col-xs-2 jz">
                            <div class="col-xs-8" style="left: -10px"><input type="text" name="yxyz" id="yxyz" placeholder="邮箱验证码"  class="form-control"></div>
                            <div class="col-xs-1" style="left: -5px"><button type="button" id="JSP" class="btn btn-success" disabled="disabled">发送验证码</button></div>
                            <br/><br/>
                        </div>

                        <div  class="col-xs-2 jz">
                            <input type="text" name="username" id="username" placeholder="昵称"  class="form-control" maxlength="8">
                        </div>

                        <div  class="col-xs-2 jz">
                            <input type="text" name="phone" id="phone" placeholder="手机号"  class="form-control">
                        </div>

                        <div  class="col-xs-2 jz">
                            <input type="password" name="password" id="password" placeholder="密码"  class="form-control" maxlength="16">
                        </div>

                        <div class="col-xs-2 jz">
                            <input type="password" name="repetition" id="repetition" placeholder="确定密码"  class="form-control">
                        </div>

                        <div class="col-xs-2 jz">
                            <button type="submit" class="btn btn-success" style="width: 100%" id="zc">注册</button>
                        </div>
                    </form>

                </div>

            </div>
        </div>
        <!-- END content-->
    </div>

    <jsp:include page="../pages/javascript.jsp"/>
    <!-- Footer -->
   <jsp:include page="../pages/footer.jsp"></jsp:include>
</div>
<!-- END Footer -->


</body>
<%--register --%>
<script >


    $(function () {
        var email=0;
        var phone=0;
        var userneme=0;
        var password=0;
        var qdpwd=0;
        var yc=null;

        function hide(){
            $("#username").hide();
            $("#phone").hide();
            $("#password").hide();
            $("#repetition").hide();
            $("#zc").hide();
        }
        function show(){
            $("#username").show();
            $("#phone").show();
            $("#password").show();
            $("#repetition").show();
            $("#zc").show();
        }
        hide();
        $("#email").blur(function () {
            if (!(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test($(this).val()))){
                $("#verify").html("邮箱格式不正确");
            }else {
                if($.trim($("#email").val())==''){
                    $("#verify").html("邮箱不能为空");
                }else {
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/ajax",
                        data:{
                            email: $("#email").val()
                        },
                        dataType: "text",//数据格式
                        success: function (data) {
                            if(data==1){
                                $("#verify").html("<font style='color:green'>"+$("#email").val()+"可以注册！</font>");
                                $('#JSP').attr("disabled",false);
                                email=1;
                            }else {
                                $("#verify").html("邮箱已使用");
                                $("#JSP").attr("disabled",true);
                                email=0;
                                hide();
                            }
                        }
                    });
                }
            }

        });
        $("#yxyz").blur(function () {
            if($.trim($(this).val())==''){
                $("#verify").html("邮箱验证码不能为空");
                hide();
            }else if ($(this).val()!=yc) {
                $("#verify").html("邮箱验证码不正确");
                hide();
            }else if($(this).val()==yc){
                $("#verify").html("邮箱验证码正确");
                show();
            }
        })

        $("#username").blur(function () {
            if($.trim($("#username").val())==''){
                $("#verify").html("名字不能为空");
            }else {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/ajax",
                    data:{
                        username: $("#username").val()
                    },
                    dataType: "text",//数据格式
                    success: function (data) {
                        if(data==1){
                            $("#verify").html("<font style='color:green'>"+$("#username").val()+"可以注册！</font>");
                            userneme=1;
                        }else {
                            $("#verify").html("名字已使用");
                            userneme=0;
                        }
                    }
                });
            }

        })

        $("#phone").blur(function () {
            if(!(/^1[34578]\d{9}$/.test($(this).val()))){
                $("#verify").html("手机号码格式不正确！");
            }else {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/ajax",
                    data:{
                        phone: $("#phone").val()
                    },
                    dataType: "text",//数据格式
                    success: function (data) {
                        if(data==1){
                            $("#verify").html("<font style='color:green'>"+$("#phone").val()+"可以注册</font>");
                            phone=1;
                        }else {
                            $("#verify").html("手机号已注册！");
                            phone=0;
                        }
                    }
                });
            }
        })

        $("#JSP").click(function () {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/senemail",
                    data:{
                        email: $("#email").val()
                    },
                    dataType: "text",
                    success: function (data) {
                        if(data==''){
                            $("#verify").html("邮箱不能使用");
                        }else {
                            yc=data;
                        }
                    }
                });
            })

        $("#password").blur(function () {
            if(!(/^\d{6,16}$/.test($(this).val()))){
                $("#verify").html("请输入6-16位数字密码");

            }else {
                $("#verify").html("<font style='color:green'>密码格式正确</font>");
                qdpwd=1;
            }
        })

        $("#repetition").blur(function () {

                if(!$.trim($(this).val())==''){
                    if($(this).val()==$("#password").val()){
                         $("#verify").html("<font style='color:green'>确定密码正确</font>");
                        password=1;
                    }else {
                        $("#verify").html("两次密码不一致！");
                        password=0;
                    }
                }else {
                    $("#verify").html("请输入确定密码");
                }


        })

        $("#myform").submit(function () {
            if(email==1&&userneme==1&&password==1&&phone==1&&qdpwd==1){
                    return true;
            }else {
                alert("想想什么没有填好！");
                return false;
            }
        })

    })
</script>
<%--register end --%>
</html>

