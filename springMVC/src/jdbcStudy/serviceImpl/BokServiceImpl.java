package jdbcStudy.serviceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jdbcStudy.Dao.BookDaos;
import jdbcStudy.domain.Bok;
import jdbcStudy.service.BokService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BokServiceImpl implements BokService
{
    @Autowired
    private BookDaos bookDao;
    
    
    @Override
    public List<Bok> findAllBook()
    {
        List<Bok> bookList = new ArrayList<Bok>(1);
        bookList = bookDao.findAll();
        
        return bookList;
    }

    @Override
    public Bok getBookById(Serializable id)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
