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

	public JiebaTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		super(indexSettings, name, settings);
	}

	public static JiebaTokenizerFactory getJiebaIndexTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaTokenizerFactory(indexSettings, env, name, settings);
	}

	public static JiebaTokenizerFactory getJiebaSearchTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaTokenizerFactory(indexSettings, env, name, settings);
	}
	
	public static JiebaTokenizerFactory getJiebaOtherTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaTokenizerFactory(indexSettings, env, name, settings);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tokenizer create() {
		return new JiebaTokenizer();
	}

}
