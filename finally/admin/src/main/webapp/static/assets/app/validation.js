/**
 * jQuery 有效性验证
 * @constructor
 */
var Validate=function () {
    var handlerInit=function () {
        /**
         * 手机格式验证
         */
        $.validator.addMethod("mobile", function (value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
        /**
         * 表单验证
         * @param formId
         */
        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };
    /**
     * 私有类转公有
     */
    return{
        init:function () {
            handlerInit();
        }
    }
}();
/**
 * 加载方法
 */
$(document).ready(function () {
    Validate.init()
})