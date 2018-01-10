package vn.edu.uit.Onto_Labelled_LDA.tool;

import java.util.List;

import vn.edu.uit.Onto_Labelled_LDA.adapter.TextFileAdapter;

public class StopwordFilter {

	private static final String STOPWORDS_DATAFILE_PATH = System.getProperty("user.dir")
			+ "/data/nlp/stopwords/en_stopwords.txt";

	private TextFileAdapter textFileAdapter;
	private List<String> stopwordList;

	public StopwordFilter() {
		this.textFileAdapter = new TextFileAdapter();
		this.initStopwordList();
	}
	
	//isStopword
	public boolean isStopword(String token) {
		
		if(this.stopwordList.contains(token.trim())) {
			return true;
		}
		
		return false;
	}

	private void initStopwordList() {
		this.stopwordList = this.textFileAdapter.parse_single_file_to_list_of_string(STOPWORDS_DATAFILE_PATH);
		System.out.println("Init stopwords data -> [" + this.stopwordList.size() + "] words !");
	}

}
