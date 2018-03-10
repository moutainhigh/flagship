package com.hzcf.flagship.model;

public class ThreasholdSet {

	private String codeName; // 指标名称

	private String associatedData; // 关联数据

	private String redLineRules;// 红线规则

	private String yellowLineRules;// 黄线规则

	private String bludLineRules;// 绿线规则

	private String beginCodeName;// 每条数据的第一个code

	private String endCodeName;// 每条数据第二个code
	
	private String threeCodeName;// 某条数据第三个code

	private String fourCodeName;// 某条数据第四个code

	private String beginValue;// 每条数据第一个value

	private String endValue;// 每条数据第二个value
	
	private String threeValue;// 某条数据第三个value
	
	private String fourValue;//某条数据的第四个value
	
	private String redMath; //红线计算公式
	
	private String redMathTwo;//红线计算公式二
	
	private String yellowMathOne; //黄线计算公式一
	
	private String yellowMathTwo;//黄线计算公式二
	
	private String yellowMathThree;//黄线计算公式三
	
	private String yellowMathFour;//黄线计算公式四
	
	private String blueMath;//绿线计算公式
	
	private String blueMathTwo;//绿线计算公式二

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName == null ? null : codeName.trim();
	}

	public String getAssociatedData() {
		return associatedData;
	}

	public void setAssociatedData(String associatedData) {
		this.associatedData = associatedData == null ? null : associatedData.trim();
	}

	public String getRedLineRules() {
		return redLineRules;
	}

	public void setRedLineRules(String redLineRules) {
		this.redLineRules = redLineRules == null ? null : redLineRules.trim();
	}

	public String getYellowLineRules() {
		return yellowLineRules;
	}

	public void setYellowLineRules(String yellowLineRules) {
		this.yellowLineRules = yellowLineRules == null ? null : yellowLineRules.trim();
	}

	public String getBludLineRules() {
		return bludLineRules;
	}

	public void setBludLineRules(String bludLineRules) {
		this.bludLineRules = bludLineRules == null ? null : bludLineRules.trim();
	}

	public String getBeginCodeName() {
		return beginCodeName;
	}

	public void setBeginCodeName(String beginCodeName) {
		this.beginCodeName = beginCodeName;
	}


	public String getEndCodeName() {
		return endCodeName;
	}

	public void setEndCodeName(String endCodeName) {
		this.endCodeName = endCodeName;
	}

	public String getBeginValue() {
		return beginValue;
	}

	public void setBeginValue(String beginValue) {
		this.beginValue = beginValue;
	}

	public String getEndValue() {
		return endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public String getRedMath() {
		return redMath;
	}

	public void setRedMath(String redMath) {
		this.redMath = redMath;
	}

	public String getYellowMathOne() {
		return yellowMathOne;
	}

	public void setYellowMathOne(String yellowMathOne) {
		this.yellowMathOne = yellowMathOne;
	}

	public String getYellowMathTwo() {
		return yellowMathTwo;
	}

	public void setYellowMathTwo(String yellowMathTwo) {
		this.yellowMathTwo = yellowMathTwo;
	}

	public String getBlueMath() {
		return blueMath;
	}

	public void setBlueMath(String blueMath) {
		this.blueMath = blueMath;
	}

	public String getRedMathTwo() {
		return redMathTwo;
	}

	public void setRedMathTwo(String redMathTwo) {
		this.redMathTwo = redMathTwo;
	}

	public String getYellowMathThree() {
		return yellowMathThree;
	}

	public void setYellowMathThree(String yellowMathThree) {
		this.yellowMathThree = yellowMathThree;
	}

	public String getThreeValue() {
		return threeValue;
	}

	public void setThreeValue(String threeValue) {
		this.threeValue = threeValue;
	}

	public String getYellowMathFour() {
		return yellowMathFour;
	}

	public void setYellowMathFour(String yellowMathFour) {
		this.yellowMathFour = yellowMathFour;
	}

	public String getBlueMathTwo() {
		return blueMathTwo;
	}

	public void setBlueMathTwo(String blueMathTwo) {
		this.blueMathTwo = blueMathTwo;
	}

	public String getThreeCodeName() {
		return threeCodeName;
	}

	public void setThreeCodeName(String threeCodeName) {
		this.threeCodeName = threeCodeName;
	}

	public String getFourCodeName() {
		return fourCodeName;
	}

	public void setFourCodeName(String fourCodeName) {
		this.fourCodeName = fourCodeName;
	}

	public String getFourValue() {
		return fourValue;
	}

	public void setFourValue(String fourValue) {
		this.fourValue = fourValue;
	}


}