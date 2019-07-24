<%--
  Created by IntelliJ IDEA.
  User: 胡献涛
  Date: 2019/7/23
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <script src="js/jquery-3.4.1.js"></script>
    <style>
        body,html{
            width: 100%;
            height: 100%;
            background: #fff;
        }

        .info div{
            float: right;
        }

    </style>
</head>

<body>

<div id="main-content" class="clearfix">
    <div id="page-content" class="clearfix">

        <div class="page-header position-relative">
            <h1 style="color: #2679b5;">品牌商<small><i class="icon-double-angle-right"></i> 订单管理</small></h1>
        </div>
        <form class="form-search">
            商品标题：
            <input type="text" class="input-medium search-query">
            <button onclick="return false;" class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
        </form>

        <div class="tabbable">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active"><a data-toggle="tab" href="#AwaitingPayment"> 待支付</a></li>
                <li class=""><a data-toggle="tab" href="#AwaitingShipment"> 待发货</a></li>
                <li class=""><a data-toggle="tab" href="#shipped">已发货</a></li>
                <li class=""><a data-toggle="tab" href="#complete">已完成</a></li>
                <li class=""><a data-toggle="tab" href="#canceled">已取消</a></li>
            </ul>
            <div class="tab-content">
                <div id="AwaitingPayment" class="tab-pane active">
                    <p>
                    <table id="table_bug_report1" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                            </th>
                            <th>商品标题</th>
                            <th>价格</th>
                            <th class="hidden-480">数量</th>
                            <th>sku</th>
                            <th class="hidden-480">订单编号</th>
                            <th class="hidden-480">订单创建时间</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">max.com</a></td>
                            <td>$60</td>
                            <td class="hidden-480">4</td>
                            <td class="">GM100320</td>
                            <td class="hidden-480">75684894</td>
                            <td>
                                2017-23-02 19:00:20
                            </td>

                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">best.com</a></td>
                            <td>$75</td>
                            <td class="hidden-480">6</td>
                            <td class="hidden-phone">GM100320</td>
                            <td class="hidden-480">75684894</td>
                            <td>2017-23-02 19:00:20</td>

                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">pro.com</a></td>
                            <td>$55</td>
                            <td>4</td>
                            <td>GM100320</td>
                            <td>75684894</td>
                            <td>2017-23-02 19:00:20</td>

                        </tr>

                        </tbody>
                    </table>
                    </p>
                </div>
                <div id="AwaitingShipment" class="tab-pane">
                    <p>
                    <table id="table_bug_report2" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                            </th>
                            <th>商品标题</th>
                            <th>价格</th>
                            <th class="hidden-480">数量</th>
                            <th>sku</th>
                            <th class="hidden-480">订单编号</th>
                            <th class="hidden-480">订单创建时间</th>

                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">best.com</a></td>
                            <td>$75</td>
                            <td class="hidden-480">6,500</td>
                            <td class="">Apr 03</td>
                            <td class="hidden-480">75684894</td>
                            <td>

                            </td>
                            <td>
                                <button class="btn btn-minier btn-purple send-out">发货</button>
                            </td>
                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">pro.com</a></td>
                            <td>$55</td>
                            <td class="hidden-480">4,250</td>
                            <td class="">Jan 21</td>
                            <td class="hidden-480">75684894</td>
                            <td>

                            </td>
                            <td>
                                <button class="btn btn-minier btn-purple send-out">发货</button>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                    </p>
                </div>
                <div id="shipped" class="tab-pane">
                    <p>
                    <table id="table_bug_report3" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                            </th>
                            <th>商品标题</th>
                            <th>价格</th>
                            <th class="hidden-480">数量</th>
                            <th>sku</th>
                            <th>订单编号</th>
                            <th class="hidden-480">订单创建时间</th>
                            <th class="hidden-480">物流跟踪号</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>

                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input" ><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">ace.com</a></td>
                            <td>$45</td>
                            <td class="hidden-480">2</td>
                            <td>GM100320</td>
                            <td>
                                52223566
                            </td>
                            <td>Feb 12</td>
                            <td><a href="brand-ordertracking.html">46578990890</a></td>
                            <td>
                                <button class="btn btn-minier btn-yellow cancel">取消</button>
                            </td>
                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">base.com</a></td>
                            <td>$35</td>
                            <td class="hidden-480">2,595</td>
                            <td>GM100320</td>
                            <td>8755765</td>
                            <td>Feb 18</td>
                            <td><a href="brand-ordertracking.html">75684894</a></td>

                            <td>
                                <button class="btn btn-minier btn-yellow cancel">取消</button>
                            </td>
                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">max.com</a></td>
                            <td>$60</td>
                            <td>10</td>
                            <td>

                            </td>
                            <td>867686875</td>
                            <td>Mar 11</td>
                            <td><a href="brand-ordertracking.html">75684894</a></td>

                            <td>
                                <button class="btn btn-minier btn-yellow cancel">取消</button>
                            </td>
                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">best.com</a></td>
                            <td>$75</td>
                            <td class="hidden-480">6</td>
                            <td>

                            </td>
                            <td>43678989</td>
                            <td class="hidden-phone">Apr 03</td>
                            <td class="hidden-480"><a href="brand-ordertracking.html">75684894</a></td>

                            <td>
                                <button class="btn btn-minier btn-yellow cancel">取消</button>
                            </td>
                        </tr>




                        </tbody>
                    </table>
                    </p>
                </div>
                <div id="complete" class="tab-pane">
                    <p>
                    <table id="table_bug_report4" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                            </th>
                            <th>商品标题</th>
                            <th>价格</th>
                            <th class="hidden-480">数量</th>
                            <th>sku</th>
                            <th>订单编号</th>
                            <th class="hidden-480">订单创建时间</th>
                            <th class="hidden-480">物流跟踪号</th>

                        </tr>
                        </thead>

                        <tbody>

                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">ace.com</a></td>
                            <td>$45</td>
                            <td class="hidden-480">3</td>
                            <td>GM100320</td>
                            <td>896777676</td>
                            <td class="hidden-phone">Feb 12</td>
                            <td class="hidden-480"><a href="brand-ordertracking.html">46578990890</a></td>


                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">base.com</a></td>
                            <td>$35</td>
                            <td class="hidden-480">2</td>
                            <td>GM100320</td>
                            <td>86754444444</td>
                            <td class="hidden-phone">Feb 18</td>
                            <td class="hidden-480"><a href="brand-ordertracking.html">75684894</a></td>


                        </tr>

                        </tbody>
                    </table>
                    </p>
                </div>
                <div id="canceled" class="tab-pane">
                    <p>
                    <table id="table_bug_report5" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                            </th>
                            <th>商品标题</th>
                            <th>价格</th>
                            <th class="hidden-480">数量</th>
                            <th>sku</th>
                            <th>订单编号</th>
                            <th class="hidden-480">订单创建时间</th>

                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">base.com</a></td>
                            <td>$35</td>
                            <td class="hidden-480">2,595</td>
                            <td class="hidden-phone">Feb 18</td>
                            <td class="hidden-480">75684894</td>
                            <td>

                            </td>

                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">max.com</a></td>
                            <td>$60</td>
                            <td class="hidden-480">4,400</td>
                            <td class="hidden-phone">Mar 11</td>
                            <td class="hidden-480">75684894</td>
                            <td>

                            </td>

                        </tr>


                        <tr>
                            <td class="center">
                                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                            </td>
                            <td><a href="bvo-goodsdetail.html">best.com</a></td>
                            <td>$75</td>
                            <td class="hidden-480">6,500</td>
                            <td class="hidden-phone">Apr 03</td>
                            <td class="hidden-480">75684894</td>
                            <td>

                            </td>

                        </tr>




                        </tbody>
                    </table>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="js/bootstrap.min.js"></script>
<script src="js/bootbox.min.js"></script>

<script>

    $(function(){

        $('.ace-checkbox-2').each(function(){
            $('.ace-checkbox-2').change(function(){
                if($(this).prop('checked')){
                    $(this).parents('.tab-pane').find('.input').prop('checked',true);
                }else{
                    $(this).parents('.tab-pane').find('.input').prop('checked',false);
                }
            })

        })

        $('.send-out').click(function(){
            bootbox.alert("已发货!");
        })
        $('.cancel').click(function(){
            bootbox.alert("订单已取消!");
        })





    })



</script>
</body>
</html>

