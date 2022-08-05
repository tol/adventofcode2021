import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Day21 {

	public static void main(String[] args) {

		int player1 = 0;
		int player2 = 0;
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("inputd21.txt"))))) {
			String s = "";
			s = in.nextLine();
			String[] p = s.split(": ");
			player1 = Integer.parseInt(p[1]);
			s = in.nextLine();
			p = s.split(": ");
			player2 = Integer.parseInt(p[1]);
			int seed = 1;
			int score1 = 0;
			int score2 = 0;
			boolean gameover = false;
			int rols = 0;
			int loserscore = 0;
			while (!gameover) {
				int move = 0;
				for (int k = 1; k < 4; k++) {
					move = move + seed;
					rols++;
					seed = (seed + 1) % 101;
					if (seed==0) seed=1;


				}

				player1 = (player1 + move % 10) % 10;
				if (player1==0) player1=10;
				score1 += (player1 );
				if (score1 < 1000) {
					move = 0;
					for (int k = 1; k < 4; k++) {
						move = move + seed;
						rols++;
						seed = (seed + 1) % 101;
						if (seed==0) seed=1;


					}

					player2 = (player2 + move % 10) % 10;
					if (player2==0) player2=10;
					score2 += (player2 ) ;
					if (score2 >= 1000) {
						loserscore = score1;
						break;
					}

				}
				else {
					loserscore = score2;
					break;
				}

				gameover = score1 >= 1000 || score2 >= 1000;
			}
			System.out.println(rols );
			System.out.println(loserscore);
			System.out.println(rols * loserscore);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
