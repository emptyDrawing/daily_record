const a = require('./dep2'); 

console.log('dep1',a);

module.exports = {
	hello : `I'm dep1`
}