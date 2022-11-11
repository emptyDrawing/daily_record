### 책추천
- [SICP](http://www.yes24.com/Product/Goods/23951885) : 컴퓨터 프로그램의 구조와 해석
- [프로그램 디자인 어떻게 할 것인가](http://www.yes24.com/Product/Goods/4853907)




### 1차비교
```javascript

<script> const log = console.log; </script>

## 명령형코드
<script type="module">
// 리스트에서 홀수를 length 만큼 뽑아서 제곱한 후 모두 더하기
function f (list, length) {
   let i = 0;
   let acc = 0;
   
   for ( const a of list ) {
       if( a % 2 ) {
         acc += a * a ;
       }
       if( ++i == length) break;
   }
   
   log(acc);
}


function main() {
   // f([1, 2, 3, 4, 5], 1);
   // f([1, 2, 3, 4, 5], 2);
   // f([1, 2, 3, 4, 5], 3);
}

main();
    
</script>

## 함수형 코드

<script>

    // 제너레이터 *
function *filter(f, iter){
    for ( const a of iter ) {
       if( f(a) ) yield a;
    }
}

function *map(f, iter){
    for ( const a of iter ) {
        yield f(a);
    }
}

// 명령은 선언한다.
function take(length, iter) {
    let res = [];
    for ( const a of iter) {
        res.push(a);
        if(res.length == length) return res;
    }
}

function reduce(f, acc, iter) {
    for ( const a of iter) {
        acc = f(acc,  a);
    }
    return acc;
}

const add = (a, b) => a + b; 

// 읽는 법은 맨 오른쪽 부터 왼쪽으로
const f = (list, length) =>
    reduce( add, 0, 
        take( length, 
            map( a => a * a , 
                filter( a => a % 2, list ))));
function main() {
    log( f([1, 2, 3, 4, 5], 1) );
    log( f([1, 2, 3, 4, 5], 2) );
    log( f([1, 2, 3, 4, 5], 3) );
}

main();

</script>

```


### go 함수

![](assets/2022-11-11-15-30-38.png)


### range
```javascript
L.range = function *(stop){
    let i = -1;
    while( ++i < stop ) yield i;
}

```
![](assets/2022-11-11-15-47-14.png)