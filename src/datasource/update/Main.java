package datasource.update;

import datasource.JdbcImpl;
import model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Topic topic = new Topic();
        System.out.println("Enter ID: ");
        topic.setId(scanner.nextInt());
        System.out.println("Enter Name: ");
        topic.setName(scanner.next());
        System.out.println("Enter Description: ");
        topic.setDescription(scanner.next());
        topic.setStatus(true);
        JdbcImpl jdbc = new JdbcImpl();
        try(Connection conn = jdbc.dataSource().getConnection()){
            String updateSql =   "UPDATE topics SET name = ?,description = ? WHERE id =?";
            PreparedStatement statement = conn.prepareStatement(updateSql);
            statement.setInt(3,topic.getId());
            statement.setString(2, topic.getName() );
            statement.setString(1, topic.getDescription());
//            statement.setBoolean(4, topic.getStatus());
            int updateRow =statement.executeUpdate();
            System.out.println(updateRow);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
