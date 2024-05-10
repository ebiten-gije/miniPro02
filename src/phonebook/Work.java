package phonebook;

import java.util.Iterator;
import java.util.List;

public class Work {
	
	public static void delete(int deleteNum) {
		
		PhoneBookDAO dao = new PhoneBookDAOImplOracle();
		
		boolean suc = dao.delete(deleteNum);
		
		System.out.println(suc ? "[삭제되었습니다.]" : "[실패하였습니다.]");
	}
	
	public static void append(PhoneBookVO vo) {
		
		PhoneBookDAO dao = new PhoneBookDAOImplOracle();
		
		boolean suc = dao.insert(vo);
		
		System.out.println(suc ? "[등록되었습니다.]" : "[실패하였습니다.]");
		
	}
	
	public static void search(String searchName) {
		
		PhoneBookDAO dao = new PhoneBookDAOImplOracle();
		
		PhoneBookVO vo = dao.get(searchName);
		
		System.out.printf("%d\t%s\t%s\t%s%n", vo.getId(), vo.getName(), vo.getHp(), vo.getTel());
	}

	public static void getList() {
		
		PhoneBookDAO dao = new PhoneBookDAOImplOracle();
		
		List <PhoneBookVO> list = dao.getList();
		
		if(list.size() > 0) {
			Iterator<PhoneBookVO> it = list.iterator();
			
			while (it.hasNext()) {
				PhoneBookVO vo = it.next();
				System.out.printf("%d\t%s\t%s\t%s%n", vo.getId(), vo.getName(), vo.getHp(), vo.getTel());
			}
			
		} else {
			System.out.println("데이터가 없습니다.");
		}

	}

}
