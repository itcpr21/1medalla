
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class product {
    connt conn=new connt();
    
    
    public int editproduct(int id,String prodname,float price){
      int c=0;
      
       String sql="Update product set prod_name = ? ,prod_price = ? where Prod_ID = ?";
         
         try{
             
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(conn.url,conn.username,conn.password);
            PreparedStatement pstmt=(PreparedStatement)con.prepareStatement(sql);
            
            pstmt.setString(1, prodname);
            pstmt.setFloat(2, price);
            pstmt.setInt(3, id);
            
          c = pstmt.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
         }
         return c;
        
    }
    
    public int addproduct(String prodname,int prodquant,float price){
        int x=0;
        
        String sql="insert into product values(?,?,?,null);";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(conn.url,conn.username,conn.password);
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,prodname);
            pstmt.setInt(2,prodquant);
            pstmt.setFloat(3,price);
            
            x=pstmt.executeUpdate();
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }
              
       
       return x; 
    }
    
    public int deleltep(Object id){
        int c=0;
       
        String sql="Delete from product where prod_id=?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(conn.url,conn.username,conn.password);
            PreparedStatement pstmt=con.prepareStatement(sql);
            
            int newid=Integer.parseInt(id.toString());
            pstmt.setInt(1,newid);
            c=pstmt.executeUpdate();
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
      return c;  
    }
    
    
    
}
