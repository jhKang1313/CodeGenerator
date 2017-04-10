package douzone.jhkang.query;

public class Wizard {
	public static void run(){
		QueryAnalyzer anal = new QueryAnalyzer(new QueryReader("select").getQuery());
		anal.parse();
		
	}
}
