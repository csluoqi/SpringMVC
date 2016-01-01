package jdbcStudy.Dao;

import java.io.Serializable;
import java.util.List;

import jdbcStudy.domain.Usr;

/**
 * Data Access Object
 * @author rocky
 *
 */
public interface UserDaos
{
    //不好的方案是传入多个变量，原因可能有以下几点，１拓展性不好，不容易适应后期需求的改变
    /**
     * 
     * 存储对象的方法
     * @param user
     * @return 成功插入后返回自动生成主键的ID
     */
    Serializable save(Usr user);
    void update(Usr user);
    /**
     * 根据主键ID删除记录
     * @param id　主键ID
     * 
     */
    void delete(Serializable id);
    
    Usr get(Serializable id);
    /**
     * 查找所有的用户
     * @return　集合
     */
    List<Usr> findAll();
    
    Usr findUserByNameAndPassword(String name,String password);
}




