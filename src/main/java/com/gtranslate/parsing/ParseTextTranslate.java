package com.gtranslate.parsing;

import com.google.gson.Gson;
import com.gtranslate.URLCONSTANTS;
import com.gtranslate.text.Text;
import com.gtranslate.text.TextTranslate;
import com.gtranslate.utils.WebUtils;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParseTextTranslate implements Parse {
	private Gson gson;
	private TextTranslate textTranslate;
	private StringBuilder url;

	public ParseTextTranslate(TextTranslate textTranslate) {
		this.textTranslate = textTranslate;
		this.gson = new Gson();
	}

	@Override
	public void appendURL() {
		Text input = textTranslate.getInput();
		Text output = textTranslate.getOutput();
		url = new StringBuilder(URLCONSTANTS.GOOGLE_TRANSLATE_TEXT);
		url.append("q=" + input.getText().replace(" ", "%20"));
		url.append("&oe=UTF-8");
		url.append("&tl=" + output.getLanguage());
		url.append("&client=gtx");
		url.append("&sl=" + input.getLanguage());
		url.append("&dt=t");
	}

	public TextTranslate getTextTranslate() {
		return textTranslate;
	}

	@Override
	public void parse() {
		appendURL();
		String result = WebUtils.source(url.toString());

		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<ArrayList<String>>> map = gson.fromJson(result, ArrayList.class);
		StringBuilder translation = new StringBuilder();
		for (ArrayList<String> sentenceData : map.get(0)) {
			translation.append(sentenceData.get(0));
		}
		Text output = textTranslate.getOutput();

		output.setText(translation.toString());
	}
}
