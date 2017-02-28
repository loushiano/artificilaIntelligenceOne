package Ass1;

import java.util.ArrayList;
import java.util.Scanner;

public class MatrixProblem {
	private final int L=3;
	private  int W=3;
	private  int SIZE=3;
	private final int MaxMovesOne=9;
	private int matrix[][];
	private ArrayList<Integer> operations;
	private ArrayList<MatrixNode> fringes;
	private ArrayList<MatrixNode> closed;
	private int GoalState[][];
	private MatrixNode head;
	int counter =0;
	
	public MatrixProblem(){
		matrix = new int[L][W];
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter the tiles numbers: ");
		for(int x=0;x<L*W;x++){
			System.out.print("for " + x/W+","+x%W+": ");
			matrix[x/W][x%W] =reader.nextInt();
			
		}
		
		GoalState = new int [L][W];
		createGoalState(GoalState);
		operations = new ArrayList<Integer>();
		for(int i=1;i<MaxMovesOne;i++){
		operations.add(i);
		}
		fringes= new ArrayList<MatrixNode>();
		closed = new ArrayList<MatrixNode>();
		head = new MatrixNode(null,matrix);
		fringes.add(head);
	}
	
	private void createGoalState(int[][] goalState2) {
	if(W==3){
		GoalState[0][0]=1;
		GoalState[0][1]=2;
		GoalState[0][2]=3;
		GoalState[1][2]=4;
		GoalState[2][2]=5;
		GoalState[2][1]=6;
		GoalState[2][0]=7;
		GoalState[1][0]=8;
		GoalState[1][1]=0;
	}else if (W==4){
		GoalState[0][0]=1;
		GoalState[0][1]=2;
		GoalState[0][2]=3;
		GoalState[0][3]=4;
		GoalState[1][3]=5;
		GoalState[1][2]=6;
		GoalState[1][1]=7;
		GoalState[1][0]=0;
	}else {
		GoalState[0][0]=1;
		GoalState[0][1]=2;
		GoalState[0][2]=3;
		GoalState[0][3]=4;
		GoalState[0][4]=5;
		GoalState[1][4]=6;
		GoalState[1][3]=7;
		GoalState[1][2]=8;
		GoalState[1][1]=9;
		GoalState[1][0]=0;
	}
		
	}

	public void bfs(){
		MatrixNode currentNode = fringes.remove(0);
		
		while(!allInRightPosition(currentNode)
				){
			closed.add(currentNode);
			counter++;
			currentNode.print();
			System.out.println(currentNode.getStage()+ " "+ counter);
			
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
			counter++;
			closed.add(currentNode);
			currentNode.print();
			System.out.println(counter);
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
		int x=getX(i,n);
		int y =getY(i,n);
		if(n.getState()[x][y]==i){
			return 0;
		}else if(canGoChessMove(i,n,x,y)){
				return 0;
			}else if(CanBeSwitchedWithBlank(i,n,x,y)){
				return 0;
			}else{
				return 2;
			}
		}
	

	

	private boolean CanBeSwitchedWithBlank(int i, MatrixNode n, int x, int y) {
		if( x!=L-1 && n.getState()[x][y] ==0 && n.getState()[x+1][y]==i ){
			return true;
		}
		if( y!=W-1 && n.getState()[x][y] ==0 && n.getState()[x][y+1]==i ){
			return true;
		}
		if(  x!=L-1 && y!=W-1 && n.getState()[x][y] ==0 && n.getState()[x+1][y+1]==i){
			return true;
		}
		if( x!=0 && n.getState()[x][y] ==0 && n.getState()[x-1][y]==i ){
			return true;
		}
		if( y!=0 && n.getState()[x][y] ==0 && n.getState()[x][y-1]==i){
			return true;
		}
		if( x!=0 && y!=0 && n.getState()[x][y] ==0 && n.getState()[x-1][y-1]==i){
			return true;
		}
		if(  x!=W-1 && y!=0 && n.getState()[x][y] ==0 && n.getState()[x+1][y-1]==i){
			return true;
		}
		if( x!=0 && y!=L-1 && n.getState()[x][y] ==0 && n.getState()[x-1][y+1]==i){
			return true;
		}
		
		return false;
	}

	private boolean canGoChessMove(int i, MatrixNode n, int x, int y) {
		if( x!=L-1 && y<W-2 && n.getState()[x][y] !=0 && n.getState()[x+1][y+2]==i && x!=L-1 && y<W-2){
			return true;
		}
		if(y!=W-1 && x<L-2 && n.getState()[x][y] !=0 && n.getState()[x+2][y+1]==i  ){
			return true;
		}
		if(x!=0 && y<W-2 && n.getState()[x][y] !=0 && n.getState()[x-1][y+2]==i){
			return true;
		}
		if(x>1 && y<W-1 && n.getState()[x][y] !=0 && n.getState()[x-2][y+1]==i ){
			return true;
		}
		if( y!=0 && x>1 && n.getState()[x][y] !=0 && n.getState()[x-2][y-1]==i && y!=0 && x>1){
			return true;
		}
		if(x!=0 && y>1 && n.getState()[x][y] !=0 && n.getState()[x-1][y-2]==i ){
			return true;
		}
		if( x!=W-1 && y>1 && n.getState()[x][y] !=0 && n.getState()[x+1][y-2]==i ){
			return true;
		}
		if( x<L-2 && y>1 && n.getState()[x][y] !=0 && n.getState()[x+2][y-1]==i ){
			return true;
		}
		return false;
	}

	private int getY(int i,MatrixNode n) {
		int x=0;
		int y=0;
		for(;x<L;x++){
			for(;y<W;y++){
				if(n.getState()[x][y]==i){
					return y;
				}
			}
		}
		return 0;
	}

	private int getX(int i,MatrixNode n) {
		int x=0;
		int y=0;
		for(;x<L;x++){
			for(;y<W;y++){
				if(n.getState()[x][y]==i){
					return x;
				}
			}
		}
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
			
			if (SIZE==3){
				doThreeByThree(currentNode);
			}else if (SIZE==4){
				doTwoByFour(currentNode);
			}else if (SIZE ==5){
				doTwoByFive(currentNode);
			}
		
	}
	private void doThreeByThree(MatrixNode currentNode) {
		int i = 9;
		while (i<17){
			int[][] state = makeCopyOf(currentNode.getState());
			int temp=0;
			boolean flag=false;
		 if(i==9){
			temp=state[0][0];
			if(state[0][0]!=0 && state[2][1]!=0){
			state[0][0] =state[2][1];
			state[2][1]=temp;
			flag=true;
			}
		}else if(i==10){
			temp=state[0][0];
			if(state[0][0]!=0 &&state[1][2]!=0){
			state[0][0] =state[1][2];
			state[1][2]=temp;
			flag=true;
			}
		}else if(i==11){
			temp=state[0][1];
			if(state[0][1]!=0 &&state[2][0]!=0){
			state[0][1] =state[2][0];
			state[2][0]=temp;
			flag=true;
			}
		}else if (i==12){
			temp=state[0][1];
			if(state[0][1]!=0 &&state[2][2]!=0){
			state[0][1] =state[2][2];
			state[2][2]=temp;
			flag=true;
			}
		}else if ( i==13){
			temp=state[0][2];
			if(state[0][2]!=0 &&state[1][0]!=0){
			state[0][2] =state[1][0];
			state[1][0]=temp;
			flag=true;
			}
		}else if ( i==14){
			temp=state[1][0];
			if(state[1][0]!=0 &&state[2][2]!=0){
			state[1][0] =state[2][2];
			state[2][2]=temp;
			flag=true;
			}
		}else if ( i==15){
			temp=state[1][2];
			if(state[1][2]!=0 &&state[2][0]!=0){
			state[1][2] =state[2][0];
			state[2][0]=temp;
			flag=true;
			}
		}else if ( i==16){
				temp=state[2][1];
				if(state[2][1]!=0 &&state[0][2]!=0){
				state[2][1] =state[0][2];
				state[0][2]=temp;
				flag=true;
				}
		}
		 MatrixNode child = new MatrixNode(currentNode,state);
			child.setStage(currentNode.getStage()+1);
			if(notInClosed(child) && flag){
				
				currentNode.addChild(child);
			}
		i++;
		}
		
	}

	private void doTwoByFour(MatrixNode currentNode) {
		int i = 0;
		while (i<4){
			int[][] state = makeCopyOf(currentNode.getState());
			int temp=0;
			boolean flag=false;
		 if(i==0){
			temp=state[0][0];
			if(state[0][0]!=0 && state[1][2]!=0){
			state[0][0] =state[1][2];
			state[1][2]=temp;
			flag=true;
			}
		}else if(i==1){
			temp=state[0][1];
			if(state[0][1]!=0 &&state[1][3]!=0){
			state[0][1] =state[1][3];
			state[1][3]=temp;
			flag=true;
			}
		}else if(i==2){
			temp=state[0][2];
			if(state[0][2]!=0 &&state[1][0]!=0){
			state[0][2] =state[1][0];
			state[1][0]=temp;
			flag=true;
			}
		}else if (i==3){
			temp=state[0][3];
			if(state[0][3]!=0 &&state[1][1]!=0){
			state[0][3] =state[1][1];
			state[1][1]=temp;
			flag=true;
			}
		}
		 MatrixNode child = new MatrixNode(currentNode,state);
			child.setStage(currentNode.getStage()+1);
			if(notInClosed(child) && flag){
				
				currentNode.addChild(child);
			}
		i++;
		}
		
	}

	private void doTwoByFive(MatrixNode currentNode) {
		int i = 0;
		while (i<6){
			int[][] state = makeCopyOf(currentNode.getState());
			int temp=0;
			boolean flag=false;
		 if(i==0){
			temp=state[0][0];
			if(state[0][0]!=0 && state[1][2]!=0){
			state[0][0] =state[1][2];
			state[1][2]=temp;
			flag=true;
			}
		}else if(i==1){
			temp=state[0][1];
			if(state[0][1]!=0 &&state[1][3]!=0){
			state[0][0] =state[1][3];
			state[1][3]=temp;
			flag=true;
			}
		}else if(i==2){
			temp=state[0][2];
			if(state[0][2]!=0 &&state[1][0]!=0){
			state[0][2] =state[1][0];
			state[1][0]=temp;
			flag=true;
			}
		}else if (i==3){
			temp=state[0][3];
			if(state[0][3]!=0 &&state[1][1]!=0){
			state[0][3] =state[1][1];
			state[1][1]=temp;
			flag=true;
			}
			
		}else if (i==4){
			temp=state[0][4];
			if(state[0][4]!=0 &&state[1][2]!=0){
			state[0][4] =state[1][2];
			state[1][2]=temp;
			flag=true;
			}
			
		}else if (i==5){
			temp=state[0][2];
			if(state[0][2]!=0 &&state[1][4]!=0){
			state[0][2] =state[1][4];
			state[1][4]=temp;
			flag=true;
			}
			
		}
		 MatrixNode child = new MatrixNode(currentNode,state);
			child.setStage(currentNode.getStage()+1);
			if(notInClosed(child) && flag){
				
				currentNode.addChild(child);
			}
		i++;
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
