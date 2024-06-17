<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
		
		/*-------------------------------------------------------------------------------------
		Headers 객체는 요청에 포함할 HTTP 헤더를 설정하기 위해 사용된다. 
		HTTP 헤더는 클라이언트와 서버 간의 요청 및 응답에 대한 메타데이터를 전달하는 데 사용.
		
		fetch 함수는 네트워크 요청을 수행하고, 프로미스를 반환하여 응답을 처리
		프로미스는 비동기 작업의 완료 또는 실패를 나타내는 객체로 .then, catch 메서드 등이 있다.
		
		*.then 메서드 안에 원하는 인수나 메서드를 자유롭게 넣을 수 있지먼 지켜야할 규칙과 권장 사항*-------------------
			반드시 프로미스 반환:
				then 메서드 내에서 다른 비동기 작업을 수행하려면 반드시 프로미스를 반환해야 합니다. 그렇지 않으면 체인이 깨질 수 있습니다.
	
			에러 처리:
				에러 처리를 위해 .catch 메서드를 사용하여 전체 프로미스 체인의 에러를 한 곳에서 처리하는 것이 좋습니다.
			
			명확한 코드 작성:
				콜백 함수 내에서 너무 많은 작업을 하지 않도록 주의합니다. 필요한 경우, 콜백 함수를 별도의 함수로 분리하여 가독성을 높입니다.
		
		*.then의 기본 사용법*----------------------------------------------------------------------	
			성공 콜백 함수(onFulfilled):
				프로미스가 성공적으로 해결되었을 때 호출됩니다.
				인수로 프로미스의 해결 값(fulfilled value)을 받습니다.
			
			실패 콜백 함수(onRejected):
				프로미스가 실패했을 때 호출됩니다.
				인수로 프로미스의 거부 이유(rejection reason)를 받습니다.	
				
			예시 ============================================
				promise
				  .then(
				    (value) => { // 성공 콜백 함수 
				      console.log('성공:', value);
				    },
				    (error) => { // 실패 콜백 함수 
				      console.error('실패:', error);
				    }
				  );
		*/
		
		var myHeaders = new Headers();
		myHeaders.append("x-access-token", "openuv-3sm9yu1rlxh0v3h5-io");
		myHeaders.append("Content-Type", "application/json");

		var requestOptions = {
		  method: 'GET',
		  headers: myHeaders,
		  redirect: 'follow'
		};

		fetch("https://api.openuv.io/api/v1/forecast?lat="+${lat}+"&lng="+${lng}+"&alt=100&dt=2024-06-17", requestOptions)
		  .then(response => {
				  if (!response.ok) { // ==  xhr.readyState && xhr.status
				      throw new Error('네트워크 응답에 문제가 있습니다.');
				    }
				  return response.text();
		  		}) // 응답 객체를 받아(response) 그 내용을 텍스트로 변환
		  .then(result => {location.href="uv_selectList.do?result=" + encodeURIComponent(result);})
		/*
		  .then(result => location.href="uv_selectList.do?result=" + encodeURIComponent(JSON.stringify(result));)

		  JSON.stringify(result) : JavaScript 객체를 JSON 문자열로 변환
		  encodeURIComponent() : 특수문자로 인한 에러 방지
		*/	
		  
/* 		  .then(result => console.log(result)) // 환된 텍스트 응답을(result) 인수로 받아 콘솔에 출력 */
		  .catch(error => console.log('error', error)); // 요청이 실패하면(error) 에러 메시지를 콘솔에 출력
		</script>
		
	</head>
	
	<body>
	
	</body>
</html>