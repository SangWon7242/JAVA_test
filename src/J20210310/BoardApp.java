package J20210310;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {

	// 모듈(유사한 목적의 기능들을 모아놓은 것)별 분리
	// 사용하는 데이터 구조에 맞게 분리 - 메서드는 자료구조에 의존

	Scanner sc = new Scanner(System.in);

	static Member loginedMember = null;
	MemberController mc = new MemberController();
	ArticleController ac = new ArticleController();

	public void BoardApp() {

		while (true) {

			if (loginedMember == null) {
				System.out.println("명령어를 입력해주세요 :");
			} else {
				System.out.println("명령어를 입력해주세요 [" + loginedMember.loginId + "(" + loginedMember.nickname + ")]");
			}

			String command = sc.next();

			if (command.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;

			} else if (command.equals("help")) {
				printHelp();
			}

			ac.doCommand(command);
			mc.doCommand(command);
		}
	}

	// =================================================================
	public void printHelp() {
		System.out.println("========================");
		System.out.println("help : 도움말");
		System.out.println("add : 데이터 추가");
		System.out.println("read : 데이터 조회");
		System.out.println("update : 데이터 수정");
		System.out.println("delete : 데이터 삭제");
		System.out.println("exit : 프로그램 종료");
		System.out.println("========================");
	}

}
