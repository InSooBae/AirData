package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airdata.Cai;




public class AirData {
	private Connection conn;

	//USERNAME
    private static final String USERNAME = "root";
    //PW
    private static final String PASSWORD = "qodlstn12";
    // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
    private static final String URL = "jdbc:mysql://localhost/airdata?useSSL=false&&serverTimezone=UTC";
    //gui 좌표
    private static final int[][] POSITION = {
            {385, 496, 328, 87, 242, 420, 115, 182, 405, 355, 375, 240, 196, 240, 320, 360, 333, 450, 127, 190, 285, 225, 285, 299, 427},
            {403, 315, 183, 293, 437, 317, 390, 435, 167, 147, 273, 382, 300, 273, 415, 315, 242, 450, 351, 363, 337, 215, 263, 300, 249}};

    public AirData() {
    	// Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
        // java 표준인 java.sql.Connection 클래스를 import해야 한다.
       

    	try{
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
       
 
           
            // @param  getConnection(url, userName, password);
            // @return Connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("연결 성공");

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
       
    }

    public void createTable() {
    	Statement stmt = null;
    	String sql = null;
    	
    	try {
    		stmt = conn.createStatement();
		
		StringBuilder sb = new StringBuilder();
		
		/*
		 * create table if not exists air(
		 * 		ymDate varchar(8) not null,		//Date 타입이 맞지만 오류 발생으로 임시방편으로 varchar()이용.
		 * 		loc_name varchar(20) not null,
		 * 		no2p decimal(4,3),
		 * 		o3p decimal(4,3),
		 * 		co2p decimal(2,1),
		 * 		so2p decimal(4,3),
		 * 		pm10 decimal(4),
		 * 		pm25 decimal(4)
		 * 	);	
		 */
		
		sql = sb.append("create table if not exists air(")
				.append("ymDate varchar(8) not null,")
				.append("loc_name varchar(20) not null,")
				.append("no2p decimal(4,3),")
				.append("o3p decimal(4,3),")
				.append("co2p decimal(2,1),")
				.append("so2p decimal(4,3),")
				.append("pm10 decimal(4),")
				.append("pm25 decimal(4)")
				.append(");").toString();
		
		//테이블 생성, 수정 ,삭제 등 db관리 명령어에 사용
		stmt.execute(sql);
		
    	} catch(SQLException e) {
    		e.printStackTrace();	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	
    }

    public void insertAirData() {
       String sql = "insert into air(ymDate,loc_name,no2p,o3p,cop,so2p,pm10,pm25) values(?,?,?,?,?,?,?,?)";
       //쿼리 실행시 단계 1)쿼리 문장 분석 -> 2)컴파일 -> 3)실행
       //Statement를 사용하면 매번 쿼리 수행할때 마다 위 3단계를 거치고(한번 이용할시)
       //PreparedStatement 이용시 처음 한번만 3단계 거친후 캐시에 담아 재사용함.(동일한 쿼리 반복적 수행시 좋음) 
       PreparedStatement pstmt = null;
       Csv csv = new Csv();
     
       List<List<String>> a=csv.getCSV();
       
       try {
    	   for(List<String> tmp:a) {
           
    		   int c=tmp.size();
    		   String tp;
    		   String t=(String)tmp.get(0);
    		   tp=t;
    		   pstmt = conn.prepareStatement(sql);

           
           
    		   if(c<8) {
    			   for(int l=c;l<8;l++)
    				   tmp.add("");
    		   }
         
               pstmt.setString(1,tp);
               pstmt.setString(2,(String) tmp.get(1));
               
               if(!((String)tmp.get(2)).equals("")) {
               float temp3 = Float.parseFloat((String)tmp.get(2));
               pstmt.setFloat(3, (float) temp3);
               }  else
                pstmt.setNull(3, java.sql.Types.INTEGER);
               
               if(!((String)tmp.get(3)).equals("")) {
               float temp4 = Float.parseFloat((String)tmp.get(3));
               pstmt.setFloat(4,(float) temp4 );
               }  else
                pstmt.setNull(4, java.sql.Types.INTEGER);
               
               if(!((String)tmp.get(4)).equals("")) {
               float temp5 = Float.parseFloat((String)tmp.get(4));
               pstmt.setFloat(5, (float) temp5);
               }  else
                pstmt.setNull(5, java.sql.Types.INTEGER);
               
               if(!((String)tmp.get(5)).equals("")) {
               float temp6 = Float.parseFloat((String)tmp.get(5));
               pstmt.setFloat(6, (float) temp6);
               } else
                pstmt.setNull(6, java.sql.Types.INTEGER);
               
               if(!((String)tmp.get(6)).equals("")) {
               int temp7 = Integer.parseInt((String)tmp.get(6));
               pstmt.setInt(7, (int) temp7);
               }  else
                pstmt.setNull(7, java.sql.Types.INTEGER);
               
               if(!((String)tmp.get(7)).equals("")) {
               int temp8 = Integer.parseInt((String)tmp.get(7));
               pstmt.setInt(8, (int) temp8);
               } else
                pstmt.setNull(8, java.sql.Types.INTEGER);
           
               //레코드 삽입,수정,삭제 등 DB관리 명령어에 사용
               pstmt.executeUpdate();
       
           }
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } finally {
           try {
               if (pstmt != null && !pstmt.isClosed())
                   pstmt.close();
           } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
       System.out.println("FIN");
    }

    public List<Air> getAllAirData() {
    	String sql =null;
    	Statement stmt = null;
    	List<Air> list = new ArrayList<Air>();
    	try {
    		//sql 문.
    		sql="SELECT * FROM air;";
    		//Statement 생성후 실행할 쿼리 정보 등록.
    		stmt=conn.createStatement();
    		//결과를 담을 ResultSet 생성 후 결과 담음.
    		ResultSet rs =stmt.executeQuery(sql);
    		
    		
    		
    		while(rs.next()) {
    			Air air = new Air();
    			air.setYmDate(rs.getString("ymDate"));
    			air.setLoc_name(rs.getString("loc_name"));
    			air.setNo2p(rs.getDouble("no2p"));
    			air.setO3p(rs.getDouble("o3p"));
    			air.setCop(rs.getDouble("cop"));
    			air.setSo2p(rs.getDouble("so2p"));
    			air.setPm10(rs.getInt("pm10"));
    			air.setPm25(rs.getInt("pm25"));
    			list.add(air);
    		}
    		
    		
    		
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    //%로(마지막 OO로 끝나는 지역 안나옴) 
    public List<Air> getSelectYMData(String ymDate) {
    	String sql =null;
    	Statement stmt = null;
    	List<Air> list = new ArrayList<Air>();
    	//count
    	int count=0;
    	try {
    		StringBuilder sb = new StringBuilder();
    		sql=sb.append("SELECT * FROM air WHERE ymDate=")
    		.append(ymDate)
    		.append(" AND NOT loc_name LIKE '%로' ")
    		.append(";").toString();
    		stmt=conn.createStatement();
    		ResultSet rs =stmt.executeQuery(sql);
    		while(rs.next()) {
    			
    			Air air = new Air();
    			air.setYmDate(rs.getString("ymDate"));
    			air.setLoc_name(rs.getString("loc_name"));
    			air.setNo2p(rs.getDouble("no2p"));
    			air.setO3p(rs.getDouble("o3p"));
    			air.setCop(rs.getDouble("cop"));
    			air.setSo2p(rs.getDouble("so2p"));
    			air.setPm10(rs.getInt("pm10"));
    			air.setPm25(rs.getInt("pm25"));
    			air.setX(POSITION[0][count]);
    			air.setY(POSITION[1][count++]);
    			list.add(air);
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
// 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드(Date 타입으로 값 넣을시 일수 1칸씩 밀려서 varchar()로 함)
//	public Date transformDate(String date) {
//    SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
//     
//    // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
//    SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
//   
//    java.util.Date tempDate = null;
//     
//    try {
//         // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
//        tempDate = beforeFormat.parse(date);
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }
//     
//    // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
//    String transDate = afterFormat.format(tempDate);
//     
//    // 반환된 String 값을 Date로 변경한다.
//    Date d = Date.valueOf(transDate);
//     
//    return d;
//}

	public static void main(String[] args) {
		AirData a= new AirData();
		List<Air> list=a.getSelectYMData("20180101");
		for (Air tmp: list) {
			Cai f = new Cai(tmp);
			System.out.println(tmp.getLoc_name());
			System.out.println(tmp.getX());
			System.out.println(tmp.getY());
		}
	}

}