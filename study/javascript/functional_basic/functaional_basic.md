


### 목표

- 부수효과를 줄인다 => 순수함수를 만든다.
- 조합성으롤 강조한다.
- 모듈성을 늘려 생산선을 확보한다.


- 순수함수는 평가시점이 중요하지 않고 동일한 인자를 넣으면 동일한 값을 리턴한다.
- 일급함수는 함수를 값으로, 원할때 평가할 수 있고 인자로 취급할 수 있는 것

- 결국 함수형 프로그래밍은 동사들도 문제를 해결하는 것.


### 다형성
```javascript
console.log(
  [1, 2, 3, 4].map(function(val) {
    return val * 2;
  })
);

console.log(
  [1, 2, 3, 4].filter(function(val) {
    return val % 2;
  })
);
```
- 위 코드는 Array의 메소드 임.

```javascript
function _filter(list, predicator ) {
  var new_list = [];

  _each(list, function(val) {
    if (predicator(val)) new_list.push(val);
  })
  return new_list;
}

function _map(list, mapper) {
  var new_list = [];

  _each(list, function(val) {
    new_list.push(mapper(val));
  })

  return new_list;
}

function _each(list, iter) {
  for (var i = 0; i < list.length; i++) {
    iter(list[i]);
  }
  return list;
}
```
- 위는 함수.

- 둘의 차이점은 결과적으로 Array 가 아닌 arraylike 객체를 많이 사용하기도 해서 외부의 데이터에 대한 다형성을 확보할수 있고
- 내부의 다형성은 `mapper`, `predicator`, `iter` 같은 내부함수가 담당한다고 볼 수 있음.
- 그리고 내부함수도 함수를 실행하려는, 쓰는 시점에 개발자가 정의할 수 있음.


### 커링

- 함수에 필요한 인자를 모아두었다가 다 차면 본래 함수를 실행시킴.

