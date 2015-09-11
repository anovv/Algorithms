
public class LinkedListReorder {
	
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public static void printList(ListNode head){
		while(head!=null){
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	public static void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        int l = getLength(head);
        ListNode prev = head;
        ListNode cur = head.next;
        for (int i = 0; i < (l-1)/2; i++){
            ListNode p = prev;
            ListNode c = cur;
            while(c.next != null){
                p = c;
                c = c.next;
            }
            p.next = null;
            prev.next = c;
            c.next = cur;
            
            prev = cur;
            cur = cur.next;
        }
    }
    
    public static int getLength(ListNode head){
        int length = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            length++;
        }
        return length;
    }
    
    public static ListNode getMid(ListNode head){
        int length = getLength(head);
        if(head == null){
        	return null;
        }
        
        for(int i = 0; i < length/2; i++){
            head = head.next;
        }
        
        return new ListNode(head.val);
    }
    
    public static ListNode getLeft(ListNode head){
        int length = getLength(head);
        if(length <=1){
        	return null;
        }
        if(head == null){
        	return null;
        }
        
        ListNode pointer = new ListNode(head.val);
        ListNode begin = pointer;
        
        for(int i = 0; i < length/2 - 1; i++){
            head = head.next;
            pointer.next = new ListNode(head.val);
            pointer = pointer.next;
        }
        
        return begin;
    }
    
    
    public static ListNode getRight(ListNode head){
        int length = getLength(head);
        if(length <=2){
        	return null;
        }
        if(head == null){
        	return null;
        }
        
        for(int i = 0; i < length/2 + 1; i++){
            head = head.next;
        }
        
        ListNode pointer = new ListNode(head.val);
        ListNode begin = pointer;
        
        while(head.next != null){
        	head = head.next;        	
        	pointer.next = new ListNode(head.val);
        	pointer = pointer.next;
        }
        return begin;
    }
    
    public static TreeNode sortedArrayToBST(int[] num) {

        if(num == null){
            return null;
        }
    	if(num.length == 0){
            return null;
        }
        
        int mid = getMidI(num);
        int[] right = getRightI(num);
        int[] left = getLeftI(num);
        
        TreeNode root = new TreeNode(mid);
        root.left = sortedArrayToBST(left);
        root.right = sortedArrayToBST(right);
        
        return root;
    }
    
    public static int getMidI(int[] num){
        if(num == null){
            return 0;
        }
        
        return num[num.length/2];
    }
    
    public static int[] getLeftI(int[] num){
        if(num == null){
            return null;
        }
        if(num.length <= 1){
            return null;
        }
        
        int[] res = new int[num.length/2];
        for(int i = 0; i < res.length; i++){
            res[i] = num[i];
        }
        
        return res;
    }
    
    
    public static int[] getRightI(int[] num){
        if(num == null){
            return null;
        }
        if(num.length <= 2){
            return null;
        }
        
        int[] res = new int[(num.length - 1)/2];
        for(int i = 0; i < res.length; i++){
            res[i] = num[i];
        }
        
        return res;
    }
    
    public static ListNode reverseReq(ListNode head){
    	if(head == null || head.next == null){
    		return head;
    	}
    	
    	ListNode temp = head.next;
    	
    	head.next = null;
    	
    	ListNode rest = reverseReq(temp);
    	
    	temp.next = head;
    	
    	return rest;
    }


    
    public static void main(String[] args){
    	ListNode l = new ListNode(1);
    	l.next = new ListNode(2);
    	l.next.next = new ListNode(3);
    	l.next.next.next = new ListNode(4);
    	//l.next.next.next.next = new ListNode(5);

    	/*printList(getLeft(l));
    	System.out.println("");
    	printList(getMid(l));
    	System.out.println("");

    	printList(getRight(l));
    	System.out.println("");*/
    	
    	printList(reverseReq(l));
    	
    	
    	
    }
    
	
}
