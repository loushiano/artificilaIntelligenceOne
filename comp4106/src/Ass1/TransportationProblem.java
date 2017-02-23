package Ass1;

import java.util.ArrayList;
import java.util.Scanner;

public class TransportationProblem {
	private final int TIME=17;
	private final int numPlayers=4;
	private Node head;
	private ArrayList<Integer> times;
	private ArrayList<Node> fringes;
	private ArrayList<Node> closed;
	private ArrayList<Node> open;
	private int stage;
	public TransportationProblem(){
		times =new ArrayList<Integer>();
		fringes =new ArrayList<Node>();
		closed =new ArrayList<Node>();
		open = new ArrayList<Node>();
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter the times of people incrementally: ");
		ArrayList<Integer> left = new ArrayList<Integer>();
		for(int i=0;i<numPlayers;i++){	
		 times.add(reader.nextInt()); // Scans the next token of the input as an int.
		 left.add(times.get(i));
		}
		left.add(0);
		ArrayList<Integer> right = new ArrayList<Integer>();
		stage=0;
		head =new Node(right,left,null);
		head.setTime(0);
		head.setStage(stage);
		fringes.add(head);
		
	}
	public void bdfs(){
		
		Node currentNode = fringes.remove(0);
		while(currentNode.getLeftSide().size()!=1 || currentNode.getTime()!=TIME
				){
			closed.add(currentNode);
			for(int i=0;i<currentNode.getLeftSide().size();i++){
				System.out.print(currentNode.getLeftSide().get(i)+",");
			}
			System.out.println(currentNode.getStage()+" "+currentNode.getTime());
			addChildren(currentNode);
			for(Node n:currentNode.getChildren()){
				
				fringes.add(n);
				
				/*for(int i=0;i<n.getLeftSide().size();i++){
					System.out.print(n.getLeftSide().get(i)+",");
				}
				System.out.print(" "+n.getTime() );
				System.out.print("child");
				System.out.println();*/
			}
			int i=0;
			while(i==0)
			if(notInClosed(fringes.get(0))){
			currentNode =fringes.remove(0);
			i=1;
			}else {
				fringes.remove(0);
				
		}
			
			
		}
		System.out.println("done");
		for(int i=0;i<currentNode.getLeftSide().size();i++){
			System.out.print(currentNode.getLeftSide().get(i)+",");
		}
		System.out.print(" "+currentNode.getTime() );
		System.out.println();
		Node n=currentNode;
		while(n!=null){
			n.showSteps();
			n=n.getParent();
		}
		
	}
	public void dfs(){
		Node currentNode = fringes.remove(0);
		while(//currentNode.getLeftSide().size()!=1 || currentNode.getTime()!=30
				true){
			closed.add(currentNode);
			for(int i=0;i<currentNode.getLeftSide().size();i++){
				System.out.print(currentNode.getLeftSide().get(i)+",");
			}
			System.out.println(currentNode.getStage()+" "+currentNode.getTime());
			if(currentNode.getLeftSide().size()==1){
				if(currentNode.getTime()==TIME){
					break;
				}
			}
			if(currentNode.getTime()<TIME){
			addChildren(currentNode);
			for (Node n: currentNode.getChildren()){
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
		Node n=currentNode;
		while(n!=null){
			n.showSteps();
			n=n.getParent();
		}
		
	}
	
	
	public void aStarSearch(){
		
		Node currentNode =fringes.remove(0);
		while(currentNode.getLeftSide().size()!=1 || currentNode.getTime()!=TIME){
			closed.add(currentNode);
			for(int i=0;i<currentNode.getLeftSide().size();i++){
				System.out.print(currentNode.getLeftSide().get(i)+",");
			}
			System.out.println(" "+ g(currentNode)+"time: "+ currentNode.getTime());
			addChildren(currentNode);
			for(Node n:currentNode.getChildren()){
				fringes.add(n);
			}
			sortFringes();
			currentNode =fringes.remove(0);
			
			
			
			
		}
		Node n=currentNode;
		while(n!=null){
			n.showSteps();
			n=n.getParent();
		}
		
	}
	private void sortFringes() {
		for(int i=0;i<fringes.size();i++){
			for(int j=0;j<fringes.size()-1;j++){
				if(g(fringes.get(j))>g(fringes.get(j+1))){
					
					Node n=fringes.get(j);
					fringes.set(j,fringes.get(j+1));
					fringes.set(j+1, n);
				}
			}
			
		}
		
	}
	private int g(Node n){
		
		
		return heuristicOne(n)+n.getStage();
	}
	private int heuristicOne(Node n) {
		if(n.getTime()>30){
			return 100;
		}else {
			if((n.getStepToThis().size()==1 && n.getLeftSide().get(n.getLeftSide().size()-1)==-1)||
					(n.getStepToThis().size()==2 && n.getLeftSide().get(n.getLeftSide().size()-1)==0)){
				return 100;
			}else{
				if(n.getStepToThis().size()>1){
					if(Math.abs(n.getStepToThis().get(1)-n.getStepToThis().get(0))>5){
						return 100;
					}
				}
			}
		}
		if(n.getStepToThis().size()==1 && n.getLeftSide().get(n.getLeftSide().size()-1)==0){
			if(n.getStepToThis().get(0)>2){
				return 100;
			}
			
		}
		return n.getLeftSide().size() +n.getTime()%TIME;
	}
	public void addChildren(Node currentNode){
		if(currentNode.getLeftSide().get(currentNode.getLeftSide().size()-1)==0){
			for(int i=0;i<currentNode.getLeftSide().size()-1;i++){
				goRight(currentNode,i);
				ArrayList temp = (ArrayList) currentNode.getLeftSide().clone();
				ArrayList right =(ArrayList) currentNode.getRightSide().clone();
				right.add(temp.remove(i));
				temp.remove(temp.size()-1);
				temp.add(-1);
				Node child =new Node(right,temp,currentNode);
				
				if(notInClosed(child)){
					child.setTime(currentNode.getTime()+(int)right.get(right.size()-1));
					ArrayList steps =new ArrayList<Integer>();
					steps.add(right.get(right.size()-1));
					child.setStepToThis(steps);
					child.setStage(currentNode.getStage()+1);
					
				currentNode.addChild(child);
					
				}
			
			}	
		
		}else {
			for(int i=0;i<currentNode.getRightSide().size();i++){
				goLeft(currentNode,i);
				ArrayList temp = (ArrayList) currentNode.getLeftSide().clone();
				ArrayList right =(ArrayList) currentNode.getRightSide().clone();;
				temp.remove(temp.size()-1);
				temp.add(right.remove(i));
				
				temp.add(0);
				Node child =new Node(right,temp,currentNode);
				if(notInClosed(child)){
					child.setTime(currentNode.getTime()+(int)temp.get(temp.size()-2));
					ArrayList steps =new ArrayList<Integer>();
					steps.add(temp.get(temp.size()-2));
					child.setStepToThis(steps);
					child.setStage(currentNode.getStage()+1);
				currentNode.addChild(child);
				}
			
			}	
		
			
		}
		
	}

	private void goRight(Node currentNode, int i) {
		if(i<currentNode.getLeftSide().size()-2){
		for(int j=i+1;j<currentNode.getLeftSide().size()-1;j++){
			ArrayList temp = (ArrayList) currentNode.getLeftSide().clone();
			ArrayList right =(ArrayList) currentNode.getRightSide().clone();
			right.add(temp.remove(j));
			right.add(temp.remove(i));
			temp.remove(temp.size()-1);
			temp.add(-1);
			Node child =new Node(right,temp,currentNode);
			if(notInClosed(child)){
				child.setTime(currentNode.getTime()+Math.max((int)right.get(right.size()-1),(int)right.get(right.size()-2)));
				ArrayList steps =new ArrayList<Integer>();
				steps.add(right.get(right.size()-2));steps.add(right.get(right.size()-1));
				child.setStepToThis(steps);
				child.setStage(currentNode.getStage()+1);
				currentNode.addChild(child);
			}
			
		}
		}
	}
	
	private void goLeft(Node currentNode, int i){
		
		if(i<currentNode.getRightSide().size()-1){
			for(int j=i+1;j<currentNode.getRightSide().size();j++){
				ArrayList temp = (ArrayList) currentNode.getLeftSide().clone();
				ArrayList right =(ArrayList) currentNode.getRightSide().clone();;
				temp.remove(temp.size()-1);
				temp.add(right.remove(j));
				temp.add(right.remove(i));
				
				
				temp.add(0);
				Node child =new Node(right,temp,currentNode);
				if(notInClosed(child)){
					child.setTime(currentNode.getTime()+Math.max((int)temp.get(temp.size()-2),(int)temp.get(temp.size()-3)));
					ArrayList steps =new ArrayList<Integer>();
					steps.add(temp.get(temp.size()-2));steps.add(temp.get(temp.size()-3));
					child.setStepToThis(steps);
					child.setStage(currentNode.getStage()+1);
					currentNode.addChild(child);
				}
				
			}
			}
		
	}
	private boolean notInClosed(Node child) {
		for (int i=0;i<closed.size();i++){
			if(same(child.getLeftSide(),closed.get(i).getLeftSide()) && child.getTime()==closed.get(i).getTime() ){
				return false;
			}
				
				
			}
		return true;
	}
	
	public boolean same(ArrayList<Integer> one, ArrayList<Integer> two){
		
			if(one.size()==two.size()){
				for(int j=0;j<one.size();j++){
			if(!one.contains(two.get(j))){
				return false;
			}
			}
					return true;
		}
		
		return false;
	}
	public static void main(String args[]){
	TransportationProblem p= new TransportationProblem();
	p.aStarSearch();
		
	}
}
