var App=function () {
    //Checkbox变量初始化
    var _masterCheckbox;
    var _checkbox;
    //id数组初始化
    var _idArray;
    /**
     * 批量删除复选框
     */
    var handlerInitCheckbox=function () {
        //Checkbox样式
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //给变量赋值(Checkbox)
        _masterCheckbox = $('input[type="checkbox"].minimal.iCheck_master');
        _checkbox = $('input[type="checkbox"].minimal');
    };
    /**
     * 主Checkbox点击事件
     */
    var handleCheckboxAll=function () {
        _masterCheckbox.on('ifClicked',function (e) {
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            else {
                _checkbox.iCheck("check");
            }
        });
    };
    /**
     * 单击删除
     * @param url
     */
    var handlerDelete=function (url) {
        $("#modal-message").html("您确定删除数据项吗？");
        //显示模态框
        $("#modal-default").modal("show");
        //当点击确定按钮时触发事件
        $("#btnconfirm").bind("click",function () {
            //定时函数(增加客户体验)
            setTimeout(function () {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "dataType":"JSON",
                    "success":function (data) {
                        $("#btnconfirm").unbind("click");
                        //成功
                        if (data.status==200){
                            $("#btnconfirm").bind("click",function () {
                                window.location.reload();
                            })
                        }
                        //失败
                        else {
                            $("#btnconfirm").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            },500);
        });
    };
    /**
     * 批量删除方法
     * @param url 地址
     */
    var handlerDeleteMulti=function (url) {
        _idArray=new Array();
        //遍历所有子复选框
        _checkbox.each(function () {
            var  _id=$(this).attr("id")
            //把被选中的复选框的id值赋给数组
            if(_id!=null&&_id!="undefine"&&$(this).is(":checked")){
                _idArray.push(_id);
            }
        });
        if(_idArray.length===0){
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }else {
            $("#modal-message").html("您确定删除数据项吗？");
        }
        //显示模态框
        $("#modal-default").modal("show");
        //当点击确定按钮时触发事件
        $("#btnconfirm").bind("click",function () {
            del()
        });

        /**
         * 当前私有函数的私有函数
         * 删除事件
         */
        function del() {
            //隐藏模态框
            $("#modal-default").modal("hide");
            if (_idArray.length==0){
                //...
            }
            //一json格式把数据往后台传输
            else {
                //定时函数(增加客户体验)
                setTimeout(function () {
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "data":{"ids":_idArray.toString()},
                        "dataType":"JSON",
                        "success":function (data) {
                            $("#btnconfirm").unbind("click");
                            //成功
                            if (data.status==200){
                                $("#btnconfirm").bind("click",function () {
                                    window.location.reload();
                                })
                            }
                            //失败
                            else {
                                $("#btnconfirm").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");

                        }
                    });
                },500);
            }
        }
    };
    /**
     * 分页
     * @param url
     * @param columns
     * @returns {jQuery}
     */
    var handlerInitDataTables=function (url,columns) {
        //分页样式
        var _dataTable=$('#table_id_example').DataTable({
            columnDefs:[
                {
                    target: -1,
                    className:'dt-body-center'
                }
            ],
            "paging":true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url,
            },
            //参数
            "columns":columns,
            //国际化(中文版)
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "未查到数据",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            //回调函数(重新加载Checkbox样式和事件)
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handleCheckboxAll();
            }
        });
        //获取DataTable
        return _dataTable;
    };
    /**
     * 用户状态
     * @param url
     */
    var handlerStatus=function (url) {
        $("#modal-message").html("您确定冻结该数据项吗？");
        //显示模态框
        $("#modal-default").modal("show");
        //当点击确定按钮时触发事件
        $("#btnconfirm").bind("click",function () {
            //定时函数(增加客户体验)
            setTimeout(function () {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "dataType":"JSON",
                    "success":function (data) {
                        $("#btnconfirm").unbind("click");
                        //成功
                        if (data.status==200){
                            $("#btnconfirm").bind("click",function () {
                                window.location.reload();
                            })
                        }
                        //失败
                        else {
                            $("#btnconfirm").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            },500);
        });
    };
    /**
     * 查看信息
     * @param url
     */
    handlerInitDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                //模态框形式查看
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };
    /**
     * 查看评论
     * @param url
     */
    handlerComment = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success:function () {
                location.href=url;
            }
        });
    };
    /**
     * 私有方法转为公有方法
     */
    return{
        //Checkbox方法
        init:function () {
            handlerInitCheckbox();
            handleCheckboxAll();
        },
        //获取Checkbox
        getCheckbox:function () {
            return _checkbox;
        },
        //批量删除方法
        deleteMulti:function (url) {
            handlerDeleteMulti(url);
        },
        //分页方法
        initDataTables:function (url,columns) {
            return handlerInitDataTables(url,columns);
        },
        //删除方法
        singleDelete:function (url) {
            handlerDelete(url);
        },
        //用户状态
        changeStatus:function (url) {
            handlerStatus(url)
        },
        //模态框查看信息方法
        showDetail:function (url) {
            handlerInitDetail(url);
        },
        //展示评论
        showComment:function (url) {
            handlerComment(url);
        }
    }
}();
/**
 * 加载公有方法
 */
$(document).ready(function () {
    //Checkbox方法
    App.init();
});
