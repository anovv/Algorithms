import java.util.ArrayList;


public class StringsMultiply {
    public static String multiply(String num1, String num2) {
        int a[] = new int[num1.length()];
        int b[] = new int[num2.length()];
        
        for(int i = 0; i < a.length; i++){
        	a[i] = Character.getNumericValue(num1.charAt(i));
        }
        
        for(int i = 0; i < b.length; i++){
        	b[i] = Character.getNumericValue(num2.charAt(i));
        }
        
        if(b.length > a.length){
            int[] temp = a;
            a = b;
            b = temp;
        }
        
        ArrayList<ArrayList<Integer>> vals = new ArrayList<ArrayList<Integer>>(); 
        for(int i = b.length - 1; i >= 0; i--){
            int val = 0;
            int prev = 0;
            ArrayList<Integer> res = new ArrayList<Integer>(); 
            for(int j = a.length - 1; j >= 0; j--){
                val = b[i]*a[j] + prev;
                //System.out.print(val);                
                if(val > 9){
                    String s = val + "";
                    
                    int[] d = new int[2];
                    for(int k = 0; k < 2; k++){
                        d[k] = Character.getNumericValue(s.charAt(k));
                    }
                    prev = d[0];
                    val = d[1];
                    
                }else{
                    prev = 0;
                }
                res.add(val);
            }
            if(prev != 0){
                res.add(prev);
            }
            for(int j = 0; j < res.size()/2; j++){
            	int temp = res.get(j); 
            	res.set(j, res.get(res.size() - 1 - j));
            	res.set(res.size() - 1 - j, temp);
            }
            
            vals.add(res);
            //System.out.println(res);
        }
        for(int i = 0 ; i < vals.size(); i++){
        	for(int j = 0; j < i; j++){
        		vals.get(i).add(0);
        	}
        }
        String result = "";
        
        if(vals.size() > 0){
        	ArrayList<Integer> t = vals.get(0);
        	for(int i = 1; i < vals.size(); i++){
        		t = add(t, vals.get(i));
        	}
        	for(int i = 0; i < t.size(); i++){
        		result += t.get(i) + "";
        	}
        	int i = 0;
            while(i < result.length() - 1 && result.charAt(i) == '0'){
                i++;
            }
            if(i == t.size()){
                return "0";
            }
            if(i != 0){
                result = "";
                for(int j = i; i < t.size(); i++){
                    result += t.get(i) + "";
                }
            }
        }        
        
        return result;
        
    }

    public static ArrayList<Integer> add(ArrayList<Integer> a, ArrayList<Integer> b){
    	if(a.size() < b.size()){
    		ArrayList<Integer> temp = a;
    		a = b;
    		b = temp;
    	}
    	int val = 0;
    	int prev = 0;
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	for(int i = a.size() - 1; i >=0; i--){
    		if(i >= a.size() - b.size()){
    			val = a.get(i) + b.get(i - a.size() + b.size()) + prev;
    		}else{
    			val = a.get(i) + prev;
    		}
    		if(val > 9){
    			String s = val + "";
                
                int[] d = new int[2];
                for(int k = 0; k < 2; k++){
                    d[k] = Character.getNumericValue(s.charAt(k));
                }
                prev = d[0];
                val = d[1];
    		}else{
    			prev = 0;
    		}
    		res.add(val);
    	}
    	if(prev != 0){
    		res.add(prev);
    	}
    	for(int i = 0; i < res.size()/2; i++){
    		int temp = res.get(i);
    		res.set(i, res.get(res.size() - 1 - i));
    		res.set(res.size() - 1 - i, temp);
    	}
    	
    	return res;
    }
    
    public static String multiply2(String num1, String num2) {
        String res = "0";
        if(num1.length() < num2.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        
        for(int i = 0; i < num2.length(); i++){
            String temp = mult(num1, num2.charAt(i));
            for(int j = 0; j < i; j++){
                temp += "0";
            }
            res = add(temp, res);
        }
        
        return res;
    }
    
    public static String mult(String a, char c){
        int r = 0;
        String res = "";
        for(int i = a.length() - 1; i >=0; i--){
            int temp = Character.getNumericValue(a.charAt(i)) * Character.getNumericValue(c) + r;
            
            if(temp > 9){
                r = temp/10;
                res += temp%10 + "";
            }else{
                res += temp + "";
            }
        }
        
        if(r > 0){
            res += r + "";
        }
        
        return new StringBuffer(res).reverse().toString();
    }
    
    public static String add(String a, String b){
        if(a.length() < b.length()){
            String temp = a;
            a = b;
            b = temp;
        }
        
        int len = a.length();
        String zeroes = "";
        for(int i = 0; i < a.length() - b.length(); i++){
            zeroes += "0";
        }
        b = zeroes + b;
        
        int r = 0;
        String res = "";
        for(int i = len - 1; i >= 0; i--){
            int temp = Character.getNumericValue(a.charAt(i)) + Character.getNumericValue(b.charAt(i)) + r;
            
            if(temp > 9){
                r = temp/10;
                res += temp%10 + "";
            }else{
                res += temp + "";
            }
        }
        if(r > 0){
            res += r + "";
        }
        
        return new StringBuffer(res).reverse().toString();
    }
       
    public static void main(String[] args){
    	System.out.println(multiply("9369162965141127216164882458728854782080715827760307787224298083754", "7204554941577564438"));
    }
}
