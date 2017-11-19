
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="ui_tb_h30">
    <div class="ui_flt" style="height: 30px; line-height: 30px;">
        共有
        <span class="ui_txt_bold04"><s:property value="#pageResult.totalCount"/></span>
        条记录，当前第
        <span class="ui_txt_bold04"><s:property value="#pageResult.currentPage"/>/<s:property value="#pageResult.totalPage"/></span>
        页
    </div>
    <div class="ui_frt">
        <%--此处的核心是将哪一页传给当前页,比如把nextpage传给当前页,就表示向后翻页.--%>
        <input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
        <input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageResult.prevPage"/>"/>
        <input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageResult.nextPage"/>"/>
        <input type="button" value="尾页" class="ui_input_btn01 btn_page totalPage" data-page="<s:property value="#pageResult.totalPage"/>"/>

        <s:select list="{5,10,20,50}" name="qo.pageSize" class="ui_select02"/>
        <%--先从此处着手，确定好当前页，然后再进行其他页设置。--%>
        转到第<s:textfield name="qo.currentPage" class="ui_input_txt01" style="weith:35px"/>页
        <input type="button" class="ui_input_btn01 btn_page" value="跳转"/>
    </div>
</div>