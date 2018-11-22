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
    <title>OURNEWS</title>

</head>

<body>
<div class="wrapper-sticky-footer">
        <jsp:include page="../pages/Header.jsp"></jsp:include>
    <div class="content-sticky">
        <!-- Header -->
        <!-- END header -->
        <!-- Content-->
        <div >
            <div style="margin:0 auto">
                <div style="text-align: center" ><br/><br/>
                    <h1>登录</h1><br/>
                    <span style="color: red">${message == null?"<br/>":message}</span>
                    <p id="em1" style="color: red"></p>
                    <p id="pwd1" style="color: red"></p>
                    <form action="${pageContext.request.contextPath}/login" method="post" id="form">
                        <input type="hidden" name="newsid" id="newsid" value="${newsid}"/>
                        <div class="col-xs-2" style=" float: none;display: block;margin-left: auto;margin-right: auto;">
                            <input type="text" name="email" placeholder="邮箱地址......" class="form-control" id="em">
                        </div>
                       <br/>
                        <div class="col-xs-2" style=" float: none;display: block;margin-left: auto;margin-right: auto;">
                            <input type="password" name="password" placeholder="密码......"  class="form-control" id="pwd" maxlength="16">
                        </div>

                        <br/>
                        <div class="col-xs-2" style=" float: none;display: block;margin-left: auto;margin-right: auto;">
                            <button type="submit" class="btn btn-success" style="width: 100%">登录</button>
                        </div>
                    </form>
                    <br/>
                    <div class="col-xs-2" style=" float: none;display: block;margin-left: auto;margin-right: auto;">
                    <a href="toRegister"><button class="btn btn-success" style="width: 100%">没有账号先注册</button></a>
                    </div>
                </div>

            </div>
        </div>
        <!-- END content-->
    </div>
    <!-- Footer -->
</div>
    <jsp:include page="../pages/footer.jsp"></jsp:include>
<!-- END Footer -->
    <jsp:include page="../pages/javascript.jsp" />

</body>
<script type="text/javascript">
    $(function () {
        var pd=1;
        var em=1;

        // 验证密码正则表达式
        function isPassword(pwd) {
            //输入6到18个数子
            var pattern = /^\d{6,16}$/;
            return pattern.test(pwd);
        }
        // 验证邮箱正则表达式
        function isEmail(email) {
            //输入6到18个数子
            var pattern = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
            return pattern.test(email);
        }

        //密码验证
        $("#pwd").blur(function() {
            var pwd = $(this).val();
            if ($.trim(pwd) == null || $.trim(pwd)=="") {
                $("#pwd1").html("密码不能为空！");
                pd=0;
            }else {
                if (isPassword(pwd)){
                    $("#pwd1").html("");
                    pd=1;
                }else {
                    $("#pwd1").html("密码格式不对!请输入至少6位，最多18位数字作为密码");
                    pd=0;
                }
            }

        })

        //邮箱地址验证
        $("#em").blur(function() {
            var email = $(this).val();
            if ($.trim(email) == null || $.trim(email)=="") {
                $("#em1").html("邮箱不能为空！");
                em=0;
            }else {
                if (isEmail(email)){
                    $("#em1").html("");
                    em=1;
                }else {
                    $("#em1").html("邮箱格式不对");
                    em=0;
                }

            }
        })

        //判断是否可以提交
        $("#form").submit(function () {
            // return false;
            if (pd>0&&em>0){
                // $("#from").submit();
                return true;
            } else {
                return false;
            }
        })
    })
</script>
</html>

