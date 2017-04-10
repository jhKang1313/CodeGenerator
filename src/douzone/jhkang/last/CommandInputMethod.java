package douzone.jhkang.last;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandInputMethod implements InputMethod{
	Scanner scanner = new Scanner(System.in);
	public List<String> queryFileInput(){
		List<String> fileList = new ArrayList<String>();
		
		System.out.println("쿼리 파일 경로 입력 : (0 입력시 종료)");
		while(true){
			String input = scanner.nextLine();
			if(input.equals("0")){
				break;
			}else{
				fileList.add(input);
			}
		}
		return fileList;
	}

	public List<ApiParameter> apiParameterInput(List<String> parameters) {
		List<ApiParameter> apiParameters = new ArrayList<ApiParameter>();
		
		for(String parameter : parameters){
			String key = parameter.replaceAll("^P_", "").toLowerCase();
			System.out.print("key 값 : " + key);
			/*System.out.println("desc 값");
			String desc = scanner.nextLine();*/
			
			apiParameters.add(new ApiParameter(key, key, "", "QueryString", "String"));
			
		}
		return apiParameters;
	}
	public ApiFormat apiInfoInput(ApiFormat format, String method){
		System.out.println("API 정보 입력");
		System.out.print("url : ");
		String url = scanner.nextLine();
		System.out.print("model Name : ");
		String model = scanner.nextLine();
		
		format.url = url;
		format.httpMethod = method;
		format.usingModel = model;
		return format;
		
	}
	public ModelFormat modelInfoInput(ModelFormat format){
		System.out.println();
		System.out.println("*model ");
		System.out.println("name : ");
		String name = scanner.nextLine();
		format.modelName = name;
		format.modelDesc = "";
		return format;
	}
}
