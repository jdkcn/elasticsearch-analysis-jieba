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
    String[] sentences = new String[] {"杭州市拱墅区人民法院\r\n民事裁定书\r\n（2015）杭拱民初字第434号\r\n原告:邹丽明，（身份证号362501197305226211）。\r\n委托代理人（特别授权代理）:金亮新。\r\n被告:刘怀波，（身份证号413027197412292734）。\r\n被告:郭枫。\r\n被告:江西省丰和营造集团有限公司。\r\n法定代表人:揭保如。\r\n委托代理人（特别授权代理）:盛军华。\r\n被告:杭州杂技总团。\r\n本院在审理原告邹丽明诉被告刘怀波、郭枫、江西省丰和营造集团有限公司、杭州杂技总团建设工程施工合同纠纷一案中，原告邹丽明以起诉主体有误为由，于2015年9月18日向本院提出撤诉申请。\r\n本院认为，原告撤诉请求，符合法律规定，应予准许。依照《中华人民共和国民事诉讼法》第一百五十四条第一款第（五）项之规定，裁定如下:\r\n准许原告邹丽明撤回起诉。\r\n案件受理费50元，减半收取25元，由原告负担。\r\n审判员孙金林\r\n二〇一五年九月十八日\r\n书记员韩瑞功"};

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
