package server;

import Controller.IState;
import Controller.밤;
import Controller.사회자;

public class MafiaChatCommand implements ICommand{
	private CommandManager networkBrain;
	private 사회자 logicBrain;
	public MafiaChatCommand(CommandManager cm, 사회자 logic) {
		this.networkBrain = cm;
		this.logicBrain=logic;
	}
	
	@Override
	public void execute(ServerThread sender, String payload, IState currentState) {
		// TODO Auto-generated method stub
		// 밤 상태에만 채팅 가능
		if(currentState instanceof 밤) {
			
			// TODO 서버는 사회자만 참조하고 있어서 Player 객체에 직접 접근이 안 됩니다... 
			// 그래서 사회자가 Player의 상태를 체크해주는 로직이 없으면 너무 복잡해져요..
//			if(sender.getRole(sender.getId()??).equals("MIFIA")) {
//				networkBrain.broadcastToMafia(sender.getNickName() + ": " + payload);
//			}
		}
	}

}
