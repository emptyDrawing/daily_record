const fs = require('fs');

fs.readFile('./read.md', (err, data) => {
	if(err) {
		throw err;
	}

	console.log('1번');
	console.log(data.toString());
});

fs.readFile('./read.md', (err, data) => {
	if(err) {
		throw err;
	}

	console.log('2번');
	console.log(data.toString());
});

fs.readFile('./read.md', (err, data) => {
	if(err) {
		throw err;
	}

	console.log('3번');
	console.log(data.toString());
});


fs.readFile('./read.md', (err, data) => {
	if(err) {
		throw err;
	}

	console.log('4번');
	console.log(data.toString());
});