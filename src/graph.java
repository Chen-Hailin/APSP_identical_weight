import java.util.*;
public class graph {
	private int vertices;
	private ArrayList<Integer>[] graph;
	private int[][] min_matrix, parent_matrix;
	@SuppressWarnings("unchecked")
	public graph(int V, int E) {
        if (E > (long) V*(V-1)/2) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                throw new IllegalArgumentException("Too few edges");
        this.vertices = V;
        graph = (ArrayList<Integer>[])new ArrayList[V];
        for(int i = 0; i < V; i++)
        	graph[i] = new ArrayList<Integer>();
        min_matrix = new int[V][V];
        parent_matrix = new int[V][V];
        Random random = new Random();
        int i = 0;
        int a , b;
        while(i < E){
        	a =  random.nextInt(V);
        	while((b = random.nextInt(V))==a){}
        	if(!graph[a].contains(b)){
        		i ++;
	        	graph[a].add(b);
	        	graph[b].add(a);
        	}
        }
    }
	public void BFS_all(){
		for(int source_node = 0; source_node < vertices; source_node++){
			BFS(source_node);
		}
	}
	private void BFS(int source_node){
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(source_node);
		while(!Q.isEmpty()){
			int cur = Q.poll();
			for(int next : graph[cur]){
				if(min_matrix[source_node][next] == 0){
					min_matrix[source_node][next] = min_matrix[source_node][cur] + 1;
					parent_matrix[source_node][next] = cur;
					Q.add(next);
				}
			}
		}
	}
	public void shortest(int source, int dest){
		shortest_value(source, dest);
		shortest_path(source, dest);
		System.out.println();
	}
	private void shortest_value(int source, int dest){
		System.out.format("Shortest path: %d\n",min_matrix[source][dest]);
	}
	private void shortest_path(int source, int dest){
		if(dest != source){
			shortest_path(source, parent_matrix[source][dest]);
			System.out.print("->"+dest);
		}
		else
			System.out.print(dest);
	}
	public boolean check_if_correct(){
		for(int i = 0; i < vertices; i++){
			for(int j = 0; j < vertices; j++){
				if(min_matrix[i][j] != min_matrix[j][i])
					return false;
			}
		}
		return true;
	}
	public void print(){
		for(int i = 0; i < vertices; i++){
			System.out.print("\n"+i);
			for(int j : graph[i])
				System.out.print("-"+j);
		}
		System.out.println("min_matrix: ");
		System.out.print("  ");
		for(int i = 0; i < vertices; i++){
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i = 0; i < vertices; i++){
			for(int j = 0; j < vertices; j++){
				if(j == 0) System.out.print(i+" ");
				System.out.print(min_matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("parent_matrix:");
		System.out.print("  ");
		for(int i = 0; i < vertices; i++){
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i = 0; i < vertices; i++){
			for(int j = 0; j < vertices; j++){
				if(j == 0) System.out.print(i+" ");
				System.out.print(parent_matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	public boolean check_if_connect(){
		for(int i = 0; i < this.vertices; i++)
			for(int j = 0; j < this.vertices; j++)
				if(i!=j && min_matrix[i][j] == 0)
					return false;
		return true;
	}
}
