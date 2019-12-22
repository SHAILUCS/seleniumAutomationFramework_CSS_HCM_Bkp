/**
 * File: JSONManager.java
 * ----------------------------
 * This class provides methods to Read/Write content in json file.
 * This class used org.json.simple API and provides its own simplistic 
 * methods, which are very easy to use and improves the readability of your tests.
 * This class abstracts the internal mechanism of reading writing a json. 
 * To understand the structure of a json file, visit : https://www.json.org/  
 * */

package com.jsonUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.seleniumExceptionHandling.CustomExceptionHandler;

@ThreadSafe
public class JSONManager {

	/**Constants specify what actions are you going to perform on Json*/
	static final String READ="READ INIT";
	
	private String jsonFilePath;
	private JSONParser jsonParser;
	private JSONArray jsonArray;
	private JSONObject jsonObject;
	private JSONObject rootJsonObject;
	private String[] jsonObjHierarchy;

	@Override
	public String toString() {
		return jsonObject.toJSONString();
	}

	/**Getter only to be used by JSONWriter Class, @returns the jsonObject*/
	JSONObject getJsonObject() {
		return jsonObject;
	}
	
	/**
	 * TODO NEED TO DESIGN/CODE THIS METHOD, can not think of anything right now(07 May 2019) may be in near future 
	 *  ................................................................ 
	 * Changes the JSONManager Object pointing to the passed child index,
	 *  which is inside an json Object array.
	 *  This idea is created to achieve multi level read/write inside array 
	 *  of json objects and so on.
	 *  
	 *  example json:
	 *  
	 *  "validationsArr":[
	 *  	{
	 *  		"testData":{
	 *  			"field1":"val1",
	 *  			"field2":"val2"
	 *  		},
	 *  		"message":[
	 *  			"validation message 1",
	 *  			"validation message 2"	
	 *  		]
	 *  	},
	 *  	{
	 *  		"testData":{
	 *  			"field1":"val3",
	 *  			"field2":"val4"
	 *  		},
	 *  		"message":[
	 *  			"validation message 3",
	 *  			"validation message 4"	
	 *  		]
	 *  	}
	 *  ]
	 * @param jsonArrObjName the name of the json Obj Arr
	 * @param childJsonObjIndex the index of Json Object inside the json obj arr
	 * @return reference to JSONManager object 
	 * */
	public JSONManager getChildJSONObject(String jsonArrObjName, int childJsonObjIndex) {
		return this;
	}
	
	/**
	 * Changes the JSONManager Object pointing to the passed child
	 * @return reference to JSONManager object 
	 * */
	public JSONManager getChildJSONObject(String childJsonObjkey) {
		if(jsonObject.containsKey(childJsonObjkey)){
			if(jsonObject.get(childJsonObjkey) instanceof JSONObject){
				jsonObject = (JSONObject) jsonObject.get(childJsonObjkey);
				
				String[] newJsonObjHierarchy = new String[jsonObjHierarchy.length+1];
				for(int i = 0; i<jsonObjHierarchy.length; i++){
					newJsonObjHierarchy[i] = jsonObjHierarchy[i];
				}
				newJsonObjHierarchy[newJsonObjHierarchy.length - 1] = childJsonObjkey;
				this.jsonObjHierarchy = newJsonObjHierarchy;
				init("READ CHILD");
			}else{
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + childJsonObjkey + "' : does not hold a JSON Object as its value, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		}else{
			CustomReporter.report(STATUS.ERROR,
					"Passed key '" + childJsonObjkey + "' : does not exist, in JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
		}
		return this;
	}
	
	/**
	 * Changes the JSONManager Object pointing to the parent
	 * @return reference to JSONManager object 
	 * */
	public JSONManager getParentJSONObject() {
		if(jsonObjHierarchy.length > 0){
				String newJsonObjHeirarchy[] = new String[jsonObjHierarchy.length-1];
				for (int i = 0; i < jsonObjHierarchy.length-1; i++) {
					newJsonObjHeirarchy[i] = jsonObjHierarchy[i];
				}
				this.jsonObjHierarchy = newJsonObjHeirarchy;
				init("READ PARENT");
		}else{
			CustomReporter.report(STATUS.ERROR,
					"You have reached at root object '"
							+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
		}
		return this;
	}
	
	/**Getter only to be used by JSONWriter Class, @returns the rootJsonObject*/
	JSONObject getRootJsonObject() {
		return rootJsonObject;
	}
	
	/**Getter only to be used by JSONWriter Class, @returns the jsonFilePath*/
	String getJsonFilePath() {
		return jsonFilePath;
	}
	
	/**
	 * <pre>
	 * Use this Constructor when you want to access the 
	 * primitive content of root object of json file, 
	 * Lets suppose json file "example.json" has structure like this:
	 *  
	 *  {
	 *  "key1":"val1",
	 *  "key2":"val2"
	 *  }
	 *  
	 *  we want to get the value associated with "key1" object
	 *  
	 * USAGE:
	 * {@code 
	 * 	JSONManager json=new JSONManager("C:/example.json");
	 * 	String str=json.getStr("key1");
	 * }
	 * 
	 * </pre>
	 */
	public JSONManager(String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
		this.jsonObjHierarchy = new String[0];
		init(READ);
	}

	/**
	 * <pre>
	 * Use this Constructor when you want to access the primitive content 
	 * of particular object of json file, 
	 * Lets suppose json file "example.json" has structure like this:
	 *  
	 *  {
	 *  "obj1":{
	 *  	"obj1.1":{
	 *  		"key":"val"
	 *  		}
	 *  	}
	 *  }
	 *  
	 *  we want to get the value associated with "key" object
	 *  
	 * USAGE:
	 * {@code 
	 * 	JSONManager json=new JSONManager("C:/example.json","obj","obj1.1");
	 * 	String str=json.getStr("key");
	 * }
	 * 
	 *  </pre>
	 * */
	public JSONManager(String jsonFilePath, String... jsonObjHeirarchy) {
		this.jsonFilePath = jsonFilePath;
		this.jsonObjHierarchy = jsonObjHeirarchy;
		init(READ);
	}

	/**
	 * Initializing the jsonObject using JSONParser, 
	 * and calling the setJsonObject method to jump 
	 * through the hierarchy of passed json objects
	 * @param readOrWriteMsg just prints proper message in console
	 * */
	void init(String readOrWriteMsg) {
		try {
			jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonFilePath));
			setJsonObject();
			CustomReporter.report(STATUS.INFO, readOrWriteMsg+" JSON: Hierarchy" + Arrays.toString(jsonObjHierarchy)
			+ "<br/><br/>File["+jsonFilePath+"]"
			+ "<br/><br/> Content<span style='font-size: smaller;'> <code class='prettyprint'>" + jsonObject.toJSONString() + "</code></span>");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			CustomReporter.report(STATUS.ERROR,
					"The system cannot find the JSON file specified [" + jsonFilePath + "]");
		}

	}

	/**
	 * setting the rootJsonObject and also 
	 * jumping to last json object of the passed hierarchy.
	 * */
	private void setJsonObject() {
		rootJsonObject = jsonObject;
		for (String key : jsonObjHierarchy) {
			if (jsonObject != null) {
				jsonObject = (JSONObject) jsonObject.get(key);
			}
		}
		if (jsonObject == null) {
			CustomReporter.report(STATUS.ERROR, "Passed JSON  Object hierarchy " + Arrays.toString(jsonObjHierarchy)
			+ " does not exist in file {" + jsonFilePath + "}");
		}
	}

	/****************************************************************************************************/
	/**
	 * <pre>
	 * It will return you array of Json Objects present inside json array type.
	 * example json : 
	 * { 
	 * 	"topping": 
	 * 		[ 
	 * 			{ 
	 * 				"id": "5001", 
	 * 				"type": "None"
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed" 
	 * 			} 
	 *	 	]
	 * }
	 * 
	 * @return JSONObject[]
	 * @param key: pass the String key, as per above 
	 * 				example key would be "topping"
	 * </pre>           
	 */
	private JSONObject[] getJsonObjectArray(String key) {
		JSONObject[] jsonObjArray = null;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof JSONArray) {
					jsonArray = (JSONArray) jsonObject.get(key);
					Object[] objArr = jsonArray.toArray();
					jsonObjArray = Arrays.copyOf(objArr, objArr.length, JSONObject[].class);
				} else {
					CustomReporter.report(STATUS.ERROR, "Passed key '" + key + "' : does not have jsonObject array");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return jsonObjArray;
	}

	/**
	 * It returns you the item count of a json array
	 * @param jsonArr string key of json array object
	 * @return int json array objects count 
	 * */
	public int getJsonArrLength(String jsonArr) {
		if (jsonObject != null) {
			if (jsonObject.containsKey(jsonArr)) {
				if (jsonObject.get(jsonArr) instanceof JSONArray) {
					jsonArray = (JSONArray) jsonObject.get(jsonArr);
					return jsonArray.size();
				} else {
					CustomReporter.report(STATUS.ERROR, "Passed key '" + jsonArr + "' : does not have jsonObject array");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + jsonArr + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + jsonArr + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return -1;
	}

	/**
	 * <pre>
	 * It will give you String Array[] value from json Array[] by 
	 * passing array name, index(of array) & key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 *			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"extraCost":50,
	 * 				"available":true
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"extraCost":70,
	 * 				"available":false
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	String[] idVal= json.getStrArr("topping",0,"id");
	 * 	System.out.println(idVal[0]); // will print 5001 
	 * }
	 *  
	 * @param jsonArr name of json Array[] object, as per above 
	 * example expected value would be "topping"
	 * @param index Among jsonObject Array which jsonObject 
	 * you want to get, as per above example expected value would be "0" or "1"
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be any key, because this method will type cast 
	 * and returns the String[] values  
	 * 
	 * @return String[], if nothing found then null
	 * </pre>
	 */
	public String[] getStrArr(String jsonArr, int index, String key) {
		String[] strArr = null;
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index];
			if (tempJsonObj != null) {
				if (tempJsonObj.containsKey(key)) {
					if (tempJsonObj.get(key) instanceof Boolean) {
						strArr = new String[1];
						strArr[0] = (Boolean) tempJsonObj.get(key) + "";
					} else if (tempJsonObj.get(key) instanceof Double) {
						strArr = new String[1];
						strArr[0] = (Double) tempJsonObj.get(key) + "";
					} else if (tempJsonObj.get(key) instanceof Long) {
						strArr = new String[1];
						strArr[0] = (Long) tempJsonObj.get(key) + "";
					} else if (tempJsonObj.get(key) instanceof String) {
						strArr = new String[1];
						strArr[0] = (String) tempJsonObj.get(key);
					} else if (tempJsonObj.get(key) instanceof JSONArray) {
						jsonArray = (JSONArray) tempJsonObj.get(key);
						Object[] tempObjArr = jsonArray.toArray();
						strArr = new String[tempObjArr.length];
						for (int i = 0; i < tempObjArr.length; i++) {
							strArr[i] = String.valueOf(tempObjArr[i]);
						}
						// strArr=Arrays.copyOf(tempObjArr,
						// tempObjArr.length,String[].class);
					}
				} else {
					CustomReporter.report(STATUS.ERROR,
							"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
									+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return strArr;
	}

	/****************************************************************************************************/
	/**
	 * <pre>
	 * It will give you String Array[] value associated with any key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 *			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"extraCost":50,
	 * 				"available":true
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"extraCost":70,
	 * 				"available":false
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	String[] idVal= json.getStrArr("id");
	 * 	System.out.println(idVal[0]); // will print 5001 
	 * }
	 *  
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be any key, because this method will type cast 
	 * and returns the String[] values
	 * 
	 * @return String[], if nothing found then null
	 * </pre>
	 */
	public String[] getStrArr(String key) {
		String[] strArr = null;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof Boolean) {
					strArr = new String[1];
					strArr[0] = (Boolean) jsonObject.get(key) + "";
				} else if (jsonObject.get(key) instanceof Double) {
					strArr = new String[1];
					strArr[0] = (Double) jsonObject.get(key) + "";
				} else if (jsonObject.get(key) instanceof Long) {
					strArr = new String[1];
					strArr[0] = (Long) jsonObject.get(key) + "";
				} else if (jsonObject.get(key) instanceof String) {
					strArr = new String[1];
					strArr[0] = (String) jsonObject.get(key);
				} else if (jsonObject.get(key) instanceof JSONArray) {
					jsonArray = (JSONArray) jsonObject.get(key);
					Object[] objArr = jsonArray.toArray();
					strArr = Arrays.copyOf(objArr, objArr.length, String[].class);
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return strArr;
	}

	/**
	 * <pre>
	 * It will give you specific value from array of json Objects by 
	 * passing array name, index(of array) & key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 * 			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"available":true
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"available":false 
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	boolean availableVal= json.getBool("topping",1,"available");
	 * 	System.out.println(availableVal); // will print false 
	 * }
	 *  
	 * @param jsonArr name of json Array[] object, as per above 
	 * example expected value would be "topping"
	 * @param index Among jsonObject Array which jsonObject 
	 * you want to get, as per above example expected value would be "0" or "1"
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "available" only, If you pass "id" or "type"
	 * method will log an error and return false in array  
	 * 
	 * @return boolean, if nothing found then false
	 * </pre>
	 */
	public boolean getBool(String jsonArr, int index, String key) {
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index];
			if (tempJsonObj != null) {
				if (tempJsonObj.containsKey(key)) {
					if (tempJsonObj.get(key) instanceof Boolean) {
						return (Boolean) tempJsonObj.get(key);
					}
				} else {
					CustomReporter.report(STATUS.ERROR,
							"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
									+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return false;
	}

	/**
	  * <pre>
	 * It will give you boolean value of passed key 
	 * example json : 
	 * { 
	 * 	"create": {
	 *			"alertEnabled": true,
	 *			"alertScheduledDate": 10,
	 *			"mom": true,
	 *			"yoy": true,
	 *			},
	 *	"edit": {
	 *			"alertEnabled": false,
	 *			"alertScheduledDate": 1,
	 *			"mom": true,
	 *			"yoy": true,
	 *			}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","create");
	 * 	boolean alertEnabledVal= json.getBool("alertEnabled");
	 * 	System.out.println(alertEnabledVal); // will print true
	 *  
	 *  json= new JSONManager("jsonFilePath","edit");
	 * 	alertEnabledVal= json.getBool("alertEnabled");
	 * 	System.out.println(alertEnabledVal); // will print false 
	 * }
	 *  
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "alertEnabled" or "mom" or "yoy" only.
	 * If you pass "alertScheduledDate" this method will log an error, and return false 
	 * @return boolean, if nothing found then false
	 * </pre>
	 */
	public boolean getBool(String key) {
		boolean val = false;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof Boolean) {
					val = (Boolean) jsonObject.get(key);
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return val;
	}

	/**
	 * <pre>
	 * It will give you specific value from array of json Objects by 
	 * passing array name, index(of array) & key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 * 			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"extraCost":50
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"extraCost":70 
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	long extraCost= json.getLong("topping",1,"extraCost");
	 * 	System.out.println(extraCost); // will print 70 
	 * }
	 *  
	 * @param jsonArr name of json Array[] object, as per above 
	 * example expected value would be "topping"
	 * @param index Among jsonObject Array which jsonObject 
	 * you want to get, as per above example expected value would be "0" or "1"
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "extraCost". If you use "id" or "type", 
	 * then method will log an error and returns 0 
	 * 
	 * @return long, if nothing found then 0
	 * </pre>
	 */
	public long getLong(String jsonArr, int index, String key) {
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index] ;
			if (tempJsonObj != null) {
				if (tempJsonObj.containsKey(key)) {
					if (tempJsonObj.get(key) instanceof Long) {
						return (Long) tempJsonObj.get(key);
					}
				} else {
					CustomReporter.report(STATUS.ERROR,
							"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
									+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return 0;
	}

	/**
	  * <pre>
	 * It will give you long value of passed key 
	 * example json : 
	 * { 
	 * 	"create": {
	 *			"alertEnabled": true,
	 *			"alertScheduledDate": 10,
	 *			"mom": true,
	 *			"yoy": true,
	 *			},
	 *	"edit": {
	 *			"alertEnabled": false,
	 *			"alertScheduledDate": 1,
	 *			"mom": true,
	 *			"yoy": true,
	 *			}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","create");
	 * 	long alertScheduledDateVal= json.getLong("alertScheduledDate");
	 * 	System.out.println(alertScheduledDateVal); // will print 10
	 *  
	 *  json= new JSONManager("jsonFilePath","edit");
	 * 	alertScheduledDateVal= json.getLong("alertScheduledDate");
	 * 	System.out.println(alertScheduledDateVal); // will print 1
	 * }
	 *  
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "alertEnabled" or "mom" or "yoy" only.
	 * If you pass any other key this method will log an error, and return 0
	 * @return long, if nothing found then 0
	 * </pre>
	 */
	public long getLong(String key) {
		long val = 0;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof Long) {
					val = (Long) jsonObject.get(key);
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return val;
	}

	/**
	 * <pre>
	 * It will give you specific value from array of json Objects by 
	 * passing array name, index(of array) & key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 * 			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"extraCost":50
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"extraCost":70 
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	int extraCost= json.getInt("topping",1,"extraCost");
	 * 	System.out.println(extraCost); // will print 70 
	 * }
	 *  
	 * @param jsonArr name of json Array[] object, as per above 
	 * example expected value would be "topping"
	 * @param index Among jsonObject Array which jsonObject 
	 * you want to get, as per above example expected value would be "0" or "1"
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "alertScheduledDate" only.
	 * If you pass any other key this method will log an error, and return 0 
	 * @return int if nothing found then 0
	* </pre>
	 */
	public int getInt(String jsonArr, int index, String key) {
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index];
			if (tempJsonObj != null) {
				if (tempJsonObj.containsKey(key)) {
					if (tempJsonObj.get(key) instanceof Long) {
						return Integer.parseInt((Long) tempJsonObj.get(key) + "");
					}
				} else {
					CustomReporter.report(STATUS.ERROR,
							"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
									+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return 0;
	}

	/**
	  * <pre>
	 * It will give you long value of passed key 
	 * example json : 
	 * { 
	 * 	"create": {
	 *			"alertEnabled": true,
	 *			"alertScheduledDate": 10,
	 *			"mom": true,
	 *			"yoy": true,
	 *			},
	 *	"edit": {
	 *			"alertEnabled": false,
	 *			"alertScheduledDate": 1,
	 *			"mom": true,
	 *			"yoy": true,
	 *			}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","create");
	 * 	int alertScheduledDateVal= json.getInt("alertScheduledDate");
	 * 	System.out.println(alertScheduledDateVal); // will print 10
	 *  
	 *  json= new JSONManager("jsonFilePath","edit");
	 * 	alertScheduledDateVal= json.getInt("alertScheduledDate");
	 * 	System.out.println(alertScheduledDateVal); // will print 1
	 * }
	 *  
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "alertScheduledDate" only.
	 * If you pass any other key this method will log an error, and return 0 
	 * @return int if nothing found then 0
	 * </pre>
	 */
	public int getInt(String key) {
		int val = 0;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof Long) {
					val = Integer.parseInt((Long) jsonObject.get(key) + "");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return val;
	}

	/**
	 * <pre>
	 * It will give you specific value from array of json Objects by 
	 * passing array name, index(of array) & key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 * 			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"extraCost":50.5
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"extraCost":70.2 
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	double extraCost= json.getDoub("topping",1,"extraCost");
	 * 	System.out.println(extraCost); // will print 70.2 
	 * }
	 *  
	 * @param jsonArr name of json Array[] object, as per above 
	 * example expected value would be "topping"
	 * @param index Among jsonObject Array which jsonObject 
	 * you want to get, as per above example expected value would be "0" or "1"
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "extraCost" only.
	 * If you pass any other key this method will log an error, and return 0.0 
	 * @return double if nothing found then 0.0
	* </pre>
	 */
	public double getDouble(String jsonArr, int index, String key) {
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index] ;
			if (tempJsonObj != null) {
				if (tempJsonObj.containsKey(key)) {
					if (tempJsonObj.get(key) instanceof Double) {
						return (Double) tempJsonObj.get(key);
					}
				} else {
					CustomReporter.report(STATUS.ERROR,
							"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
									+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return 0.0;
	}

	/**
	  * <pre>
	 * It will give you double value of passed key 
	 * example json : 
	 * { 
	 * 	"create": {
	 *			"alertEnabled": true,
	 *			"alertScheduledDate": 10,
	 *			"mom": true,
	 *			"rate": 50.8
	 *			},
	 *	"edit": {
	 *			"alertEnabled": false,
	 *			"alertScheduledDate": 1,
	 *			"mom": true,
	 *			"rate": 75.2
	 *			}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","create");
	 * 	double alertScheduledDateVal= json.getDouble("rate");
	 * 	System.out.println(alertScheduledDateVal); // will print 10
	 *  
	 *  json= new JSONManager("jsonFilePath","edit");
	 * 	alertScheduledDateVal= json.getDouble("rate");
	 * 	System.out.println(alertScheduledDateVal); // will print 1
	 * }
	 *  
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "rate"only.
	 * If you pass any other key this method will log an error, and return 0.0 
	 * @return double, if nothing found then 0.0
	 * </pre>
	 */
	public double getDouble(String key) {
		double val = 0.0;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof Double) {
					val = (Double) jsonObject.get(key);
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return val;
	}

	/**
	 * <pre>
	 * It will give you specific value from array of json Objects by 
	 * passing array name, index(of array) & key 
	 * example json : 
	 * { 
	 * 	"pizzaParam":{
	 * 		"topping": 
	 * 		[ 
	 * 			{ 
	 * 				"id": "5001", 
	 * 				"type": "Cheese Crust",
	 * 				"extraCost":50.5
	 * 			}, 
	 * 			{ 
	 * 				"id": "5002", 
	 * 				"type": "Glazed",
	 * 				"extraCost":70.2 
	 * 			} 
	 *	 	]
	 *	}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","pizzaParam");
	 * 	String typeVal= json.getStr("topping",1,"type");
	 * 	System.out.println(typeVal); // will print Glazed 
	 * }
	 *  
	 * @param jsonArr name of json Array[] object, as per above 
	 * example expected value would be "topping"
	 * @param index Among jsonObject Array which jsonObject 
	 * you want to get, as per above example expected value would be "0" or "1"
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "id" or "type" only.
	 * If you pass any other key this method will log an error, and return null 
	 * @return double if nothing found then null
	* </pre>
	 */
	public String getStr(String jsonArr, int index, String key) {
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index];
			if (tempJsonObj != null) {
				if (tempJsonObj.containsKey(key)) {
					if (tempJsonObj.get(key) instanceof String) {
						return (String) tempJsonObj.get(key);
					}
				} else {
					CustomReporter.report(STATUS.ERROR,
							"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
									+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return null;
	}

	/**
	  * <pre>
	 * It will give you String value of passed key 
	 * example json : 
	 * { 
	 * 	"create": {
	 *			"alertText": "Alert Text 1",
	 *			"alertScheduledDate": 10,
	 *			"mom": true,
	 *			"rate": 50.8
	 *			},
	 *	"edit": {
	 *			"alertText": "Alert Text 2",
	 *			"alertScheduledDate": 1,
	 *			"mom": true,
	 *			"rate": 75.2
	 *			}
	 * }
	 * 
	 * Usage: {@code
	 * 	JSONManager json= new JSONManager("jsonFilePath","create");
	 * 	String alertTxtVal= json.getStr("alertText");
	 * 	System.out.println(alertTxtVal); // will print Alert Text 1
	 *  
	 *  json= new JSONManager("jsonFilePath","edit");
	 * 	alertTxtVal= json.getStr("alertText");
	 * 	System.out.println(alertTxtVal); // will print Alert Text 2
	 * }
	 *  
	 * @param key The key of the object whose value is desired, as per above 
	 * example expected value would be "alertText" only.
	 * If you pass any other key this method will log an error, and return null
	 * @return String, if nothing found then null
	 * </pre>
	 */
	public String getStr(String key) {
		String val = null;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				if (jsonObject.get(key) instanceof String) {
					val = (String) jsonObject.get(key);
				}
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Passed key '" + key + "' : does not exist, in JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " located {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR,
					"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
							+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
		}
		return val;
	}

	/**
	 * Many times we want to know the keys prior to get the value.
	 * @return Set<String> of Keys
	 * */
	@SuppressWarnings("unchecked")
	public Set<String> keySet() {
		if (jsonObject != null) {
			return jsonObject.keySet();
		}
		return null;
	}
	
	/**
	 * Many times we want to get the current Json Object 
	 * @return JSONObject whichever object our object is currently pointing to
	 * */
	public JSONObject getMapObj() {
		return jsonObject;
	}

	/**
	 * This will check whether passed key existence in Json Object  
	 * @return boolean, if key is not found then false
	 * */
	public boolean containsKey(String key) {
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This will check whether passed KEY exists in array of json Objects by 
	 * passing array name, index(of array) & KEY  
	 * @return boolean, if key is not found then false
	 * */
	public boolean containsKey(String jsonArr, int index, String key) {
		
		JSONObject[] objArr = getJsonObjectArray(jsonArr);
		if (objArr != null && index < objArr.length) {
			JSONObject tempJsonObj = objArr[index];
			if (tempJsonObj != null) {
				return tempJsonObj.containsKey(key);
			} else {
				CustomReporter.report(STATUS.ERROR,
						"Value for '" + key + "' : can not be found because, Passed JSON  Object hierarchy "
								+ Arrays.toString(jsonObjHierarchy) + " does not exist in file {" + jsonFilePath + "}");
			}
		} else {
			CustomReporter.report(STATUS.ERROR, "jsonObj Array is null, or passed index: "+index+" is Out of Bounds");
		}
		return false;
	}


	/**
	 * This method enable us to write any type of key:value pair into currently pointing Json Object
	 * If key already exists then it will overwrite its content, or It will create a new key value pair
	 * */
	public void put(Object key, Object value) {
		JSONWriter.put(this, key, value);
	}
	
}

/**
 * JSONWriter
 * This class is created to keep write operation thread safe.
 * */
@ThreadSafe
class JSONWriter {
	private static JSONObject jsonObject;
	private static JSONObject rootJsonObject;
	private static FileWriter file;
	
	
	/**
	 * This method will not allow any other thread access it unless current
	 * thread completed its execution.
	 * This method will write the K:V content in passed Json Manager Object(Synchronously)
	 */
	@SuppressWarnings("unchecked")
	static synchronized void put(JSONManager jsonMgr,Object key, Object value) {
		try {
			jsonMgr.init("WRITING {"+(String)key+":"+(String)value+"} IN ");
			jsonObject=jsonMgr.getJsonObject();
			rootJsonObject=jsonMgr.getRootJsonObject();
			//CustomReporter.report(STATUS.INFO, "jsonObject	"+jsonObject.toString());
			//CustomReporter.report(STATUS.INFO, "rootJsonObject	"+rootJsonObject.toString());
			
			jsonObject.put(key, value);
			file = new FileWriter(jsonMgr.getJsonFilePath());
			file.write(rootJsonObject.toJSONString());
			file.flush();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

}
