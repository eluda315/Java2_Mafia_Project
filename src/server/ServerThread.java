package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

// 주 역할: 담당 클라이언트의 말을 듣고 GameManager에게 전달
// GameManager가 메세지 보내라고 시키면 담당 클라이언트에게 채팅 보냄
public class ServerThread extends Thread {
	private String nickName; // 닉네임은 GameManager가 설정
    private Socket socket;
    private GameManager gameManager;
    private PrintWriter pw;

    public ServerThread(Socket socket, GameManager gm) {
        this.socket = socket;
        this.gameManager = gm;
    }

    @Override
    public void run() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            this.pw = pw;

            // GameManager에게 새로운 클라이언트(나) 연결된 거 알리기
            gameManager.handleNewConnection(this);

            String request="";
            // 클라이언트 연결이 끊어지면 readLine()이 null 반환
            while ((request = bf.readLine()) != null) {
                gameManager.processMessage(this, request); // 받은 메세지를 gameManager에게 전달
            }
        } catch (IOException e) {
        	log("클라이언트 연결 오류");
        } finally {
        	gameManager.handleDisconnect(this); // 클라이언트 연결 해제 시 gameManager에게 알리기
        }
    }

    // gameManager가 메세지 보내라 하면 각자의 소켓으로 메세지를 보냄
    public void sendMessage(String message) {
        if (pw != null) {
            pw.println(message);
        } else {
            log("PrintWriter 초기화 안 됨");
        }
    }
    
    // GameManager가 사용
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private void log(String log) {
        System.out.println("[Thread " + this.threadId() + "] " + log);
    }
}