package com.gtranslate;

import java.io.IOException;
import java.io.InputStream;

import com.gtranslate.parsing.Parse;
import com.gtranslate.parsing.ParseTextDetect;
import com.gtranslate.parsing.ParseTextTranslate;
import com.gtranslate.text.Text;
import com.gtranslate.text.TextTranslate;

import javazoom.jl.decoder.JavaLayerException;

public class Translator {
	private static Translator translator;

	public synchronized static Translator getInstance() {

		if (translator == null) {
			translator = new Translator();
		}
		return translator;

	}

	private Translator() {
	}

	public String detect(String text) {

		Text input = new Text();
		input.setText(text);
		Parse parse = new ParseTextDetect(input);
		parse.parse();
		return input.getLanguage();

	}

	public String translate(String text, String languageInput, String languageOutput) {

		Text input = new Text(text, languageInput);
		TextTranslate textTranslate = new TextTranslate(input, languageOutput);
		Parse parse = new ParseTextTranslate(textTranslate);
		parse.parse();
		return textTranslate.getOutput().getText();

	}

	public void translate(TextTranslate textTranslate) {

		Parse parse = new ParseTextTranslate(textTranslate);
		parse.parse();

	}

}
