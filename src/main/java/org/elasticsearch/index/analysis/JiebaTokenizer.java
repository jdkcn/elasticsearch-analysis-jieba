/**
 * Project:elasticsearch-analysis-jieba
 * File:JiebaTokenizer.java
 * Copyright 2004-2016 Homolo Co., Ltd. All rights reserved.
 */
package org.elasticsearch.index.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.AttributeFactory;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

/**
 * @author homolo
 * @date 2016年12月14日
 */
public final class JiebaTokenizer extends Tokenizer {
	
	private final Logger logger = LogManager.getLogger(JiebaTokenizer.class);

	private String segMode;
	
	private Iterator<SegToken> iterator;

	private final JiebaSegmenter segmenter;
	private final CharTermAttribute termAtt;
	private final OffsetAttribute offsetAtt;
	private final TypeAttribute typeAtt;

	public JiebaTokenizer(String segMode) {
		super();
		this.segMode = segMode;
		segmenter = new JiebaSegmenter();
		termAtt = addAttribute(CharTermAttribute.class);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
	}

	public JiebaTokenizer(String segMode,AttributeFactory factory) {
		super(factory);
		this.segMode = segMode;
		segmenter = new JiebaSegmenter();
		termAtt = addAttribute(CharTermAttribute.class);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
	}

	public boolean incrementToken() throws IOException {
		
		clearAttributes();
		if (iterator == null) {
			String inputString = org.apache.commons.io.IOUtils.toString(input);
			List<SegToken> segTokenList = new ArrayList<SegToken>();
			if(segMode.equals("other")){
				char[] ctoken = inputString.toCharArray();
				for (int i = 0; i < ctoken.length; i++) {
					/* 全角=>半角 */
					if (ctoken[i] > 0xFF00 && ctoken[i] < 0xFF5F){
						ctoken[i] = (char) (ctoken[i] - 0xFEE0);
					}
					/* 大写=>小写 */
					if (ctoken[i] > 0x40 && ctoken[i] < 0x5b){
						ctoken[i] = (char) (ctoken[i] + 0x20);
					}
				}
				String token = String.valueOf(ctoken);
				segTokenList.add(new SegToken(token, 0, token.length()));
			}else if(segMode.equals("index")){
				segTokenList = segmenter.process(inputString, SegMode.INDEX);
			}else if(segMode.equals("search")){
				segTokenList = segmenter.process(inputString, SegMode.SEARCH);
			}
			logger.info("segMode is {}",segMode);
			//logger.info("segTokenList is {}",segTokenList.toString());
			iterator = segTokenList.iterator();
		}
		if (iterator.hasNext()) {
			SegToken token = iterator.next();
			offsetAtt.setOffset(token.startOffset, token.endOffset);
			String tokenString = token.word;
			termAtt.copyBuffer(tokenString.toCharArray(), 0, tokenString.length());
			typeAtt.setType("word");
			return true;

		}
		return false;
	}

	@Override
	public void reset() throws IOException {
		super.reset();
		iterator = null;
	}

	@Override
	public void end() {
	}

}
