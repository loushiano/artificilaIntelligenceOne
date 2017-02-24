package Ass1;

import java.util.ArrayList;

public class TestingPurpose {
	private ArrayList<Node> closed;
	private final int L=3;
	private final int W=3;
	private int[][] matrix;
	public TestingPurpose(){
		closed = new ArrayList<Node>();
		ArrayList<Integer> one =new ArrayList<Integer>();
		ArrayList<Integer> two =new ArrayList<Integer>();
		one.add(3);one.add(4);one.add(5);
		Node n =new Node(two,one,null);
		closed.add(n);
		matrix = new int[L][W];
		matrix[0][0]=1;
		matrix[0][1]=2;
		matrix[0][2]=3;
		matrix[1][0]=4;
		matrix[1][1]=0;
		matrix[1][2]=8;
		matrix[2][0]=7;
		matrix[2][1]=6;
		matrix[2][2]=5;
		
	}
	
	public boolean check (){
		
			/*ArrayList<Integer> n =new ArrayList<Integer>();
			n.add(3);n.add(6);n.add(5);
			Node l=new Node(null,n,null);
			return notInClosed(l);
		*/
		return allInRightPosition( new MatrixNode(null,matrix));
		
	}
	private boolean notInClosed(Node child) {
		for (int i=0;i<closed.size();i++){
			if(child.getLeftSide().size()==closed.get(i).getLeftSide().size()){
				for(int j=0;j<child.getLeftSide().size();j++){
					if(!closed.get(i).getLeftSide().contains(child.getLeftSide().get(j))){
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}
	private boolean allInRightPosition(MatrixNode currentNode) {
		int k =1;
		if(currentNode.getState()[1][1]!=0){
			return false;
		}
			int i=0;
			int j=0;
			for(j=0;j<W;j++){
				
					if(currentNode.getState()[i][j]!=k){
						return false;
					}
				
				k++;
			}
			j=2;
			for(i=1;i<W;i++){
				if(currentNode.getState()[i][j]!=k){
					return false;
				}
				k++;
				i=2;
				for(j=W-1;j>0;j--){
					if(currentNode.getState()[i][j]!=k){
						return false;
					}
					k++;
				}
				if(currentNode.getState()[1][0]!=8){
					return false;
				}
			}
		
		return true;
	}
	public static void main(String[] args){
		TestingPurpose test =new TestingPurpose();
		
		boolean a=test.check();
		if(a){
			System.out.println("hi");
		}else {
			System.out.println("false");
		}

	}
}
