package org.elasticsearch.index.analysis;

/**
 * AnalysisPlugin 
 * Plugins that register custom analysis components should implement AnalysisPlugin and remove their onModule(AnalysisModule) implementation.
 * https://www.elastic.co/guide/en/elasticsearch/reference/5.1/breaking_50_plugins.html#_analysisplugin
 * @author homolo
 * @date 2016年12月14日
 */
//public class JiebaAnalysisBinderProcessor extends Plugin implements AnalysisPlugin {
//
//    @Override
//    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
//        tokenFiltersBindings.processTokenFilter("jieba",
//                JiebaTokenFilterFactory.class);
//        super.processTokenFilters(tokenFiltersBindings);
//    }
//
//    @Override
//    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
//        analyzersBindings.processAnalyzer("jieba", JiebaAnalyzerProvider.class);
//        super.processAnalyzers(analyzersBindings);
//    }
//
//}

public class JiebaAnalysisBinderProcessor {


}