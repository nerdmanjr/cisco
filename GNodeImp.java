import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * 
 */

/**
 * @author General
 *
 */
public class GNodeImp implements GNode {
	private String name;
	private List<GNode> children;

	public GNodeImp(String name) {
		this.name = name;
		this.children = new ArrayList<GNode>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public GNode[] getChildren() {
		if(this.children != null)
			return (GNode[]) this.children.toArray(new GNode[this.children.size()]);
		else
			return new GNode[]{};
	}

	public void addChild(GNode child){
		this.children.add(child);
	}

	public ArrayList walkGraph(GNode node) {
		ArrayList<GNode> nodes = new ArrayList<GNode>();
        Stack<GNode> stack = new Stack<GNode>();
        HashSet<String> visited = new HashSet<String>();
        stack.push(node);

        while (!stack.isEmpty()) {
        	GNode currNode = stack.pop();

            if(!visited.contains(currNode.getName())){
            	nodes.add(currNode);
            	visited.add(currNode.getName());
            }

            for (int index = 0; index < currNode.getChildren().length; index++) {
                GNode child = currNode.getChildren()[index];

                if (!visited.contains(child.getName())) {
                    stack.push(child);
                }
            }
        }

        return nodes;
    }

	public ArrayList paths(GNode node){
		ArrayList<ArrayList<GNode>> paths = new ArrayList<ArrayList<GNode>>();
		Stack<GNode> path = new Stack<GNode>();

		pathsUtil(node, paths, path, 0);
		return paths;
	}

	public void pathsUtil(GNode currNode, ArrayList<ArrayList<GNode>> paths, Stack<GNode> path, int count){
		if(currNode == null)
			return;
		
		path.push(currNode);

		if(currNode.getChildren().length == 0){
			paths.add(count, new ArrayList<GNode>());
			paths.get(count).addAll(path);
			path.pop();
			count++;
		}
		else{
			for(GNode child : currNode.getChildren()){
				pathsUtil(child, paths, path, count);
			}

			path.pop();
		}
	}

	@Override
	public String toString(){
		return this.name;
	}

	public static void main(String[] args) {
		/**************  Test One **************/
		GNodeImp test = new GNodeImp("");
		GNodeImp rootOne = new GNodeImp("A");
		GNodeImp c1 = new GNodeImp("B");
		GNodeImp c2 = new GNodeImp("C");
		GNodeImp c3 = new GNodeImp("D");

		GNodeImp c11 = new GNodeImp("E");
		GNodeImp c12 = new GNodeImp("F");
		GNodeImp c21 = new GNodeImp("G");
		GNodeImp c22 = new GNodeImp("H");
		GNodeImp c23 = new GNodeImp("I");
		GNodeImp c31 = new GNodeImp("J");

		rootOne.addChild(c1);
		rootOne.addChild(c2);
		rootOne.addChild(c3);

		c1.addChild(c11);
		c1.addChild(c12);

		c2.addChild(c21);
		c2.addChild(c22);
		c2.addChild(c23);

		c3.addChild(c31);

		System.out.println("/**************  Test One List Initiated**************/");
		System.out.println("Expected Output: (A, D, J, C, I, H, G, B, F, E)");
		System.out.println("Actual Output: " + test.walkGraph(rootOne));
		System.out.println("/**************  Test Two Path Initiated**************/");
		System.out.println("Expected Output: ( (A B E) (A B F) (A C G) (A C H) (A C I) (A D J) )");
		System.out.println("Actual Output: " + test.paths(rootOne));
		System.out.println("/**************  Test One Completed**************/");

		GNodeImp rootTwo = new GNodeImp("A");
		GNodeImp d1 = new GNodeImp("B");
		GNodeImp d2 = new GNodeImp("C");
		GNodeImp d3 = new GNodeImp("D");

		GNodeImp d11 = new GNodeImp("E");
		GNodeImp d12 = new GNodeImp("F");
		
		GNodeImp d21 = new GNodeImp("G");
		GNodeImp d22 = new GNodeImp("H");
		GNodeImp d23 = new GNodeImp("I");
		GNodeImp d31 = new GNodeImp("J");
		GNodeImp d311 = new GNodeImp("K");
		GNodeImp d312 = new GNodeImp("L");

		rootTwo.addChild(d1);
		rootTwo.addChild(d2);

		d1.addChild(d3);
		d2.addChild(d3);
		
		d3.addChild(d11);
		d3.addChild(d12);
		d11.addChild(d21);
		d12.addChild(d22);
		d12.addChild(d23);

		d23.addChild(d31);
		d23.addChild(d311);
		d23.addChild(d312);

		System.out.println("/**************  Test Two List Initiated**************/");
		System.out.println("Expected Output: (A, C, D, F, I, L, K, J, H, E, G, B)");
		System.out.println("Actual Output: " + test.walkGraph(rootTwo));
		System.out.println("/**************  Test Two Path Initiated**************/");
		System.out.println("Expected Output: ((A, C, D, F, I, L), (A, C, D, F, I, K), (A, C, D, F, I, J), (A, C, D, F, H), (A, C, D, E, G), (A, B, D, F, I, L), (A, B, D, F, I, K), (A, B, D, F, I, J), (A, B, D, F, H), (A, B, D, E, G))");
		System.out.println("Actual Output: " + test.paths(rootTwo));
		System.out.println("/**************  Test Two Completed**************/");
	}
}
