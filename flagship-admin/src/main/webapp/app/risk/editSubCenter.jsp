<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>编辑分中心</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <%@ include file="/common/header.jsp" %>
    <script type="text/javascript">
  //负责人下拉框追加内容
	  var json = [];
    //回显  信贷主管 和简称
     $(document).ready(function(){
    	 //信贷主管编号 和 姓名
    	 var creditManagerNo ="${subcenter.creditManagerNo}";
    	 var creditManagerName ="${subcenter.creditManagerName}";
    	 if(null != creditManagerNo && '' != creditManagerName && null !=creditManagerName ){
    		 var nos = creditManagerNo.split(",");
        	 var names = creditManagerName.split(",");
        	 for (var i=0;i<nos.length;i++){
        		 var obj = {}; 
        		 obj.id = nos[i];
        		 obj.text = names[i];
        		 obj.selected= true;
        		 json.push(obj);
        	 }
        	 data = $.parseJSON(JSON.stringify(json));
    		$("#creditManagerName").combobox("loadData", data); 
    	 }
    }); 
        //提交
        function submitForm() {
        	var value = $('#creditManagerName').combobox('getValues');
    		var text = $('#creditManagerName').combobox('getText');
    		$("#creditManagerNo").val(value);
    		$("#creditName").val(text);
            var subCenterForm = $("#subCenterForm");
            subCenterForm.form('submit', {
                url: '${app}/risk/doEditSubCenter',
                onSubmit: function () {
               	 if (subCenterForm.form("validate")) {
               		 
               		var shortName = $("#shortName").textbox("getValue");
                  	if(2>shortName.length || shortName.length>10){
              			$.messager.show({
              				title:'信息提示',
              				msg:'简称长度2~10！!',
              				timeout:3000,
              				showType:'slide'
              			});
              			return false;
              		}
                  	
                  	var name = $("#name").textbox("getValue");
                  	if(null == name || '' == name){
                  		$.messager.show({
              				title:'信息提示',
              				msg:'名称不能为空！',
              				timeout:3000,
              				showType:'slide'
              			});
                  		return false;
                  	}
                  	return true;
               	 }else{
               		 return false;
               	 }
               }, 
                success: function (data) {
                	closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/risk/toSubcenterList?messageCode=" + obj.messageCode,"市场风险管理部");
					parent.closeTab("编辑分中心");
                }
            });
        }

        //取消
        function resetForm() {
            parent.closeTab("编辑分中心");
        }

        //查找负责人
        function openSearch_manager() {
            $("#write_into_flag").val("manager");
            document.getElementById("searchPrincipal").style.display = "block";
            $('#searchPrincipal').dialog({
                title: '查找负责人',
                width: 500,
                height: 250,
                closed: false,
                left: 680,
                top: 60
            });
        }

        //查找信贷主管
        function openSearch_creditManager() {
            $("#write_into_flag").val("creditManager");
            document.getElementById("searchPrincipal").style.display = "block";
            $('#searchPrincipal').dialog({
                title: '查找信贷主管',
                width: 500,
                height: 250,
                closed: false,
                left: 680,
                top: 60
            });
        }

        //负责人列表
        function searchPrincipal() {
            var name = $("#principalName").textbox("getValue");
            var datagrid2;
            datagrid2 = $('#principalTable').datagrid({
                url: '${app}/risk/findPrincipalName?name=' + encodeURI(encodeURI(name)),
                title: '',
                pagination: false,
                fit: true,
                singleSelect: true,
                border: false,
                idField: 'id',
                columns: [[
                    {
                        field: 'name',
                        title: '姓名',
                        width: 120

                    }, {
                        field: 'workCode',
                        title: '员工编号',
                        width: 120
                    }, {
                        field: 'jobTitle',
                        title: '职务',
                        width: 120
                    }, {
                        field: 'depName',
                        title: '部门',
                        width: 120
                    }]],
                onClickRow: function (index, row) {
                	 var writeIntoFlag = $("#write_into_flag").val();
                     var name = row["name"];
                     var orgNo = row["workCode"];

                     if (writeIntoFlag == 'manager'){
                         $("#managerName").textbox("setValue", name);
                         $("#managerNo").val(orgNo);
                         //关闭搜索框
                         $('#searchPrincipal').dialog("close");
                         //清空搜索框和datagrid的值
                         $("#principalName").textbox("setValue","");
                 		//清空datagrid
                 		$('#principalTable').datagrid('loadData',{total:0,rows:[]});
                     } else if (writeIntoFlag == 'creditManager'){
                    	
                     	var obj = {};
                     	var id = row["workCode"];
         				obj.id= id;
         				obj.text = row["name"];
         				obj.selected = true;
                     	var length = json.length;
         				if(length == 0){
         					json.push(obj);
         				}else{
         					//判断json中是否有重复数据
         					var flag = checkJson(json,id);
         					if(flag){
         						json.push(obj);
         					}
         				}
         				data = $.parseJSON(JSON.stringify(json));
         				$("#creditManagerName").combobox("loadData", data); 
                     }
                }
            });
        }
        
      //下拉框去重
    	function checkJson(json,id){
    		for(var i in json){
    			 if(json[i].id == id ){
    				 return false;
    				 break;
    			}
    		}
    		return true;
    	}
    </script>
</head>

<body style="background: white;">
<form id="subCenterForm" class="easyui-form" method="post">
    <table class="tableForm" border="0" width="100%">
        <tr>
            <td class="tdR"><span style="color: red;">*</span>名称:</td>
            <td>
                <input id="name" name="name" class="easyui-textbox"
                       data-options="required:true,validType:['length[0,30]']"
                       value="${subcenter.name}"
                       style="width: 175px;height: 24px;"/>&nbsp;&nbsp;
                <input name="subcenterNo" value="${subcenter.subcenterNo}" hidden>
            </td>
        </tr>

        <tr>
            <td class="tdR">上级:</td>
            <td>
                市场风险管理部
            </td>
        </tr>


        <tr>
            <td class="tdR"><span style="color: red">*</span>简称:</td>
            <td align="left">
                <input id="shortName" name="shortName" class="easyui-textbox"
                       data-options="required:true,validType:['length[2,10]'],missingMessage:'简称长度2~10!'"
                        value="${subcenter.shortName}"
                       style="width: 175px;height: 24px;"/>&nbsp;&nbsp;
            </td>
        </tr>

         <tr>
            <td class="tdR">负责人:</td>
            <td valign="middle">
                <input id="managerName" name="managerName" class='easyui-textbox' readonly
                       data-options="events:{focus:openSearch_manager},multiline:true"
                      style="width: 175px;height: 24px;" value="${subcenter.managerName}"/>
                <input id="managerNo" name="managerNo" type="hidden"/>
            </td>
        </tr>

  				
        <tr>
            <td class="tdR">信贷主管:</td>
            <td valign="middle">
            		<input  class="easyui-combobox" id="creditManagerName"  style="width: 175px;height: 30px;" data-options="events:{focus:openSearch_creditManager},valueField:'id', textField:'text', panelHeight:'auto',multiple:true"  editable="false">
              <!-- 信贷主管编号 -->
  		    <input type="hidden" id="creditManagerNo"  name="creditManagerNo">
  		     <!-- 信贷主管姓名 -->
  		    <input type="hidden" id="creditName" name="creditManagerName">
            </td>
        </tr>

        <tr>
            <td class="tdR"><span style="color: red">*</span>管理营业部:</td>
            <td valign="middle" title="${managementOrgData}">
                <input id="managementOrgData" class='easyui-textbox'
                       data-options="multiline:true"
                       value="${managementOrgData}"
                       style="width:330px;height:60px" disabled/>
            </td>
        </tr>

        <tr>
            <td colspan="4" align="center">
                <a class="easyui-linkbutton" id="submitButton" iconCls="icon-ok" onclick="submitForm();">提交</a>
                &nbsp;
                <a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
            </td>
        </tr>
    </table>
</form>

    <!-- 查找联系人 -->
    <div id="searchPrincipal" style="display: none;overflow: hidden;">

        <div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%;">

            <div data-options="region:'north',split:false" style="width: 100%;height:14%">
                <input type="text" id="principalName" class="easyui-textbox" style="width: 200px;height: 24px;"/>
                <a class="easyui-linkbutton" iconCls="icon-search" onclick="searchPrincipal()">查询</a>
                <input type="hidden" id="write_into_flag" value="manager">
            </div>

            <div data-options="region:'center',split:false" style="width: 100%;height:86%">
                <table id="principalTable"></table>
            </div>
        </div>
    </div>
</body>
</html>
