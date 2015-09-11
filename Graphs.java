import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Graphs {
	
	
	static class UndirectedGraphNode {
	     int label;
	     ArrayList<UndirectedGraphNode> neighbors;
	     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	}		
	
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return null;
        }
        
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        Queue<UndirectedGraphNode> q1 = new LinkedList<UndirectedGraphNode>();
        Queue<UndirectedGraphNode> q2 = new LinkedList<UndirectedGraphNode>();
        
        q1.add(node);
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        q2.add(head);
        visited.add(node);
        
        
        while(!q1.isEmpty()){
            UndirectedGraphNode cur = q1.poll();
            UndirectedGraphNode ex = q2.poll();
            ArrayList<UndirectedGraphNode> neighbors = cur.neighbors;
            visited.add(cur);
            for(int i = 0; i < neighbors.size(); i++){                
            	UndirectedGraphNode neighbor = neighbors.get(i);
            	if(!visited.contains(neighbor)){
	                UndirectedGraphNode n = new UndirectedGraphNode(neighbor.label);
	                ex.neighbors.add(n);	                    
                    q1.add(neighbor);
                    q2.add(n);
                    visited.add(neighbor);
                }
            }
        }
        
        return head;
    }
	
		
	public static void BFS(UndirectedGraphNode node){
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		
		if(node != null){
			q.add(node);
			visited.add(node);
		}	
		
		while(!q.isEmpty()){
			UndirectedGraphNode cur = q.poll();
			System.out.print(cur.label + " ");
			//visited.add(cur);
			for(int i = 0; i < cur.neighbors.size(); i++){
				UndirectedGraphNode n = cur.neighbors.get(i);
				if(!visited.contains(n)){
					q.add(n);
					visited.add(n);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		UndirectedGraphNode a = new UndirectedGraphNode(0);
		UndirectedGraphNode b = new UndirectedGraphNode(0);
		UndirectedGraphNode c = new UndirectedGraphNode(0); 
		
		a.neighbors.add(b);
		a.neighbors.add(c);

		b.neighbors.add(a);
		b.neighbors.add(c);
		
		c.neighbors.add(a);
		c.neighbors.add(b);
				
		//BFS(a);
		//BFS(cloneGraph(a));
	}
}
