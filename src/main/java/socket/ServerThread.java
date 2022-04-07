package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread implements ISubject {


    private static ServerSocket __serverSocket;

    private final List<IObserver> __observers = new ArrayList<>();

    private boolean __disconnect;

    public ServerThread(int port) throws IOException {
        __serverSocket = new ServerSocket(port);
        __disconnect = false;
    }


    @Override
    public void run() {

        try {
            while(!__serverSocket.isClosed()){
                try {
                    Socket _socket = __serverSocket.accept();
                    System.out.println("Подключился клиент");
                    registerObserver(new ObserverTread(_socket));
                } catch (IOException e) {
                    System.out.println("Соеденение разорвано...");
                }
            }
        }
        finally {
            try {
                __serverSocket.close();
            } catch (IOException e) {
                System.out.println("Соеденение разорвано...");
            }
        }

    }

    public List<IObserver> getObservers() {
        return __observers;
    }

    public void disconnect() throws IOException {
        __serverSocket.close();
    }

    public boolean isDisconnect() {
        return __disconnect;
    }

    @Override
    public void registerObserver(IObserver observer) {
        __observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        __observers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {

        if (__observers.size() > 0){
            for(IObserver o: __observers){
                if (o.isDisconnect()){
                    removeObserver(o);
                    continue;
                }
                o.update(msg);
            }
        }

    }
}
