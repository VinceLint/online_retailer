<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>品牌商管理</title>
</head>
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
            return false;
        } else {
            $("#errorEnglishName").html("");
        }

        if (telephone == null || telephone == "") {
            $("#errorTelephone").html("电话号码不能为空");
            return false;
        } else {
            $("#errorTelephone").html("");
        }

        var state = $('#type option:selected').val();
        if (state == null){
            state = 0
        }
        $("#state").val(state)
        return true;
    }

    $("#type").change(function () {
        var state = $('#type option:selected').val();
        $("#state").val(state)
    })

    window.onload = function () {
        if (${msg.status == 1}) {
            $('.bs-example-modal-sm').modal()
            $('.bs-example-modal-sm').css({color: "lawngreen"})
        } else if (${msg.status == 0}) {
            $('.bs-example-modal-sm').modal()
            $('.bs-example-modal-sm').css({color: "red"})
        }
    }
</script>
<body>


<%--模态框展示修改结果--%>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     style="text-align: center;font-weight: bold;font-size: 20px;color: lawngreen">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <span class="glyphicon glyphicon-triangle-right" aria-hidden="true" style="color: blue"></span>
            <span class="glyphicon glyphicon-${msg.icon}" aria-hidden="true"></span>${msg.describe}
            <span class="glyphicon glyphicon-triangle-left" aria-hidden="true" style="color: blue"></span>
        </div>
    </div>
</div>

<div class="container">
    <form action="company" method="post" onsubmit="return checkform()">
        <div class="form-group">
            <label>公司中文名称 Company Name</label> <span><font color="red"
                                                           id="errorChineseName">${errorInfoChineseName }</font></span>

            <input type="text" id="ChineseName" class="form-control" name="userName" placeholder="Company Name"
                   value="${user.userName}"/>
        </div>
        <br>
        <div class="form-group">
            <label>公司英文名称 English Name</label> <span><font color="red"
                                                           id="errorEnglishName">${errorInfoEnglishName }</font></span>

            <input type="text" id="EnglishName" class="form-control" name="mvoEngName" placeholder="Company Name"
                   value="${user.mvoEngName}"/>
        </div>
        <br>
        <div class="form-group">
            <label>公司电话 Telephone</label> <span><font color="red"
                                                      id="errorTelephone">${errorInfoTelephone }</font></span>

            <input type="text" id="Telephone" class="form-control" name="userPhone" placeholder="Company Name"
                   value="${user.userPhone}"/>
        </div>
        <br>
        <div class="form-group">
            <label>公司邮箱 Email</label>
            <input type="text" class="form-control" name="userMail" placeholder="Email" value="${user.userMail}"/>
        </div>
        <br>

        <div class="form-group-lg">
            <label>公司简介 Introduction</label>
            <textarea name="mvoIntroduction" class="form-control" placeholder="Introduction"
                      rows="5">${user.mvoIntroduction}</textarea>
        </div>
        <br>

        <div class="form-group-lg">
            <label>公司类型 GMC Report Type</label>

            <input type="hidden" id="state" name="mvoType" class="projectfile" value="0"/>
            <select id="type" class="selectpicker">
                <c:forEach var="item" items="${typeList}">
                    <c:if test="${item.key == user.mvoType}">
                        <option value="${item.key}" selected=selected>${item.value}</option>
                    </c:if>
                    <option value="${item.key}">${item.value}</option>
                </c:forEach>
            </select>

        </div>
        <br>


        <div class="form-group-lg">
            <label>品牌商认证url GMC Report url</label>
            <input name="mvoUrl" class="form-control" placeholder="GMC Report Url" value="${user.mvoUrl}"></input>
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
