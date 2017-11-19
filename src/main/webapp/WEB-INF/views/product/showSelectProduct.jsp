<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/js/plugins/fancybox/jquery.fancybox.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artdialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/fancybox/jquery.fancybox.pack.js"></script>
    <title>PSS-货品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        <%--设置图片弹出层--%>
        $(function () {
            $(".example_group").fancybox();
        })
        $(function () {
            $(".btn_selectProduct").click(function () {
                window.returnValue=$(this).data("json");
                window.close();
            });
        })
        
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form id="searchForm" namespace="/" action="product" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                        <%-- <div id="box_top">搜索</div>
                         <div id="box_center">
                             姓名/邮箱
                             <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                             所属部门
                             <s:select list="#depts" name="qo.deptId" cssClass="ui_select01" listKey="id" listValue="name" headerKey="-1" headerValue="所有部门"/>
                         </div>--%>
                    <div id="box_bottom">
                            <%--<input type="button" value="新增" class="ui_input_btn01 btn_input"
                                   data-url="<s:url namespace="/" action="product_input"/>"/>--%>
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
                        <th>产品图片</th>
                        <th>产品名称</th>
                        <th>产品品牌</th>
                        <th>产品编号</th>
                        <th>成本价格</th>
                        <th>销售价格</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td>
                                    <%--<s:property value=""/> 这个获取参数都用domain里的get方法获取,
                                    如果没有相关字段,可以直接设置get方法,方法名是get字段,注意大小写--%>
                                <a class="example_group" href="<s:property value="imagePath"/>"
                                   title="<s:property value="name"/>">
                                    <img alt="" src="<s:property value="smallImagePath"/>" width="80"/></a>
                            <td><s:property value="name"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td>
                                <input type="button" value="选择此商品" class="ui_input_btn01 btn_selectProduct"
                                       data-json="<s:property value="jasonString"/>"/>
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
