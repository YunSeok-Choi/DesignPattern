package prototype;

public class Drakon extends Monster {

    public Drakon(String name, int numHeads, boolean canBreatheFire) {
        super(name);
        this.numHeads = numHeads;
        this.canBreatheFire = canBreatheFire;
    }

    @Override
    public String spitPoison() {
        return this.name + "이(가) 독을 뿜는다";
    }

    @Override
    public Monster copy() throws CloneNotSupportedException {
        return (Monster) this.clone();
    }
}
