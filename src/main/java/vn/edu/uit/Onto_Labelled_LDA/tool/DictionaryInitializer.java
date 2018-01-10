package vn.edu.uit.Onto_Labelled_LDA.tool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryInitializer {
	
	private String dataPath;
	private List<String> data = new ArrayList<>();
	
	public DictionaryInitializer(String dataPath) {
		this.dataPath = dataPath;
		this.loadData();
	}
	
	private void loadData() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(this.dataPath), "UTF-8"));
			String line = "";
			while((line = reader.readLine()) != null) {
				this.data.add(line.trim());
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	};

}
