package socket;

import Db.NotificationService;
import Db.entity.Notification;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ObserverTread extends Thread implements IObserver {

    private final Socket __socket;
    private final BufferedReader __reader;
    private final PrintWriter __writer;
    private final NotificationService __notificationService = new NotificationService();
    private boolean __disconnect;

    public ObserverTread(Socket socket) throws IOException {
        __socket = socket;
        __reader = new BufferedReader(new InputStreamReader(__socket.getInputStream()));
        __writer = new PrintWriter(__socket.getOutputStream(), true);
        start();

    }

    @Override
    public void run() {

        String msg = "";
        try {
            while (!isDisconnect()) {
                msg = __reader.readLine();

                if (msg != null) {
                    Gson _gson = new Gson();
                    Notification _notification = _gson.fromJson(msg, Notification.class);
                    __notificationService.insert(_notification);
                }
            }
        } catch (IOException e) {
            System.out.println("Клиент отключился");
            close();
        }
    }

    @Override
    public void update(String msg) {
        __writer.println(msg);
    }

    @Override
    public boolean isDisconnect() {
        return __disconnect;
    }

    @Override
    public void disconnect() {

    }

    private void close() {
        __disconnect = true;
        try {
            __writer.close();
            __reader.close();
            __socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
