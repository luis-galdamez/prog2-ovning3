package se.su.ovning3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();


	public void exportRecordings(String fileName) {
		File file = new File(fileName);
		try(PrintWriter fileWriter = new PrintWriter(file)){
		
			int recordingsSize = recordings.size();
			fileWriter.println(recordingsSize);
			
			for(Recording r: recordings){
				fileWriter.println("<recording>");
				fileWriter.println("\t<artist>" + r.getArtist() + "</artist>");
				fileWriter.println("\t<title>" + r.getTitle() + "</title>");
				fileWriter.println("\t<year>" + r.getYear() + "</year>");
				fileWriter.println("\t<genres>");
				for(String g: r.getGenre()){
					fileWriter.println("\t\t<genre>" + g + "</genre>");
					
				}
				fileWriter.println("\t</genres>");
				fileWriter.println("</recording>");
			}
		}
		catch(IOException e){
			System.out.println(fileName + " Error while writing on the file");
		}
	}

	public void importRecordings(String fileName) {

		File file = new File(fileName);

		try(FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader)){

			String line;
			bufferedReader.readLine();
			while((line = bufferedReader.readLine()) != null){
				String[] parts = line.split(";");
				int year = Integer.parseInt(parts[2].trim());
				String numberOfGenresStr = bufferedReader.readLine();
				int numberOfGenres = Integer.parseInt(numberOfGenresStr);
				Set<String> genres = new HashSet<>();
				for(int i = 0; i < numberOfGenres; i++){
					genres.add(bufferedReader.readLine());
				}
				Recording r = new Recording(parts[0].trim(), parts[1].trim(), year, genres);
				recordings.add(r);
			}
		}
		catch(IOException e){
			System.out.println(fileName + " File not found");
		}
	}

	public Map<Integer, Double> importSales(String fileName) {	
		File file = new File(fileName);
		Map<Integer, Double> importSales = new HashMap<>();

		try(FileInputStream fileInput = new FileInputStream(file);
			DataInputStream dataInput = new DataInputStream(fileInput)){

				int poster = dataInput.readInt();
				for(int i = 0; i < poster; i++){
					int year = dataInput.readInt();
					int month = dataInput.readInt();
					int day = dataInput.readInt();
					double value = dataInput.readDouble();

					int key = year * 100 + month;
					if(!importSales.containsKey(key)){
						importSales.put(key, value);
					} else {
						double currentValue = importSales.get(key);
						currentValue += value;
						importSales.put(key, currentValue);
					}
				}
			}
			
		catch(IOException e){
			System.out.println(fileName + "Cannot read file");
		}
		
		return importSales;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}

