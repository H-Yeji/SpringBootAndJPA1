package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //컴포넌트 스캔 대상이 돼서 자동으로 스프링
@Transactional(readOnly = true) //트랜잭션 안에서 이루어져야 함
@RequiredArgsConstructor //final을 가진 것을 가지고 생성자를 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) { //회원 가입 서비스
        //중복 회원 검증
        validateDuplicateMember(member);
        //검증을 통과하면 회원 저장
        memberRepository.save(member);
        //id 반환
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());//같은 이름이 있는지 조회
        if (!findMembers.isEmpty()) { //비어있지 않으면 = 이미 이름이 존재하면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); //memberRepository에 만들어둔 findAll메서드
    }

    /**
     * 회원 한명 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
