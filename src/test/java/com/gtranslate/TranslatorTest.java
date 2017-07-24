package com.gtranslate;

import com.google.gson.Gson;
import javazoom.jl.decoder.JavaLayerException;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class TranslatorTest {
	Translator translator = Translator.getInstance();

	@Test
	public void testDetectLanguage() {
		String language = translator.detect("Hello World");
		Assert.assertEquals("en", language);
	}

	@Test
	public void testPlayingAudio() throws IOException, JavaLayerException {
		Audio audio = Audio.getInstance();
		InputStream sound = audio.getAudio("你好世界", Language.CHINESE_SIMPLIFIED);
		audio.play(sound);

		Assert.assertTrue(sound != null);
	}

	@Test
	public void testTranslateText() {
		String text = translator.translate("I am programmer", Language.ENGLISH, Language.PORTUGUESE);
		Assert.assertEquals("Eu sou programador", text);
	}

}