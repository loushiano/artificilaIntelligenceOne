package Ass1;

import java.util.ArrayList;

public class TestingPurpose {
	private ArrayList<Node> closed;
	public TestingPurpose(){
		closed = new ArrayList<Node>();
		ArrayList<Integer> one =new ArrayList<Integer>();
		ArrayList<Integer> two =new ArrayList<Integer>();
		one.add(3);one.add(4);one.add(5);
		Node n =new Node(two,one,null);
		closed.add(n);
	}
	
	public boolean check (){
		
			ArrayList<Integer> n =new ArrayList<Integer>();
			n.add(3);n.add(6);n.add(5);
			Node l=new Node(null,n,null);
			return notInClosed(l);
		
		
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
