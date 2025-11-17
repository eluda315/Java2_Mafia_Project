package server;

import Controller.IState;

public class MafiaChatCommand implements Command{
	private CommandManager commandManager;
	public MafiaChatCommand(CommandManager cm) {
		this.commandManager = cm;
	}
	
	@Override
	public void execute(ServerThread sender, String payload, IState currentState) {
		// TODO Auto-generated method stub
		// 밤 상태에만 채팅 가능
		
		
//		if(currentState instanceof 밤) {
//			if(sender.getRole(sender.getId()??).equals("MIFIA")) {
//				commandManager.broadcastToMafia(sender.getNickName() + ": " + payload);
//			}
//		}
	}

}
