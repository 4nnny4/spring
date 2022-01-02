# spring
spring studie5



@controller 어노테이션
컨트롤러 클래스에 @Controller 어노테이션을 작성
해당 어노테이션이 적용된 클래스는 Controller임을 나타내고
해당 클래스가 Controller로 사용됨을 Spring FrameWork가 파악

@RequestMapping
해당 어노테이션이 선언된 클래스의 모든 메소드가 하나의 요청에 대한 처리를 할경우 사용됨
value 에 url을 입력, Method 영역에 메소드 방식을 입력

return -> dispatcher.forward처럼 해당 이름을 가진 뷰로 이동시킴
null 일시 이동 x

매개변수에 파라미터와 같은 이름이 적혀있다면
매개변수를 통해 가져옴, vo에 같은 파라미터와 이름이 같은 필드가 존재할경우 vo를 통해 담겨옴
