package server;

public interface Command {
	public void execute(ServerThread sender, String payload, State currentState);
}
