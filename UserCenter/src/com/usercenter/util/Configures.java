package com.usercenter.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class Configures {

	protected static Configures configures=null;
	
	private Map<String, String> params = new HashMap<String, String>();
	
	public Configures() {
		// TODO Auto-generated constructor stub
		if (configures == null) {
			// Get conf.properties
			try {
				
				ResourceBundle bundle = ResourceBundle.getBundle("confs");
				Set<String> keys=bundle.keySet();
				
				for (String key : keys) {
					String value=bundle.getString(key);
					System.out.println(value);
					
					params.put(key, value);
				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public String getParam(String key){
		
		if (params.containsKey(key))
			return params.get(key);
		else
			return null;
	}
	
	public static Configures getInstance() {
		if (configures == null) {
			synchronized (Configures.class) {
				if (configures == null) {
					configures = new Configures();
				}
			}
		}
		return configures;
	}
}
