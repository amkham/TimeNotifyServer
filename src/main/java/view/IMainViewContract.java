package view;

import Db.entity.Notification;

import java.util.List;

public interface IMainViewContract {
    void updateNotifyList(List<Notification> entities);
    void updateTimer(String time);
    void setLog(String msg);
    void close();
}
