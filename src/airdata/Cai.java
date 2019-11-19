package airdata;

import db.Air;

// CAI 계산 클래스
public class Cai {
	private final static double[] SO2L = {0,0.021,0.051,0.151};
	private final static double[] SO2H = {0.02,0.05,0.15,1};
	private final static double[] COL = {0,2.01,9.01,15.01};
	private final static double[] COH = {2,9,15,50};
	private final static double[] O3L = {0,0.031,0.091,0.151};
	private final static double[] O3H = {0.03,0.09,0.15,0.6};
	private final static double[] NO2L = {0,0.031,0.061,0.201};
	private final static double[] NO2H = {0.03,0.06,0.2,2};
	private final static int[] PM10L = {0,31,81,151};
	private final static int[] PM10H = {30,80,150,600};
	private final static int[] PM25L = {0,16,36,76};
	private final static int[] PM25H = {15,35,75,500};
	private final static int [] IL = {0,51,101,251};
	private final static int [] IH = {50,100,250,500};
	private final double NO2;
	private final double O3;
	private final double CO;
	private final double SO2;
	private final int PM10;
	private final int PM25;
	public Cai(Air a) {
		NO2=a.getNo2p();
		O3=a.getO3p();
		CO=a.getCop();
		SO2=a.getSo2p();
		PM10=a.getPm10();
		PM25=a.getPm25();
	}
	public int getSO2Cai() {
		double so2;
		int i=0;
		for (i=0; i<4; i++) {
			if(SO2>=SO2L[i] && SO2<=SO2H[i])
				break;
		}
		so2=(IH[i]-IL[i])/(SO2H[i]-SO2L[i])*(SO2-SO2L[i])+IL[i];
		return (int)so2;
	}
	public int getCOCai() {
		double co;
		int i=0;
		for (i=0; i<4; i++) {
			if(CO>=COL[i] && CO<=COH[i])
				break;
		}
		co=(IH[i]-IL[i])/(COH[i]-COL[i])*(CO-COL[i])+IL[i];
		return (int)co;
	}
	public int getO3Cai() {
		double o3;
		int i=0;
		for (i=0; i<4; i++) {
			if(O3>=O3L[i] && O3<=O3H[i])
				break;
		}
		o3=(IH[i]-IL[i])/(O3H[i]-O3L[i])*(O3-O3L[i])+IL[i];
		return (int)o3;
	}

	public int getNO2Cai() {
		double no2;
		int i=0;
		for (i=0; i<4; i++) {
			if(NO2>=NO2L[i] && NO2<=NO2H[i])
				break;
		}
		no2=(IH[i]-IL[i])/(NO2H[i]-NO2L[i])*(NO2-NO2L[i])+IL[i];
		return (int)no2;
	}
	// 미세먼지 구간부터 계산법이 바뀌어서 대략적인 계산함. (오차범위 존재)
	public int getPM10Cai() {
		double pm10;
		int i=0;
		for (i=0; i<4;i++ ) {
			if(PM10>=PM10L[i] && PM10<=PM10H[i])
				break;
			
		}
		
		pm10=(double)(IH[i]-IL[i])/(double)(PM10H[i]-PM10L[i])*(((double)PM10*0.8)-PM10L[i])+IL[i];

		return (int) Math.round(pm10);
	}
	// 초미세먼지도 계산법이 바뀌어서 대략적인 계산함.(오차범위 존재)
		public int getPM25Cai() {
			double pm25;
			int i=0;
			for (i=0; i<4;i++ ) {
				if(PM25>=PM25L[i] && PM25<=PM25H[i])
					break;
				
			}
			
			pm25=(double)(IH[i]-IL[i])/(double)(PM25H[i]-PM25L[i])*(((double)PM25*0.8)-PM25L[i])+IL[i];
		
			return (int) Math.round(pm25);
		}
	
}
