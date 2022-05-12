<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 등록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/back.css">
</head>
<body>

	<h2>댓글 등록</h2>
	
	<form:form commandName="repData"  method="POST">
		<table class="table table-bordered border border-primary">
		
		
		     <tr>
              <td style="text-align:center;">댓글 내용</td>
              <td colspan="2"><form:textarea style="width:500px;height:200px;font-size:20px;" path="reContent"  />
                   <form:errors path="reContent" />
               </td>
			</tr>
			
			</table>

		 <input type="submit" class="btn btn-primary" value="댓글 등록" />
		 <button type="button" class="btn btn-light" onclick="location.href='/review/list'">리스트로 이동</button>
         
      <br>  <br>  <br>
   
    
			</form:form>
         
			   <c:forEach var="r" items="${rep1 }">
			<table class="table table-bordered border border-primary table table-hover ">
			
			  <tr>
			      <td style="text-align:center ;" width="150" class="fw-bold table-primary">댓글 넘버</td>
			    <td width="300" class="fw-bold table-primary">  ${r.reNum}</td>
			</tr>
			
			
			    <tr>
			      <td style="text-align:center ;">리뷰 넘버</td>
			    <td width="300">  ${r.aNum}</td>
			</tr>
			
			    <tr>
			      <td style="text-align:center ;">맴버 넘버</td>
			    <td width="300">  ${r.mNum}</td>
			</tr>
			
				    <tr>
			      <td style="text-align:center ;">입력한 댓글 날짜</td>
			    <td width="300">  ${r.reregDate}</td>
			</tr>
	
				    <tr>
			      <td style="text-align:center ;">댓글 내용</td>
			    <td width="300" height="200">  ${r.reContent}</td>
			</tr>
		
			</table>
	<span >			
<button type="button" class="btn btn-info" onclick="location.href='/review/repupdate/${r.aNum}/${r.reNum }'">수정하기</button> 
	</span>
		<span>
 <button type="button" class="btn btn-danger" class="butt" onclick="location.href='/review/repdel/${r.aNum}/${r.reNum }'">삭제하기</button> 
      </span>
     <br>
			<br>
		</c:forEach>
			

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>