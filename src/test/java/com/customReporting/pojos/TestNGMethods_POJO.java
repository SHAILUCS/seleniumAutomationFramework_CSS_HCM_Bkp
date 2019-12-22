package com.customReporting.pojos;

import com.configData_Util.Util;
import com.customReporting.Test;

public class TestNGMethods_POJO {
	private String desc;
	private String methodName;
	private String status;
	private String group;
	private String start;
	private String end;
	private String elapsed;

	public TestNGMethods_POJO(Test test) {
		this.desc = test.getScenario();
		this.methodName = test.getTestNG_MethodName();
		this.status = test.getStatus();
		this.group = test.getGroup();
		this.start = Util.convertToHHMMSS(test.getStartTime_Scenario());
		this.end = Util.convertToHHMMSS(test.getEndTime_Scenario());
		this.elapsed = test.getExecutionTime_Scenario();
	}
	
	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getElapsed() {
		return elapsed;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getStatus() {
		return status;
	}

	public String getGroup() {
		return group;
	}
}
