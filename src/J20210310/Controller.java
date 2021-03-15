package J20210310;

import java.util.Scanner;

public class Controller {

	Scanner sc = new Scanner(System.in);

	// ==============================================================
	public boolean isLogin() {
		if (BoardApp.loginedMember == null) {

			System.out.println("로그인 후 이용해주세요.");
			return false;

		} else {
			return true;
		}
	}

	// ===============================================================

}
