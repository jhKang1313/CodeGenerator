package douzone.jhkang.query;

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
	public QueryReader(File file){
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
	public QueryReader(String testKind){		//for Test -> local file Read
		try{
			if(testKind.equals("select")){
				reader = new BufferedReader(new FileReader("select.txt"));
				String line = "";
				while((line = reader.readLine()) != null){
					query += line + "\n";
				}
			}else if(testKind.equals("insert")){
				
			}else if(testKind.equals("update")){
				
			}else if(testKind.equals("delete")){
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
