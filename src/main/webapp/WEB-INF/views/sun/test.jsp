<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<style>
			.container{
		        position: relative;
				margin : auto;
		        border: 3px solid; 
		        width: 30px;
		        height: 100px;
		        overflow: hidden;
			}
		
		    .square {
		        width: 150px;
		        height: 150px;
		        position: absolute;
		        border-radius: 30px;/*이거 조정*/
		        background-color: blue;
		        animation: rotate 3s linear infinite; /* rotate 애니메이션을 적용 애니메이션의 이름은 rotate이며, 3초 동안 일정한 속도로(linear) 회전하며(infinite) 무한히 반복 */
		    }
    
		    /* rotate 애니메이션 키프레임 */
		    @keyframes rotate {
		        0% {
		            transform: rotate(0deg);
			        top : 110px;
		        }
		        100% {
		            transform: rotate(360deg);
			        top : 20px;
		        }
		    }
		</style>
	</head>
	
	<body>
		<div class="container">
			<div class="square"></div>
		</div>
	</body>
</html>