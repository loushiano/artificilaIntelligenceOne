package Ass1;

import java.util.ArrayList;

public class MatrixNode {
	private int L =2;
	private int W=5;
	private int stage;
	private MatrixNode parent;
	private ArrayList<MatrixNode> children;
	private int[][] state;
	public MatrixNode(MatrixNode node,int[][] state){
		parent =node;
		children = new ArrayList<MatrixNode>();
		this.state =state;
		stage=0;
	}
	public int getStage() {
		// TODO Auto-generated method stub
		return stage;
	}
	public void setStage(int i){
		stage=i;
	}
	public ArrayList<MatrixNode> getChildren() {
		// TODO Auto-generated method stub
		return children;
	}
	public void addChild(MatrixNode child){
		children.add(child);
	}
	public int[][] getState()
	{
		return state;
	}
	public MatrixNode getParent(){
		return parent;
	}
	public void print() {
		for(int i=0;i<L;i++){
			for(int j=0;j<W;j++){
				System.out.print(state[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	

}
