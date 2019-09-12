
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class loginc {
    
    connt con = new connt();
    
   public int log_in(String uname,String pass){
       int x=0;
       
       try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn=(Connection) DriverManager.getConnection(con.url,con.username,con.password);
            String sql="select * from register where uname=? and pass=md5(?);";
           PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
          
           pstmt.setString(1,uname);
           pstmt.setString(2,pass);
           
      ResultSet rs=pstmt.executeQuery();
      
      if(rs.next()){
          x=1;
          
      }else{
          x=0;
          
      }
           
           
           
           
           
       } catch (ClassNotFoundException ex) {
            Logger.getLogger(loginc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(loginc.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return x;
       
   }
    
    
    
}
