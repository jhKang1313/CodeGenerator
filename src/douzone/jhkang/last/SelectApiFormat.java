package douzone.jhkang.last;

import java.util.List;

public class SelectApiFormat extends ApiFormat{
	public String format;
	public SelectApiFormat(){
		
	}
	public SelectApiFormat(String url, String apiDesc, String httpMethod,String apiType, String returnType, String usingModel,List<ApiParameter> apiParameter, List<QueryParameter> queryParameter, String query) {
		super(url, apiDesc, httpMethod, apiType, returnType, usingModel, apiParameter, queryParameter, query);
		format = String.format("@DzApi(url=\"/%s\", desc=\"%s\", httpMethod=DzRequestMethod.%s)\n", url, apiDesc, httpMethod) 
				+String.format("public %s<%s> %s(\n", returnType, usingModel, url);												 
		
		for(int i = 0 ; i < apiParameter.size() ; i++){
			ApiParameter apiParam = apiParameter.get(i);
			format += String.format("\t\t@DzParam(key = \"%s\", desc = \"%s\", paramType = DzParamType.%s) %s %s", apiParam.key, apiParam.desc, apiParam.type, apiParam.dataType, apiParam.attrName);
			if(i < apiParameter.size() - 1){
				format += ",\n";
			}
		}
		format += ") throws Exception{\n"
				+ String.format("\tList<%s> items = new ArrayList<%s>();\n", usingModel, usingModel)
				+ "\ttry{\n"
				+ "\t\tHashMap<String, Object> parameters = new HashMap<String, Object>();\n";
				
		for(QueryParameter queryParam : queryParameter){
			format += queryParam.isVariable? 
					"\t\tparameters.put(\"" + queryParam.key + "\", " + queryParam.value + ");\n": 
					"\t\tparameters.put(\"" + queryParam.key + "\", " + "\"queryParam.value\");\n";
			
		}
		format += "\n";
		format += String.format("\t\tString sqlText = QueryGenerator.get(this.getClass(), \"%s.ini\", parameters);\n", query)
				+ "\t\tSqlPack so = new SqlPack();\n"
				+ "\t\tso.setStoreProcedure(false);\n"
				+ "\t\tso.setSqlText(sqlText);\n"
				+ String.format("\t\titems = this.queryForModel(so, %s.class);\n", usingModel)
				+ "\t}catch(DzApplicationRuntimeException e) {\n"
				+ "\t\tthrow e;\n"
				+ "\t}catch (Exception e) {\n"
				+ "\t\tthrow e;\n"
				+ "\t}\n"
				+ "\treturn items;\n"
				+ "}";
		
	}
}
