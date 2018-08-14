import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Aug 14 16:59:56 BST 2018
 */



/**
 * @author Aquila Group Holdings Ltd
 */
public class ThisIsRubbish extends JFrame {
    public ThisIsRubbish() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu3 = new JMenu();
        menu2 = new JMenu();
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "center:default:grow, $lcgap, default:grow",
            "2*($lgap, default:grow), $lgap"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- menuItem1 ----
                menuItem1.setText("Open");
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Exit");
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu3 ========
            {
                menu3.setText("Edit");
            }
            menuBar1.add(menu3);

            //======== menu2 ========
            {
                menu2.setText("View");
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("A Label!!!!");
        contentPane.add(label1, CC.xy(1, 2));

        //---- button1 ----
        button1.setText("A Button");
        contentPane.add(button1, CC.xy(3, 4));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menu3;
    private JMenu menu2;
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
