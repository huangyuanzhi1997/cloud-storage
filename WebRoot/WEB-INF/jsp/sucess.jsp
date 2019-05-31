<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 7]><html class="ie7 lte9 lte8 lte7" lang="zh-cn"><![endif]-->
<!--[if IE 8]><html class="ie8 lte9 lte8" lang="zh-cn"><![endif]-->
<!--[if IE 9]><html class="ie9 lte9" lang="zh-cn"><![endif]-->
<!--[if gt IE 9]><!-->
<html lang="zh-cn">
    <!--<![endif]-->
    <head>
        <title>登录成功</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" href="css/fineuploader.css">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="FlatUI/css/flat-ui.css" rel="stylesheet">
        <link href="FlatUI/css/docs.css" rel="stylesheet">
        <link href="FlatUI/css/demo.css" rel="stylesheet">
        <link href="FlatUI/bootstrap/css/prettify.css" rel="stylesheet">
        <style type="text/css">
            body {
                font-family: '微软雅黑';
                font-size: 11pt
            }
        </style>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation"
             style="background-color:#34495E">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" style="font-family:宋体"> <img
                            style="height:50px;margin-top:-12px;margin-left:-10px">
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown" id="accountmenu"><a class="dropdown-toggle" data-toggle="dropdown"
                                                                 href="javascript:void(0);"><span class="glyphicon glyphicon-user"></span></a>
                            <ul class="dropdown-menu">
                                <li><a>个人信息</a></li>

                                <li class="divider"></li>
                                <li><a>注销</a></li>
                            </ul></li>
                    </ul>
                    <div class="navbar-form navbar-right" role="search" style="broder-color:#08a889">
                        <div class="form-group">
                            <div class="input-group">
                                <input class="form-control" id="navbarInput-01" type="search" placeholder="搜索我的文件..."
                                       style="background-color:#ffffff"> <span class="input-group-btn"> <a
                                        href="javascript:void(0);" type="submit" class="btn" style="background-color:#ffffff"
                                        id="searchfiles"><span class="fui-search"></span></a>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar" id="2" style="left:0px">
                    <ul class="nav nav-sidebar" id="toggle" style="margin-bottom:0px">
                        <li class="active" id="r_active"><a><span class="menu-icons fa fa-home"
                                                                  style="margin-left:37px"></span>&nbsp;&nbsp;&nbsp;全部文件</a></li>
                        <li class="category"><a><span class="glyphicon glyphicon-cog" style="margin-left:37px"></span>&nbsp;&nbsp;&nbsp;设置</a></li>

                        <li
                            style="width: 327px;height: 0px;border-top: #dce1e6 1px solid;border-bottom: #f7f9fb 1px solid;"></li>
                    </ul>
                    <div style="margin-top:10px">
                        <div style="margin-left: 21px;font-size: 5px;color:#95A5A6;display:inline">我的容量:</div>
                        <div style="float:right;font-size:5px;margin-top: 2px;">
                            <a class="capacity">扩容</a>
                        </div>
                        <div class="progress" style="margin-top: 18px;margin-left: 18px ">
                            <div class="progress-bar" style="width: 0%;"></div>
                        </div>
                    </div>
                </div>
                <div class="1 main col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
                    <div class="col-md-12">
                        <a href="javascript:void(0);" class="toggle-nav" style="margin-left: -13px;"><i
                                class="fa fa-bars"></i></a>
                        <h7>
                            <b>全部文件</b></h7>
                        <div style="border: 1px solid #D7D9ED;" class="row">
                            <div style="margin-top:5px ;padding-left:1px;padding-right:1px;margin-bottom:5px"
                                 class="col-lg-12">

                                <div class=" fileupload-buttonbar col-lg-10 col-md-10 col-sm-10"
                                     style="padding-left:1px;padding-right:1px;float:left">
                                    <div id="result-uploader" class="right" style="display:inline;float:left;margin-top:8px"></div>
                                    <button style="height: 40px;float:left;margin-top:2px;margin-left:2px" class='btn btn-primary'
                                            id="delete">删除文件</button>
                                    <button style="height: 40px;float:left;margin-top:2px;margin-left:2px" class='btn btn-primary'
                                            id="download">下载文件</button> <a href="javascript:void(0);"
                                       class='btn btn-primary'
                                       style="float:left;width:102px;height:40px;margin-top:2px;margin-left:2px" id="copy"
                                       class="copy">复制</a> <a href="javascript:void(0);" class='btn btn-primary move'
                                       style="float:left;width:102px;height:40px;margin-top:2px;margin-left:2px" id="move">移动</a>
                                    <a href="javascript:void(0);" class='btn btn-primary'
                                       style="float:left;width:102px;height:40px;margin-top:2px;margin-left:2px" id="rename">重命名</a>
                                </div>
                                <div class="btn-toolbar  " style="float:right; position:relative;margin-top: 2px;">
                                    <div class="btn-group" id="searchnone" style="display:block">
                                        <a class="btn btn-primary active" id="btn"><span class="glyphicon glyphicon-th-list"></span></a>
                                        <a class="btn btn-primary " id="btn1"><span class="glyphicon glyphicon-th-large"></span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive row"
                             style="border: 1px solid #D7D9ED;border-top: 0px solid #D7D9ED;">
                            <div style="height:27px;inline-height:20px;border-bottom: 1px solid #D7D9ED;">
                                <a style="float:left">&laquo;全部文件 | </a>
                            </div>
                            <table class="table table-striped" style="margin-bottom: 0px;">
                                <thead>
                                    <tr>
                                        <th style="width:1%"> <input name="chkAll" type="checkbox" id="operAll" value="checkbox"
                                                                     style="filter:alpha(opacity=0);  width: 23px;height: 20px;display:block"></label></th>
                                        <th style="width:29%">文件名</th>
                                        <th style="width:5%">大小</th>
                                        <th style="width:35%">修改日期</th>
                                    </tr>
                                </thead>
                                <tbody id="tab">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--   javascript -->
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/fineuploader.js"></script>
        <script src="js/checkbox.js"></script>
        <script>
            var container_path = "${path}"
            //上传文件
            $(function() {
                createUploader();
                //读取列表
                loadallfile()
                //全选
                var operAll = $('#operAll')
                operAll.click(function() {
                    $("[name$='ck']").each(function() {
                        this.checked = operAll.is(':checked')
                    })
                });
                //删除
                $('#delete').click(function() {
                    var s = $.makeArray($("[name$='ck']:checked").map(function(a) {
                        return $(this).attr('filename')
                    })).join(',')
                    if (s) {
                        $.post('delete.action',{path:container_path,name:s},function(s){
                            alert('删除成功');
                            location.reload()
                        })
                    }else{
                        alert('请至少选择一个文件')
                    }
                })
                //下载
                $('#download').click(function() {
                    $("[name$='ck']:checked").each(function() {
                        window.open("download.action?path="+container_path+"&name="+$(this).attr('filename')); 
                    })
                })
            });
            function loadallfile() {
                var data = {
                    path: container_path
                };
                $.ajax({
                    url: "home.action", //此处需编写后台方法
                    type: "get",
                    data: data,
                    success: function(s) {
                        var tab = $('#tab');
                        tab.html("")
                        s.forEach(function(file) {
                            tab.append('<tr>' +
                                    '<td><input name="ck" type="checkbox" filename="' + file.filename + '" value="checkbox" style="filter:alpha(opacity=0);  width: 23px;height: 20px;display:block" "></td>' +
                                    '<td>' + file.filename + '</td>' +
                                    '<td>' + file.size + '</td>' +
                                    '<td>' + new Date(file.updateAt) + '</td>'
                                    + '</tr>')
                        })
                    }
                });
            }
            function createUploader() {
                var uploader = new qq.FineUploader(
                        {
                            element: document.getElementById('result-uploader'),
                            request: {
                                endpoint: 'uploadfile.action?path=' + container_path //此处需编写后台方法
                            },
                            text: {
                                uploadButton: '<i class="glyphicon glyphicon-plus"></i>上传文件'
                            },
                            template: '<div class="qq-uploader">'
                                    + '<pre class="qq-upload-drop-area"><span>{dragZoneText}</span></pre>'
                                    + '<div class="qq-upload-button btn btn-primary" style="display:inline">{uploadButtonText}</div>'
                                    + '<span class="qq-drop-processing" style="display:none"><span>{dropProcessingText}</span>'
                                    + '<span class="qq-drop-processing-spinner"></span></span>'
                                    + '<ul class="qq-upload-list" style="margin-top: 10px; text-align: center;display:none"></ul>'
                                    + '</div>',
                            classes: {
                                success: 'alert alert-success',
                                fail: 'alert alert-error'
                            },
                            callbacks: {
                                onComplete: function(id, fileName, responseJSON) {
                                    if (responseJSON.success) {
                                        var fielpath = responseJSON.other;
                                        createfile(fileName, fielpath);
                                    }
                                }
                            }
                        });
            }
            function createfile(fileName, filepath) {
                var data = {
                    path: container_path,
                    name: fileName,
                    filepath: filepath
                };
                alert("上传成功");
                $.ajax({
                    url: "createfile.action", //此处需编写后台方法
                    type: "post",
                    data: data,
                    success: function(s) {
                        location.reload();
                    }
                });
            }
        </script>
    </body>
</html>
