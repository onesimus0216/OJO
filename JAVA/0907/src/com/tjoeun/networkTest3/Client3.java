package com.tjoeun.networkTest3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client3 extends JFrame implements ActionListener, Runnable {

	JTextArea textArea;		// 전체 대화 내용이 출력될 영역
	JPanel panel;			// 대화 내용을 입력하는 텍스트 필드와 전송 버튼이 올라갈 패널
	JTextField textField;	// 대화 내용을 입력하는 텍스트 필드
	JButton button;			// 텍스트 필드에 입력한 대화 내용을 전송하는 버튼

	Socket socket;
	PrintWriter printWriter;
	Scanner scanner;
	String message = "";
	
	public Client3() {
		setTitle("1:1 채팅 프로그램 - 클라이언트");
		setBounds(1000, 50, 500, 700);
		
//		채팅창 만들기
		textArea = new JTextArea();
		textArea.setBackground(Color.ORANGE);
//		textArea.setEnabled(false);		// 비활성화
		textArea.setEditable(false);	// 편집 불가능
		add(textArea);

		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(500, 40));
		textField = new JTextField();
		panel.add(textField);
		button = new JButton("전송");
		panel.add(button, BorderLayout.EAST);
		add(panel, BorderLayout.SOUTH);
		
//		텍스트 필드와 전송 버튼에 ActionListener를 걸어준다.
		textField.addActionListener(this);
		button.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
//				클라이언트 채팅 창이 닫힐 때 서버에게 나간다고 알려준다. => 통신을 종료한다.
				int result = JOptionPane.showConfirmDialog(textArea, "채팅을 종료하겠습니까?", "채팅 종료", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
//					서버에게 나간다고 알려준다.
					printWriter.write("저 나가요~~~~~ 바이바이~~~~~\n");
					printWriter.write("bye\n");
					printWriter.flush();
//					채팅에 사용한 통신 객체를 닫아준다.
					if (socket != null) { try { socket.close(); } catch (IOException err) { err.printStackTrace(); } }
//					채팅창을 닫는다.
					System.exit(0);
				} else {
//					윈도우에서 "X"을 클릭해도 윈도우가 닫히지 않게한다.
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		Client3 client = new Client3();
		
		try {
//			서버에 접속한다.
			client.socket = new Socket("192.168.0.26", 10004);
			client.message = "192.168.0.26 서버의 10004번 포트로 접속 시도\n";
			client.message += client.socket + " 접속 성공\n";
			client.textArea.setText(client.message);
			
//			서버에 접속했으므로 메시지를 입력할 수 있도록 텍스트 필드로 포커스를 이동한다.
			client.textField.requestFocus();
			
//			서버와 메시지를 주고받기 위해서 데이터 전송에 사용할 객체를 선언한다.
			client.printWriter = new PrintWriter(client.socket.getOutputStream());
			client.scanner = new Scanner(client.socket.getInputStream());

//			서버에서 전송되는 메시지를 받는 스레드를 실행한다.
			Thread thread = new Thread(client);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
//	서버에서 언제 메시지를 보내올지 모르기 때문에 통신이 종료될 때 까지 반복하며 사바에서 전송되는 메시지는 스레드로 받는다.
	@Override
	public void run() {
		
//		서버와 통신이 유지되고 있는 동안 반복한다. => 통신 소켓이 null이 아닌 동안 반복한다.
		while (socket != null) {
//			서버에서 전송되는 메시지를 받는다.
			String msg = "";
			try {
				msg = scanner.nextLine().trim();
			} catch (Exception e) {
//				e.printStackTrace();
				break;
			}
//			서버에서 전송된 메시지를 클라이언트 채팅창에 표시한다.
			if (msg.length() > 0) {
				message = "server >> " + msg + "\n" + message;
				textArea.setText(message);
//				서버에서 "bye"를 전송받으면 통신을 종료한다.
				if (msg.toLowerCase().equals("bye")) {
					break;
				}
			}
			try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
//		서버와 통신이 종료되면 메시지를 입력할 수 없도록 텍스트 필드와 전송 버튼을 비활성화 시킨다.
		textField.setEnabled(false);
		button.setEnabled(false);

//		통신에 사용한 모든 객체를 닫는다.
		if (socket != null) { try { socket.close(); } catch (IOException e) { e.printStackTrace(); } }
		
	}

//	텍스트 필드에서 엔터키를 누르거나 전송 버튼을 클릭해서 ActionListener가 실행되면 서버로 데이터를 전송한다.
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		텍스트 필드에 입력된 메시지를 받는다.
		String msg = textField.getText().trim();
		
//		텍스트 필드에 메시지가 입력된 상태일 경우 메시지를 클라이언트 채팅창에 표시하고 서버로 전송한다.
		if (msg.length() > 0) {
//			텍스트 필드에 입력한 메시지를 클라이언트 채팅창에 표시한다.
			message = "client >> " + msg + "\n" + message;
			textArea.setText(message);
//			텍스트 필드에 입력한 메시지를 서버로 전송한다.
			if (printWriter != null) {
				printWriter.write(msg + "\n");
				printWriter.flush();
			}
		}
		
//		다음 메시지를 입력받기 위해서 텍스트 필드에 입력된 메시지를 지우고 포커스를 옮겨준다.
		textField.setText("");
		textField.requestFocus();
		
	}

}






















