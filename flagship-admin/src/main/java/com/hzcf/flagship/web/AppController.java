package com.hzcf.flagship.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.service.AppPushMessageService;
import com.hzcf.flagship.service.AppUserService;
import com.hzcf.flagship.service.ImageService;
import com.hzcf.flagship.util.Md5Util2;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.RandomNumUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.util.push.PushUtil;

import net.sf.json.JSONArray;

/**
 * 
 *<dl>
 *<dt>类名：AppController.java</dt>
 *<dd>描述: APP用户管理 </dd> 
 *<dd>创建时间： 2017年5月13日 下午2:22:42</dd>
 *<dd>创建人： Caigen</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月13日 下午2:22:42    Caigen       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping(value="/app")
public class AppController extends BaseController{ 
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppPushMessageService appPushMessageService;
	@Autowired
	private ImageService imageService;
	private String androidAppkey = PropertyUtil.getInfo("androidAppkey");
	private String androidAppMasterSecret = PropertyUtil.getInfo("androidAppMasterSecret");
	private String iosAppkey = PropertyUtil.getInfo("iosAppkey");
	private String iosAppMasterSecret = PropertyUtil.getInfo("iosAppMasterSecret");
	private String production_mode = "";
	private String md5_key = "fff2f795bc974b5f88ddf519c238ee4e";
	
	/**
	 * 分页查询页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toAppUserList")
	public String toEmpList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/appuser/appuser_list";
	}
	
	/**
	 * 分页查询数据
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/users")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String name = StringUtil.trim(request.getParameter("name"));// 姓名
			if (StringUtil.isNotBlank(name)) {
				paramsCondition.put("name", name);
			}
			String username = StringUtil.trim(request.getParameter("username"));// 用户名
			if (StringUtil.isNotBlank(username)) {
				paramsCondition.put("username", username);
			}
			String mobile = StringUtil.trim(request.getParameter("mobile"));// 手机号
			if (StringUtil.isNotBlank(mobile)) {
				paramsCondition.put("mobile", mobile);
			}
			 
			PageModel pageModel = appUserService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 获取角色
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRoles")
	public JSONArray getRoles(HttpServletRequest request) {
		List<?> list=appUserService.getRoles();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 客户端用户新增页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/appuser/appuser_add";
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @param session
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doAdd")
	public DataMsg doAdd(HttpServletRequest request, @ModelAttribute AppUser user,HttpSession session,DataMsg dataMsg) {
		int j=appUserService.findNotDelByMobile(user.getMobile());
		if(j>0){
			logger.error("手机号存在");
			dataMsg.setMessageCode("0026");
			return dataMsg;
		}
		
		try {
			//删除存在手机号的用户(状态已置为3或6)
			appUserService.deleteDelUserByMobile(user.getMobile());
			String password = RandomNumUtil.getRandomNum("10");//随机生成环信密码
			String oauths=request.getParameter("oauth");
			String[] oauth = oauths.split(",");
			String[] letter=request.getParameterValues("letter");
			user.setPassword(Md5Util2.md5(user.getPassword(),md5_key));
			user.setStatus("1");
			user.setImPassword(password);
			user.setCreateTime(new Date());
			Integer employeeId = getSystemCurrentUser(session).getEmployeeId();
			user.setCreatorId(employeeId);
			appUserService.insertSelective(user,employeeId,password,oauth,letter);
			dataMsg.setMessageCode("0001");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	
	 
	/**
	 * 客户端用户修改页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toEdit/{id}")
	public String toEdit(@PathVariable Integer id,Model model) {
		Map<String,Object> obj = appUserService.findByUID(id);
		model.addAttribute("user", obj);
		return "app/appuser/appuser_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param user
	 * @param session
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doEdit")
	public DataMsg doEdit(HttpServletRequest request, @ModelAttribute AppUser user,HttpSession session,DataMsg dataMsg){
		try { 
			// 唯一校验
			if(!user.getMobile().equals(request.getParameter("oldmobile"))){
				int j=appUserService.findByMobile(user.getMobile());
				if(j>0){
					logger.error("手机号存在");
					dataMsg.setMessageCode("0026");
					return dataMsg;
				}
			}
			String oldauth=request.getParameter("oldauth");
			String oauths=request.getParameter("oauth");
			String[] oauth = oauths.split(",");
			
			String oldLetter=request.getParameter("oldLetter");
			String[] letter=request.getParameterValues("letter");
			Integer employeeId = getSystemCurrentUser(session).getEmployeeId();
			user.setCreateTime(new Date());
			user.setCreatorId(getSystemCurrentUser(session).getEmployeeId()); 
			// 执行 
			appUserService.updateByPrimaryKeySelective(user,oldLetter,letter,employeeId,oldauth,oauth);  
			dataMsg.setMessageCode("0003");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0004");
		}
		return dataMsg;
	}
	/**
	 * 禁用或启用
	 * @param user
	 * @param session
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/disableOrEnabled")
	public DataMsg disableOrEnabled(@ModelAttribute AppUser user,HttpSession session,DataMsg dataMsg) {
		try { 
			// 执行
			appUserService.updateByPrimaryKeySelective(user,null,null,null,null,null);
			//禁用2 或者 启用1
			Map<String,Object> map = new HashMap<String,Object> ();
			map.put("userId", user.getId());
			if(user.getStatus().equals("2")){
				map.put("status", "2");
				appUserService.updateToUserIdStatus(map);
			}
			if(user.getStatus().equals("1")){
				map.put("status", "1");
				appUserService.updateToUserIdStatusOpen(map);
			}
			
			if(user.getStatus().equals("1")){
				dataMsg.setMessageCode("0008");
			}else if(user.getStatus().equals("0")){
				dataMsg.setMessageCode("0010");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			if(user.getStatus().equals("1")){
				dataMsg.setMessageCode("0009");
			}else if(user.getStatus().equals("0")){
				dataMsg.setMessageCode("0011");
			}
		} finally {
		}
		return dataMsg;
	}
	
	/**
	 * 前往广播页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toBroadCast")
	public String toBroadCast(HttpServletRequest request,HttpServletResponse response) {
		 
		return "app/appuser/appuser_broadCast";
	}
	
	/**
	 * 推送APP广播
	 * @param request
	 * @param user
	 * @param session
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doBroadCast")
	public DataMsg doBroadCast(HttpServletRequest request, @ModelAttribute AppUser user,HttpSession session,DataMsg dataMsg) {
		this.production_mode=PropertyUtil.getInfo("production_mode");
		/*this.androidAppkey=PropertyUtil.getInfo("androidAppkey");
		this.androidAppMasterSecret=PropertyUtil.getInfo("androidAppMasterSecret");
		this.iosAppkey=PropertyUtil.getInfo("iosAppkey");
		this.iosAppMasterSecret=PropertyUtil.getInfo("iosAppMasterSecret");*/
		PushUtil util = new PushUtil(androidAppkey, androidAppMasterSecret, iosAppkey, iosAppMasterSecret, production_mode);
		Integer status = util.broadCast("notification", null, request.getParameter("title"), request.getParameter("content"));
		if (status==200) {
			appPushMessageService.insertBroadcastPushMessage(request.getParameter("title"), request.getParameter("content"));
		}
		dataMsg.setMessageCode("0025");
		return dataMsg;
	}
	
	/**
	 * APP消息推送（非广播）
	 * @param request
	 * @param user
	 * @param session
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doUniBroad")
	public DataMsg doUniBroad(HttpServletRequest request, @ModelAttribute AppUser user,HttpSession session,DataMsg dataMsg) {
		this.production_mode = PropertyUtil.getInfo("production_mode");
		logger.info(request.getParameter("mobiles"));
		String[] rqmobiles = request.getParameter("mobiles").split(";");
		StringBuffer mobiles = new StringBuffer();
		for (int i = 0; i < rqmobiles.length; i++) { 
			mobiles.append(rqmobiles[i].split(",")[1]).append(","); 
		} 
		String reString=mobiles.substring(0, mobiles.lastIndexOf(","));
		logger.info(reString);
		PushUtil util = new PushUtil(androidAppkey, androidAppMasterSecret, iosAppkey, iosAppMasterSecret, production_mode);
		Integer status = util.customCast("PHONE", reString, "notification", null, request.getParameter("title"), request.getParameter("content"));
		if (status==200) {
			appPushMessageService.insertPushMessageByMobiles(reString, request.getParameter("title"), request.getParameter("content"));
		}
		dataMsg.setMessageCode("0025");
		return dataMsg;
	}
	/**
	 * 获取分类
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getSorts")
	public JSONArray getSorts(HttpServletRequest request) {
		List<?> list=appUserService.getSorts();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 获取所有用户
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAllUser")
	public JSONArray getAllUser(HttpServletRequest request,AppUser user) {
		if(null != user.getSortId() && user.getSortId() == 1){
			user.setSortId(null);
		}
		if(null != user.getSortId() && user.getSortId() == 4){
			user.setSortId(0);
		}
		List<?> list=appUserService.getAllUser(user);
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 修改默认头像
	 * @param pictureFile
	 * @param dataMsg
	 * @return
	 */
	@RequestMapping("/imageUpload")  
    @ResponseBody
    public DataMsg imageUpload(@RequestParam("pictureFile") CommonsMultipartFile pictureFile,DataMsg dataMsg){  
    	logger.info("---------------进入修改 默认图片的方法---------------");
    	Image image = new Image();
    	image.setImage(pictureFile.getBytes());
    	image.setCreateTime(new Date());
    	try {
    		imageService.updateImg(image);
    		dataMsg.setMessageCode("0027");
		} catch (Exception e) {
			e.printStackTrace();
			dataMsg.setMessageCode("0028");
		}
		return dataMsg;
    }  
	
	/**
	 * 
	 * Description: 跳转到选择权限的页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 */
	@RequestMapping(value="/toSelectPermission")
	public String toSelectPermission(Model model) {
		return "app/appuser/app_permission_select";
	}
	
	
	/**
	 * 
	 * Description: 查找所有权限
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value="/permissions")
	public List<Map<String, Object>> listTreePermissions(){
		List<Map<String, Object>> permissionLists = appUserService.findPermissionList();
		return permissionLists;
	}
}
