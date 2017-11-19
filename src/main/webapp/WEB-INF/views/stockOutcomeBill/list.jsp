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
    <script type="text/javascript" src="/js/plugins/my97datepicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>PSS-销售出库单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("input[name='qo.beginTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate:$("input[name='qo.endTime']").val()||new Date()
                });
            });
            $("input[name='qo.endTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    minDate:$("input[name='qo.beginTime']").val()
                });
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form id="searchForm" namespace="/" action="stockOutcomeBill" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间:
                        <s:date name="qo.beginTime" format="yyyy-MM-dd" var="bd"/>
                        <s:textfield name="qo.beginTime" cssClass="ui_input_txt02" value="%{bd}"/>~
                        <s:date name="qo.endTime" format="yyyy-MM-dd" var="ed"/>
                        <s:textfield name="qo.endTime" cssClass="ui_input_txt02" value="%{ed}"/>
                        销售人员:
                        <s:select list="#clients" name="qo.clientId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="销售人员"/>
                        仓库:
                        <s:select list="#depots" name="qo.depotId" cssClass="ui_select01"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有仓库"/>
                        审核状态:
                        <s:select list="#{'-1':'所有状态','0':'未审核','1':'已审核'}" name="qo.status" cssClass="ui_select01"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="stockOutcomeBill_input"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>订单编号</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>采购数量</th>
                        <th>采购金额</th>
                        <th>制单人</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.listData">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb" data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="sn"/></td>
                            <td><s:date name="vdate" format="yyyy-MM-dd"/></td>
                            <td><s:property value="depot.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>
                            <td>
                                <s:if test="status ==0"><span style="color: #990000">未审核</span></s:if>
                                <s:elseif test="status ==1"><span style="color: #00B83F">已审核</span></s:elseif>
                            </td>
                            <s:if test="status ==0">
                                <td>
                                    <s:a namespace="/" action="stockOutcomeBill_audit">
                                        <s:param name="stockOutcomeBill.id" value="id"/>审核
                                    </s:a>
                                    <s:a namespace="/" action="stockOutcomeBill_input">
                                        <s:param name="stockOutcomeBill.id" value="id"/>编辑
                                    </s:a>
                                    <s:url namespace="/" action="stockOutcomeBill_delete" var="url">
                                        <s:param name="stockOutcomeBill.id" value="id"/>
                                    </s:url>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="<s:property value="#url"/>">删除</a>
                                </td>
                            </s:if>
                            <s:elseif test="status ==1">
                                <td>
                                    <s:a namespace="/" action="stockOutcomeBill_view">
                                        <s:param name="stockOutcomeBill.id" value="id"/>查看
                                    </s:a>
                                </td>
                            </s:elseif>

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
