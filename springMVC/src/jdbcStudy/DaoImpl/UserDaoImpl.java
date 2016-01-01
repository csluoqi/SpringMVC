package jdbcStudy.DaoImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbcStudy.Dao.UserDaos;
import jdbcStudy.db.factory.DB;
import jdbcStudy.domain.Usr;

public class UserDaoImpl implements UserDaos
{
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;

    @Override
    public Serializable save(Usr user)
    {
        Long id = 0L;
        String sql = "insert into tb_user(name,password) values(?,?);";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPassword());
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            if (rs == null)
            {
                return id;
            }
            boolean next = rs.next();
            if (next)
            {
                id = rs.getLong(1);
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
        return id;
    }

    @Override
    public void update(Usr user)
    {
        conn = DB.getDbInstance().getConnection();
        String sql = "update tb_user set name = ?, password=? where id=?";
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getPassword());
            pstm.setLong(3, (long)user.getId());
            
            pstm.executeUpdate();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        
    }

    @Override
    public void delete(Serializable id)
    {
        conn = DB.getDbInstance().getConnection();
        String sql = "delete from tb_user where id=?";
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, (long)id);
            pstm.executeUpdate();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            DB.getDbInstance().CloseConnection(conn, pstm, rs);
        }
        
    }

    @Override
    public Usr get(Serializable id)
    {
        Usr user = null;
        String sql = "select u.id, u.name,u.password from tb_user as u where u.id=?;";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, (Long)id);
            rs = pstm.executeQuery();
            if (rs == null)
            {
                return null;
            }
            boolean next = rs.next();
            if (next)
            {
                user = new Usr();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
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
        return user;
    
    }

    @Override
    public List<Usr> findAll()
    {
        List<Usr> userList = new ArrayList<Usr>(1);
        
        Usr user = null;
        String sql = "select u.name,u.password from tb_user as u;";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs == null)
            {
                return null;
            }
            boolean next = rs.next();
            while (next)
            {
                user = new Usr();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
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
        return userList;
    
    }

    @Override
    public Usr findUserByNameAndPassword(String name, String password)
    {
        Usr user = null;
        String sql = "select u.id, u.name,u.password from tb_user as u where u.name =? and u.password = ?;";
        conn = DB.getDbInstance().getConnection();
        try
        {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if (rs == null)
            {
                return null;
            }
            boolean next = rs.next();
            if (next)
            {
                user = new Usr();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
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
        return user;
    }

    public static void main(String[] args)
    {
       UserDaos userDaoImpl = new UserDaoImpl();
       Usr user = new Usr();
       user.setId(7L);
       user.setName("qwerty");
       user.setPassword("23");
       //userDaoImpl.delete(4l);
       //System.out.println(userDaoImpl.findUserByNameAndPassword("lq", "123"));
       //System.out.println(userDaoImpl.findAll());
       //System.out.println(userDaoImpl.get(1l));
       //System.out.println(userDaoImpl.save(user));
       userDaoImpl.update(user);
    }
}
