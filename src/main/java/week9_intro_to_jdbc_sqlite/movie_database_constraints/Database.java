package week9_intro_to_jdbc_sqlite.movie_database_constraints;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clara on 10/2/19.
 */
public class Database {
    
    private String databasePath;
    
    Database(String databasePath) {
        this.databasePath = databasePath;
    
        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()  ) {
        
            String createTableSQL = "CREATE TABLE IF NOT EXISTS movies " +
                    "(name TEXT UNIQUE NOT NULL CHECK(length(trim(name)) > 0), " +       // Null and blank names forbidden
                    "stars INTEGER CHECK(stars >= 0 AND stars <= 5), " +               // stars must be between 0 and 5
                    "watched BOOLEAN )";
            
            statement.execute(createTableSQL);
            
        } catch (SQLException sqle) {
            throw new RuntimeException("Error creating movie DB table", sqle);
        }
    }
    
    
    public void addNewMovie(Movie movie) throws MovieException {
        
        try (Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movies values (trim(?), ?, ?)")  ) {
            
            preparedStatement.setString(1, movie.name);
            preparedStatement.setInt(2, movie.stars);
            preparedStatement.setBoolean(3, movie.watched);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException sqle) {
            throw new MovieException("Error adding movie " + movie, sqle);
        }
    }
    
    
    public List<Movie> getAllMovies() {
        
        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()  ) {
            
            ResultSet movieResults = statement.executeQuery("SELECT * FROM movies");
            
            List<Movie> movies = new ArrayList<>();
            
            while (movieResults.next()) {
                String name = movieResults.getString("name");
                int stars = movieResults.getInt("stars");
                boolean watched = movieResults.getBoolean("watched");
                
                Movie movie = new Movie(name, stars, watched);
                movies.add(movie);
            }
            
            return movies;
            
        } catch (SQLException sqle) {
            throw new RuntimeException("Error fetching all movies", sqle);
        }
    }
    
    
    public List<Movie> getAllMoviesByWatched(boolean watchedStatus) {
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies WHERE watched = ?")  ) {
        
            preparedStatement.setBoolean(1, watchedStatus);
            ResultSet movieResults = preparedStatement.executeQuery();
        
            List<Movie> movies = new ArrayList<>();
        
            while (movieResults.next()) {
                String name = movieResults.getString("name");
                int stars = movieResults.getInt("stars");
                boolean watched = movieResults.getBoolean("watched");
                Movie movie = new Movie(name, stars, watched);
                movies.add(movie);
            }
        
            return movies;
        
        } catch (SQLException sqle) {
            throw new RuntimeException("Error getting all watched movies for status " + watchedStatus, sqle);
        }
    }
    
    
    public void updateMovie(Movie movie) throws MovieException {
    
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE movies SET stars = ?, watched = ? WHERE name = ?")) {
    
            preparedStatement.setInt(1, movie.stars);
            preparedStatement.setBoolean(2, movie.watched);
            preparedStatement.setString(3, movie.name);
    
            preparedStatement.executeUpdate();
            
        } catch (SQLException sqle) {
            throw new MovieException("Error updating movie DB for movie " + movie, sqle);
        }
    }
    
    
    public void deleteMovie(Movie movie) throws MovieException {
    
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movies WHERE name = ?")) {
        
            preparedStatement.setString(1, movie.name);
        
            preparedStatement.executeUpdate();
        
        } catch (SQLException sqle) {
            throw new MovieException("Error deleting movie from table for movie " + movie, sqle);
        }
        
    }

    class MovieException extends Exception {
        MovieException(String msg, Exception cause) {
            super(msg, cause);
        }
    }
    
}
