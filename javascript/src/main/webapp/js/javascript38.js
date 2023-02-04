onload = () => {
	let a = document.querySelectorAll('a');
	let img = document.querySelectorAll('img')[0];
	let count = 3;
	
	a[0].onclick = () => {
		let imgAlt = img.getAttribute('alt');
		count = --count < 1 ? 5 : count;
		img.setAttribute('src', `./images/img0${count}.jpg`);
		img.setAttribute('alt', 'img0' + count);
	}

	a[1].onclick = () => {
		let imgAlt = img.getAttribute('alt');
		count = ++count > 5 ? 1 : count;
		img.setAttribute('src', `./images/img0${count}.jpg`);
		img.setAttribute('alt', 'img0' + count);
	}
};






















