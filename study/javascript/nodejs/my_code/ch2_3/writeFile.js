const fs = require('fs').promises;

fs.writeFile('./write.md', '글입력')
	.then(()=>{
		return fs.readFile('./write.md')
	})
	.then( (data) =>{
		console.log(data.toString())
	})
	.catch((err) => {
		console.log(err);
	})
;