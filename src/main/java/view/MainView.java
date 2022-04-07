package view;

import presenter.MainViewPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MainView extends JFrame {
    private JPanel __mainPanel;
    private JTextField __portTextField;
    private JButton __serverStartBtn;
    private JLabel __timerLabel;
    private JList __notifyListField;
    private JList __logListField;
    private JButton __serverStopBtn;


    private final MainViewPresenter __presenter = new MainViewPresenter(this);

    public MainView() throws HeadlessException {

        setContentPane(__mainPanel);
        setLocationRelativeTo(null);
        setSize(700,500);
        setTitle("Сервер");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initStartBtn();
        initStopBtn();

        setVisible(true);



    }


    public void updateTimer(String time){
        __timerLabel.setText(time);
    }

    public void updateLog(List<String> logs){
        fillListField(__logListField, logs);
    }

    public void updateNotifications(List<String> notifications){
        fillListField(__notifyListField, notifications);
    }


    void initStartBtn(){
        __serverStartBtn.addActionListener(e -> {
            try {
                String _port = __portTextField.getText();
                __presenter.start(Integer.parseInt(_port));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    void initStopBtn(){
        __serverStopBtn.addActionListener(e -> {
            try {
                __presenter.stop();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    void fillListField(JList jList, List<String> list){

        SwingUtilities.invokeLater(()-> {
            DefaultListModel<String> _listModel = new DefaultListModel<>();
            _listModel.addAll(list);
            jList.setModel(_listModel);
        });

    }



}
