package mg.cnaps.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import mg.cnaps.services.CsLogService;


public class ReferenceUtil {
	
	@Autowired
	CsLogService service;
	
	
	
	public  static String convertDateToString(Date daty) {
		DateFormat df = new SimpleDateFormat("ddMMyy");
		return df.format(daty);
	}
	
	public static  String getReferenceDemande(String prestation,long sequence,String DR){
		Date daty = new Date();
		String datyy = convertDateToString(daty);
		return prestation+datyy+String.format("%04d",sequence )+DR;
	}
	
	
	public static String getDateNow(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date daty = new Date();
		
		return df.format(daty);
	}
	
	public static String itemCIE() {
		return "rep";
	}
	
	
}
