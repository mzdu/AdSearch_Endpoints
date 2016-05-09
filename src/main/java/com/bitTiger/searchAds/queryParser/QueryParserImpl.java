package com.bitTiger.searchAds.queryParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.AttributeFactory;

public class QueryParserImpl implements QueryParser{

  @Override
  public List<String> parseQuery(String queryStr) {
    // tokenize queryStr, remove stop word, stemming
	List<String> tokens = new ArrayList<String>();
	AttributeFactory factory = AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY;
	Tokenizer tokenizer = new StandardTokenizer(factory);
	tokenizer.setReader(new StringReader(queryStr));
	CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
    TokenStream tokenStream = new StopFilter(tokenizer, stopWords);
//    StringBuilder sb = new StringBuilder();
    CharTermAttribute charTermAttribute = tokenizer.addAttribute(CharTermAttribute.class);
    try {
    	tokenStream.reset();
        while (tokenStream.incrementToken()) {
            String term = charTermAttribute.toString();
            
            tokens.add(term);
//            sb.append(term + " ");
        }
        tokenStream.end();
        tokenStream.close();

        tokenizer.close();  
	} catch (IOException e) {
		e.printStackTrace();
	}
//	System.out.println("QU="+ sb.toString());
	return tokens;	
  }  
}

