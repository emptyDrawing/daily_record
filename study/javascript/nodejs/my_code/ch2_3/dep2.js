const b = require('./dep1'); 

console.log('dep2', b)

module.exports = {
	hello : `I'm dep2`
}