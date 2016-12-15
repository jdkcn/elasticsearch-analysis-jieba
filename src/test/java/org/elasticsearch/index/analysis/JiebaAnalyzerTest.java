package org.elasticsearch.index.analysis;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.junit.Test;

public class JiebaAnalyzerTest {
	Path dataPath = new File(System.getProperty("basedir"), "src/main/dic").toPath();
    String[] sentences = new String[] {"盘山县吴家镇水利服务站诉于凤东供用水合同纠纷一审民事裁定书"};

    @Test
	public void test() throws IOException {
		JiebaAnalyzer analyzer = new JiebaAnalyzer(dataPath,"search");

		for (String sentence : sentences) {
			TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(sentence));
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				CharTermAttribute termAtt = tokenStream.getAttribute(CharTermAttribute.class);
				OffsetAttribute offsetAtt = tokenStream.getAttribute(OffsetAttribute.class);
				System.out.println(termAtt.toString() + "," + offsetAtt.startOffset() + "," + offsetAtt.endOffset());
			}
			tokenStream.reset();
		}

		analyzer.close();
	}

//    @Test
//    public void testSegModeOther() throws IOException {
//    	JiebaAnalyzer analyzer = new JiebaAnalyzer("index", dataPath, true);
//
//        for (String sentence : sentences) {
//            TokenStream tokenStream = analyzer.tokenStream(null,
//                    new StringReader(sentence));
//            tokenStream.reset();
//            while (tokenStream.incrementToken()) {
//                CharTermAttribute termAtt = tokenStream
//                        .getAttribute(CharTermAttribute.class);
//                OffsetAttribute offsetAtt = tokenStream
//                        .getAttribute(OffsetAttribute.class);
//                System.out
//                        .println(termAtt.toString() + ","
//                                + offsetAtt.startOffset() + ","
//                                + offsetAtt.endOffset());
//            }
//            tokenStream.reset();
//        }
//        
//        analyzer.close();
//    }
//
//    @Test
//    public void testBugSentences() throws IOException {
//        String[] bugSentences = new String[] { "干脆就把那部蒙人的闲法给废了拉倒！RT @laoshipukong : 27日，全国人大常委会第三次审议侵权责任法草案，删除了有关医疗损害责任“举证倒置”的规定。在医患纠纷中本已处于弱势地位的消费者由此将陷入万劫不复的境地。 " };
//        JiebaAnalyzer analyzer = new JiebaAnalyzer("index", dataPath, true);
//
//        for (String sentence : bugSentences) {
//            TokenStream tokenStream = analyzer.tokenStream(null,
//                    new StringReader(sentence));
//            tokenStream.reset();
//            while (tokenStream.incrementToken()) {
//                CharTermAttribute termAtt = tokenStream
//                        .getAttribute(CharTermAttribute.class);
//                OffsetAttribute offsetAtt = tokenStream
//                        .getAttribute(OffsetAttribute.class);
//                System.out
//                        .println(termAtt.toString() + ","
//                                + offsetAtt.startOffset() + ","
//                                + offsetAtt.endOffset());
//            }
//            tokenStream.reset();
//        }
//
//        analyzer.close();
//    }
    
//    @Test
//    public void testLoadDict() throws IOException {
//    	JiebaAnalyzer analyzer = new JiebaAnalyzer("index", dataPath, true);
//    	
//    	String[] sentences = new String[] {
//    		"我剛買了一個 16GB 的 USB 隨身碟",
//    		"我剛買了一個 16GBUSB 隨身碟",
//            "今天有iphone6和nexus5的大拍賣"
//    	};
//    	
//        for (String sentence : sentences) {
//            TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(sentence));
//            tokenStream.reset();
//            System.out.println(sentence);
//            while (tokenStream.incrementToken()) {
//                CharTermAttribute termAtt = tokenStream.getAttribute(CharTermAttribute.class);
//                OffsetAttribute offsetAtt = tokenStream.getAttribute(OffsetAttribute.class);
//                System.out.println(
//                	termAtt.toString() + "," +
//                	offsetAtt.startOffset() + "," +
//                    offsetAtt.endOffset()
//                );
//            }
//            System.out.println();
//            tokenStream.reset();
//        }    	
//    	
//    	analyzer.close();
//    }
}
