<%--
  Created by IntelliJ IDEA.
  User: GreenArrow
  Date: 2019-07-24
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp" %>
<html>
<head>
    <title>company_brand</title>
</head>
<style>
    table {
        text-align: center;
    }

    div {
        height: 340px;
    }

    thead {
        font-weight: bold;
    }

    td {
        font-size: 20px;
        vertical-align: center;
        horiz-align: center;
    }

    body {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: black;
        line-height: 25px;
        letter-spacing: 1px
    }
</style>
<body>
<div id="company">
    <h2 style="text-align: center">[品牌商信息]</h2>
    <table id="companyInfo" class="table table-striped table-hover">

    </table>
</div>
<div id="brand">
    <h2 style="text-align: center">[品牌信息]</h2>
    <table class="table table-hover table-striped" id="brandInfo">
    </table>
</div>
<div id="page" style="height: 50px">

</div>
</body>

<script type="text/javascript">
    window.onload = page(3);
    function page(pageNow) {
        $.ajax({
            url: "companyInfo/" + pageNow,
            type: "post",
            dataType: "json",
            success: function (data) {
                //显示数据
                var companys = data.companyList
                var brands = data.brandList
                var mvoTypes = data.mvoType
                if (companys.length != 0) {
                    var head = '    <thead>\n' +
                        '    <tr>\n' +
                        '        <td class="warning">品牌商ID</td>\n' +
                        '        <td class="warning">品牌商中文名</td>\n' +
                        '        <td class="warning">品牌商英文名</td>\n' +
                        '        <td class="warning">操作</td>\n' +
                        '    </tr>\n' +
                        '    </thead>'
                    $("#companyInfo").append(head)
                }
                if (brands.length != 0) {
                    var head = '\n' +
                        '        <thead>\n' +
                        '        <tr>\n' +
                        '            <td class="success">品牌名称</td>\n' +
                        '            <td class="success">品牌商ID</td>\n' +
                        '            <td class="success">品牌图片</td>\n' +
                        '            <td class="success">操作</td>\n' +
                        '        </tr>\n' +
                        '        </thead>'
                    $("#brandInfo").append(head)
                }
                for (i = 0; i < companys.length; i++) {
                    var html = '<tr><td> <span class="label label-success">' + companys[i].userId + '</span></td><td><span class="label label-warning">' +
                        companys[i].userName + '</span></td><td>' +
                        mvoTypes[i] + '</td><td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button> ' +
                        '</td></tr>'
                    $("#companyInfo").append(html)
                }
                for (i = 0; i < brands.length; i++) {
                    var html = '<tr><td><span class="label label-success">' + brands[i].brandId + '</span></td><td><span class="label label-warning">' +
                        brands[i].brandMerId + '</td><td>' +
                        brands[i].brandUrl + '</span></td><td><button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>修改</button> ' +
                        '</td></tr>'
                    $("#brandInfo").append(html)
                }
                //显示分页
                var pageBean = data.pageBean
                var html_head = "<nav aria-label=\"Page navigation\">\n" +
                    "  <ul class=\"pagination\">\n" +
                    "    <li>"
                var html_tail = "    </li>\n" +
                    "  </ul>\n" +
                    "</nav>"
                var page_content = ""
                for (var i = 1; i <= pageBean.totallPage; i++) {
                    if (pageBean.pageNow == (i)) {
                        page_content += "<li class=\"active\"><a href=\"companyInfo/\">" + (i) + "</a></li>"
                    }
                    else {
                        page_content += "<li><a href=\"#\">" + (i) + "</a></li>"
                    }
                }
                var html = html_head + page_content + html_tail;
                $("#page").append(html)
            }
        });

    }
</script>
</html>
