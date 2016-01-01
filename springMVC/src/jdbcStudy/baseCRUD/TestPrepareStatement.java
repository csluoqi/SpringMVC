package jdbcStudy.baseCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jdbcStudy.db.factory.DB;
import jdbcStudy.domain.Bok;

/**
 * 编写基本的增删改查, 数据使用的BOOK
 * 
 * @author rocky
 *
 */
public class TestPrepareStatement
{
    private static Log log = LogFactory.getLog(TestPrepareStatement.class);
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    /**
     * 通过Id查找book对象
     * @param id id
     * @return book 对象
     */
    @SuppressWarnings("static-access")
    public Bok queryById(Long id)
    {
        Bok book = null;
        String sql = "select b.NAME,b.AUTHOR,b.INFO,b.PRICE,"
                + "b.PUBLISHDATE from BOOK as b where b.ID = ?;";
        conn = DB.getDbInstance().getConnection();
        boolean next = false;
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs == null)
            {
                return null;
            }
            next = rs.next();
            if (next)
            {
                book = new Bok();
                book.setId(id);
                book.setName(rs.getString("NAME"));
                book.setAuthor(rs.getString("AUTHOR"));
                book.setPrice(rs.getDouble("PRICE"));
                book.setInfo(rs.getString("INFO"));
                book.setPublishDate(rs.getDate("PUBLISHDATE"));
                log.info("添加成功");
                return book;
            }
        } catch (SQLException e)
        {
            log.info(e.getMessage());
        }
        finally
        {
        DB.getDbInstance().CloseConnection(conn, pstm, rs);    
        }
        return book;
    }

    public List<Bok> queryAll()
    {
        List<Bok> bookList = new ArrayList<Bok>(1);
        Bok book = null;
        String sql = "select * from BOOK;";
        
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            if (rs == null)
            {
                return null;               
            }
            
            Boolean next = rs.next();
            while (next)
            {
                book = new Bok();
                book.setId(rs.getLong("ID"));
                book.setName(rs.getString("NAME"));
                book.setAuthor(rs.getString("AUTHOR"));
                book.setPrice(rs.getDouble("PRICE"));
                book.setInfo(rs.getString("INFO"));
                book.setPublishDate(rs.getDate("PUBLISHDATE"));
                bookList.add(book);
                
                next = rs.next();
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        
        return bookList;
    }

    /**
     * 
     * @param book　book对象
     * @return　影响的行数
     */
    public int insert(Bok book)
    {
        int affectRow = 0;
        String sql = "insert into BOOK (NAME,AUTHOR,PRICE,INFO,PUBLISHDATE) "
                + "values(?,?,?,?,?);";
        /**
         * "insert into BOOK (NAME,AUTHOR,PRICE,INFO,PUBLISHDATE) "
                + "values("js","taobao","98","javascript","2015-12-12 12:!2:12");
         */
        conn = DB.getDbInstance().getConnection();
        
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setDouble(3, book.getPrice());
            pstm.setString(4, book.getInfo());
            pstm.setDate(5, new java.sql.Date(111111111111l));
            affectRow = pstm.executeUpdate();
            
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        return affectRow;
    }

    /**
     * 更新
     * @param book　book对象
     */
    public int update(Bok book)
    {
        int affectRow = 0;
        String sql = "update BOOK set NAME = ?,AUTHOR = ?,INFO=?,price=?,PUBLISHDATE=? where ID=?;";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setString(3, book.getInfo());
            pstm.setDouble(4, book.getPrice());
            pstm.setDate(5, new java.sql.Date(1212121212l));
            pstm.setLong(6, book.getId());
            affectRow = pstm.executeUpdate();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        return affectRow;
    }

    /**
     * 删除
     * @param id　BOOK的主键ID
     * @return 影响的行数
     */
    public int delete(Long id)
    {
        int affectRow = 0;
        String sql = "delete from BOOK where ID = ?";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, id);
            affectRow = pstm.executeUpdate();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        return affectRow;
    }
    public static void main(String[] args)
    {
        TestPrepareStatement t = new TestPrepareStatement();
        //System.out.println(t.queryById(1l));
        //System.out.println(t.queryAll());
        Bok book = new Bok();
        book.setName("js");
        book.setAuthor("taobao1");
        book.setPrice(123D);
        book.setInfo("javascript");
        book.setPublishDate(new Date());
        book.setId(4l);
        //System.out.println(t.insert(book));
        System.out.println(t.delete(book.getId()));
    }
}
