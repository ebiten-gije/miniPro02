package phonebook;

import java.util.List;

public interface PhoneBookDAO {

	public List <PhoneBookVO> getList();
	
	public boolean insert (PhoneBookVO phoneBookVo);
	
	public boolean delete (Integer id);
	
	public PhoneBookVO get(String inStr);
	
	
}
