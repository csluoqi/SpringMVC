package jdbcStudy.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcStudy.db.factory.DB;
import jdbcStudy.domain.Usr;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao
{
    private static Logger log = Logger.getLogger(LoginDao.class);
    public boolean verifyUser(Usr user)
    {
     Connection conn = null;
     PreparedStatement pstm = null;
     ResultSet rs = null;
     
     conn = DB.getDbInstance().getConnection();
     String sql = "select count(*) as amount from tb_user as u where u.name = ? and u.password=?;";
     try
    {
       
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getName());
        pstm.setString(2, user.getPassword()); 
        rs = pstm.executeQuery();
        if (rs == null)
        {
            return false;
        }
        int amount = 0;
        boolean next = rs.next();
        if (next)
        {
            amount = rs.getInt("amount");
            if (amount != 0)
            {
                log.info("is valid User!");
                return true;
            }
        }
    } catch (SQLException e)
    {
        log.info(e.getMessage());
    }
     
     return false;   
    }
}
