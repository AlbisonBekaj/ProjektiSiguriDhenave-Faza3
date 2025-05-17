package repository;

import database.DBConnection;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import models.dto.CreateUserDto;
import models.dto.UpdateUserDto;

public class UserRepository extends BaseRepository<User, CreateUserDto, UpdateUserDto>{
   public UserRepository() {super("users");}

    public User fromResultSet(ResultSet result) throws SQLException {
        return User.getInstance(result);
    }

    public User create(CreateUserDto userDto){
        String query ="""
                INSERT INTO USERS(username, salt, salted_hash)
                 VALUES(?,?,?)
                """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,userDto.getUserName());
            pstm.setString(2,userDto.getSalt());
            pstm.setString(3,userDto.getSaltedHash());
            pstm.execute();
            ResultSet resultSet = pstm.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User update(UpdateUserDto userDto){
        StringBuilder query = new StringBuilder("UPDATE USERS SET ");
        ArrayList<Object> params = new ArrayList<>();

        if(userDto.getUserName() != null){
            query.append("USERNAME = ?, ");
            params.add(userDto.getUserName());
        }
        if(userDto.getSalt() != null){
            query.append("SALT = ?, ");
            params.add(userDto.getSalt());
        }
        if(userDto.getSaltedHash() != null){
            query.append("SALTED_HASH = ?, ");
            params.add(userDto.getSaltedHash());
        }
        if(params.isEmpty()){
            return getById(userDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE ID = ?");
        params.add(userDto.getId());

        try{
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for(int i = 0; i < params.size(); i++){
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if(updated == 1) {
                return this.getById(userDto.getId());
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByUsername(String username){
       String query= """
               SELECT * FROM users WHERE username = ?
               """;
        try{
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, username);
            ResultSet resultSet = pstm.executeQuery(); // Correct method for SELECT
            if (resultSet.next()) {
                return fromResultSet(resultSet); // Assuming this maps the row to a User object
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("salt"),
                        rs.getString("salted_hash")
                ); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
