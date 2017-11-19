//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
    }
}

//配置main页面左菜单
$(function () {
    $("#nav_resource ul li a").click(function () {
        $("#rightMain").prop("src", $(this).data("url"));
        $("#here_area").html("当前位置：&nbsp;>&nbsp;" + $(this).text());
    });
})

$(function () {
    loadDate();

    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });

    //菜单切换
    $("#TabPage2 li").click(function () {
        //首先移除所有样式
        $.each($("#TabPage2 li"), function (index, item) {
            $(item).removeClass("selected");
            $(item).children("img").prop("src", "/images/common/" + (index + 1) + ".jpg");
        })
        //给选中的元素增加样式用addclass
        $(this).addClass("selected");
        //给选中元素变更图片
        var index = $(this).index() + 1
        $(this).children("img").prop("src", "/images/common/" + index + "_hover.jpg");
        //上面导航图片变更
        $("#nav_module").children("img").prop("src", "/images/common/module_" + index + ".png")
        //加载菜单树,效用下面的方法ztree那里的
        loadMenus($(this).data("rootmenu"));
    });
});


//===================
//配置zTree
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: onClick
    },
    async: {
        enable: true,
        url: "systemMenu_queryMenusByParentSn.action",
        autoParam: ["sn=qo.parentSn"]
    }
};

function onClick(event, treeId, treeNode) {
    if (treeNode.action) {
        $("#rightMain").prop("src", treeNode.action + ".action");
        $("#here_area").html("当前位置：" + treeNode.getParentNode().name + "&nbsp;>&nbsp;" + treeNode.name)
    }
}

var zNodes = {
    business: [
        {id: 1, pId: 0, name: "业务管理模块", isParent: true, sn: "business"}

        /* {id: 11, pId: 1, name: "业务一"},
         {id: 12, pId: 1, name: "业务二"}*/
    ],
    systemManage: [
        {id: 2, pId: 0, name: "系统管理模块", isParent: true, sn: "systemManage"}

        /* {id: 21, pId: 2, name: "员工管理", action: "employee"},
         {id: 22, pId: 2, name: "部门管理", action: "department"},
         {id: 23, pId: 2, name: "角色管理", action: "role"},
         {id: 24, pId: 2, name: "权限管理", action: "permission"}*/
    ],
    charts: [
        {id: 3, pId: 0, name: "报表管理模块", isParent: true, sn: "charts"}

        /* {id: 31, pId: 3, name: "报表1"},
         {id: 32, pId: 3, name: "报表2"},
         {id: 33, pId: 3, name: "报表3"}*/
    ]
}

function loadMenus(sn) {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}

$(function () {
    loadMenus("business");//此处为文档加载之后默认显示的菜单
});
//===================