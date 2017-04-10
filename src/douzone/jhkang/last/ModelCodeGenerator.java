package douzone.jhkang.last;

import java.util.List;

public class ModelCodeGenerator extends ModelFormat implements CodeGenerator{
	
	public ModelCodeGenerator(String modelName, String modelDesc, List<ModelField> modelField) {
		super(modelName, modelDesc, modelField);
	}
	public String codeGenerate() {
		return format;
	}
}
