package command.hottub;

import command.Command;
import receiver.Hottub;

public class HottubOffCommand implements Command {

    private final Hottub hottub;

	public HottubOffCommand(Hottub hottub) {
		this.hottub = hottub;
	}

    @Override
	public void execute() {
		hottub.cool();
		hottub.off();
	}

    @Override
    public void undo() {
        hottub.on();
        hottub.heat();
        hottub.bubblesOn();
    }
}
