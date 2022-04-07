package Db;

import Db.entity.Notification;
import Db.local.LocalDbConnection;
import Db.local.NotificationDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import socket.IObserver;
import socket.ISubject;

import java.util.ArrayList;
import java.util.List;

public class NotificationService  {

    public interface DataBaseChangeListener{
        void result(Notification notification);
        void result(List<Notification> notifications);
    }

    private DataBaseChangeListener __listener;

    private final NotificationDao __dao = new NotificationDao();

    public void setListener(DataBaseChangeListener listener) {
        __listener = listener;
    }

    public void getById(long id){
        new Thread(() ->{
            if(__listener != null)  __listener.result(__dao.getById(id));
        }).start();
    }

    public void insert(Notification notification){

        new Thread(() ->{
            __dao.insert(notification);
            if(__listener != null)  __listener.result(notification);
        }).start();;
    }

    public void getAll() {
        new Thread(() ->{
            if(__listener != null)  __listener.result(__dao.getAll());
        }).start();
    }

    public void delete(Notification notification){
        new Thread(() -> __dao.delete(notification)).start();
    }

    public void update(Notification notification) {
        new Thread(() -> __dao.update(notification)).start();
    }



}
