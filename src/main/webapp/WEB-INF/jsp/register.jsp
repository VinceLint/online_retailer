<%--
  Created by IntelliJ IDEA.
  User: rreez
  Date: 2019/7/23
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册页面</title>
</head>
<body>
<h3>注册页面</h3>
<hr/>
<form action="${pageContext.request.contextPath}/user/doRegist" method="post">
    <table border="1px">
        <tr height="35px">
            <td width="150px">用户名</td>
            <td width="300px">
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr height="35px">
            <td>密码</td>
            <td>
                <input type="text" name="password" id="password">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" id="regist" value="注册"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>