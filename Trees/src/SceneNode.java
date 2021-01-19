/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents a SceneNode that works with the SceneTree classs
 */
public class SceneNode {
	private String title;
	private String sceneDescription;
	private int sceneID;
	private SceneNode left;
	private SceneNode middle;
	private SceneNode right;
	private SceneNode parento;
	private static int numScenes;
	
	/**
	 * Constructor with no parameters. Initializes a SceneNode object.
	 */
	public SceneNode() {
		title = ""; sceneDescription = ""; sceneID = 0; left = null; middle = null; right = null; numScenes = 1; parento = null;
	}
	
	/**
	 * Constructor that initalizes SceneNode object with specific parameters.
	 * @param title
	 * @param sceneDescription
	 * @param parent
	 * @param sceneID
	 */
	public SceneNode(String title, String sceneDescription, SceneNode parent, int sceneID) {
		this.title = title;
		this.sceneDescription = sceneDescription;
		parento = parent;
		this.sceneID = sceneID;
	}
	
	/**
	 * This method adds a child node to a specfic node.
	 * @param scene the new node to be added
	 * @throws FullSceneException if there is already 3 child nodes
	 */
	public void addSceneNode(SceneNode scene) throws FullSceneException { 
		if(left != null && middle != null && right != null) throw new FullSceneException("No new nodes can be added");
		else if(left == null) left = scene;
		else if(left != null && middle == null) middle = scene;
		else right = scene;
	}
	
	/**
	 * This method returns true if the node is a leaf node and false otherwise.
	 */
	public boolean isEnding() {
		if(left == null && middle == null && right == null) return true; 
		return false;
	}
	
	/**
	 * This method displays Scene information as it would be seen in game mode.
	 */
	public void displayScene() {
		System.out.println(title);
		System.out.println(sceneDescription);
		System.out.println();
		if(left != null) System.out.println("A) " + left.title);
		if(middle != null) System.out.println("B) " + middle.title);
		if(right != null) System.out.println("C) " + right.title);
	}
	
	/**
	 * This method displays all scene information in creation mode.
	 */
	public void displayFullScene() {
		System.out.println("Scene ID#: " + sceneID);
		System.out.println("Title: " + title);
		System.out.println("Scene: " + sceneDescription);
		String s = "";
		if(left != null) s = s + left.title + ",";
		if(middle != null) s = middle.title + ",";
		if(right != null) s = right.title + ",";
		System.out.println("Leads to: " + s);
	}
	
	/**
	 * Returns a string representation of the scene object.
	 */
	public String toString() {
		String s = title + "(#" + sceneID + ")";
		return s;
	}
	
	/**
	 * @return the sceneID of this node.
	 */
	public int getSceneID() {
		return sceneID;
	}
	
	/**
	 * @return the parent node of this node
	 */
	public SceneNode getParento() {
		return parento;
	}
	
	/**
	 * Sets a new parent node (used in the moveScene method)
	 * @param parent the new parent node to switch it too
	 */
	public void setParento(SceneNode parent) {
		parento = parent;
	}
	
	/**
	 * @return the SceneNode stored as the left child.
	 */
	public SceneNode getLeft() {
		return left;
	}
	
	/**
	 * Sets a new node for left child.
	 * @param left the new node to be set to
	 */
	public void setLeft(SceneNode left) {
		this.left = left;
	}
	
	/**
	 * @return the SceneNode stored as the middle child
	 */
	public SceneNode getMiddle() {
		return middle;
	}
	
	/**
	 * Sets a new node for middle child.
	 * @param middle the new node to be set to
	 */
	public void setMiddle(SceneNode middle) {
		this.middle = middle;
	}
	
	/**
	 * @return the SceneNode stored as the right child
	 */
	public SceneNode getRight() {
		return right;
	}
	
	/**
	 * Sets a new node for the right child.
	 * @param right the new node to be set to
	 */
	public void setRight(SceneNode right) {
		this.right = right;
	}
	
	/**
	 * This method increments the numScenes variable by one.
	 */
	public static void incNumScenes() {
		numScenes = numScenes + 1;
	}
	
	/**
	 * Returns the current value for numScenes
	 * @return numScenes
	 */
	public static int getNumScenes() {
		return numScenes;
	}
	
	/**
	 * This method returns the sceneDescription varibale	
	 * @return sceneDescription
	 */
	public String getSceneDescription() {
		return sceneDescription;
	}
	
	/**
	 * THis method returns the title variable
	 * @return title
	 */
	public String getTitle() {
		return title;
	}	
}
