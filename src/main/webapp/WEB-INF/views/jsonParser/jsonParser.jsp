<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Json Parser</title>
    </head>
    <body>
    	<textarea id="inputJson"></textarea>
    	<textarea id="outputString"></textarea>
    	<button id="parseJson">CLICK</button>
 	</body>
 	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 	<script>
 		$(function() {
 			$("#parseJson").on("click", function() {
 				$.ajax({
 					type:"get",
 					url:"${ctx}/jsonParser/jsonString",
 					data: {
 						inputJson: $("#inputJson").val()
 					},
 					success: function(respData) {
 						$("#outputString").val(respData);
 					}
 				});
 			});
 		});
 	</script>
</html>