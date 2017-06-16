package cn.appinfodb.controller;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.service.BackendUser.BackendUserService;
import cn.appinfodb.service.DataDictionary.DataDictionaryService;
import cn.appinfodb.tools.Constants;
@RequestMapping("/BackendUser")
@Controller
public class BackendUserController {
	@Autowired
	BackendUserService backendUserService;
	@Autowired
	DataDictionaryService dataDictionaryService;
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "backendlogin";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam String userCode,
									@RequestParam String userPassword,
									HttpSession session){
		BackendUser  backdendUser=backendUserService.fountBackendUserBy(userCode, userPassword);
			if (backdendUser!=null) {
				session.setAttribute(Constants.BACKENDUSER_SESSION,backdendUser);
				int id=backdendUser.getUserType();
				DataDictionary  dataDictionary=dataDictionaryService.selectDataById(id);
				System.out.println(dataDictionary.getValueName());
				session.setAttribute(Constants.data,dataDictionary);
				return "redirect:/BackendUser/dologin";
			}else{
				throw new RuntimeException("用户名或密码错误");
			}
	}
	@RequestMapping(value="/dologin")
	public String dologin(HttpSession session){
		if (session.getAttribute(Constants.BACKENDUSER_SESSION)==null) {
			return "redirect:/BackendUser/login"; 
		}else{
			return "backend/main";
		}
	}

	//注销
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "backendlogin";
	}
	
}
