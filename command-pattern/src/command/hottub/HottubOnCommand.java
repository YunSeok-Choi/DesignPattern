package command.hottub;

import command.Command;
import receiver.Hottub;

public class HottubOnCommand implements Command {

    private final Hottub hottub;

	public HottubOnCommand(Hottub hottub) {
		this.hottub = hottub;
	}

    @Override
	public void execute() {
		hottub.on();
		hottub.heat();
		hottub.bubblesOn();
	}

    @Override
    public void undo() {
        hottub.cool();
        hottub.off();
    }
}
