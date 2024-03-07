package in.prachi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.prachi.binding.SearchCriteria;
import in.prachi.entity.StudentEnq;
import in.prachi.repo.StudentEnqRepo;
@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	
	private StudentEnqRepo srepo;
	@Override
	public boolean addEnq(StudentEnq se) {
		StudentEnq savedEnq = srepo.save(se);
		
		return savedEnq.getEnqId()!= null;
		
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria sc) {
	    StudentEnq enq = new StudentEnq();
	    // setting cid
	    enq.setCid(cid);

	    // If mode selected, add to query
	    if (sc.getClassMode() != null && !sc.getClassMode().isEmpty()) {
	        enq.setClassMode(sc.getClassMode());
	    }

	    // If courseName selected, add to query
	    if (sc.getCourseName() != null && !sc.getCourseName().isEmpty()) {
	        enq.setCourseName(sc.getCourseName());
	    }

	    // If enqStatus selected, add to query
	    if (sc.getEnqStatus() != null && !sc.getEnqStatus().isEmpty()) {
	        enq.setEnqStatus(sc.getEnqStatus());
	    }

	    Example<StudentEnq> of = Example.of(enq);
	    List<StudentEnq> enquiries = srepo.findAll(of);
	    return enquiries;
	}

	}


