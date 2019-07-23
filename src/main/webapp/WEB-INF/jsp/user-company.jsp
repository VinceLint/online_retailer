<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>品牌商管理</title>
</head>
<!--  boostrap jquery在线引用，需要联网  -->
<!-- Latest compiled and minified CSS -->
<script typet="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
      crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
      integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
      crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<style>
    .container {
        width: 50%;

    }

    label {
        color: #1c4374;
        font: 18px pxGeorgia, "Times New Roman", Times, serif;
    }

    font {
        font-weight: bold;
        font-size: medium;
    }


</style>
<script type="text/javascript">
    function checkform() {
        var chinese =
            $("#ChineseName").val();
        var english =
            $("#EnglishName").val();
        var telephone = $("#Telephone").val();
        if (chinese == null || chinese == "") {
            $("#errorChineseName").html("公司中文名不能为空");
            return false;
        } else {
            $("#errorChineseName").html("");
        }
        if (english == null || english == "") {
            $("#errorEnglishName").html("公司英文名不能为空");
        } else {
            $("#errorEnglishName").html("");
        }

        if (telephone == null || telephone == "") {
            $("#errorTelephone").html("电话号码不能为空");
            return false;
        } else {
            $("#errorTelephone").html("");
        }

        return true;
    }
</script>
<body>
<div class="container">
    <form action="/online_retailer/company" method="post" onsubmit="return checkform()">
        <div class="form-group">
            <label>公司中文名称 Company Name</label> <span><font color="red"
                                                           id="errorChineseName">${errorInfoChineseName }</font></span>

            <input type="text" id="ChineseName" class="form-control" name="userName" placeholder="Company Name"/>
        </div>
        <br>
        <div class="form-group">
            <label>公司英文名称 English Name</label> <span><font color="red"
                                                         id="errorEnglishName">${errorInfoEnglishName }</font></span>

            <input type="text" id="EnglishName" class="form-control" name="mvoEngName" placeholder="Company Name"/>
        </div>
        <br>
        <div class="form-group">
            <label>公司电话 Telephone</label> <span><font color="red"
                                                      id="errorTelephone">${errorInfoTelephone }</font></span>

            <input type="text" id="Telephone" class="form-control" name="userPhone" placeholder="Company Name"/>
        </div>
        <br>
        <div class="form-group">
            <label>公司邮箱 Email</label>
            <input type="text" class="form-control" name="userMail" placeholder="Email"/>
        </div>
        <br>

        <div class="form-group-lg">
            <label>公司简介 Introduction</label>
            <textarea name="mvoIntroduction" class="form-control" placeholder="Introduction" rows="5"></textarea>
        </div>
        <br>

        <div class="form-group-lg">
            <label>公司类型 GMC Report Type</label>
            <select id="type" class="selectpicker">
                <c:forEach var="item" items="${typeList}">
                    <option value="${item.key}">${item.value}</option>
                </c:forEach>
            </select>

        </div>
        <br>


        <div class="form-group-lg">
            <label>品牌商认证url GMC Report url</label>
            <input name="mvoUrl" class="form-control" placeholder="GMC Report Url"></input>
        </div>

        <div class="form-group">
            <label>选择商标Logo</label>
            <input type="file" name="Logo"/>
        </div>
        <br>

        <button type="submit" class="btn btn-success" style="width: 30%"><span class="glyphicon glyphicon-ok"
                                                                               aria-hidden="true"></span>提交
        </button>
        <button type="reset" class="btn btn-info" style="width: 30%;float: right"><span
                class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
        </button>
    </form>
</div>
</body>
</html>
