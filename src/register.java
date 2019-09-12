
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
public class register {
    connt con= new connt();
    
    
    public int reg(String firstname,String lastname,String username,String password){
    int x=0;
        
        
    String sql="INSERT INTO `reglog`.`register` (`fname`, `lname`, `uname`, `pass`) VALUES (?,?,?,md5(?));";
    
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=(Connection) DriverManager.getConnection(con.url,con.username,con.password);
        
        PreparedStatement pstmt=conn.prepareStatement(sql);
        
        pstmt.setString(1,firstname);
           pstmt.setString(2,lastname);
              pstmt.setString(3,username);
                 pstmt.setString(4,password);
                 
                x= pstmt.executeUpdate();
        
        
        
        
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
   return x;     
}
    
    public int confirmpasssword(String pass,String repass){
        int c=0;
        if (pass.equals(repass)){
            c=1;
            
        }else{
            c=0;
            
        }
        
        return c;
    }
    public int checkusername(String user){
        int x=0;
        
        String sql="select uname from register where uname=?";
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(con.url,con.username,con.password);
            PreparedStatement pstmt =(PreparedStatement) conn.prepareStatement(sql);
        
            pstmt.setString(1,user);
            
           ResultSet rs= pstmt.executeQuery();
           
           
           if(rs.next()){
               x=1;
               
           }else{
               x=0;
               
           }
            
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return x;
        
    }
    
    
    
}
