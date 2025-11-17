package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.ClientManager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextField;

public class Lobby extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titleTextLabel;
	private JButton startButton;
	private JLabel playerTextLabel;
	private JList<String> playerList;
	private JTextField nicknameInputField;
	private JLabel noticeTextLabel;
	
	private DefaultListModel<String> enteredPlayer = new DefaultListModel<String>();

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
		
		titleTextLabel = new JLabel("마피아 게임 프로젝트");
		titleTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleTextLabel.setFont(new Font("굴림", Font.PLAIN, 80));
		titleTextLabel.setBounds(0, 43, 966, 102);
		contentPane.add(titleTextLabel);
		
		startButton = new JButton("시작하기");
		startButton.setFont(new Font("굴림", Font.PLAIN, 50));
		startButton.setBounds(312, 395, 334, 102);
		contentPane.add(startButton);
		startButton.setEnabled(false);
		
		//시작하기 버튼 누르면 View 띄우고 Lobby는 닫기
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				View view = new View();
				view.setVisible(true);
				dispose();
				ClientManager.sendMessage("게임시작");
				System.out.println("게임시작 메세지 전송완성");
			}
		});
						
		playerTextLabel = new JLabel("입장한 플레이어");
		playerTextLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		playerTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerTextLabel.setBounds(312, 186, 334, 49);
		contentPane.add(playerTextLabel);
										
		playerList = new JList<String>();
		playerList.setBackground(new Color(192, 192, 192));
		playerList.setBounds(182, 237, 596, 135);
		playerList.setVisibleRowCount(6);
		playerList.setModel(enteredPlayer);
		
		DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
		playerList.setCellRenderer(centerRenderer);
		
		contentPane.add(playerList);
		
		nicknameInputField = new JTextField();
		nicknameInputField.setBounds(483, 155, 106, 21);
		contentPane.add(nicknameInputField);
		nicknameInputField.setColumns(10);
		
		//닉네임 입력받아 ClientManager에게 보내기
		nicknameInputField.addActionListener(new ActionListener() { 			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTextField nicknameField = (JTextField) e.getSource(); 	
				String nickname = nicknameField.getText();
				ClientManager.sendMessage(nickname); 
				System.out.println("닉네임: "+ nickname + " ClientManeger에게 보내기 완료");
				//서버까지 연결되면 서버가 잘 받아야 화면에 띄우기
				enteredPlayer.addElement(nickname);
				nicknameField.setText("");
				
				if(enteredPlayer.getSize() >= 4) {
					startButton.setEnabled(true);
				}						
			}
		});
				
		noticeTextLabel = new JLabel("닉네임을 입력하세요");
		noticeTextLabel.setBounds(359, 158, 112, 15);
		contentPane.add(noticeTextLabel);

	}
	
	
	
	
}
