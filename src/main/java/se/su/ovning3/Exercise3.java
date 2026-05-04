package se.su.ovning3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {



	}

	public void importRecordings(String fileName) {
		File file = new File(fileName);
		try{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			bufferedReader.readLine();
			while((line = bufferedReader.readLine()) != null){
				String[] parts = line.split(";");
				int year = Integer.parseInt(parts[2]);
				String numberOfGenresStr = bufferedReader.readLine();
				int numberOfGenres = Integer.parseInt(numberOfGenresStr);
				Set<String> genres = new HashSet<>();
				for(int i = 0; i < numberOfGenres; i++){
					genres.add(bufferedReader.readLine());
				}
				Recording r = new Recording(parts[0], parts[1], year, genres);
				recordings.add(r);
			}
			bufferedReader.close();
		}
		catch(IOException e){
			System.out.println(fileName + "File not found");
		}
	}

	public Map<Integer, Double> importSales(String fileName) {
		return null;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}

