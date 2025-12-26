package test;

import adaptee.Turkey;
import adaptee.WildTurkey;
import adapter.TurkeyAdapter;
import target.Duck;
import target.MallardDuck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AdapterTest {

    private Duck duck;
    private Turkey turkey;

    private Duck turkeyAdapter;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        duck = new MallardDuck();
        turkey = new WildTurkey();

        turkeyAdapter = new TurkeyAdapter(turkey);

        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testMallardDuckQuack() {
        duck.quack();
        assertThat(outputStream.toString().trim()).isEqualTo("꽥");
    }

    @Test
    public void testMallardDuckFly() {
        duck.fly();
        assertThat(outputStream.toString().trim()).isEqualTo("날고 있어요!!");
    }

    @Test
    public void testWildTurkeyGobble() {
        turkey.gobble();
        assertThat(outputStream.toString().trim()).isEqualTo("골골");
    }

    @Test
    public void testWildTurkeyFly() {
        turkey.fly();
        assertThat(outputStream.toString().trim()).isEqualTo("짧은 거리를 날고 있어요!");
    }

    @Test
    public void testTurkeyAdapterQuack() {
        turkeyAdapter.quack();
        assertThat(outputStream.toString().trim()).isEqualTo("골골");
    }

    @Test
    public void testTurkeyAdapterFly() {
        turkeyAdapter.fly();
        String output = outputStream.toString().trim();

        String[] lines = output.split(System.lineSeparator());
        assertThat(lines).hasSize(5);

        for (String line : lines) {
            assertThat(line).isEqualTo("짧은 거리를 날고 있어요!");
        }
    }
}
