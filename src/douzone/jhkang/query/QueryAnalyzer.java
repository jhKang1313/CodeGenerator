package douzone.jhkang.query;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;

public class QueryAnalyzer {
	
	private String originQuery;
	private String trimQuery;
	private TGSqlParser sqlParser;
	public QueryAnalyzer(String query){
		this.originQuery = query;
		this.trimQuery = query.replaceAll("[\\{|\\}|@]", "");
		sqlParser = new TGSqlParser(EDbVendor.dbvoracle);
	}
	public TCustomSqlStatement parse(){
		sqlParser.sqltext = trimQuery;
		if((sqlParser.parse()) == 0){
			return sqlParser.sqlstatements.get(0);
		}else{
			sqlParser.getErrormessage();
			return null;
		}
	}
	public String getOriginQuery(){
		return this.originQuery;
	}
}
