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

    img{
        height: 120px;
        width: 100px;
    }

    table {
        text-align: center;
    }


    /*#company, #brand {*/
        /*height: 340px;*/
        /*text-align: center;*/
    /*}*/

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
<%--隐藏数据，修改需要--%>
<input id="brandName_hidden" value="" style="display: none"/>

<%--修改--%>
<div id="updateInfo" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" style="text-align: center">
    <div class="modal-dialog" style="height: 100px;width: 440px" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改品牌信息</h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <%--主要修改代码区--%>
                    <form method="post" action="companyUpdate" enctype="multipart/form-data" id="formUpload">
                        <div class="form-group">
                            <label for="brandName" style="font-size: 15px;font-weight:bold"><span
                                    class="glyphicon glyphicon-pencil"
                                    aria-hidden="true">品牌名称</span></label>
                            <input name="brandName" type="text" class="form-control" id="brandName"
                                   placeholder="brand name">
                        </div>
                        <div class="form-group">
                            <label for="uploadFile" style="font-size: 15px;font-weight:bold"><span
                                    class="glyphicon glyphicon-file" aria-hidden="true">图片上传</span></label>
                            <input type="file" accept="image/*" class="form-control" id="uploadFile" name="uploadFile">
                            <input id="brandID_hidden" value="" style="display: none" name="brandId">
                            <button class="btn btn-success"
                                    style="text-align:center;float: right;margin: 5px 100px;height: 30px;"
                                    onclick="return uploadfile()">upload
                            </button>
                        </div>
                    </form>

                    <%--结束--%>
                </div>
            </div>
            <div class="modal-footer">
                <span id="error" style="float: left;color: red; font-size: 13px;font-weight: bold"></span>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<%--模态框判断成功--%>
<div id="true" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     style="text-align: center;font-weight: bold;font-size: 20px;color: dodgerblue">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <span class="glyphicon glyphicon-triangle-right" aria-hidden="true" style="color: blue"></span>
            <span id="result_true" class="glyphicon glyphicon-scissors" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-triangle-left" aria-hidden="true" style="color: blue"></span>
        </div>
    </div>
</div>


<%--模态框判断失败--%>
<div id="false" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     style="text-align: center;font-weight: bold;font-size: 20px;color: orangered">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <span class="glyphicon glyphicon-triangle-right" aria-hidden="true" style="color: blue"></span>
            <span id="result_false" class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-triangle-left" aria-hidden="true" style="color: blue"></span>
        </div>
    </div>
</div>


<div id="company" style="text-align: center;">
    <h2 style="text-align: center">[品牌商信息]</h2>
    <table id="companyInfo" class="table table-striped table-hover">

    </table>
</div>
<div id="brand" style="text-align: center">
    <h2 style="text-align: center">[品牌信息]</h2>
    <table class="table table-hover table-striped" id="brandInfo">
    </table>
</div>
<div id="page" style="height: 50px;text-align: center">
</div>
</body>

<script type="text/javascript">

    window.onload = copyWithPage(1);

    function showResult() {
        ${reslut}
    }

    function deleteBrand(brandId) {
        if (confirm("确定要删除吗？")) {
            $.ajax({
                url: "companyDelete/" + brandId,
                async: false,
                type: "post",
                dataType: "json",
                success: function (data) {
                    var result = data.result
                    if (result == "true") {
                        $("#result_true").text("删除成功！")
                        $("#true").modal()
                    } else if (result == "false") {
                        $("#result_false").text("删除失败！")
                        $("#false").modal()
                    }
                    copyWithPage(1)
                }
            })
            return true
        } else {
            return false
        }

    }

    function uploadfile() {
        var fileList = $('#uploadFile')[0].files;
        if (fileList.length == 0 || fileList.length > 1) {
            $("#error").html("只允许上传一张图片或文件为空!")
            return false;
        }
        $("#formUpload").submit();
    }

    function updateBrand(id, name) {
        $("#brandID_hidden").val(id);
        $("#brandName_hidden").val(name);
        $("#updateInfo").modal()
        $("#brandName").val(name)
    }


    function getPage(pageNow) {
        var pageBeanInfo = null;
        var companys = null;
        var brands = null;
        var mvoTypes = null;
        $.ajax({
            url: "companyInfo/" + pageNow,
            async: false,
            type: "post",
            dataType: "json",
            success: function (data) {
                companys = data.companyList
                brands = data.brandList
                mvoTypes = data.mvoType
                pageBeanInfo = data.pageBean
            }
        })
        return {"companys": companys, "brands": brands, "mvoTypes": mvoTypes, "pageBeanInfo": pageBeanInfo}
    }

    function copyWithPage(page) {
        data = getPage(page)
        companyFill(data["companys"], data["mvoTypes"])
        brandFill(data["brands"])
        pageTail(data.pageBeanInfo)
    }

    function companyFill(companys, mvoTypes) {
        $("#companyInfo").html("")
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
        for (i = 0; i < companys.length; i++) {
            var html = '<tr><td> <span class="label label-success">' + companys[i].userId + '</span></td><td><span class="label label-warning">' +
                companys[i].userName + '</span></td><td>' +
                mvoTypes[i] + '</td><td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button> ' +
                '</td></tr>'
            $("#companyInfo").append(html)
        }

    }

    function brandFill(brands) {
        $("#brandInfo").html("")
        if (brands.length != 0) {
            var head = '\n' +
                '        <thead>\n' +
                '        <tr>\n' +
                '            <td class="success">品牌编号</td>\n' +
                '            <td class="success">品牌名称</td>\n' +
                '            <td class="success">品牌图片</td>\n' +
                '            <td class="success">操作</td>\n' +
                '        </tr>\n' +
                '        </thead>'
            $("#brandInfo").append(head)
        }

        for (let i = 0; i < brands.length; i++) {
            var html = '<tr><td><span class="label label-success">' +
                brands[i].brandId +
                '</span></td><td><span class="label label-warning">' +
                (brands[i].brandName || "暂无") +
                '</td><td>' +
                '<img src=\"' + brands[i].brandUrl +'\" alt=\"缺失图片\" class=\"img-thumbnail\">' +
                '</span></td><td>'
            console.log(html)
            var name = (brands[i].brandName || "")
            name = '\'' + name + '\''
            var button_delete = '<button type="button" class="btn btn-danger" onclick="deleteBrand(' + brands[i].brandId + ',' + name  + ')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除 </button>'
            var button_update = '<button type="button" class="btn btn-primary" onclick="updateBrand(' + brands[i].brandId + ',' + name + ')"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 修改 </button>'
            html += (button_delete + button_update + '</td></tr>')
            $("#brandInfo").append(html)
        }
    }

    function pageTail(pageBean) {
        $("#page").html("")
        //显示分页
        var pageBean = pageBean
        var html_head = "<nav aria-label=\"Page navigation\">\n" +
            "  <ul class=\"pagination\">\n" +
            "    <li>"
        var html_tail = "    </li>\n" +
            "  </ul>\n" +
            "</nav>"
        var page_content = ""
        for (var i = 1; i <= pageBean.totallPage; i++) {
            if (pageBean.pageNow == (i)) {
                page_content += "<li class = 'active'><a href=\"#\">" + i + "</a></li>"
            } else {
                page_content += "<li>" + "<a href='#'" + "onclick='copyWithPage(" + i + ")'" + ">" + i + "</a></li>"
            }
        }
        var html = html_head + page_content + html_tail;
        $("#page").append(html)


    }
</script>
</html>
