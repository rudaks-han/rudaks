package kr.co.rudaks.migration;

import java.sql.*;

public class RudaksToPostgresql
{
    
    public static void main(String[] args) 
    {
        Connection sourceConn = null;
        Connection targetConn = null;
        PreparedStatement sourceStmt = null;
        PreparedStatement targetStmt = null;
        ResultSet sourceRs = null;
        ResultSet targetRs = null;
        
        try
        {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("org.postgresql.Driver");
            
            sourceConn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=rudaks", "sa", "xkdla");
            targetConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rudaks", "postgres", "xkdla");
            
            sourceStmt = sourceConn.prepareStatement("SELECT * FROM t_bbs WHERE flag_delete = 'N' ORDER BY seq");
            
            sourceRs = sourceStmt.executeQuery();
            
            String insertSql = "INSERT INTO t_post(id, category, username, email, view_count, attach_count, ipaddress, delete_flag, title, old_seq, content, created_date, updated_date)";
            insertSql += " VALUES(nextval('seq_post'), ?,?,?,?,?,?,?,?,?,?,?,?)";
            
            String insertGuestbookCurrSql = "INSERT INTO t_guestbook(id, ref, username, email, ipaddress, delete_flag, password, comment, created_date, updated_date, old_ref)";
            insertGuestbookCurrSql += " VALUES(nextval('seq_guestbook'), currval('seq_guestbook'),?,?,?,?,?,?,?,?,?)";
            
            String insertGuestbookSql = "INSERT INTO t_guestbook(id, ref, username, email, ipaddress, delete_flag, password, comment, created_date, updated_date, old_ref)";
            insertGuestbookSql += " VALUES(nextval('seq_guestbook'), ?,?,?,?,?,?,?,?,?,?)";
            
            while (sourceRs.next())
            {
                int seq = sourceRs.getInt(1);
                String name = sourceRs.getString(2);
                String email = sourceRs.getString(3);
                String title = sourceRs.getString(4);
                String writeday = sourceRs.getString(5);
                String passwd = sourceRs.getString(6);
                String num = sourceRs.getString(7);
                String bbs = sourceRs.getString(8);
                int cnt = sourceRs.getInt(9);
                int ref = sourceRs.getInt(10);
                int step = sourceRs.getInt(11);
                String b_level = sourceRs.getString(12);
                int attachCount = sourceRs.getInt(13);                
                String filename1 = sourceRs.getString(14);
                String filename2 = sourceRs.getString(15);
                String filename3 = sourceRs.getString(16);
                String userIp = sourceRs.getString(17);
                String flag_delete = sourceRs.getString(18);
                String content = sourceRs.getString(19);
                String exp = sourceRs.getString(20);
                
                // category, username, email, view_count, attach_count, ipaddress, delete_flag, title, content, created_date, updated_date
                
                String category = getCategory(bbs);
                if ("".equals(category))
                {
                    //System.err.println("category is null ==> " + bbs + ", " + title);
                    continue;
                }
                
                //if (true) continue;
                
                if (!"루닥스".equals(name))
                {
                    System.err.println("name is not 루닥스 ==> " + bbs + ", " + title);
                    //continue;
                }
                
                if (email == null)
                {
                    System.err.println("email is null ==> " + bbs + ", " + title);
                    email = "noemail";
                }
                
                if ("freeboard".equals(category))
                { 
                    //content = content.replaceAll("<br>", "");
                    //content = StringUtil.replaceAll(content, "\r\n", "");
                    content = content.replaceAll("&nbsp;", " ");
                    content = removeHtmlTag(content);
                    
                    int index = 1;
                    if (step==0)
                    {
                        targetStmt = targetConn.prepareStatement(insertGuestbookCurrSql);
                        targetStmt.setString(1, name);
                        targetStmt.setString(2, email);
                        targetStmt.setString(3, userIp);
                        targetStmt.setString(4, "N");
                        targetStmt.setString(5, passwd);
                        targetStmt.setString(6, content);
                        targetStmt.setString(7, writeday);
                        targetStmt.setString(8, writeday);
                        targetStmt.setInt(9, ref);
                        
                        targetStmt.executeUpdate();
                    }
                    else
                    {
                        targetStmt = targetConn.prepareStatement(insertGuestbookSql);
                        
                        PreparedStatement stmt1 = targetConn.prepareStatement("select ref from t_guestbook where old_ref = ?");
                        stmt1.setInt(1,  ref);
                        ResultSet rs = stmt1.executeQuery();
                        if (rs.next())
                        {
                            int ref2 = rs.getInt(1);
                            targetStmt.setInt(1, ref2);
                            
                        }
                        rs = null;
                        stmt1.close();
                        
                        targetStmt.setString(2, name);
                        targetStmt.setString(3, email);
                        targetStmt.setString(4, userIp);
                        targetStmt.setString(5, "N");
                        targetStmt.setString(6, passwd);
                        targetStmt.setString(7, content);
                        targetStmt.setString(8, writeday);
                        targetStmt.setString(9, writeday);
                        targetStmt.setInt(10, ref);
                        
                        targetStmt.executeUpdate();
                        targetStmt.close();
                    }
                   
                    
                }
                else
                {
                    if (!"루닥스".equals(name))
                        continue;
                    
                    targetStmt = targetConn.prepareStatement(insertSql);
                    
                    targetStmt.setString(1, category);
                    targetStmt.setString(2, name);
                    targetStmt.setString(3, email);
                    targetStmt.setInt(4, cnt);
                    targetStmt.setInt(5, attachCount);
                    targetStmt.setString(6, userIp);
                    targetStmt.setString(7, flag_delete);
                    targetStmt.setString(8, title);
                    targetStmt.setInt(9, seq);
                    targetStmt.setString(10, content);
                    targetStmt.setString(11, writeday);
                    targetStmt.setString(12, writeday);
                    targetStmt.executeUpdate();
                    targetStmt.close();
                }
            }
        }
        catch (Exception e)
        {            
            e.printStackTrace();
        }
        finally
        {
            if (sourceStmt != null) { try {sourceStmt.close(); } catch (Exception ex) {}}
            if (targetStmt != null) { try {targetStmt.close(); } catch (Exception ex) {}}
            if (sourceConn != null) { try {sourceConn.close(); } catch (Exception ex) {}}
            if (targetConn != null) { try {targetConn.close(); } catch (Exception ex) {}}
        }
    }
    
    public static String getCategory(String bbs)
    {
        String category = "";
        if ("asp".equals(bbs))
            category = "program_etc";
        else if ("book".equals(bbs))
            category = "etc";
        else if ("db".equals(bbs))
            category = "db";
        else if ("favorite".equals(bbs))
            category = "etc";
        else if ("freeboard".equals(bbs))
            category = "freeboard";
        else if ("humor".equals(bbs))
            category = "etc";
        else if ("java".equals(bbs))
            category = "java";
        else if ("js".equals(bbs))
            category = "html";
        else if ("jsp".equals(bbs))
            category = "jsp";
        else if ("linux".equals(bbs))
            category = "os";
        else if ("mail".equals(bbs)) // 제거
            category = "";
        else if ("mypds".equals(bbs)) // 제거
            category = "";
        else if ("network".equals(bbs)) // 제거
            category = "";
        else if ("news".equals(bbs)) // 제거
            category = "";
        else if ("pds".equals(bbs)) // 제거
            category = "";
        else if ("secure".equals(bbs)) // 제거
            category = "";
        else if ("spectra".equals(bbs)) // 제거
            category = "";
        else if ("think".equals(bbs))
            category = "etc";
        else if ("tip".equals(bbs))
            category = "etc";
        else if ("tool".equals(bbs))
            category = "tool";
        else if ("win2k".equals(bbs))
            category = "os";
        else if ("xml".equals(bbs))
            category = "program_etc";
        
        return category;
    }
    
    public static String removeHtmlTag(String str) throws Exception
    {
        try
        {
            if (str == null || str.length() == 0)
                return "";
            else
                return str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
        }
        catch(java.lang.NoSuchMethodError e)
        {
            throw new Exception("StringUtil.removeHtmlTag Method Error!! (Support JVM 1.4.x)");
        }
    }
}
