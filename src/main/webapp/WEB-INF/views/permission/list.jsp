<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-权限管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".btn_reload").click(function () {
                $.dialog({
                    title: "提示",
                    content: "确认加载吗？",
                    icon: "face-smile",
                    cancel: true,
                    ok: function () {
                        var dialog = $.dialog({
                            title: "提示",
                            icon: "face-smile"
                        });
                        $.get("/permission_reload.action", function (data) {
                            dialog.content(data).button({
                                name: "确定",
                                callback: function () {
                                    window.location.reload();
                                }
                            })
                        })
                    }
                })
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form id="searchForm" namespace="/" action="permission" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="加载权限" class="ui_input_btn01 btn_reload"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>权限表达式</th>
                        <th>权限名称</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="expression"/></td>
                            <td><s:property value="name"/></td>
                            <td>
                                    <%--<s:a namespace="/" action="permission_input">
                                        <s:param name="permission.id" value="id"/>编辑
                                    </s:a>--%>
                                <s:url namespace="/" action="permission_delete" var="url">
                                    <s:param name="permission.id" value="id"/>
                                </s:url>

                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="url"/>">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <%@include file="/WEB-INF/views/commons/common_page.jsp" %>
        </div>
    </div>
</s:form>
</body>
</html>
