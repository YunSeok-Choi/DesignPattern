package test;

import factory.TreeFactory;
import flyweight.Tree;
import flyweight.TreeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플라이웨이트 패턴 테스트")
public class FlyweightTest {

    private TreeFactory treeFactory;

    @BeforeEach
    void setUp() {
        treeFactory = new TreeFactory();
    }

    @Test
    @DisplayName("같은 타입의 나무는 동일한 인스턴스를 공유한다")
    void testSameTypeSharesInstance() {
        // Given & When
        Tree deciduous1 = treeFactory.getTree(TreeType.DECIDUOUS);
        Tree deciduous2 = treeFactory.getTree(TreeType.DECIDUOUS);
        Tree conifer1 = treeFactory.getTree(TreeType.CONIFER);
        Tree conifer2 = treeFactory.getTree(TreeType.CONIFER);

        // Then
        assertThat(deciduous1).isSameAs(deciduous2);
        assertThat(conifer1).isSameAs(conifer2);
    }

    @Test
    @DisplayName("다른 타입의 나무는 다른 인스턴스이다")
    void testDifferentTypesAreDifferentInstances() {
        // Given & When
        Tree deciduous = treeFactory.getTree(TreeType.DECIDUOUS);
        Tree conifer = treeFactory.getTree(TreeType.CONIFER);

        // Then
        assertThat(deciduous).isNotSameAs(conifer);
    }

    @Test
    @DisplayName("여러 위치에 같은 Flyweight 객체를 재사용할 수 있다")
    void testFlyweightReuseAtMultipleLocations() {
        // Given - 하나의 Flyweight 객체
        Tree conifer = treeFactory.getTree(TreeType.CONIFER);
        int[][] locations = {{1, 1}, {33, 50}, {100, 90}};

        // When & Then - 여러 위치에서 동일한 인스턴스 재사용하며 위치값도 정확히 표시
        for (int[] location : locations) {
            Tree reusedTree = treeFactory.getTree(TreeType.CONIFER);
            String result = reusedTree.display(location[0], location[1]);

            assertThat(reusedTree).isSameAs(conifer);
            assertThat(result).isEqualTo("침엽수가 위치한 좌표: " + location[0] + ", " + location[1]);
        }
    }

    @Test
    @DisplayName("Flyweight 패턴으로 메모리를 절약할 수 있다")
    void testMemorySavingWithFlyweight() {
        // Given
        int treeCount = 1000;

        // When
        Tree[] trees = new Tree[treeCount];
        for (int i = 0; i < treeCount; i++) {
            trees[i] = treeFactory.getTree(i % 2 == 0 ? TreeType.DECIDUOUS : TreeType.CONIFER);
        }

        // Then - 1000개 요청해도 실제 인스턴스는 2개만 존재
        long uniqueInstances = java.util.Arrays.stream(trees)
                .map(System::identityHashCode)
                .distinct()
                .count();
        assertThat(uniqueInstances).isEqualTo(2);
    }
}
