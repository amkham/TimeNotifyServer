package Db.local;

import Db.entity.Notification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NotificationDao {

    public Notification getById(long id){
        return LocalDbConnection.getSession().openSession().get(Notification.class, id);
    }

    public void insert(Notification notification){
        Session _session = LocalDbConnection
                .getSession()
                .openSession();
        Transaction _transaction = _session.beginTransaction();
        _session.save(notification);
        _transaction.commit();
        _session.close();
    }

    public List<Notification> getAll()
    {
        Session _session = LocalDbConnection
                .getSession()
                .openSession();
        List<Notification> _result = _session.createQuery("FROM Notification", Notification.class).list();
        _session.close();
        return _result;
    }

    public void delete(Notification notification){
        Session _session = LocalDbConnection
                .getSession()
                .openSession();
        Transaction _transaction = _session.beginTransaction();
        _session.delete(notification);
        _transaction.commit();
        _session.close();
    }

    public void update(Notification notification){
        Session _session = LocalDbConnection
                .getSession()
                .openSession();
        Transaction _transaction = _session.beginTransaction();
        _session.update(notification);
        _transaction.commit();
        _session.close();
    }
}
