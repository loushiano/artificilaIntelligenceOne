package Ass1;

import java.util.ArrayList;

public class MatrixProblem {
	private final int L=3;
	private final int W=3;
	private int matrix[][];
	private ArrayList<Integer> operations;
	private ArrayList<MatrixNode> fringes;
	private ArrayList<MatrixNode> closed;
	private MatrixNode head;
	public MatrixProblem(){
		matrix = new int[L][W];
		matrix[0][0]=1;
		matrix[0][1]=2;
		matrix[0][2]=3;
		matrix[1][0]=8;
		matrix[1][1]=7;
		matrix[1][2]=0;
		matrix[2][0]=4;
		matrix[2][1]=6;
		matrix[2][2]=5;
		operations = new ArrayList<Integer>();
		for(int i=1;i<9;i++){
		operations.add(i);
		}
		fringes= new ArrayList<MatrixNode>();
		closed = new ArrayList<MatrixNode>();
		head = new MatrixNode(null,matrix);
		fringes.add(head);
	}
	
	public void bfs(){
		MatrixNode currentNode = fringes.remove(0);
		
		while(!allInRightPosition(currentNode)
				){
			closed.add(currentNode);
			currentNode.print();
			System.out.println(currentNode.getStage());
			addChildren(currentNode);
			for(MatrixNode n:currentNode.getChildren()){
				
				fringes.add(n);
				
				
			}
			
			int i=0;
			while(i==0)
			if(notInClosed(fringes.get(0))){
				currentNode=fringes.remove(0);
			i=1;
			}else {
				fringes.remove(0);
				
		}
			
		}
		System.out.println("done");
		currentNode.print();
	
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

	private void addChildren(MatrixNode currentNode) {
		int[] arr=findPostionOfZero(currentNode);
			int x=arr[0];
			int y=arr[1];
			for(int i:operations){
				int [][] state = makeCopyOf(currentNode.getState());
				boolean flag=false;
				
				if( i==1){
					if(x!=L-1){
					state[x][y]=state[x+1][y];
					state[x+1][y]=0;
					
					flag=true;
					}
				}else if (y!=W-1 && i==2){
					state[x][y]=state[x][y+1];
					state[x][y+1]=0;
					flag=true;
					
				}else if(x!=0 && i==3){
					state[x][y]=state[x-1][y];
					state[x-1][y]=0;
					flag=true;
					
				}else if (y!=0 && i==4){
					state[x][y]=state[x][y-1];
					state[x][y-1]=0;
					flag=true;
					
				}else if (y!=W-1 && x!=L-1 && i==5){
					state[x][y]=state[x+1][y+1];
					state[x+1][y+1]=0;
					flag=true;
				}else if (y!=0 && x!=0 && i==6){
					state[x][y]=state[x-1][y-1];
					state[x-1][y-1]=0;
					flag=true;
				}else if (y!=W-1 && x!=0 && i==7){
					state[x][y]=state[x-1][y+1];
					state[x-1][y+1]=0;
					flag=true;
				}else if (y!=0 && x!=L-1 && i==8){
					state[x][y]=state[x+1][y-1];
					state[x+1][y-1]=0;
					flag=true;
				}
				MatrixNode child = new MatrixNode(currentNode,state);
				child.setStage(currentNode.getStage()+1);
				if(notInClosed(child) && flag){
					
					currentNode.addChild(child);
				}
			}
		
	}
	private int[][] makeCopyOf(int[][] state) {
		int[][] k = new int[L][W];
		for(int i=0;i<L;i++){
			for(int j=0;j<W;j++){
				k[i][j] =state[i][j];
			}
		}
		return k;
	}

	private int[] findPostionOfZero(MatrixNode currentNode) {
		for(int i=0;i<L;i++){
			for(int j=0;j<W;j++){
				if(currentNode.getState()[i][j]==0){
					int[] arr = new int[2];
					arr[0]=i;arr[1]=j;
					return arr;
				}
			}
		}
		return null;
	}

	public boolean notInClosed(MatrixNode currentNode){
		for(int l=0;l<closed.size();l++){
			if(same(currentNode.getState(),closed.get(l).getState())){
				return false;
			}
		}
		return true;
	}
	private boolean same(int[][] state, int[][] state2) {
		for(int i=0;i<L;i++){
			for(int j=0;j<W;j++){
				if(state[i][j]!=state2[i][j]){
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String args[]){
		MatrixProblem mp = new MatrixProblem();
		mp.bfs();
	}
}
