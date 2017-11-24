	package json;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonPrinter {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (true) {

			String jsonStr = scan.nextLine();
			System.out.println("original json string : " + jsonStr);

			if ("#".equals(jsonStr)) {
				break;
			}
			try {
				JsonPrinter jp = new JsonPrinter();
				System.out.println(jp.parseJson(jsonStr));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		scan.close();
	}
	
	public String parseJson(JSONObject json) throws Exception {
		StringBuilder sb = new StringBuilder();
		try {
			forward(json, 0, 0, sb);
		} catch (Exception e) {
			throw new Exception("json转换过程出错!", e);
		}
		return sb.toString();
	}
	
	public String parseJson(String jsonString) throws Exception {
		JSONObject json = null;
		try {
			json = JSON.parseObject(jsonString);
		} catch (Exception e) {
			throw new IllegalArgumentException("输入非json格式字符串!", e);
		}
		return parseJson(json);
	}

	private void forward(Object obj, int tblNum, int arrNum, StringBuilder sb) throws Exception {
		if (obj == null || obj instanceof String) {
			sb.append('"').append(obj).append("\"\n");
		} else if (obj instanceof JSONObject) { // 进来的对象是JSONObject类型，则遍历后输出
			handleJsonObj((JSONObject)obj, tblNum, arrNum, sb);
		} else if (obj instanceof JSONArray) {
			handleJsonArr((JSONArray)obj, tblNum, arrNum, sb);
		} else {
			throw new Exception("收到数据非String,JSONObject,JSONArray");
		}
	}

	private void handleJsonObj(JSONObject jsonObj, int tblNum, int arrNum, StringBuilder sb) throws Exception {
		if (arrNum < 2)
			sb.append('\n');
		if (arrNum > 0) {
			if (tblNum > 0)
				sb.append(addTable(tblNum));
			sb.append("array[]: ").append(arrNum).append('\n');
		}
		for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
			String key = entry.getKey();
			if (tblNum > 0)
				sb.append(addTable(tblNum));
			Object value = entry.getValue();
			sb.append(key).append(" : ");
			forward(value, tblNum + 1, arrNum, sb);
		}
	}

	private void handleJsonArr(JSONArray jsonArray, int tblNum, int arrNum, StringBuilder sb) throws Exception {
		Iterator<Object> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			forward(iterator.next(), tblNum, ++arrNum, sb);
		}
	}
	
	private String addTable(int tblNum) {
		if (tblNum > 0) {
			StringBuilder sb = new StringBuilder();
			while(tblNum-- > 0)
				sb.append("====");
			return sb.toString();
		}
		return null;
	}
}
