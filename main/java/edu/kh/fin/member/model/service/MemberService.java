package edu.kh.fin.member.model.service;

import java.util.Map;

import edu.kh.fin.member.model.vo.Member;

// 인터페이스란 ? 모든 메소드가 추상인 클래스
// - 필드는 묵시적을 public static final
// - 메소드는 묵시적으로 public abstract
// default 메소드도 가능은 하다

// Service에서 Interface를 사용하는 이유??

/*
 *  1. 프로젝트에 규칙성을 부여하기 위해서
 *  --> 인터페이스 상속 -> 오버라이딩 강제화
 *  -> 상속받은 모든 클래스가 동일한 형태가 된다.
 *  
 *  
 *  2. 유지보수를 위해서
 *  --> Service라는 비지니스 로직처리 부분은 수정이 많다보니
 *  		인터페이스를 상속받은 다른 클래스에 수정코드를 작성후
 *  		부모 인터페이스 = 자식 객체; (다형성 업캐스팅) 상태에서
 *  		대입 되는 객체만을 교체
 *  
 *  3. 클래스간의 결합도를 낮추기 위해서
 *  
 *  
 *  4. Spring AOP를 위해서
 *  -> AOP는 spring - proxy를 이용해 동작하는데
 *  	spring-proxy 객체는 service 인터페이스를 상속받아 동작
 */
public interface MemberService {
		
	Member login(Member member);

	/**
	 * @param inputId
	 * @return
	 */
	int idDupCheck(String inputId);

	int emailDupCheck(String inputEmail);

	int signUp(Member member);

	int updateMember(Member member);

	int updatePw(Map<String, String> map);

	int secession(String currentPw, Member loginMember);
}
