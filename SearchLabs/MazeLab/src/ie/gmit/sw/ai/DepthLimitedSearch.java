package ie.gmit.sw.ai;

public class DepthLimitedSearch {
	private static final int limit = 1;
	public DepthLimitedSearch(Maze maze) {
		search(maze.getStartNode(), limit, 0);		
	}

	public void search(Node node, int limit, int depth){
		if (depth > limit){
			System.out.println("Maximum Depth Reached. Failed to locate goal node.");
			System.exit(0);
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
		Maze maze = Maze.getInstance();
		DepthLimitedSearch search = new DepthLimitedSearch(maze);
	}
}
