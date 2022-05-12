<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 삭제</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/back.css">
</head>
<body>

	<h2>댓글 삭제</h2>
	
	     <form method="post">
			   <c:forEach var="r" items="${rep }">
			<table class="table table-bordered border border-primary table table-hover ">
			
			  <tr>
			      <td style="text-align:center ;" width="150">내가 작성한 댓글</td>
			    <td width="300">  ${r.reContent}</td>
			</tr>
			
			</table>
			
			
			 <input type="submit" class="btn btn-danger" value="댓글 삭제" />
			   <br> <br>
			</c:forEach>
       <br> <br> <br>
 	</form>
    

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>