<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style>
.star {
    position: relative;
    font-size: 2rem;
    color: #ddd;
  }
  
  .star input {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    opacity: 0;
    cursor: pointer;
  }
  
  .star span {
    width: 0;
    position: absolute; 
    left: 0;
    color: yellow;
    overflow: hidden;
    pointer-events: none;
  }


</style>

<meta charset="UTF-8">
<title>리뷰 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/back.css">
</head>
<body>

	<h2>리뷰 수정</h2>
	
	<form:form commandName="ArtUpdateData"  method="POST">
	<script src="${pageContext.request.contextPath }/resources/ckeditor1/ckeditor.js">
	</script>
	<c:forEach var="a" items="${art }">
	<textarea id="ck1" name="aContent" >${a.aContent }</textarea>
	<script>
			
					CKEDITOR.replace('ck1',  {
						width:800,
						height:400,
						filebrowserUploadUrl : '${pageContext.request.contextPath}/imageUpload'	});
				</script>
</c:forEach>

						<h3>리뷰 점수</h3>
			<div class=".container">
			
			<span class="star"> ★★★★★ <span>   ★★★★★ </span>
            <input type="range" name="aScore" oninput="drawStar(this)" value="1" step="1" min="0" max="10">
            </span>
			</div>
				
			<script>
			 const drawStar = (target) => {
		    	 console.log(target.value);
		    	    document.querySelector(`.star span`).style.width = target.value * 10+"%";
		    	  }
			 </script>
		 <input type="submit" class="btn btn-info" value="리뷰 수정" />
         
   
			</form:form>
		
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>