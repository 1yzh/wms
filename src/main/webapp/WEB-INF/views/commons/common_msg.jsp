
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    <s:if test="hasActionErrors()">
    var msg  = "<s:property value="actionErrors"/>";
    $.dialog({
        title:"温馨提示",
        content:msg,
        icon:"error",
        ok:true
    });
    </s:if>
    <s:if test="hasActionMessages()">
    var msg = "<s:property value="actionMessages"/>";
    $.dialog({
        title:"温馨提示",
        content:msg,
        icon:"face-smile",
        ok:true
    });
    </s:if>
</script>
