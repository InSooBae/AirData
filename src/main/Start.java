package main;

import Ui.Main;
import db.AirData;

public class Start {

	public static void main(String[] args) {
		new Main();
		
		AirData a = new AirData();
		a.createTable();
		a.insertAirData();
		
		a=null;
	}
}
