import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class DP {	
	
	public static int[] LIS(int[] values){
		int[] mem = new int[values.length];
		mem[0] = 1;
		for(int i = 1; i < mem.length; i++){
			int max = Integer.MIN_VALUE;
			for(int j = 0; j < i; j++){
				if(values[j] < values[i]){
					if(mem[j] > max){
						max = mem[j];
					}
				}
			}
			mem[i] = max + 1;
		}
		
		int max = 0;
		int index = 0;
		for(int i = 0; i < mem.length; i++){
			if(max < mem[i]){
				max = mem[i];
				index = i;
			}
		}
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		int prev = Integer.MAX_VALUE;
		while(index >= 0){
			if(mem[index] == max && values[index] < prev){
				res.add(values[index]);
				max--;
				prev = values[index];				
			}
			index--;
		}
		
		int[] r = new int[res.size()];
		for(int i = 0; i < res.size(); i++){
			r[i] = res.get(res.size() - i - 1);
		}
		
		return r;
	}
	
	
	public static String LCSubseq(String a, String b){
		int[][] mem = new int[a.length() + 1][b.length() + 1];
		for(int i = 0; i < mem.length; i++){
			mem[i][0] = 0;
		}
		
		for(int i = 0; i < mem[0].length; i++){
			mem[0][i] = 0;
		}
		
		for(int i = 1; i < mem.length; i++){
			for(int j = 1; j < mem[0].length; j++){
				if(a.charAt(i - 1) == b.charAt(j - 1)){
					mem[i][j] = mem[i - 1][j - 1] + 1;
				}else{
					mem[i][j] = max(mem[i - 1][j], mem[i][j - 1]);
				}
			}
		}
		
		int x = a.length();
		int y = b.length();
		String s = "";
		while(x != 0 && y != 0){
			if(mem[x][y] == mem [x-1][y]){
				x--; 
			}else if(mem[x][y] == mem[x][y - 1]){
				y--;
			}else{
				s += a.charAt(x - 1);
				x--;
				y--;
			}
		}		
		return new StringBuffer(s).reverse().toString(); //
		//return mem[a.length()][b.length()];
	}
		
	public static int LISCont(int[] values){
		if(values.length == 1){
			return 1;
		}
		int start = 0;
		int end = 0;
		int max_len = Integer.MIN_VALUE;
		int len = 1;
		int temp_start = 0;
		for(int i = 1; i < values.length; i++){
			if(values[i - 1] < values[i]){
				len++;
			}else{
				len = 1;
				temp_start = i;
			}if(len > max_len){			
				max_len = len;
				end = i;
				start = temp_start;
			}			
		}
		return max_len;
	}
	
	public static int[] maxSumSubarray(int[] values){//Kadane
		boolean all_neg = true;
		for(int i = 0; i < values.length; i++){
			all_neg &= (values[i] < 0);
		}
		if(all_neg){
			int temp = Integer.MIN_VALUE;
			int position = 0;
			for(int i = 0; i < values.length; i++){
				if(values[i] > temp){
					temp = values[i];
					position = i;
				}
			}
			int[] r = {values[position]};
			return r;
		}
		int start = 0;
		int end = 0;
		int max_sum = Integer.MIN_VALUE;
		int sum = 0;
		int temp_start = -1;
		for(int i = 0; i < values.length; i++){
			sum += values[i];
			if(sum < 0){
				sum = 0;
				temp_start = i;
			}else if(sum > max_sum){
				max_sum = sum;
				start = temp_start + 1;
				end = i;
			}
		}
		int[] res = new int[end - start + 1];
		for(int i = 0; i < res.length; i++){
			res[i] = values[i + start];
		}
		return res;
	}
	
	public static int maxSumSubarrayDP(int[] values){
		int[] m = new int[values.length];
		m[0] = values[0];
		for(int i = 1; i < m.length; i++){
			m[i] = Math.max(m[i - 1] + values[i], values[i]);
		}
		int max = Integer.MIN_VALUE;
		for(int i : m){
			if(i > max){
				max = i;
			}
		}
		
		return max;
	}	
	
	public static int max(int a, int b){
		return (a > b) ? a : b;
	}
	
	public static int min(int a, int b){
		return (a < b) ? a : b;
	}
	
	public static int min(int a, int b, int c){
		return (min(a, b) < c) ? min(a, b) : c;
	}
	
	public static int minEditDistance(String a, String b){
		int[][] vals = new int[a.length() + 1][b.length() + 1];
		for(int i = 0; i < vals.length; i++){
			vals[i][0] = i;
		}
		for(int j = 0; j < vals[0].length; j++){
			vals[0][j] = j;
		}
		for(int i = 1; i < vals.length; i++){
			for(int j = 1; j < vals[0].length; j++){
				if(a.charAt(i - 1) == b.charAt(j - 1)){
					vals[i][j] = vals[i - 1][j - 1];
				}else{
					vals[i][j] = min(vals[i - 1][j], vals[i][j - 1]) + 1;
				}
			}
		}		
		return vals[a.length()][b.length()];		
	}	
	
	public static String LCSubstring(String a, String b){
		int[][] mem = new int[a.length() + 1][b.length() + 1];
		for(int i = 0; i < mem.length; i++){
			mem[i][0] = 0;
		}
		
		for(int i = 0; i < mem[0].length; i++){
			mem[0][i] = 0;
		}
		
		for(int i = 1; i < mem.length; i++){
			for(int j = 1; j < mem[0].length; j++){
				if(a.charAt(i - 1) == b.charAt(j - 1)){
					mem[i][j] = mem[i - 1][j - 1] + 1;
				}else{
					mem[i][j] = 0;
				}				
			}
		}
		
		int x = 0;
		int y = 0;
		int max = 0;
		for(int i = 0; i < mem.length; i++){
			for(int j = 0; j < mem[0].length; j++){
				if(mem[i][j] > max){
					max = mem[i][j];
					x = i;
					y = j;
				}
			}
		}
		String s = "";
		while(mem[x][y] != 0){
			s+=a.charAt(x - 1);
			x--;
			y--;
		}
		return new StringBuffer(s).reverse().toString();
	}
	
	public static int coinsChange(int value, int[] set){
		int[] money = new int[value + 1];
		money[0] = 0;
		for(int i = 1; i < money.length; i++){
			int min = Integer.MAX_VALUE;
			for(int j  = 0; j < set.length; j++){
				if(i - set[j] >= 0 && money[i - set[j]] < min){
					min = money[i - set[j]];
				}
			}
			money[i] = min + 1;
		}
		return money[value];
	} 
	
	public static int kadanesAlg(int[] vals){
		int max_so_far = 0, max_ending_here = 0;
		for(int i : vals){
			max_ending_here = Math.max(0, max_ending_here + i);
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		
		return max_so_far;
	}
	
	public static int kadanesAlg2d(int[][] vals){
		int[] temp = new int[vals[0].length];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < vals.length; i++){
			for(int j = 0; j < temp.length; j++){
				temp[j] = 0;
			}
			for(int j = i; j < vals.length; j++){
				for(int k = 0; k < temp.length; k++){
					temp[k] += vals[j][k];
				}
				int temp_max = kadanesAlg(temp);
				if(temp_max > max){
					max  = temp_max;
				}
			}
		}
		return max;
	}
	
	public static int maxSteal(int[] values){//f(i)=max(v[i] + f(i-2), f(i-1))
		if(values.length == 0){
			return 0;
		}
		
		if(values.length == 1){
			return values[0];			
		}
		
		if(values.length == 2){
			return max(values[0], values[1]);
		}
		
		int temp1 = values[0];//f(i-2)
		int temp2 = max(values[0], values[1]);//f(i-1)
		int res = 0;
		for(int i = 2; i < values.length; i++){
			res = max(values[i] + temp1, temp2);//f(i)
			temp1 = temp2;
			temp2 = res;
		}
		
		return res;
	}	
	
	public static String LongetSubstrAllSame(String s){
	
		if(s.length() == 1){
			return s;
		}
		int max_len = Integer.MIN_VALUE;
		int len = 1;
		int start = 0;
		int end = 0;
		int temp_start = -1;
		for(int i = 1; i < s.length(); i++){
			if(s.charAt(i - 1) == s.charAt(i)){
				len++;
			}else{
				len = 1;
				temp_start = i - 1;
			}				
			if(len > max_len){
				max_len = len;
				start = temp_start + 1;
				end = i;
			}
		}
		
		return s.substring(start, end + 1);
		
	}
	
	public static int minPathSum(int[][] grid) {
        for(int i = 1; i < grid.length; i++ ){
            grid[i][0] += grid[i - 1][0];
        }
        for(int i = 1; i < grid[0].length; i++ ){
            grid[0][i] += grid[0][i - 1];
        }
        
        for(int i = 1; i < grid.length; i++){
            for(int j = 1; j < grid[0].length; j++){
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[grid.length - 1][grid[0].length - 1];
    }
	
	public static void paths(int[][] grid, int x, int y, String p, ArrayList<String> ps){
		if(x < grid.length - 1 && y < grid[0].length - 1){
			paths(grid, x + 1, y, p + " " + grid[x][y], ps);
			paths(grid, x, y + 1, p + " " + grid[x][y], ps);
			
		}if(x == grid.length - 1 && y == grid[0].length - 1){
			p += " " + grid[x][y];
			ps.add(p);
			return;
		}if(x == grid.length - 1){
			paths(grid, x, y + 1, p + " " + grid[x][y], ps);
			
		}if(y == grid[0].length - 1){
			paths(grid, x + 1, y, p + " " + grid[x][y], ps);			
		}
	}
	
	public static int numberOfPaths(int m, int n){
		if(m == 1 || n == 1){
			return 1;
		}
		
		return numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1);
	}
	
	/*public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        int[] alph = new int[26];
        for(int i = 0; i < alph.length; i++){
            alph[i] = 0;
        }
        int length = Integer.MIN_VALUE;
        int temp = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(alph[c - 'a'] == 0){
                alph[c - 'a'] = 1;
                temp++;
            }else{
                if(temp > length){
                    length = temp;
                }
                temp = 0;
                for(int j = 0; j < alph.length; j++){
                    alph[j] = 0;
                }
                i--;
            }
        }
        if(temp > length){
            length = temp;
        }
                
        return length;
    }*/
	
	public static int maximalRectangle(char[][] matrix) {//not dp
		int maxsize = 0;
		for(int i = 1; i <= matrix.length; i++){
			for(int j = 1; j <= matrix[0].length; j++){
				int tempsize = i*j;
				boolean check = false;
				loop:
				for(int k = 0; k < matrix.length; k++){
					for(int l = 0; l < matrix[0].length; l++){
						check = isAllOnes(matrix, k, l, i, j);
						if(check){
							break loop;
						}
					}
				}
				if(check && tempsize > maxsize){
					maxsize = tempsize;
				}
			}
		}
		return maxsize;
    }
	
	public static boolean isAllOnes(char[][] matrix, int i, int j, int len_i, int len_j){
		if(i + len_i > matrix.length || j + len_j > matrix[0].length){
			return false;
		}
		boolean check = true;
		for(int k = i; k < len_i + i; k++){
			for(int l = j; l < len_j + j; l++){
						
				check &= (matrix[k][l] == '1');
				if(check == false){
					return false;
				}
			}
		}
		return check;
	}
	
	public static int knapsack01(int capacity, int[] values, int[] weights){//
		int[][] profits = new int[values.length + 1][capacity + 1];
		
		for(int i = 0; i < profits.length; i++){
			profits[i][0] = 0;
		}
		
		for(int i = 0; i < profits[0].length; i++){
			profits[0][i] = 0;
		}
		
		for(int i = 1; i < profits.length; i++){
			for(int j = 1; j < profits[0].length; j++){
				if(j < weights[i - 1]){
					profits[i][j] = profits[i - 1][j];
				}else{
					profits[i][j] = Math.max(profits[i - 1][j], values[i - 1] + profits[i - 1][j - weights[i - 1]]);
				}
			}
		}
		
		return profits[profits.length - 1][profits[0].length - 1];
	}
	
	public static String reverseString(String s){
		if(s.length() < 2){
			return s;
		}		
		return reverseString(s.substring(1)) + s.charAt(0); 
	}
	
	public static int[] getKMPFailureFunction(String needle){	
		if(needle.length() == 1){
			int[] a = new int[needle.length()];
			a[0] = -1;
			return a;
		}
		int[] T = new int[needle.length()];
		T[0] = -1;
		T[1] = 0;
		int pos = 2;
		int cnd = 0;
		while(pos < T.length){
			if(needle.charAt(pos - 1) == needle.charAt(cnd)){
				cnd++;
				T[pos] = cnd;
				pos++;
			}else if(cnd > 0){
				cnd = T[cnd];
			}else{
				T[pos] = 0;
				pos++;
			}
		}
		
		return T;
	}
	
	public static int KMP(String haystack, String needle){
		int m = 0;
		int i = 0;
		int[] T = getKMPFailureFunction(needle);
		while(m + i < haystack.length()){
			if(haystack.charAt(m + i) == needle.charAt(i)){
				if(i == needle.length() - 1){
					return m;
				}
				i++;
			}else{
				m = m + i - T[i];
				if(T[i] > -1){
					i = T[i];
				}else{
					i = 0;
				}
			}
		}
		return -1;
	}
	
	public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Arrays.sort(S);
		for(int i : S){
			a.add(i);
		}
        return subsetsHelper((ArrayList<Integer>) new ArrayList(Arrays.asList(S)));
    }
    
    public static ArrayList<ArrayList<Integer>> subsetsHelper(ArrayList<Integer> S){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(S.isEmpty()){
            res.add(new ArrayList<Integer>());
            return res;
        }
        
        int head = S.get(0);	
        ArrayList<Integer> rest = (ArrayList<Integer>) new ArrayList(S.subList(1, S.size()));
        for(ArrayList<Integer> set : subsetsHelper(rest)){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(head);
            temp.addAll(set);
            res.add(temp);
            res.add(set);
        }
        return res;
    }	
    
    public static void shuffle(int[] a){
    	Random random = new Random();
    	for(int i = 0; i < a.length; i++){
    		int pos = i + random.nextInt(a.length - i);
    		swap(a, i, pos);
    	}
    }
    
    public static void swap(int[]a, int i, int j){
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
	
        
    public static boolean partitionProblem(int[] a, int targetSum){
    	
    	boolean[][] b = new boolean[a.length + 1][targetSum + 1];
    	
    	for(int i = 0; i < b.length; i++){
    		b[i][0] = true;
    	}
    	
    	for(int i = 1; i < b[0].length; i++){
    		b[0][i] = false;
    	}
    	
    	for(int i = 1; i < b.length; i++){
    		for(int j = 1; j < b[0].length; j++){
    			if(j - a[i - 1] >= 0){
    				b[i][j] = b[i - 1][j] || b[i - 1][j - a[i - 1]];
    			}else{
    				b[i][j] = b[i - 1][j];
    			}
    		}
    	}
    	
    	return b[b.length - 1][b[0].length - 1];
    	
    }    
    
    /*static class Queue<E>{
    	Stack<E> in = new Stack<E>();
    	Stack<E> out = new Stack<E>();
    	
    	public void add(E a){
    		in.push(a);
    	}
    	
    	public E poll(){
    		if(out.isEmpty()){
    			while(!in.isEmpty()){
    				out.push(in.pop());
    			}
    		}
    		
    		return out.pop();
    	}
    }*/
    
    public static class Singleton{
    	private static Singleton instance = null;
    	private Singleton(){
    		//...
    	}
    	
    	public static Singleton getInstance(){
    		if(instance == null){
    			instance = new Singleton();
    		}
    		return instance;
    	}
    }
    
    static class Stack<E>{
    	private Queue<E> in = new LinkedList<E>();
    	private Queue<E> out = new LinkedList<E>();
    	
    	void push(E e){
    		in.add(e);
    	}
    	
    	E pop(){
    		while(in.size() != 1){
    			out.add(in.poll());
    		}
    		
    		E res = in.poll();
    		Queue<E> temp = out;
    		out = in;
    		in = temp;
    		return res;
    	}
    }
    
        
    public static double findMedian(int A[], int B[]){
        int n = A.length + B.length;
        double m = (double) kTh(A, 0, A.length, B, 0, B.length, n/2);
        if(n%2 == 0 && n >=2){
            m = (m + (double) kTh(A, 0, A.length, B, 0, B.length, n/2 - 1)) * 0.5; 
        }
        return m;
    }
    
    public static int kTh(int[] A, int begA, int endA, int[] B, int begB, int endB, int k){
    	
        if(begA >= endA){
            return B[begB + k];
        }
        
        if(begB >= endB){
            return A[begA + k];
        }
        
        
        int midA = (begA + endA)/2;
        int midB = (begB + endB)/2;
        
        int lenA = midA - begA;
        int lenB = midB - begB;
        
        if(A[midA] <= B[midB]){
            if(k <= lenA + lenB){
                return kTh(A, begA, endA, B, begB, midB, k);
            }else{
            	return kTh(A, midA + 1, endA, B, begB, endB, k - lenA - 1);
            }
        }else{
            if(k <= lenA + lenB){
                return kTh(A, begA, midA, B, begB, endB, k);
            }else{
                return kTh(A, begA, endA, B, midB + 1, endB, k - lenB - 1);
            }
        }
        
    }
    
    public static void main(String[] args) {    
    	System.out.println(partitionProblem(new int[]{1, 4, 6}, 5));
    }
}
