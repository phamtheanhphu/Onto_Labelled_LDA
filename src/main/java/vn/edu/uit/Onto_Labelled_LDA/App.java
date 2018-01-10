package vn.edu.uit.Onto_Labelled_LDA;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import vn.edu.uit.Onto_Labelled_LDA.adapter.TextFileAdapter;
import vn.edu.uit.Onto_Labelled_LDA.tool.SpecTermRecognizer;

public class App {

	private static final String PROJECT_CUR_DIR = System.getProperty("user.dir");
	private static final String TOPIC_ID_NAME_MAPS_FILE_PATH = PROJECT_CUR_DIR
			+ "/data/training_set/topic_id_name_maps.txt";
	private static final String TRAINING_SET_FILE_PATH = PROJECT_CUR_DIR
			+ "/data/training_set/corpus_topic-id_doc-id_doc-content.txt";

	private static final String ACM_LLDA_MODEL_DIR_PATH = PROJECT_CUR_DIR + "/data/acm_llda_model";
	private static final String ACM_LLDA_MODEL_DOC_FILE_PATH = ACM_LLDA_MODEL_DIR_PATH + "/acm_model";

	private static final String CORPUS_DIR_PATH = "E:\\data\\experiments\\6_topics_1500";

	private static SpecTermRecognizer specTermRecognizer = new SpecTermRecognizer();
	private static TextFileAdapter textFileAdapter = new TextFileAdapter();

	public static void main(String[] args) {
		generate_DOI_list();
	}

	private static void generate_DOI_list() {
		
		String dataset_file_path = "C:\\Users\\phu.pham\\Desktop\\dblp_publishes_id_doi.txt";
		HashMap<Integer, String> doc_id_doi_maps = textFileAdapter.file_to_hashmap_int_string(dataset_file_path, 
				0, 1);
		File[] topicDirList = new File(CORPUS_DIR_PATH).listFiles();
		
		List<String> output_strings = new ArrayList<>();
		
		for (File topicDir : topicDirList) {


			if (topicDir.isDirectory()) {

				File[] documents = new File(topicDir.getAbsolutePath()).listFiles();

				for (File document : documents) {

					int doc_id = Integer.parseInt(document.getName().replaceAll(".txt", ""));
					String output_string = "\"" + doc_id + "\"" +"\t" + "\""+ doc_id_doi_maps.get(doc_id) + "\"";
					output_strings.add(output_string);

				}
			}
		}
		String outputDataFilePath = "E:\\data\\experiments\\full_length_pdf_files\\6_topics_1500\\paper_id_doi_list.txt";
		textFileAdapter.write_list_string_to_single_data_file(output_strings, outputDataFilePath);

	}

	/*
	 * private static void generate_LLDA_corpus() {
	 * 
	 * HashMap<Integer, Set<String>> topicId_docContent_maps =
	 * textFileAdapter.file_to_hashmap_int_setOfString( TRAINING_SET_FILE_PATH,
	 * 0, 2);
	 * 
	 * List<String> output_strings = new ArrayList<>();
	 * 
	 * for (Entry<Integer, Set<String>> entry :
	 * topicId_docContent_maps.entrySet()) {
	 * 
	 * int topic_id = entry.getKey();
	 * 
	 * for(String doc_content : entry.getValue()) {
	 * 
	 * //text pre-processing doc_content =
	 * specTermRecognizer.process(doc_content, true); String output_string = "["
	 * + topic_id + "]" + " " + doc_content.trim();
	 * output_strings.add(output_string);
	 * 
	 * //System.out.println(doc_content);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * textFileAdapter.write_list_string_to_single_data_file(output_strings,
	 * ACM_LLDA_MODEL_DOC_FILE_PATH);
	 * 
	 * }
	 */

	private static void prepare_dataset() {

		List<String> output_strings = new ArrayList<>();

		HashMap<String, Integer> reversed_topic_name_id_maps = textFileAdapter
				.file_to_hashmap_string_int(TOPIC_ID_NAME_MAPS_FILE_PATH, 1, 0);

		File[] topicDirList = new File(CORPUS_DIR_PATH).listFiles();

		for (File topicDir : topicDirList) {

			int topic_id = reversed_topic_name_id_maps.get(topicDir.getName());

			if (topicDir.isDirectory()) {

				File[] documents = new File(topicDir.getAbsolutePath()).listFiles();

				for (File document : documents) {

					int doc_id = Integer.parseInt(document.getName().replaceAll(".txt", ""));
					String doc_content = textFileAdapter.parse_single_file_to_string(document.getAbsolutePath());

					// text pre-processing
					doc_content = specTermRecognizer.process(doc_content, true);
					System.out.println(doc_content);

					String output_string = topic_id + "\t" + doc_id + "\t" + "\"" + doc_content.trim() + "\"";
					output_strings.add(output_string);

				}
			}
		}

		textFileAdapter.write_list_string_to_single_data_file(output_strings, TRAINING_SET_FILE_PATH);

	}

}
