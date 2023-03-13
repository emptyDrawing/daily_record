const {spawn} = require('child_process');

const process = spawn('python3', ['test.py']);

process.stdout.on('data', data => {
	console.log(data.toString('utf8'));
})

process.stderr.on('data', data => {
	console.log(data.toString('utf8'));
})
