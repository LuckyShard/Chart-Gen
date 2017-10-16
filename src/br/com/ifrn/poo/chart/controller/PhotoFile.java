package br.com.ifrn.poo.chart.controller;
import com.restfb.Facebook;
public class PhotoFile {
	@Facebook("tag_uid")
	private String uId;

	@Facebook("tag_text")
	private String Text;
	public PhotoFile(String id,String text) {
		this.uId = id;
		this.Text = text;
	}


}
