package jdbcStudy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbcStudy.Dao.LoginDao;
import jdbcStudy.domain.Usr;
import jdbcStudy.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private LoginDao loginDao;
    @Override
    public boolean verifyUser(Usr user)
    {
        return loginDao.verifyUser(user);
    }

}
