package phonebook;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("*************************************************");
		System.out.println("*		전화번호 관리 프로그램		*");
		System.out.println("*************************************************");
		
		Scanner sc = new Scanner(System.in);
		
		boolean q = true;
		String str = "1. 리스트	2. 등록	3. 삭제	4. 검색	5.종료\n-------------------------------------------"
				+ "------\n>메뉴번호: ";
		
		while (q) {
			System.out.print(str);
			
			int num = sc.nextInt();
			
			switch (num) {
			
			case 1:
				System.out.println("<1. 리스트>");
				Work.getList();
				break;
				
			case 2:
				System.out.println("<2. 등록>");
				System.out.print(">이름: ");
				String name = sc.next();
				
				System.out.print(">휴대전화: ");
				String hp = sc.next();
				
				System.out.print(">회사전화: ");
				String tel = sc.next();
				
				PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
				
				try {
					Work.append(vo);
				} catch (Exception e) {
					System.err.println("다시 시도해주세용~");
				}
				
				break;
				
			case 3:
				System.out.println("<3. 삭제>");
				System.out.print(">번호: ");
				
				int deleteNum = sc.nextInt();
				
				try {
					Work.delete(deleteNum);
				} catch (Exception e) {
					System.err.println("다시 시도해주세용~");
				}
				
				
				break;
				
			case 4:
				System.out.println("<4. 검색>");
				System.out.print(">이름: ");
				String searchName = sc.next();

				try {
					Work.search(searchName);
				} catch (Exception e) {
					System.err.println("찾으시는 내용은 존재하지 않습니다.");
				}
				
				break;	
				
			case 5: q = false;
				System.out.println("*************************************************");
				System.out.println("*			감사합니다			*");
				System.out.println("*************************************************");
				break;
			
			default :
				System.out.println("[다시입력해주세요]\n");
				break;
			}
		}
		
		sc.close();

	}

}
