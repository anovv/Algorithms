import java.util.ArrayList;
import java.util.List;


public class Test {
	
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int val){
			this.val = val;
		}
	}
	
	static class Stack{
		int capacity = 4;
		
		int[] s = new int[capacity];
		int index = -1;
		
		void push(int elem){
			if(index == capacity - 1){
				capacity *= 2;
				int[] temp = new int[capacity];
				for(int i = 0; i < s.length; i++){
					temp[i] = s[i];
				}
				s = temp;
			}
			index++;
			s[index] = elem;
		}
		
		int pop(){
			if(index >= 0){
				index--;
				return s[index + 1];
			}
			return -1;
		}
	}
		
	public static ArrayList<TreeNode> generateTrees(int n) {
        int vals[] = new int[n];
        
        for(int i = 0; i < vals.length; i++){
            vals[i] = i + 1;
        }
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        permutations(vals, 0, res);
        ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
        for(ArrayList<Integer> v : res){
            TreeNode node = generate(v);
            trees.add(node);
        }
        
        return trees;
    }
    
    public static TreeNode generate(ArrayList<Integer> vals){
        if(vals.size() == 0){
            return null;
        }
        TreeNode root = new TreeNode(vals.get(0));
        for(int i = 1; i < vals.size(); i++){
            insert(root, vals.get(i));
        }
        
        return root;
    }
    
    public static void insert(TreeNode root, int val){
        if(root == null){
            return;
        }
        
        if(val > root.val){
            if(root.right != null){
                insert(root.right, val);
            }else{
                root.right = new TreeNode(val);
            }
        }else{
            if(root.left != null){
                insert(root.left, val);
            }else{
                root.left = new TreeNode(val);
            }
        }
    }
    
    public static void permutations(int[] vals, int k, ArrayList<ArrayList<Integer>> res){
        for(int i = k; i < vals.length; i++){
            swap(vals, i, k);
            permutations(vals, k + 1, res);
            swap(vals, i, k);
        }
         
         
        if(k == vals.length - 1){
            ArrayList<Integer> a = new ArrayList<Integer>();
            for(int i: vals){
                a.add(i);
            }
            res.add(a);
        }
    }    
    
    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
	
    static class Triplet{
    	int a;
    	int b;
    	int c;
    	Triplet(int a, int b, int c){
    		this.a = a;
    		this.b = b;
    		this.c = c;
    	}
    	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			result = prime * result + c;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Triplet other = (Triplet) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
    	
    }
    
	public static void main(String[] args) {
		
		System.out.println("01".substring(0, 0).length());
	}
	
	static int shortPalin(String S) {
        int[] min = {Integer.MAX_VALUE};
        int cur = 0;
        helper(S, min, cur);
        return cur;
    }

    static void helper(String s, int[] min, int cur){
        if(cur > 3){
        	return;
        }
        if(isPalin(s)){
            min[0] = Math.min(min[0], cur);
            return;
        }
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            List<Character> list = new ArrayList<Character>();
            for(int j = 0; j < s.length(); j++){
                list.add(s.charAt(j));
            }
            for(int j = 0; j < s.length(); j++){
                list.add(j, c);
                String str = listToString(list);
                helper(str, min, cur + 1);
                list.remove(j);
            }
        }
    }
    
    static String listToString(List<Character> list){
        String res = "";
        for(char c : list){
            res += c;
        }
        return res;
    }

    static boolean isPalin(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    
    public static int minZeroOne(int orig){
        int i = 1;
        int num = orig;
        while(num < Integer.MAX_VALUE){
            num = orig * i;
            if(isZeroOne(num)){
                break;
            }else{
                i++;
            }
        }
        return num;
    }
    
    public static boolean isZeroOne(int num){
        String s = num + "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1' || s.charAt(i) == '0'){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
}
