package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    /**
     * 查询
     * @throws IOException
     */
    @Test
    public  void run1() throws IOException {
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory =builder.build(inputStream);
        SqlSession session = factory.openSession();
        AccountDao dao = session.getMapper(AccountDao.class);
        List<Account> list = dao.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
        //关闭资源
        session.close();
        inputStream.close();
    }

    /**
     * 保存
     * @throws IOException
     */
    @Test
    public  void run2() throws IOException {
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory =builder.build(inputStream);
        SqlSession session = factory.openSession();
        AccountDao dao = session.getMapper(AccountDao.class);

        Account account = new Account();
        account.setMoney(30000000000.0);
        account.setName("胸大");
        dao.saveAccount(account);
        session.commit();
        //关闭资源
        session.close();
        inputStream.close();
    }
}
