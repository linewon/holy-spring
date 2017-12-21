package json;

import java.util.Scanner;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonEnumTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		String text = scanner.nextLine();
		System.out.println(parseJsonToEnum(JSON.parseObject(text)));
		
		scanner.close();
	}

	// 换行控制，几个一换行，是否换行
	public static String parseJsonToEnum(JSONObject json) throws JSONException {

		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (Entry<String, Object> entry : json.entrySet()) {
			if (count != 0)
				sb.append(", ");
			
			if (count == 3) {
				sb.append('\n');
				count = 1;
			} else
				count++;

			String key = entry.getKey();
			String val = (String) entry.getValue();
			sb.append(getEnumString(key, val));
		}
		sb.append(';');

		return sb.toString();
	}

	// 需要加个判断条件，是否自动在数字前补 _
	private static String getEnumString(String key, String val) {
		StringBuilder sb = new StringBuilder();
		if (Character.isDigit(key.charAt(0)))
			sb.append('_');
		sb.append(key).append("(\"").append(val).append("\")");

		return sb.toString();
	}
}
