package main;

import controller.Controller;
import model.Model;

public class Application {
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
	}
}
