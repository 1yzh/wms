jQuery.ajaxSettings.traditional = true
//单条数据删除
$(function () {
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        $.dialog({
            title: "提示",
            content: "确定要删除数据吗？",
            cancel: true,
            ok: function () {
                //预先定义一个dialog对话框，内容在后面设置
                var dialog = $.dialog({
                    title: "提示",
                    icon: "face-smile",
                });
                $.post(url, function (data) {
                    //此处调用之前调用的对话框，设置属性：显示内容和OK按钮
                    dialog.content(data).button({
                        name: "ok！",
                        //点击ok后回掉函数
                        callback: function () {
                            window.location.reload();
                        }
                    });
                });
            }
        });
    });
})

//批量删除
$(function () {
    $("#all").click(function () {
        $(".acb").prop("checked", this.checked);
    });

    $(".btn_batchdelete").click(function () {
        var ids = [];
        $.each($(".acb:checked"), function (index, item) {
            ids[index] = $(item).data("eid");
        });
        /* var ids=$.map($(".acb:checked"),function (item,index) {
             console.log(arguments);
             return $(item).data("eid");
         })*/
        if (ids.length == 0) {
            $.dialog({
                title: "提示",
                content: "请选择要删除的数据",
                icon: "face-bad",
                ok: true
            });
            // alert("请选择");
            return;
        }
        var url = $(this).data("url");

        $.dialog({
            title: "提示",
            content: "确定要删除数据吗？",
            cancel: true,
            ok: function () {
                var dialog = $.dialog({
                    title: "提示",
                    icon: "face-smile",
                });
                $.post(url, {ids: ids}, function (data) {
                    dialog.content(data).button({
                        name: "ok！",
                        callback: function () {
                            window.location.reload();
                        }
                    });
                });
            }
        });
    });
});


//设置翻页
$(function () {//当页面加载完毕
    $(".btn_page").click(function () {//给btn_page一个点击事件
        $("input[name='qo.currentPage']").val($(this).data("page") || $("input[name='qo.currentPage']").val());//用或运算表示前面值为空时取后面值
        $("#searchForm").submit();//提交表单
    });
    $(":input[name='qo.pageSize']").change(function () {
        $("#searchForm").submit();//改变页面大小,需要提交表单
    });
    //设置框的类型为数字,并且取值范围最小1最大是totalpage
    $("input[name='qo.currentPage']").prop("type", "number").prop("max", $(".totalPage").data("page")).prop("min", "1");
})

//设置跳转到指定页面
$(function () {
    $(".btn_input").click(function () {
        //window.location.href 用于设置页面跳转到哪里
        window.location.href = $(this).data("url");//使用s:url namespace="/" action=""可以表示一个跳转action
    });
})

/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});


