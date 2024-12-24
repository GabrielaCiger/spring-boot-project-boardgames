package org.example.springboot_firstproject.data.access;

import org.example.springboot_firstproject.service.user.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySqlUserDao implements GameUserDao {

    @Autowired
    private DbConnection dbConnection;

    public MySqlUserDao() {}

    @Override
    public List<GameUser> getAllUsers() {
        List<GameUser> users = new ArrayList<>();
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            String request = "select * from users";
            ResultSet rs = con.createStatement().executeQuery(request);

            while (rs.next()) {
                GameUser user = new GameUser();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    dbConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public Optional<GameUser> getUserById(String id) {
        GameUser user = null;
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            String request = "select * from users where id = ?";
            PreparedStatement ps = con.prepareStatement(request);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new GameUser();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    dbConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<GameUser> getUserByUsername(String username) {
        GameUser user = null;
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            String request = "select * from users where username = ?";
            PreparedStatement ps = con.prepareStatement(request);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new GameUser();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    dbConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean createUser(GameUser user) {
        Connection con = null;
        GameUser newUser = null;
        try {
            con = dbConnection.getConnection();
            String request = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(request);
            ps = con.prepareStatement(request);
            ps.setString(1, user.getId().toString());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    dbConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(GameUser user) {
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            String request = "UPDATE users SET username = ?, password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(request);
            ps = con.prepareStatement(request);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getId().toString());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    dbConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        Connection con = null;
        try {
            con = dbConnection.getConnection();
            String request = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(request);
            ps = con.prepareStatement(request);
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    dbConnection.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
