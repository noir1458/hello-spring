package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        // 테스트 끝날때마다 저장소를 지워줌
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);

        //assertj 라이브러리
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByname() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //2개 가입한것

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
    // 근데 전체를 돌리면 에러가 난다... 왜??
    // 모든 테스트는 순서와 상관없이  설계되어야 한다.
    // findAll이 먼저 실행되고 findByName이 실행되어 먼저 저장된게 나온것
    // 테스트 하나끝나고는 데이터 클리어해야함

    //afterEach 작성후에는 전체 테스트시 통과한다
}
