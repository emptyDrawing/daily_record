### 자바버젼 짤막상식

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


## Optional \<T\>

- null 포인트문제..

```java
        // 이상태로 바로 시작하면 null 포인트...
        OnClass testProgress = springClass.get(0);
        System.out.println(testProgress.getProgress().getStudyDuration());
```

- 그래서 이걸 극복하는건 null 체크를 하던가 getter 에서 에러(IllegalStateException) 를 던지거나..
- 이걸 명시적으로 할수 있는게 Optional ...

```java
    // return 할때만 이렇게 하는게 수정범위가 가장 적음.
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }
    // 파라미터로 쓴경우 별도 채크를 해야됨.
    public void setProgress(Optional<Progress> progress) {
        progress.ifPresent( p -> this.progress = p );
    }
    // 그런데 객체에 Optional을 null 을 넣을수 있음..
```

- of / ofNullable : of 는 무조건 null 이 아닐때 쓰는데 null이면 nullPoint...
- Collectionm, Map, Stream, Array 들은 Optional으로 감싸지 말것

- 사용예 
```java       
        Optional<OnClass> checkClass = springClass.stream()
            .filter( c -> c.getTitle().startsWith("jpa"))
            .findFirst();
        
        System.out.println(checkClass.isPresent());

```

- Optional 에서 get() 으로 값을 가져올수 있지만 null 일수 있어서..
- ifPresent() 를 써서 그안에서 할일을 쓰거나
- orElse() ( 리턴은 Optional<T> 에서 T 형) : 이미 상수로 만들어진 경우에 쓰면 좋고 
- orElseGet( Supplier  ) : Lazy하게 쓸 수 있어서 이미 있으면 해당코드가 안돌아서 좋음
- orElseThrow()
- filter()  / map() : 있다는 가정하에 돌아가는데.. 리턴형이 Optional 
    - 그런데 map 으로 꺼낸 애가 또 Optional 이면? 복잡해짐.
    - 그럴때는 flatMap()



## Date / Time

- 예전 타입은 뭔가 직관적이지 않음 ( Mutable 하여 값도 변경가능하다.) [참고링크](https://codeblog.jonskeet.uk/2017/04/23/all-about-java-util-date/)
```java       
        Date oldStyleDate = new Date(); // timeStamp...
        long time = oldStyleDate.getTime();
        
        Calendar osCal = new GregorianCalendar();
        SimpleDateFormat osSdf = new SimpleDateFormat();
```
- 그래서 multiThread 환경에서 유용하지 않음.
- 그런데 또 typeSafity 도 없어서.. ( int 로 받거나 해서 ) 애매하다.
- 그래서 [JodaTime](https://www.joda.org/joda-time/) 을 쓰기도 했는데 이게 표준화 되어 들어옴.

- Clear / Flunet(계속 이어나갈수 있어서 유려해졌다.) / Immutable / Extendsible 해야된다는 원칙으로 만들어짐.

- 변경된 API는 타임스탬프의 경우 Instant / 특정날짜 LocalDate / LocalTime / LocalDateTime 등이 있음.

- 사용 예 ( 기계시간 )
```java
        // EPOCH 시간 / 기계시간
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT 기준임


        ZoneId systemDefault = ZoneId.systemDefault();
        System.out.println(systemDefault);
        System.out.println(instant.atZone(systemDefault)); // 존아이디로 가능함.

        // 2022-10-18T04:12:33.208219291Z
        // Asia/Seoul
        // 2022-10-18T13:12:33.208219291+09:00[Asia/Seoul]
```

- 사용 예 ( 사람시간 )
```java
        // 사람시간
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime birthDay = LocalDateTime.of(1989, Month.JUNE, 17, 5, 10, 0);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    
        System.out.println(nowInKorea);
        System.out.println(birthDay);
        
        // 2022-10-18T13:15:59.818138946
        // 2022-10-18T13:15:59.819253788+09:00[Asia/Seoul]
        // 1989-06-17T05:10
```

- 기간 ( Period - 날짜 / Duration - 시간 )
```java

        LocalDate today = LocalDate.now();
        LocalDate nextYear = LocalDate.of(2023, Month.FEBRUARY, 1);

        Period period= Period.between(today, nextYear);
        System.out.printf("%d %d %d \n",period.getYears(),period.getMonths(),period.getDays());

        Period until = today.until(nextYear);
        System.out.println(until.get(ChronoUnit.DAYS));

        System.out.println(ChronoUnit.DAYS.between(today, nextYear));

        // 0 3 14 
        // 14
        // 106
```

- 포맷팅
```java
        LocalDateTime now = LocalDateTime.now();
        final DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy)); // 10/18/2022
        System.out.println(LocalDate.parse("10/10/2004", MMddyyyy)); //2004-10-10
```

- 예전 API 와도 호환은 됨
```java
        Date date = new Date();
        Instant instant = date.toInstant();
        Date newDate = Date.from(instant);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone legecyId = TimeZone.getTimeZone(zoneId);
```

## CompletableFuture

- 동시성 문제...자바에서 지원하는 컨커런트 프로그래밍
    - 멀티프로세싱 (ProcessBuilder)
    - 멀티쓰레드 ( Thread / Runnable )

- Thread 는 순서가 애매하다.
```java
    public static void main(String[] args) {

        final MyThread myThread = new MyThread();
        myThread.start();


        System.out.println("Hello :" +Thread.currentThread().getName());

    }

    static class MyThread extends Thread {
        public void run() {
            System.out.println("Thread : "+ Thread.currentThread().getName());
        }
    };

    // 이게 정상같지만
    // Thread : Thread-0
    // Hello :main

    // 이렇게도 나온다.
    // Hello :main
    // Thread : Thread-0
```

```java
// 람다

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( () -> {

                while(true){
                    System.out.println("lamda-awalk : "+ Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("exit");
                        return ; //이게 실제로 끝나는거, Interrupted 되었다고 끝나진 않음.
                    }
                }

            }
        );
        thread.start();

        System.out.println("Hello :" +Thread.currentThread().getName());
        Thread.sleep(3000);
        thread.interrupt();
    }

    // 결과
    // lamda-awalk : Thread-0
    // Hello :main
    // lamda-awalk : Thread-0
    // lamda-awalk : Thread-0
    // exit
```

```java
// join - 연결한 Thread를 기다림


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( () -> {
            System.out.println("lamda-awalk : "+ Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }    
        });
        thread.start();

        System.out.println("Hello :" +Thread.currentThread().getName());
        thread.join(); // 사실 여기에도 interrupt 관리도 해주어야됨.
        System.out.println(thread + " is stop");

    }

    //결과
    // Hello :main
    // lamda-awalk : Thread-0
    // Thread[Thread-0,5,] is stop // 3초뒤 결과
```

- 이래서 수십수백개 Thread 를 관리하기 어려움.


### Executors

- 쓰레드를 만드는 과정을 고수준의 API에 위임 ( 만들기 / 관리 / 작업처리 및 실행)
```java
    public static void main(String[] args) {

        ExecutorService exSvc = Executors.newFixedThreadPool(2);
        exSvc.submit(getRunnable("hello"));
        exSvc.submit(getRunnable("now "));
        exSvc.submit(getRunnable("abc "));
        exSvc.submit(getRunnable("1235456 "));
        exSvc.submit(getRunnable("1231231231231"));
        // 프로세스가 살아있어서 shutdown 을 해야함.
        exSvc.shutdown(); // graceful shutdown - 다 끝내고 죽어라

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " : " + Thread.currentThread().getName() );
    }

    // 결과
    // hello : pool-1-thread-1
    // now  : pool-1-thread-2
    // abc  : pool-1-thread-1
    // 1235456  : pool-1-thread-2
    // 1231231231231 : pool-1-thread-1


    // 스케줄
    ScheduledExecutorService exSvc = Executors.newScheduledThreadPool(2);
    exSvc.scheduleAtFixedRate(getRunnable("now "),1,2,TimeUnit.SECONDS);

    Thread.sleep(10000); // 바로 죽으면 스케줄을 못보니 sleep
    exSvc.shutdown(); 
```

- Runnable 은 return 이 void 라서 결과를 가져올려면 Callable / Future 
```java
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService exSvc = Executors.newSingleThreadExecutor();
        Future<String> future = exSvc.submit(getCallable("now"));

        System.out.println("start");
        // blocking...
        System.out.println("future status : " + future.isDone()); 
        System.out.println(future.get());
        System.out.println("future status : " + future.isDone()); 

        System.out.println("end!");
        exSvc.shutdown();
    }



    private static Callable<String> getCallable(String message) {  
        return (() -> {
            Thread.sleep(2000);
            return message + " : " + Thread.currentThread().getName();
        });
    }

    // 결과
    // start
    // future status : false
    // now : pool-1-thread-1
    // future status : true
    // end!
```
- 그런데 한번 cancel() 시키면 값을 못가져옴.

- 한번에 여러개 보낼수도 있음
```java
        
        ExecutorService exSvc = Executors.newSingleThreadExecutor();
        // 제일 길게 걸리는거 까지 기다린다.
        // 안그럴거면 invokeAny()
        List<Future<String>> futures = exSvc.invokeAll(Arrays.asList(
            getCallable("1s",1000)
            ,getCallable("4s",1000)
            ,getCallable("2s",1000)
        ));


        System.out.println("start");
        
        futures.forEach(
            (f) -> {
                try {
                    System.out.println( f.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        );

        System.out.println("end!");

        exSvc.shutdown();
    }



    private static Callable<String> getCallable(String message, int time) {  
        return (() -> {
            Thread.sleep(time);
            return message + " : " + Thread.currentThread().getName();
        });
    }
```

- CompletableFutre 심화
    - 이제는 제대로 비동기적으로 멀티쓰레드 프로세싱을 할 수 있음.
    - Future로는 하기 어렵던 작업들을 쉽게 할수 있음.
    - 외부에서 완료 / 취소 /  get()에 타임아웃을 설정.
    - 여러 Future를 조합 [ Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기 ] 등을 할수 있음
    - 예외 처리용 API를 제공

```java
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 별도의 Executor 를 만들지 않아도 됨.
        CompletableFuture<String> completeFuture = new CompletableFuture<>();
         
        completeFuture.complete("ok! "+ Thread.currentThread().getName());

        System.out.println(completeFuture.get());

        // 좀더 줄여쓰면
        // return 이 없는 경우 
        CompletableFuture.runAsync( () -> System.out.println("simple run " + Thread.currentThread().getName()) );
        // return  있는 경우
        CompletableFuture<String> simpleFutrue = CompletableFuture.supplyAsync( () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "simple run ok!" + Thread.currentThread().getName();
        });
        // 하지만 아직 blocking 하는 방식이다.
        System.out.println(simpleFutrue.get());

    }
```

```java
        CompletableFuture<String> simpleFutrue = CompletableFuture.supplyAsync( () -> {
            System.out.println("supplyAsync : "  + Thread.currentThread().getName());
            return "simple run ok!";
        }).thenApply( (s) -> {
            System.out.println("thenApply : " + Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(simpleFutrue.get());

        //결과
        // supplyAsync : ForkJoinPool.commonPool-worker-1
        // thenApply : main
        // SIMPLE RUN OK!

        // return 이 없을땐
        CompletableFuture<Void> simpleFutrue = CompletableFuture.supplyAsync( () -> {
            System.out.println("supplyAsync : "  + Thread.currentThread().getName());
            return "simple run ok!";
        }).thenAccept( (s) -> {
            System.out.println("thenApply : " + Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        }).thenRun( ()->{
            System.out.println("Runnable : " + Thread.currentThread().getName());
        });

        simpleFutrue.get();
```

- 그럼 왜 ThreadPool을 만들지 않고 동작하냐면, 자동적으로 ForkJoinPool 이 만들어지고, 여기서 Dequeue 에 작업이 쌓여서 Work stealing 을 쓴다고 한다.
      
- [더 읽어볼 자료1](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=jjoommnn&logNo=220240780407)
- [더 읽어볼 자료2](https://m.blog.naver.com/jjoommnn/220262427804)

- 하지만 직접 ThreadPool 부여도 가능함.
```java
    ExecutorService exSvc = Executors.newFixedThreadPool(4);
    // 두번째 인자 확인.
    CompletableFuture.supplyAsync( () -> { ~~~ }, exSvc).thenAccept( ~~~ );
```

- CompletableFuture 연결 / 조합하기
```java
// 연쇄적으로 되어야 되는 경우
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        CompletableFuture<String> hello = CompletableFuture.supplyAsync( () -> {
            System.out.println("hello : "  + Thread.currentThread().getName());
            return "hello";
        });

        CompletableFuture<String> composeFuture = hello.thenCompose( ExecutorsTest::getMessageWorld );
        System.out.println(composeFuture.get());
    }

    private static CompletableFuture<String> getMessageWorld(String message) {
        return CompletableFuture.supplyAsync( () -> {
            System.out.println("add World : "  + Thread.currentThread().getName());
            return message + "world";
        });
    }
```

```java
// 따로 따로 실행되어야 되는 경우
        CompletableFuture<String> hello = CompletableFuture.supplyAsync( () -> {
            System.out.println("hello : "  + Thread.currentThread().getName());
            return "hello";
        });
        
        CompletableFuture<String> world = CompletableFuture.supplyAsync( () -> {
            System.out.println("world : "  + Thread.currentThread().getName());
            return "world";
        });
        
       System.out.println(hello.thenCombine(world, (h, w) -> h + " "+ w).get());
    // 결과
    // hello : ForkJoinPool.commonPool-worker-1
    // world : ForkJoinPool.commonPool-worker-2
    // hello world
```

```java
// 좀더 프로그래밍적으로 표편
    CompletableFuture.allOf(hello, world)
        .thenAccept( result -> {
            ~~~~
        // 하지만 result 에 뭐가 들어오는지 모른다.
    } );
// 예제 및 결과
    System.out.println( CompletableFuture.allOf(hello, world).thenAccept(System.out::println).get() );
    // 결과
    // null
    // null

// 제대로 받아보자
    List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
    CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
    CompletableFuture<List<String>> results = 
        CompletableFuture.allOf(futuresArray)
            .thenApply( (v) -> futures.stream()
                    .map(CompletableFuture::join) // join 은 unCheckedExecption
                    .collect(Collectors.toList()));
    results.get().forEach(System.out::println);
// 아무거나 하나 빨리 끝나는거
    // Aceept 은 Comsumer
    CompletableFuture.anyOf(hello, world).thenAccept( System.out::println );
```

```java
// 예외처리는 
        boolean throwsError = true;
        
        CompletableFuture<String> world = CompletableFuture.supplyAsync( () -> {
            if(throwsError){
                throw new IllegalStateException("Test");
            }
            System.out.println("world : "  + Thread.currentThread().getName());
            return "world";
        }).exceptionally( ex -> {
            return ex.getMessage();
        });
        
// handle 은 정상 / 에러 두경우 사용 가능. 그래서 Bifuncation 이 들어옴
        boolean throwsError = true;
        
        CompletableFuture<String> world = CompletableFuture.supplyAsync( () -> {

            if(throwsError){
                throw new IllegalStateException("Test");
            }

            System.out.println("world : "  + Thread.currentThread().getName());
            return "world";
        }).handle( (result, ex) -> {
            if(ex != null) {
                return "Error!";
            }
            return result;
        });
```

- 더 공부할건 ForkJoin , Flow API


## ETC

### Annotaions 변화

- 타입선언부에서 선언 가능 및 중복사용 가능
```java
@Target(ElementType.TYPE_PARAMETER)
// 여기만 가능함 제레닉-타입지정하는 부분만.
    static class MyWork<@MyTestMark T> {
        public static <@MyTestMark C> void print(C c){
        } 
    }
@Target(ElementType.TYPE_USE)
// 이건 타입 선언하는 모든곳에서 가능함.
    static class MyWork<@MyTestMark T> {
        public static <@MyTestMark C> void print(@MyTestMark C c) throws @MyTestMark Exception{
            List<@MyTestMark String> test = new ArrayList<> ();
    }
```

```java
// 중복사용법
// Container dml Retention / Target 범위가 중복으로 사용할 애보다 넓어야 된다.

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface MyTestMarkContainer {
     
    MyTestMark[] value();
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(MyTestMarkContainer.class)
public @interface MyTestMark {

    String value();
}

@MyTestMark("test")
@MyTestMark("test2")
@MyTestMark("test3")

public class EtcStudy {
    
    public static void main(String[] args) {
        MyTestMark[] marks = EtcStudy.class.getAnnotationsByType(MyTestMark.class); 
        Arrays.stream(marks).forEach( m -> System.out.println(m.value()));
        System.out.println("=================== 위 -기존 / 아래 컨테이터 ===================");
        Arrays.stream( EtcStudy.class.getAnnotation(MyTestMarkContainer.class).value())
            .forEach( m -> System.out.println(m.value()));
    }
}

// 결과
// test
// test2
// test3
// =================== 위 -기존 / 아래 컨테이터 ===================
// test
// test2
// test3

```

### 배열 병렬 정렬
```java
    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));

    }
    // 결과
    // serial sorting took 1034843
    // parallel sorting took 1932415
```

### JVM 변경점
- JVM의 여러 메모리 영역 중에 PermGen 메모리 영역이 없어지고 Metaspace 영역이 생겼다.
- 동적으로 Class 가 만드는 경우가 생겨서 PermGen 이 꽉 차서 GBC 하는데 그래도 꽉차서 터질 수 있음.
- 근본적으로는 이런 동적 생성하는 부분을 찾아서 해결해야되지만..
- java8 에서는 Metaspace 로 변경되면서 일부 해소됨.
    - Heap : Eden / Old 
    - native Memory 영역에 Metaspace 라고 생겨 여기에 클래스 메타데이터를 담고 고정크기가 아님.


- PermGen : permanent generation, 클래스 메타데이터를 담는 곳.
    - Heap 영역에 속함.
    - 기본값으로 제한된 크기를 가지고 있음.  
    - `-XX:PermSize=N`, PermGen 초기 사이즈 설정
    - `-XX:MaxPermSize=N`, PermGen 최대 사이즈 설정

- Metaspace : 클래스 메타데이터를 담는 곳.
    - Heap 영역이 아니라, Native 메모리 영역이다.
    - 기본값으로 제한된 크기를 가지고 있지 않다. (필요한 만큼 계속 늘어난다.)
    - 자바 8부터는 PermGen 관련 java 옵션은 무시한다.
    - `-XX:MetaspaceSize=N`, Metaspace 초기 사이즈 설정.
    - `-XX:MaxMetaspaceSize=N`, Metaspace 최대 사이즈 설정. ( 이걸 설정하지 않으면 서버 메모리가 계속 먹힘 )

- 참고링크
    - [http://mail.openjdk.java.net/pipermail/hotspot-dev/2012-September/006679.html]
    - [https://m.post.naver.com/viewer/postView.nhn?volumeNo=23726161&memberNo=36733075]
    - [https://m.post.naver.com/viewer/postView.nhn?volumeNo=24042502&memberNo=36733075]
    - [https://dzone.com/articles/java-8-permgen-metaspace]
    - [jstat-jvm cmd 로 간단히 모니터링](https://steady-coding.tistory.com/m/591) / [사용법2](https://hbase.tistory.com/m/237)