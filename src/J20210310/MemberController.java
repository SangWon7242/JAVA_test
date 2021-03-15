package J20210310;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberController extends Controller {
	
	ArrayList<Member> members = new ArrayList<Member>();
	int mnum = 1;
	
	
	
	// ==============================================================
	public void doCommand(String command) {
		if (command.equals("signup")) {
			addMember();
			
		} else if (command.equals("signin")) {
			loginMember();
		
		} else if(command.equals("logout")) {
			if(isLogin()) {				
				doLogout();
			}
			
		}
	}
	
	
	// ===============================================================
	public void doLogout() {
		BoardApp.loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
	}
	
	// ===============================================================
	// 로그인 기능
	
	public void addMember() {
		System.out.println("===회원가입을 진행합니다.===");
		System.out.println("아이디를 입력해주세요 : ");
		String id = sc.next();
		
		System.out.println("비밀번호를 입력해주세요 : ");
		String pw = sc.next();
		
		System.out.println("닉네임을 입력해주세요 : ");
		String nick = sc.next();
		
		Member member = new Member(mnum, id, pw, nick);
		members.add(member);
		mnum++;
		
		System.out.println("===회원가입이 완료 되었습니다.===");
	}
	

	// =========================================================
	// 로그인 메서드

	private boolean doLogin(String inputId, String inputPw) {
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			
			if(member.loginId.equals(inputId) && member.loginPw.equals(inputPw)) {
				
				
				
				BoardApp.loginedMember = member;
				return true;
			}
		}	
		return false;
	}
	
	// =========================================================
	// 회원가입 메서드
	private void loginMember() {
		System.out.println("아이디 : ");
		String inputId = sc.next();
		System.out.println("비밀번호 : ");
		String inputPw = sc.next();
			
		if(doLogin(inputId, inputPw)) {
			System.out.println(BoardApp.loginedMember.nickname + "님 환영합니다!");
		} else {
			System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
		}
			
	}
}
