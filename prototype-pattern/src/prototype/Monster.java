package prototype;

public abstract class Monster implements Cloneable {

    protected boolean eatsChildren = true;
    protected boolean hasWings = false;
    protected int numHeads = 1;
    protected boolean canBreatheFire = false;
    protected String name;

    public Monster(String name) {
        this.name = name;
    }

    public String spitPoison() {
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumHeads() {
        return numHeads;
    }

    public boolean canBreatheFire() {
        return canBreatheFire;
    }

    public boolean eatsChildren() {
        return eatsChildren;
    }

    public boolean hasWings() {
        return hasWings;
    }

    public abstract Monster copy() throws CloneNotSupportedException;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("나는 ").append(this.name).append("이고 머리가 ").append(this.numHeads).append("개 있다. ");
        if (this.canBreatheFire) {
            sb.append("불을 뿜을 수 있다. ");
        }
        if (this.eatsChildren) {
            sb.append("아이들을 잡아먹는다. ");
        }
        if (this.hasWings) {
            sb.append("날개가 있다. ");
        }
        return sb.toString().trim();
    }
}
