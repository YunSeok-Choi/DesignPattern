import behavior.AxeBehavior;
import behavior.BowAndArrowBehavior;
import behavior.SwordBehavior;
import role.Character;
import role.King;
import role.Knight;
import role.Troll;

public class Main {
    public static void main(String[] args) {

        // Character 코드 참고

        Character king = new King(new SwordBehavior());
        Character knight = new Knight(new BowAndArrowBehavior());
        Character troll = new Troll(new AxeBehavior());

        System.out.println(king.getName() + "의 " + king.fight());
        System.out.println(knight.getName() + "의 " + knight.fight());
        System.out.println(troll.getName() + "의 " + troll.fight());

        System.out.println();

        knight.changeWeapon(new SwordBehavior());
        System.out.println(knight.getName() + "의 " + knight.fight());

    }
}
