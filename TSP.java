/**
 * Author : Rishav Raj
 * Date : 19th April, 2020
 * Discription : This program finds solution for Travelling Salesman Problem using Dynamic Programming Approach.
 */
import java.util.Scanner;

public class TSP {
    
    private int[][] graph;
    private int sNode;
    private int minCost;
    
    //Constructor...
    public TSP(){
        this.minCost = Integer.MAX_VALUE;
    }
    
    //Entry Point of Program...
    public static void main(String[] args){
        new TSP().start();
    }
    
    //Main Logic starts here...
    private void start(){
		this.graph = takeInput();
		int[] set = Set.getDifference(new Set(graph.length).getSet(), sNode);
		System.out.println("\nNode Traversal Started from : " + sNode + " ");
        System.out.println("\nMinimum Cost for traversing all node once is : " + tsp(sNode, sNode, set, 0, minCost, sNode));
    }
    
    //This function takes input from user...
    private int[][] takeInput(){
        System.out.print("Enter No. of Nodes : ");
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] graph = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print("\nCost from " + (i+1) + " to " + (j+1) + " : ");
                graph[i][j] = Integer.parseInt(scanner.nextLine());
            }
        }
		System.out.println("\nEnter Starting Node : ");
		this.sNode = Integer.parseInt(scanner.nextLine());
        return graph;
    }
    
    //This function calculates tsp solution...
    private int tsp(int cNode, int sNode, 
            int[] rSet, int cost, int minCost, int index){
		System.out.println("\n\tCurrent Node : " + cNode + " & Total Cost : " + cost);
        if(rSet==null && graph[cNode-1][sNode-1]>0){
			System.out.println("\nEnd of Traversal path");
            return Math.min(cost+graph[cNode-1][index-1], minCost);
        }else if(rSet.length>0){
            for(int i=0; i<rSet.length; i++){
				System.out.print("\nTraversing under : " + cNode);
                minCost = Math.min(tsp(rSet[i], cNode, 
                        Set.getDifference(rSet, rSet[i]), 
                        cost+graph[cNode-1][rSet[i]-1], 
                        minCost, index), minCost);
            }
        }
        return minCost;
    }    
}

//This class is written to create & manipulate set i.e required for solving TSP problem...
class Set {
    
    private final int size;
    
    public Set(int size){
        this.size = size;
    }
    
    public int[] getSet(){
        int[] rSet = new int[size];
        for(int i=1; i<= size; i++){
            rSet[i-1] = i;
        }
        return rSet; 
    }
    
    public static int[] getDifference(int[] set, int n){
        if(set.length==1 && n==set[0])
            return null;
        int[] rSet = new int[set.length - 1];
        for(int i=1, j=0; i<=set.length; i++){
            if(n==set[i-1])
                continue;
            rSet[j++] = set[i-1];
        }
        return rSet;
    }
}