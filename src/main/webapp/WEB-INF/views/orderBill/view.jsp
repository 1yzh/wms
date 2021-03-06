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
    <script type="text/javascript">
        $(function () {
            $(":input").prop("readonly",true);
        })
        $(function () {
            //确定保存
            $(".btn_submit").click(function () {
                //设置每个菜单明细的数据
                $.each($("#edit_table_body tr"), function (index, item) {
                    var tr = $(item);
                    tr.find("[tag=pid]").prop("name", "orderBill.items[" + index + "].product.id");
                    tr.find("[tag=costPrice]").prop("name", "orderBill.items[" + index + "].costPrice");
                    tr.find("[tag=remark]").prop("name", "orderBill.items[" + index + "].remark");
                    tr.find("[tag=number]").prop("name", "orderBill.items[" + index + "].number");
                })
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="orderBill_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="orderBill.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单查看</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.supplier.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02"/>
                    </td>
                </tr>


                <tr>
                    <td class="ui_text_rt" width="140">订单明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <s:iterator value="orderBill.items">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield name="product.name" disabled="true" readonly="true"
                                                     cssClass="ui_input_txt04"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                    <td><s:textfield tag="costPrice" name="costPrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"><s:property value="amount"/></span></td>
                                    <td><s:textfield tag="remark" name="remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </td>
                </tr>


                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="返回" class="ui_input_btn01 btn_submit"
                                     onclick="window.history.back()"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>