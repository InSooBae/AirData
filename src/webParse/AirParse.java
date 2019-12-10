package webParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import db.Air;

class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {

	  public int compare(List<T> o1, List<T> o2) {
	    return o1.get(1).compareTo(o2.get(1));
	  }

	}

public class AirParse {
	/* method=period(기간별)
	 * cal1=2018	(시작년.)
	 * cal1Month=01	(시작 월)
	 * cal1Day=01	(시작 일)
	 * cal2=2018	(끝 년)
	 * cal2Month=04	(끝 월)
	 * cal2Day=29	(끝 일)
	 * pNum=4		(페이지넘버)
	*/
	private static final int[][] POSITION = {
            {385, 496, 328, 87, 242, 420, 115, 182, 405, 355, 375, 240, 196, 240, 320, 360, 333, 450, 127, 190, 285, 225, 285, 299, 427},
            {403, 315, 183, 293, 437, 317, 390, 435, 167, 147, 273, 382, 300, 273, 415, 315, 242, 380, 351, 363, 337, 215, 263, 300, 249}};
	private static String URL = "http://cleanair.seoul.go.kr/air_pollution.htm?";
	
	public List<List<String>> getWebData(String month,String day) {
		String	method ="period";
		String cal1 = "2018";
		String cal1Month=month;
		String cal1Day=day;
		String cal2="2018";
		String cal2Month=month;
		String cal2Day=day;
		int pNum=1;
		List<List<String>> ret = new ArrayList<List<String>>();
//		System.out.println("URL :: "+ URL+getURL(method,cal1,cal1Month,cal1Day,cal2,cal2Month,cal2Day,pNum));
		
		//1. Document를 가져온다.
		Document doc;
		try {
			doc = Jsoup.connect(URL+getURL(method,cal1,cal1Month,cal1Day,cal2,cal2Month,cal2Day,pNum)).get();
			//2. 목록을 가져온다.
//			System.out.println(""+doc.toString());
			Elements elements = doc.select("tr").not(".ft_b");
//			Elements element = doc.select()
			int idx=0;
			
			//3. 목록(배열)에서 정보를 가져온다.
			for(Element element:elements) {
				
				if((idx++)==0)
					continue;
				List<String> tmpList = new ArrayList<String>();
				String array[] = element.text().replace("-","" ).split(" ");
				tmpList = new ArrayList<String>(Arrays.asList(array));
				
				ret.add(tmpList);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collections.sort(ret,new ListComparator<>());
		
//		for(List<String> ls : ret) {
//			Air a = new Air();
//			a.setYmDate(ls.get(0));
//			System.out.println("년 : "+a.getYmDate());
//			a.setLoc_name(ls.get(1));
//			System.out.println("년 : "+a.getLoc_name());
//			a.setNo2p(Double.parseDouble(ls.get(5)));
//			System.out.println("년 : "+a.getNo2p());
//			a.setO3p(Double.parseDouble(ls.get(4)));
//			System.out.println("년 : "+a.getO3p());
//			a.setCop(Double.parseDouble(ls.get(6)));
//			System.out.println("년 : "+a.getCop());
//			a.setSo2p(Double.parseDouble(ls.get(7)));
//			System.out.println("년 : "+a.getSo2p());
//			a.setPm10(Integer.parseInt(ls.get(2)));
//			System.out.println("년 : "+a.getPm10());
//			a.setPm25(Integer.parseInt(ls.get(3)));
//			System.out.println("년 : "+a.getPm25());
//			a.setX(POSITION[0][count]);
//			a.setY(POSITION[1][count++]);
//			
//			
//			air.add(a);
//			
//		}
			
		
		return ret;
	}
	
	
	public static String getURL(String method,String cal1,String cal1Month,String cal1Day,String cal2,String cal2Month,String cal2Day,int pNum) {
		String params ="method="+method+""
						+"&msrntwCode=A"
						+"&lGroup="
						+"&mGroup="
						+"&sGroup="
						+"&cal1="
						+cal1
						+"&cal1Month="
						+cal1Month
						+"&cal1Day="
						+cal1Day
						+"&cal2="
						+cal2
						+"&cal2Month="
						+cal2Month
						+"&cal2Day="
						+cal2Day
						+"&time="
						+"&pNum="
						+pNum;
		
		return params;
	}
	
}
