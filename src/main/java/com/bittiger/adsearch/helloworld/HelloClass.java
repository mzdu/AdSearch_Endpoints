package com.bittiger.adsearch.helloworld;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.AttributeFactory;

public class HelloClass {
    public String message = "Hello";

    public HelloClass () {
    }

    public HelloClass (String queryStr) {
    	
		List<String> tokens = new ArrayList<String>();
		AttributeFactory factory = AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY;
		Tokenizer tokenizer = new StandardTokenizer(factory);
		tokenizer.setReader(new StringReader(queryStr));
		CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
	    TokenStream tokenStream = new StopFilter(tokenizer, stopWords);
	    StringBuilder sb = new StringBuilder();
	    CharTermAttribute charTermAttribute = tokenizer.addAttribute(CharTermAttribute.class);
	    try {
	    	tokenStream.reset();
	        while (tokenStream.incrementToken()) {
	            String term = charTermAttribute.toString();
	            
	            tokens.add(term);
	            sb.append(term + " /");
	        }
	        tokenStream.end();
	        tokenStream.close();

	        tokenizer.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("QU="+ sb.toString());
//		return tokens;	
		String st1 = "Output= " + sb.toString();
//		return st1;
        this.message = "Parser->" + st1 + " !";
    }

    public String getMessage() {
        return message;
    }
}
