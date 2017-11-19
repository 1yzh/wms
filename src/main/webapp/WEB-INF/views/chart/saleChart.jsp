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
    <title>PSS-销售报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("input[name='sqo.beginTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate:$("input[name='sqo.endTime']").val()||new Date()
                });
            });
            $("input[name='sqo.endTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    minDate:$("input[name='sqo.beginTime']").val()
                });
            });
            $(".chart").change(function () {
                var url=$("#searchForm").serialize();
                if("line"==this.value){
                    showModalDialog("/chart_saleChartByLine.action?"+url,"","dialogWidth=1200px;dialogHeight=600px");
                }else if("pie"==this.value){
                    showModalDialog("/chart_saleChartByPie.action?"+url,"","dialogWidth=1200px;dialogHeight=600px");
                }
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form id="searchForm" namespace="/" action="chart_saleChart" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间:
                        <s:date name="sqo.beginTime" format="yyyy-MM-dd" var="bd"/>
                        <s:textfield name="sqo.beginTime" cssClass="ui_input_txt04" value="%{bd}"/>~
                        <s:date name="sqo.endTime" format="yyyy-MM-dd" var="ed"/>
                        <s:textfield name="sqo.endTime" cssClass="ui_input_txt04" value="%{ed}"/>
                        货品:
                        <s:textfield name="sqo.keyword" cssClass="ui_input_txt04"/>
                        客户:
                        <s:select list="#clients" name="sqo.clientId" cssClass="ui_select02"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有供应商"/>
                        品牌:
                        <s:select list="#brands" name="sqo.brandId" cssClass="ui_select02"
                                  listKey="id" listValue="name" headerKey="-1" headerValue="所有品牌"/>
                        分组:
                        <s:select list="#groupTypes" listValue="groupName" name="sqo.groupType" cssClass="ui_select01"/>
                        图标:
                        <s:select list="#{'line':'线性图','pie':'饼状图'}" cssClass="ui_select02 chart"/>
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
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#saleChart">
                        <tr>
                            <td><s:property value="groupType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="grossProfit"/></td>
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
