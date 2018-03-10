<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hzcf.flagship.constant.Constants"%>
<%
	request.setAttribute("app",request.getContextPath());
	request.setAttribute("vEnter", "\n");
%>

<link href="${app}/js/jquery-easyui-1.4.1/themes/default/easyui.css" rel="stylesheet" type="text/css">
<link href="${app}/js/jquery-easyui-1.4.1/themes/icon.css" rel="stylesheet" type="text/css">
<link href="${app}/js/common/css/base.css" rel="stylesheet" type="text/css">

<script src="${app}/js/jquery-easyui-1.4.1/jquery-1.8.0.min.js" charset="UTF-8" type="text/javascript"></script>
<script src="${app}/js/jquery-easyui-1.4.1/jquery.easyui.min.js" charset="UTF-8" type="text/javascript"></script>
<script src="${app}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js" charset="UTF-8" type="text/javascript"></script>

<script src="${app}/js/common/js/common.js" charset="UTF-8" type="text/javascript"></script>
<script type="text/javascript" src="${app }/js/area/area.js"></script>
<script src="${app}/js/common/js/idCardValidate.js" charset="UTF-8" type="text/javascript"></script>
<script src="${app}/js/common/js/util.js" charset="UTF-8" type="text/javascript"></script>
<script src="${app}/js/common/js/jquery.formatCurrency-1.4.0.js" charset="UTF-8" type="text/javascript"></script>
<script src="${app}/js/common/js/jquery-form.js" type="text/javascript"></script>   
<link rel="stylesheet" href="${app}/js/kindeditor-4.1.4/themes/default/default.css" />
<link rel="stylesheet" href="${app}/js/kindeditor-4.1.4/plugins/code/prettify.css" />
<script charset="utf-8" src="${app}/js/kindeditor-4.1.4/kindeditor.js"></script>
<script charset="utf-8" src="${app}/js/kindeditor-4.1.4/lang/zh_CN.js"></script>
<script charset="utf-8" src="${app}/js/kindeditor-4.1.4/plugins/code/prettify.js"></script>
<script type="text/javascript" src="${app}/js/kindeditor-4.1.4/kindeditor.js"></script>  
<script type="text/javascript" src="${app}/js/kindeditor-4.1.4/base64.js"></script>  
<script type="text/javascript" src="${app}/js/common/js/jquery.serializejson.js"></script>  
<script>
	$(function(){
		if("" != "${messageCode}"){
			$.messager.show({
				title:'信息提示',
				msg:'${messageCode}',
				timeout:5000,
				showType:'slide'
			});
		}
	});
	
	/**
	 * 删除附件
	 * @param {Object} delid 附件ID
	 */
	function delA(path,delid){
		$.messager.confirm("信息提示","请注意，删除的附件将无法恢复，是否确认删除？",function(b){
			if(b){
				$.ajax({
					async:false,
					type:'post',
					url:'${app}/admin/upload/fileUpload_delAttachment.shtml',
					dataType:'html',
					data:'delAttachmentPath=' + path + "&id=" + delid,
					success:function(msg){
						if("删除成功" == msg){
							$("#li_" + delid).remove(); //移除选项
							$("br").remove();
							attachmentClear(delid);
						}else{
							$.messager.alert("信息提示",msg,"error");
						}
					}
				});
			}
		});
	}

	/**
	 * 附件下载
	 * @param {Object} path 路径
	 * @param {Object} fileName 文件名
	 */
	function downloadFile(path,fileName){
		if(getOs() == "Firefox"){
			window.location.href="${app}/download.shtml?path=" + path +"&fileName="+fileName;
		}else{
			window.location.href="${app}/download.shtml?path="+encodeURIComponent(encodeURIComponent(path))+"&fileName="+encodeURIComponent(encodeURIComponent(fileName));
		}
	}
	 /**
	  * 后台列表格式化金额
	  * @param {Object} s 金额字符串
	  * @param {Object} n 保留小数位数
	  * @return {TypeName} 
	  */
	function fmoney(s, n){   
	   n = n > 0 && n <= 20 ? n : 2;   
	   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	   var l = s.split(".")[0].split("").reverse(),   
	   r = s.split(".")[1];   
	   t = "";   
	   for(i = 0; i < l.length; i ++ ){   
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	   }   
	   return t.split("").reverse().join("") + "." + r;   
	} 
</script>
<input type="hidden" name="contextPath" id="contextPath" value="${app}"/>
<jsp:include page="/common/submit_page.jsp"></jsp:include>
