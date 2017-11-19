<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>订货报表</title>

    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script src="/js/plugins/highcharts/highcharts.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                title: {
                    text: '订货报表',
                    x: -20 //center
                },
                subtitle: {
                    text: '按<s:property value="#groupName"/>划分',
                    x: -20
                },
                xAxis: {
                    categories: <s:property value="types" escapeHtml="false"/>
                },
                yAxis: {
                    title: {
                        text: '总金额 (￥)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '采购总金额',
                    data: <s:property value="totalAmounts" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>


<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>
