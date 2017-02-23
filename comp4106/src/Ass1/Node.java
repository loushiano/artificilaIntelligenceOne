package Ass1;

import java.util.ArrayList;

public class Node {

	private ArrayList<Integer> rightSide;
	private ArrayList<Integer> leftSide;
	private Node parent;
	private ArrayList<Node> children;
	private ArrayList<Integer> stepToThis;
	private int stage;
	public ArrayList<Integer> getStepToThis() {
		return stepToThis;
	}
	public void setStepToThis(ArrayList<Integer> stepToThis) {
		this.stepToThis = stepToThis;
	}
	private int time;
	
	public Node(ArrayList<Integer> rightSide,ArrayList<Integer> leftSide,Node parent){
		this.setRightSide(rightSide);
		this.setLeftSide(leftSide);
		this.parent=parent;
		children = new ArrayList<Node>();
		time =0;
		stepToThis =new ArrayList<Integer>();
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void addChild(Node node){
		children.add(node);
	}
	public ArrayList<Node> getChildren(){
		return children;
	}
	public ArrayList<Integer> getLeftSide() {
		return leftSide;
	}
	public void setLeftSide(ArrayList<Integer> leftSide) {
		this.leftSide = leftSide;
	}
	public ArrayList<Integer> getRightSide() {
		return rightSide;
	}
	public void setRightSide(ArrayList<Integer> rightSide) {
		this.rightSide = rightSide;
	}
	public void showSteps(){
		for(int i=0 ;i<stepToThis.size();i++){
			System.out.print(stepToThis.get(i)+",");
		}
		System.out.println();
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
}
