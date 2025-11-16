package server;

public class MafiaChatCommand implements Command{
	private GameManager gameManager;
	public MafiaChatCommand(GameManager gm) {
		this.gameManager = gm;
	}
	
	@Override
	public void execute(ServerThread sender, String payload, State currentState) {
		// TODO Auto-generated method stub
		// 밤 상태에서만 실행되나요?
		
		
//		if(currentState instanceof 밤) {
//			if(sender.getRole(sender.getId()??).equals("MIFIA")) {
//				gameManager.broadcastToMafia(sender.getNickName() + ": " + payload);
//			}
//		}
	}

}
