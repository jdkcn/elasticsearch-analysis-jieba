/**
 * Project:elasticsearch-analysis-jieba
 * File:JiebaTokenizer.java
 * Copyright 2004-2016 Homolo Co., Ltd. All rights reserved.
 */
package org.elasticsearch.index.analysis;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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

	private Iterator<SegToken> iterator;

	private final JiebaSegmenter segmenter;
	private final CharTermAttribute termAtt;
	private final OffsetAttribute offsetAtt;
	private final TypeAttribute typeAtt;

	public JiebaTokenizer() {
		super();
		segmenter = new JiebaSegmenter();
		termAtt = addAttribute(CharTermAttribute.class);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
	}

	public JiebaTokenizer(AttributeFactory factory) {
		super(factory);
		segmenter = new JiebaSegmenter();
		termAtt = addAttribute(CharTermAttribute.class);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
	}

	public boolean incrementToken() throws IOException {

		clearAttributes();
		if (iterator == null) {
			String inputString = org.apache.commons.io.IOUtils.toString(input);
			List<SegToken> segTokenList = segmenter.process(inputString, SegMode.INDEX);
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
