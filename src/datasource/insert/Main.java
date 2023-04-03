package datasource.insert;

import datasource.JdbcImpl;
import model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static JdbcImpl jdbc;
    private static Scanner scanner;

    public static void main(String[] args) {
        jdbc = new JdbcImpl();
        scanner = new Scanner(System.in);


        Topic topic = new Topic();
        System.out.println("Enter ID :");
        topic.setId(scanner.nextInt());
        System.out.println("Enter Name: ");
        topic.setName(scanner.next());
        System.out.println("Enter Description: ");
        topic.setDescription(scanner.next());
        topic.setStatus(true);
        insertTopics(topic);
    }
        private static void insertTopics(Topic topic){
            try(Connection conn = jdbc.dataSource().getConnection()){
                String insertSql =   "INSERT INTO topics (id,name , description,status)" + "VALUES(?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(insertSql);
                statement.setInt(1,topic.getId());
                statement.setString(2, topic.getName() );
                statement.setString(3, topic.getDescription());
                statement.setBoolean(4, topic.getStatus());

                int count =statement.executeUpdate();
                System.out.println(count);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
}
