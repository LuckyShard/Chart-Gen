package br.com.ifrn.poo.chart.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FacebookController { 
	public void postIt(String token, String path, String name_chart,String message, PhotoFile ph) throws FileNotFoundException{
		String a_token = token;
		ArrayList<PhotoFile> t_list = new ArrayList<PhotoFile>();
		t_list.add(ph);
		
		@SuppressWarnings("deprecation")
		FacebookClient f = new DefaultFacebookClient(a_token);
		FileInputStream fi = new FileInputStream(new File(path + name_chart));
		@SuppressWarnings("deprecation")
		FacebookType resp = f.publish("me/photos",FacebookType.class,BinaryAttachment.with(name_chart, fi)
		,Parameter.with("message", message),Parameter.with("tags", t_list));
	}

}
