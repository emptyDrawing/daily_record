//console.log('this->', this);
console.log('this === module.exports ', this === module.exports);

function a() {
	// console.log('func a()', this);
	console.log('func a()-> this === global', this  === global) ;
}

a();