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
import com.hzcf.flagship.constant.MoneyConstants;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.model.ThreasholdSet;
import com.hzcf.flagship.service.IndexService;

/**
 *<dl>
 *<dt>类名：MoneyThresholdSetController.java</dt>
 *<dd>描述: 理财阈值设置</dd> 
 *<dd>创建时间： 2017年5月17日 下午4:01:50</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月17日 下午4:01:50    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/moneyThreshold")
public class MoneyThresholdSetController extends BaseController {


	@Autowired
	private IndexService indexService;
	
	public  static Map<String,Object> map = new HashMap<String,Object>();
	
	/**
	 * 跳转到理财阈值列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/set")
	public  String toThresholdSet(Model model) {
		 model.addAttribute("a","money");
		//得到理财/融资指标表 内容
	    List<Index> list = indexService.selectByExample(new IndexExample());
	    DecimalFormat df = new DecimalFormat("###################.###########");
	    for (Index index : list) {
				map.put(index.getCode(),df.format(index.getValue()));
		}
		return "app/data/moneyThresholdSet";
	}
	
	/**
	 * 
	 * Description: 理财 理财日指标列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/pageList")
	public Map<String,Object> pageList(Model model) {
	   
	    JSONObject json = new JSONObject();
	    json.put("total", 10);
	    //理财融资日指标
	    ThreasholdSet  dayDeeds = new ThreasholdSet();//理财日业绩
	    dayDeeds.setCodeName(MoneyConstants.MA1);
	    dayDeeds.setAssociatedData(MoneyConstants.MA2);
	    dayDeeds.setRedLineRules(MoneyConstants.MA3+map.get(TemplateConstants.M1)+"%");
	    dayDeeds.setYellowLineRules(MoneyConstants.MA4_1+map.get(TemplateConstants.M1)+"%"+MoneyConstants.MA4_2+map.get(TemplateConstants.M2)+"%");
	    dayDeeds.setBludLineRules(MoneyConstants.MA5+map.get(TemplateConstants.M2)+"%");
	   
	    //从列表页面 带到修改页面 用于对应和回显
	    dayDeeds.setBeginCodeName(TemplateConstants.M1);
	    dayDeeds.setEndCodeName(TemplateConstants.M2);
	    dayDeeds.setBeginValue((String.valueOf((map.get(TemplateConstants.M1)))));
	    dayDeeds.setEndValue((String.valueOf((map.get(TemplateConstants.M2)))));
	    dayDeeds.setRedMath(MoneyConstants.R1_R);
	    dayDeeds.setYellowMathOne(MoneyConstants.R1_Y1);
	    dayDeeds.setYellowMathTwo(MoneyConstants.R1_Y2);
	    dayDeeds.setBlueMath(MoneyConstants.R1_B);
	    
	    ThreasholdSet  aggregateGet = new ThreasholdSet();//理财达成率业绩
	    aggregateGet.setCodeName(MoneyConstants.MB1);
	    aggregateGet.setAssociatedData(MoneyConstants.MB2);
	    aggregateGet.setRedLineRules(MoneyConstants.MB3+map.get(TemplateConstants.M3)+"%");
	    aggregateGet.setYellowLineRules(MoneyConstants.MB4_1+map.get(TemplateConstants.M3)+"%"+MoneyConstants.MB4_2+map.get(TemplateConstants.M4)+"%");
	    aggregateGet.setBludLineRules(MoneyConstants.MB5+map.get(TemplateConstants.M4)+"%");
	   
	    //从列表页面 带到修改页面 用于对应和回显
	    aggregateGet.setBeginCodeName(TemplateConstants.M3);
	    aggregateGet.setEndCodeName(TemplateConstants.M4);
	    aggregateGet.setBeginValue((String.valueOf((map.get(TemplateConstants.M3)))));
	    aggregateGet.setEndValue((String.valueOf((map.get(TemplateConstants.M4)))));
	    aggregateGet.setRedMath(MoneyConstants.R2_R);
	    aggregateGet.setYellowMathOne(MoneyConstants.R2_Y1);
	    aggregateGet.setYellowMathTwo(MoneyConstants.R2_Y2);
	    aggregateGet.setBlueMath(MoneyConstants.R2_B);
	    
	    List<ThreasholdSet> moneyList = new ArrayList<ThreasholdSet>();
	    moneyList.add(dayDeeds);
	    moneyList.add(aggregateGet);
	    json.put("rows", moneyList);
		return json;
	}
	/**
	 * 
	 * Description: 理财理财 月指标列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/pageMonthList")
	public Map<String,Object> pageMonthList(Model model) {
		JSONObject json = new JSONObject();
	    ThreasholdSet  planRate = new ThreasholdSet();//计划达成率
	    planRate.setCodeName(MoneyConstants.MC1);
	    planRate.setAssociatedData(MoneyConstants.MC2);
	    planRate.setRedLineRules(MoneyConstants.MC3+map.get(TemplateConstants.M5)+"%");
	    planRate.setYellowLineRules(map.get(TemplateConstants.M5)+"%"+MoneyConstants.MC4+map.get(TemplateConstants.M6)+"%");
	    planRate.setBludLineRules(MoneyConstants.MC5+map.get(TemplateConstants.M6)+"%");
	   
	    //从列表页面 带到修改页面 用于对应和回显
	    planRate.setBeginCodeName(TemplateConstants.M5);
	    planRate.setEndCodeName(TemplateConstants.M6);
	    planRate.setBeginValue((String.valueOf((map.get(TemplateConstants.M5)))));
	    planRate.setEndValue((String.valueOf((map.get(TemplateConstants.M6)))));
	    planRate.setRedMath(MoneyConstants.R3_R);
	    planRate.setYellowMathOne(MoneyConstants.R3_Y1);
	    planRate.setYellowMathTwo(MoneyConstants.R3_Y2);
	    planRate.setBlueMath(MoneyConstants.R3_B);
	    
	    List<ThreasholdSet> moneyMonthList = new ArrayList<ThreasholdSet>();
	    moneyMonthList.add(planRate);
	    json.put("rows", moneyMonthList);
	    return json;
	}
	/**
	 * 跳转到理财阈值修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(ThreasholdSet threasholdSet ,Model model,HttpServletRequest request) {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		try {
			String redMath = java.net.URLDecoder.decode(request.getParameter("redMath"), "utf-8");
			String yellowMathOne = java.net.URLDecoder.decode(request.getParameter("yellowMathOne"), "utf-8");
			String yellowMathTwo = java.net.URLDecoder.decode(request.getParameter("yellowMathTwo"), "utf-8");
			String blueMath = java.net.URLDecoder.decode(request.getParameter("blueMath"), "utf-8");
			responseMap.put("beginCodeName", threasholdSet.getBeginCodeName());
			responseMap.put("endCodeName", threasholdSet.getEndCodeName());
			responseMap.put("beginValue", threasholdSet.getBeginValue()+"%");
			responseMap.put("endValue", threasholdSet.getEndValue()+"%");
			responseMap.put("redMath", redMath);
			responseMap.put("yellowMathOne", yellowMathOne);
			responseMap.put("yellowMathTwo", yellowMathTwo);
			responseMap.put("blueMath", blueMath);
			model.addAllAttributes(responseMap);
		} catch (Exception e) {
			logger.error(e.getMessage()); 
		}
		
		return "app/data/moneyThresholdSetUpdate";
	}
	
	/**
	 * 理财阈值修改
	 * 
	 * @return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public boolean doUpdate(ThreasholdSet threasholdSet,HttpServletRequest request) {
		try {
			Employee employee = getSystemCurrentUser(request.getSession());
			//修改第一个值
			Index index = new Index();
			String str = threasholdSet.getBeginValue();
			String[] beginValue = str.split("%");
			index.setCode(threasholdSet.getBeginCodeName());
			index.setValue(Double.valueOf(beginValue[0]));
			//得到当前登录用户id
			if(null !=employee ){
				index.setCreatorId(employee.getEmployeeId()); 
			}
			index.setCreateTime(new Date());
			indexService.updateByCode(index);
			//修改第二个值
			Index indexTwo = new Index();
			indexTwo.setCode(threasholdSet.getEndCodeName());
			String strT = threasholdSet.getEndValue();
			String[] endValue = strT.split("%");
			indexTwo.setValue(Double.valueOf(endValue[0]));
			if(null !=employee ){
				indexTwo.setCreatorId(employee.getEmployeeId()); 
			}
			indexTwo.setCreateTime(new Date());
			indexService.updateByCode(indexTwo);
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage()); 
			return false;
		}
	}
}
