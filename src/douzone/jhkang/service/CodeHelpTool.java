package douzone.jhkang.service;

import java.util.ArrayList;
import java.util.List;

public class CodeHelpTool{
	public static void main(String[] args){
		
		List<ApiParameter> apiParameter = new ArrayList<ApiParameter>();
		
		apiParameter.add(new ApiParameter("cd_module", "cd_module", "모듈코드", "QueryString", "String"));
		apiParameter.add(new ApiParameter("cd_field", "cd_field", "코드", "QueryString", "String"));
		apiParameter.add(new ApiParameter("keyword", "keyword", "키워드", "QueryString", "String"));
		
		List<QueryParameter> queryParameter = new ArrayList<QueryParameter>();
		queryParameter.add(new QueryParameter("P_CD_MODULE", true, "cd_module"));
		queryParameter.add(new QueryParameter("P_CD_FIELD", true, "cd_field"));
		queryParameter.add(new QueryParameter("P_KEYWORD", true, "keyword"));
		
		
		CodeGenerator codeGen = new SelectCodeGenerator("csbcic00500_list_header", "통합마스터코드관리 - 조회", "GET", "Path, Query", "List", "csbcic00500_Code", apiParameter, queryParameter, "csbcic00700_list_header");
		
		System.out.println(codeGen.codeGenerate());
	}
}