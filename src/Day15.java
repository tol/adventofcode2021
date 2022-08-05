import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Day15 {

	public static final int N = 500;

	public static int min(int x,int y){
		return x<y?x:y;
	}
    public static  int calculatePath(int[][] board)  {
		int[][] dp=new int[N][N];
		dp[0][0]=0;

		for(int i=1;i< N;i++) {
			dp[0][i]=dp[0][i-1] +board[0][i];

			dp[i][0]=dp[i-1][0]+board[i][0];
		}

		for(int i=1;i< N;i++) {
			for(int j=1;j< N;j++) {

				dp[i][j]= min(dp[i][j-1] +board[i][j],dp[i-1][j]+board[i][j]);
			}
		}
		return dp[N-1][N-1];

	}

	public static  int[][] generateBoard(int[][] board, int n) {
		int i = n * 5;
		int[][] newboard = new int[i][i];
		for(int j=0;j<n*5;j++) {
			for (i = 0; i < n * 5; i++) {
				int v=(board[j%n][i % n] + j / (n) +i / (n))  % 10+(board[j%n][i % n] + j / (n) +i / (n))  /9;
				newboard[j][i] = v==0?1:v;
				//newboard[i][j] = (board[i % n][j%n] + i / n) % 10;
				System.out.print(newboard[j][i]);//+"= "+board[j%n][i % n]+"+"+j / n +i / n+" ");
			}
			System.out.println();
		}
		return newboard;

	}


	public static void main (String[] args ){

		int[][] board=new int[N][N];
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("inputd15.txt"))))) {
			int i=0,j=0;
			while (in.hasNext()) {
				String s = in.nextLine();
				//1163751742
				j=0;
				for(Character c:s.toCharArray()){

                   board[i][j]=(Character.digit(c,10));
					j++;
				}
				i++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		System.out.println(calculatePath(board));
		System.out.println(calculatePath(generateBoard(board,100)));

	}
}

