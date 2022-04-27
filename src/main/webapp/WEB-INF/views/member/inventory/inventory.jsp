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
<title>인벤토리</title>
<style>
table{
	border: 2px sold gray;
	border-collapse: collapse;
}
</style>
</head>
<body>
<h1><sec:authentication property="principal.nickname"/>의 인벤토리 </h1>
	<div class="items">
		<table id ="itemTable">
			
<!-- 
<c:if test="${not empty items }">
	<c:forEach var="item" items="${items }">
		<c:if test="${item.invenUse == 'Y' }">
			<c:set var="checked" value="checked"/>
		</c:if>
		<c:if test="${item.invenUse == 'N' }">
			<c:set var="checked" value=""/>
		</c:if>
			<tr style="background-color: #B0F6AC">
				<td>
					<input type="radio" id="${item.itemName }" name="iconNum" value="${item.itemNum }" ${checked } onclick="clickRadio()">
					<label for="${item.itemName }">
					<c:if test="${item.itemNum == 1 }">
						<img alt="아이콘이미지" src="<c:url value='/resources/images/user_icon/${item.itemFilename}'/>" width="15">
					</c:if>
					<c:if test="${item.itemNum != 1 }">
						<img alt="아이콘이미지" src="<c:url value='/images/icons/${item.itemFilename}'/>" width="15">
					</c:if>
					</label>
				</td>
				<td>${item.itemName }</td>
				<td>${item.itemPrice }</td>
				<td>
					<c:if test="${checked != 'checked' }">
					<button type="button">버리기</button>
					</c:if>
				</td>
			</tr>
	</c:forEach>
</c:if>
-->
		</table>	
	</div>
</body>

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
            str += "<input type=\"radio\" id=\"" + data[i].itemName + "\" name=\"itemNum\" value=\"" + data[i].itemNum + "\" " + checked + " onclick=\"clickRadio("+data[i].itemNum+")\">";
        	str += "<label for=\""+ data[i].itemName +"\">";
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
                str+="<button type=\"button\" onclick=\"deleteItem("+data[i].itemNum+")\" >버리기</button>";
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
</html>