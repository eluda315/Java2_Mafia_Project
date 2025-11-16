package Model;

public class Player {
	public String nickname;
	public int id;
	private String role;
	public boolean is_alive = true;
	ISkill skill = null;
	
	public String getPlayer(int ID) {
		
		return nickname;
	}
	
	public String getRole(int ID) {
		
		return role;
	}
}
