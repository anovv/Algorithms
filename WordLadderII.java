import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class WordLadderII {
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<String> ladder = new ArrayList<String>(); 
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        WordTree tree = buildTree(start, end, dict);
        traverseTree(tree.root, res, ladder);
        long s = 60000;
        for(int i = 0; i < res.size(); i++){
            if(res.get(i).size() < s){
                s = res.get(i).size();
            }
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < res.size(); i++){
            if(res.get(i).size() == s){
                result.add(res.get(i));
            }
        }
        
        return result;
    }
    
    public void traverseTree(Node root, ArrayList<ArrayList<String>> res, ArrayList<String> ladder){
        //ArrayList<String> ladder = new ArrayList<String>();
        ladder.add(root.data);
        if(root.childs == null){
            res.add(ladder);
        }else{
            for(int i = 0; i < root.childs.size(); i++){
                traverseTree(root.childs.get(i), res, ladder);
            }
        }
        ladder.remove(root.data);
    }
    
    public class WordTree{
        Node root;
        WordTree(Node root){
            this.root = root;
        }
    }
    
    public class Node{
        String data;
        Node parent;
        ArrayList<Node> childs = null;
        Node(String data){
            this.data = data;
        }
        
        public void setChilds(ArrayList<String> childs){
            this.childs = new ArrayList<Node>();
            for(int i = 0; i < childs.size(); i++){
                Node child = new Node(childs.get(i));
                this.childs.add(child);
                child.parent = this;
            }
        }
    }
    
    public ArrayList<String> getClosest(HashSet<String> dict, String patern){
        ArrayList<String> res = new ArrayList<String>();
        Iterator it = dict.iterator();
        while(it.hasNext()){
            String temp = (String) it.next();
            int m = 0;
            for(int i = 0; i < patern.length(); i++){
                if(patern.charAt(i)!=temp.charAt(i)){
                    m++;
                }
            }
            if(m == 1){
                res.add(temp);
                //dict.remove(temp);
                it.remove();
            }
        }
        
        return res;
    }
    
    public void addEnd(Node root, String end){
        if(root.childs == null || root.childs.size() == 0){
            ArrayList<String> e = new ArrayList<String>();
            e.add(end);
            root.setChilds(e);
        }else{
            for(int i = 0; i < root.childs.size(); i++){
                addEnd(root.childs.get(i), end);
            }
        }
    }
    
    public void fill(HashSet<String> dict, Node root){
        /*if(!dict.isEmpty()){
            ArrayList<String> closest = getClosest(dict, root);
            if(closest.size()!=0){
                root.setChilds(closest);
                for(int i = 0; i < root.childs.size(); i++){
                    fill(dict, root.childs.get(i), end);
                }
            }else{
                ArrayList<String> e = new ArrayList<String>();
                e.add(end);
                node.setChilds(e);
            }
        }*/
        Node current = root;
        int level = 0;
        while(!dict.isEmpty()){
            ArrayList<String> closest = getClosest(dict, current.data);
            if(closest.size()!=0){
                current.setChilds(closest);
            }
            if(current.parent == null){
                level++;
                current = current.childs.get(0);
                continue;
            }
            int pos = 0;
            for(int i = 0; i < current.parent.childs.size(); i++){
                if(current.parent.childs.get(i)==current){
                    pos = i;
                    break;
                }
            }
            if(pos < current.parent.childs.size()-1){
                current = current.parent.childs.get(pos++);
            }else if(pos == current.parent.childs.size()-1){
                for(int i = 0; i < level; i++){
                    current = current.parent;
                }
                for(int i = 0; i < level + 1; i++){
                	if(current.childs!=null){
	                    current = current.childs.get(0);
	                    level++;
                	}else{
                		break;
                	}
                }
            }
        }
    }
    
    public WordTree buildTree(String start, String end, HashSet<String> dict){
        Node root = new Node(start);
        //dict.add(end);
        WordTree tree = new WordTree(root);
        fill(dict, tree.root);
        addEnd(tree.root, end);
        return tree;
    }
    
    public static void main(String[] args){
    	HashSet<String> dict = new HashSet<String>();
    	dict.add("hot");
    	dict.add("dot");
    	dict.add("dog");
    	dict.add("lot");
    	dict.add("log");
    	System.out.println(new WordLadderII().findLadders("hit", "cog", dict));
    }
}
