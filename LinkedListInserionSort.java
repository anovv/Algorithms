import java.util.HashSet;



public class LinkedListInserionSort {
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
	
	
	public static ListNode insertionSortList(ListNode head) {
        if(head == null){
            return null;
        }
        int length = getLength(head);
        ListNode cur = head;
        while(cur.next != null){
            if(cur.next.val < cur.val){
                
                ListNode c = head;
                ListNode p = null;
                while(c.val < cur.next.val){
                    p = c;
                    c = c.next;
                }
                if(p == null){
                    ListNode temp = new ListNode(cur.next.val);
                    temp.next = c;
                    head = temp;
                    cur.next = cur.next.next;
                }else{
                    ListNode temp = new ListNode(cur.next.val);
                    p.next = temp;
                    temp.next = cur;
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;   
            }
        }
        return head;
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
    
    public static void main(String[] args){
    	ListNode t = new ListNode(4);
    	t.next = new ListNode(3);
    	t.next.next = new ListNode(2);
    	t.next.next.next = new ListNode(1);
    	//printList(insertionSortList(t));
    	HashSet<Integer> set = new HashSet<Integer>();
    }
}
