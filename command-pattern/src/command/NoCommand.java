package command;

// null 방지용 Command
public class NoCommand implements Command {

    @Override
    public void execute() {}

    @Override
    public void undo() {}
}
