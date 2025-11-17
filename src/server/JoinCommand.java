package server;

import Controller.IState;

// ----------------------------------
// 입장은 로비에서 하니까 출력은 없이 닉네임 세팅만 하도록 수정 -> 서버가 해야 하는 일일까요?
// 아니면 컨트롤러에서 닉네임을 등록하는건지...

public class JoinCommand implements ICommand{
	private CommandManager commandManager;
	public JoinCommand(CommandManager cm) {
		this.commandManager = cm;
	}
	
	@Override
	public void execute(ServerThread sender, String payload, IState currentState) {
		// TODO Auto-generated method stub
		// 입장한 사람의 닉네임을 등록하고, 로비에 입장한 사람 목록 띄우기.(이건 서버가 안 해도 될까요?)
		sender.setNickName(payload);
		//gameManager.broadcastAll(payload+"님이 입장했습니다.");
		
	}

}
