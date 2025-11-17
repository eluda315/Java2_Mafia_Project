package Controller;

import java.util.Scanner;

import Model.Player;

public class 밤 implements IState{

	@Override
	public void limitTime(int time) {
		// TODO Auto-generated method stub
		
	}
	
	public void start_night() {
		//
		사회자 manager = 사회자.매니저;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[밤] : 각 플레이어 행동 개시");
		
		for(Player player : manager.players) {
			if(player.is_alive==false) continue;
			
			if(player.skill!=null) {
				//아이디만 넘겨서는 할 수가 없음...
				player.skill.skill(player.id, target);
			}
		}
		
	}

	@Override
	public void execute(사회자 manager) {
		// TODO Auto-generated method stub
		
	}
			
		
		
	}

