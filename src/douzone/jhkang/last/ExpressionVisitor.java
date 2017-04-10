package douzone.jhkang.last;

import java.util.ArrayList;
import java.util.List;

import gudusoft.gsqlparser.nodes.IExpressionVisitor;
import gudusoft.gsqlparser.nodes.TParseTreeNode;

public class ExpressionVisitor implements IExpressionVisitor{
	private List<String> childNodes = new ArrayList<String>();
	public ExpressionVisitor(List<String> childNodes){
		this.childNodes = childNodes;
	}
	public boolean exprVisit(TParseTreeNode parentNode, boolean isLeafNode) {
		if(isLeafNode){
			childNodes.add(parentNode.toString());
		}
		return true;
	}
}
