package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;

public class Lobby extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titleTestLabel;
	private JButton startButton;
	private JLabel playerTextLabel;
	private JList playerList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lobby frame = new Lobby();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lobby() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titleTestLabel = new JLabel("마피아 게임 프로젝트");
		titleTestLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleTestLabel.setFont(new Font("굴림", Font.PLAIN, 80));
		titleTestLabel.setBounds(0, 43, 966, 148);
		contentPane.add(titleTestLabel);
		
		startButton = new JButton("시작하기");
		startButton.setFont(new Font("굴림", Font.PLAIN, 50));
		startButton.setBounds(312, 369, 334, 102);
		contentPane.add(startButton);
		
		playerTextLabel = new JLabel("입장한 플레이어");
		playerTextLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		playerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerTextLabel.setBounds(312, 160, 334, 49);
		contentPane.add(playerTextLabel);
		
		playerList = new JList();
		playerList.setBackground(new Color(192, 192, 192));
		playerList.setBounds(182, 211, 596, 135);
		contentPane.add(playerList);

	}
}
