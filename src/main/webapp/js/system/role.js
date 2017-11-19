//文档加载完毕,移动权限相关
$(function () {

    //全部移动==>
    $("#selectAll").click(function () {
        $(".all_permissions option").appendTo($(".selected_permissions"))
    });

    //全部移动<==
    $("#deselectAll").click(function () {
        $(".selected_permissions option").appendTo($(".all_permissions"))
    });

    //选择移动-->
    $("#select").click(function () {
        $(".all_permissions option:selected").appendTo($(".selected_permissions"))
    });

    //选择移动<--
    $("#deselect").click(function () {
        $(".selected_permissions option:selected").appendTo($(".all_permissions"))
    });

    //从左边移除移到到右边的选择项
    //定义ids，为右边选项数组
    var ids = $.map($(".selected_permissions option"), function (item, index) {
        return item.value;
    });
    //迭代左边选项，看是否在右边选项的数组里，如果在则删除
    $.each($(".all_permissions option"), function (index, item) {
        if ($.inArray(item.value, ids) >= 0) {
            (item).remove();
        }
    });

    //表单提交时，将右边选项处于选择状态
    $("#editForm").submit(function () {
        $(".selected_permissions option").prop("selected", true);
    });
})



//文档加载完毕,移动菜单相关
$(function () {

    //全部移动==>
    $("#mselectAll").click(function () {
        $(".all_menus option").appendTo($(".selected_menus"))
    });

    //全部移动<==
    $("#mdeselectAll").click(function () {
        $(".selected_menus option").appendTo($(".all_menus"))
    });

    //选择移动-->
    $("#mselect").click(function () {
        $(".all_menus option:selected").appendTo($(".selected_menus"))
    });

    //选择移动<--
    $("#mdeselect").click(function () {
        $(".selected_menus option:selected").appendTo($(".all_menus"))
    });

    //从左边移除移到到右边的选择项
    //定义ids，为右边选项数组
    var ids = $.map($(".selected_menus option"), function (item, index) {
        return item.value;
    });
    //迭代左边选项，看是否在右边选项的数组里，如果在则删除
    $.each($(".all_menus option"), function (index, item) {
        if ($.inArray(item.value, ids) >= 0) {
            (item).remove();
        }
    });

    //表单提交时，将右边选项处于选择状态
    $("#editForm").submit(function () {
        $(".selected_menus option").prop("selected", true);
    });
})