$(() => {
//	class 속성이 error로 지정된 에러 메시지를 숨긴다.
	$('.error').hide();
	
//	id 속성이 single로 지정된 form에서 submit 버튼이 클릭되면 실행할 이벤트를 지정한다.
//	submit(html이나 javascript는 onsubmit) 이벤트는 input 태그나 button 태그의 type 속성이
//	submit으로 지정된 버튼이 클릭되면 발생되는 이벤트이다.
//	submit 이벤트가 실행되면 서버로 전송하기 전에 폼의 유효성을 검사하고 폼에 입력된 데이터가
//	이상이 없으면 true(기본값)를 리턴시키고 이상이 있으면 에러 메시지를 출력한 후 false를 리턴시킨다.
//	submit 이벤트의 리턴값이 true면 action 페이지로 form의 데이터를 가지고 이동하고 false면 현재
//	페이지를 그대로 표시한다.
	$('#single').submit(() => {
		// alert('submit 버튼이 클릭됨');
		let userID = $('#infoBox').val().trim();
		// console.log(userID);
		if (userID == null || userID == '') {
			$('#infoBox').val('');
			$('#infoBox').focus();
			$('.error').show();
			return false; // false가 리턴되면 현재 페이지에 그대로 머물러있는다.
		}
		return true; // true가 리턴되면 action 페이지로 이동한다.
	});
	
//	전체선택 체크 박스가 클릭되면 나머지 모든 체크 박스를 선택 또는 해제한다.
	$('input:checkbox[name=all]').click(() => {
		// console.log('전체선택 체크 박스 클릭');
		// prop('속성이름'): 인수로 지정된 속성의 프로퍼티를 얻어온다.
		let checked = $('input:checkbox[name=all]').prop('checked');
		// console.log(checked);
		// name 속성이 chk인 체크 박스의 프로퍼티를 name 속성이 all인 체크 박스의 프로퍼티로 바꾼다.
		// prop('속성이름', 프로퍼티): 인수로 지정된 속성의 프로퍼티를 변경한다.
		// 1.
		// $('input:checkbox[name=chk]').each(index => {
		//     $('input:checkbox[name=chk]').eq(index).prop('checked', checked);
		// });
		// 2.
		// $('input:checkbox[name=chk]').each(function (index) {
		//     $(this).prop('checked', checked);
		// });
		// 3.
		$('input:checkbox[name=chk]').prop('checked', checked);
	});
	
//	name 속성이 chk인 체크 박스가 클릭되면 name 속성이 chk인 모든 체크 박스가 체크되었나 검사해서
//	모두 체크되었으면 전체선택 체크 박스를 체크하고 그렇치 않으면 체크안되게 한다.
	$('input:checkbox[name=chk]').click(() => {
		// console.log('name 속성이 chk인 체크 박스 클릭');
		// name 속성이 chk인 요소의 개수와 name 속성값이 chk인 요소 중에서 체크된 요소의 개수를 비교한다.
		// console.log('name 속성이 chk인 요소의 개수: ' + $('input:checkbox[name=chk]').length);
		// console.log('name 속성이 chk인 요소 중에서 체크된 요소의 개수: ' + 
		//	$('input:checkbox[name=chk]:checked').length);
		if ($('input:checkbox[name=chk]').length == $('input:checkbox[name=chk]:checked').length) {
			// console.log('모두 체크됨');
			$('input:checkbox[name=all]').prop('checked', true);
		} else {
			// console.log('모두 체크되지 않음');
			$('input:checkbox[name=all]').prop('checked', false);
		}
	});
	
//	체크 박스를 선택하고 확인 버튼을 클릭하면 체크 여부를 확인해서 그 결과를 id 속성이 result로
//	지정된 div 태그 내부에 출력한다.
	$('#confirm').click(() => {
		// console.log('획인 버튼 click 이벤트 실행');
		// 선택한 과일 가격이 표시될 div 태그 내부를 내용을 지운다.
		// $('#result').html('');
		// $('#result').text('');
		// empty(): 선택된 요소의 내부 내용을 지운다.
		$('#result').empty();
		
		// name 속성에 chk가 지정된 체크 박스의 체크된 항목의 개수를 얻어온다.
		let count = $('input:checkbox[name=chk]:checked').length;
		// console.log(count);
		if (count == 0) {
			// alert('과일을 1개 이상 선택하세요!!');
			$('#result').html('<b style="color: red;">과일을 1개 이상 선택하세요!!</b>');
		} else {
			$('input:checkbox[name=chk]:checked').each(index => {
				let chk = $('input:checkbox[name=chk]:checked').eq(index);
				console.log(chk); // <input type="checkbox" ~
				console.log(chk.val()); // 과일 가격
				console.log(chk.next()); // <b>
				// console.log(chk.next().html()); // 과일 이름
				console.log(chk.next().text()); // 과일 이름
				// append(): html() 메소드나 text() 메소드는 기존의 내용을 지우고 인수로 지정한 내용을
				// 넣어주지만 append() 메소드는 기존에 내용에 인수로 지정한 내용을 추가한다.
				$('#result').append('과일 이름: ' + chk.next().text() + ', 과일 가격: ' +
					chk.val() + '<br/>');
			});
		}
	});
	
//	형제(sibling) 요소 탐색
//	.siblings() : 선택된 요소의 형제 요소 중에서 지정한 선택자에 해당되는 모든 요소를 선택한다.
//	.next() : 선택된 요소 바로 다음에 위치한 형제 요소 1개를 선택한다.
//	.nextAll() : 선택된 요소 바로 다음에 위치한 형제 요소를 모두 선택한다.
//	.nextUntil() : 선택된 요소 바로 다음에 위치한 형제 요소 중에서 지정한 선택자에 해당되는 요소 
//	               바로 이전까지의 요소를 모두 선택한다.
//	.prev() : 선택된 요소 바로 이전에 위치한 형제 요소 1개를 선택한다.
//	.prevAll() : 선택된 요소 바로 이전에 위치한 형제 요소를 모두 선택한다.
//	.prevUntil() : 선택된 요소 바로 이전에 위치한 형제 요소 중에서 지정한 선택자에 해당되는 요소 
//	               바로 다음까지의 요소를 모두 선택한다.		
	
});






















