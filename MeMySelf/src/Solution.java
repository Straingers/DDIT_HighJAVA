import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
//		int[] answers = {1,2,3,4,5};
		int[] answers = {1,3,2,4,2};
		List<Integer> answer = new ArrayList<>();
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] thr = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int cntA = 0;
        int cntB = 0;
        int cntC = 0;
        for(int i = 0; i < answers.length; i++) {
        	if(one[i % one.length] == answers[i]) {
        		cntA++;
        	}
        	if(two[i % two.length] == answers[i]) {
        		cntB++;
        	}
        	if(thr[i % thr.length] == answers[i]) {
        		cntC++;
        	}
        }
        int[] tmp = {cntA, cntB, cntC};
        Arrays.sort(tmp);
        for(int i = 0; i < tmp.length; i++) {
        	if(tmp[i] == tmp[tmp.length-1]) {
        		answer.add(tmp[i]);
        	}
        }
        int[] answer2 = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
			if(answer.get(i) == cntA) {
				answer.set(i, 1);
			}
			if(answer.get(i) == cntB) {
				answer.set(i, 2);
			}
			if(answer.get(i) == cntC) {
				answer.set(i, 3);
			}
			
		}
        System.out.println(answer);
	}
}
