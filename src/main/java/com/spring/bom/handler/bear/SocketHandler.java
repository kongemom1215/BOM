package com.spring.bom.handler.bear;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.spring.bom.model.bear.Chat;
import com.spring.bom.model.bear.User;
import com.spring.bom.service.bear.ChatService;


@Component
public class SocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private ChatService cs; 
	
	List<HashMap<String, Object>> rls = new ArrayList<>(); // 웹소켓 세션을 담아둘 리스트 --- rommlistsessions\
	//파일전송
	private static final String FILE_UPLOAD_PATH = "C:/spring/springSrc3914/bom/src/main/webapp/image";
	static int fileUploadIdx = 0;
	static String fileUploadSession = "";
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message){
		//메시지 발송
		System.out.println("--------메세지 발송 ---------");
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		System.out.println(obj);
		//view 단에서 session 저장해서 가지고 오는방향
		//회원아이디 
		int ucode = Integer.parseInt((String)obj.get("sessionId"));
		System.out.println("sessionId - > " + ucode);
		//chat에서 메세지 작성한거 kkk로 저장   
		String kkk = (String)obj.get("msg");
		System.out.println("msg - > " + kkk);
		int roomnumber = Integer.parseInt(String.valueOf(obj.get("roomNumber")));
		System.out.println("ccode - > " + roomnumber);
		//session id 를 int형식 id 로가지고오기   
				//Integer.parseInt((String)obj.get("sessionId"));
		//roomnumber int형식 roomnumber 로가지고오기
		//int number = Integer.parseInt((String)obj.get("roomNumber")) ;
		System.out.println("회원아이디 : " + ucode + " 방번호 : " + roomnumber + " 메세지내용 : " + kkk );
		//chat vo에 변수저장후 넘기기 
		Chat chat = new Chat();
		//메세지
		chat.setCdmessage(kkk);
		// 방코드 
		chat.setCcode(roomnumber);
		// 회원아이디
		chat.setUcode(ucode);
		int result = cs.chatmsg(chat);
		System.out.println("chat -> " +chat);
		if(result >0 ) {
		System.out.println("socketHandler handleTextMessage 메세지 저장성공 - > " +result);}
		else {
			System.out.println("socketHandler handleTextMessage 메세지 저장실패 ");
		}
		System.out.println("socketHandler handleTextMessage kkk - > " + kkk);
		System.out.println("socketHandler handleTextMessage msg - >" + msg);
		System.out.println("socketHandler handleTextMessage obj ->  "+ obj);
		
		String rN = String.valueOf(obj.get("roomNumber")) ;
		HashMap<String, Object> temp = new HashMap<String, Object>();
		System.out.println("sockethandler chaeck1 " );
		if(rls.size() > 0) {
			System.out.println("sockethandler chaeck2 " );
			for(int i=0; i<rls.size(); i++) {
				System.out.println("sockethandler chaeck3 " );
				String roomNumber = (String) rls.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
				if(roomNumber.equals(rN)) { //같은값의 방이 존재한다면
					temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
					System.out.println("sockethandler chaeck4 " );
					break;
				}
			}System.out.println("sockethandler chaeck5 " );
		//	Chat chat = new Chat();
		//	chat.setCdmessage(obj.toString());
			//해당 방의 세션들만 찾아서 메시지를 발송해준다.
			for(String k : temp.keySet()) { 
				if(k.equals("roomNumber")) { //다만 방번호일 경우에는 건너뛴다.
					continue;
				}
				
				WebSocketSession wss = (WebSocketSession) temp.get(k);
				
				if(wss != null) {
					try {
						wss.sendMessage(new TextMessage(obj.toJSONString()));
						System.out.println("socketHandler handleTextMessage wss - > " + wss);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("socket 핸들러 메세지 끝");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("afterConnectionEstablished session : " + session);
		//소켓 연결
		System.out.println("<- socket 연결 -> ");
		super.afterConnectionEstablished(session);
		boolean flag = false;
		String url = session.getUri().toString();
		System.out.println("SocketHandler afterConnectionEstablished url -> "+url);
		String roomNumber = url.split("/chating/")[1];
		int idx = rls.size(); //방의 사이즈를 조사한다.
		System.out.println("SocketHandler afterConnectionEstablished idx -> "+idx);
		if(rls.size() > 0) {
			System.out.println("sockethandler 1");
			for(int i=0; i<rls.size(); i++) {
				String rN = (String) rls.get(i).get("roomNumber");
				System.out.println("SocketHandler afterConnectionEstablished rn -> "+rN);
				if(rN.equals(roomNumber)) {
					System.out.println("sockethandler if문 ");
					flag = true;
					idx = i;
					break;
				}
			}
		}
		
		if(flag) { //존재하는 방이라면 세션만 추가한다.
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		}else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
			System.out.println("map - > " +map);
		}
		
		//세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		System.out.println("소캣 핸들러 afterConnectionEstablished obj -> " + obj);
		session.sendMessage(new TextMessage(obj.toJSONString()));
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		//소켓 종료
		System.out.println("afterConnectionClosed  session : " + session);
				if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
					for(int i=0; i<rls.size(); i++) {
						rls.get(i).remove(session.getId());
					}
				}
				super.afterConnectionClosed(session, status);
			}
	//json형태의 문자열을 파라미터로 받아서 SimpleJson의 파서를 활용하여 JSONObject로 파싱처리를 해주는 함수입니다.
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	
	
	
	
	
	
	
	
	

}
