package com.gtranslate.text;

public class Text {
	private String language;
	private String text;

	public Text() {
	}

	public Text(String language) {
		this.language = language;
	}

	public Text(String text, String language) {
		this.text = text;
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public String getText() {
		return text;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setText(String text) {
		this.text = text;
	}

}
