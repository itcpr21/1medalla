
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.DriverManager;
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
public class notifications {
    connt conn = new connt();
    
    public void checkLowProducts(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(conn.url, conn.username, conn.password);

            String sql = "select * from product";
            String status_sql = "UPDATE product SET status=? WHERE Prod_id=?;";
            Statement stmt = (Statement) con.prepareCall(sql);
            PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(status_sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                int quantitys = rs.getInt("prod_quant");
                int stat = rs.getInt("status");
                String products = rs.getString("prod_name");
                String id = rs.getString("prod_id");
                
                if(quantitys < 5 && stat != 1){
                    pstmt.setInt(1, 1);
                    pstmt.setString(2, id);
                    pstmt.executeUpdate();
                    displayNotification(products);
                }else if(quantitys >= 5 && stat == 1){
                    pstmt.setInt(1, 2);
                    pstmt.setString(2, id);
                    pstmt.executeUpdate();
                }
                
                
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(notifications.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(notifications.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(notifications.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    void displayNotification(String product) throws AWTException{
        
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("img/warning.png");
        
        TrayIcon trayIcon = new TrayIcon(image,"");
        trayIcon.setImageAutoSize(true);
        tray.add(trayIcon);
        trayIcon.displayMessage("LOW QUANTITY", product+" product low on quantity", TrayIcon.MessageType.WARNING);
    }
    
}
