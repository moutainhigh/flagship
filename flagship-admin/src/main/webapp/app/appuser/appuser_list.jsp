<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户端用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){ 
        	
			$('#file_upload').filebox({
				buttonText:'选择图片',
				buttonAlign:'right'
			})
			
			
			datagrid = $('#datagrid').datagrid({
				url : '${app}/app/users',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'id',
				columns : [[{
					field:'cd',
					checkbox:true
				},{
					field : 'name',
					title : '姓名',
					width : 80
				},{
					field : 'username',
					title : '用户名',
					width : 80 
				},{
					field : 'mobile',
					title : '手机号',
					width : 120 
				},{
					field : 'employeeNo',
					title : '员工编号',
					width : 120 
				},{
					field : 'auname',
					title : '权限',
					width : 500 ,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'sortname',
					title : '类别',
					width : 120 
				},{
					field : 'status',
					title : '状态',
					width : 100,
					formatter:function(value,rowData,rowIndex){
						var retStr = "";
						if(value == 1){
							retStr = "<span style='color:green;'>正常</span>";
						}else if(value == 4){
							retStr = "<span style='color:green;'>ios注册</span>";
						}else{
							retStr = "<span style='color:red;'>已禁用</span>";
						}
						return retStr;
					}
				}
				]]
			});
		});
		
		
		//添加
		function toAdd(){
			parent.createTab('${app}/app/toAdd','添加客户端用户');
		}
		
		//修改
		function toEdit(){
			var rows = datagrid.datagrid('getSelections');
			
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				if(rows[0].status != 1){
					$.messager.alert("信息提示", "请选择状态为\"正常\"的用户!", "info",function(){});
					return;
				}
				parent.createTab('${app}/app/toEdit/' + rows[0].id,'修改客户端用户');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要修改的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//禁用
		function disableFun(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				if(rows[0].status == 2 || rows[0].status==5){
					$.messager.alert("信息提示", "该用户已禁用!", "info",function(){});
					return;
				}
				$.messager.confirm("请确认", "您确实要禁用该用户吗？", function(b){
					if(b){
						openMask();
						if(rows[0].status==1){
							$.ajax({
								async:false,
								type:'post',
								url:"${app}/app/disableOrEnabled?id=" + rows[0].id + "&status=2",
								dataType:'json',
								success:function(msg){
									closeMask();
									parent.createTab('${app}/app/toAppUserList?messageCode=' + msg.messageCode,'客户端用户管理');
								}
							});
						}else{
							$.ajax({
								async:false,
								type:'post',
								url:"${app}/app/disableOrEnabled?id=" + rows[0].id + "&status=5",
								dataType:'json',
								success:function(msg){
									closeMask();
									parent.createTab('${app}/app/toAppUserList?messageCode=' + msg.messageCode,'客户端用户管理');
								}
							});
						}
						
					}
				});
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要禁用的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//启用
		function enabledFun(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				if(rows[0].status == 1 || rows[0].status==4){
					$.messager.alert("信息提示", "该用户已启用!", "info",function(){});
					return;
				}
				$.messager.confirm("请确认", "您确实要启用该用户吗？", function(b){
					if(b){
						openMask();
						if(rows[0].status == 2){
							$.ajax({
								async:false,
								type:'post',
								url:"${app}/app/disableOrEnabled?id=" + rows[0].id + "&status=1",
								dataType:'json',
								success:function(msg){
									closeMask();
									parent.createTab('${app}/app/toAppUserList?messageCode=' + msg.messageCode,'客户端用户管理');
								}
							});
						}else{
							$.ajax({
								async:false,
								type:'post',
								url:"${app}/app/disableOrEnabled?id=" + rows[0].id + "&status=4",
								dataType:'json',
								success:function(msg){
									closeMask();
									parent.createTab('${app}/app/toAppUserList?messageCode=' + msg.messageCode,'客户端用户管理');
								}
							});
						}
						
					}
				});
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要启用的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//删除用户
		function deleteFun(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				//alert(rows[0].status);
				
				$.messager.confirm("请确认", "您确实要删除该用户吗？", function(b){
					if(b){
						openMask();
						if(rows[0].status == 1 || rows[0].status == 2){
							$.ajax({
								async:false,
								type:'post',
								url:"${app}/app/disableOrEnabled?id=" + rows[0].id + "&status=3",
								dataType:'json',
								success:function(msg){
									closeMask();
									parent.createTab('${app}/app/toAppUserList?messageCode=' + msg.messageCode,'客户端用户管理');
								}
							});
						}else{
							$.ajax({
								async:false,
								type:'post',
								url:"${app}/app/disableOrEnabled?id=" + rows[0].id + "&status=6",
								dataType:'json',
								success:function(msg){
									closeMask();
									parent.createTab('${app}/app/toAppUserList?messageCode=' + msg.messageCode,'客户端用户管理');
								}
							});
						}
						
					}
				}); 
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要删除的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
		
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		//清空
		function clearFromFun(datagrid){
			clearForm(datagrid); 
		}
		// 广播
		function toBroadCast(){
			parent.createTab('${app}/app/toBroadCast','APP广播');
		}
		
		// 消息推送（非广播）
		function toUniBroad(){ 
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				var res='';
				for(i=0;i<rows.length;i++){
					res+=rows[i].username+','+rows[i].mobile+';';
				}
				$("#mobiles").val(res);
				$("#mobiles_tip").html(res);
				$('#w').window('open');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要推送的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
			
		}
		
		
		//提交
		function submitForm(){
			var addForm = $("#addForm");
			addForm.form('submit',{
				url:'${app}/app/doUniBroad',
				onSubmit:function(){
					if(addForm.form("validate")){
						if($("input[name=title]").val() != "" && $("input[name=content]").val() != ""){
							openMask();
							return true;
						}else {
							$.messager.alert('提示信息','请填写完整的表单','info');
							return false;
						}
					}else{
						return false;
					}
				},
				success:function(data){
					closeMask();
					$('#w').window('close');
					$('#datagrid').datagrid('unselectAll');
				}
			});
			
		}
		
		//取消
		function resetForm(){
			$('#w').window('close');
			// $('#datagrid').datagrid('unselectAll');
		}
		//修改默认头像
		function defaultHeadImage(){
			$('#uploadImageWindow').window('open');
		}
		//回显图片
		function change_photo(){
	        PreviewImage($("input[name='pictureFile']")[0], 'Img', 'Imgdiv');
	    }
		// 取消修改默认图片
		function cancel() {
			$('#uploadImageWindow').window('close');
		}
		//提交图片
		
		function imgSubmit(){
			var editForm = $("#upload_form");
			if($('#Img').attr('src') == undefined || '' == $('#Img').attr('src')){
				$.messager.alert('提示信息','请选择图片','info');
				return false;
			}
			editForm.form('submit',{
				url:'${app}/app/imageUpload',
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/app/toAppUserList?messageCode=" + obj.messageCode,"客户端用户管理");
				}
			});
		}
		
		/* 图片预览 */
 		function PreviewImage(fileObj,imgPreviewId,divPreviewId){  
		    var allowExtention=".jpg,.bmp,.gif,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;  
		    var extention=fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();              
		    var browserVersion= window.navigator.userAgent.toUpperCase();  
		    if(allowExtention.indexOf(extention)>-1){   
		        if(fileObj.files){//HTML5实现预览，兼容chrome、火狐7+等  
		            if(window.FileReader){  
		                var reader = new FileReader();   
		                reader.onload = function(e){  
		                    document.getElementById(imgPreviewId).setAttribute("src",e.target.result);  
		                }    
		                reader.readAsDataURL(fileObj.files[0]);  
		            }else if(browserVersion.indexOf("SAFARI")>-1){  
		                alert("不支持Safari6.0以下浏览器的图片预览!");  
		            }  
		        }else if (browserVersion.indexOf("MSIE")>-1){  
		            if(browserVersion.indexOf("MSIE 6")>-1){//ie6  
		                document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);  
		            }else{//ie[7-9]  
		                fileObj.select();  
		                if(browserVersion.indexOf("MSIE 9")>-1)  
		                    fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问  
		                var newPreview =document.getElementById(divPreviewId+"New");  
		                if(newPreview==null){  
		                    newPreview =document.createElement("div");  
		                    newPreview.setAttribute("id",divPreviewId+"New");  
		                    newPreview.style.width = document.getElementById(imgPreviewId).width+"px";  
		                    newPreview.style.height = document.getElementById(imgPreviewId).height+"px";  
		                    newPreview.style.border="solid 1px #d2e2e2";  
		                }  
		                newPreview.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";                              
		                var tempDivPreview=document.getElementById(divPreviewId);  
		                tempDivPreview.parentNode.insertBefore(newPreview,tempDivPreview);  
		                tempDivPreview.style.display="none";                      
		            }  
		        }else if(browserVersion.indexOf("FIREFOX")>-1){//firefox  
		            var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);  
		            if(firefoxVersion<7){//firefox7以下版本  
		                document.getElementById(imgPreviewId).setAttribute("src",fileObj.files[0].getAsDataURL());  
		            }else{//firefox7.0+                      
		                document.getElementById(imgPreviewId).setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));  
		            }  
		        }else{  
		            document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);  
		        }           
		    }else{  
		        alert("仅支持"+allowExtention+"为后缀名的文件!");  
		        fileObj.value="";//清空选中文件  
		        if(browserVersion.indexOf("MSIE")>-1){                          
		            fileObj.select();  
		            document.selection.clear();  
		        }                  
		        fileObj.outerHTML=fileObj.outerHTML;  
		    }  
		} 
		
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:30px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="5%">姓名:</td>
					<td width="10%">
						<input id="name" name="name" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="5%">用户名:</td>
					<td width="10%">
						<input id="username" name="username" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="5%">手机号:</td>
					<td width="10%">
						<input id="mobile" name="mobile" maxlength="11" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td>
					<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
					</td>
				</tr>
				 
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
 	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAdd();">新增用户</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toEdit();">修改</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-lock',plain:true" onclick="disableFun();">禁用</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true" onclick="enabledFun();">启用</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="deleteFun();">删除</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-large-smartart',plain:true" onclick="toBroadCast();">APP广播</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-large-smartart',plain:true" onclick="toUniBroad();">消息推送</a> 
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" onclick="defaultHeadImage();">修改默认头像</a> 
	</div>
	
	<div id="w" class="easyui-window" title="消息推送" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:300px;">
	  
	    <form id="addForm" class="easyui-form" method="post"  >
		<table class="tableForm" border="1" width="100%" >
			<tr>
			
			<td width="15%" class="tdR"><span style="color: red">*</span>接收方:</td>
				<td  >
					<input type="hidden" id="mobiles" name="mobiles"/>
					<div id="mobiles_tip" ></div>
				</td> 
				
			</tr>
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>标题:</td>
				<td >
					<input type="text" id="title" name="title" class="easyui-textbox" data-options="required:true,validType:['length[0,10]']" style="width: 200px;height: 24px;"/>
				</td>
				
			</tr>
			<tr>
			
			<td width="15%" class="tdR"><span style="color: red">*</span>内容:</td>
				<td  >
					<input type="text" id="conent" name="content" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 200px;height: 120px;"/>
				</td> 
				
			</tr>
			 
			 
			<tr>
				<td colspan="2" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
			</tr>
		</table> 
	</form>
	    
	</div>
	
	<div id="uploadImageWindow" class="easyui-window" title="修改默认头像" data-options="modal:true,closed:true,iconCls:'icon-save',resizable:false,maximizable:false,collapsible:false,draggable:false,minimizable:false,closable:false" style="width:350px;height:290px;">
	  
	<form id="upload_form" enctype="multipart/form-data"  method="post">
        <center><input class="easyui-filebox" style="width:240px;margin-top: 5px" data-options='onChange:change_photo' id="file_upload" name="pictureFile"/></center>
    </form>
    <center>
    <div id="Imgdiv" >
        <img  id="Img" width="240" height="180px"/>
    </div>
	</center>
	<center><a class="easyui-linkbutton" id="submitButton" style="margin-top: 5px" iconCls="icon-ok" onclick="imgSubmit();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
    <a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:cancel();">取消</a></center>
	
	    
	
  </body>
</html>
