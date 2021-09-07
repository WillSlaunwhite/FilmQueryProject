package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("What would you like to do?\n" + "1. Look up a film by its ID\n"
				+ "2. Look up a film by a search keyword\n" + "3. Exit the application");
		int choice = 0;

		try {
			choice = (int) input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.err.println("Please give a number.");
			System.err.println(e);
		}

		switch (choice) {
		case 1:
			System.out.println("What's the ID you're looking for?\n");
			int movieId = input.nextInt();
			Film film = db.findFilmById(movieId);
			try {
				System.out.println("Title: " + film.getTitle() + " Year: " + film.getReleaseYear() + " Language: "
						+ film.getLanguage() + " Rating: " + film.getRating() + " Description: " + film.getDescription()
						+ film.getActors() + "\n");
			} catch (NullPointerException e) {
				System.out.println("\n\nInvalid movie id, please try again\n\n");
			}

			startUserInterface(new Scanner(System.in));

		case 2:
			System.out.println("What're you looking for?");
			String searchTerm = input.nextLine();
			List<Film> movies = db.findFilmBySearch(searchTerm);

			if (movies.size() == 0) {
				System.out.println("Movie not found. please try again.,");
				startUserInterface(new Scanner(System.in));
			}

			for (Film f : movies) {
				System.out.println("Title: " + f.getTitle() + " Language: " + f.getLanguage() + " Year: "
						+ f.getReleaseYear() + " Rating: " + f.getRating() + " Description: " + f.getDescription()
						+ f.getActors() + "\n");
			}

			startUserInterface(new Scanner(System.in));

		case 3:
			System.out.println("Goodbye.");
			break;

		}

	}

}
