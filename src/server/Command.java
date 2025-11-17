package server;

import Controller.IState;

public interface Command {
	public void execute(ServerThread sender, String payload, IState currentState);
}
