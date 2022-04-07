package presenter;

import Db.NotificationService;
import Db.entity.Notification;
import com.google.gson.Gson;
import responce.Response;
import socket.ServerThread;
import utils.SimpleTimer;
import view.MainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainViewPresenter implements IMainViewPresenterContract {

    private final MainView __view;
    private final List<String> __logs = new ArrayList<>();
    private final NotificationService __notificationService = new NotificationService();
    private SimpleTimer __simpleTimer;
    private ServerThread __serverThread;
    private List<Notification> __notifications = new ArrayList<>();

    private Gson __gson = new Gson();

    public MainViewPresenter(MainView view) {
        __view = view;
    }

    @Override
    public void start(int port) throws IOException {

        serverStart(port);
        initTimer();
        initService();
    }


    @Override
    public void stop() throws IOException {

        __simpleTimer.end();
        __serverThread.disconnect();
        addLog("Сервер остановлен");

    }

    void initService() {
        __notificationService.setListener(new NotificationService.DataBaseChangeListener() {
            @Override
            public void result(Notification notification) {
            }

            @Override
            public void result(List<Notification> notifications) {
                Notification[] _result = notifications.toArray(new Notification[0]);
                Response _response = new Response(_result, __simpleTimer.getTime());
                __serverThread.notifyObservers(__gson.toJson(_response));
            }
        });
    }

    void initTimer() {
        __simpleTimer = new SimpleTimer();
        __simpleTimer.setTimerTick(time -> {
            __notificationService.getAll();
            __view.updateTimer(time.toString());
        });
        __simpleTimer.start();
    }


    private void serverStart(int port) throws IOException {
        __serverThread = new ServerThread(port);
        __serverThread.start();
        addLog("Сервер запущен на порту " + port);
    }

    private void addLog(String log) {
        __logs.add(log);
        __view.updateLog(__logs);
    }

}
