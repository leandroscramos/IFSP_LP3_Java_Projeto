package dao;

import connection.ConnectionFactory;
import model.LoginModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    LoginModel login = new LoginModel();

    public void searchLogin(LoginModel login){

        try{
            stmt = con.prepareStatement("select * from login where user like'%\"+login.getUser()+\"%'");
            stmt.execute();

            cntn.rs.first();
        }catch(SQLException e){
            System.out.print(e.getMessage());
        }
    }


}
