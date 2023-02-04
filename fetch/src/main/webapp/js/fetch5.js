function fetchAjax(pageName) {
	fetch(pageName)
		.then(function(response) {
			if (response.status == 200) {
				response.text().then(function(text) {
					document.querySelectorAll('div')[0].innerHTML = text;
				});
			} else {
				document.querySelectorAll('div')[0].innerHTML = '요청 실패';
			}
		});
}

$(() => {
	if (location.hash) {
		fetchAjax(location.hash.substring(2));
	} else {
		fetchAjax('summary');
	}
	
	// list 파일의 내용을 읽어서 <ol id="list"> ~ </ol> 사이에 넣어주는 fetch
	/*
	fetch('list')
		.then(function (res) {
			// console.log(res.status);
			res.text().then(function (data) {
				// document.querySelector('#list').innerHTML = data;
				$('#list').html(data);
			});
		});
	*/
	
	// then() 내부에 then() 나오는 기법을 nested라 한다.
	// fetch('list').then(res => res.text().then(data => $('#list').html(data)));
	
	// then()이 리턴한 결과를 외부에서 다른 then()이 받는 기법을 chaining이라 한다.
	fetch('list')
		.then(res => res.text())
		.then(data => $('#list').html(data));
	
	// list2 파일의 내용을 읽어서 <ol id="list2"> ~ </ol> 사이에 넣어주는 fetch
	/*
	fetch('list2')
		.then(function (res) {
			res.text().then(function (data) {
				// console.log(data);
				// split() 함수를 사용해서 ','를 경계로 문자열을 분리해서 배열로 리턴한다.
				let obj = data.split(',');
				// console.log(obj);
				
				// <ol> 태그 내부에 출력할 태그 목록을 만든다.
				let items = '';
				for (let i=1; i<=obj.length; i++) {
					// <li><a href="#!1" onclick="fetchAjax('1')">1절 가사</a></li> 모양을 만든다.
					let item = '<li><a href="#!' + i + '" onclick="fetchAjax(\'' + i + '\')">' +
						obj[i - 1] + '</a></li>';
					items += item;
				}
				$('#list2').html(items);
			});
		});
	*/	
	
	/*
	fetch('list2')
		.then(res => {
			res.text().then(data => {
				let obj = data.split(',');
				let items = '';
				for (let i=1; i<=obj.length; i++) {
					let item = '<li><a href="#!' + i + '" onclick="fetchAjax(\'' + i + '\')">' +
						obj[i - 1] + '</a></li>';
					items += item;
				}
				$('#list2').html(items);
			});
		});
	*/
	
	fetch('list2')
		.then(res => res.text())
		.then(data => {
			let obj = data.split(',');
			let items = '';
			for (let i=1; i<=obj.length; i++) {
				let item = '<li><a href="#!' + i + '" onclick="fetchAjax(\'' + i + '\')">' +
					obj[i - 1] + '</a></li>';
				items += item;
			}
			$('#list2').html(items);
		});
});



















