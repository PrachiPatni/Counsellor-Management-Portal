package in.prachi.service;

import java.util.List;

import in.prachi.binding.SearchCriteria;
import in.prachi.entity.StudentEnq;

public interface EnquiryService {
 //store the inquiry in the table
	public boolean addEnq(StudentEnq se);
	
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);
}
