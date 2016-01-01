package jdbcStudy.urlSet;

/**
 * 1.跳转页面的路径,比如findAll页面为jdbcStudy_home.jsp,那么这个可以用一个常量表示
 * 这样在多个地方那个引用时就避免了出现拼写错误引起的bug
 * 2.变量的命名规则是每个单词全部大写,单词与单词之间用下划线链接
 * 3.在这次搭建框架时由于视图解析器用的有设置所以可以这样简写,如果没有设置则写全路径
 * @author rocky
 */
public class PagePath
{
    /**book的主页**/
    public  static final String JDBCSTUDY_BOOK = "jdbcStudy_book";
}
