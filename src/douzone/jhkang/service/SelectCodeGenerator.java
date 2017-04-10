package douzone.jhkang.service;

import java.util.List;

public class SelectCodeGenerator extends SelectApiFormat implements CodeGenerator{
	public SelectCodeGenerator(String url, String apiDesc, String httpMethod,String apiType, String returnType, String usingModel,List<ApiParameter> apiParameter, List<QueryParameter> queryParameter, String query) {
		super(url, apiDesc, httpMethod, apiType, returnType, usingModel, apiParameter, queryParameter, query);
	}
	public String codeGenerate() {
		return this.format;
	}
}
