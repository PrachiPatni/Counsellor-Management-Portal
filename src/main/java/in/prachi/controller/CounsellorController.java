package in.prachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.prachi.binding.DashboardResponse;
import in.prachi.entity.Counsellor;
import in.prachi.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	//to display login page
	@Autowired
	private CounsellorService counsellorSvc;
	@GetMapping("/")
	public String index(Model model) {
		
	   //to send binding objects
	   model.addAttribute("counsellor", new Counsellor());//with this counsellor object we will bind login form
		return "loginView";
	}
	@GetMapping("/logout")
	public String logout( HttpServletRequest req,Model model) {
		
	HttpSession session= req.getSession(false);
	session.invalidate();
		return "redirect:/";
		
	}
	@PostMapping("/login")
	public String handleLogin( Counsellor c,HttpServletRequest req,  Model model) {
	    Counsellor obj = counsellorSvc.loginCheck(c.getEmail(), c.getPwd());
	    
	    if (obj == null) {
	        model.addAttribute("errMsg", "Invalid Credentials");
	        return "loginView";
	    }
	    //Important lines to build dash board you cannot add the inquiry not display inquiry or cannot filter the inquiry
	    	HttpSession session = req.getSession(true);//to give the new session /if false it will give the existing session
	    session.setAttribute("CID", obj.getCid());
	    
	    
	    
	    // Consider storing only necessary information in the session or cookies, not sensitive data like password
	    // Also, ensure that 'dashboardView' is a proper mapping or return the appropriate view name
	    return "redirect:/dashboard";
	}

	
	
	@GetMapping("/dashboard")
	public String buildDashboard( HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("CID");
		//typecasting
		Integer cid= (Integer)obj;
		//get data for dashboard
		DashboardResponse dashboardInfo = counsellorSvc.getDashboardInfo(cid);
		model.addAttribute("dashboard", dashboardInfo);
		return "dashboardView";
	}
	
	//to display registration page
	@GetMapping("/register")
	public String regPage(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "registerView";//will load the empty registration page
	}
	//to handle registration
	
	@PostMapping("/register")//post reg for form submission
	public String handleRegistration(Counsellor c, Model model) {
		
		String msg = counsellorSvc.saveCounsellor(c);
		model.addAttribute("msg",msg);
		return "registerView";//when you submit the form this method will call the service method service method will return a message, may be success or a failure
	}
	
	//recover password method 
	@GetMapping("/forgot-pwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwdView";//no binding is used for it 
	}
	@GetMapping("/recover-pwd")
	public String recoverPwd(@RequestParam String email, Model model) {
		boolean status = counsellorSvc.recoverPwd(email);
		if(status) {
			model.addAttribute("smsg","Pwd sent to your email");
		}else {
			model.addAttribute("errmsg","Invalid Email");
		}
		return "forgotPwdView";
	}
}
