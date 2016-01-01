package jdbcStudy.DaoImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import jdbcStudy.Dao.BookDaos;
import jdbcStudy.db.factory.DB;
import jdbcStudy.domain.Bok;

@Repository
public class BookDaoImpl implements BookDaos
{
    private static Logger log = Logger.getLogger(BookDaoImpl.class);
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public Serializable saveBook(Bok book)
    {
     
        int afffectRow = 0;
        String sql = "insert into BOOK(name,author,price,info,amount) "
                + "values(?,?,?,?,?);";
        //values('qwe','123','123.0','qwewertr','123');
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setDouble(3, book.getPrice());
            pstm.setString(4, book.getInfo());
            pstm.setInt(5, book.getAmount());
            afffectRow = pstm.executeUpdate(); 
        } catch (SQLException e)
        {
            log.error(e.getMessage());
        }
        finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        
        return afffectRow;
    }

    @Override
    public Serializable updateBook(Bok book)
    {
        /**
         * private Long id;
    private String name;
    private String author;
    private Double price;
    private String info;
    private Date publishDate;
    private Integer amount;
         */
        int affectRow = 0;
        String sql = "update BOOK set name=?,author=?,price=?,info=?,amount=? where id=?";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setDouble(3, book.getPrice());
            pstm.setString(4, book.getInfo());
            pstm.setInt(5, book.getAmount());
            pstm.setLong(6, book.getId());
            affectRow = pstm.executeUpdate();
        } catch (SQLException e)
        {
            log.error(e.getMessage());
        }
        finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        
        return affectRow;
    }

    @Override
    public Serializable deleteBook(Serializable id)
    {
        int affectRow = 0;
        String sql = "delete from BOOK where id=?";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1,(Long)id);
            affectRow = pstm.executeUpdate();
        } catch (SQLException e)
        {
            log.error(e.getMessage());
        }
        finally{
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        return affectRow;
    }

    @Override
    public List<Bok> findAll()
    {
        List<Bok> bookList = new ArrayList<Bok>(1);
        Bok book = null;
        String sql = "select * from BOOK;";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs == null)
            {
                return bookList;
            }
            boolean next = rs.next();
            while (next)
            {
                /**
                 * private Long id;
            private String name;
            private String author;
            private Double price;
            private String info;
            private Date publishDate;
            private Integer amount;
                 */
                book = new Bok();
                book.setId(rs.getLong("ID"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setInfo(rs.getString("info"));
                book.setAmount(rs.getInt("amount"));
                
                bookList.add(book);
                next = rs.next();
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        return bookList;
    }

    @Override
    public Bok findBookById(Serializable id)
    {
        Bok book = new Bok();
        String sql = "select * from BOOK where id=?;";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs == null)
            {
                return book;
            }
            boolean next = rs.next();
            if (next)
            {
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setInfo(rs.getString("info"));
                book.setAmount(rs.getInt("amount"));
            }
        } catch (SQLException e)
        {
            log.error(e.getMessage());
        }
        DB.getDbInstance().CloseConnection(conn, pstm, rs);
        return book;
    }

}
