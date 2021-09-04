package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading database driver:");
			System.err.println(e);
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT *" + " FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
				// TODO: call findActorsByFilmId(filmId)
				// TODO: assign actors to film

			}
		} catch (SQLException e) {
			System.err.println("Database Error - film");
			System.err.println(e);

		}

		return film;

	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = " SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}

		} catch (SQLException e) {
			System.err.println("Database Error - actor");
			System.err.println(e);
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> filmActors = new ArrayList<Actor>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name\n"
					+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id\n"
					+ "JOIN film ON film.id = film_actor.film_id\n" + "WHERE film_id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				filmActors.add(actor);
			}

		} catch (SQLException e) {
			System.err.println("Database error - actors by film id");
			System.err.println(e);
		}
		
		if(filmActors.size() == 0) {
			return null;
		}
		
		return filmActors;
	}
	
	
	
	
	// Gonna try returning a string with the name of the film and actor info 
		// instead of List<Actor> 
//		@Override
//		public String findActorsByFilmId(int filmId) {
//			StringBuilder filmActors = new StringBuilder();
//
//			try {
//				Connection conn = DriverManager.getConnection(URL, user, pass);
//				String sql = "SELECT actor.id, actor.first_name, actor.last_name, film.title\n"
//						+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id\n"
//						+ "JOIN film ON film.id = film_actor.film_id\n" + "WHERE film_id = ?;";
//				PreparedStatement stmt = conn.prepareStatement(sql);
//				stmt.setInt(1, filmId);
//				ResultSet rs = stmt.executeQuery();
//				while (rs.next()) {
//					Actor actor = new Actor();
//					Film film = new Film();
//					film.setTitle(rs.getString("title"));
//					filmActors.append(" Title: " +film.getTitle());
//					actor.setId(rs.getInt("id"));
//					filmActors.append(" Actor Id: " + String.valueOf(actor.getId()));
//					actor.setFirstName(rs.getString("first_name"));
//					actor.setLastName(rs.getString("last_name"));
//					filmActors.append(" Name: " + actor.getFirstName() + " " + actor.getLastName());
//				}
//
//			} catch (SQLException e) {
//				System.err.println("Database error - actors by film id");
//				System.err.println(e);
//			}
//
//			return filmActors.toString();
//		}

}
