package vn.edu.uit.Onto_Labelled_LDA.adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextFileAdapter {

	public TextFileAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	// file_to_hashmap_int_setOfString
	public HashMap<Integer, Set<String>> file_to_hashmap_int_setOfString(String dataFilePath, int key_col,
			int val_col) {

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFilePath)))) {

			HashMap<Integer, Set<String>> results = new HashMap<>();

			String line;

			while ((line = reader.readLine()) != null) {

				try {

					String[] splits = line.split("\t");

					try {

						Integer key = Integer.parseInt(splits[key_col].replaceAll("\"", ""));
						String value = splits[val_col].replaceAll("\"", "");

						if (!results.containsKey(key)) {
							Set<String> values = new HashSet<>();
							values.add(value);
							results.put(key, values);
						} else {
							results.get(key).add(value);
						}

					} catch (NumberFormatException e) {
						continue;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}

			return results;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// file_to_hashmap_string_int
	public HashMap<String, Integer> file_to_hashmap_string_int(String dataFilePath, int key_col, int val_col) {

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFilePath)))) {

			HashMap<String, Integer> results = new HashMap<>();
			String line;

			while ((line = reader.readLine()) != null) {

				try {

					String[] splits = line.split("\t");

					try {

						String key = splits[key_col].replaceAll("\"", "");
						Integer value = Integer.parseInt(splits[val_col].replaceAll("\"", ""));

						results.put(key, value);

					} catch (NumberFormatException e) {
						continue;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}

			return results;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// file_to_hashmap_int_string
	public HashMap<Integer, String> file_to_hashmap_int_string(String dataFilePath, int key_col, int val_col) {

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFilePath)))) {

			HashMap<Integer, String> results = new HashMap<>();
			String line;

			while ((line = reader.readLine()) != null) {

				try {

					String[] splits = line.split("\t");

					try {

						Integer key = Integer.parseInt(splits[key_col].replaceAll("\"", ""));
						String value = splits[val_col].replaceAll("\"", "");

						results.put(key, value);

					} catch (NumberFormatException e) {
						continue;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}

			return results;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// file_to_hashmap_int_int
	public HashMap<Integer, Integer> file_to_hashmap_int_int(String dataFilePath, int key_col, int val_col) {

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFilePath)))) {

			HashMap<Integer, Integer> results = new HashMap<>();
			String line;

			while ((line = reader.readLine()) != null) {

				try {

					String[] splits = line.split("\t");

					try {

						Integer id = Integer.parseInt(splits[key_col].replaceAll("\"", ""));
						Integer value = Integer.parseInt(splits[key_col].replaceAll("\"", ""));

						results.put(id, value);

					} catch (NumberFormatException e) {
						continue;
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}

			return results;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// parse_single_file_to_list_of_string
	public List<String> parse_single_file_to_list_of_string(String dataFilePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFilePath)))) {
			List<String> results = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line.trim());
				line = line.trim();
				if (line.length() > 0) {
					results.add(line);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// parse_single_file_to_string
	public String parse_single_file_to_string(String dataFilePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataFilePath)))) {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line.trim());
				line = line.trim();
				if (line.length() > 0) {
					stringBuilder.append(line).append(" ");
				}
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// write_list_string_to_single_data_file
	public void write_list_string_to_single_data_file(List<String> inputData, String outputDataFilePath) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(new File(outputDataFilePath)))) {
			for (String input : inputData) {
				writer.println(input);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// write_string_append_to_single_file
	public void write_string_append_to_single_file(String text, String inputDataFilePath) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(inputDataFilePath, true)));
			out.println(text);
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
			return;
		}
	}

}
