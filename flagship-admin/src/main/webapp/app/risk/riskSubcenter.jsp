<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>分中心</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <script type="text/javascript">
        var datagrid = null;
        $(document).ready(function () {

            //初始化加载营业部列表
            datagrid = $('#datagrid').datagrid({
                url: '${app}/risk/subcenterList',
                title: '',
                pagination: true,
                singleSelect : true,
                pageSize: <%=Constants.PAGE_SIZE_TWENTY%>,
                pageList: [10, 20, 30, 40, 50],
                fit: true,
                idField : 'id',
                border: false,
                columns: [
                    [
                        {
                            field: 'subcenterNo',
                            title: '组织编号',
                            width: 240
                        },
                        {
                            field: 'name',
                            title: '组织名称',
                            width: 160,
                            align:'center',
                            formatter: function (value, row, index) {
                                if (null == value) {
                                    return "";
                                }
                                return value;
                            }
                        },
                        {
                            field: 'shortName',
                            title: '简称',
                            width: 160,
                            align:'center'
                        },
                        {
                            field: 'managerName',
                            title: '负责人',
                            width: 160,
                            align:'center'
                        },
                        {
                            field: 'operate',
                            title: '操作',
                            width: 160,
                            align:'center',
                            formatter: function (value, row, index) {
                                var ed = "<a href='#' onclick='edit(\""+row.id+"\",\""+index+"\");' >编辑</a>";
                                var de = "<a href='#' onclick='del(\""+row.name+"\",\""+row.subcenterNo+"\");' >删除</a>";
                                return ed +"&nbsp;&nbsp;&nbsp;&nbsp;"+de;
                            }
                        }
                    ]
                ]
            });
        });

        //删除
        function del(name,subcenterNo){
            $.messager.confirm("请确认", "您确实要删除该分中心吗？", function(b){
                if(b){
                    openMask();
                    $.ajax({
                        async:false,
                        type:'post',
                        url: "${app}/risk/deleteSubCenter/" + subcenterNo,
                        data:{
                            "name":name
                        },
                        dataType:'json',
                        success:function(msg){
                            closeMask();
                            parent.createTab('${app}/risk/toSubcenterList?messageCode=' + msg.messageCode,'市场风险管理部');
                        }
                    });
                }
            });
        }
        //编辑负责人
        function edit(id, index) {
            parent.createTab('${app}/risk/toEditSubCenter/'+id,'编辑分中心');
        }

        //点击新增 弹框
        function toAdd() {
            parent.createTab('${app}/risk/toAddSubCenter','添加分中心');
        }

    </script>
</head>
<body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">

<div region="center" style="background:#eee;">
    <!-- 结束展示树的div -->
    <div id="div" style="height:10%;width: 100%">
        <a style="width: 100px;height: 25px;margin-top: 12px;margin-left: 10px" class="easyui-linkbutton" iconCls="icon-add"
           onclick="javascript:toAdd();">新增</a>
    </div>

    <!-- 营业部列表 -->
    <div style="height:90%;width: 100%">
        <table id="datagrid"></table>
    </div>
</div>
</body>
</html>