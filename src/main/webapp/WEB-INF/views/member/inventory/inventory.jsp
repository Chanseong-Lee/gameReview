<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>인벤토리</title>
<style>
table{
	border: 2px sold gray;
	border-collapse: collapse;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<ul class="navbar-nav">
	 <li class="nav-item">
		<h1><sec:authentication property="principal.nickname"/>의 인벤토리 </h1>
	 </li>
</ul>
</nav>
	<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
		<div class="toast-container">
		  	<div id="liveToastSuccess" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
	    		<div class="toast-header">
	      			<img width="15px" src="${pageContext.request.contextPath }/resources/images/user_icon/default_admin_icon.png" class="rounded me-2">
	      			<strong class="me-auto">관리자</strong>
	      			<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
	    		</div>
	    		<div class="toast-body">
	      			성공적으로 아이템이 바뀌었습니다.
	    		</div>
	  		</div>
		  	<div id="liveToastDelete" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
	    		<div class="toast-header bg-warning">
	      			<img width="15px" src="${pageContext.request.contextPath }/resources/images/user_icon/default_admin_icon.png" class="rounded me-2">
	      			<strong class="me-auto">관리자</strong>
	      			<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
	    		</div>
	    		<div class="toast-body">
	      			성공적으로 아이템이 삭제되었습니다.
	    		</div>
	  		</div>
		</div>
	</div>
	<div class="items">
		<table class="table table-striped table-hover table-sm" id ="itemTable">

		</table>	
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
window.onload = function(){
	listAjax();
}


let itemTable = document.querySelector("#itemTable");
let listAjax = function(){
    fetch("inventory/inventoryList")
    .then(function(response){
        return response.json();
    }).then(function(data){
        console.log(data);
        let num = 0;
        let str = "<tr><th>아이콘</th><th>이름</th><th>구입가격</th><th>버리기</th></tr>";
        for(var i in data){
        	let checked = "";
        	let style="";
        	if(data[i].invenUse == 'Y'){
        		checked = "checked";
        		style="style=\"background-color: #B0F6AC\""
        	}else if(data[i].invenUse == 'N'){
        		checked = "";
        	}
        	
        	str += "<tr id=\"item"+ num +"\""+style+">";
        	str += "<td>";
            str += "<input class=\"form-check-input\" type=\"radio\" id=\"" + data[i].itemName + "\" name=\"itemNum\" value=\"" + data[i].itemNum + "\" " + checked + " onclick=\"clickRadio("+data[i].itemNum+")\">";
        	str += "&nbsp;&nbsp;<label class=\"form-check-label\" for=\""+ data[i].itemName +"\">";
            if(data[i].itemNum == 1){
                str += "<img src=\"<c:url value='/resources/images/user_icon/"+ data[i].itemFilename +"'/>\" width=\"15\">" 
            }else if(data[i].itemNum == 6){
            	str += "<img src=\"<c:url value='/resources/images/user_icon/"+ data[i].itemFilename +"'/>\" width=\"15\">" 
        	}else{
                str += "<img src=\"<c:url value='/images/icons/"+ data[i].itemFilename +"' />\" width=\"15\">"
            }            
            str += "</label>"
            str += "</td>";
            str += "<td>"+ data[i].itemName +"</td>";
            str += "<td>"+ data[i].itemPrice +"</td>";
            str += "<td>";
            if(checked != 'checked' && data[i].itemNum != 1 && data[i].itemNum != 6){
                str+="<button class=\"btn btn-warning btn-sm\" type=\"button\" onclick=\"deleteItem("+data[i].itemNum+")\" >버리기</button>";
            }
            str += "</td>";
            str += "</tr>";
            itemTable.innerHTML=str;
            num+=1;
        }
    })
}

let clickRadio = function(itemNum){
	const data = {
			itemNum:itemNum,
	}	
	
	fetch("inventory/selectItem",{
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		},
		body: JSON.stringify(data),
	}).then(function(response){
		return response.text();
	}).then(function(text){
		if(text=='1'){
			console.log("성공!");
			listAjax();
		}else if(text=='2'){
			console.log("실패!");
			alert("서버에러!");
			self.close();
		}
	})
	var toastLiveExample = document.getElementById('liveToastSuccess');
	var toast = new bootstrap.Toast(toastLiveExample);

    toast.show();
}

let deleteItem = function(itemNum){
	const data = {
			itemNum:itemNum,
	}
	let flag = confirm("정말 아이템을 버리시겠습니까?");
	if(flag){
	
		fetch("inventory/deleteItem",{
			method: "POST",
			headers: {
				"Content-Type": "application/json; charset=utf-8"
			},
			body: JSON.stringify(data),
		}).then(function(response){
			return response.text();
		}).then(function(text){
			if(text=='1'){
				console.log("성공!");
				listAjax();
				var toastLiveExample = document.getElementById('liveToastDelete');
				var toast = new bootstrap.Toast(toastLiveExample);
				toast.show();
			}else if(text=='2'){
				console.log("실패!");
				alert("서버에러!");
				self.close();
			}else if(text=='3'){
				console.log("실패!");
				alert("잘못된 접근!");
				self.close();
			}
		})
	}
}


let turnGreen = function(){
    let radios = document.getElementsByName("itemNum");
	console.log(radios)
	console.log(radios.length)
    for(var i = 0; i < radios.length; i++){//0 1 2
    	if(radios[i].checked){
    		document.getElementById("item"+(i)).style.backgroundColor = "#B0F6AC";
    	}else{
    		document.getElementById("item"+(i)).style.backgroundColor = "white";
    	}
    }
}
</script>
</body>
</html>