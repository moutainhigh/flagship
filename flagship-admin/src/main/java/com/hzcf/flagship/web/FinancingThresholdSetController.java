package com.hzcf.flagship.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.constant.FinancingConstants;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.model.ThreasholdSet;
import com.hzcf.flagship.service.IndexService;


/**
 *<dl>
 *<dt>类名：FinancingThresholdSetController.java</dt>
 *<dd>描述: 融资阈值设置</dd> 
 *<dd>创建时间： 2017年5月18日 下午2:36:30</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月18日 下午2:36:30    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/financeThreshold")
public class FinancingThresholdSetController extends BaseController {


	@Autowired
	private IndexService indexService;
	
	private  Map<String,Object> map = new HashMap<String,Object>();
	
	/**
	 * 跳转到融资阈值列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/set")
	public  String toThresholdSet(Model model) {
		getMoneyAndFinanceList();
		return "app/data/financingThresholdSet";
	}
	
	/**
	 * 
	 * Description: 融资 融资日指标列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/pageList")
	public Map<String,Object> pageList(Model model) {
		  //融资日指标列表
		getMoneyAndFinanceList();
		JSONObject json = new JSONObject();
		json.put("total", 10);
		ThreasholdSet planGet = new ThreasholdSet();// 融资日指标 计划达成率
		planGet.setCodeName(FinancingConstants.FA1);
		planGet.setAssociatedData(FinancingConstants.FA2);
		planGet.setRedLineRules(FinancingConstants.FA3 + map.get(TemplateConstants.F1) + "%");
		planGet.setYellowLineRules(
				map.get(TemplateConstants.F1) + "%" + FinancingConstants.FA4 + map.get(TemplateConstants.F2) + "%");
		planGet.setBludLineRules(FinancingConstants.FA5 + map.get(TemplateConstants.F2) + "%");
		// 从列表页面 带到修改页面 用于对应和回显
		planGet.setBeginCodeName(TemplateConstants.F1);
		planGet.setEndCodeName(TemplateConstants.F2);
		planGet.setBeginValue(String.valueOf(map.get(TemplateConstants.F1)) + "%");
		planGet.setEndValue(String.valueOf(map.get(TemplateConstants.F2)) + "%");
		planGet.setRedMath(FinancingConstants.R1_R);
		planGet.setYellowMathOne(FinancingConstants.R1_Y1);
		planGet.setYellowMathTwo(FinancingConstants.R1_Y2);
		planGet.setBlueMath(FinancingConstants.R1_B);

		ThreasholdSet peopleAvg = new ThreasholdSet();// 融资日指标 人均进件
		peopleAvg.setCodeName(FinancingConstants.FB1);
		peopleAvg.setAssociatedData(FinancingConstants.FB2);
		peopleAvg.setRedLineRules(FinancingConstants.FB3 + map.get(TemplateConstants.F3));
		peopleAvg.setYellowLineRules(FinancingConstants.FB4_1 + map.get(TemplateConstants.F3) + FinancingConstants.FB4_2
				+ map.get(TemplateConstants.F4));
		peopleAvg.setBludLineRules(FinancingConstants.FB5 + map.get(TemplateConstants.F4));
		// 从列表页面 带到修改页面 用于对应和回显
		peopleAvg.setBeginCodeName(TemplateConstants.F3);
		peopleAvg.setEndCodeName(TemplateConstants.F4);
		peopleAvg.setBeginValue(String.valueOf(map.get(TemplateConstants.F3)));
		peopleAvg.setEndValue(String.valueOf(map.get(TemplateConstants.F4)));
		peopleAvg.setRedMath(FinancingConstants.R2_R);
		peopleAvg.setYellowMathOne(FinancingConstants.R2_Y1);
		peopleAvg.setYellowMathTwo(FinancingConstants.R2_Y2);
		peopleAvg.setBlueMath(FinancingConstants.R2_B);

		ThreasholdSet confirmRate = new ThreasholdSet();// 融资日指标 批核率
		confirmRate.setCodeName(FinancingConstants.FC1);
		confirmRate.setAssociatedData(FinancingConstants.FC2);
		confirmRate.setRedLineRules(FinancingConstants.FC3_1 + map.get(TemplateConstants.F5) + "%"
				+ FinancingConstants.FC3_2 + map.get(TemplateConstants.F8) + "%");
		confirmRate.setYellowLineRules(map.get(TemplateConstants.F5) + "%" + FinancingConstants.FC4_1
				+ map.get(TemplateConstants.F6) + "%" + FinancingConstants.FC4_2 + map.get(TemplateConstants.F7) + "%"
				+ FinancingConstants.FC4_3 + map.get(TemplateConstants.F8) + "%");
		confirmRate.setBludLineRules(
				map.get(TemplateConstants.F6) + "%" + FinancingConstants.FC5 + map.get(TemplateConstants.F7) + "%");
		// 从列表页面 带到修改页面 用于对应和回显
		confirmRate.setBeginCodeName(TemplateConstants.F5);
		confirmRate.setEndCodeName(TemplateConstants.F6);
		confirmRate.setThreeCodeName(TemplateConstants.F7);
		confirmRate.setFourCodeName(TemplateConstants.F8);
		confirmRate.setBeginValue(String.valueOf(map.get(TemplateConstants.F5)) + "%");
		confirmRate.setEndValue(String.valueOf(map.get(TemplateConstants.F6)) + "%");
		confirmRate.setThreeValue(String.valueOf(map.get(TemplateConstants.F7)) + "%");
		confirmRate.setFourValue(String.valueOf(map.get(TemplateConstants.F8)) + "%");
		confirmRate.setRedMath(FinancingConstants.R3_R1);
		confirmRate.setRedMathTwo(FinancingConstants.R3_R2);
		confirmRate.setYellowMathOne(FinancingConstants.R3_Y1);
		confirmRate.setYellowMathTwo(FinancingConstants.R3_Y2);
		confirmRate.setYellowMathThree(FinancingConstants.R3_Y3);
		confirmRate.setYellowMathFour(FinancingConstants.R3_Y4);
		confirmRate.setBlueMath(FinancingConstants.R3_B1);
		confirmRate.setBlueMathTwo(FinancingConstants.R3_B2);

		ThreasholdSet confirmAvg = new ThreasholdSet();// 融资日指标 批核件均
		confirmAvg.setCodeName(FinancingConstants.FD1);
		confirmAvg.setAssociatedData(FinancingConstants.FD2);
		confirmAvg.setRedLineRules(FinancingConstants.FD3_1 + map.get(TemplateConstants.F9) + "万"
				+ FinancingConstants.FD3_2 + map.get(TemplateConstants.F12) + "万");// 待定
		confirmAvg.setYellowLineRules(map.get(TemplateConstants.F9) + "万" + FinancingConstants.FD4_1
				+ map.get(TemplateConstants.F10) + "万" + FinancingConstants.FD4_2 + map.get(TemplateConstants.F11) + "万"
				+ FinancingConstants.FD4_3 + map.get(TemplateConstants.F12) + "万");
		confirmAvg.setBludLineRules(
				map.get(TemplateConstants.F10) + "万" + FinancingConstants.FC5 + map.get(TemplateConstants.F11) + "万");
		// 从列表页面 带到修改页面 用于对应和回显
		confirmAvg.setBeginCodeName(TemplateConstants.F9);
		confirmAvg.setEndCodeName(TemplateConstants.F10);
		confirmAvg.setThreeCodeName(TemplateConstants.F11);
		confirmAvg.setFourCodeName(TemplateConstants.F12);
		confirmAvg.setBeginValue(String.valueOf(map.get(TemplateConstants.F9)) + "万");
		confirmAvg.setEndValue(String.valueOf(map.get(TemplateConstants.F10)) + "万");
		confirmAvg.setThreeValue(String.valueOf(map.get(TemplateConstants.F11)) + "万");
		confirmAvg.setFourValue(String.valueOf(map.get(TemplateConstants.F12)) + "万");
		confirmAvg.setRedMath(FinancingConstants.R4_R1);
		confirmAvg.setRedMathTwo(FinancingConstants.R4_R2);
		confirmAvg.setYellowMathOne(FinancingConstants.R4_Y1);
		confirmAvg.setYellowMathTwo(FinancingConstants.R4_Y2);
		confirmAvg.setYellowMathThree(FinancingConstants.R4_Y3);
		confirmAvg.setYellowMathFour(FinancingConstants.R4_Y4);
		confirmAvg.setBlueMath(FinancingConstants.R4_B1);
		confirmAvg.setBlueMathTwo(FinancingConstants.R4_B2);

		List<ThreasholdSet> moneyList = new ArrayList<ThreasholdSet>();
		moneyList.add(planGet);
		moneyList.add(peopleAvg);
		moneyList.add(confirmRate);
		moneyList.add(confirmAvg);
		json.put("rows", moneyList);
		return json;
	}
	/**
	 * 
	 * Description: 融资 融资 月指标列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/pageMonthList")
	public Map<String,Object> financePageMonthList(Model model) {
		
		getMoneyAndFinanceList();
		JSONObject json = new JSONObject();
	    ThreasholdSet  planGet = new ThreasholdSet();//计划达成率
	    planGet.setCodeName(FinancingConstants.FE1);
	    planGet.setAssociatedData(FinancingConstants.FE2);
	    planGet.setRedLineRules(FinancingConstants.FE3+map.get(TemplateConstants.F13)+"%");
	    planGet.setYellowLineRules(map.get(TemplateConstants.F13)+"%"+FinancingConstants.FE4+map.get(TemplateConstants.F14)+"%");
	    planGet.setBludLineRules(FinancingConstants.FE5+map.get(TemplateConstants.F14)+"%");
	    //从列表页面 带到修改页面 用于对应和回显
	    planGet.setBeginCodeName(TemplateConstants.F13);
	    planGet.setEndCodeName(TemplateConstants.F14);
	    planGet.setBeginValue(String.valueOf(map.get(TemplateConstants.F13))+"%");
	    planGet.setEndValue(String.valueOf(map.get(TemplateConstants.F14))+"%");
	    planGet.setRedMath(FinancingConstants.R5_R);
	    planGet.setYellowMathOne(FinancingConstants.R1_Y1);
	    planGet.setRedMath(FinancingConstants.R5_R);
	    planGet.setYellowMathOne(FinancingConstants.R5_Y1);
	    planGet.setYellowMathTwo(FinancingConstants.R5_Y2);
	    planGet.setBlueMath(FinancingConstants.R5_B);
	    
	    ThreasholdSet backRate = new ThreasholdSet();//C-M1回款率
	    backRate.setCodeName(FinancingConstants.FF1);
	    backRate.setAssociatedData(FinancingConstants.FF2);
	    backRate.setRedLineRules(FinancingConstants.FF3+map.get(TemplateConstants.F15)+"%");
	    backRate.setYellowLineRules(map.get(TemplateConstants.F15)+"%"+FinancingConstants.FF4+map.get(TemplateConstants.F16)+"%");
	    backRate.setBludLineRules(FinancingConstants.FF5+map.get(TemplateConstants.F16)+"%");
	    //从列表页面 带到修改页面 用于对应和回显
	    backRate.setBeginCodeName(TemplateConstants.F15);
	    backRate.setEndCodeName(TemplateConstants.F16);
	    backRate.setThreeCodeName(TemplateConstants.F31);//最小值的code
	    backRate.setBeginValue(map.get(TemplateConstants.F15)+"%");
	    backRate.setEndValue(map.get(TemplateConstants.F16)+"%");
	    backRate.setThreeValue(map.get(TemplateConstants.F31)+"%");//最小值
	    backRate.setRedMath(FinancingConstants.R6_R);
	    backRate.setYellowMathOne(FinancingConstants.R6_Y1);
	    backRate.setYellowMathTwo(FinancingConstants.R6_Y2);
	    backRate.setBlueMath(FinancingConstants.R6_B);
	    
	    ThreasholdSet  loseRate = new ThreasholdSet();//年化新增损失率
	    loseRate.setCodeName(FinancingConstants.FG1);
	    loseRate.setAssociatedData(FinancingConstants.FG2);
	    loseRate.setRedLineRules(FinancingConstants.FG3+map.get(TemplateConstants.F17)+"%");
	    loseRate.setYellowLineRules(map.get(TemplateConstants.F18)+"%"+FinancingConstants.FG4+map.get(TemplateConstants.F17)+"%");
	    loseRate.setBludLineRules(FinancingConstants.FG5+map.get(TemplateConstants.F18)+"%");
	    //从列表页面 带到修改页面 用于对应和回显
	    loseRate.setBeginCodeName(TemplateConstants.F17);
	    loseRate.setEndCodeName(TemplateConstants.F18);
	    loseRate.setFourCodeName(TemplateConstants.F32);//最大值的code
	    loseRate.setBeginValue(String.valueOf(map.get(TemplateConstants.F17)+"%"));
	    loseRate.setEndValue(String.valueOf(map.get(TemplateConstants.F18)+"%"));
	    loseRate.setFourValue(String.valueOf(map.get(TemplateConstants.F32)+"%"));//最大值
	    loseRate.setRedMath(FinancingConstants.R7_R);
	    loseRate.setYellowMathOne(FinancingConstants.R7_Y1);
	    loseRate.setYellowMathTwo(FinancingConstants.R7_Y2);
	    loseRate.setBlueMath(FinancingConstants.R7_B);
	    
	    ThreasholdSet  capacityAvg = new ThreasholdSet();//全员人均产能
	    capacityAvg.setCodeName(FinancingConstants.FH1);
	    capacityAvg.setAssociatedData(FinancingConstants.FH2);
	    capacityAvg.setRedLineRules(FinancingConstants.FH3+map.get(TemplateConstants.F19)+"万");
	    capacityAvg.setYellowLineRules(map.get(TemplateConstants.F19)+"万"+FinancingConstants.FH4+map.get(TemplateConstants.F20)+"万");
	    capacityAvg.setBludLineRules(FinancingConstants.FH5+map.get(TemplateConstants.F20)+"万");
	    //从列表页面 带到修改页面 用于对应和回显
	    capacityAvg.setBeginCodeName(TemplateConstants.F19);
	    capacityAvg.setEndCodeName(TemplateConstants.F20);
	    capacityAvg.setBeginValue(String.valueOf(map.get(TemplateConstants.F19))+"万");
	    capacityAvg.setEndValue(String.valueOf(map.get(TemplateConstants.F20))+"万");
	    capacityAvg.setRedMath(FinancingConstants.R8_R);
	    capacityAvg.setYellowMathOne(FinancingConstants.R8_Y1);
	    capacityAvg.setYellowMathTwo(FinancingConstants.R8_Y2);
	    capacityAvg.setBlueMath(FinancingConstants.R8_B);
	    
	    ThreasholdSet  counselorAvg = new ThreasholdSet();//咨询师人均产能
	    counselorAvg.setCodeName(FinancingConstants.FI1);
	    counselorAvg.setAssociatedData(FinancingConstants.FI2);
	    counselorAvg.setRedLineRules(FinancingConstants.FI3+map.get(TemplateConstants.F21)+"万");
	    counselorAvg.setYellowLineRules(map.get(TemplateConstants.F21)+"万"+FinancingConstants.FE4+map.get(TemplateConstants.F22)+"万");
	    counselorAvg.setBludLineRules(FinancingConstants.FI5+map.get(TemplateConstants.F22)+"万");
	    //从列表页面 带到修改页面 用于对应和回显
	    counselorAvg.setBeginCodeName(TemplateConstants.F21);
	    counselorAvg.setEndCodeName(TemplateConstants.F22);
	    counselorAvg.setBeginValue(String.valueOf(map.get(TemplateConstants.F21))+"万");
	    counselorAvg.setEndValue(String.valueOf(map.get(TemplateConstants.F22))+"万");
	    counselorAvg.setRedMath(FinancingConstants.R9_R);
	    counselorAvg.setYellowMathOne(FinancingConstants.R9_Y1);
	    counselorAvg.setYellowMathTwo(FinancingConstants.R9_Y2);
	    counselorAvg.setBlueMath(FinancingConstants.R9_B);
	    
	    ThreasholdSet  confirmRate = new ThreasholdSet();//批核率
	    confirmRate.setCodeName(FinancingConstants.FJ1);
	    confirmRate.setAssociatedData(FinancingConstants.FJ2);
	    confirmRate.setRedLineRules(FinancingConstants.FJ3_1+map.get(TemplateConstants.F23)+"%"+FinancingConstants.FJ3_2+map.get(TemplateConstants.F26)+"%");
	    confirmRate.setYellowLineRules(map.get(TemplateConstants.F23)+"%"+FinancingConstants.FJ4_1+FinancingConstants.FJ4_2+map.get(TemplateConstants.F25)+"%"+FinancingConstants.FJ4_3+map.get(TemplateConstants.F26)+"%");
	    confirmRate.setBludLineRules(map.get(TemplateConstants.F24)+"%"+FinancingConstants.FJ5+map.get(TemplateConstants.F25)+"%");
	    //从列表页面 带到修改页面 用于对应和回显
	    confirmRate.setBeginCodeName(TemplateConstants.F23);
	    confirmRate.setEndCodeName(TemplateConstants.F24);
	    confirmRate.setThreeCodeName(TemplateConstants.F25);
	    confirmRate.setFourCodeName(TemplateConstants.F26);
	    confirmRate.setBeginValue(String.valueOf(map.get(TemplateConstants.F23))+"%");
	    confirmRate.setEndValue(String.valueOf(map.get(TemplateConstants.F24))+"%");
	    confirmRate.setThreeValue(String.valueOf(map.get(TemplateConstants.F25))+"%");
	    confirmRate.setFourValue(String.valueOf(map.get(TemplateConstants.F26))+"%");
	    confirmRate.setRedMath(FinancingConstants.R10_R1);
	    confirmRate.setRedMathTwo(FinancingConstants.R10_R2);
	    confirmRate.setYellowMathOne(FinancingConstants.R10_Y1);
	    confirmRate.setYellowMathTwo(FinancingConstants.R10_Y2);
	    confirmRate.setYellowMathThree(FinancingConstants.R10_Y3);
	    confirmRate.setYellowMathFour(FinancingConstants.R10_Y4);
	    confirmRate.setBlueMath(FinancingConstants.R10_B1);
	    confirmRate.setBlueMathTwo(FinancingConstants.R10_B2);
	    
	    ThreasholdSet  confirmCount = new ThreasholdSet();//批核件均
	    confirmCount.setCodeName(FinancingConstants.FK1);
	    confirmCount.setAssociatedData(FinancingConstants.FK2);
	    confirmCount.setRedLineRules(FinancingConstants.FK3_1+map.get(TemplateConstants.F27)+"万"+FinancingConstants.FK3_2+map.get(TemplateConstants.F30)+"万");
	    confirmCount.setYellowLineRules(map.get(TemplateConstants.F27)+"万"+FinancingConstants.FK4_1+map.get(TemplateConstants.F28)+"万"+FinancingConstants.FK4_2+map.get(TemplateConstants.F29)+"万"+FinancingConstants.FK4_3+map.get(TemplateConstants.F30)+"万");
	    confirmCount.setBludLineRules(map.get(TemplateConstants.F28)+"万"+FinancingConstants.FK5+map.get(TemplateConstants.F29)+"万");
	    //从列表页面 带到修改页面 用于对应和回显
	    confirmCount.setBeginCodeName(TemplateConstants.F27);
	    confirmCount.setEndCodeName(TemplateConstants.F28);
	    confirmCount.setThreeCodeName(TemplateConstants.F29);
	    confirmCount.setFourCodeName(TemplateConstants.F30);
	    confirmCount.setBeginValue(String.valueOf(map.get(TemplateConstants.F27))+"万");
	    confirmCount.setEndValue(String.valueOf(map.get(TemplateConstants.F28))+"万");
	    confirmCount.setThreeValue(String.valueOf(map.get(TemplateConstants.F29))+"万");
	    confirmCount.setFourValue(String.valueOf(map.get(TemplateConstants.F30))+"万");
	    confirmCount.setRedMath(FinancingConstants.R11_R1);
	    confirmCount.setRedMathTwo(FinancingConstants.R11_R2);
	    confirmCount.setYellowMathOne(FinancingConstants.R11_Y1);
	    confirmCount.setYellowMathTwo(FinancingConstants.R11_Y2);
	    confirmCount.setYellowMathThree(FinancingConstants.R11_Y3);
	    confirmCount.setYellowMathFour(FinancingConstants.R11_Y4);
	    confirmCount.setBlueMath(FinancingConstants.R11_B1);
	    confirmCount.setBlueMathTwo(FinancingConstants.R11_B2);
	    
	    List<ThreasholdSet> moneyMonthList = new ArrayList<ThreasholdSet>();
	    moneyMonthList.add(planGet);
	    moneyMonthList.add(backRate);
	    moneyMonthList.add(loseRate);
	    moneyMonthList.add(capacityAvg);
	    moneyMonthList.add(counselorAvg);
	    moneyMonthList.add(confirmRate);
	    moneyMonthList.add(confirmCount);
	    
	    json.put("rows", moneyMonthList);
	    return json;
	}
	/**
	 * 跳转到融资阈值修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public String financeToUpdate(ThreasholdSet threasholdSet ,Model model,HttpServletRequest request) {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		try {
			String redMath = new String(request.getParameter("redMath").getBytes("ISO-8859-1"),"utf-8");
			String redMathTwo = new String(request.getParameter("redMathTwo").getBytes("ISO-8859-1"),"utf-8");
			String blueMath = new String(request.getParameter("blueMath").getBytes("ISO-8859-1"),"utf-8");
			String blueMathTwo = new String(request.getParameter("blueMathTwo").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathOne = new String(request.getParameter("yellowMathOne").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathTwo = new String(request.getParameter("yellowMathTwo").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathThree = new String(request.getParameter("yellowMathThree").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathFour = new String(request.getParameter("yellowMathFour").getBytes("ISO-8859-1"),"utf-8");
			String beginValue = new String(request.getParameter("beginValue").getBytes("ISO-8859-1"),"utf-8");
			String endValue = new String(request.getParameter("endValue").getBytes("ISO-8859-1"),"utf-8");
			String threeValue = new String(request.getParameter("threeValue").getBytes("ISO-8859-1"),"utf-8");
			String fourValue = new String(request.getParameter("fourValue").getBytes("ISO-8859-1"),"utf-8");
			responseMap.put("beginCodeName", threasholdSet.getBeginCodeName());
			responseMap.put("endCodeName", threasholdSet.getEndCodeName());
			responseMap.put("threeCodeName", threasholdSet.getThreeCodeName());
			responseMap.put("fourCodeName", threasholdSet.getFourCodeName());
			responseMap.put("beginValue", beginValue);
			responseMap.put("endValue", endValue);
			responseMap.put("threeValue", threeValue);
			responseMap.put("fourValue", fourValue);
			
			responseMap.put("redMath", redMath);
			responseMap.put("redMathTwo", redMathTwo);
			responseMap.put("yellowMathOne", yellowMathOne);
			responseMap.put("yellowMathTwo", yellowMathTwo);
			responseMap.put("yellowMathThree", yellowMathThree);
			responseMap.put("yellowMathFour", yellowMathFour);
			responseMap.put("blueMath", blueMath);
			responseMap.put("blueMathTwo", blueMathTwo);
			model.addAllAttributes(responseMap);
		} catch (Exception e) {
			logger.error(e.getMessage()); 
		}
		
		return "app/data/financingThresholdSetUpdate";
	}
	
	/**
	 * 融资阈值修改
	 * 
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public boolean financeDoUpdate(ThreasholdSet threasholdSet,HttpServletRequest request) {
		
		try {
			   
			// 得到当前用户的id
			Integer id = null;
			Employee employee = getSystemCurrentUser(request.getSession());
			if (null != employee) {
				id = employee.getEmployeeId();
			}
			// 修改第一个值
			Index index = new Index();
			String beginCodeName = threasholdSet.getBeginCodeName();
			String str = threasholdSet.getBeginValue();
			if (str.contains("%")) {
				String[] beginValue = str.split("%");
				index.setValue(Double.valueOf(beginValue[0]));
			} else if (str.contains("万")) {
				String[] beginValue = str.split("万");
				index.setValue(Double.valueOf(beginValue[0]));
			} else {
				index.setValue(Double.valueOf(str));
			}
			index.setCreatorId(id);
			index.setCode(beginCodeName);
			index.setCreateTime(new Date());
			indexService.updateByCode(index);

			// 修改第二个值
			Index indexTwo = new Index();
			String endCodeName = threasholdSet.getEndCodeName();
			String strT = threasholdSet.getEndValue();
			if (strT.contains("%")) {
				String[] endValue = strT.split("%");
				indexTwo.setValue(Double.valueOf(endValue[0]));
			} else if (strT.contains("万")) {
				String[] endValue = strT.split("万");
				indexTwo.setValue(Double.valueOf(endValue[0]));
			} else {
				indexTwo.setValue(Double.valueOf(strT));
			}
			indexTwo.setCreatorId(id);
			indexTwo.setCode(endCodeName);
			indexTwo.setCreateTime(new Date());
			indexService.updateByCode(indexTwo);

			// 修改C-M1回款率 最小值
			if (null != threasholdSet.getThreeValue()) {
				Index indexThree = new Index();
				String strThree = threasholdSet.getThreeValue();
				String[] threeValue = strThree.split("%");
				indexThree.setCode(threasholdSet.getThreeCodeName());
				indexThree.setValue(Double.valueOf(threeValue[0]));
				indexThree.setCreatorId(id);
				indexThree.setCreateTime(new Date());
				indexService.updateByCode(indexThree);
			}
			// 修改年化新增损失率 最大值
			if (null != threasholdSet.getFourValue()) {
				Index indexFour = new Index();
				String strFour= threasholdSet.getFourValue();
				String[] fourValue = strFour.split("%");
				indexFour.setCode(threasholdSet.getFourCodeName());
				indexFour.setValue(Double.valueOf(fourValue[0]));
				indexFour.setCreatorId(id);
				indexFour.setCreateTime(new Date());
				indexService.updateByCode(indexFour);
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 跳转到融资阈值修改 多值页面
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdateMoreValue")
	public String financeToUpdateMoreValue(ThreasholdSet threasholdSet ,Model model,HttpServletRequest request) {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		try {
			String redMath = new String(request.getParameter("redMath").getBytes("ISO-8859-1"),"utf-8");
			String redMathTwo = new String(request.getParameter("redMathTwo").getBytes("ISO-8859-1"),"utf-8");
			String blueMath = new String(request.getParameter("blueMath").getBytes("ISO-8859-1"),"utf-8");
			String blueMathTwo = new String(request.getParameter("blueMathTwo").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathOne = new String(request.getParameter("yellowMathOne").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathTwo = new String(request.getParameter("yellowMathTwo").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathThree = new String(request.getParameter("yellowMathThree").getBytes("ISO-8859-1"),"utf-8");
			String yellowMathFour = new String(request.getParameter("yellowMathFour").getBytes("ISO-8859-1"),"utf-8");

			String beginValue = new String(request.getParameter("beginValue").getBytes("ISO-8859-1"),"utf-8");
			String endValue = new String(request.getParameter("endValue").getBytes("ISO-8859-1"),"utf-8");
			String threeValue = new String(request.getParameter("threeValue").getBytes("ISO-8859-1"),"utf-8");
			String fourValue = new String(request.getParameter("fourValue").getBytes("ISO-8859-1"),"utf-8");
			responseMap.put("beginCodeName", threasholdSet.getBeginCodeName());
			responseMap.put("endCodeName", threasholdSet.getEndCodeName());
			responseMap.put("threeCodeName", threasholdSet.getThreeCodeName());
			responseMap.put("fourCodeName", threasholdSet.getFourCodeName());
			responseMap.put("beginValue", beginValue);
			responseMap.put("endValue", endValue);
			responseMap.put("threeValue", threeValue);
			responseMap.put("fourValue", fourValue);
			
			responseMap.put("redMath", redMath);
			responseMap.put("redMathTwo", redMathTwo);
			responseMap.put("yellowMathOne", yellowMathOne);
			responseMap.put("yellowMathTwo", yellowMathTwo);
			responseMap.put("yellowMathThree", yellowMathThree);
			responseMap.put("yellowMathFour", yellowMathFour);
			responseMap.put("blueMath", blueMath);
			responseMap.put("blueMathTwo", blueMathTwo);
			model.addAllAttributes(responseMap);
		} catch (Exception e) {
			logger.error(e.getMessage()); 
		}
		
		return "app/data/financingUpdateMoreValue";
	}
	/**
	 * 融资阈值修改 多值修改
	 * 
	 * @return
	 */
	@RequestMapping("/doUpdateMoreValue")
	@ResponseBody
	public boolean doUpdateMoreValue(ThreasholdSet threasholdSet,HttpServletRequest request) {
		
		try {
			   
			// 得到当前用户的id
			Integer id = null;
			Employee employee = getSystemCurrentUser(request.getSession());
			if (null != employee) {
				id = employee.getEmployeeId();
			}
			// 修改第一个值
			Index index = new Index();
			String beginCodeName = threasholdSet.getBeginCodeName();
			String str = threasholdSet.getBeginValue();
			if (str.contains("%")) {
				String[] beginValue = str.split("%");
				index.setValue(Double.valueOf(beginValue[0]));
			} else if (str.contains("万")) {
				String[] beginValue = str.split("万");
				index.setValue(Double.valueOf(beginValue[0]));
			} else {
				index.setValue(Double.valueOf(str));
			}
			index.setCreatorId(id);
			index.setCode(beginCodeName);
			index.setCreateTime(new Date());
			indexService.updateByCode(index);

			// 修改第二个值
			Index indexTwo = new Index();
			String endCodeName = threasholdSet.getEndCodeName();
			String strT = threasholdSet.getEndValue();
			if (strT.contains("%")) {
				String[] endValue = strT.split("%");
				indexTwo.setValue(Double.valueOf(endValue[0]));
			} else if (strT.contains("万")) {
				String[] endValue = strT.split("万");
				indexTwo.setValue(Double.valueOf(endValue[0]));
			} else {
				indexTwo.setValue(Double.valueOf(strT));
			}
			indexTwo.setCreatorId(id);
			indexTwo.setCode(endCodeName);
			indexTwo.setCreateTime(new Date());
			indexService.updateByCode(indexTwo);

			// 修改第三个值
			Index indexThree = new Index();
			String threeCodeName = threasholdSet.getThreeCodeName();
			String strThree = threasholdSet.getThreeValue();
			if (strThree.contains("%")) {
				String[] threeValue = strThree.split("%");
				indexThree.setValue(Double.valueOf(threeValue[0]));
			} else if (strThree.contains("万")) {
				String[] threeValue = strThree.split("万");
				indexThree.setValue(Double.valueOf(threeValue[0]));
			} else {
				indexThree.setValue(Double.valueOf(strThree));
			}
			indexThree.setCreatorId(id);
			indexThree.setCode(threeCodeName);
			indexThree.setCreateTime(new Date());
			indexService.updateByCode(indexThree);
				
			// 修改第四个值
			Index indexFour = new Index();
			String fourCodeName = threasholdSet.getFourCodeName();
			String strFour = threasholdSet.getFourValue();
			if (strFour.contains("%")) {
				String[] fourValue = strFour.split("%");
				indexFour.setValue(Double.valueOf(fourValue[0]));
			} else if (strFour.contains("万")) {
				String[] fourValue = strFour.split("万");
				indexFour.setValue(Double.valueOf(fourValue[0]));
			} else {
				indexFour.setValue(Double.valueOf(strFour));
			}
			indexFour.setCreatorId(id);
			indexFour.setCode(fourCodeName);
			indexFour.setCreateTime(new Date());
			indexService.updateByCode(indexFour);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 得到理财/融资指标表 内容
	 */
    public void getMoneyAndFinanceList(){
	    List<Index> list = indexService.selectByExample(new IndexExample());
	    DecimalFormat df = new DecimalFormat("###################.###########");
	    for (Index index : list) {
				map.put(index.getCode(),df.format(index.getValue()));
		}
   }
}
