package org.elasticsearch.index.analysis;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class JiebaAnalyzerProvider extends AbstractIndexAnalyzerProvider<JiebaAnalyzer> {
	
	private final JiebaAnalyzer analyzer;

	@Inject
	public JiebaAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings,String segMode) {
		super(indexSettings, name, settings);
		analyzer = new JiebaAnalyzer(env,segMode);
	}

	public static JiebaAnalyzerProvider getJiebaIndexAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaAnalyzerProvider(indexSettings, env, name, settings, "index" );
	}
	
	public static JiebaAnalyzerProvider getJiebaSearchAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaAnalyzerProvider(indexSettings, env, name, settings, "search" );
	}
	
	public static JiebaAnalyzerProvider getJiebaOtherAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaAnalyzerProvider(indexSettings, env, name, settings, "other" );
	}

	@Override
	public JiebaAnalyzer get() {
		return this.analyzer;
	}
}
