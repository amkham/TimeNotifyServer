package socket;

import Db.entity.Notification;

import java.util.List;

public interface IObserver {
    void update(String msg);
    boolean isDisconnect();
    void disconnect();
}
