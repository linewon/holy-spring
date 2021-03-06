package line.modules.json.service;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class JsonParserService {
	
	/**
	 * 入口1
	 */
	public String parseJson(JSONObject json) throws Exception {
		StringBuilder sb = new StringBuilder();
		forward(json, 0, 0, sb);
		return sb.toString();
	}
	
	/**
	 * 入口2
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String parseJson(String jsonString) throws Exception {
		JSONObject json = null;
		try {
			json = JSON.parseObject(jsonString);
		} catch (Exception e) {
			throw new IllegalArgumentException("输入非json格式字符串!", e);
		}
		return parseJson(json);
	}

	/**
	 * 根据对象类型转发处理，处理String类型对象
	 */
	private void forward(Object obj, int tblNum, int arrNum, StringBuilder sb) throws Exception {
		if (obj == null || obj instanceof String) {
			sb.append('"').append(obj).append("\"\n");
		} else if (obj instanceof JSONObject) { // 进来的对象是JSONObject类型，则遍历后输出
			handleJsonObj((JSONObject)obj, tblNum, arrNum, sb);
		} else if (obj instanceof JSONArray) {
			handleJsonArr((JSONArray)obj, tblNum, arrNum, sb);
		} else {
			throw new IllegalArgumentException("收到数据非String,JSONObject,JSONArray");
		}
	}
	/**
	 * 处理JSONObject对象
	 */
	private void handleJsonObj(JSONObject jsonObj, int tblNum, int arrNum, StringBuilder sb) throws Exception {
		if (arrNum < 2)
			sb.append('\n'); // jsonarray，在array[]: 1 之前输入一个换行
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

	/**
	 * 处理JSONArray对象
	 */
	private void handleJsonArr(JSONArray jsonArray, int tblNum, int arrNum, StringBuilder sb) throws Exception {
		Iterator<Object> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			forward(iterator.next(), tblNum, ++arrNum, sb);
		}
	}

	/**
	 * 添加缩进
	 */
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
