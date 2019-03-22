package persist;

import java.util.Scanner;

public class InitDatabase {
	public static void init(Scanner keyboard) {
		System.out.println("Which database (0=fake, 1=derby): ");
		int which = Integer.parseInt(keyboard.nextLine());
		if (which == 0) {
			DatabaseProvider.setInstance(new FakeDatabase());
		}else if(which == 1) {
			System.out.println("not implemented yet");
		}else {
			throw new IllegalArgumentException("Invalid choice: " + which);
		}
		
	}
}
