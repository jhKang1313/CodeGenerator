package douzone.jhkang.last;

import java.util.List;

public interface InputMethod {
	public List<String> queryFileInput();
	public List<ApiParameter> apiParameterInput(List<String> parameters);
	public ApiFormat apiInfoInput(ApiFormat format, String method);
	public ModelFormat modelInfoInput(ModelFormat format);
}
