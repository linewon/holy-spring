package json;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * parse json string into regular format on web
 * 
 * @author line
 *
 */
public class JsonWebPrinter {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {

			String jsonStr = scanner.nextLine();
			System.out.println("original json string : " + jsonStr);

			if ("#".equals(jsonStr)) {
				break;
			}
			try {
				JSONObject jsonObj = (JSONObject) JSON.parse(jsonStr);
				System.out.println("---------json object---------");
				System.out.println(jsonObj.toJSONString());
				System.out.println("-----------------------------");
				StringBuilder sb = new StringBuilder();
				printJson(jsonObj, 0, 0, sb);
			} catch (Exception e) {
				System.out.println("请输入正确格式的json字符串 ! ");
				e.printStackTrace();
			}
		}
		scanner.close();
	}

	/**
	 * 递推打印JSONObject
	 * 
	 * @param obj
	 * @throws Exception
	 */
	private static void printJson(Object obj, int tblNum, int arrNum, StringBuilder resultStr) throws Exception {
		if (obj instanceof String) {

			System.out.println("\"" + obj + "\"");
		} else if (obj instanceof JSONObject) {
			System.out.println();
			if (arrNum > 0) {
				printTable(tblNum);
				System.out.println("array[" + arrNum + "]");
			}
			JSONObject jsonObj = (JSONObject) obj;
			for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
				String key = entry.getKey();
				printTable(tblNum);
				Object value = entry.getValue();
				System.out.print(key + " : ");
				printJson(value, tblNum + 1, arrNum, resultStr);
			}
		} else if (obj instanceof JSONArray) {

			JSONArray jsonArray = (JSONArray) obj;
			Iterator<Object> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				printJson(iterator.next(), tblNum, ++arrNum, resultStr);
			}
		}
	}
	
	private static void printTable(int num) {
		for (int i = 0; i < num; i++)
			System.out.print("----");
	}
}