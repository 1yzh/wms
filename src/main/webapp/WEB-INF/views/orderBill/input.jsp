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
    <script type="text/javascript" src="/js/plugins/my97datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function removeData(copy) {//抽取出清空订单明细数据的方法
            copy.find("[tag=name]").val("");
            copy.find("[tag=pid]").val("");
            copy.find("[tag=brand]").text("");
            copy.find("[tag=costPrice]").val("");
            copy.find("[tag=remark]").val("");
            copy.find("[tag=number]").val("");
            copy.find("[tag=amount]").text("");
        }

        $(function () {
            $("#edit_table_body").on("click", ".searchproduct", function () {//统一事件,点击放大镜加载明细
                var productV = window.showModalDialog("product_showSelectProduct.action", "", "dialogWidth=1200px;dialogHeight=600px")
                var tr = $(this).closest("tr");
                tr.find("[tag=name]").val(productV.name);
                tr.find("[tag=pid]").val(productV.id);
                tr.find("[tag=brand]").text(productV.brandName);
                tr.find("[tag=costPrice]").val(productV.costPrice);
            }).on("blur", "input[tag=costPrice],input[tag=number]", function () {//统一事件,失去焦点事件
                var tr = $(this).closest("tr");
                var costPrice = tr.find("input[tag=costPrice]").val();
                var number = tr.find("input[tag=number]").val();
                tr.find("[tag=amount]").text((costPrice * number).toFixed(2));
            }).on("click", ".removeItem", function () {//统一事件,点击删除明细
                var tr = $(this).closest("tr");
                if ($("#edit_table_body tr").size() > 1) {//所有#edit_table_body元素下的tr组成一个数组,他的size大于1
                    tr.remove();
                } else {
                    removeData(tr);//调用上面清空明细数据代码
                }
            })
            //追加明细
            $(".appendRow").click(function () {
                var copy = $("#edit_table_body tr:first").clone();//复制tr元素,clone里面用true参数表示将tr元素的事件一起复制,
                // 上面使用了统一事件,此处不用true参数了
                copy.appendTo($("#edit_table_body"))//将tr元素添加到tbody中,用下面代码清空复制的数据
                removeData(copy);//调用上面清空明细数据代码
            });
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
                $("#editForm").submit();//表单提交,而不是元素提交
            });

            $("input[name='orderBill.vdate']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate:new Date()
                });

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
            <span style="color: #1A5CC6;">采购订单编辑</span>
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
                        <s:select list="#suppliers" name="orderBill.supplier.id" listKey="id" listValue="name"
                                  cssClass="ui_select03"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:date name="orderBill.vdate" format="yyyy-MM-dd" var="vd"/>
                        <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02" value="%{vd}"/>
                    </td>
                </tr>


                <tr>
                    <td class="ui_text_rt" width="140">订单明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
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
                            <s:if test="orderBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="orderBill.items.product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="costPrice" name="orderBill.items.costPrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="orderBill.items.number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="orderBill.items.remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:if>
                            <s:else>
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
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>


                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>