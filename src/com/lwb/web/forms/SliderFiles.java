package com.lwb.web.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class SliderFiles extends ActionForm {
	private FormFile pic1;
	private FormFile pic2;
	private FormFile pic3;
	private FormFile pic4;
	private FormFile pic5;

	public FormFile getPic1() {
		return pic1;
	}
	public void setPic1(FormFile pic1) {
		this.pic1 = pic1;
	}
	public FormFile getPic2() {
		return pic2;
	}
	public void setPic2(FormFile pic2) {
		this.pic2 = pic2;
	}
	public FormFile getPic3() {
		return pic3;
	}
	public void setPic3(FormFile pic3) {
		this.pic3 = pic3;
	}
	public FormFile getPic4() {
		return pic4;
	}
	public void setPic4(FormFile pic4) {
		this.pic4 = pic4;
	}
	public FormFile getPic5() {
		return pic5;
	}
	public void setPic5(FormFile pic5) {
		this.pic5 = pic5;
	}
}
