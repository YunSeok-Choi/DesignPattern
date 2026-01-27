package prototype;

public class Dragon extends Monster {

    public Dragon(String name, boolean hasWings) {
        super(name);
        this.hasWings = hasWings;
        this.canBreatheFire = true;
    }

    @Override
    public Monster copy() throws CloneNotSupportedException {
        return (Monster) this.clone();
    }
}
