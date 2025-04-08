<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <%@ include file="../../static/head.jsp" %>
    <link href="http://www.bootcss.com/p/bootstrap-datetimepicker/bootstrap-datetimepicker/css/datetimepicker.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath}/resources/ueditor/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<style>
    .error {
        color: red;
    }
</style>
<body>
<!-- Pre Loader -->
<div class="loading">
    <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
    </div>
</div>
<!--/Pre Loader -->
<div class="wrapper">
    <!-- Page Content -->
    <div id="content">
        <!-- Top Navigation -->
        <%@ include file="../../static/topNav.jsp" %>
        <!-- Menu -->
        <div class="container menu-nav">
            <nav class="navbar navbar-expand-lg lochana-bg text-white">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="ti-menu text-white"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul id="navUl" class="navbar-nav mr-auto">

                    </ul>
                </div>
            </nav>
        </div>
        <!-- /Menu -->
        <!-- Breadcrumb -->
        <!-- Page Title -->
        <div class="container mt-0">
            <div class="row breadcrumb-bar">
                <div class="col-md-6">
                    <h3 class="block-title">编辑稿件</h3>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                <span class="ti-home"></span>
                            </a>
                        </li>
                        <li class="breadcrumb-item">稿件管理</li>
                        <li class="breadcrumb-item active">编辑稿件</li>
                    </ol>
                </div>
            </div>
        </div>
        <!-- /Page Title -->

        <!-- /Breadcrumb -->
        <!-- Main Content -->
        <div class="container">

            <div class="row">
                <!-- Widget Item -->
                <div class="col-md-12">
                    <div class="widget-area-2 lochana-box-shadow">
                        <h3 class="widget-title">稿件信息</h3>
                        <form id="addOrUpdateForm">
                            <div class="form-row">
                            <!-- 级联表的字段 -->

                                    <div class="form-group col-md-6 aaaaaa zuozhe">
                                        <label>作者</label>
                                        <div>
                                            <select id="zuozheSelect" name="zuozheSelect"
                                                    class="selectpicker form-control"  data-live-search="true"
                                                    title="请选择" data-header="请选择" data-size="5" data-width="650px">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6 zuozhe">
                                        <label>作者姓名</label>
                                        <input id="zuozheName" name="zuozheName" class="form-control"
                                               placeholder="作者姓名" readonly>
                                    </div>
                                    <div class="form-group col-md-6 zuozhe">
                                        <label>作者身份证号</label>
                                        <input id="zuozheIdNumber" name="zuozheIdNumber" class="form-control"
                                               placeholder="作者身份证号" readonly>
                                    </div>
                                    <div class="form-group col-md-6 zuozhe">
                                        <label>现住地址</label>
                                        <input id="zuozheAddress" name="zuozheAddress" class="form-control"
                                               placeholder="现住地址" readonly>
                                    </div>
                                    <div class="form-group col-md-6 zuozhe">
                                        <label>出生年月</label>
                                        <input id="zuozheTime" name="zuozheTime" class="form-control"
                                               placeholder="出生年月" readonly>
                                    </div>
                                    <div class="form-group col-md-6 zuozhe">
                                        <label>作者照片</label>
                                        <img id="zuozhePhotoImg" src="" width="100" height="100">
                                    </div>
                            <!-- 当前表的字段 -->
                                    <input id="updateId" name="id" type="hidden">
                                <input id="zuozheId" name="zuozheId" type="hidden">
                                <input id="zhuanjiaId" name="zhuanjiaId" type="hidden">
                                    <div class="form-group col-md-6">
                                        <label>稿件名字</label>
                                        <input id="gaojianName" name="gaojianName" class="form-control"
                                               placeholder="稿件名字">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>稿件类型</label>
                                        <select id="gaojianTypesSelect" name="gaojianTypes" class="form-control">
                                        </select>
                                    </div>
                                    <div class="form-group  col-md-6">
                                        <label>稿件介绍</label>
                                        <input id="gaojianContentupload" name="file" type="file">
                                        <script id="gaojianContentEditor" type="text/plain"
                                                style="width:100%;height:230px;"></script>
                                        <script type = "text/javascript" >
                                        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                                        //相见文档配置属于你自己的编译器
                                        var gaojianContentUe = UE.getEditor('gaojianContentEditor', {
                                            toolbars: [
                                                [
                                                    'undo', //撤销
                                                    'bold', //加粗
                                                    'redo', //重做
                                                    'underline', //下划线
                                                    'horizontal', //分隔线
                                                    'inserttitle', //插入标题
                                                    'cleardoc', //清空文档
                                                    'fontfamily', //字体
                                                    'fontsize', //字号
                                                    'paragraph', //段落格式
                                                    'inserttable', //插入表格
                                                    'justifyleft', //居左对齐
                                                    'justifyright', //居右对齐
                                                    'justifycenter', //居中对
                                                    'justifyjustify', //两端对齐
                                                    'forecolor', //字体颜色
                                                    'fullscreen', //全屏
                                                    'edittip ', //编辑提示
                                                    'customstyle', //自定义标题
                                                ]
                                            ]
                                        });
                                        </script>
                                        <input type="hidden" name="gaojianContent" id="gaojianContent-input">
                                    </div>
                                    <div class="form-group col-md-6 cccccc">
                                        <label>稿件</label>
                                        <input name="file" type="file" class="form-control-file" id="gaojianFileupload">
                                        <label id="gaojianFileName"></label>
                                        <input name="gaojianFile" id="gaojianFile-input" type="hidden">
                                    </div>
                                <div class="form-group col-md-6 aaaaaa zhuanjia">
                                    <label>专家</label>
                                    <div>
                                        <select id="zhuanjiaSelect" name="zhuanjiaSelect"
                                                class="selectpicker form-control"  data-live-search="true"
                                                title="请选择" data-header="请选择" data-size="5" data-width="650px">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-md-6 zhuanjia">
                                    <label>专家姓名</label>
                                    <input id="zhuanjiaName" name="zhuanjiaName" class="form-control"
                                           placeholder="专家姓名" readonly>
                                </div>
                                <div class="form-group col-md-6 zhuanjia">
                                    <label>专家身份证号</label>
                                    <input id="zhuanjiaIdNumber" name="zhuanjiaIdNumber" class="form-control"
                                           placeholder="专家身份证号" readonly>
                                </div>
                                <div class="form-group col-md-6 zhuanjia">
                                    <label>专家照片</label>
                                    <img id="zhuanjiaPhotoImg" src="" width="100" height="100">
                                </div>
                                    <div class="form-group col-md-6 dddddd">
                                        <label>审稿结果</label>
                                        <select id="gaojianYesnoTypesSelect" name="gaojianYesnoTypes" class="form-control">
                                        </select>
                                    </div>
                                    <div class="form-group  col-md-6 dddddd">
                                        <label>审核回复</label>
                                        <input id="gaojianShenheContentupload" name="file" type="file">
                                        <script id="gaojianShenheContentEditor" type="text/plain"
                                                style="width:100%;height:230px;"></script>
                                        <script type = "text/javascript" >
                                        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                                        //相见文档配置属于你自己的编译器
                                        var gaojianShenheContentUe = UE.getEditor('gaojianShenheContentEditor', {
                                            toolbars: [
                                                [
                                                    'undo', //撤销
                                                    'bold', //加粗
                                                    'redo', //重做
                                                    'underline', //下划线
                                                    'horizontal', //分隔线
                                                    'inserttitle', //插入标题
                                                    'cleardoc', //清空文档
                                                    'fontfamily', //字体
                                                    'fontsize', //字号
                                                    'paragraph', //段落格式
                                                    'inserttable', //插入表格
                                                    'justifyleft', //居左对齐
                                                    'justifyright', //居右对齐
                                                    'justifycenter', //居中对
                                                    'justifyjustify', //两端对齐
                                                    'forecolor', //字体颜色
                                                    'fullscreen', //全屏
                                                    'edittip ', //编辑提示
                                                    'customstyle', //自定义标题
                                                ]
                                            ]
                                        });
                                        </script>
                                        <input type="hidden" name="gaojianShenheContent" id="gaojianShenheContent-input">
                                    </div>
                                    <div class="form-group col-md-12 mb-3">
                                        <button id="submitBtn" type="button" class="btn btn-primary btn-lg">提交</button>
                                        <button id="exitBtn" type="button" class="btn btn-primary btn-lg">返回</button>
                                    </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /Widget Item -->
            </div>
        </div>
        <!-- /Main Content -->
    </div>
    <!-- /Page Content -->
</div>
<!-- Back to Top -->
<a id="back-to-top" href="#" class="back-to-top">
    <span class="ti-angle-up"></span>
</a>
<!-- /Back to Top -->
<%@ include file="../../static/foot.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.fileupload.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/validate/messages_zh.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/validate/card.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/resources/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" charset="utf-8"
                 src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/laydate.js"></script>
<script>
    <%@ include file="../../utils/menu.jsp"%>
    <%@ include file="../../static/setMenu.js"%>
    <%@ include file="../../utils/baseUrl.jsp"%>

    var tableName = "gaojian";
    var pageType = "add-or-update";
    var updateId = "";
    var crossTableId = -1;
    var crossTableName = '';
    var ruleForm = {};
    var crossRuleForm = {};


    // 下拉框数组
        <!-- 当前表的下拉框数组 -->
    var gaojianTypesOptions = [];
    var gaojianYesnoTypesOptions = [];
        <!-- 级联表的下拉框数组 -->
    var zhuanjiaOptions = [];
    var zuozheOptions = [];

    var ruleForm = {};


    // 文件上传
    function upload() {

        <!-- 当前表的文件上传 -->

        $('#gaojianContentupload').fileupload({
            url: baseUrl + 'file/upload',
            headers: {token: window.sessionStorage.getItem("token")},
            dataType: 'json',
            done: function (e, data) {
                UE.getEditor('gaojianContentEditor').execCommand('insertHtml', '<img src=\"' + baseUrl + 'upload/' + data.result.file + '\" width=900 height=560>');
            }
        });


        $('#gaojianFileupload').fileupload({
            url: baseUrl + 'file/upload',
            headers: {token: window.sessionStorage.getItem("token")},
            dataType: 'json',
            done: function (e, data) {
                document.getElementById('gaojianFile-input').setAttribute('value', baseUrl + 'file/download?fileName=' + data.result.file);
                document.getElementById('gaojianFileName').innerHTML = "上传成功!";
            }
        });


        $('#gaojianShenheContentupload').fileupload({
            url: baseUrl + 'file/upload',
            headers: {token: window.sessionStorage.getItem("token")},
            dataType: 'json',
            done: function (e, data) {
                UE.getEditor('gaojianShenheContentEditor').execCommand('insertHtml', '<img src=\"' + baseUrl + 'upload/' + data.result.file + '\" width=900 height=560>');
            }
        });


    }

    // 表单提交
    function submit() {
        if (validform() == true && compare() == true) {
            let data = {};
            getContent();
            if(window.sessionStorage.getItem('role') != '作者'){//当前登录用户不为这个
                if($("#zuozheId") !=null){
                    var zuozheId = $("#zuozheId").val();
                    if(zuozheId == null || zuozheId =='' || zuozheId == 'null'){
                        alert("作者不能为空");
                        return;
                    }
                }
            }
            if(!(window.sessionStorage.getItem('role') == '专家' || window.sessionStorage.getItem('role') == '作者')){//当前登录用户不为这个
                if($("#zhuanjiaId") !=null){
                    var zhuanjiaId = $("#zhuanjiaId").val();
                    if(zhuanjiaId == null || zhuanjiaId =='' || zhuanjiaId == 'null'){
                        alert("专家不能为空");
                        return;
                    }
                }
            }
            let value = $('#addOrUpdateForm').serializeArray();
            $.each(value, function (index, item) {
                data[item.name] = item.value;
            });
            let json = JSON.stringify(data);
            var urlParam;
            var successMes = '';
            if (updateId != null && updateId != "null" && updateId != '') {
                urlParam = 'update';
                successMes = '修改成功';
            } else {
                urlParam = 'save';
                    successMes = '添加成功';

            }
            httpJson("gaojian/" + urlParam, "POST", data, (res) => {
                if(res.code == 0){
                    window.sessionStorage.removeItem('addgaojian');
                    window.sessionStorage.removeItem('updateId');
                    let flag = true;
                    if (flag) {
                        alert(successMes);
                    }
                    if (window.sessionStorage.getItem('onlyme') != null && window.sessionStorage.getItem('onlyme') == "true") {
                        window.sessionStorage.removeItem('onlyme');
                        window.sessionStorage.setItem("reload","reload");
                        window.parent.location.href = "${pageContext.request.contextPath}/index.jsp";
                    } else {
                        window.location.href = "list.jsp";
                    }
                }
            });
        } else {
            alert("表单未填完整或有错误");
        }
    }

    // 查询列表
        <!-- 查询当前表的所有列表 -->
        function gaojianTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=gaojian_types", "GET", {}, (res) => {
                if(res.code == 0){
                    gaojianTypesOptions = res.data.list;
                }
            });
        }
        function gaojianYesnoTypesSelect() {
            //填充下拉框选项
            http("dictionary/page?page=1&limit=100&sort=&order=&dicCode=gaojian_yesno_types", "GET", {}, (res) => {
                if(res.code == 0){
                    gaojianYesnoTypesOptions = res.data.list;
                }
            });
        }
        <!-- 查询级联表的所有列表 -->
        function zhuanjiaSelect() {
            //填充下拉框选项
            http("zhuanjia/page?page=1&limit=100&sort=&order=", "GET", {}, (res) => {
                if(res.code == 0){
                    zhuanjiaOptions = res.data.list;
                }
            });
        }

        function zhuanjiaSelectOne(id) {
            http("zhuanjia/info/"+id, "GET", {}, (res) => {
                if(res.code == 0){
                ruleForm = res.data;
                zhuanjiaShowImg();
                zhuanjiaShowVideo();
                zhuanjiaDataBind();
            }
        });
        }
        function zuozheSelect() {
            //填充下拉框选项
            http("zuozhe/page?page=1&limit=100&sort=&order=", "GET", {}, (res) => {
                if(res.code == 0){
                    zuozheOptions = res.data.list;
                }
            });
        }

        function zuozheSelectOne(id) {
            http("zuozhe/info/"+id, "GET", {}, (res) => {
                if(res.code == 0){
                ruleForm = res.data;
                zuozheShowImg();
                zuozheShowVideo();
                zuozheDataBind();
            }
        });
        }



    // 初始化下拉框
    <!-- 初始化当前表的下拉框 -->
        function initializationGaojiantypesSelect(){
            var gaojianTypesSelect = document.getElementById('gaojianTypesSelect');
            if(gaojianTypesSelect != null && gaojianTypesOptions != null  && gaojianTypesOptions.length > 0 ){
                for (var i = 0; i < gaojianTypesOptions.length; i++) {
                    gaojianTypesSelect.add(new Option(gaojianTypesOptions[i].indexName,gaojianTypesOptions[i].codeIndex));
                }
            }
        }
        function initializationGaojianyesnotypesSelect(){
            var gaojianYesnoTypesSelect = document.getElementById('gaojianYesnoTypesSelect');
            if(gaojianYesnoTypesSelect != null && gaojianYesnoTypesOptions != null  && gaojianYesnoTypesOptions.length > 0 ){
                for (var i = 0; i < gaojianYesnoTypesOptions.length; i++) {
                    gaojianYesnoTypesSelect.add(new Option(gaojianYesnoTypesOptions[i].indexName,gaojianYesnoTypesOptions[i].codeIndex));
                }
            }
        }

        function initializationzhuanjiaSelect() {
            var zhuanjiaSelect = document.getElementById('zhuanjiaSelect');
            if(zhuanjiaSelect != null && zhuanjiaOptions != null  && zhuanjiaOptions.length > 0 ) {
                for (var i = 0; i < zhuanjiaOptions.length; i++) {
                        zhuanjiaSelect.add(new Option(zhuanjiaOptions[i].zhuanjiaName, zhuanjiaOptions[i].id));
                }

                $("#zhuanjiaSelect").change(function(e) {
                        zhuanjiaSelectOne(e.target.value);
                });
            }

        }

        function initializationzuozheSelect() {
            var zuozheSelect = document.getElementById('zuozheSelect');
            if(zuozheSelect != null && zuozheOptions != null  && zuozheOptions.length > 0 ) {
                for (var i = 0; i < zuozheOptions.length; i++) {
                        zuozheSelect.add(new Option(zuozheOptions[i].zuozheName, zuozheOptions[i].id));
                }

                $("#zuozheSelect").change(function(e) {
                        zuozheSelectOne(e.target.value);
                });
            }

        }



    // 下拉框选项回显
    function setSelectOption() {

        <!-- 当前表的下拉框回显 -->

        var gaojianTypesSelect = document.getElementById("gaojianTypesSelect");
        if(gaojianTypesSelect != null && gaojianTypesOptions != null  && gaojianTypesOptions.length > 0 ) {
            for (var i = 0; i < gaojianTypesOptions.length; i++) {
                if (gaojianTypesOptions[i].codeIndex == ruleForm.gaojianTypes) {//下拉框value对比,如果一致就赋值汉字
                        gaojianTypesSelect.options[i].selected = true;
                }
            }
        }

        var gaojianYesnoTypesSelect = document.getElementById("gaojianYesnoTypesSelect");
        if(gaojianYesnoTypesSelect != null && gaojianYesnoTypesOptions != null  && gaojianYesnoTypesOptions.length > 0 ) {
            for (var i = 0; i < gaojianYesnoTypesOptions.length; i++) {
                if (gaojianYesnoTypesOptions[i].codeIndex == ruleForm.gaojianYesnoTypes) {//下拉框value对比,如果一致就赋值汉字
                        gaojianYesnoTypesSelect.options[i].selected = true;
                }
            }
        }
        <!--  级联表的下拉框回显  -->
            var zhuanjiaSelect = document.getElementById("zhuanjiaSelect");
            if(zhuanjiaSelect != null && zhuanjiaOptions != null  && zhuanjiaOptions.length > 0 ) {
                for (var i = 0; i < zhuanjiaOptions.length; i++) {
                    if (zhuanjiaOptions[i].id == ruleForm.zhuanjiaId) {//下拉框value对比,如果一致就赋值汉字
                        zhuanjiaSelect.options[i+1].selected = true;
                        $("#zhuanjiaSelect" ).selectpicker('refresh');
                    }
                }
            }
            var zuozheSelect = document.getElementById("zuozheSelect");
            if(zuozheSelect != null && zuozheOptions != null  && zuozheOptions.length > 0 ) {
                for (var i = 0; i < zuozheOptions.length; i++) {
                    if (zuozheOptions[i].id == ruleForm.zuozheId) {//下拉框value对比,如果一致就赋值汉字
                        zuozheSelect.options[i+1].selected = true;
                        $("#zuozheSelect" ).selectpicker('refresh');
                    }
                }
            }
    }


    // 填充富文本框
    function setContent() {

        <!-- 当前表的填充富文本框 -->
        if (ruleForm.gaojianContent != null && ruleForm.gaojianContent != 'null' && ruleForm.gaojianContent != '' && $("#gaojianContentupload").length>0) {

            var gaojianContentUeditor = UE.getEditor('gaojianContentEditor');
            gaojianContentUeditor.ready(function () {
                var mes = '';
                if(ruleForm.gaojianContent != null){
                    mes = ''+ ruleForm.gaojianContent;
                    mes = mes.replace(/\n/g, "<br>");
                }
                gaojianContentUeditor.setContent(mes);
            });
        }
        if (ruleForm.gaojianShenheContent != null && ruleForm.gaojianShenheContent != 'null' && ruleForm.gaojianShenheContent != '' && $("#gaojianShenheContentupload").length>0) {

            var gaojianShenheContentUeditor = UE.getEditor('gaojianShenheContentEditor');
            gaojianShenheContentUeditor.ready(function () {
                var mes = '';
                if(ruleForm.gaojianShenheContent != null){
                    mes = ''+ ruleForm.gaojianShenheContent;
                    mes = mes.replace(/\n/g, "<br>");
                }
                gaojianShenheContentUeditor.setContent(mes);
            });
        }
    }
    // 获取富文本框内容
    function getContent() {

        <!-- 获取当前表的富文本框内容 -->
        if($("#gaojianContentupload").length>0) {
            var gaojianContentEditor = UE.getEditor('gaojianContentEditor');
            if (gaojianContentEditor.hasContents()) {
                $('#gaojianContent-input').attr('value', gaojianContentEditor.getPlainTxt());
            }
        }
        if($("#gaojianShenheContentupload").length>0) {
            var gaojianShenheContentEditor = UE.getEditor('gaojianShenheContentEditor');
            if (gaojianShenheContentEditor.hasContents()) {
                $('#gaojianShenheContent-input').attr('value', gaojianShenheContentEditor.getPlainTxt());
            }
        }
    }
    //数字检查
        <!-- 当前表的数字检查 -->

    function exit() {
        window.sessionStorage.removeItem("updateId");
        window.sessionStorage.removeItem('addgaojian');
        window.location.href = "list.jsp";
    }
    // 表单校验
    function validform() {
        return $("#addOrUpdateForm").validate({
            rules: {
                zuozheId: "required",
                zhuanjiaId: "required",
                gaojianName: "required",
                gaojianTypes: "required",
                gaojianContent: "required",
                gaojianFile: "required",
                insertTime: "required",
                gaojianYesnoTypes: "required",
                gaojianShenheContent: "required",
            },
            messages: {
                zuozheId: "作者不能为空",
                zhuanjiaId: "专家不能为空",
                gaojianName: "稿件名字不能为空",
                gaojianTypes: "稿件类型不能为空",
                gaojianContent: "稿件介绍不能为空",
                gaojianFile: "稿件不能为空",
                insertTime: "交稿时间不能为空",
                gaojianYesnoTypes: "审稿结果不能为空",
                gaojianShenheContent: "审核回复不能为空",
            }
        }).form();
    }

    // 获取当前详情
    function getDetails() {
        var addgaojian = window.sessionStorage.getItem("addgaojian");
        if (addgaojian != null && addgaojian != "" && addgaojian != "null") {
            //注册表单验证
            $(validform());
            $('#submitBtn').text('新增');

        } else {
            $('#submitBtn').text('修改');
            var userId = window.sessionStorage.getItem('userId');
            updateId = userId;//先赋值登录用户id
            var uId  = window.sessionStorage.getItem('updateId');//获取修改传过来的id
            if (uId != null && uId != "" && uId != "null") {
                //如果修改id不为空就赋值修改id
                updateId = uId;
            }
            window.sessionStorage.removeItem('updateId');
            http("gaojian/info/" + updateId, "GET", {}, (res) => {
                if(res.code == 0)
                {
                    ruleForm = res.data
                    // 是/否下拉框回显
                    setSelectOption();
                    // 设置图片src
                    showImg();
                    // 设置视频src
                    showVideo();
                    // 数据填充
                    dataBind();
                    // 富文本框回显
                    setContent();
                    //注册表单验证
                    $(validform());
                }

            });
        }
    }

    // 清除可能会重复渲染的selection
    function clear(className) {
        var elements = document.getElementsByClassName(className);
        for (var i = elements.length - 1; i >= 0; i--) {
            elements[i].parentNode.removeChild(elements[i]);
        }
    }

    function dateTimePick() {
            laydate.render({
                elem: '#insertTime-input'
                ,type: 'datetime'
            });
    }


    function dataBind() {


    <!--  级联表的数据回显  -->
        zhuanjiaDataBind();
        zuozheDataBind();


    <!--  当前表的数据回显  -->
        $("#updateId").val(ruleForm.id);
        $("#zuozheId").val(ruleForm.zuozheId);
        $("#zhuanjiaId").val(ruleForm.zhuanjiaId);
        $("#gaojianName").val(ruleForm.gaojianName);
        $("#gaojianContent").val(ruleForm.gaojianContent);
        $("#insertTime-input").val(ruleForm.insertTime);
        $("#gaojianShenheContent").val(ruleForm.gaojianShenheContent);

    }
    <!--  级联表的数据回显  -->
    function zhuanjiaDataBind(){

                    <!-- 把id赋值给当前表的id-->
        $("#zhuanjiaId").val(ruleForm.id);

        $("#zhuanjiaName").val(ruleForm.zhuanjiaName);
        $("#zhuanjiaIdNumber").val(ruleForm.zhuanjiaIdNumber);
    }

    function zuozheDataBind(){

                    <!-- 把id赋值给当前表的id-->
        $("#zuozheId").val(ruleForm.id);

        $("#zuozheName").val(ruleForm.zuozheName);
        $("#zuozheIdNumber").val(ruleForm.zuozheIdNumber);
        $("#zuozheAddress").val(ruleForm.zuozheAddress);
        $("#zuozheTime").val(ruleForm.zuozheTime);
    }


    //图片显示
    function showImg() {
        <!--  当前表的图片  -->

        <!--  级联表的图片  -->
        zhuanjiaShowImg();
        zuozheShowImg();
    }


    <!--  级联表的图片  -->

    function zhuanjiaShowImg() {
        $("#zhuanjiaPhotoImg").attr("src",ruleForm.zhuanjiaPhoto);
    }


    function zuozheShowImg() {
        $("#zuozhePhotoImg").attr("src",ruleForm.zuozhePhoto);
    }



    //视频回显
    function showVideo() {
    <!--  当前表的视频  -->

    <!--  级联表的视频  -->
        zhuanjiaShowVideo();
        zuozheShowVideo();
    }


    <!--  级联表的视频  -->

    function zhuanjiaShowVideo() {
        $("#zhuanjiaPhotoV").attr("src",ruleForm.zhuanjiaPhoto);
    }

    function zuozheShowVideo() {
        $("#zuozhePhotoV").attr("src",ruleForm.zuozhePhoto);
    }



    $(document).ready(function () {
        //设置右上角用户名
        $('.dropdown-menu h5').html(window.sessionStorage.getItem('username'))
        //设置项目名
        $('.sidebar-header h3 a').html(projectName)
        //设置导航栏菜单
        setMenu();
        $('#exitBtn').on('click', function (e) {
            e.preventDefault();
            exit();
        });
        //初始化时间插件
        dateTimePick();
        //查询所有下拉框
            <!--  当前表的下拉框  -->
            gaojianTypesSelect();
            gaojianYesnoTypesSelect();
            <!-- 查询级联表的下拉框(用id做option,用名字及其他参数做名字级联修改) -->
            zhuanjiaSelect();
            zuozheSelect();



        // 初始化下拉框
            <!--  初始化当前表的下拉框  -->
            initializationGaojiantypesSelect();
            initializationGaojianyesnotypesSelect();
            <!--  初始化级联表的下拉框  -->
            initializationzhuanjiaSelect();
            initializationzuozheSelect();

        $(".selectpicker" ).selectpicker('refresh');
        getDetails();
        //初始化上传按钮
        upload();
    <%@ include file="../../static/myInfo.js"%>
                $('#submitBtn').on('click', function (e) {
                    e.preventDefault();
                    //console.log("点击了...提交按钮");
                    submit();
                });
        readonly();
        window.sessionStorage.removeItem('addgaojian');
    });

    function readonly() {
        if (window.sessionStorage.getItem('role') == '管理员') {
            //$('#gaojianName').attr('readonly', 'readonly');
            //$('#role').attr('style', 'pointer-events: none;');
        }
		else if (window.sessionStorage.getItem('role') == '作者') {
            // $(".aaaaaa").remove();
            $(".zuozhe").remove();//删除当前用户的信息
            $(".zhuanjia").remove();//删除专家信息
            $(".dddddd").remove();//删除审核信息
        }
		else if (window.sessionStorage.getItem('role') == '专家') {
            $(".aaaaaa").remove();
            $(".zhuanjia").remove();//删除当前用户的信息

            $('#gaojianName').attr('readonly', 'readonly');
            $('#gaojianTypesSelect').attr('readonly', 'readonly');
            $('#gaojianTypesSelect').attr('style', 'pointer-events: none;');
            var gaojianContentEditor = UE.getEditor('gaojianContentEditor');
            gaojianContentEditor.ready(function () {
                gaojianContentEditor.setDisabled('fullscreen');
            });

            $(".cccccc").remove();//删除稿件上传信息
        }
        else{
            // alert("未知情况.......");
            // var replyContentUeditor = UE.getEditor('replyContentEditor');
            // replyContentUeditor.ready(function () {
            //     replyContentUeditor.setDisabled('fullscreen');
            // });
        }
    }

    //比较大小
    function compare() {
        var largerVal = null;
        var smallerVal = null;
        if (largerVal != null && smallerVal != null) {
            if (largerVal <= smallerVal) {
                alert(smallerName + '不能大于等于' + largerName);
                return false;
            }
        }
        return true;
    }


    // 用户登出
    <%@ include file="../../static/logout.jsp"%>
</script>
</body>

</html>