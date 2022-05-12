<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/back.css">
</head>
<body>

	<h2>댓글 수정</h2>
	
	<form:form commandName="repupdateData"  method="POST">
		<table class="table table-bordered border border-primary">
		
		
		     <tr>
              <td style="text-align:center;">수정할 댓글 내용</td>
              <td colspan="2"><form:textarea style="width:500px;height:200px;font-size:20px;" path="reContent"  />
                   <form:errors path="reContent" />
               </td>
			</tr>
			
			</table>

			   
		 <input type="submit" class="btn btn-info" value="댓글 수정" />
         
   
			</form:form>
		
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>