package com.framgia.capstone.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
	
	public JSONParser(){
		super();
	}
	
	public boolean KiemTraDangNhap(JSONObject object){
		boolean kiemtra = false;
		
		try{
			kiemtra = object.getBoolean("Value");
		}catch(JSONException e){
			Log.d("JSON", e.getMessage());
		}
		
		return kiemtra;
		
	}

	public boolean KiemTraDK(JSONObject object){
		boolean kiemtra = false;

		try{
			kiemtra = object.getBoolean("Value");
		}catch(JSONException e){
			Log.d("JSON", e.getMessage());
		}

		return kiemtra;

	}
	
}
