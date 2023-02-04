const purpleCuteSlime = {
	name: '슬라임',
	arrtibute: 'cute',
	color: 'purple',
};

//	rest: spread와는 반대로 퍼져있는 것을 모으는 역할을 한다. 연산자는 spread와 같이 '...'을 사용한다.
//	지정된 속성을 제외한 나머지가 rest에 저장되므로 rest는 반드시 맨 마지막에 딱 1번만 써야한다.
//	객체에 rest를 사용할 때는 뽑아닐 변수 이름이 객체를 구성하는 key와 같아야 한다.
const {color, ...cuteSlime} = purpleCuteSlime;
console.log(color);
console.log(cuteSlime);

const {arrtibute, ...slime} = cuteSlime;
console.log(arrtibute);
console.log(slime);
console.log('=================================');

const numbers = [0, 1, 2, 3, 4, 5, 6];

//	배열에 사용할 때도 rest 맨 마지막에 딱 1번만 써야한다.
//	배열에 rest를 사용할 때는 변수 이름은 뭘 적어도 상관없고 배열의 인덱스 순서대로 뽑혀나온다.
const [a, b, ...c] = numbers;
console.log(a);
console.log(b);
console.log(c);
console.log('=================================');

//	spread와 rest를 사용한 함수
function sum(...rest) {
//	console.log(rest);

//	return rest.reduce(function (acc, curr) {
//		return acc + curr;
//	}, 0);

	return rest.reduce((acc, curr) => acc + curr, 0);
}

const spread = [1, 2, 3, 4, 5, 6, 7];
console.log(sum(...spread));
console.log('=================================');

function maxValue(...numbers) {
//	console.log(numbers);
	
//	return numbers.reduce(function (acc, curr) {
//		return curr > acc ? curr : acc;
//	}, numbers[0]);
	
	return numbers.reduce((acc, curr) => curr > acc ? curr : acc, numbers[0]);
}

const result = maxValue(1, 2, 3, 4, 10, 5, 6, 7);
console.log(result);









