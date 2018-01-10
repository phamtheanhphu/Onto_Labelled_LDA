package vn.edu.uit.Onto_Labelled_LDA.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class StanfordNLPTool {

	// TODO Auto-generated method stub
	private static final String TEXT_PATTERN_REGEX = "[^A-Za-z0-9_ ]";
	
	
	private Properties props;
	private StanfordCoreNLP pipeline;

	public StanfordNLPTool() {

		super();
		
		this.props = new Properties();
		this.props.put("annotators", "tokenize, ssplit, pos, lemma");
		this.pipeline = new StanfordCoreNLP(props, false);

	}

	// analyze_text
	public String analyze_text(String text) {
		
		StringBuilder sb = new StringBuilder();
		
		Annotation document = this.pipeline.process(text);

		if (document != null) {

			for (CoreMap sentence : document.get(SentencesAnnotation.class)) {

				for (CoreLabel token : sentence.get(TokensAnnotation.class)) {

					String returnToken = token.get(TextAnnotation.class);

					returnToken = token.get(LemmaAnnotation.class);
					
					sb.append(returnToken).append(" ");

				}

			}

		}

		return sb.toString();

	}

}
