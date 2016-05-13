package com.xdidian.keryhu.propertyregister.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.xdidian.keryhu.propertyregister.client.UserAccountClient;
import com.xdidian.keryhu.propertyregister.domain.HostProperty;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;
import com.xdidian.keryhu.propertyregister.service.ConverterUtil;
import com.xdidian.keryhu.propertyregister.service.UserService;
import com.xdidian.keryhu.propertyregister.stream.EmailActivatedProducer;
import com.xdidian.keryhu.propertyregister.stream.SaveProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MainRest {

	private final HostProperty hostProperty;
	
	private final ConverterUtil converterUtil;

	private final UserAccountClient userClient;
	
	private final UserService userService;

	private final SaveProducer saveproducer;
	
	private final EmailActivatedProducer emailActivatedProducer;
	
	/**
	 * 
	* @Title: createUser
	* @Description: TODO(验证输入信息的合法性的方法只方法逻辑层，在此，user－account里面不做判断，以为就算黑客恶意注册了，
	* 那么他也做不了什么事情，因为接下来需要邮件验证，手机验证，营业执照验证等，而且admin会定期检测账户
	* 在注册完后，页面自动导航到 result 页面，并且附带了 2个带有email信息的链接)
	* @param @param propertyForm
	* @param @return    设定文件
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/property/register")
	public ModelAndView  createUser(@RequestBody PropertyForm propertyForm,RedirectAttributes attr) {
		
		log.info("正在调用property－register service 的 save get方法。");
		
		//验证输入信息的合法性
		userService.vlidateBeforSave(propertyForm);
			
		//发送email激活的message出去。
		emailActivatedProducer.send(converterUtil.propertyFormToEmailActivatedDto.apply(propertyForm));
				
		//用户注册完，发送dto具体信息的message 给 user-account,用于保存
		saveproducer.send(converterUtil.propertyFormToRegisterDto.apply(propertyForm));
		
		ModelAndView mav=new ModelAndView();
		
		//重新发送的url
		String resend=new StringBuffer(hostProperty.getHostName())
				.append(":8080/email-activate/email/resend?email=")
				.append(propertyForm.getEmail())
				.toString();		
				
		//重新注册的url
		String reregister=new StringBuffer(hostProperty.getHostName())
				.append(":8080/email-activate/email/reregister?email=")
				.append(propertyForm.getEmail())
				.toString();	
		//注册完后，导航到的页面
		
		String redirectUrl=new StringBuffer("redirect:")
				.append(hostProperty.getHostName())
				.append(":8080/register/result")
				.toString();
		attr.addAttribute("resend", resend);
		attr.addAttribute("reregister", reregister);
		mav.setViewName(redirectUrl);
		return mav;
		
	}
	
	
	/**
	 * 
	* @Title: isEmailExist
	* @Description: TODO(用于前台物业公司注册时，查询登陆的email是否存在于数据库，此为调用的后台接口)
	* @param @param email  需要被查询的email
	* @param @return    设定文件 返回的是一个json对象 Map对象，， 如果存在此email，则返回true，否则false
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/isEmailExist",method=RequestMethod.GET)
	public ResponseEntity<?> isEmailExist(@RequestParam("email") String email){
		log.info("查到的email 是否存在于 数据库 ：{} ",userClient.isEmailExist(email));
		return ResponseEntity.ok(userClient.isEmailExist(email));
	}
	
	
	/**
	 * 
	* @Title: isPhoneExist
	* @Description: TODO(用于前台物业公司注册时，查询登陆的phone是否存在于数据库，此为调用的后台接口)
	* @param @param phone  需要被调用的phone
	* @param @return    设定文件   如果存在此email，则返回true，否则false
	* @return ResponseEntity<?>    返回类型
	* @throws
	 */
	@RequestMapping(value="/query/isPhoneExist",method=RequestMethod.GET)
	public ResponseEntity<?> isPhoneExist(@RequestParam("phone") String phone){
		log.info("查到的手机号是否存在于数据库： {}",userClient.isPhoneExist(phone));
		return ResponseEntity.ok(userClient.isPhoneExist(phone));
	}
	
	
	/**
	 * 
	* @Title: isComponyNameExist
	* @Description: TODO(查询数据库中是否存在此公司名字)
	* @param @param companyName  需要被查询的公司名字参数
	* @param @return    设定文件   返回数据库中是否存在此公司名字
	* @return ResponseEntity<?>    返回类型  ResponseEntity<map json>
	* @throws
	 */
	@RequestMapping(method=RequestMethod.GET,value="/query/isCompanyNameExist")
    public ResponseEntity<?> isCompanyNameExist(@RequestParam(value="", required=true) String companyName){
		
	    log.info("查的公司名字是否在数据库中：{} ",userClient.isCompanyNameExist(companyName));
		return ResponseEntity.ok(userClient.isCompanyNameExist(companyName));
	}  
	
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping( "/admin")
	public String admin(){
		return "property-register this is admin role page ";
	}
	
	


}
