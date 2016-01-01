package jdbcStudy.service;

import java.io.Serializable;
import java.util.List;

import jdbcStudy.domain.Bok;

public interface BokService
{
    /**
     * 查找所有的book
     * @return　book的集合
     */
    List<Bok> findAllBook();
    /**
     * 通过id查找Book
     * @param id bookid
     * @return 改id对应的book
     */
    Bok getBookById(Serializable id);
    
    
}
