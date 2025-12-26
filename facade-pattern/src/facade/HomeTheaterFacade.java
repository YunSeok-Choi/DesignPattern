package facade;

import domain.*;

public class HomeTheaterFacade {

    private final Amplifier amplifier;
    private final Tuner tuner;
    private final StreamingPlayer streamingPlayer;
    private final Projector projector;
    private final TheaterLights theaterLights;
    private final CdPlayer cdPlayer;
    private final Screen screen;
    private final PopcornPopper popcornPopper;

    public HomeTheaterFacade(
            Amplifier amplifier,
            Tuner tuner,
            StreamingPlayer streamingPlayer,
            Projector projector,
            TheaterLights theaterLights,
            CdPlayer cdPlayer,
            Screen screen,
            PopcornPopper popcornPopper)
    {
        this.amplifier = amplifier;
        this.tuner = tuner;
        this.streamingPlayer = streamingPlayer;
        this.projector = projector;
        this.theaterLights = theaterLights;
        this.cdPlayer = cdPlayer;
        this.screen = screen;
        this.popcornPopper = popcornPopper;
    }


    public String watchMovie(String movie) {
        StringBuilder result = new StringBuilder();
        result.append("영화 볼 준비 중...\n");
        result.append(popcornPopper.on()).append("\n");
        result.append(popcornPopper.pop()).append("\n");
        result.append(theaterLights.dim(10)).append("\n");
        result.append(screen.down()).append("\n");
        result.append(projector.on()).append("\n");
        result.append(projector.wideScreenMode()).append("\n");
        result.append(amplifier.on()).append("\n");
        result.append(amplifier.setStreamingPlayer(streamingPlayer)).append("\n");
        result.append(amplifier.setSurroundSound()).append("\n");
        result.append(amplifier.setVolume(5)).append("\n");
        result.append(streamingPlayer.on()).append("\n");
        result.append(streamingPlayer.play(movie));
        return result.toString();
    }


    public String endMovie() {
        StringBuilder result = new StringBuilder();
        result.append("홈시어터를 끄는 중...\n");
        result.append(popcornPopper.off()).append("\n");
        result.append(theaterLights.on()).append("\n");
        result.append(screen.up()).append("\n");
        result.append(projector.off()).append("\n");
        result.append(amplifier.off()).append("\n");
        result.append(streamingPlayer.stop()).append("\n");
        result.append(streamingPlayer.off());
        return result.toString();
    }

    public String listenToRadio(double frequency) {
        StringBuilder result = new StringBuilder();
        result.append("전파를 맞추는 중...\n");
        result.append(tuner.on()).append("\n");
        result.append(tuner.setFrequency(frequency)).append("\n");
        result.append(amplifier.on()).append("\n");
        result.append(amplifier.setVolume(5)).append("\n");
        result.append(amplifier.setTuner(tuner));
        return result.toString();
    }

    public String endRadio() {
        StringBuilder result = new StringBuilder();
        result.append("튜너 종료 중...\n");
        result.append(tuner.off()).append("\n");
        result.append(amplifier.off());
        return result.toString();
    }

}
