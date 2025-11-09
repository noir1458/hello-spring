package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService; //= new MemberService();
    //근데 이건 다른 객체 아닌가...?? 애매.. 2개를 쓸 이유가 없다
    // 다른 객체 repository를 쓰면 내용물이 달라질수도. 지금은 static라서 괜찮긴 한데
    MemoryMemberRepository memberRepository; // = new MemoryMemberRepository();

    // 각 테스트 실행 이전에 각각 넣어주도록 한다.
    // 이렇게 하면 memberService랑 memberRepository가 같은 객체를 쓰게 된다.
    // DI(Dependency Injection) 의존성 주입
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // memberService가 memberRepository를 외부에서 넣어주도록 변경
    }

    @AfterEach
    public void afterEach() {
        // 테스트 끝날때마다 저장소를 지워줌
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName( ));
    }

    @Test
    public void duplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            //fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}