package J20210310;

public class Reply {
	
	int num;
	int articleNum;
	String body; // 내용
	String nickname; // 작성자
	String regDate; // 작성일
	
	
	public Reply(int anum, int aArticleNum, String aBody, String aRegDate, String aNickname) {
		
		num = anum;
		articleNum = aArticleNum;
		body = aBody;
		regDate = aRegDate;
		nickname = aNickname;
	}
	
}
