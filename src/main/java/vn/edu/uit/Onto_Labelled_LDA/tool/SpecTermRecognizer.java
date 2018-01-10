package vn.edu.uit.Onto_Labelled_LDA.tool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecTermRecognizer {

	private StanfordNLPTool stanfordNLPTool = new StanfordNLPTool();
	private StopwordFilter stopwordFilter = new StopwordFilter();

	private static final String currentUserDirPath = System.getProperty("user.dir");
	private List<String> dicData = new ArrayList<>();

	public SpecTermRecognizer() {

		dicData.addAll(
				new DictionaryInitializer(currentUserDirPath + "/data/nlp/dictionary/computing-dic_en.txt").getData());

		dicData.addAll(
				new DictionaryInitializer(currentUserDirPath + "/data/nlp/dictionary/artificial_intelligence.txt")
						.getData());
		dicData.addAll(
				new DictionaryInitializer(currentUserDirPath + "/data/nlp/dictionary/data_mining.txt").getData());

		// remove duplications
		Set<String> hs = new HashSet<>();
		hs.addAll(dicData);
		dicData.clear();
		dicData.addAll(hs);

		System.out.println("**Info: loading dictionary data: " + this.dicData.size() + " (records)");
		System.out.println("**Info: longest term's length: " + this.getLongestTermLength() + " (words)");
	}

	public String process(String input, boolean is_remove_stopword) {

		if (dicData.size() > 0) {

			int maxTermLength = this.getLongestTermLength();

			StringBuilder sb = new StringBuilder();

			// pre-process
			String preprocessed_text = input.toLowerCase();
			preprocessed_text = this.stanfordNLPTool.analyze_text(preprocessed_text);
			
			// tokenizing
			String[] splits = preprocessed_text.split(" ");

			List<String> segments = new ArrayList<>();
			if (is_remove_stopword) {
				for (String split : splits) {
					if (!this.stopwordFilter.isStopword(split)) {
						segments.add(split);
					}
				}
			}

			int current_pos = 0;

			while (current_pos < segments.size()) {

				String combinedTerm = segments.get(current_pos);

				List<String> termStack = new ArrayList<>();

				termStack.add(combinedTerm);

				for (int j = 1; j <= maxTermLength; j++) {

					if ((current_pos + j) < segments.size()) {

						combinedTerm = combinedTerm + "_" + segments.get(current_pos + j);

						if (this.dicData.contains(combinedTerm.toLowerCase())) {
							termStack.add(combinedTerm);
						} else {
							continue;
						}
					} else {
						break;
					}

				}
				if (termStack.size() > 1) {
					sb.append(termStack.get(termStack.size() - 1)).append(" ");
					current_pos = current_pos + termStack.size();
				} else {
					sb.append(segments.get(current_pos)).append(" ");
					current_pos++;
				}

			}
			
			String output_string = sb.toString().replaceAll("[^A-Za-z0-9_ ]", "");
			
			return output_string;
		}
		
		return input;
	}

	private int getLongestTermLength() {
		int maxLength = 0;
		for (String dicTerm : dicData) {
			if (dicTerm.split("_").length > maxLength) {
				maxLength = dicTerm.split("_").length;
			}
		}
		return maxLength;
	}
}
