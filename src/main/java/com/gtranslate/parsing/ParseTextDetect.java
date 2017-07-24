package com.gtranslate.parsing;

import com.google.gson.Gson;
import com.gtranslate.URLCONSTANTS;
import com.gtranslate.text.Text;
import com.gtranslate.utils.WebUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseTextDetect implements Parse {
	private Gson gson;
	private Text input;
	private StringBuilder url;

	public ParseTextDetect(Text input) {
		this.input = input;
		this.gson = new Gson();
	}

	@Override
	public void appendURL() {
		url = new StringBuilder(URLCONSTANTS.GOOGLE_TRANSLATE_TEXT);
		url.append("q=" + input.getText().replace(" ", "%20"));
		url.append("&oe=UTF-8");
		url.append("ie=UTF-8");
		url.append("&tl=en");
		url.append("&client=gtx");
		url.append("&sl=auto");
		url.append("&dt=t");
	}

	@Override
	public void parse() {

		appendURL();
		String result = WebUtils.source(url.toString());

		@SuppressWarnings("unchecked")
		ArrayList<String> map = gson.fromJson(result, ArrayList.class);
		String inputLanguage = map.get(2);

		input.setLanguage(inputLanguage);
	}

}
