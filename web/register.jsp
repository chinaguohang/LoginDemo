<%--
  Created by IntelliJ IDEA.
  User: guohang
  Date: 2017/10/22
  Time: 下午3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form name="frm1" action="${pageContext.request.contextPath }/admin?method=register" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td>
                <input type="text" name="userName">
                ${requestScope.message} <!-- 如果用户名存在注册失败，给用户提示 -->
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="亲，点我注册！">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
