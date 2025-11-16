package server;

// ----------------------------------
// 근데 입장은 로비에서 출력해야 되지 않나요? 이것도 따로 생각해봐야 하네염...
// 일단은 상태 상관없이 출려되도록 만들었습니다.

public class JoinCommand implements Command{
	private GameManager gameManager;
	public JoinCommand(GameManager gm) {
		this.gameManager = gm;
	}
	
	@Override
	public void execute(ServerThread sender, String payload, State currentState) {
		// TODO Auto-generated method stub
		// 입장한 사람의 닉네임을 등록하고, 전체 채팅으로 입장 알림
		sender.setNickName(payload);
		gameManager.broadcastAll(payload+"님이 입장했습니다.");
		
	}

}
