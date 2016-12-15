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
    String[] sentences = new String[] {"杭州市拱墅区人民法院\r\n民事裁定书\r\n（2015）杭拱民初字第434号\r\n原告:邹丽明，（身份证号362501197305226211）。\r\n委托代理人（特别授权代理）:金亮新。\r\n被告:刘怀波，（身份证号413027197412292734）。\r\n被告:郭枫。\r\n被告:江西省丰和营造集团有限公司。\r\n法定代表人:揭保如。\r\n委托代理人（特别授权代理）:盛军华。\r\n被告:杭州杂技总团。\r\n本院在审理原告邹丽明诉被告刘怀波、郭枫、江西省丰和营造集团有限公司、杭州杂技总团建设工程施工合同纠纷一案中，原告邹丽明以起诉主体有误为由，于2015年9月18日向本院提出撤诉申请。\r\n本院认为，原告撤诉请求，符合法律规定，应予准许。依照《中华人民共和国民事诉讼法》第一百五十四条第一款第（五）项之规定，裁定如下:\r\n准许原告邹丽明撤回起诉。\r\n案件受理费50元，减半收取25元，由原告负担。\r\n审判员孙金林\r\n二〇一五年九月十八日\r\n书记员韩瑞功"};

    @Test
	public void testA() throws IOException {
    	JiebaTokenizer tokenizer = new JiebaTokenizer("index");
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
