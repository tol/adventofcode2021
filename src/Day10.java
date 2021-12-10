import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 {

	public static void main(String[] args) {
		Map<Character,Character> matches=new HashMap<>();
		matches.put(']','[');
		matches.put('}','{');
		matches.put(')','(');
		matches.put('>','<');
		Map<Character,Character> revmatches=new HashMap<>();
		revmatches.put('[',']');
		revmatches.put('{','}');
		revmatches.put('(',')');
		revmatches.put('<','>');
		try (Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("inputd10.txt"))))) {
			List<Character> fault=new ArrayList<>();
			List<String> closingBrackets=new ArrayList<>();
			while (in.hasNext()) {
				String s = in.nextLine();
				//System.out.println(s);
				Stack<Character> stack=new Stack<>();
               boolean corupted=false;
 				for(char c:s.toCharArray()){
					if (matches.containsValue(c)) {
						stack.push(c);
					}else{
						if (stack.empty()){
							break;
						}
						Character end=stack.pop();
						if (matches.get(c)!=end){
							fault.add(c);
							corupted=true;
							//System.out.println(s);
							//System.out.println(c);
							break;
						}
					}
				}
				if (!corupted){
					String ss="";
					while (!stack.empty()){
						ss=ss+revmatches.get(stack.pop());

					}
					closingBrackets.add(ss);
				}
			}


/*			): 3 points.
			]: 57 points.
			}: 1197 points.
			>: 25137 points*/
			int sum=fault.stream().reduce(0,(acc,x) -> {
				if (x==']'){
					return acc=acc+57;
				}
				if (x=='}'){
					return acc=acc+1197;
				}
				if (x==')'){
					return acc=acc+3;
				}
				if (x=='>'){
					return acc=acc+25137;
				}
				return acc;
			},Integer::sum);
			System.out.println(sum);
/*			): 1 point.
			]: 2 points.
			}: 3 points.
			>: 4 points.*/


			Stream<Long> integerStream = closingBrackets.stream().map(x -> {
				long s = 0;
				for (char c : x.toCharArray()) {
					switch (c) {
						case ')': {
							s = s * 5 + 1;
							break;
						}
						case ']': {
							s = s * 5 + 2;
							break;
						}
						case '}': {
							s = s * 5 + 3;
							break;
						}
						case '>': {
							s = s * 5 + 4;
							break;
						}
					}
				}
				return s;
			}).sorted();
			int size=closingBrackets.size();
			long sum2=integerStream.skip((size-1)/2).limit(2-size%2).findFirst().get();
			//List<Long> l=integerStream.collect(Collectors.toList());
			//System.out.println(l.get(26));
			System.out.println(sum2);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
