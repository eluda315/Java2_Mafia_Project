package server;

public class AllChatCommand implements Command {
	private GameManager gameManager;
	public AllChatCommand(GameManager gm) {
		this.gameManager = gm;
	}

	@Override
	public void execute(ServerThread sender, String payload, State currentState) {
		// TODO Auto-generated method stub
		// 토론 상태일 때만 실행됨.
		
		
//		if(currentState instanceof 투표) {
//			if(sender.is_alive()) {
//				gameManager.broadcastAll(sender.getNickName() + ": " + payload);
//			} // 죽었으면 그냥 아무것도 안되게 했습니다. 상태를 알려주는 것도 좋을 것 같아요.
//		} else {
//			sender.sendMessage("채팅은 토론 상태에서만 할 수 있습니다.");
//		}
	}

}
