<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html ng-app="myApp">
  <head>
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src="<%=basePath%>/page/js/angular.min.js"></script>
  <script>
  console.log(angular.fromJson('{"userName":"gaojian", "password":"gaojian123"}'));
    var app = angular.module('myApp', []);
    app.controller('MyController', function($scope, $http) {
	    $http({
		    method: 'POST',
		    url: '<%=basePath%>getUser',
		    data: {"userName":"gaojian", "password":"gaojian123"},
		    headers: {'Content-type': 'application/json', 'Accept': 'application/json'},
		    })
	    .then(function(result) {
	        console.log(result);
	    }, function(error) {
	        console.log(error);
	    });
        //$scope.name="hello world!";
    });
  </script>
  </head>
  
  <body>
<div ng-controller="MyController">
  <h1>{{name}}</h1>
</div>
  </body>
</html>
