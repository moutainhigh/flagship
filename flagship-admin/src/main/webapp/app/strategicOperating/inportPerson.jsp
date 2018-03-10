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
    <title>花名册导入</title>
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
                url:'${app}/stratage/assetPerson/assetPersonUpLoad',
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
                method : 'POST',
                url : '${app}/stratage/assetPerson',
                title : '',
                pagination : true,
                pageSize : <%=Constants.PAGE_SIZE%>,
                pageList : [10,20,30,40,50],
                fit : true,
                singleSelect : true,
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
                        field : 'depName',
                        title : '部门',
                        width : 130,
                        align:'center'
                    },{
                        field : 'name',
                        title : '姓名',
                        width : 80,
                        align:'center'
                    },{
                        field : 'districtName',
                        title : '大区',
                        width : 130,
                        align:'center'
                    },{
                        field : 'salesdepName',
                        title : '营业部',
                        width : 130,
                        align:'center'
                    },{
                        field : 'teamName',
                        title : '团队',
                        width : 130,
                        align:'center'
                    },{
                        field : 'subTeamName',
                        title : '小团',
                        width : 130,
                        align:'center'
                    },{
                        field : 'personNo',
                        title : '员工编号',
                        width : 130,
                        align:'center'
                    },{
                        field : 'mobile',
                        title : '手机号',
                        width : 130,
                        align:'center'
                    },{
                        field : 'dimissionTime',
                        title : '离职时间',
                        width : 130,
                        align:'center',
                        formatter:function(value,row,index){
                            if (value == null || value == ''){
                                return null;
                            }
                            var unixTimestamp = new Date(value);
                            return unixTimestamp.toLocaleDateString("zh");
                        }
                    }
                ]]
            });


            // 部门(下拉框加载)
            $("#orgNo").combobox({
                url:'${app}/stratage/getBusinessNameWithResultsView',
                valueField:'orgNo',
                textField:'orgName'
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
                        <input id="orgNo" name="depNo" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
                    </td>
                    <td  width="20px"></td>
                    <td class="tdR"><span>姓名:</span></td>
                    <td  width="5px"></td>
                    <td>
                        <input id="name" name="name" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
                    </td>
                    <td  width="20px"></td>
                    <td class="tdR"><span>手机号:</span></td>
                    <td  width="5px"></td>
                    <td>
                        <input id="mobile" name="mobile" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
                    </td>
                    <td colspan="2" style="padding-left: 60px;">
                        <a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
                        <a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
                    </td>

            </tr>
        </table>
        	    <input type="hidden" id="path" name="ruleName" value="花名册导入模板.xls">
    </form>
</div>

<div region="center" border="false" style="overflow: hidden;">
    <table id="datagrid"></table>
</div>
</body>
</html>
