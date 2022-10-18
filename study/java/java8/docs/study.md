### 자바버젼

- LTS : LTS는 Long Term Support

- LTS 는 8 , 11, 17 이라서 상용은 이버젼을 이용하자.


### 강의자료
- [구글 Docs 링크](https://docs.google.com/document/d/1UxKM56um1mjGeayxmJmvALM5CDIJC17vx1-sDwoEbSs/edit) / [소스 코드](https://github.com/whiteship/java8)

## 함수형 인터페이스 / 람다표현식

- 추상메소드가 하나만 있어야 함수형 인터페이스가 됨. ( SAM : Single Abstract Method )
- 인터페이스에 스태틱 메소드가 들어올 수 있음. ( public 생략가능 )
- @FunctionalInterface 를 달아주면 컴파일시 에러를 확인 할수 있다.

- 익명함수를 람다표현식으로 바꿀수 있음

```java
    RunSomtime runSomtime = () -> System.out.println("Hello World!");

    RunSomtime runSomtime = () -> { 
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
    }
```

- 함수를 고차함수(First Class Object - 파라미터 또는 리턴 타입으로 쓴다.) 로 사용 가능하다.

- 순수함수 : 입력값이 동일한 경우 결과값은 항상 같아야된다.
    - 이게 보장되지 않는 경우는 사실 클로저 처럼 함수 밖의 값(상태값) 을 참조하기 때문이다.
    - 또는 외부에 있는 값을 변경하는 경우
    


### 자바기본 제공형 인터페이스

- java.lang.function 패키지에 있음.
- Function <T, R>  : T 를 받아서 R을 리턴함. apply 만 구현하면 됨..

- 함수를 인자로 받아서 조합할 수 있음.
    ```java
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        
        // compose  는 인자 먼저 그다음에 plus10
        Function<Integer, Integer> mul2plus10 = plus10.compose(multiply2);
        System.out.println(mul2plus10.apply(3)); // 16
        // andThen 은 순차적으로
        System.out.println(plus10.andThen(multiply2).apply(3)); // 26;

    ```

- Consumer 는 return 이 없음 ( accept(~~) ) / Supplier 는 인자가 없음 ( get() )
- Predicate 은 인자를 받아서 boolean 리턴 ( test() / and() / or()  등등.. )
- 입력값 출력값 타입이 같으면 UnaryOperator / BinaryOperator(두개의 입력값, 출력값의 타입이 같은경우 )

```java
        Supplier<Integer> get10 = () -> 10;
        BinaryOperator<Integer> getPlusTwo = (a,b) -> a + b;
```

- 변수 캡쳐
    - [로컬클래스 / 익명클래스] 와 람다표현식 에서
    - 변수가 사실상 final일때 참조가능한데..
```java

    private void run() {
        int baseNum = 10;

        IntConsumer printInt = (i) -> System.out.println(baseNum);

        printInt.accept(10);

        baseNum++; // 에러가 나서 사용이 불가능
    }
```



    -  쉐도잉 ( 더 작은 범위의 scpoe 에 정의된 변수가 큰 범위의 변수를 가리는)일 때는 다르다.
        - 로컬클래스 / 익명클래스는 쉐도잉이 되고
        - 람다는 쉐도잉이 안됨. 정확히는 람다를 감싸고 있는 메소드와 scpoe 이 같음


```java
    private void run() {
        int baseNum = 10;

        IntConsumer shading = new IntConsumer() {
            public void accept(int baseNum) {
                System.out.println(baseNum); // 가능
            };
        }
        // 여기서는 컴파일 에러가 난다. 같은.. 스콥이라서
        IntConsumer printInt = (baseNum) -> System.out.println(baseNum);

        shading.accept(10);
        printInt.accept(10);
    }

```


## 메소드 레퍼런스

- 다른 클래스에 정의된 메소드를 참고하는건데 사실 System.out.printIn 도 일종의..
    - 스태틱메소드 -> 타입::스태틱 메소드
    - 인스턴스메소드-> 타입(임의객체) 또는 특정객체 ::인스턴스 메소드
```java
    // 타입형으로 적을때
        String[] testStr = {"hello", "world", "is", "test"};

        Arrays.sort(testStr, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(testStr)); // [hello, is, test, world]

```


    - 생성자 -> 타입::new
    - 생성자의 경우 Supplier 와 조합하면 매우 좋게... 사용할수 있다.
    - 생성자의 인자가 없으면 Supplier , 있으면 Function<T,R> 을 활용하면 됨.

    - 이런게 좋은게 뭐냐면 함수를 실행시켜야지 그때 일이 벌어진다. (Lazy 하게 사용가능함.)


## 인터페이스의 변화점

### 기본메소드(default Method)

- 기존 인터페이스에 새로운 기능을 추가하려고 하면 그거를 상속하는 애들은 꼭 그걸 구현해야됬는데
- 디폴트 키워드를 붙이면 그냥 기능을 추가 할수 있음.

- 하지만 모든 곳에서 동일하게 동작하는지 모름.
- @implSpec 을 활용하여 문서화 시켜야되고 아니면 Override 한다.

- Object의 메소드인 equals, hashcode 등은 Override 불가 ( interface 에선)

- 내가 수정할수 있는 인터페이스에서만 사용가능하며,  다른 인터페이스에서 추상화(하지만 구현체는 재정의 또 해야됨) 시킬수 있음.

- 만약 다중의 인터페이스를 상속할 때 같은 이름의 Default Method 일 경우  중복되는 메소드 중
  무엇을 써야되는지 애매하기 때문에 컴파일 에러가 난다.
  해결하기 위해서는 직접 오버라이딩 하여 해결하자.

- Interface에서 이제는 Static 메소드도 제공가능하다. ( 유틸 성격일때 활용해보자 )

### 기본 API에서 유용한 Default Method

- Interable
```java
        List<String> names = new ArrayList();
        names.add("hello");
        names.add("test");
        names.add("todo");
        names.add("world");

        names.forEach( System.out::println );
        System.out.println("========================");

        // 애는 parallels 하게 사용하기 좋음.
        Spliterator<String> splitor = names.spliterator();
        Spliterator<String> splitor2 = splitor.trySplit();

        while(splitor.tryAdvance(System.out::println));
        System.out.println("========================");

        while(splitor2.tryAdvance(System.out::println));
        
        // 결과
        // hello
        // test
        // todo
        // world
        // ========================
        // todo
        // world
        // ========================
        // hello
        // test
```


- Collection
    - stream() / parallelStream() / removeif(Predicate) / splitorator() 등등은 확인해보자.

- Comparator 
    - 정렬이랑 많이 쓰이는데
    ```java
        Comparator<? super String> comparator = String::compareToIgnoreCase;
        names.sort(comparator.reversed());
    ```
    - thenComparing() / nullFirst() 등등이 있으니 확인해보자.


- 예전에는 하나의 인터페이스에 3개의 메소드가 있었을때, 추상클래스를 만들어서 빈 메소드로 대체하고 그다음 구현하는 클래스는 추상클래스를 extends 필요한거만 만들었다.
- 그런데 이제는 Default Method 를 인터페이스에서 정의하고 클래스에서 Implemnts 를 함..
    - ex) WebMvcConfigurer 에 이제 default 가 나와서 WebMvcConfigurerAdapter 가 의미 없어짐

## *! 스트림 API !*

- 데이터를 담는 저장소는 아니다.
- Functional in nature / 소스를 변경하지 않는다....
- 그래서 무제한이지만 Short Circult 을 통해서 제한을 할수 있다.
- 중계 오퍼레이션은 Lazy 하게 동작한다.
- stream을 이어가는 중계 오퍼레이터 / 끝마치는 터미널 오퍼레이터 으로 구분된다.

- 손쉽게 병렬처리도 가능하다.
    -  list.parallelStream().~~~
    - spliterator 를 이용하여 알아서 병렬처리한다...
    - 하지만 반드시 빠른건 아니다.

    ```java
        list.parallelStream().map( s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList()).forEach(System.out::println);
         
        //결과
        //cvbzxcvas main
        //abds ForkJoinPool.commonPool-worker-1
        //qweqweqwe ForkJoinPool.commonPool-worker-2
        //ABDS
        //CVBZXCVAS
        //QWEQWEQWE

    ```


- 중계 오퍼레이터
    - stream을 리턴함
    - 터미널 형 만나기전까지 동작하지 않고 정의만 된다.
    - stateless / stateful 로 구분되는데 외부 변수를 참조하면 stateful

    ```java
            List<String> list = new ArrayList<String>();

            list.add("abds");
            list.add("cvbzxcvas");
            list.add("qweqweqwe");


            list.stream().map(
                s -> {
                    System.out.println(s);
                    return s.toUpperCase();
                } 
            );// .collecat(Collector.toList());

            System.out.println("================================================================");
            list.forEach(System.out::println);

            // 중계형 오퍼레이터 는 터미널 만나기전까지 실행되지 않는다.
            // 결과
            // ================================================================
            // abds
            // cvbzxcvas
            // qweqweqwe


    ```


-터미널 오퍼레이션
    - stream 이 아닌 다른 타입으로 리턴한다.

### 스트림 사용예제

```java

        List<OnClass> java8Class = new ArrayList<>();
        java8Class.add(new OnClass(1,"stream",true));
        java8Class.add(new OnClass(2,"ramda",true));
        java8Class.add(new OnClass(3,"time",false));
        java8Class.add(new OnClass(4,"refactor",false));

        List<OnClass> springClass = new ArrayList<>();
        springClass.add(new OnClass(5,"spring boot",true));
        springClass.add(new OnClass(6,"spring data jpa",false));
        springClass.add(new OnClass(7,"spring mvc",false));
        springClass.add(new OnClass(8,"spring core",false));

        List<List<OnClass>> toListenClass = new ArrayList<>();
        toListenClass.add(java8Class);
        toListenClass.add(springClass);


        System.out.println("closed 되지 않는 수업은 ");
        springClass.stream()
            .filter(Predicate.not( OnClass::isClosed))
            .forEach( c -> System.out.println(c.getId()) );

                
        System.out.println("FlatMap - 두 수업목록에 들어가 있는 수업아이디 출력");
        toListenClass.stream()
            .flatMap( Collection::stream )
            .forEach( c -> System.out.println(c.getId()) );

        System.out.println("10부터 1씩 증가하는 무제한 스트림중 앞 10개 빼고 최대 10개까지 수집");
        Stream<Integer> justWord = Stream.iterate(10, i -> i+1 );  // 이렇게 만 하면 중계형이라서 일어나지 않는다.
        justWord
            .skip(10)
            .limit(10)
            .forEach(System.out::println);


```

- 


## Optional \<T\>


