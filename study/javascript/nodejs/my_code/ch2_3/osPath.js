const os = require('os');
const path = require('path');

os.uptime();
console.log("🚀 ~ file: os.js:4 ~ os.uptime():", os.uptime())
os.homedir();
console.log("🚀 ~ file: os.js:6 ~ os.homedir():", os.homedir())
os.platform();
console.log("🚀 ~ file: os.js:8 ~ os.platform():", os.platform())
os.hostname();
console.log("🚀 ~ file: os.js:10 ~ os.hostname():", os.hostname())
os.freemem();
console.log("🚀 ~ file: os.js:12 ~ os.freemem():", os.freemem())
os.totalmem();
console.log("🚀 ~ file: os.js:14 ~ os.totalmem():", os.totalmem())
os.cpus();
console.log("🚀 ~ file: os.js:16 ~ os.cpus():", os.cpus())


console.log(path.join(__dirname, '..', '.var.js'));
console.log(path.resolve(__dirname, '..', '/var.js'));