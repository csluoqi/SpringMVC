package jdbcStudy.service;

import jdbcStudy.domain.Usr;

public interface LoginService
{
    boolean verifyUser(Usr user);
    
}
