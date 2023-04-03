package datasource.delete;

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
        topic.setStatus(true);
        JdbcImpl jdbc = new JdbcImpl();
        try(Connection conn = jdbc.dataSource().getConnection()){
            String deleteSql =   "DELETE FROM topics where id = ? ";
            PreparedStatement statement = conn.prepareStatement(deleteSql);
            statement.setInt(1,topic.getId());
            int updateRow =statement.executeUpdate();
            System.out.println(updateRow);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
