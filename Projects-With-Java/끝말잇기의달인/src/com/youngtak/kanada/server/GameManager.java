package com.youngtak.kanada.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {
	private List<String> words;
	private Socket currentPlayer;
	private boolean isStarted;
	private List<Socket> playerList;
	private HashMap<Socket, Integer> scores;
	private HashMap<Socket, String> nickNameMap;
	private Shared shared;
	private Dict dict;
	
	
	enum GAME_CMD {
		/* 현재 순서를 지정한다 */ SET_TURN,
		/* 입력된 말을 공유한다 */ ECHO,
		/* 게임 종료 */ GAME_ENDED
	}
	
	public GameManager() {
	}
	
	public GameManager(Shared shared, List<Socket> playerList, HashMap<Socket, String> nickNameMap) {
		this.shared = shared;
		words = new ArrayList<String>();
		scores = new HashMap<Socket, Integer>();
		for (Socket s : playerList) {
			scores.put(s, 0);
		}
		this.nickNameMap = nickNameMap;
		this.currentPlayer = playerList.get(0);
		this.playerList = playerList;
		this.dict = new Dict();
	}
	
	public void startTurn() {
		if (!isStarted) {
			isStarted = true;
		}
		broadcast(GAME_CMD.SET_TURN, nickNameMap.get(currentPlayer));
	}
	
	public void broadcast(GAME_CMD cmd, String arg) {
		switch(cmd) {
		case SET_TURN:
			shared.broadcast("%SET_TURN" + arg);
			shared.broadcast(arg + " 플레이어의 차례입니다.");
			break;
		case ECHO:
			shared.broadcast(arg);
			break;
		case GAME_ENDED:
			shared.broadcast("%GAME_ENDED");
			break;
		default:
			break;
		}
	}
	
	public void broadcast(GAME_CMD cmd) {
		broadcast(cmd, "");
	}
	
	public synchronized boolean isExistedWord(String word) {
		boolean result = false;
		
		for (String s : words) {
			if (s.equals(word)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public synchronized boolean isChained(String newWord) {
		if (words.size() == 0) {
			return true;
		} else {
			String lastestWord = words.get(words.size() - 1);
			
			if (newWord.charAt(0) == lastestWord.charAt(lastestWord.length() - 1)) {
				return true;
			} else {			
				return false;
			}	
		}
	}
	
	public synchronized boolean defExist(String word) {
		String def = dict.getWordDef(word);
		if (def.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized void listen(Socket from, String msg) {
		if (currentPlayer == from) {
			if (msg.equals("!기권")) {
				endGame();
				return;
			}
			
			if (defExist(msg) && !isExistedWord(msg) && isChained(msg)) {
				scores.put(from, scores.get(from) + 1);
				words.add(msg);
				try {
					currentPlayer = playerList.get(playerList.indexOf(from) + 1);
				} catch (Exception e) {
					currentPlayer = playerList.get(0);
				}
				broadcast(GAME_CMD.ECHO, nickNameMap.get(from) + "(정답!!)" + " > " + msg);
				startTurn();
			} else {
				broadcast(GAME_CMD.ECHO, nickNameMap.get(from) + "(오답)" + " > " + msg);
			}
		} else {
			broadcast(GAME_CMD.ECHO, nickNameMap.get(from) + "(현재 차례 아님)" + " > " + msg);
			return;
		}
	}
	
	public synchronized void endGame() {
		broadcast(GAME_CMD.GAME_ENDED);
		broadcast(GAME_CMD.ECHO, "성적 발표");
		for (Socket s : scores.keySet()) {
			broadcast(GAME_CMD.ECHO, nickNameMap.get(s) + ": " + scores.get(s) + "점");
		}
		shared.gameEnded();
	}
}
