package J20210310;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController extends Controller {
	// extends Controller >> 상속개념
	
	ArrayList<Article> articles = new ArrayList<Article>(); // 게시물 저장소
	ArrayList<Reply> replies = new ArrayList<Reply>(); // 댓글 저장소
	
	int num = 4; // 1씩 증가. 시퀀스
	int rnum = 1;
	
	int readCmd;
	
	// 생성자
	public ArticleController() {		
		// 테스트 데이터 3개 만들기
		Article a1 = new Article(1, "안녕하세요", "안녕하세요", "2021.03.08", "익명", 30);
		Article a2 = new Article(2, "반갑습니다", "반갑습니다", "2021.03.08", "익명", 20);
		Article a3 = new Article(3, "안녕2", "안녕2", "2021.03.08", "익명", 10);
		
		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
	}
	
	// ============================================================
	
	public void doCommand(String command){
		
		if (command.equals("add")) {
			
			// 로그인 여부 체크
			if(isLogin()) {				
				addArticle(); // top 다운방식				
			}
			

		} else if (command.equals("list")) {
			printArticleList(articles);

		} else if (command.equals("update")) {
			updateArticle();
			printArticleList(articles);

		} else if (command.equals("delete")) {
			deleteArticle();
			printArticleList(articles);
			
		} else if (command.equals("search")) {
			searchArticle();
			
		} else if (command.equals("read")) {			
			if(isLogin()) {				
				readArticle();
			}
		}
	}
	
	// ==============================================================
	// 상세보기 게시물, 댓글 출력 메서드
		public void printArticle(Article article) {
			System.out.println("===========" + article.num + "번 째 게시물=========");
			System.out.println("번호 : " +  article.num);
			System.out.println("제목 : " +  article.title);
			System.out.println("내용 : " +  article.body);
			System.out.println("등록날짜 : " +  article.regDate);
			System.out.println("조회수 : " +  article.hit);
			System.out.println("작성자 : " +  article.nickname);
			System.out.println("==============================");
			
			for(int i = 0; i < replies.size(); i++) {
				System.out.println("=========================");
				Reply reply = replies.get(i);
				if(article.num == reply.articleNum) {
					System.out.println("내용 :" + reply.body);
					System.out.println("작성자 :" + reply.nickname);
					System.out.println("작성일 :" + reply.regDate);
					System.out.println("=========================");
				}
			}
		}
		
		public void searchArticle() {
			// 제목으로 검색
			// 게시물 제목, 검색키워드
			// 게시물제목.contains(검색키워드)
			System.out.println("검색 항목을 선택해주세요 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자) : ");
			int searchFlag = sc.nextInt();
			System.out.println("검색 키워드를 입력해주세요 : ");
			String keyword = sc.next();
			
			ArrayList<Article> searchedArticles = new ArrayList<Article>();
			
			String searchTarget = "";
			
			for(int i = 0; i < articles.size(); i++) {
				Article article = articles.get(i);
				
				if(searchFlag == 1) {
					searchTarget = article.title; // 게시물 제목
				} else if(searchFlag == 2) {
					searchTarget = article.body;
				} else if(searchFlag == 3) {
					searchTarget = article.title + article.body;
				} else if(searchFlag == 4) {
					searchTarget = article.nickname;
				}
				
				if(searchTarget.contains(keyword)) {
					searchedArticles.add(article);
				}
			}
			
			printArticleList(searchedArticles);
			
		}
		//=================================================================
		// 게시물 추가 메서드
		public void addArticle() {
			
			// 번호 저장 -> 중복 되어서는 안되는 데이터
			
			System.out.println("제목을 입력해주세요 : ");
			String title = sc.next();
			System.out.println("내용을 입력해주세요 : ");
			String body = sc.next();
			
			// Article class를 통해 알려준 게시물 저장소 만들어줘
					
			Article a1 = new Article(num, title, body, "2021.03.08", "익명", 0);
			articles.add(a1);
					
			num++;
			
			System.out.println("게시물이 등록되었습니다.");
		}

		//=================================================================
		// 전체 게시물을 받아서 출력해주는 메서드
		public void printArticleList(ArrayList<Article> articles) {
			System.out.println("======================");
			
			for(int i = 0; i < articles.size(); i++) {
				
				Article a1 = articles.get(i);
				System.out.println("번호 : " + a1.num);
				System.out.println("제목 : " + a1.title);
				System.out.println("등록일 : " + a1.regDate);
				System.out.println("작성자 : " + a1.nickname);
				System.out.println("조회수 : " + a1.hit);
				
				System.out.println("======================");
			}
		}
		
		
		//=================================================================
		// 특정 게시물의 index를 찾아주는 메서드
		public int getIndexOfArticle(int targetNum) {

			int targetIndex = -1;
			
			for(int i = 0; i < articles.size(); i++) {
				
				Article article = articles.get(i);
				
				if(article.num == targetNum) {
					targetIndex = i;
				}
			}
			
			return targetIndex; 
		}
		//=================================================================
		public void readArticle() {
			System.out.println("상세보기할 게시물 번호를 입력해주세요 : ");
			int readNum = sc.nextInt();
			
			int readIndex = getIndexOfArticle(readNum);
				
			if(readIndex == -1) {
				System.out.println("게시물이 존재하지 않습니다. : ");
			} else {
				Article article = articles.get(readIndex);
				printArticle(article);
				readProcess(article);
			}
		}
	 
		
		
		
		//=================================================================
		// 댓글 기능 메서드
		public void readProcess(Article article) {
			while(true)	{
				System.out.println("상세보기 기능을 선택해주세요(1. 댓글 기능, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
						
			try {
				readCmd = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("상세보기 기능을 선택해주세요(1. 댓글 기능, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			}
			
						
			if(readCmd == 1) {
				System.out.println("댓글 내용을 입력해주세요 : ");
				String rbody = sc.nextLine();
				
				Reply reply = new Reply(rnum, article.num, rbody, "익명", "2021.03.10");
				replies.add(reply);
				rnum++;
				
				System.out.println("댓글 등록되었습니다.");
				
				printArticle(article);
				} else if(readCmd == 2) {
					System.out.println("좋아요");
				} else if(readCmd == 3) {
					System.out.println("수정");
				} else if(readCmd == 4) {
					System.out.println("삭제");
				} else if(readCmd == 5) {
					System.out.println("목록으로");
					break;
				}
			}
				
		}
			
		
		//=================================================================
		
		public void updateArticle() {
			System.out.println("몇번 데이터를 수정하시겠습니까? :");
			int targetNum = sc.nextInt();
			
			// 호출한 쪽으로 넣어주는 방법 : 인수 - 매개변수
			
			int targetIndex = getIndexOfArticle(targetNum); // 메서드를 사용(호출) 
					
			if(targetIndex == -1) {
				System.out.println("없는 게시물 번호입니다.!!");
				
			} else {
				// 수정 코드
				System.out.println("새제목 : ");
				String newTitle = sc.next();
				System.out.println("새내용 : ");
				String newBody = sc.next();
				
				Article article = articles.get(targetIndex);
				article.title = newTitle;
				article.body = newBody;
					
			}
		}
		
		//=================================================================
		public void deleteArticle() {

			System.out.println("몇번 데이터를 삭제하시겠습니까? :");
			int targetNum = sc.nextInt();
			int targetIndex = getIndexOfArticle(targetNum);
			
			if(targetIndex == -1) {
				System.out.println("없는 게시물 번호입니다.!!");
			} else {
				// 삭제 코드
				articles.remove(targetIndex);			
			}
			
		}
		//=================================================================		
	
}
