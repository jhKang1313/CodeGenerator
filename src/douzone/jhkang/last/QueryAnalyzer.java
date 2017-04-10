package douzone.jhkang.last;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;

public class QueryAnalyzer {
	
	private String originQuery;
	private String trimQuery;
	public TGSqlParser sqlParser;
	public TCustomSqlStatement statement;
	public QueryAnalyzer(String query){
		this.originQuery = query;
		this.trimQuery = query.replaceAll("[\\{\\}@]", "");
		sqlParser = new TGSqlParser(EDbVendor.dbvoracle);
	}
	public void parse(){
		sqlParser.sqltext = trimQuery;
		if((sqlParser.parse()) == 0){
			statement = sqlParser.sqlstatements.get(0);
		}else{
			sqlParser.getErrormessage();
			statement = null;
		}
	}
	public String getOriginQuery(){
		return this.originQuery;
	}
}
