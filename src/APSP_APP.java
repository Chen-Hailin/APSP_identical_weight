import java.util.Scanner;
public class APSP_APP {
	private static graph g1, g2;
	private static Scanner sc = new Scanner(System.in);
	public static void main(String args[]){
		long start, end;
		int[] size = {0, 1000,5000,10000,50000,100000};
		for(int j = 0; j < 2; j++){
			System.out.format("Vertices %d:\n",(j+1)*5000);
			for(int i = 0; i < 6; i++){
				g1 = new graph(5000, size[i]);
				start = System.currentTimeMillis();
				g1.BFS_all();
				end = System.currentTimeMillis();
				System.out.format("edges: %6d  CPU time: %8d\n",size[i],end-start);
			}
		}
	}
	public static void demo(){
		g1 = new graph(5000, 10000);
		g2 = new graph(10000, 10000);
		System.out.println("finish creating");
		g1.BFS_all();
		System.out.println("");
		g2.BFS_all();
		//g1.print();
		//g2.print();
		System.out.println("g1:" + g1.check_if_correct() + " g2:"+ g2.check_if_correct());
		int choice = 1;
		while(choice!= 0){
			System.out.print("choose g1 : 1\n"
					+ "choose g2 : 2");
			choice = sc.nextInt();
			System.out.println("Input source and destination");
			if(choice == 1)
				g1.shortest(sc.nextInt(), sc.nextInt());
			else
				g2.shortest(sc.nextInt(), sc.nextInt());
			System.out.print("Do you wish to continue?\n yes : 1\n"
					+ "No : 0");
			choice = sc.nextInt();
		}
	}
}
