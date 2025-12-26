package test;

import domain.*;
import facade.HomeTheaterFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FacadeTest {

    private HomeTheaterFacade homeTheaterFacade;

    private Amplifier amplifier;
    private Tuner tuner;
    private StreamingPlayer streamingPlayer;
    private CdPlayer cdPlayer;
    private PopcornPopper popcornPopper;
    private Projector projector;
    private TheaterLights theaterLights;
    private Screen screen;

    @BeforeEach
    public void setUp() {
        amplifier = new Amplifier("앰프");
        tuner = new Tuner("튜너", amplifier);
        cdPlayer = new CdPlayer("CD 플레이어", amplifier);
        streamingPlayer = new StreamingPlayer("스트리밍 플레이어", amplifier);
        popcornPopper = new PopcornPopper("팝콘 기계");
        projector = new Projector("프로젝터",streamingPlayer);
        screen = new Screen("스크린");
        theaterLights = new TheaterLights("조명");


        homeTheaterFacade = new HomeTheaterFacade(
                amplifier,
                tuner,
                streamingPlayer,
                projector,
                theaterLights,
                cdPlayer,
                screen,
                popcornPopper
        );
    }

    @Test
    public void testWatchMovie() {

        // When - 영화 "해리포터"를 시청
        String result = homeTheaterFacade.watchMovie("해리포터");

        // Then - 모든 기기가 올바른 순서로 동작해야 함
        assertThat(result).contains("영화 볼 준비 중...");
        assertThat(result).contains("팝콘 기계 on");
        assertThat(result).contains("팝콘 기계 popping popcorn!");
        assertThat(result).contains("조명 dimming to 10%");
        assertThat(result).contains("스크린 going down");
        assertThat(result).contains("프로젝터 on");
        assertThat(result).contains("프로젝터 in widescreen mode (16x9 aspect ratio)");
        assertThat(result).contains("앰프 on");
        assertThat(result).contains("앰프 setting Streaming player to 스트리밍 플레이어");
        assertThat(result).contains("앰프 surround sound on (5 speakers, 1 subwoofer)");
        assertThat(result).contains("앰프 setting volume to 5");
        assertThat(result).contains("스트리밍 플레이어 on");
        assertThat(result).contains("스트리밍 플레이어 playing \"해리포터\"");
    }

    @Test
    public void testEndMovie() {
        homeTheaterFacade.watchMovie("해리포터");

        // When - 영화 시청을 종료
        String result = homeTheaterFacade.endMovie();

        // Then - 모든 기기가 올바른 순서로 꺼져야 함
        assertThat(result).contains("홈시어터를 끄는 중...");
        assertThat(result).contains("팝콘 기계 off");
        assertThat(result).contains("조명 on");
        assertThat(result).contains("스크린 going up");
        assertThat(result).contains("프로젝터 off");
        assertThat(result).contains("앰프 off");
        assertThat(result).contains("스트리밍 플레이어 stopped \"해리포터\"");
        assertThat(result).contains("스트리밍 플레이어 off");
    }

    @Test
    public void testListenToRadio() {

        // When - 라디오 주파수 98.5로 라디오 청취 시작
        String result = homeTheaterFacade.listenToRadio(98.5);

        // Then - 튜너와 앰프가 올바르게 설정되어야 함
        assertThat(result).contains("전파를 맞추는 중...");
        assertThat(result).contains("튜너 on");
        assertThat(result).contains("튜너 setting frequency to 98.5");
        assertThat(result).contains("앰프 on");
        assertThat(result).contains("앰프 setting volume to 5");
        assertThat(result).contains("앰프 setting tuner to 튜너");
    }

    @Test
    public void testEndRadio() {
        // Given - 라디오 주파수 98.5로 라디오가 재생 중
        homeTheaterFacade.listenToRadio(98.5);

        // When - 라디오 청취를 종료
        String result = homeTheaterFacade.endRadio();

        // Then - 튜너와 앰프가 꺼져야 함
        assertThat(result).contains("튜너 종료 중...");
        assertThat(result).contains("튜너 off");
        assertThat(result).contains("앰프 off");
    }

    @Test
    public void testIndividualComponents() {

        // When & Then - 각 컴포넌트가 독립적으로 올바르게 동작해야 함
        assertThat(amplifier.on()).isEqualTo("앰프 on");
        assertThat(amplifier.setVolume(10)).isEqualTo("앰프 setting volume to 10");
        assertThat(tuner.setFrequency(100.1)).isEqualTo("튜너 setting frequency to 100.1");
        assertThat(streamingPlayer.play("타이타닉")).isEqualTo("스트리밍 플레이어 playing \"타이타닉\"");
        assertThat(popcornPopper.pop()).isEqualTo("팝콘 기계 popping popcorn!");
        assertThat(projector.wideScreenMode()).isEqualTo("프로젝터 in widescreen mode (16x9 aspect ratio)");
        assertThat(screen.down()).isEqualTo("스크린 going down");
        assertThat(theaterLights.dim(20)).isEqualTo("조명 dimming to 20%");
    }

}
