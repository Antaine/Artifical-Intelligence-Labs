package ie.gmit.sw.ai;

public class IterativeDeepening {
   public void search(Node node, int limit, int depth){
      if (depth > limit){
    	  return;
      }

      if (node.isGoalNode()){
    	  System.out.println("Reached goal node " + node.getNodeName());
    	  System.exit(0);
      }else{
    	  if (!node.isVisited()){
    		  node.setVisited(true);
    		  Node[] children = node.children();
    		  for (int i = 0; i < children.length; i++) {
    			  search(children[i], limit, depth + 1);
    		  }
    	  }
      }
   }

   public static void main(String[] args) {
	   	IterativeDeepening dfid;
      	int limit = 0;
      	while(true){
      		dfid = new IterativeDeepening ();			
      		dfid.search(new Maze().getStartNode(), limit, 0);			
      		limit++;
      	}
   }
}
