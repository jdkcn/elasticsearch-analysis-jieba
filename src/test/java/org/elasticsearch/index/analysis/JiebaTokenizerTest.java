/**
 * Project:elasticsearch-analysis-jieba
 * File:JiebaTokenizerTest.java
 * Copyright 2004-2016 Homolo Co., Ltd. All rights reserved.
 */
package org.elasticsearch.index.analysis;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.junit.Test;

/**
 * @author homolo
 * @date 2016年12月14日
 */
public class JiebaTokenizerTest {
	
	Path dataPath = new File(System.getProperty("basedir"), "src/main/dic").toPath();
    String[] sentences = new String[] {"盘山县吴家镇水利服务站诉于凤东供用水合同纠纷一审民事裁定书"};

    @Test
	public void testA() throws IOException {
    	JiebaTokenizer tokenizer = new JiebaTokenizer();
    	for (String sentence : sentences) {
    		tokenizer.setReader(new StringReader(sentence));
			tokenizer.reset();
			while (tokenizer.incrementToken()) {
				CharTermAttribute termAtt = tokenizer.getAttribute(CharTermAttribute.class);
				OffsetAttribute offsetAtt = tokenizer.getAttribute(OffsetAttribute.class);
				System.out.println(termAtt.toString() + "," + offsetAtt.startOffset() + "," + offsetAtt.endOffset());
			}
			tokenizer.reset();
		}
    	tokenizer.close();
	}
    
    

//    @Test
//    public void testB() throws IOException {
//    	SentenceTokenizer tokenizer = new SentenceTokenizer();
//		for (String sentence : sentences) {
//			tokenizer.setReader(new StringReader(sentence));
//			tokenizer.reset();
//			while (tokenizer.incrementToken()) {
//				CharTermAttribute termAtt = tokenizer.getAttribute(CharTermAttribute.class);
//				OffsetAttribute offsetAtt = tokenizer.getAttribute(OffsetAttribute.class);
//				System.out.println(termAtt.toString() + "," + offsetAtt.startOffset() + "," + offsetAtt.endOffset());
//			}
//			tokenizer.reset();
//		}
//		tokenizer.close();
//	}
    
 

}
