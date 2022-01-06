package edu.kh.fin.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.fin.member.model.dao.MemberDAO;
import edu.kh.fin.member.model.vo.Member;

@Service // Service 레이어, 비지니스 로직을 가진 클래스임을 명시 + Bean등록
public class MemberServiceImpl implements MemberService {

	@Autowired // bean으로 등록된 MemberDAO 객체 의존성 주입(DI)

	private MemberDAO dao;

	@Autowired
	private BCryptPasswordEncoder encoder;
	// Bean으로 등록된 BCryptPasswordEncoder 객체를 의존성 주입(DI)

	@Override
	public Member login(Member member) {

		System.out.println("서비스 bean 등록 및 DI 성공");

		String encPw = encoder.encode(member.getMemberPw());
		// 평문 (암호화 x 비밀번호)

		System.out.println("암호화된 비밀번호 : " + encPw);

		// 로그인 dao 호출
		Member loginMember = dao.login(member.getMemberId());
		System.out.println(loginMember);
		// 조회 성공시 member 객체 실패시 null

		// DB에 일치하는 아이디를 가진 회원이 있고
		// 입력 받은 비밀번호와 암호화된 비밀번호가 일치할때 ==> 로그인 성공
		if (loginMember != null && encoder.matches(member.getMemberPw(),loginMember.getMemberPw())) {

			loginMember.setMemberPw(null);

		} else { // 로그인 실패
			loginMember = null;
		}
		return loginMember;

	}

	@Override
	public int idDupCheck(String inputId) {
	// 커넥션 x closeconn x 
		
		return dao.idDupCheck(inputId);
	}

	@Override
	public int emailDupCheck(String inputEmail) {
		
		return dao.emailDupCheck(inputEmail);
	}

	// 회원가입
	// rollbackFor 속성 : 어떤 예외 발생 시 롤백을 수행할 지 지정
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int signUp(Member member) {
		
		// 비밀번호 암호화
		String encPw = encoder.encode(member.getMemberPw());
		member.setMemberPw(encPw);
		
		return dao.signUp(member);
		// 트랜잭션 처리 @Transactional
		
		/* * * * * 스프링에서 트랜잭션을 처리하는 방법 * * * * * * 
		 * 
		 * 	1. 코드 기반 처리 방법 (기존 commit, rollback )
		 * 	2.선언적 트랜잭션 처리 방법
		 * 	1) <tx:advice> XML 방식
		 * 	2)@Transactional 어노테이션 방식
		 * 	-> 조건 1. 트랜잭션 매니저가 Bean으로 등록 되어 있어야 함
		 * 		조건 2. @Transactional 어노테이션을 인식하기 위한 
		 * 				<tx:annotation-driven/> 태그가 존재해야함
		 * 
		 * 	@Transactional 어노테이션 rollback을 위한 어노테이션
		 * 	왜 커밋은 안하느냐 ? 커넥션 반환시 아무런 트랜잭션 처리가 되어있지 않다면 자동 commit
		 *		
		 *		@Transactional은 RuntimeException이 발생했을 때 Rollback을 수행함
		 *		
		 *
		 * * * * */
	}

	@Override
//	@Transactional //트랜잭션 수행은 여러 dml 수행시 사용  . 하나는 안써도 무방
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		
		return dao.updateMember(member);
	}

	@Override
	@Transactional
	public int updatePw(Map<String, String> map) {
		
		String savePw = dao.isPw(map.get("memberNo"));
		
		int result = 0;
		if(encoder.matches(map.get("currentPw"),savePw)) {
			// 일치 할때
			//새 비밀번호 암호화
			String encPw = encoder.encode(map.get("newPw"));
			map.put("newPw", encPw); // map에 key값이 "newPw" 인 요소의 value를 encPw로 변경한다
			
			result = dao.updatePw(map);
			
		}
		return result;
	}

	@Override
	@Transactional
	public int secession(String currentPw, Member loginMember) {
		int result = 0;
		String savePw = dao.isPw(loginMember.getMemberNo()+"");
		if(encoder.matches(currentPw,savePw)) {
			result = dao.secessionMember(loginMember);
		}
		
		return  result ;
	}

	

}
