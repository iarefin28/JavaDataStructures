/**
 * Name: Ishan Arefin 
 * Student ID: 112937865
 * Recitation: R10
 * 
 * This class represents AdverntureDesigner, where you can make your own game and play it
 */
import java.awt.Cursor;
import java.util.Scanner;

public class AdventureDesigner {
	static SceneTree tree; 
	
	/**
	 * Default construtor for AdventureDesigner, initializes tree 
	 */
	public AdventureDesigner() {
		tree = null;
	}
	
	/**
	 * Main method is where all menu options are located
	 * @param args
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean quit = false; 
		System.out.print("Creating a story...\n");
		System.out.print("Please enter a title: ");
		String title = s.nextLine();
		System.out.print("Please enter a scene: ");
		String scene1 = s.nextLine();
		System.out.println("Scene #1 added.");
		SceneNode first = new SceneNode(title, scene1, null, 1);
		tree = new SceneTree(first);
		SceneNode.incNumScenes();

		while(!quit) {
			System.out.println("Menu:\nA) Add Scene\n"
					+ "R) Remove Scene\nS) Show current scene\nP) Print Adventure Tree\n"
					+ "B) Go Back A Scene\nF) Go Forward A Scene\n"
					+ "G) Play Game\nN) Print Path To Cursor\nM) Move Scene\nQ) Quit");
			System.out.println("Please select an option: ");
			String input = s.nextLine();
			input = input.toUpperCase();
			
			if(input.equals("A")) {
				System.out.print("Please enter a title: ");
				String nextTitle = s.nextLine();
				System.out.print("Please enter a scene: ");
				String nextSceneDescription = s.nextLine();
				SceneNode.incNumScenes();
				try {
					tree.addNewNode(nextTitle, nextSceneDescription, tree.getCursor(), SceneNode.getNumScenes());
					System.out.println("Scene #" + SceneNode.getNumScenes() + " added.");
				} catch (FullSceneException e) {
					System.out.println("Can't add anymore scenes to this node.");
				}
			}
			
			
			if(input.equals("R")) {
				System.out.println("Please enter an option: ");
				String toRemove = s.nextLine();
				if(toRemove.equals("A") || toRemove.equals("B") || toRemove.equals("C")) {
					try {
						tree.removeScene(toRemove);
					} catch (NoSuchNodeException e) {
						System.out.println("There is no node to remove.");
					}
				}
				else {
					System.out.println("Nothing was removed, please enter a valid selection (A or B or C)");
				}
			}
			
			
			if(input.equals("S")) {
				tree.getCursor().displayFullScene();
			}
			
			if(input.equals("P")) {
				System.out.println(tree.toString());
			}
			
			if(input.equals("B")) {
				try {
					tree.moveCursorBackwards();
				} catch (NoSuchNodeException e) {
					System.out.println("Can't move any farther back.");
				}
			}
			
			
			if(input.equals("F")) {
				System.out.println("Which option do you wish to go to: ");
				String op = s.nextLine();
				if(op.equals("A") || op.equals("B") || op.equals("C")) {
					try {
						tree.moveCursorForward(op);
					} catch (NoSuchNodeException e) {
						System.out.println("That option does not exist.");
					}
				}
				else {
					System.out.println("Nothing was removed, please enter a valid selection (A or B or C)");
				}
			}
			if(input.equals("G")) {
				tree.setCursor(first);
				playGame();
			}
			
			if(input.equals("N")) {
				System.out.println(tree.getPathFromRoot());
			}
			
			if(input.equals("M")) {
				System.out.println("Move current scene to: ");
				int moveTo = s.nextInt();
				try {
					tree.moveScene(moveTo);
				} catch (NoSuchNodeException e) {
					System.out.println("There is no scene " + moveTo);
				} catch (FullSceneException e) {
					System.out.println("There is not enough space to add this scene.");
				}
			}
			
			if(input.equals("Q")) {
				quit = true;
				System.out.println("Program terminating....");
			}
			
		}
	}
	
	/**
	 * This method is called when the game is played. 
	 */
	public static void playGame() {
		System.out.println("Now beginning game...");
		System.out.println();
		Scanner in = new Scanner(System.in);
		
		while(!tree.getCursor().isEnding()) {
			tree.getCursor().displayScene();
			System.out.println();
			System.out.println("Please enter an option: ");
			String op = in.nextLine();
			try {
				tree.moveCursorForward(op);
			} catch (NoSuchNodeException e) {
				System.out.println("Please enter a valid option.");
			}
		}
		
		System.out.println("The end\n");
		System.out.println("Returning back to creation mode...");
	}
}