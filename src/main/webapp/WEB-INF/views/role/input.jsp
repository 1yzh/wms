<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/jqueryvalidate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/js/system/role.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="role_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="role.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">角色编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">角色名称</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">角色编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <td>&nbsp;</td>
                <tr>
                    <td class="ui_text_rt" width="140">分配角色</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <s:select list="#permissions" listKey="id" listValue="name" class="ui_multiselect01 all_permissions"
                                              multiple="true"/>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <s:select list="role.permissions" name="role.permissions.id" listKey="id"
                                              listValue="name" class="ui_multiselect01 selected_permissions"
                                              multiple="true"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">分配菜单</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <s:select list="#menus" listKey="id" listValue="name" class="ui_multiselect01 all_menus"
                                              multiple="true"/>
                                </td>
                                <td align="center">
                                    <input type="button" id="mselect" value="-->" class="left2right"/><br/>
                                    <input type="button" id="mselectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="mdeselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="mdeselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <s:select list="role.menus" name="role.menus.id" listKey="id"
                                              listValue="name" class="ui_multiselect01 selected_menus"
                                              multiple="true"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                </tr>
            </table>
                <td class="ui_text_lt">
            <input type="submit" value="确定保存" class="ui_input_btn01"/>
            <input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                </td>
        </div>
    </div>
</s:form>
</body>
</html>