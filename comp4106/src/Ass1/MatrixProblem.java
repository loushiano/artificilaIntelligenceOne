package Ass1;

import java.util.ArrayList;

public class MatrixProblem {
	private final int L=3;
	private final int W=3;
	private final int MaxMovesOne=16;
	private int matrix[][];
	private ArrayList<Integer> operations;
	private ArrayList<MatrixNode> fringes;
	private ArrayList<MatrixNode> closed;
	private int GoalState[][];
	private MatrixNode head;
	public MatrixProblem(){
		matrix = new int[L][W];
		matrix[0][0]=1;
		matrix[0][1]=2;
		matrix[0][2]=3;
		matrix[1][0]=7;
		matrix[1][1]=4;
		matrix[1][2]=0;
		matrix[2][0]=8;
		matrix[2][1]=6;
		matrix[2][2]=5;
		GoalState = new int [L][W];
		GoalState[0][0]=1;
		GoalState[0][1]=2;
		GoalState[0][2]=3;
		GoalState[1][2]=4;
		GoalState[2][2]=5;
		GoalState[2][1]=6;
		GoalState[2][0]=7;
		GoalState[1][0]=8;
		GoalState[1][1]=0;
		operations = new ArrayList<Integer>();
		for(int i=1;i<MaxMovesOne;i++){
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
	public void dfs(){
		MatrixNode currentNode = fringes.remove(0);
		while(!allInRightPosition(currentNode)
				){
			closed.add(currentNode);
			currentNode.print();
			System.out.println(currentNode.getStage());
			if(currentNode.getStage()<30){
			addChildren(currentNode);
			for (MatrixNode n: currentNode.getChildren()){
				fringes.add(n);
			}
			}
			
			int i=0;
			while(i==0)
			if(notInClosed(fringes.get(fringes.size()-1))){
				currentNode=fringes.remove(fringes.size()-1);
			i=1;
			}else {
				fringes.remove(fringes.size()-1);
				
		}
		}
		System.out.println("done");
		currentNode.print();
	}
public void aStarSearch(){
		
		MatrixNode currentNode =fringes.remove(0);
		while(!allInRightPosition(currentNode)){
			closed.add(currentNode);
			currentNode.print();
			addChildren(currentNode);
			for(MatrixNode n:currentNode.getChildren()){
				fringes.add(n);
			}
			sortFringes();
			currentNode =fringes.remove(0);
			
			
			
			
		}
		System.out.println("done");
		currentNode.print();
	}
private void sortFringes() {
	for(int i=0;i<fringes.size();i++){
		for(int j=0;j<fringes.size()-1;j++){
			if(g(fringes.get(j))>g(fringes.get(j+1))){
				
				MatrixNode n=fringes.get(j);
				fringes.set(j,fringes.get(j+1));
				fringes.set(j+1, n);
			}
		}
		
	}
	
}
private int g(MatrixNode n){
	
	
	return heuristicOne(n)+n.getStage();
}
	private int heuristicOne(MatrixNode n) {
		
		int count=0;
		for(int i=0;i<L;i++){
			for(int j=0;j<W;j++){
				if(n.getState()[i][j]!=GoalState[i][j]){
					count++;
				}
			}
		}
		
		return count;
	}
	private int heuristicTwo(MatrixNode n){
		int count=0;
		for(int i=0;i<L*W-1;i++){
			count +=howManySteps(i,n);
			
		}
		return count;
	}
	private int howManySteps(int i,MatrixNode n) {
		int x=getX(i);
		int y =getY(i);
		if(n.getState()[x][y]==i){
			return 0;
		}else if(canGoChessMove(i,n,x,y)){
				return 1;
			}else if(CanBeSwitchedWithBlank(i,n,x,y)){
				return 1;
			}else{
				return howFar(i,n,x,y);
			}
		}
	

	private int howFar(int i, MatrixNode n, int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean CanBeSwitchedWithBlank(int i, MatrixNode n, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean canGoChessMove(int i, MatrixNode n, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	private int getY(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int getX(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int heuristicCombination(MatrixNode n){
		return heuristicOne(n)+heuristicTwo(n);//both
	}

	private boolean allInRightPosition(MatrixNode currentNode) {
		/**int k =1;
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
		
		return true;**/
		for(int i=0;i<L;i++){
			for(int j=0;j<W;j++){
				if(currentNode.getState()[i][j]!=GoalState[i][j]){
					return false;
				}
			}
		}
		return true;
		
	}

	private void addChildren(MatrixNode currentNode) {
		int[] arr=findPostionOfZero(currentNode);
		int temp;
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
				}else if(i==9){
					temp=state[0][0];
					if(state[0][0]!=0 && state[2][1]!=0){
					state[0][0] =state[2][1];
					state[2][1]=temp;
					}
				}else if(i==10){
					temp=state[0][0];
					if(state[0][0]!=0 &&state[1][2]!=0){
					state[0][0] =state[1][2];
					state[1][2]=temp;
					}
				}else if(i==11){
					temp=state[0][1];
					if(state[0][1]!=0 &&state[2][0]!=0){
					state[0][1] =state[2][0];
					state[2][0]=temp;
					}
				}else if (i==12){
					temp=state[0][1];
					if(state[0][1]!=0 &&state[2][2]!=0){
					state[0][1] =state[2][2];
					state[2][2]=temp;
					}
				}else if ( i==13){
					temp=state[0][2];
					if(state[0][2]!=0 &&state[1][0]!=0){
					state[0][2] =state[1][0];
					state[1][0]=temp;
					}
				}else if ( i==14){
					temp=state[1][0];
					if(state[1][0]!=0 &&state[2][2]!=0){
					state[1][0] =state[2][2];
					state[2][2]=temp;
					}
				}else if ( i==15){
					temp=state[1][2];
					if(state[1][2]!=0 &&state[2][0]!=0){
					state[1][2] =state[2][0];
					state[2][0]=temp;
					}
				}else if ( i==16){
						temp=state[2][1];
						if(state[2][1]!=0 &&state[0][2]!=0){
						state[2][1] =state[0][2];
						state[0][2]=temp;
						}
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
		mp.aStarSearch();
	}
}
