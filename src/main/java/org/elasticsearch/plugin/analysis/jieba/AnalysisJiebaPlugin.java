package org.elasticsearch.plugin.analysis.jieba;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.JiebaAnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

public class AnalysisJiebaPlugin extends Plugin implements AnalysisPlugin{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
		Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();
		extra.put("jieba", JiebaAnalyzerProvider::getJiebaAnalyzerProvider);
		return extra;
	}

}
