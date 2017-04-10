package douzone.jhkang.last;

import java.util.List;

public class ModelFormat{
	public String modelName;
	public String modelDesc;
	List<ModelField> modelField;
	
	public String format; 
	
	public ModelFormat(){
		
	}
						  
	public ModelFormat(String modelName, String modelDesc, List<ModelField> modelField){
		this.modelName = modelName;
		this.modelDesc = modelDesc;
		this.modelField = modelField;
		format += String.format("@DzModel(name=\"%s\", desc=\"\")\n", modelName, modelDesc)
				+ String.format("public class %s {\n", modelName);
		for(ModelField field : modelField){
			format += String.format("\t@SerializedName(\"%s\")\n", field.alias) 
					+ String.format("\tDzModelField(name=\"%s\", desc=\"%s\", colName=\"%s\")\n", field.name, field.desc, field.colName)
					+ String.format("\tprivate %s %s;\n", field.dataType, field.name);
		}
		format += "}";
	}
}
