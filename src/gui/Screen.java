package gui;

public abstract class Screen {

	String name;

	public Screen(String name) {
		this.name = name;
		initialize();

	}

	protected abstract void initialize(); // initializes the contents of the Screen (To be used for Main menu and
										  // Settings)

	public void dispose(Object[] contents) {
		for (Object obj : contents) {
			obj = null;
		}
	}

}
