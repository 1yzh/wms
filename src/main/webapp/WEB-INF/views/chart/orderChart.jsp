<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/my97datepicker/WdatePicker.js"></script>
    <title>PSS-订单报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("input[name='oqo.beginTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate: $("input[name='oqo.endTime']").val() || new Date()
                });
            });
            $("input[name='oqo.endTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    minDate: $("input[name='oqo.beginTime']").val()
                });
            });

            $(".chart").change(function () {
                var url = $("#searchForm").serialize();
                if ("line" == this.value) {
                    showModalDialog("/chart_orderChartByLine.action?" + url, "", "dialogWidth=1200px;dialogHeight=600px");
                } else if ("pie" == this.value) {
                    showModalDialog("/chart_orderChartByPie.action?" + url, "", "dialogWidth=1200px;dialogHeight=600px");

                }
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form id="searchForm" namespace="/" action="chart_orderChart.action" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间:
                        <s:date name="oqo.beginTime" format="yyyy-MM-dd" var="bd"/>
                        <s:textfield name="oqo.beginTime" cssClass="ui_input_txt04" value="%{bd}"/>~
                        <s:date name="oqo.endTime" format="yyyy-MM-dd" var="ed"/>
                        <s:textfield name="oqo.endTime" cssClass="ui_input_txt04" value="%{ed}"/>
                        货品:
                        <s:textfield name="oqo.keyword" cssClass="ui_input_txt04"/>
                        供应商:
                        <s:select list="#suppliers" name="oqo.supplierId" cssClass="ui_select02"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有供应商"/>
                        品牌:
                        <s:select list="#brands" name="oqo.brandId" cssClass="ui_select02"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有品牌"/>
                        分组:
                        <s:select
                                list="#groupTypes" listValue="groupName"
                                name="oqo.groupType" cssClass="ui_select02"/>
                        报表:
                        <s:select list="#{'line':'线形图','pie':'饼状图'}" name="chart" cssClass="ui_select02 chart"/>
                    </div>
                    <div id="box_bottom">
                        <input type="submit" value="查询" class="ui_input_btn01 btn_page"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>分组</th>
                        <th>订单总数量</th>
                        <th>订单总金额</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#orderChart">
                        <tr>
                            <td><s:property value="groupType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>
