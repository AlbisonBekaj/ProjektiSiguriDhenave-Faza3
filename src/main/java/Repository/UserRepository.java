package Repository;

import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.dto.CreateUserDto;
import models.dto.UpdateUserDto;

public class UserRepository extends BaseRepository<User, CreateUserDto, UpdateUserDto>{
    UserRepository() {super("users");}

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

}
