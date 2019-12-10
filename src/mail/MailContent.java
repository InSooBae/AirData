package mail;

import java.util.ArrayList;
import java.util.stream.Collectors;

import db.Air;
import db.AirData;

public class MailContent {
	String[] loc_name = {"강남구","강남대로","강동구","강변북로","강북구","강서구","공항대로","관악구","광진구","구로구","금천구","노원구","도봉구","도산대로","동대문구","동작구","동작대로","마포구","서대문구","서초구","성동구","성북구","송파구","신촌로","양천구","영등포구","영등포로","용산구","은평구","정릉로","종로","종로구","중구","중랑구","천호대로","청계천로","한강대로","홍릉로","화랑로"};

	MailContent(){
		
	};
	String MailContent(int index_month, int index_day, int index_loc)
	{
		String month="";
		String day="";
		
		month=""+(index_month+1);
		day=""+(index_day+1);
		
		if(index_month<10)
			month="0"+(index_month+1);
		if(index_day<10)
			day="0"+(index_day+1);
	
		String date="2018"+month+day;
		
		String loc = loc_name[index_loc];
		Air data = new Air();
		AirData getdata = new AirData();
		ArrayList alldata = getdata.getAllDatafromYMData(date,loc);
		
		String content = (String) alldata.stream() 
                .map(String::valueOf) 
                .collect(Collectors.joining("\n")); 
		
		//String content="";
		System.out.println(date);
		System.out.println(alldata);
		System.out.println(loc_name[index_loc]);
		return content;
		
	}
}
