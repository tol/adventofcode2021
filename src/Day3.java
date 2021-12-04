import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Integer.*;
import static java.lang.System.out;

public class Day3 {


	public static String filter(List<String> input,final int i ,boolean o2) {
		String pattern= calculatebits(input,o2);
		if (input.size()==1){
			return input.get(0);
		}else{
			List<String> filtered=input.stream().filter(a->a.charAt(i)==pattern.charAt(i)).collect(Collectors.toList());
			return filter(filtered,i+1,o2);
		}

	}


	public static String  calculatebits(List<String> input,boolean o2){
		int[] bits=null;

		int n=0;
		for (String s:input){

			if (bits==null){
				bits= new int[s.length()];

			}
			int j=0;
			for (char c:s.toCharArray()){
				int bit=Character.getNumericValue(c);
				bits[j]=bits[j]+bit;
				j++;
			}
			out.println(s);

			n++;
		}
		String g="";
		String e="";
		for (int bit:bits){
			if (bit>=n/2){
				g=g+"1";
				e=e+"0";
			}else{
				g=g+"0";
				e=e+"1";
			}
		}

		if (o2) return g;
		    else return e;
	}

	public static void main(String[] args){
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("inputd3.txt"))))) {
			int n=0;
			int size=0;
			int[] bits=null;
			List<String> input=new ArrayList<>();
			while (in.hasNext()) {
				String s = in.nextLine();
				if (bits==null){
					bits= new int[s.length()];

					size=s.length();
				}
				int j=0;
				for (char c:s.toCharArray()){
					int bit=Character.getNumericValue(c);
					bits[j]=bits[j]+bit;
				j++;
				}
				out.println(s);

				input.add(s);
				n++;
			}
			String g="";
			String e="";
			for (int bit:bits){
				if (bit>=n/2){
					g=g+"1";
					e=e+"0";
				}else{
					g=g+"0";
					e=e+"1";
				}
			}

			String o2=filter(input,0,true);
			String co2=filter(input,0,false);

			out.println("---------");
			out.println(co2);
			out.println(o2);
			out.println("---------");


			out.println(g);
			out.println(e);
			int gamma= parseInt(g,2);
			int epsilon= parseInt(e,2);

			out.println(gamma);
			out.println(epsilon);
			out.println(epsilon*gamma);
			out.println(parseInt(o2,2)*parseInt(co2,2));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
