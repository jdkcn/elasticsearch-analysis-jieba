package org.elasticsearch.index.analysis;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class JiebaAnalyzerProvider extends AbstractIndexAnalyzerProvider<JiebaAnalyzer> {
	private final JiebaAnalyzer analyzer;

	@Inject
	public JiebaAnalyzerProvider(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings) {
		super(indexSettings, name, settings);
		analyzer = new JiebaAnalyzer(settings, env);
	}

	public static JiebaAnalyzerProvider getJiebaAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
		return new JiebaAnalyzerProvider(indexSettings, env, name, settings);
	}

	@Override
	public JiebaAnalyzer get() {
		return this.analyzer;
	}
}
