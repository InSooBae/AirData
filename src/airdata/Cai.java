package airdata;

import db.Air;

// CAI 계산 클래스
public class Cai {
	final static double[] so2L = {0,0.021,0.051,0.151};
	final static double[] so2H = {0.02,0.05,0.15,1};
	final static double[] coL = {0,2.01,9.01,15.01};
	final static double[] coH = {2,9,15,50};
	final static double[] o3L = {0,0.031,0.091,0.151};
	final static double[] o3H = {0.03,0.09,0.15,0.6};
	final static double[] no2L = {0,0.031,0.061,0.201};
	final static double[] no2H = {0.03,0.06,0.2,2};
	final static int[] pm10L = {0,31,81,151};
	final static int[] pm10H = {30,80,150,600};
	final static int[] pm25L = {0,16,36,76};
	final static int[] pm25H = {15,35,75,500};
	final static int [] iL = {0,51,101,251};
	final static int [] iH = {50,100,250,500};
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
			if(SO2>=so2L[i] && SO2<=so2H[i])
				break;
		}
		so2=(iH[i]-iL[i])/(so2H[i]-so2L[i])*(SO2-so2L[i])+iL[i];
		return (int)so2;
	}
	public int getCOCai() {
		double co;
		int i=0;
		for (i=0; i<4; i++) {
			if(CO>=coL[i] && CO<=coH[i])
				break;
		}
		co=(iH[i]-iL[i])/(coH[i]-coL[i])*(CO-coL[i])+iL[i];
		return (int)co;
	}
	public int getO3Cai() {
		double o3;
		int i=0;
		for (i=0; i<4; i++) {
			if(O3>=o3L[i] && O3<=o3H[i])
				break;
		}
		o3=(iH[i]-iL[i])/(o3H[i]-o3L[i])*(O3-o3L[i])+iL[i];
		return (int)o3;
	}

	public int getNO2Cai() {
		double no2;
		int i=0;
		for (i=0; i<4; i++) {
			if(NO2>=no2L[i] && NO2<=no2H[i])
				break;
		}
		no2=(iH[i]-iL[i])/(no2H[i]-no2L[i])*(NO2-no2L[i])+iL[i];
		return (int)no2;
	}
	// 미세먼지 구간부터 계산법이 바뀌어서 대략적인 계산함. (오차범위 존재)
	public int getPM10Cai() {
		double pm10;
		int i=0;
		for (i=0; i<4;i++ ) {
			if(PM10>=pm10L[i] && PM10<=pm10H[i])
				break;
			
		}
		
		pm10=(double)(iH[i]-iL[i])/(double)(pm10H[i]-pm10L[i])*(((double)PM10*0.8)-pm10L[i])+iL[i];

		return (int) Math.round(pm10);
	}
	// 초미세먼지도 계산법이 바뀌어서 대략적인 계산함.(오차범위 존재)
		public int getPM25Cai() {
			double pm25;
			int i=0;
			for (i=0; i<4;i++ ) {
				if(PM25>=pm25L[i] && PM25<=pm25H[i])
					break;
				
			}
			
			pm25=(double)(iH[i]-iL[i])/(double)(pm25H[i]-pm25L[i])*(((double)PM25*0.8)-pm25L[i])+iL[i];
		
			return (int) Math.round(pm25);
		}
	
}
