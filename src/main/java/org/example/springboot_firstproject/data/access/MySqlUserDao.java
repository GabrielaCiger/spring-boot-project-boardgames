package org.example.springboot_firstproject.data.access;

import org.example.springboot_firstproject.service.user.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySqlUserDao implements GameUserDao {

    @Autowired
    private DbConnection dbConnection;

    public MySqlUserDao() {}

    // Helper method to simplify connection handling
    private Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }

    // Helper method to execute updates (INSERT, UPDATE, DELETE)
    private boolean executeUpdate(String query, String... params) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                ps.setString(i + 1, params[i]);
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to map a ResultSet to a GameUser object
    private GameUser mapResultSetToGameUser(ResultSet rs) throws SQLException {
        GameUser user = new GameUser();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    /* * CRUD - SQL REQUESTS */

    @Override
    public List<GameUser> getAllUsers() {
        String query = "SELECT * FROM users";
        List<GameUser> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                GameUser user = mapResultSetToGameUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<GameUser> getUserById(String id) {
        String query = "SELECT * FROM users WHERE userId = ?";
        return getUserBy(query, id);
    }

    @Override
    public Optional<GameUser> getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        return getUserBy(query, username);
    }

    private Optional<GameUser> getUserBy(String query, String param) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToGameUser(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean createUser(GameUser user) {
        String query = "INSERT INTO users (userId, username, password) VALUES (?, ?, ?)";
        return executeUpdate(query, user.getUserId().toString(), user.getUsername(), user.getPassword());
    }

    @Override
    public boolean updateUser(GameUser user) {
        String query = "UPDATE users SET username = ?, password = ? WHERE userId = ?";
        return executeUpdate(query, user.getUsername(), user.getPassword(), user.getUserId().toString());
    }

    @Override
    public boolean deleteUser(String id) {
        String query = "DELETE FROM users WHERE userId = ?";
        return executeUpdate(query, id);
    }
}
