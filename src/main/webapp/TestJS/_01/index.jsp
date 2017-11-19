<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/TestJS/jquery-easyui/themes/default/easyui.css">
<!-- 样式文件 -->
<link rel="stylesheet" type="text/css" href="/TestJS/jquery-easyui/themes/icon.css">
<!-- 图标样式 -->
<script type="text/javascript" src="/TestJS/jquery-easyui/jquery.min.js"></script>
<!-- jQuery核心库 -->
<script type="text/javascript" src="/TestJS/jquery-easyui/jquery.easyui.min.js"></script>
<!-- EasyUI核心库 -->

<html>
<head>
    <title>页面布局</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="height:50px"></div>
    <div data-options="region:'south',split:true" style="height:50px;"></div>
    <div data-options="region:'west',split:true" title="West" style="width:100px;"></div>
    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'"></div>
</div>
</body>
</html>
