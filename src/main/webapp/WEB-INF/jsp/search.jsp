<%--
  Created by IntelliJ IDEA.
  User: GreenArrow
  Date: 2019-07-27
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jsp" %>
<html>
<head>
    <title>search</title>
</head>
<style>
    img {
        height: 120px;
        width: 100px;
    }
</style>
<body>
<div id="brand" style="text-align: center">
    <h2 style="text-align: center">[品牌信息]
        <button class="btn btn-primary" onclick="getBrandSearch()" type="submit"><span id="basic-addon1"><span
                class="glyphicon glyphicon-search"
                aria-hidden="true"></span></span>
        </button>
        <input type="text" placeholder="brandName" aria-describedby="basic-addon1" id="searchKey">
    </h2>
    <table class="table table-hover table-striped" id="brandInfo">

    </table>
</div>
</body>
<script type="text/javascript">
    function getBrandSearch() {
        $("#brandInfo").html("")
        search_name = $("#searchKey").val();
        if (search_name == null) {
            search_name = ""
        }
        $.ajax({
            url: "/online_retailer/Brand/brandSearchResult/" + search_name,
            async: false,
            type: "post",
            dataType: "json",
            success: function (data) {
                brandList = data.brandList;
                console.log(brandList)
                $("#brandInfo").html();

                if (brandList.length != 0) {
                    var head = '\n' +
                        '        <thead>\n' +
                        '        <tr>\n' +
                        '            <td class="success">品牌编号</td>\n' +
                        '            <td class="success">品牌名称</td>\n' +
                        '            <td class="success">品牌图片</td>\n' +
                        '            <td class="success">品牌商家</td>\n' +
                        '        </thead>'
                    $("#brandInfo").append(head)
                }


                for (let i = 0; i < brandList.length; i++) {
                    var html = '<tr><td><span class="label label-success">' +
                        brandList[i].brandId +
                        '</span></td><td><span class="label label-warning">' +
                        (brandList[i].brandName || "No Name!") +
                        '</td><td>' +
                        '<img src=\"' + brandList[i].brandUrl + '\" alt=\"缺失图片\" class=\"img-thumbnail\">' +
                        '</span></td><td>' +
                        '</span></td><td><span class="label label-info">' +
                        (brandList[i].brandMerId)
                    html += ('</td></tr>')
                    $("#brandInfo").append(html)
                }
            }
        })
    }
</script>
</html>
