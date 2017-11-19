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
    <title>PSS-系统菜单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form id="searchForm" namespace="/" action="systemMenu" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <s:url namespace="/" action="systemMenu_input" var="url">
                            <s:param name="qo.parentId" value="qo.parentId"/>
                        </s:url>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:property value="#url"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            当前:
            <s:a namespace="/" name="systemMenu">
                根目录
            </s:a>
            <s:iterator value="#menus">
                ▶<s:a namespace="/" name="systemMenu">
                <s:param name="qo.parentId" value="id"/>
                <s:property value="name"/>
            </s:a>
            </s:iterator>
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>菜单名称</th>
                        <th>父级菜单</th>
                        <th>url</th>
                        <th>菜单编号</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="parent.name"/></td>
                            <td><s:property value="url"/></td>
                            <td><s:property value="sn"/></td>
                            <td>
                                <s:a namespace="/" action="systemMenu">
                                    <s:param name="qo.parentId" value="id"/>查看子菜单
                                </s:a>
                                <s:a namespace="/" action="systemMenu_input">
                                    <s:param name="qo.parentId" value="qo.parentId"/>
                                    <s:param name="systemMenu.id" value="id"/>编辑
                                </s:a>
                                <s:url namespace="/" action="systemMenu_delete" var="url">
                                    <s:param name="systemMenu.id" value="id"/>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="#url"/>">删除</a>
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
