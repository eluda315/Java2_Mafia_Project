package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Player;

public class 사회자 {
	//사회자 객체 하나만 있어야 되니까 싱글톤 생성해봤음
	public static 사회자 매니저;
	
	//IState gameState = null;
	RoleFactory roleFactory = null;
	
	public List <Player> players = new ArrayList<>();
	public List <Player> ghosts = new ArrayList<>();
	
	public int dayCount=0;
	
	private int killedID=0;
	private int healedID=0;
	
	public int getKilledID() {
		return killedID;
	}

	public void setKilledID(int killedID) {
		this.killedID = killedID;
	}

	public int getHealedID() {
		return healedID;
	}

	public void setHealedID(int healedID) {
		this.healedID = healedID;
	}

	public 사회자() {
		매니저 = this;
		roleFactory = new RoleFactory(); 
	}
	
	public void init_game() {
		//몇 명이 게임하는지 입력 -> 나중에 ip 개수 세기로 바꿔야겠지만 일단...
		Scanner sc = new Scanner(System.in);
		System.out.println("==== 마피아 게임 시작! ====");
		System.out.print("플레이어 수 입력 (일단 4명) : ");
		int n = sc.nextInt();
		sc.nextLine();//엔터 먹어가기
		
		for(int i=0; i<n; i++) {
			int id = (i+1);
			System.out.print(id+"번 플레이어 닉네임: ");
			String nickname = sc.nextLine();
			Player p = roleFactory.createPlayer(nickname, id);
			players.add(p);
		}
		
		dayCount = 0;
		
		// 디버그용: 직업 출력 - 나중에 주석 처리해야됨
        System.out.println("\n[디버그] 직업 배정 결과");
        for (Player p : players) {
            System.out.println("[" + p.id + "] " + p.nickname + " : " + p.getRole());
        }
	}
	
	public void notifyAll(String message) {
		// 나중에 소켓 브로드캐스트로 대체
        System.out.println("[공지] " + message);
	}
	
	public void checkEnd() {
		int 생존마피아 = 0;
		int 생존시민 = 0;
		
		for(Player p : players) {
			if(p.is_alive == false) continue;//죽은 애 쓰루
			
			if("마피아".equals(p.getRole())) 생존마피아++;
			else 생존시민++;
		}
		
		if(생존마피아==0) {
			notifyAll("마피아가 모두 검거됐습니다. 시민 승리!");
			System.exit(0);
		}
		
		if(생존마피아>=생존시민) {
			notifyAll("마피아와 시민의 수가 같아졌습니다. 마피아 승리!");
			System.exit(0);
		}
	}

	public Player player_ID(int id) {
		for(Player p : players) {
			if(p.id == id) {
				return p;
			}
		}
		
		return null;
	}
	
	public void start() {
		init_game();
		Scanner sc = new Scanner(System.in);
		
		/*
		System.out.println("\n==============================");
        System.out.println("바로 밤이 되었습니다.");
        System.out.println("==============================");
		
		gameState = new 밤();
        gameState.limitTime(30);   // 형식상 시간 제한
        ((밤) gameState).start_night();

        resolveNight();   // 밤 결과 적용
        checkEnd();
		*/
       
		while(true) {
			
			System.out.println("\n==============================");
            System.out.println("DAY " + dayCount + " - 밤");
            System.out.println("==============================");
            //gameState = new 밤();
            //gameState.limitTime(30);   // 나중에 시간 제한 변경하기
            //((밤) gameState).start_night();
			
            if(killedID == healedID) {
            	System.out.println("의사의 보호로 아무도 죽지 않았습니다!");
            }
            
            else{
            	//아... Player하고 ID 연결해줄 무언가 필요하다.....
            	Player victim = player_ID(killedID);
            	if(victim != null && victim.is_alive) {
            		victim.is_alive = false;
            		ghosts.add(victim);
            		System.out.println("밤 사이 ["+victim.id+"]"+victim.nickname+"이(가) 사망했습니다.");
            	}
            	
            	else {
            		System.out.println("지목된 대상이 이미 죽었거나 존재하지 않습니다.");
            	}
            }
            
            killedID = 0;
            healedID = 0;
            
			System.out.println("\n==============================");
            System.out.println("DAY " + dayCount + " - 낮 / 토론");
            System.out.println("==============================");
            //gameState = new 토론();
            //gameState.limitTime(60);
            //((토론) gameState).chat();

            System.out.println("\n==============================");
            System.out.println("DAY " + dayCount + " - 낮 / 투표");
            System.out.println("==============================");
            //gameState = new 투표();
            //gameState.limitTime(30);
            //((투표) gameState).vote();
            
            checkEnd();
            dayCount++;
            
		}
		
	}
}
