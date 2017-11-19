//参数校验
$(function () {
    //页面表单空间调用validate方法
    $("#editForm").validate({
        /*
        * 固定的格式
        * rules:{},
        * messages{}
        * */

        rules:{
            'employee.name':{//employee.name带有点符号，需要用单引号括起来
                "required":true,
                "rangelength":[4,16]
            },
            'employee.password':{
                "required":true,
                "rangelength":[4,16]
            },
            'repassword':{
                "equalTo":"#password"
            },
            'employee.email':{
                "required":true,
                "email":true
            },
            'employee.age':{
                "required":true,
                "range":[18,66]
            }
        },
        messages:{
            'employee.name':"用户名必须填写，且长度在{0}~{1}之间",
            'employee.password':"密码必须填写，且长度在{0}~{1}之间",
            'repassword':"密码必须填写一致",
            'employee.email':"请填写email的正确格式",
            'employee.age':"年龄必须在{0}~{1}岁之间"
        }
    });
});
