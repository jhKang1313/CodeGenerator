package douzone.jhkang.last;

public class ModelField {
	public String alias;
	public String name;
	public String desc;
	public String colName;
	public String dataType;
	
	public ModelField(String alias, String name, String desc, String colName, String dataType){
		this.alias = alias;
		this.name = name;
		this.desc = desc;
		this.colName = colName;
		this.dataType = dataType;
	}
}
