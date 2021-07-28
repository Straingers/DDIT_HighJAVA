package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 공공데이터 포털의 OPEN API
 * (시군구별관광기후지수조회)
 * @author macween
 */
public class OpenAPI {
	private String svcKey = "response";  
	private String apiKey = "lY6hcGsVMGTH3f6rdGMm9hyw6vWqSVW8WmTz9U7OWXCXJ9SQ3J56hqVJqryW4rL8mZwyz7Srmi%2Fyir%2FNt7p7ig%3D%3D"; // 개인별 발급.
	private String startIdx = "1";  
	private String endIdx = "10";	
	private String currentDate = "2019122010";
	private String date = "3";
	
	/**
	 * JSON
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private JSONObject getJSONObject() throws IOException, ParseException {
		//http://apis.data.go.kr/1360000/TourStnInfoService/getCityTourClmIdx?serviceKey=lY6hcGsVMGTH3f6rdGMm9hyw6vWqSVW8WmTz9U7OWXCXJ9SQ3J56hqVJqryW4rL8mZwyz7Srmi%2Fyir%2FNt7p7ig%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&CURRENT_DATE=2019122010&DAY=3&CITY_AREA_ID=5013000000
		URL url = new URL("http://apis.data.go.kr/1360000/TourStnInfoService/getCityTourClmIdx?" + 
				"serviceKey=" + apiKey + 
				"&pageNo=" + startIdx + "&numOfRows=" + endIdx + "&dataType=JSON" + 
				"&CURRENT_DATE=" + currentDate + "&DAY=" + date);
		URLConnection urlConnection = url.openConnection();

		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));

		return (JSONObject)obj;
	}

	/**
	 * 시작
	 * @throws IOException
	 * @throws ParseException
	 */
	public void start() throws IOException, ParseException {


		JSONObject jsonfile = getJSONObject();

		JSONObject rootObj = (JSONObject) jsonfile.get(svcKey);

		JSONObject result = (JSONObject)rootObj.get("header");
		
		String code = (String)result.get("resultCode");

		if(code.equals("00")) { // 정상 상태이면...
			
			JSONObject tmp = (JSONObject)rootObj.get("body");
			JSONObject tmp2 = (JSONObject)tmp.get("items");
			JSONArray list = (JSONArray)tmp2.get("item");

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = list.iterator();
			
			while(it.hasNext()){

				JSONObject tempJson = it.next();

				System.out.println("도시명 : " + tempJson.get("totalCityName"));
				System.out.println("도시아이디 : " + tempJson.get("cityAreaId"));
				System.out.println("기후지수 : " + tempJson.get("kmaTci"));
				System.out.println("기후등급 : " + tempJson.get("TCI_GRADE"));
				System.out.println("측정날짜 : " + tempJson.get("tm"));

				System.out.println("-------------------------");
			}
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		new OpenAPI().start();
	}
}
