package J20210310;

//설계도
public class Article {
	
	
	// static이 붙은 자원(변수, 메서드)을 class(설계도/틀)에 고정시켜서
	// 인스턴스(클래스 복사본)들이 공유하기 위한 것.
	int num;
	String title; 
	String body;
	String regDate;
	String nickname;
	int hit;
	
	// 생성자 - 메서드의 일종, 특수한 메서드
	// 1. 무조건 class명과 이름이 동이한 메서드
	// 2. 리턴타입이 없다.
	// 3. 인스턴스(객체, 복사본) 생성 시 반드시 한번 실행
	// 4. 기본 생성자는 생략 가능
	
	public Article(int aNum, String aTitle, String aBody, String aRegDate, String aNickname, int aHit) {
		num = aNum; // 대입 연산
		title = aTitle;
		body = aBody;
		regDate = aRegDate;
		nickname = aNickname;
		hit = aHit;
	}
}