package douzone.jhkang.last;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QueryReader {
	private String query = "";
	private BufferedReader reader;
	
	public String getQuery(){
		return query;
	}
	public void read(File file){
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine()) != null){
				query += line + "\n";
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void read(String fileName){		//for Test -> local file Read
		try{
			reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while((line = reader.readLine()) != null){
				query += line + "\n";
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
