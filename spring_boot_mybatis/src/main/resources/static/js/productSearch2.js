/**
 *  상품 검색을 위한 js, ajax post 방식 요청
 *  데이터(form 태그 내 여러개의 값을 쿼리 스트링 방식으로 한번에 추출하고 변환하는 기능 serialize() 사용)
 *  type=prdName&keyword=모니터 와 같은 형식으로 파라미터 구성
 */
 
 $(document).ready(function(){
 
 	//검색 폼에 submit 이벤트가 발생하면 처리 함수
 	$('#prdSearchFrm2').on('submit', function(){
 
 		event.preventDefault();
 		
 		//폼에 입력된 모든 데이터 쿼리스트링변환
 		//이 시점 this는 prdSearchFrm1이고 form태그 내 모든 태그객체의 value를 쿼리스트링으로 변환, 태그내 name 속성 필수
 		//연결된 productSearchForm1.jsp를 예시로
 		//한개의 form태그 내 name 속성이 있는 2개의 태그에 대해서 쿼리 스트링 방식으로 데이터 집합을 생성해 줌
 		//type=prdName&keyword=모니터 과 같이 구성해주는 함수
 		let formData = $(this).serialize(); //서버측으로 전송할 예정
 		//console.log(formData);
 		//필수 입력값 입력 확인
 		let keyword = $("#keyword").val();
 		let type = $("#type").val();
 		
 		if(keyword == "" || type == ""){
 			alert("검색조건과 검색어 입력하세요");
 		} else {
 		
 			$.ajax({//서버와 비동기통신
 				type:"post",
 				url:"/product/productSearch2",
 				data:formData, //formData는 key=value 형식으로 2개 data가 저장됨, 컨트롤러는 HashMap으로 받는게 효율적인
 				success:function(result){//컨트롤러가 View페이지 반환하므로 html 페이지가 result에 전달됨
 					//전달된 html 페이지를 searchResultBox id의 div태그에 삽입
 					$('#searchResultBox').html(result); 												
 				},//success 긑
 				error:function(){
 					alert("실패");
 				}
 			});//ajax 끝
 		
 		}//else 끝
 	});//on끝
 	
 });//ready끝