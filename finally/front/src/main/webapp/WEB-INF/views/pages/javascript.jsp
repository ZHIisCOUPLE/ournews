<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="${pageContext.request.contextPath}/static/js/html5shiv.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/respond.min.js"></script>
<!-- All JavaScript libraries -->
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<!-- Custom JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>


<%-- Header --%>
<script type="text/javascript">
    $(function () {
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
</script>
<script type="text/javascript">
    $(function () {
        <%-- ${pageContext.request.contextPath} 为上下文路径 --%>
        $("#login").click(function () {
            window.location.href = "${pageContext.request.contextPath}/login";
        });
        $("#toRegister").click(function () {
            window.location.href = "${pageContext.request.contextPath}/toRegister";
        });
    })
</script>
<script type="text/javascript">
    $(function () {
        $("#addnews").click(function () {
            window.location.href = "${pageContext.request.contextPath}/addnewspage";
        });
    })
</script>
<script type="text/javascript">
    $(function () {
        $("#logout").click(function () {
            window.location.href = "${pageContext.request.contextPath}/logout";
        });
        <%-- ${pageContext.request.contextPath} 为上下文路径 --%>
        $("#userinfo").click(function () {
            window.location.href = "${pageContext.request.contextPath}/userinfo";
        });
    })
</script>
<script type="text/javascript">
    $("#seeknews").submit(function () {
        if($("#seek").val()==''||$("#seek").val()=="输入你感兴趣的新闻"){
            alert("请输入再搜索");
            return false;
        }else {
            return true;
        }
    })
</script>
<%-- Header end 。。。 --%>



