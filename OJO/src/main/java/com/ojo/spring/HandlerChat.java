package com.ojo.spring;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HandlerChat extends TextWebSocketHandler {

	// (<"bang_id", ��ID>, <"session", ����>) - (<"bang_id", ��ID>, <"session", ����>) - (<"bang_id", ��ID>, <"session", ����>) ���� 
	private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
	
	// Ŭ���̾�Ʈ�� ������ �޼��� ���� ó��
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		super.handleTextMessage(session, message);
        
		// JSON --> Map���� ��ȯ
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);
		
		// CLIENT ����
		if(mapReceive.get("cmd").equals("CMD_ENTER")) {
			// ���� ����Ʈ�� ����
			
			boolean flag = true;
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				if(mapSessionList.get("session")==session) {
					flag = false;
				}
			}
			if(flag) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bang_id", mapReceive.get("bang_id"));
				map.put("nickname", mapReceive.get("nickname"));
				map.put("session", session);
				sessionList.add(map);
			}
//			System.out.println("enter"+map);
			System.out.println("���Ǹ���Ʈ(enter)"+sessionList);
			// ���� ä�ù濡 ���� �޼��� ����
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String bang_id = (String) mapSessionList.get("bang_id");
				//String nickname = (String) mapSessionList.get("nickname");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				//System.out.println(mapReceive.get("bang_id"));
				if(bang_id.equals(mapReceive.get("bang_id"))) {
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("bang_id", bang_id);
					mapToSend.put("cmd", "CMD_ENTER");
					mapToSend.put("nickname", mapReceive.get("nickname"));
					//mapToSend.put("msg", nickname +  "���� ���� �߽��ϴ�.");
					
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			}
		}
		else if(mapReceive.get("cmd").equals("CMD_ENTER_RESPONSE")) {
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String bang_id = (String) mapSessionList.get("bang_id");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				if(sess !=session && bang_id.equals(mapReceive.get("bang_id"))){
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("cmd", "CMD_ENTER_RESPONSE");
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			}
			System.out.println("���Ǹ���Ʈ(enterRes)"+sessionList);
		}
		else if(mapReceive.get("cmd").equals("CMD_OUT")) {
			System.out.println("���Ǹ���Ʈ(out:before)"+sessionList);
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String bang_id = (String) mapSessionList.get("bang_id");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				
				if(sess !=session && bang_id.equals(mapReceive.get("bang_id"))){
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("cmd", "CMD_OUT");
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			}
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String bang_id = (String) mapSessionList.get("bang_id");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				if(session.equals(sess)) {
					sessionList.remove(mapSessionList);
				}
			}
			System.out.println("���Ǹ���Ʈ(out:after)"+sessionList);
		}
		else if(mapReceive.get("cmd").equals("CMD_MSG_SEND")) {
		// CLIENT �޼���
			// ���� ä�ù濡 �޼��� ����
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String bang_id = (String) mapSessionList.get("bang_id");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				
				if (bang_id.equals(mapReceive.get("bang_id"))) {
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("bang_id", bang_id);
					mapToSend.put("cmd", "CMD_MSG_SEND");
					mapToSend.put("msg",mapReceive.get("msg"));
					mapToSend.put("nickname",mapReceive.get("nickname"));

					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			} 
		}
	}

	// Ŭ���̾�Ʈ�� ������ ���� ó��
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		super.afterConnectionClosed(session, status);
        
		ObjectMapper objectMapper = new ObjectMapper();
		String now_bang_id = "";
		
		// ����� ������ ����Ʈ���� ����
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> map = sessionList.get(i);
			String bang_id = (String) map.get("bang_id");
			WebSocketSession sess = (WebSocketSession) map.get("session");
			
			if(session.equals(sess)) {
				now_bang_id = bang_id;
				sessionList.remove(map);
				break;
			}	
		}
		
		// ���� ä�ù濡 ���� �޼��� ���� 
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> mapSessionList = sessionList.get(i);
			String bang_id = (String) mapSessionList.get("bang_id");
			WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");

			if (bang_id.equals(now_bang_id)) {
				Map<String, String> mapToSend = new HashMap<String, String>();
				mapToSend.put("bang_id", bang_id);
				mapToSend.put("cmd", "CMD_EXIT");
				mapToSend.put("msg", session.getId() + "���� ���� �߽��ϴ�.");

				String jsonStr = objectMapper.writeValueAsString(mapToSend);
				sess.sendMessage(new TextMessage(jsonStr));
			}
		}
		
	}
}