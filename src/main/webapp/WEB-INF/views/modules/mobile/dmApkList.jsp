<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#iosA").click(function(){
                $.ajax({
                    type:'post',
                    url:'${ctx}/mobile/dmApk/isAvailableForIos',
                    dataType:'json',
                    success:function(data){;
						if(data.tatus==0){
                            $("#iosA").val("启用ios");
						}else{
                            $("#iosA").val("停止ios");

						}
                    }
                });
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mobile/dmApk/">版本列表</a></li>
		<shiro:hasPermission name="mobile:dmApk:edit"><li><a href="${ctx}/mobile/dmApk/form">版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dmApk" action="${ctx}/mobile/dmApk/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>平台：</label>
				<form:input path="os" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"><input id="iosA" class="btn btn-primary" type="button" value="${available==0?'停止ios':'启用ios'}"/></li>
		</ul>
	</form:form>


	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>包名</th>
				<th>名称</th>
				<th>平台</th>
				<th>版本</th>
				<shiro:hasPermission name="mobile:dmApk:delete">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dmApk">
			<tr>
				<td>
                        ${dmApk.packagename}
				</td>
				<td>
					${dmApk.name}
				</td>
				<td>
					${dmApk.os}
				</td>
				<td>
					${dmApk.version}
				</td>
				<shiro:hasPermission name="mobile:dmApk:delete">
					<td>
    				<a href="${ctx}/mobile/dmApk/form?id=${dmApk.id}">修改</a>
					<a href="${ctx}/mobile/dmApk/delete?id=${dmApk.id}" onclick="return confirmx('确认要删除该版本吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>