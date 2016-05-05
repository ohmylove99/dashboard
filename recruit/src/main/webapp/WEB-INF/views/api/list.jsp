<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="reqUrl"
	value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<html>
<head>
<title>Restful API</title>
</head>

<body>

	<h3>Restful API</h3>
	<h4>API</h4>
	<ul>
		<li>Task List: <a href="${reqUrl}/api/v1/task">${reqUrl}/api/v1/task</a></li>
		<li>Task Get(id=1) : <a href="${reqUrl}/api/v1/task/1">${reqUrl}/api/v1/task/1</a></li>
	</ul>

	<h4>Update API</h4>
	<ul>
		<li>Create task: ${reqUrl}/api/v1/task method=Post, consumes=JSON</li>
		<li>Update task: (id=1) ：${reqUrl}/api/v1/task/1 method=Put,
			consumes=JSON</li>
	</ul>
</body>
</html>
