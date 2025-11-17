package server;

import Controller.IState;

public interface ICommand {
	public void execute(ServerThread sender, String payload, IState currentState);
}
