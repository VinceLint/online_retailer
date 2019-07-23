<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>品牌商管理</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
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
    font{
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

    <form action="user-company.jsp" method="post" onsubmit="return checkform()">
        <div class="form-group">
            <label for="ChineseName">公司中文名称 Company Name</label>
            <input type="text" class="form-control" id="ChineseName" placeholder="Company Name">
            <span ><font color="red" id="errorChineseName" >${errorInfoChineseName }</font></span>
        </div>
        <br>
        <div class="form-group">
            <label for="EnglishName">公司名称 Company Name</label>
            <input type="text" class="form-control" id="EnglishName" placeholder="Company Name">
            <span><font color="red" id="errorEnglishName">${errorInfoEnglishName }</font></span>
        </div>
        <br>
        <div class="form-group">
            <label for="Telephone">公司电话 Telephone</label>
            <input type="text" class="form-control" id="Telephone" placeholder="Company Name">
            <span><font color="red" id="errorTelephone">${errorInfoTelephone }</font></span>
        </div>
        <br>
        <div class="form-group">
            <label for="Email">公司邮箱 Email</label>
            <input type="text" class="form-control" id="Email" placeholder="Email">

        </div>
        <br>

        <div class="form-group-lg">
            <label for="Introduction">公司简介 Introduction</label>
            <textarea id="Introduction" class="form-control" placeholder="Introduction" rows="5"></textarea>
        </div>
        <br>

        <div class="form-group-lg">
            <label for="GMC-Report-Type">品牌商认证类型 GMC Report Type</label>
            <textarea id="GMC-Report-Type" class="form-control" placeholder="GMC Report" rows="5"></textarea>
        </div>
        <br>

        <div class="form-group-lg">
            <label for="GMC-Report-Url">品牌商认证类型 GMC Report url</label>
            <textarea id="GMC-Report-Url" class="form-control" placeholder="GMC Report Url" rows="5"></textarea>
        </div>

        <div class="form-group">
            <label for="Logo">选择商标Logo</label>
            <input type="file" id="Logo">
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
