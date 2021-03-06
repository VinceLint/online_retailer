<%--
  Created by IntelliJ IDEA.
  User: janzes
  Date: 2019/7/26
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>借卖方账户余额</title>


    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

</head>
<style>
    .btn-3{
        margin-top:10px;
        padding-top:20px;
        position:absolute;
    }
</style>
<body>
<div class="page-header position-relative">
    <h1 style="color: #2679b5;">借卖方 <small><i class="el-icon-d-arrow-right"></i> 账户余额</small></h1>
    <hr><br>

</div>

<!-- import CSS -->
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>

<div id="app">


    <div id="table3">
        <el-table
                :data="tableData3"
                border
                style="width: 100%"
                stripe="true">
            <el-table-column
                    label="账户"
                    prop="id">
            </el-table-column>
            <el-table-column
                    label="金额"
                    prop="bal">
            </el-table-column>
            <el-table-column

                    align="right">
                <template slot="header" slot-scope="scope">
                    <p>操作</p>
                </template>
                <template slot-scope="scope">

                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row),visible = true">充值</el-button>


                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleWithDraw(scope.$index, scope.row), visible1 = true">提现</el-button>

                </template>
            </el-table-column>
        </el-table>

        </template>
<%--//充值弹出框--%>
        <el-dialog title="充值" :visible.sync="visible" >

            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="金额" prop="money">
                    <el-input v-model.number="ruleForm.money"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>

            </el-form>
        </el-dialog>

        <%--//提现弹出框--%>
        <el-dialog :visible.sync="visible1" title="提现">

            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="金额" prop="money">
                    <el-input v-model.number="ruleForm.money"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>

            </el-form>
        </el-dialog>
        <br>


        <el-button type="primary" class='btn-3' @click="handlejump()" >交易流水</el-button>
    </div>
</div>

    <script>



        var vm1=new Vue({
            el: '#table3',
            data: function () {
                // 弹窗内容

                var checkMoney = (rule, value, callback) => {
                    if (!value) {
                        return callback(new Error('金额不能为空'));
                    }
                    setTimeout(() => {
                        if (!Number.isInteger(value)) {
                            callback(new Error('请输入数字值'));
                        } else {
                            if (value < 0) {
                                callback(new Error('金额必须大于零'));
                            } else {
                                callback();
                            }
                        }
                    }, 1000);
                };
                var validatePass = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请输入密码'));
                    } else {
                        if (this.ruleForm.checkPass !== '') {
                            this.$refs.ruleForm.validateField('checkPass');
                        }
                        callback();
                    }
                };
                var validatePass2 = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请再次输入密码'));
                    } else if (value !== this.ruleForm.pass) {
                        callback(new Error('两次输入密码不一致!'));
                    } else {
                        this.isEdit=true;
                        callback();
                    }
                };

                return {
                    visible: false,
                    visible1: false,
                    isEdit:false,
                    chooseId:'',
                    row:'',
                    form:{
                    id:'',
                    bal:'',
                        sign:1
                    },
                    dateForUpdate:[],
                    search: '',
                    walletForm:{
                      id:'',
                      bal:'',
                      pass:'',
                    },
                    dateNotUpdate:{
                        id:'',
                        bal:'',
                        pass:'',
                    },
                    ruleForm: {
                        pass: '',
                        checkPass: '',
                        money: ''
                    },
                    // 弹窗内容
                    rules: {
                        pass: [
                            { validator: validatePass, trigger: 'blur' }
                        ],
                        checkPass: [
                            { validator: validatePass2, trigger: 'blur' }
                        ],
                        money: [
                            { validator: checkMoney, trigger: 'blur' }
                        ]
                    }
                }
            },
            methods: {
                handleEdit(index, row) {
                    console.log(index, row);
                    console.log(row.id);
                    // 放入旧的钱包信息
                    this.dateForUpdate=[];
                    this.form.id=row.id;
                    this.form.bal=row.bal;
                    this.row=index;
                    this.form.sign=1;

                    // this.isEdit=true;
                    this.dateForUpdate.push(this.form);

                    console.log(this.form,this.dateForUpdate);

                    console.log(row.bal);

                },
                handleWithDraw(index, row) {
                    console.log(index, row);
                    this.form.id=row.id;
                    this.form.bal=row.bal;
                    this.form.sign=-1;
                    this.row=index;
                    this.dateForUpdate=[];
                    this.dateForUpdate.push(this.form);
                    console.log(this.form,this.dateForUpdate);

                },
                // 弹窗内容
                submitForm(formName) {

                    var did=this.form.id;
                    var dbal=this.form.bal;
                    var updateDate={
                        id:did,
                        bal:dbal
                    }

                    if(this.isEdit){
                        this.dateForUpdate.push(this.ruleForm);
                        console.log(updateDate,this.row)

                        // var id=this.form.id;
                        // var bal=this.form.bal;
                        // var pass=this.form.pass;
                        updateDate.bal+=this.ruleForm.money*this.form.sign;
                        $.ajax({
                            type:"POST",
                            url:"http://127.0.0.1:8080/online_retailer_war_exploded/EditWal",
                            contentType:"application/json; charset:utf-8",
                            data:JSON.stringify(this.dateForUpdate),
                            dataType: "json",
                            success:function(date){
                                console.log(date.result)
                                if(date.result=="success"){

                                    //手动刷新
                                    // window.location.reload();
                                    this.$set(this.tableData3,this.row,updateDate);
                                    this.visible = false;
                                    this.visible1=false;
                                    this.isEdit=false;
                                    if(this.form.sign==1){
                                        this.$alert('<strong>充值成功,请等待管理员审核</strong>', '感谢', {
                                            dangerouslyUseHTMLString: true
                                        });
                                    }else{
                                        this.$alert('<strong>提现成功，请等待管理员审核</strong>', '感谢', {
                                            dangerouslyUseHTMLString: true
                                        });
                                    }



                                }else if(date.result=="passError"){
                                    this.$alert('<strong>密码错误</strong>', '好像出了点问题', {
                                        dangerouslyUseHTMLString: true
                                    });
                                }else if(date.result=="recordError") {
                                    this.$alert('<strong>记录失败</strong>', '好像出了点问题', {
                                        dangerouslyUseHTMLString: true
                                    });
                                }else if(date.result=="unenough") {
                                    this.$alert('<strong>余额不足</strong>', '好像出了点问题', {
                                        dangerouslyUseHTMLString: true
                                    });
                                }else
                                {
                                    this.$alert('<strong>网络繁忙</strong>', '好像出了点问题', {
                                        dangerouslyUseHTMLString: true
                                    });
                                }
                            }.bind(this),
                            error:function (message) {
                                console.log(message)
                                console.log("error")
                            }

                        })


                    }else{
                        this.$alert('<strong>两次密码要相同哦</strong>', '提示', {
                            dangerouslyUseHTMLString: true
                        });
                    }
                    this.$refs[formName].validate((valid) => {
                        if (!valid) {
                            console.log('error submit!!');
                            return false;
                        }
                    });

                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                handlejump() {
                    //跳转页面
                    window.location="http://127.0.0.1:8080/online_retailer_war_exploded/html/bvo-transation.html";
                },
                init() {
                    var that=this;
                    this.tableData3=[]

                    $.ajax({
                        type: "post",
                        url: "http://127.0.0.1:8080/online_retailer_war_exploded/toWalList",
                        dataType: "json",
                        success: function (r) {
                            $.each(r,function(i,obj){
                                this.tableData3.push({
                                    id:obj.walId,
                                    bal:obj.walBalance
                                })
                            }.bind(this))
                            console.log(r);
                            // for (var i = 0; i < r.length; i++) {//循环显示标题
                            //
                            //     $("#news-list2 ul").append("<li><a index='" + i + "'>" + r[i].walId + "</a></li>");
                            // }
                            // $("#news-list2 ul li a").click(function () {
                            //     // 单击左边的新闻标题显示内容
                            //     var index = $(this).attr("index");
                            //     $("#news h1").html(r[index].walId);
                            //     $("#news .content").html(r[index].walEmail);
                            // });
                        }.bind(this)
                    });
                }
            },


            mounted:function(){
                this.init();
            }


        })


    </script>


</body>
</html>
