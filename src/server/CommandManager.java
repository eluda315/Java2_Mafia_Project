package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// -- 클라이언트에서 메세지를 보낼 때 join, message, mafia_message 이런걸 구분해서 보내줘야 할 것 같습니당. --


// 주 역할: 모든 게임 로직, 상태, 클라이언트 목록 관리
public class CommandManager {
	
	private State currentState; // State 패턴을 위한 필드
	private Map<String, Command> commandMap; // Command 패턴을 위한 필드
	private List<ServerThread> allClients = Collections.synchronizedList(new ArrayList<>()); // 여러 스레드가 동시에 접근하므로 동기화된 리스트 사용
	
	public CommandManager() {
		// 여기서 현재 상태도 초기화 하면 될 것 같아요
		//this.currentState = new 투표();
		
		// 커맨드 패턴 적용을 위한 커멘드 초기화
		this.commandMap = new HashMap<>();
		this.commandMap.put("join", new JoinCommand(this));
        this.commandMap.put("message", new AllChatCommand(this));
        this.commandMap.put("mafia_message", new MafiaChatCommand(this));
	}
	
    public synchronized void handleNewConnection(ServerThread clientThread) {
        allClients.add(clientThread);
        System.out.println("[CommandManager] 새 연결. 총 " + allClients.size() + "명");
        // 수정 가능. 일단은 사용자가 직접 join:닉네임을 쳐야 닉네임이 저장되는 형식임.(해당 클라이언트에게만 보이는 메세지)
        clientThread.sendMessage("서버에 접속했습니다. 닉네임을 'join:닉네임' 형식으로 입력하세요.");
    }
	
    public synchronized void handleDisconnect(ServerThread clientThread) {
        allClients.remove(clientThread);
        System.out.println("[CommandManager] 연결 해제: " + clientThread.getNickName() + ". 남은 인원 " + allClients.size() + "명");
        broadcastAll(clientThread.getNickName() + "님이 퇴장했습니다."); // 모든 유저에게 퇴장 알림
    }

	// ServerThread가 이 메소드를 호출하면 command 패턴을 적용하여 처리?
	public synchronized void processMessage(ServerThread sender, String rawMessage) {
	    if(rawMessage == null || rawMessage.equals("")) return;
	    System.out.println("[CommandManager] 메시지 받음: " + rawMessage);
		
	    String[] tokens = rawMessage.split(":", 2);
	    String commandKey = tokens[0]; // "join", "message", "mafia_message" 중 하나
	    String payload;
	    if(tokens.length > 1) {
	    	payload = tokens[1]; // 유저가 보낸 메세지(실제 내용)
	    } else {
	    	payload="";
	    }
	    
	    
	    
	    // 커맨드 객체에게 실행을 위임
	    Command command = commandMap.get(commandKey);
	    if (command != null) {
	        command.execute(sender, payload, currentState);
	    } else {
	    	// join, message, mafia_message가 아닌 명령어가 왔을 시
	    	sender.sendMessage("알 수 없는 명령어입니다: " + commandKey);
	    }
	}
	
	public void broadcastAll(String message) {
        for (ServerThread client : allClients) {
            client.sendMessage(message);
        }
    }
	
	// Player 객체가 없어서 일단 주석 처리했습니다. 대충 이런식으로 만들면 될 것 같아요.
	public void broadcastToMafia(String message) {
//         for (ServerThread client : allClients) {
//             if (client.getRole(id?).equals("MAFIA")) {
//                 client.sendMessage(message);
//             }
//         }
    }
	
	
}
