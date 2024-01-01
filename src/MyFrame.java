//Michelle Banwag
import java.awt.*;
import javax.swing.*;
import java.awt.Component;
public class MyFrame extends JFrame {
    private int W, H;
    private Component lblID, lblName,lblType,lblGender, lblColor,lblBreed,lblPrice;
    private JTextField txtID,txtName,cboType,cboGender,txtColor,cboBreed,txtPrice;
    private Font f;


    public MyFrame() {
        super();
        H = 800;
        W = 500;
        setWindowSize(W, H);
    }


    public MyFrame(int width, int height) {
        super();
        setWindowSize(width, height);
    }


    public MyFrame(String title, int width, int height) {
        super(title);
        setWindowSize(width, height);
    }


    public MyFrame(String title, int width, int height, boolean visible) {
        super(title);
        setWindowSize(width,height);
        setVisible(visible);
    }


    public void setWindowSize(int width, int height) {
        H = height;
        W = width;
        setSize(width, height);
    }


    public void setMyFrame(String title, int width, int height) {
        setTitle(title);
        setWindowSize(width, height);
        setVisible(isVisible());
        setResizable(true);
        setLocationRelativeTo(null);
    }


    public void setMyFrame(String title, int width, int height, boolean visible) {
        setMyFrame(title, width, height);
        setWindowSize(width,height);
        setVisible(visible);
    }


    public void setMyFrame(String title, int width, int height, boolean visible, int close_operation) {
        setMyFrame(title,width,height,visible);
        setDefaultCloseOperation(close_operation);


    }
    public void setMyFrame(String title, int width, int height, boolean visible, int close_operation, boolean resize){
        setMyFrame(title,width,height,visible,close_operation,resize);
        setResizable(resize);
    }
    public void setBackgroundColor(int red, int green, int blue, int opacity){
        getContentPane().setBackground(new Color(red,green,blue,opacity));
    }
    public JPanel setBackgroundImage(String file){
        JPanel panelBG=new JPanel();
        JLabel img=new JLabel(new ImageIcon(file));
        panelBG.add(img);
        return panelBG;
    }
}
