const a = true;
if (a) {
	// 보면 알겠지만 TOP LEVEL await이다.
	const m1 = await import('./func.mjs');
	console.log(m1);
	const m2 = await import('./var.mjs');
	console.log(m2);
}
