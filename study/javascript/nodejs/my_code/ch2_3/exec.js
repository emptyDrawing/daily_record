const {exec} = require('child_process');

let process = exec('dir');

process.stdout.on('data', data => {
	console.log(data.toString('utf8'));
})

process.stderr.on('data', data => {
	console.log(data.toString('utf8'));
})
