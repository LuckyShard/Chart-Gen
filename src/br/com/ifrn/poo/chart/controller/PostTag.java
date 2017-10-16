package br.com.ifrn.poo.chart.controller;
import com.restfb.Facebook;
public class PostTag {
	@Facebook("tag_uid")
	private String uId;

	@Facebook("tag_text")
	private String Text;
	public PostTag(String id,String text) {
		this.uId = id;
		this.Text = text;
	}


}
