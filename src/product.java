
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    
    public int addQuantityProd(int id, int quantity){
         
         int y=0;
       
          String sql="Update product set prod_quant = prod_quant + ?  where prod_id = ?";
         
         try{
             
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(conn.url,conn.username,conn.password);
            PreparedStatement pstmt=(PreparedStatement)con.prepareStatement(sql);
            
            pstmt.setInt(1,quantity);
            pstmt.setInt(2,id);
            
            y=pstmt.executeUpdate();
            
            
         }
         
         catch (ClassNotFoundException ex) {
             Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return y;
     }
     
    
    
    public int editproduct2(int id,String prodname,Object price){
      int c=0;
      
       String sql="UPDATE product SET prod_name = ? , prod_price = ? WHERE  prod_id = ?;";
         
         try{
             
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(conn.url,conn.username,conn.password);
            PreparedStatement pstmt=(PreparedStatement)con.prepareStatement(sql);
            
            pstmt.setString(1, prodname);
            float newprice = Float.parseFloat(price.toString());
            pstmt.setFloat(2, newprice);
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
        
        String sql="insert into product values(null,?,?,?,0);";
        
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
    final void searchProd(String prodname, JTable table){
         
         String sql="Select * from product where prod_id like ? or prod_name like ?;";
         try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection(conn.url, conn.username,conn.password);
    PreparedStatement pstmt = con.prepareStatement(sql);
    
    pstmt.setString(1,"%"+ prodname +"%");
    pstmt.setString(2,"%"+ prodname +"%");
    
                                                             
    ResultSet rs=pstmt.executeQuery();
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
     while(rs.next()){
         
        model.addRow(new Object[]{rs.getString("Prod_ID"),rs.getString("Prod_name"),rs.getString("prod_quant"),rs.getString("prod_price")});
    }
    
    
}       catch (ClassNotFoundException ex) {   
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    
     }

    
    
    
}
