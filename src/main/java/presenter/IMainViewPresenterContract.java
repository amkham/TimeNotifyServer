package presenter;

import Db.entity.Notification;

import java.io.IOException;
import java.util.List;

public interface IMainViewPresenterContract {

    void start(int port) throws IOException;
    void stop() throws IOException;
}
