package kr.co.rudaks.migration;


import java.sql.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class RudaksToMongodb
{
    
    public static void main(String[] args) 
    {
        Connection sourceConn = null;
        PreparedStatement sourceStmt = null;
        ResultSet sourceRs = null;
        
        MongoClient mongoClient = null;
        
        try
        {
            
            mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
            DB db = mongoClient.getDB("rudaks");
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            sourceConn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=rudaks", "sa", "xkdla");
            
            sourceStmt = sourceConn.prepareStatement("SELECT * FROM kcount WHERE idx > ? AND idx <= ?");
            sourceStmt.setInt(1, Integer.parseInt(args[0]));
            sourceStmt.setInt(2, Integer.parseInt(args[1]));
            
            sourceRs = sourceStmt.executeQuery();
            
            while (sourceRs.next())
            {
                DBCollection userTable = db.getCollection("access_log");
                BasicDBObject doc = new BasicDBObject();
                doc.append("session_id", sourceRs.getString(1));
                doc.append("created_date", sourceRs.getString(2) + sourceRs.getString(3));
                doc.append("ip", sourceRs.getString(5));
                doc.append("referer", sourceRs.getString(6));
                doc.append("browser", sourceRs.getString(7));
                doc.append("os", sourceRs.getString(8));
                
                userTable.insert(doc);
            }
        }
        catch (Exception e)
        {            
            e.printStackTrace();
        }
        finally
        {
            if (sourceStmt != null) { try {sourceStmt.close(); } catch (Exception ex) {}}
            if (sourceConn != null) { try {sourceConn.close(); } catch (Exception ex) {}}
        }
    }
    
}
