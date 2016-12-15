/**
 * Project:elasticsearch-analysis-jieba
 * File:JiebaTokenizerFactory.java
 * Copyright 2004-2016 Homolo Co., Ltd. All rights reserved.
 */
package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

/**
 * @author homolo
 * @date 2016年12月14日
 */
public class JiebaTokenizerFactory extends AbstractTokenizerFactory {
	
	private final Tokenizer tokenizer;

	public JiebaTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings,String segMode) {
		super(indexSettings, name, settings);
		tokenizer = new JiebaTokenizer(segMode);
	}

	public static JiebaTokenizerFactory getJiebaIndexTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaTokenizerFactory(indexSettings, env, name, settings,"index");
	}

	public static JiebaTokenizerFactory getJiebaSearchTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaTokenizerFactory(indexSettings, env, name, settings,"search");
	}
	
	public static JiebaTokenizerFactory getJiebaOtherTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaTokenizerFactory(indexSettings, env, name, settings,"other");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tokenizer create() {
		return this.tokenizer;
	}

}
