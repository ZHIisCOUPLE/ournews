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

    <title>个人信息</title>
</head>

<style>
    .btnwidth{
        width: 100%
    }
    .jz{
        float: none;
        display: block;
        margin-left: auto;margin-right: auto;
    }
    .fontsl{
        max-width: 200px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>
<body>
<div class="wrapper-sticky-footer">
    <div class="content-sticky">
        <!-- Header -->
        <jsp:include page="../pages/Header.jsp"></jsp:include>
        <!-- END header -->
        <!-- Content-->
        <div  class="row">
        <div class="col-sm-6">
            <div>
                <div style="text-align: center;"  ><br/><br/>
                    <h1>个人信息</h1>
                    <span id="verify" style="color: red">${message == null?"<br/>":message}</span>
                    <form action="${pageContext.request.contextPath}/userinfo" method="post" id="myform">
                        <div class="col-xs-3 jz" >
                            邮箱<input type="email" name="email" id="email"  class="form-control" value="${Userinfo.email}">
                        </div><br/>

                        <div class="col-xs-3 jz" >
                            昵称 <input type="text" name="username" id="username"   class="form-control" value="${Userinfo.username}">
                        </div><br/>

                        <div class="col-xs-3 jz" >
                            手机号<input type="text" name="phone" id="phone"   class="form-control" value="${Userinfo.phone}">
                        </div><br/>
                        <input type="hidden" name="id" value="${Userinfo.id}">
                        <br/><br/>

                        <div class="col-xs-3 jz" >
                         <button type="submit" class="btn btn-success btnwidth" id="save" >信息保存</button>
                        </div>
                    </form>
                    <br/>
                    <div class="col-xs-3 jz" >
                        <a href="${pageContext.request.contextPath}/"><button class="btn btn-success btnwidth" >返回首页</button></a>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-sm-4 " style="left: 100px">
            <br/>
            <div style="left: 100px"><h1>我发布的新闻</h1></div>
            <br/><br/>
            <div>
            <c:forEach items="${userNewsList}" var="newslist">
                <div class="col-xs-8">
                    <div class="col-xs-8"><a href="${pageContext.request.contextPath}/news?id=${newslist.id}"><p class="fontsl">${newslist.name}  </p> </a></div>
                     <div class="col-xs-4"><a href="${pageContext.request.contextPath}/delete?id=${newslist.id}"><button class="btn btn-danger">删除</button></a></div>
                </div> <br/><br/> <br/><br/>
            </c:forEach>
            </div>
        </div>
            <!-- END content-->
        </div>
    </div>
    <!-- Footer -->
    <jsp:include page="../pages/footer.jsp"></jsp:include>

    <jsp:include page="../pages/javascript.jsp" />

</div>
<!-- END Footer -->

<script >

    $(function () {
        $("#save").hide();
        function hide(){
            $("#save").hide();
        }
        function show(){
            $("#save").show();
        }
        var email=1;
        var phone=1;
        var userneme=1;

        $("#email").blur(function () {
            if($(this).val()!="${Userinfo.email}"){
                if (!(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test($(this).val()))){
                    $("#verify").html("邮箱格式不对");
                    email=0;
                }else {
                    $.ajax({
                        type:"post",
                        url:"ajax",
                        data:{
                            email: $("#email").val()
                        },
                        dataType: "text",//数据格式
                        success: function (data) {
                            if(data==1){
                                $("#verify").html("邮箱可以使用");
                                email=1;
                                show();
                            }else {
                                $("#verify").html("邮箱已使用");
                                email=0;
                                hide();
                            }
                        }
                    });
                }
            }else {
                hide();
            }
        });
        $("#username").blur(function () {
            if($(this).val()!="${Userinfo.username}"){
                if($.trim($("#username").val())==''){
                    $("#verify").html("名字不能为空");
                    userneme=0;
                }else {
                    $.ajax({
                        type:"post",
                        url:"ajax",
                        data:{
                            username: $("#username").val()
                        },
                        dataType: "text",//数据格式
                        success: function (data) {
                            if(data==1){
                                $("#verify").html("名字可以使用");
                                show();
                            }else {
                                $("#verify").html("名字已使用");
                                userneme=0;
                                hide();
                            }
                        }
                    });
                }
            }else {
                hide();
            }
        });
        $("#phone").blur(function () {
            if($(this).val()!="${Userinfo.phone}"){
                if(!(/^1[34578]\d{9}$/.test($(this).val()))){
                    $("#verify").html("手机号码不对");
                    phone=0;
                }else {
                    $.ajax({
                        type:"post",
                        url:"ajax",
                        data:{
                            phone: $("#phone").val()
                        },
                        dataType: "text",//数据格式
                        success: function (data) {
                            if(data==1){
                                $("#verify").html("手机号可以使用");
                                phone=1;
                                show();
                            }else {
                                $("#verify").html("手机号已使用");
                                phone=0;
                                hide();
                            }
                        }
                    });
                }
            }else {
                hide();
            }
        });

        $("#myform").submit(function () {
            if(email==1&&userneme==1&&phone==1){
                return true;
            }else {
                alert("想想什么没有填好！");
                return false;
            }
        })

    })
</script>
</body>
</html>

