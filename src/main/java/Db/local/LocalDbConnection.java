package Db.local;

import Db.entity.Notification;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class LocalDbConnection {

    private static SessionFactory __sessionFactory;

    private LocalDbConnection(){};

    public static SessionFactory getSession(){

        if (__sessionFactory == null){

            synchronized (SessionFactory.class){

                if (__sessionFactory == null){
                        Configuration _configuration = new Configuration().configure();
                        _configuration.addAnnotatedClass(Notification.class);
                        StandardServiceRegistryBuilder _builder = new StandardServiceRegistryBuilder()
                                .applySettings(_configuration.getProperties());
                        __sessionFactory = _configuration.buildSessionFactory(_builder.build());
                }
            }
        }
        return __sessionFactory;
    }

}
