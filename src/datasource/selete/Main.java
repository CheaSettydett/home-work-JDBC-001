package datasource.selete;

import datasource.JdbcImpl;
import model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JdbcImpl jdbc = new JdbcImpl();
        try(Connection conn = jdbc.dataSource().getConnection()){
            //1.Create SQL Statement
            String sqlselectSQL  = "SELECT * FROM topics";
            PreparedStatement statement = conn.prepareStatement(sqlselectSQL);
            //2. Execute SQl Statement object
            ResultSet resultSet = statement.executeQuery();
            //3. Process Result with ResultSet
            List<Topic> topics = new ArrayList<>();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Boolean status = resultSet.getBoolean("status");
                topics.add(new Topic(id ,name ,description,status));
            }
            topics.forEach(System.out::println);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
