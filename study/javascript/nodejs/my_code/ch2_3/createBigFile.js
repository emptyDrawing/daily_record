const fs = require('fs');

const file = fs.createWriteStream('./big.txt');

for (let i = 0; i <= 2_800_000; i++) {
	file.write("안녕하세요 엄청큰 파일 만드는 중입니다. \n");
}
file.end()