<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<style>
			#skin_container{padding:2px;
							width: 165px;
							height: 135px;
							border: 1px solid;
							overflow: hidden;
							}
			.skin_box{
					margin: 2px;
					width: 50px;
					height: 50px;
					float: left;
					display: block;
					cursor: pointer;
					border-radius: 20px;
					}
					
			#day_container{width: 800px;
						   height: 60px;
						   overflow: hidden;
						   }
			
			.uv_box{width: 40px;
					height: 50px;
					border: 1px solid;
					float: left;
					}
					
			#slide_container{ width: 800px;
							  height: 60px;
							  border: 6px groove #5a5a73;
		 					  cursor: pointer;
		 					  border-radius: 30px;
		 					  background-color: #2d2d36;
		 					  position: relative;
		 					  overflow: hidden; 
							  animation: linechange 0.5s linear infinite;
						}	

			#slide_container h2{position:absolute;
								z-index: 1;
								left: 35%}
								
			#slide{width: 200px;
					height: 200px;
					z-index: 2;
					position: absolute;
					border-radius: 50px;
					margin-top:-40px;
					left: -100px;
					}

			#slide_back{width: 200px;
					height: 200px;
					z-index: 2;
					position: absolute;
					margin-top:-5px;
					border-radius: 50px;
					top: 0;
					left: -100px;
					}

			#skin_type{width: 30px;
					   height: 30px;
					   border-radius: 20px;
					   border: 1px solid;
						}	
						
			   @keyframes linechange {
			        0% {
			             border: 6px solid #5a5a73;
			        }100% {
			          	 border: 6px solid #88b3a8;;
			        }
		    	}	
		</style>
		
		<script>
			// 피부 타입 바꾸기
			function change_color(color) {
				let skin_type = document.getElementById("skin_type");
				skin_type.style.backgroundColor = color;
				
				let skinType = document.getElementById("skinType");
				skinType.value = color;
			}
		
			// uv 타입 바꾸기
			function uv_change(uv_color) {
				let uvType = document.getElementById("uvType");
				uvType.value =  uv_color;
				alert( uv_color);
				
				let slide = document.getElementById("slide");
				let slide_back = document.getElementById("slide_back");
				slide.style.backgroundColor = uv_color;
				slide_back.style.backgroundColor = uv_color;
			}
			
			// 게이지 상승 함수
			function gauge() {
				let uvType = document.getElementById("uvType");
				let skinType = document.getElementById("skinType");
				
				let uv_arr = ["#558B2F", "#F9A825", "#EF6C00", "#B71C1C", "#6A1B9A"];
				let shin_arr = ["#f7dac6", "#f2c9ac", "#d6aa8b", "#bd855e", "#8c5738", "#382012"];
				
				let uv_level;
				let shin_level;
				
				// uv 타입에 따른 index 번호 지정하기
				for(let i = 0; i < uv_arr.length; i++){
					if(uv_arr[i] == uvType.value){
						uv_level = i;
						break;
					}
				}
				
				// 피부 타입에 따른 index 번호 지정하기
				for(let i = 0; i < shin_arr.length; i++){
					if(shin_arr[i] == skinType.value){
						shin_level = i;
						break;
					}
				}
				console.log(uv_level + "/" + shin_level);
				
				let slide = document.getElementById("slide");
				slide.style.backgroundColor = uvType.value;
				
				let uv_min = [
				// low ......	
//				    [20, 15, 10, 8, 5], //피부타입1
				    [20, 15, 10, 8, 5], //피부타입1
				    [30, 20, 15, 10, 8], //피부타입2
				    [40, 30, 20, 15, 10], //피부타입3
				    [60, 40, 30, 20, 15], //피부타입4
				    [80, 60, 40, 30, 20], //피부타입5
				    [0, 80, 60, 40, 30] //피부타입6
				];
				
				
				let slide_back = document.getElementById("slide_back");
			    // 동적으로 <style> 요소 생성
		        let styleSheet = document.createElement("style");
		        styleSheet.type = "text/css"; // JavaScript를 사용하여 동적으로 생성된 <style> 요소의 타입을 설정하는 코드
											// CSS 파일이 아닌 HTML 문서 내에서 스타일을 정의할 때는 대부분 text/css를 사용
		        // 동적으로 키프레임 애니메이션 정의
/* 	 	        let keyframes_rotate = " @keyframes rotate { 0% { transform: rotate(0deg); left: -100px; }   100% { transform: rotate(360deg); left: 90%; } } #slide{ animation: rotate 8s linear;  } ";
		        let keyframes_back = " @keyframes back { 0% { width: 0px;}   100% { width: 120%; } } #slide_back{ animation: back 8s linear;  } ";  */

		        // 동적으로 키프레임 애니메이션 정의
		        let keyframes_rotate = " @keyframes rotate { 0% { transform: rotate(0deg); left: -100px; }   100% { transform: rotate(360deg); left: 90%; } } #slide{ animation: rotate "+uv_min[shin_level][uv_level]+"s linear;  } ";
		        let keyframes_back = " @keyframes back { 0% { width: 0px;}   100% { width: 120%; } } #slide_back{ animation: back "+uv_min[shin_level][uv_level]+"s linear;  } ";  

		        // <style> 요소에 키프레임 애니메이션 추가
		        styleSheet.innerHTML = keyframes_rotate + "\n" + keyframes_back;
		        
		        document.head.appendChild(styleSheet);

				
			}
		</script>
	</head>
	<body>
		<table border="1" align="center">
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.uv_time}</td>
					<td>${vo.uv}</td>
					<td>${vo.altitude}</td>
					<td>${vo.azimuth}</td>
				</tr>
			</c:forEach>
		</table>
		
		<hr>
		<br>

		<!-- 선택한 값들 input 태그에 넣어두고 필요할 때 불러오기 -->
		<input type="hidden" id="skinType">
		<input type="hidden" id="uvType">
		
		<div id="day_container">
			<c:forEach var="vo" items="${list}">
				<!-- calc() 함수는 CSS 스타일 시트에서 사용되어 스타일 속성의 값을 동적으로 계산하여 설정할 수 있는 기능을 제공 -->
				<div class="uv_box" onclick="uv_change('${vo.uv_color}');" style="width: calc(100% / ${list.size()} - 2px); background-color: ${vo.uv_color}">
					${vo.uv}			
				</div>
			</c:forEach>
		</div>
		<br>
		
		<div id="slide_container" onclick="gauge();" align="center">
			<h2 align="center" style="color: #d3d3f2">Click this Point !</h2>
			<div id="slide"></div>
			<div id="slide_back"></div>
		</div>

		
		
		<br>
		your skin type<div id="skin_type"></div>
		<br>
		
		<div id="skin_container">
		 Check your skin
		<br>
			<div class="skin_box" style="background-color: #f7dac6" onclick="change_color('#f7dac6');"></div>
			<div class="skin_box" style="background-color: #f2c9ac" onclick="change_color('#f2c9ac');"></div>
			<div class="skin_box" style="background-color: #d6aa8b" onclick="change_color('#d6aa8b');"></div>
			<div class="skin_box" style="background-color: #bd855e" onclick="change_color('#bd855e');"></div>
			<div class="skin_box" style="background-color: #8c5738" onclick="change_color('#8c5738');"></div>
			<div class="skin_box" style="background-color: #382012" onclick="change_color('#382012');"></div>
		</div>
		
	</body>
</html>