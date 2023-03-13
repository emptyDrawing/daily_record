const fs = require('fs');
const zlib = require('zlib');

// 16바이트씩 나눠서
const readStream = fs.createReadStream('./read.md', {highWaterMark: 16});
const zlibStream = zlib.createGzip();
const writeStream = fs.createWriteStream('./writeStream.md.gz');

readStream.pipe(zlibStream).pipe(writeStream);