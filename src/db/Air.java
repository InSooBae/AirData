package db;

public class Air {
	private String ymDate;
	private String loc_name;
	private double no2p;
	private double o3p;
	private double cop;
	private double so2p;
	private int pm10;
	private int pm25;
	private int x;
	private int y;
	
	public void setYmDate(String ymDate) {
		this.ymDate=ymDate;
	}
	
	public String getYmDate() {
		return ymDate;
	}
	
	public void setLoc_name(String loc_name) {
		this.loc_name=loc_name;
	}
	
	public String getLoc_name() {
		return loc_name;
	}
	
	public void setNo2p(double no2p) {
		this.no2p = no2p;
	}
	
	public double getNo2p() {
		return no2p;
	}
	
	public void setO3p(double o3p) {
		this.o3p=o3p;
	}
	
	public double getO3p() {
		return o3p;
	}
	
	public void setCop(double cop) {
		this.cop=cop;
	}
	
	public double getCop() {
		return cop;
	}
	
	public void setSo2p(double so2p) {
		this.so2p=so2p;
	}
	
	public double getSo2p() {
		return so2p;
	}
	
	public void setPm10(int pm10) {
		this.pm10=pm10;
	}
	public int getPm10() {
		return pm10;
	}
	
	public void setPm25(int pm25) {
		this.pm25=pm25;
	}
	
	public int getPm25() {
		return pm25;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public int getY() {
		return y;
	}
}
