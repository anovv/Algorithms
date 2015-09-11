import java.util.ArrayList;
import java.util.Stack;


public class Triangle {
	public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        //ArrayList<Integer> res = new ArrayList<Integer>();
        int[] min = {Integer.MAX_VALUE};
        /*for(int i = 0; i < res.size(); i++){
            if(res.get(i) < min){
                min = res.get(i);
            }   
        }*/
        helper(min, 0, triangle, 0, 0);
        
        return min[0];
    }
    
    public static void helper(int[] min, int sum, ArrayList<ArrayList<Integer>> triangle, int cur_ar, int cur_elem){
        if(cur_ar == triangle.size()){
            if(min[0] > sum){
            	min[0] = sum;
            }
        	return;
        }
        
        sum += triangle.get(cur_ar).get(cur_elem);
        
        helper(min, sum, triangle, cur_ar + 1, cur_elem);
        helper(min, sum, triangle, cur_ar + 1, cur_elem + 1);
    }
    
    public static void main(String[] args){
    	ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> a = new ArrayList<Integer>();
    	ArrayList<Integer> b = new ArrayList<Integer>();
    	ArrayList<Integer> c = new ArrayList<Integer>();
    	ArrayList<Integer> d = new ArrayList<Integer>();
    	a.add(2);
    	b.add(3);
    	b.add(4);
    	c.add(6);
    	c.add(5);
    	c.add(7);
    	d.add(4);
    	d.add(1);
    	d.add(8);
    	d.add(3);
    	triangle.add(a);
    	triangle.add(b);
    	triangle.add(c);
    	triangle.add(d);
    	System.out.println(minimumTotal(triangle));
    }
}
