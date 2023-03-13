const odd = '홀수'
const even = '짝수'



module.exports = {
	odd,
	even,
}

// 이렇게도 가능한데.. 차이는
// 		exports.odd;
// 		exports.even;

// 기본적으로 module.exports === exports === {} 인데
// module.exports 에 직접대입하면 exports 와 참조관계가 끉어져서.. 애매해짐.


