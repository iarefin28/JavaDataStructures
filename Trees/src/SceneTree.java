/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a SceneTree that is constructed of SceneNode objects
 */
public class SceneTree {
	private SceneNode root;
	private SceneNode cursor;
	
	/**
	 * Default constructor of SceneTree, initializes variables to null
	 */
	public SceneTree() {
		root = null;
		cursor = null;
	}
	/** 
	 * Another constructor with parameters of SceneTree
	 * @param first the first node aka the root node
	 */
	public SceneTree(SceneNode first) {
		root = first;
		cursor = first;
	}
	
	/**
	 * This method moves cursor back to the parent node of the currently selected node.
	 * @throws NoSuchNodeException if there is no parent node
	 */
	public void moveCursorBackwards() throws NoSuchNodeException{
		if(cursor.getParento() == null) throw new NoSuchNodeException("This node has no parent node.");
		cursor = cursor.getParento();
		System.out.println("Successfully moved back to " + cursor.getTitle() + ".");
	}
	
	/**
	 * This method moves cursor forward to one of it's children
	 * @param option If the user selects A, B, or C, it will move cursor to left, middle, right child respectively.
	 * @throws NoSuchNodeException
	 */
	public void moveCursorForward(String option) throws NoSuchNodeException {
		if(option.equals("A") && cursor.getLeft() != null) {
			cursor = cursor.getLeft();
			System.out.println("Successfully moved to " + cursor.getTitle() + ".");
		}
		else if(option.equals("A") && cursor.getLeft() == null) throw new NoSuchNodeException("This node has no child node.");
		
		if(option.equals("B") && cursor.getMiddle() != null) {
			cursor = cursor.getMiddle();
			System.out.println("Successfully moved to " + cursor.getTitle() + ".");
		}
		else if(option.equals("B") && cursor.getMiddle() == null) throw new NoSuchNodeException("This node has no child node.");
		
		if(option.equals("C") && cursor.getRight() != null) {
			cursor = cursor.getRight();
			System.out.println("Successfully moved to " + cursor.getTitle() + ".");
		}
		else if(option.equals("C") && cursor.getRight() == null) throw new NoSuchNodeException("This node has no child node.");
	}
	
	/**
	 * This method adds a new node to the tree
	 * @param title the title of the new node
	 * @param sceneDescription a description of the new node
	 * @param parento the parent node of the new node
	 * @param sceneID the scene id of the node to be added
	 * @throws FullSceneException if this new node cannot be added into the position where cursor exists
	 */
	public void addNewNode(String title, String sceneDescription, SceneNode parento, int sceneID) throws FullSceneException {
		if(cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() != null) throw new FullSceneException("The node does not have any available child positions");
		SceneNode newNode = new SceneNode(title, sceneDescription, parento, sceneID);
		
		if (cursor.getLeft() == null) cursor.setLeft(newNode);
		else if(cursor.getMiddle() == null) cursor.setMiddle(newNode);
		else if(cursor.getRight() == null) cursor.setRight(newNode);
	}
	
	/**
	 * This method removes the specified child node of cursor
	 * @param option If the user selects A, B, or C, it will move cursor to left, middle, right child respectively.
	 * @throws NoSuchNodeException if there is no node in the selected option
	 */
	public void removeScene(String option) throws NoSuchNodeException {
		if(option.equals("A") && cursor.getLeft() != null) {
			System.out.println(cursor.getLeft().getTitle() + " removed.");
			if(cursor.getMiddle() != null && cursor.getRight() != null) {
				cursor.setLeft(cursor.getMiddle());
				cursor.setMiddle(cursor.getRight());
				cursor.setRight(null);
			}
			else if(cursor.getMiddle() != null && cursor.getRight() == null) {
				cursor.setLeft(cursor.getMiddle());
				cursor.setMiddle(null);
			}
		}
		else if(option.equals("A") && cursor.getLeft() == null) throw new NoSuchNodeException("No node to remove.");
		
		
		if(option.equals("B") && cursor.getMiddle() != null) {
			System.out.println(cursor.getMiddle().getTitle() + " removed.");
			if(cursor.getRight() != null) {
				cursor.setMiddle(cursor.getRight());
				cursor.setRight(null);
			}
			else {
				cursor.setMiddle(null);
			}
		}
		else if(option.equals("B") && cursor.getMiddle() == null) throw new NoSuchNodeException("No node to remove.");
		
		if(option.equals("C") && cursor.getLeft() != null) {
			System.out.println(cursor.getRight().getTitle() + " removed.");
			cursor.setRight(null);
		}
		else throw new NoSuchNodeException("No node to remove.");
	}
	
	/**
	 * This method moves a scene to become a child of another scene
	 * @param sceneIDToMoveTo the new parent node of current cursor
	 * @throws NoSuchNodeException if the scene with that id does not exist
	 * @throws FullSceneException if the sceneIDToMoveTo already has maximum child nodes
	 */
	public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException {
		SceneNode tempCursor = root;
		tempCursor = moveSceneHelper(tempCursor, sceneIDToMoveTo);
		if(tempCursor == null) throw new NoSuchNodeException("This node doesnt exist.");
		else {
			if(tempCursor.getLeft() != null && tempCursor.getMiddle() != null && tempCursor.getRight() != null) throw new FullSceneException("Not enough space.");
			else {
				cursor.setParento(tempCursor);
				if(tempCursor.getLeft() == null) tempCursor.setLeft(cursor);
				else if(tempCursor.getLeft() != null && tempCursor.getMiddle() == null) tempCursor.setMiddle(cursor);
				else if(tempCursor.getLeft() != null && tempCursor.getMiddle() != null) tempCursor.setRight(cursor);
				System.out.println("Scucessfully moved scene.");
				//if(cursor.getParento().getLeft() == cursor) cursor.getParento();
			}
		}
	}
	
	/**
	 * Recursive helper method for move scene. This finds the scene that will become the new parent node
	 * @param n a node to start searching from
	 * @param moveTo the sceneID of the scene we are looking for
	 * @return SceneNode 
	 */
	public SceneNode moveSceneHelper(SceneNode n, int moveTo) {
		if(n != null) {
			if(n.getSceneID() == moveTo) {
				return n;
			}
			else {
				SceneNode w = moveSceneHelper(n.getLeft(), moveTo);
				if(w == null) {
					w = moveSceneHelper(n.getMiddle(), moveTo);
					if(w == null) {
						w = moveSceneHelper(n.getRight(), moveTo);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @return The path taken to get to cursor from root
	 */
	public String getPathFromRoot() {
		String a = "";
		String[] reversePath = new String[100]; 
		int count = 0;
		SceneNode last = cursor;
		while(last != null) {
			reversePath[count] = last.getTitle();
			count++;
			last = last.getParento();
		}
		for(int i = count-1; i >= 0; i--) {
			a = a + reversePath[i] + ",";
		}
		return a;
	}
	
	/**
	 * Displays the tree in a string format.
	 */
	public String toString() {
		String s = toStringHelper(root);
		return s;
	}
	
	/**
	 * Helper method for toSTring method.
	 * @param w
	 * @return
	 */
	public String toStringHelper(SceneNode w) {
		String s = "";
		s += w.toString() + "\n";
		if(w.getLeft() != null) {
			s += "\t A)";
			s += toStringHelper(w.getLeft());
		}
		if(w.getMiddle() != null) {
			s += "\t B)";
			s += toStringHelper(w.getMiddle());
		}
		if(w.getRight() != null) {
			s += "\t C)";
			s += toStringHelper(w.getRight());
		}
		return s;
	}  
	
	/**
	 * Obtains the SceneNode represented by cursor.
	 * @return
	 */
	public SceneNode getCursor() {
		return cursor;
	}
	/**
	 * Sets a new value for cursor
	 * @param cursor the new value to be set for
	 */
	public void setCursor(SceneNode cursor) {
		this.cursor = cursor;
	}
}
