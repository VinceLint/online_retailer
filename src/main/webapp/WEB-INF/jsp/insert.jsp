<%--
  Created by IntelliJ IDEA.
  User: xiaoxi
  Date: 2019/7/23
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>insert</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/uimaker/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/uimaker/dialog.css">



</head>

<script>

    $(function () {

        $('#dialog').dialog({
            title: "新增商品",
            width: 700,
            height: 620,
            buttons: [
                {
                    text: "保存",
                    icons_Cls: 'icon-save',
                    handler: function () {
                        $('#myform').form('submit', {
                            url: "${pageContext.request.contextPath}/Goods/queryInsert"
                        });
                        alert("保存成功");
                        $('#dialog').dialog('close');
                        /*window.location.reload();*/
                        window.location.href = "${pageContext.request.contextPath}/Goods/insert";
                    }
                },
                {
                    text: "取消",
                    icons_Cls: 'icon-cancel',
                    handler: function () {
                        $('#dialog').dialog('close');
                    }
                }
            ]
        });

        $('#dialog2').dialog({
            title: "编辑商品",
            width: 700,
            height: 600,
            buttons: [
                {
                    text: "保存",
                    icons_Cls: 'icon-save',
                    handler: function () {
                        var rowData = $('#dg').datagrid('getSelected');
                        $('#myform2').form('submit', {
                            url: "${pageContext.request.contextPath}/Goods/queryUpdate",
                            onSubmit: function (param) {
                                param.goodsId = rowData.goodsId;
                            },
                            success: function () {
                                alert("修改成功");
                                $('#dialog2').dialog('close');
                                window.location.href = "${pageContext.request.contextPath}/Goods/insert";
                            }

                        });
                    }
                },
                {
                    text: "取消",
                    icons_Cls: 'icon-cancel',
                    handler: function () {
                        $('#dialog2').dialog('close');
                    }
                }
            ]
        });

        $('#dialog').dialog('close');

        $('#dialog2').dialog('close');

        $('#add').click(function () {
            /*清空表单*/
            $('#myform').form('clear');
            $('#dialog').dialog('open');
        });

        $('#edit').click(function () {
            /*判断是否选择了数据*/
            var rowData = $('#dg').datagrid('getSelected');
            if (!rowData) {
                alert('请选择一条数据进行编辑');
                return;
            } else {
                $('#myform2').form('clear');
                $('#dialog2').dialog('open');

                /*数据回显*/
                console.log(rowData);

                $('#myform2').form('load', {
                    title: rowData.goodsTitle,
                    price: rowData.goodsPrice,
                    amount: rowData.goodsAmount,
                    clazz: rowData.goodsClass,
                    describe: rowData.goodsDescribe,
                    length: rowData.goodsLength,
                    width: rowData.goodsWidth,
                    height: rowData.goodsHeight,
                    weight: rowData.goodsWeight

                });
            }
        });

        $('#del').click(function () {
            var rowData = $('#dg').datagrid('getSelected');
            if (!rowData) {
                alert('请选择一条数据进行删除');
            } else {
                var msg = "您确认要删除这条商品信息吗？"
                if (confirm(msg) == true) {
                    window.location.href = '${pageContext.request.contextPath}/Goods/queryDelete/' + rowData.goodsId;
                } else {
                    window.location.reload();
                }
            }
        });

        $('#clazz').combobox({
            editable: false,
            valueField: 'value',
            textField: 'label',
            data: [{
                label: "家用电器",
                value: 0
            }, {
                label: "日用品",
                value: 2
            }, {
                label: "数码产品",
                value: 3
            }],
            width: 160,
            panelHeight: 'auto'
        });

        $('#clazz2').combobox({
            editable: false,
            valueField: 'value',
            textField: 'label',
            data: [{
                label: "家用电器",
                value: 0
            }, {
                label: "日用品",
                value: 2
            }, {
                label: "数码产品",
                value: 3
            }],
            width: 160,
            panelHeight: 'auto'
        });

        $('#dg').datagrid({
            url: "${pageContext.request.contextPath}/Goods/searchGoods",
            columns: [[
                {field: 'goodsId', title: '商品sku码', width: 100, align: 'center'},
                {field: 'goodsTitle', title: '商品标题', width: 100, align: 'center'},
                {field: 'goodsPrice', title: '商品价格', width: 100, align: 'center'},
                /*{field:'brandId',title:'品牌商编号',width:100,align:'center'},*/
                {field: 'goodsAmount', title: '库存', width: 100, align: 'center'},
                {
                    field: 'goodsClass',
                    title: '分类',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (row.goodsClass == 0) {
                            return "家用电器";
                        } else if (row.goodsClass == 2) {
                            return "日用品";
                        } else if (row.goodsClass == 3) {
                            return "数码产品";
                        }
                    }
                },
                {
                    field: 'goodsStatus',
                    title: '状态',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (row.goodsStatus == 0) {
                            return "<font color='red'>待入仓</font>";
                        } else if (row.goodsStatus == 1) {
                            return "<font color='orange'>待上架</font>";
                        } else if (row.goodsStatus == 2) {
                            return "<font color='#6495ed'>上架中</font>";
                        }
                    }
                },
                {
                    field: '_operator',
                    title: '操作',
                    width: 100,
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (row.goodsStatus == 0) {
                            var btn_1 = '<a href="#" name="btn_1" class="easyui-linkbutton" onclick="putin(' + row.goodsId + ')"></a>';
                            return btn_1;
                        } else if (row.goodsStatus == 1) {
                            var btn_2 = '<a href="#" name="btn_2" class="easyui-linkbutton" onclick="onshelves(' + row.goodsId + ')"></a>';
                            return btn_2;
                        } else if (row.goodsStatus == 2) {
                            var btn_3 = '<a href="#" name="btn_3" class="easyui-linkbutton" onclick="offshelves(' + row.goodsId + ')"></a>';
                            return btn_3;
                        }
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $("a[name='btn_1']").linkbutton({text: '入仓', plain: true, iconCls: 'icon-back'});
                $("a[name='btn_2']").linkbutton({text: '上架', plain: true, iconCls: 'icon-redo'});
                $("a[name='btn_3']").linkbutton({text: '下架', plain: true, iconCls: 'icon-undo'});
            },
            fit: true, //填充页面
            fitColumns: true, //每一列宽度填充
            striped: true, //斑马线效果
            singleSelect: true, //只能单行选中
            rownumbers: true, //行号
            toolbar: '#tb',
            pagination: true
        });

    });

    function putin(goodsId) {
        console.log(goodsId);
        window.location.href = "${pageContext.request.contextPath}/Goods/putin/" + goodsId;
        alert("入仓成功");
    }

    function onshelves(goodsId) {
        console.log(goodsId);
        window.location.href = "${pageContext.request.contextPath}/Goods/onshelves/" + goodsId;
        alert("上架成功");
    }

    function offshelves(goodsId) {
        console.log(goodsId);
        window.location.href = "${pageContext.request.contextPath}/Goods/offshelves/" + goodsId;
        alert("下架成功");
    }

    $.extend($.fn.validatebox.defaults.rules,{
        numberCheckSub : {
            validator : function(value) {
                return /^[0-9]+$/.test(value);},
            message : "只能输入数字"
        },
        stringCheckSub : {
            validator : function(value) {
                return /^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(value);},
            message : "只能包括中文字、英文字母、数字"
        }
    })

</script>

<body>

<%--选项栏--%>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="del">删除</a>
    <a href="${pageContext.request.contextPath}/Goods/insert" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" id="rel">刷新</a>
</div>

<%--新增商品数据表单--%>
<div id="dialog">
    <form id="myform" method="post" enctype="multipart/form-data">
        <table align="center" style="border-spacing: 0px 10px">
            <tr>
                <td>商品标题：</td>
                <td><input type="text" name="title" style="width:400px;" class="easyui-validatebox"
                           data-options="required:true, validType:'stringCheckSub'"></td>
            </tr>
            <tr>
                <td>商品sku码：</td>
                <td><input type="text" name="id" style="width:400px;" class="easyui-validatebox"
                           data-options="required:true, validType:'numberCheckSub'"></td>
            </tr>
            <tr>
                <td>商品价格：</td>
                <td><input type="text" name="price" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;元
                </td>
            </tr>
            <tr>
                <td>商品库存：</td>
                <td><input type="text" name="amount" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true, validType:'numberCheckSub'"></td>
            </tr>
            <tr>
                <td>商品分类：</td>
                <td><select id="clazz" name="clazz" style="width:400px;"></select></td>
            </tr>
            <tr>
                <td>商品描述：</td>
                <td><textarea name="describe" style="width:400px;height:100px;"></textarea></td>
            </tr>
            <tr>
                <td>商品规格:</td>
                <td></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;长:</td>
                <td><input type="text" name="length" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;m
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;宽:</td>
                <td><input type="text" name="width" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;m
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;高:</td>
                <td><input type="text" name="height" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;m
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;重量:</td>
                <td><input type="text" name="weight" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;kg
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;主图:</td>
                <td><input type="file" name="file" accept="image/png,image/jpeg"></td>
            </tr>
        </table>
    </form>
</div>

<%--编辑商品数据表单--%>
<div id="dialog2">
    <form id="myform2" method="post" enctype="multipart/form-data">
        <table align="center" style="border-spacing: 0px 10px">
            <tr>
                <td>商品标题：</td>
                <td><input type="text" name="title" style="width:400px;" class="easyui-validatebox"
                           data-options="required:true"></td>
            </tr>
            <tr>
                <td>商品价格：</td>
                <td><input type="text" name="price" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;元
                </td>
            </tr>
            <tr>
                <td>商品库存：</td>
                <td><input type="text" name="amount" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true"></td>
            </tr>
            <tr>
                <td>商品分类：</td>
                <td><select id="clazz2" name="clazz" style="width:400px;"></select></td>
            </tr>
            <tr>
                <td>商品描述：</td>
                <td><textarea name="describe" style="width:400px;height:100px;"></textarea></td>
            </tr>
            <tr>
                <td>商品规格:</td>
                <td></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;长:</td>
                <td><input type="text" name="length" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;m
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;宽:</td>
                <td><input type="text" name="width" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;m
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;高:</td>
                <td><input type="text" name="height" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;m
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;重量:</td>
                <td><input type="text" name="weight" style="width:200px;" class="easyui-validatebox"
                           data-options="required:true">&nbsp;kg
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;&nbsp;主图:</td>
                <td><input type="file" name="file" accept="image/png,image/jpeg"></td>
            </tr>
        </table>
    </form>
</div>

<%--商品数据列表--%>
<table id="dg"></table>

</body>
</html>
