/*
    Name: Christopher Prats
    Course: CNT 4714 Fall 2020
    Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
    Date: November 1,2020
    Class: SQLClient
 */
import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SQLClient {
    private JPanel Panel;
    private JLabel DatabaseInfoSection;
    private JLabel SQLCommandSection;
    private JLabel JDBCDriverLabel;
    private JLabel DatabaseLabel;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel SQLExecutionWinLabel;
    private JTextField UsernameField;
    private JTextArea SQLTextArea;
    private JLabel ConnectionLabel;
    private JButton ConnectButton;
    private JButton ClearButton;
    private JButton ExecuteButton;
    private JPasswordField PasswordField;
    private JComboBox DatabaseBox;
    private JComboBox DriverBox;
    private JTable ResultTable;
    private JButton ClearResult;
    String sqlCommand;
    String username;
    String password;
    int selectedDriver;
    String selectedDriverStr;
    int selectedDatabase;
    String selectedDatabaseStr;
    Connection connection;
    DatabaseMetaData dbMetaData;
    Statement statement;
    ResultSet resultSet;

    public SQLClient() {
        DriverBox.addItem("");
        DriverBox.addItem("com.mysql.cj.jdbc.Driver");
        ConnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = UsernameField.getText();
                password = String.valueOf(PasswordField.getPassword());
                switch (selectedDriver) {
                    case 0:
                        break;
                    case 1:
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        try {
                            connection = DriverManager.getConnection(selectedDatabaseStr, username, password);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        break;
                }
            }
        });
        ClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLTextArea.setText("");
            }
        });
        ExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlCommand = SQLTextArea.getText();
                try {
                    dbMetaData = connection.getMetaData();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sqlCommand);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        DriverBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDriver = DriverBox.getSelectedIndex();
                selectedDriverStr = DriverBox.getSelectedItem().toString();
                switch (selectedDriver) {
                    case 0:
                        DatabaseBox.removeAllItems();
                        break;
                    case 1:
                        DatabaseBox.removeAllItems();
                        DatabaseBox.addItem("");
                        DatabaseBox.addItem("jdbc:mysql://localhost:3306/project3?useTimezone=true&serverTimezone=UTC");
                        DatabaseBox.addItem("jdbc:mysql://localhost:3306/bikedb?useTimezone=true&serverTimezone=UTC");
                        DatabaseBox.addItem("jdbc:mysql://localhost:3306/testdb?useTimezone=true&serverTimezone=UTC");
                        DatabaseBox.addItem("jdbc:mysql://MySQL.ChrisPrats.lan:3306/project3?useTimezone=true&serverTimezone=UTC");
                        DatabaseBox.addItem("jdbc:mysql://MySQL.ChrisPrats.lan:3306/bikedb?useTimezone=true&serverTimezone=UTC");
                        DatabaseBox.addItem("jdbc:mysql://MySQL.ChrisPrats.lan:3306/testdb?useTimezone=true&serverTimezone=UTC");
                        break;
                }
            }
        });
        DatabaseBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDatabase = DatabaseBox.getSelectedIndex();
                selectedDatabaseStr = DatabaseBox.getSelectedItem().toString();
            }
        });
        ClearResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultTable.setModel(new DefaultTableModel());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SQL Client App");
        frame.setContentPane(new SQLClient().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(1080, 480));
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Panel = new JPanel();
        Panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 12, new Insets(0, 0, 0, 0), -1, -1));
        DatabaseInfoSection = new JLabel();
        DatabaseInfoSection.setBackground(new Color(-16776961));
        DatabaseInfoSection.setForeground(new Color(-16776961));
        DatabaseInfoSection.setText("Enter Database Information");
        Panel.add(DatabaseInfoSection, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JDBCDriverLabel = new JLabel();
        JDBCDriverLabel.setText("JDBC Driver");
        Panel.add(JDBCDriverLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DatabaseLabel = new JLabel();
        DatabaseLabel.setText("Database");
        Panel.add(DatabaseLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UsernameLabel = new JLabel();
        UsernameLabel.setText("Username");
        Panel.add(UsernameLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PasswordLabel = new JLabel();
        PasswordLabel.setText("Password");
        Panel.add(PasswordLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SQLExecutionWinLabel = new JLabel();
        SQLExecutionWinLabel.setBackground(new Color(-16776961));
        SQLExecutionWinLabel.setForeground(new Color(-16776961));
        SQLExecutionWinLabel.setText("SQL Execution Result Window");
        Panel.add(SQLExecutionWinLabel, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        Panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ConnectButton = new JButton();
        ConnectButton.setText("Connect to Database");
        Panel.add(ConnectButton, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(132, 30), null, 0, false));
        ExecuteButton = new JButton();
        ExecuteButton.setText("Execute SQL Command");
        Panel.add(ExecuteButton, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ResultTable = new JTable();
        Panel.add(ResultTable, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-16777216));
        panel1.setForeground(new Color(-16777216));
        Panel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ConnectionLabel = new JLabel();
        ConnectionLabel.setBackground(new Color(-65536));
        ConnectionLabel.setForeground(new Color(-65536));
        ConnectionLabel.setText("No Connection Now");
        panel1.add(ConnectionLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DriverBox = new JComboBox();
        Panel.add(DriverBox, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DatabaseBox = new JComboBox();
        Panel.add(DatabaseBox, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UsernameField = new JTextField();
        Panel.add(UsernameField, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PasswordField = new JPasswordField();
        Font PasswordFieldFont = this.$$$getFont$$$(null, -1, -1, PasswordField.getFont());
        if (PasswordFieldFont != null) PasswordField.setFont(PasswordFieldFont);
        Panel.add(PasswordField, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ClearButton = new JButton();
        ClearButton.setText("Clear SQL Command");
        Panel.add(ClearButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SQLCommandSection = new JLabel();
        SQLCommandSection.setBackground(new Color(-16776961));
        SQLCommandSection.setForeground(new Color(-16776961));
        SQLCommandSection.setText("Enter an SQL Command");
        Panel.add(SQLCommandSection, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SQLTextArea = new JTextArea();
        Panel.add(SQLTextArea, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        ClearResult = new JButton();
        ClearResult.setText("Clear Result Window");
        Panel.add(ClearResult, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Panel;
    }

}
