package com.hzcf.flagship.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws ParseException {
	Map<String, Object> result = getMonthAndRecordDateList("2018-03");
		System.out.println("list:"+result.get("list"));
		List<LinkedHashMap<String, Object>> recordList; 
		recordList =  (List<LinkedHashMap<String, Object>>) result.get("tableList");
		String startDate = (String) recordList.get(recordList.size()-1).get("recordDate");
		String endDate =  (String) recordList.get(0).get("recordDate");
		System.out.println("tableList:"+result.get("tableList"));
		System.out.println("startDate:"+ startDate);
		System.out.println("endDate:"+ endDate);
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar startCal = Calendar.getInstance();
		Date startParse = df.parse(startDate);
		startCal.setTime(startParse);
		
		
		Calendar endCal = Calendar.getInstance();
		Date endParse = df.parse(endDate);
		endCal.setTime(endParse);
		
        int startYear = startCal.get(Calendar.YEAR);
        int endMonth = endCal.get(Calendar.MONTH )+1;
        String dateResult;
        if(endMonth == 1){
			dateResult = startYear+ "年"+"1"+"月";
		}else{
			dateResult = startYear+ "年"+"1"+"-"+endMonth+"月";
		}
        System.out.println(dateResult);
	}

	public  static  Map<String, Object> getMonthAndRecordDateList(String date) throws ParseException{
			
			 Map<String,Object> map = new HashMap<String,Object>();
			 List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
			 List<LinkedHashMap<String,Object>> tableList = new ArrayList<LinkedHashMap<String,Object>>();

			 DateFormat df = new SimpleDateFormat("yyyy-MM");
			 DateFormat dfs = new SimpleDateFormat("yyyyMM");
			 
			Calendar cal = Calendar.getInstance();
			int y = Integer.valueOf(date.split("-")[0]);
			int m = Integer.valueOf(date.split("-")[1]);
			int month = cal.get(Calendar.MONTH )+1;
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			//传入日期是本年本月
			if(year == y && month == m){
				if(day > 26){
					//获取t月近半年
					for (int i = 0; i <6; i++) {
						 LinkedHashMap<String,Object> chartMap = new LinkedHashMap<String,Object>();
						 String recordDate = df.format(cal.getTime())+"-26";
						 chartMap.put("recordDate", recordDate);
						 chartMap.put("month", dfs.format( cal.getTime()));
						 cal.add(Calendar.MONTH,-1);
						 list.add(chartMap);	
					}
					//获取t所在年 t月 到1月
					Calendar tableCal = Calendar.getInstance();
					int tableMonth = tableCal.get(Calendar.MONTH )+1;
					for (int i = 0; i < tableMonth; i++) {
						 LinkedHashMap<String,Object> tableMap = new LinkedHashMap<String,Object>();
						 String recordDate = df.format(tableCal.getTime())+"-26";
						 tableMap.put("recordDate", recordDate);
						 tableMap.put("month", dfs.format( tableCal.getTime()));
						 tableCal.add(Calendar.MONTH,-1);
						 tableList.add(tableMap);	
					}
					map.put("list", list);
					map.put("tableList", tableList);
				}else{
					//获取（t-1）月近半年
					Calendar lastCal = Calendar.getInstance();
					for (int i = 0; i <6; i++) {
						 LinkedHashMap<String,Object> chartMap = new LinkedHashMap<String,Object>();
						 lastCal.add(Calendar.MONTH,-1);
						 String recordDate = df.format(lastCal.getTime())+"-26";
						 chartMap.put("recordDate", recordDate);
						 chartMap.put("month", dfs.format(lastCal.getTime()));
						 list.add(chartMap);	
					}
					
					//获取（t-1）月到1月
					Calendar lastTableCal = Calendar.getInstance();
					lastTableCal.add(Calendar.MONTH,-1);
					int lastTableMonth = lastTableCal.get(Calendar.MONTH )+1;
					for (int i = 0; i < lastTableMonth; i++) {
						 LinkedHashMap<String,Object> tableMap = new LinkedHashMap<String,Object>();
						 String recordDate = df.format(lastTableCal.getTime())+"-26";
						 tableMap.put("recordDate", recordDate);
						 tableMap.put("month", dfs.format( lastTableCal.getTime()));
						 lastTableCal.add(Calendar.MONTH,-1);
						 tableList.add(tableMap);
					}
					map.put("list", list);
					map.put("tableList", tableList);
				}
				//传入日期非本月
			}else{
				System.out.println("传入日期不是本月");
				Calendar now = Calendar.getInstance();
				Date parse = df.parse(date);
		        now.setTime(parse);
				//获取t月近半年
				for (int i = 0; i < 6; i++) {
					 LinkedHashMap<String,Object> chartMap = new LinkedHashMap<String,Object>();
					 String recordDate = df.format(now.getTime())+"-26";
					 chartMap.put("recordDate", recordDate);
					 chartMap.put("month", dfs.format( now.getTime()));
					 now.add(Calendar.MONTH,-1);
					 list.add(chartMap);	
				}
				
				
				//获取t所在年 t月 到1月
				Calendar now2 = Calendar.getInstance();
				Date parse2 = df.parse(date);
		        now2.setTime(parse2);
				int tableMonth2 = now2.get(Calendar.MONTH )+1;
				for (int i = 0; i < tableMonth2; i++) {
					 LinkedHashMap<String,Object> tableMap = new LinkedHashMap<String,Object>();
					 String recordDate = df.format(now2.getTime())+"-26";
					 tableMap.put("recordDate", recordDate);
					 tableMap.put("month", dfs.format(now2.getTime()));
					 now2.add(Calendar.MONTH,-1);
					 tableList.add(tableMap);	
				}
				map.put("list", list);
				map.put("tableList", tableList);
			}
			return map;
	}
}
			