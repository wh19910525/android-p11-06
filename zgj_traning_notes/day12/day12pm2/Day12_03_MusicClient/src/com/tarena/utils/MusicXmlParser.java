package com.tarena.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.tarena.entity.Music;

public class MusicXmlParser {
	private class MusicXmlHandler extends DefaultHandler {
		private ArrayList<Music> musics;
		private Music music;
		private String tagName;

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			String data = new String(ch, start, length);
			if ("name".equals(tagName)) {
				music.setName(data);
			} else if ("singer".equals(tagName)) {
				music.setSinger(data);
			} else if ("author".equals(tagName)) {
				music.setAuthor(data);
			} else if ("composer".equals(tagName)) {
				music.setComposer(data);
			} else if ("album".equals(tagName)) {
				music.setAlbum(data);
			} else if ("albumpic".equals(tagName)) {
				music.setAlbumPath(data);
			} else if ("musicpath".equals(tagName)) {
				music.setMusicPath(data);
			} else if ("time".equals(tagName)) {
				music.setDuration(data);
			} else if ("downcount".equals(tagName)) {
				music.setDownCount(Integer.parseInt(data));
			} else if ("favcount".equals(tagName)) {
				music.setFavCount(Integer.parseInt(data));
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			if ("music".equals(localName)) {
				musics.add(music);
			}
			tagName = null;
		}

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			musics = new ArrayList<Music>();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			tagName = localName;
			if ("music".equals(localName)) {
				music = new Music();
				music.setId(Integer.parseInt(attributes.getValue("id")));
			}
		}

	}

	public ArrayList<Music> parse(InputStream in)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		MusicXmlHandler handler = new MusicXmlHandler();
		parser.parse(in, handler);
		return handler.musics;
	}

}
