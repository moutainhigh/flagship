<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <!--  弹窗title居中  -->
    <style>
        .panel-title {
            text-align: center;
            font-size: 14px;
        }
    </style>
    <title>业绩目标导入</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <script type="text/javascript">

        //下载模板
        function downLoad(){
        	 var fileName =  $("#path").val();
        	 var name = encodeURI(encodeURI(fileName));
             location.href="${app}/money/downLoad?fileName="+name;
        };

        // 上传
        function upload() {
            var multipart = $("#uploadFileInput").val();
            if(multipart==""||multipart==null){
                alert("请先选择文件!");
                return ;
            }

            $("#uploadForm").ajaxSubmit({
                type : 'POST',
                dataType:"json",
                url:'${app}/stratage/assetPlan/assetPlanUpLoad',
                success : function(data) {
                    var count = data.count;
                    var error = data.error;
                    if(1 == data.status ){
                        $.messager.show({
                            title:'信息提示',
                            msg:error,
                            timeout:5000,
                            showType:'slide'
                        });
                    }else if(2 == data.status ){
                        $.messager.show({
                            title:'信息提示',
                            msg:"请下载的模板",
                            timeout:5000,
                            showType:'slide'
                        });
                    }else{
                        $.messager.show({
                            title:'信息提示',
                            msg:"成功上传"+count+"条数据",
                            timeout:3000,
                            showType:'slide'
                        });
                        setTimeout(function(){
                            window.location.reload();
                        },1500);
                    }
                }
            });
        };


        //搜索
        function searchFun() {
            datagrid.datagrid('load',serializeObject($("#searchForm")));
            datagrid.datagrid('clearSelections');
            datagrid.datagrid('clearChecked');
        }

        var datagrid;
        $(function(){
            datagrid = $('#datagrid').datagrid({
                method : 'GET',
                url : '${app}/stratage/assetPlan',
                title : '',
                pagination : true,
                pageSize : <%=Constants.PAGE_SIZE%>,
                pageList : [10,20,30,40,50],
                fit : true,
                singleSelect : true,
                toolbar:"#toolbar",
                border : false,
                idField : 'id',
                columns : [[
                    {
                        field:'rowNumbers',
                        title: '序号',
                        align: 'center',
                        formatter: function(val,rec,index){
                            var op = $('#datagrid').datagrid('options');
                            return op.pageSize * (op.pageNumber - 1) + (index + 1);
                        },
                        width : 60
                    },{
                        field : 'org_name',
                        title : '部门',
                        width : 130,
                        align:'center'
                    },{
                        field : 'business_name',
                        title : '业务',
                        width : 80,
                        align:'center'
                    },{
                        field : 'year',
                        title : '年份',
                        width : 60,
                        align:'center'
                    },{
                        field : '年度总目标',
                        title : '年度总目标',
                        width : 100,
                        align:'center'
                    },{
                        field : '1月',
                        title : '1月',
                        width : 100,
                        align:'center'
                    },{
                        field : '2月',
                        title : '2月',
                        width : 100,
                        align:'center'
                    },{
                        field : '3月',
                        title : '3月',
                        width : 100,
                        align:'center'
                    },{
                        field : '4月',
                        title : '4月',
                        width : 100,
                        align:'center'
                    },{
                        field : '5月',
                        title : '5月',
                        width : 100,
                        align:'center'
                    },{
                        field : '6月',
                        title : '6月',
                        width : 100,
                        align:'center'
                    },{
                        field : '7月',
                        title : '7月',
                        width : 100,
                        align:'center'
                    },{
                        field : '8月',
                        title : '8月',
                        width : 100,
                        align:'center'
                    },{
                        field : '9月',
                        title : '9月',
                        width : 100,
                        align:'center'
                    },{
                        field : '10月',
                        title : '10月',
                        width : 100,
                        align:'center'
                    },{
                        field : '11月',
                        title : '11月',
                        width : 100,
                        align:'center'
                    },{
                        field : '12月',
                        title : '12月',
                        width : 100,
                        align:'center'
                    }
                ]]
            });


            // 部门(下拉框加载)
            $("#orgNo").combobox({
                url:'${app}/stratage/getBusinessNameWithResultsView',
                valueField:'orgNo',
                textField:'orgName'
            });

            // 业务(下拉框加载)
            $("#businessNo").combobox({
                url:'${app}/stratage/assetPlan/selectAssetBusiness',
                valueField:'businessNo',
                textField:'businessName'
            });

            // 年份(下拉框加载)
            $("#year").combobox({
                url:'${app}/stratage/assetPlan/selectUsefulYear',
                valueField:'year',
                textField:'yearDesc'
            });

        });

        //清空
        function clearFromFun(datagrid){
            clearForm(datagrid);
        }
    </script>
</head>
<body class="easyui-layout" fit="true" style="width: 100%;height: 100%;" >

<div region="north" border="false" style="height:95px;  overflow:hidden;background-color: #F4F4F4">
    <form id="uploadForm"  enctype="multipart/form-data" style="margin-top: 10px;">
        <table class="searchForm">
            <tr>
            <td  width="10px"></td>
                <td>
                    <input type="file" name="file" id="uploadFileInput" >
                </td>
                <td>
                    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="upload()">上传</a>
                    <a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-download" onclick="downLoad()">模板下载</a>
                </td>
            </tr>
        </table>
    </form>

    <form id="searchForm">
        <table class="searchForm">
            <tr>
					<td  width="10px"></td>
                    <td class="tdR"><span>部门:</span></td>
                    <td  width="5px"></td>
                    <td>
                        <input id="orgNo" name="orgNo" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
                    </td>
                    <td  width="20px"></td>
                    <td>业务:</td>
                    <td  width="5px"></td>
                    <td>
                        <input id="businessNo" name="businessNo" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
                    </td>
                    <td  width="20px"></td>
                    <td>年份:</td>
                    <td  width="5px"></td>
                    <td>
                        <input id="year" name="year" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
                    </td>
                    <td colspan="2" style="padding-left: 60px;">
                        <a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
                        <a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
                    </td>

            </tr>
        </table>
        	    <input type="hidden" id="path" name="ruleName" value="业绩目标导入模板.xls">
    </form>
</div>

<div region="center" border="false" style="overflow: hidden;">
    <table id="datagrid"></table>
</div>
</body>
</html>
