package dynamic_proxy.test;

import dynamic_proxy.domain.Person;
import dynamic_proxy.domain.PersonImpl;
import dynamic_proxy.handler.NonOwnerInvocationHandler;
import dynamic_proxy.handler.OwnerInvocationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("동적 프록시 테스트")
class DynamicProxyTest {

    private Person gildong;
    private Person dooly;

    @BeforeEach
    void setUp() {
        gildong = new PersonImpl();
        gildong.setName("홍길동");
        gildong.setInterests("자동차, 컴퓨터, 음악");
        gildong.setGeekRating(7);

        dooly = new PersonImpl();
        dooly.setName("둘리");
        dooly.setInterests("쇼핑, 영화, 음악");
        dooly.setGeekRating(6);
    }

    Person getOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }

    Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }

    @Test
    @DisplayName("소유자는 자신의 이름을 조회할 수 있다")
    void testOwnerCanGetName() {
        Person ownerProxy = getOwnerProxy(gildong);

        String name = ownerProxy.getName();

        assertEquals("홍길동", name);
    }

    @Test
    @DisplayName("소유자는 자신의 관심사를 설정할 수 있다")
    void testOwnerCanSetInterests() {
        Person ownerProxy = getOwnerProxy(gildong);

        ownerProxy.setInterests("볼링, 바둑");

        assertEquals("볼링, 바둑", ownerProxy.getInterests());
    }

    @Test
    @DisplayName("소유자는 자신의 평점을 설정할 수 없다")
    void testOwnerCannotSetOwnRating() {
        Person ownerProxy = getOwnerProxy(gildong);

        assertThatThrownBy(() -> ownerProxy.setGeekRating(10))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("소유자는 자신의 평점을 설정할 수 없습니다");
    }

    @Test
    @DisplayName("소유자는 자신의 평점을 조회할 수 있다")
    void testOwnerCanGetRating() {
        Person ownerProxy = getOwnerProxy(gildong);

        int rating = ownerProxy.getGeekRating();

        assertEquals(7, rating);
    }

    @Test
    @DisplayName("비소유자는 타인의 이름을 조회할 수 있다")
    void testNonOwnerCanGetName() {
        Person nonOwnerProxy = getNonOwnerProxy(gildong);

        String name = nonOwnerProxy.getName();

        assertEquals("홍길동", name);
    }

    @Test
    @DisplayName("비소유자는 타인의 관심사를 설정할 수 없다")
    void testNonOwnerCannotSetInterests() {
        Person nonOwnerProxy = getNonOwnerProxy(gildong);

        assertThatThrownBy(() -> nonOwnerProxy.setInterests("볼링, 바둑"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("타인의 정보를 수정할 수 없습니다");
    }

    @Test
    @DisplayName("비소유자는 타인의 평점을 설정할 수 있다")
    void testNonOwnerCanSetRating() {
        Person nonOwnerProxy = getNonOwnerProxy(gildong);

        nonOwnerProxy.setGeekRating(3);

        assertEquals(5, nonOwnerProxy.getGeekRating());
    }

    @Test
    @DisplayName("비소유자는 타인의 평점을 조회할 수 있다")
    void testNonOwnerCanGetRating() {
        Person nonOwnerProxy = getNonOwnerProxy(dooly);

        int rating = nonOwnerProxy.getGeekRating();

        assertEquals(6, rating);
    }

    @Test
    @DisplayName("PersonImpl은 이름을 저장하고 반환한다")
    void testPersonName() {
        Person person = new PersonImpl();
        person.setName("테스트");

        assertEquals("테스트", person.getName());
    }

    @Test
    @DisplayName("평점은 누적 평균으로 계산된다")
    void testGeekRatingAverage() {
        Person person = new PersonImpl();
        person.setGeekRating(10);
        person.setGeekRating(6);

        assertEquals(8, person.getGeekRating());
    }

    @Test
    @DisplayName("평점이 없으면 0을 반환한다")
    void testGeekRatingZeroWhenNoRating() {
        Person person = new PersonImpl();

        assertEquals(0, person.getGeekRating());
    }
}
