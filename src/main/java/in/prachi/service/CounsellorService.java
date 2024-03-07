package in.prachi.service;

import in.prachi.binding.DashboardResponse;
import in.prachi.entity.Counsellor;

public interface CounsellorService {
  
	public String saveCounsellor(Counsellor c);
	
	public Counsellor loginCheck(String email, String pwd);
	
	public boolean recoverPwd(String email);
	
	public DashboardResponse getDashboardInfo(Integer cid);
}
