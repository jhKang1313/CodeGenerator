package douzone.jhkang.last;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gudusoft.gsqlparser.nodes.TExpression;
import gudusoft.gsqlparser.nodes.TResultColumn;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

public class Wizard {
	private InputMethod inputMethod;
	private CodeGenerator codeGenerator;
	private List<QueryAnalyzer> queryAnalyzers = new ArrayList<QueryAnalyzer>();
	private QueryReader queryReader = new QueryReader();
	private String resultQuery = "";
	
	public Wizard(InputMethod inputMethod){
		this.inputMethod = inputMethod;
	}
	enum ApiRequestType {SELECT, SAVE_SINGLE, SAVE_DUO}; 
	public void run(){
		List<String> queryFiles = inputMethod.queryFileInput();
		for(String queryFile : queryFiles ){
			queryReader.read(queryFile);
			queryAnalyzers.add(new QueryAnalyzer(queryReader.getQuery())); 
		}
		switch(getApiRequestType()){
		case SELECT:
			resultQuery = selectQueryGenerate();
			break;
		case SAVE_SINGLE:
			
			break;
			
			
		case SAVE_DUO:
			
			
			break;
			
		default:
				
			break;
		}
		System.out.println(resultQuery);
		
	}
	private ApiRequestType getApiRequestType(){
		for(QueryAnalyzer queryAnalyzer : queryAnalyzers){
			queryAnalyzer.parse();
			switch(queryAnalyzer.statement.sqlstatementtype){
			case sstselect :
				return ApiRequestType.SELECT;
			case sstupdate :
				break;
			default :
				break;
			}
			
		}
		return null;
	}
	public String selectQueryGenerate(){
		TSelectSqlStatement selectStament = (TSelectSqlStatement)queryAnalyzers.get(0).statement;
		
		
		
		if(selectStament.isCombinedQuery()){
			
			
		} else {
			
			/* API Gen */
			ApiFormat apiFormat = inputMethod.apiInfoInput(new SelectApiFormat(), "GET");
			List<ApiParameter> apiParameters = null;
			List<QueryParameter> queryParameters = null;
			if (selectStament.getWhereClause() != null){
				List<String> parameters = getParameter(selectStament);
				apiParameters = inputMethod.apiParameterInput(parameters);
				queryParameters = getQueryParamter(parameters);
            }
			codeGenerator = new SelectCodeGenerator(apiFormat.url, apiFormat.apiDesc, apiFormat.httpMethod, null, "List", apiFormat.usingModel, apiParameters, queryParameters, "csbcic00500_list_header");
			resultQuery = codeGenerator.codeGenerate();
			
			/* Create Model */
			TResultColumnList columnList = selectStament.getResultColumnList();
			List<ModelField> modelFields = new ArrayList<ModelField>();
			
			for(int i = 0;  i < columnList.size() ; i++){
				TResultColumn column = columnList.getResultColumn(i); 
				String columnName = column.getAliasClause() == null ? column.getExpr().toString() : column.getAliasClause().toString();
				modelFields.add(new ModelField(columnName, columnName.toLowerCase(), "", columnName.toLowerCase(), "String"));
			}
			codeGenerator = new ModelCodeGenerator(apiFormat.usingModel, "", modelFields);
			
			resultQuery += "\nModel------------------------\n"
						+ codeGenerator.codeGenerate();
			
			
		}
		
		return resultQuery;
	}
	public List<QueryParameter> getQueryParamter(List<String> parameters){
		List<QueryParameter> queryParameters = new ArrayList<QueryParameter>();
		
		for(String parameter : parameters){
			queryParameters.add(new QueryParameter(parameter, true, parameter.replaceAll("^P_", "").toLowerCase()));
		}
		return queryParameters;
	}
	public List<String> getParameter(TSelectSqlStatement selectStament){
		TExpression expression = selectStament.getWhereClause().getCondition();
		List<String> leafNodes = new ArrayList<String>();
		List<String> parameters = new ArrayList<String>();
		
		/* �쟾�쐞 �깘�깋 */
		expression.preOrderTraverse(new ExpressionVisitor(leafNodes));
		Pattern pattern = Pattern.compile("P_[.]*");
		
		for(String leafNode : leafNodes){
			Matcher matcher = pattern.matcher(leafNode);
			boolean dupleFlag = false;
			if(matcher.find()){
				for(String parameter : parameters){
					if(leafNode.equals(parameter)){
						dupleFlag = true;
						break;
					}
				}
				if(!dupleFlag){
					parameters.add(leafNode);
				}
			}
		}
		return parameters;
	}
}
