package test;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		imp("TEST", 1);
	}

	private static void imp(String cadena, int reps) {

		for (int i = 0; i < reps; i++) {
			System.out.println(cadena);

		}

	}
}
