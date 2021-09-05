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
//    app.test();
    app.launch();
  }

//  private void test() {
//	int rand = (int)(Math.random() * 100);
//    Film film = db.findFilmById(rand);
//    Actor actor = db.findActorById(rand);
//	List<Actor> actors = db.findActorsByFilmId(rand);
//    
//    System.out.println(film);
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	System.out.println("What would you like to do? "
			+ "1. Look up a film by its ID\n" + "2. Look up a film by a search keyword\n"
			+ "3. Exit the application");
	int choice=0;
	
	try { 
		choice = (int)input.nextInt();
		input.nextLine();
	}
	catch(InputMismatchException e) {
		System.err.println("Please give a number");
		System.err.println(e);
	}

	
	switch(choice) {
    case 1:
    	System.out.println("What's the ID you're looking for?\n");
    	int movieId = input.nextInt();
    	input.nextLine();
    	
    	Film film = db.findFilmById(movieId);
    	System.out.println("Title: " + film.getTitle() + " Year: " + film.getReleaseYear() + " Rating: " + film.getRating() +
    			" Description: " + film.getDescription());
    	
    	
    case 2:
    	System.out.println("What're you looking for?");
    	String searchTerm = input.nextLine();
    	System.out.println("term " + searchTerm);
    	List<Film> movies = db.findFilmBySearch(searchTerm);
    	
    	for(Film f : movies) {
    		System.out.println("Title: " + f.getTitle() + " Language: " + f.getLanguage() + " Year: " + f.getReleaseYear() + " Rating: " + f.getRating() +
    			" Description: " + f.getDescription() + f.getActors());
    	}
    	
    case 3:
    	break;
    	
    	
    }
    
  }

}
