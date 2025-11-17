package server;

// ----------------------------------
// 근데 입장은 로비에서 하니까 출력은 없이 닉네임 세팅만 하도록 수정

public class JoinCommand implements Command{
	private CommandManager commandManager;
	public JoinCommand(CommandManager cm) {
		this.commandManager = cm;
	}
	
	@Override
	public void execute(ServerThread sender, String payload, State currentState) {
		// TODO Auto-generated method stub
		// 입장한 사람의 닉네임을 등록하고, 로비에 입장한 사람 목록 띄우기.(이건 서버가 안 해도 될까요?)
		sender.setNickName(payload);
		//gameManager.broadcastAll(payload+"님이 입장했습니다.");
		
	}

}
