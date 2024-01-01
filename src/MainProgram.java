import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.Month;
import java.time.Year;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class MainProgram extends MyFrame implements ActionListener, MouseListener, KeyListener {
    private JLabel lblID, lblName, lblType, lblGender, lblColor, lblBreed, lblPrice;
    private JTextField txtID, txtName, txtColor, txtPrice;
    private JComboBox cboGender, cboType, cboBreed;
    private Font f = new Font("Arial", Font.BOLD, 20);
    private JPanel panelPetInfo, panelBirthdate, panelButtons, panelSearch, panelTable;
    private JLabel lblAge;
    private JTextField txtAge;
    private JComboBox cboMonth, cboDay, cboYear;
    private int age;
    private int current_year = Year.now().getValue();
    private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnClose;
    private JLabel lblSearch;
    private JTextField txtSearch;
    private JTable tbl_Pet;
    private DefaultTableModel model_pet;
    private Vector columns, rowData;
    private TableRowSorter tbl_sort;

    public MainProgram() {
        initializedComponents();
        petInfo();
        add(panelPetInfo).setBounds(10, 10, 300, 400);
        panelPetBirthDate();
        add(panelBirthdate).setBounds(10, 420, 300, 70);
        panelButtons();
        add(panelButtons).setBounds(40, 500, 600, 30);
        add(panelPetSearch()).setBounds(320,20,300,30);
        add(panelPetTable()).setBounds(320, 50, 530, 445);
        add(setBackgroundImage("C:\\Users\\Michelle A. Banwag\\Downloads\\img6.jpg"));
        setMyFrame("Pet Registration Form", 890, 600, true, DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        txtID.setText(Integer.toString(Integer.parseInt(model_pet.getRowCount() + "")));
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClose.addActionListener(this);


        tbl_Pet.addMouseListener(this);
        txtSearch.addKeyListener(this);


        txtName.addKeyListener(this);
        txtPrice.addKeyListener(this);
        txtColor.addKeyListener(this);
        resetComponents();


    }
    public void resetComponents(){
        txtID.setText(Integer.toString(model_pet.getRowCount() + 1));


        btnAdd.setEnabled(true);
        btnClear.setEnabled(true);
        btnClose.setEnabled(true);


        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);


        txtName.setText("");
        txtPrice.setText("");
        txtColor.setText("");
        txtAge.setText("0");


        cboGender.setSelectedItem(0);
        cboType.setSelectedItem(0);
        cboBreed.setSelectedItem(0);
        cboMonth.setSelectedItem(0);
        cboDay.setSelectedItem(0);
        cboYear.setSelectedItem(0);
    }
    public void tableClick(){
        txtID.setText(Integer.toString(model_pet.getRowCount() + 1));
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
    }


    private void panelButtons() {
        panelPetButtons();
    }


    public void initializedComponents() {
        lblID = new JLabel("ID: ");
        lblName = new JLabel("Name");
        lblType = new JLabel("Type");
        lblGender = new JLabel("Gender");
        lblColor = new JLabel("Color");
        lblBreed = new JLabel("Breed");
        lblPrice = new JLabel("Price");


        txtID = new JTextField(20);
        txtID.setEditable(true);


        txtName = new JTextField(20);
        txtColor = new JTextField(20);
        txtPrice = new JTextField(20);


        cboGender = new JComboBox<>();
        cboType = new JComboBox<>();
        cboBreed = new JComboBox<>();


        loadToComboBox();


        btnAdd = new JButton("Add New", new ImageIcon("C:\\Users\\Michelle A. Banwag\\Downloads\\add_icon.jpg"));
        btnClear = new JButton("Clear", new ImageIcon("C:\\Users\\Michelle A. Banwag\\Downloads\\clear_icon.jpg"));


        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClose = new JButton("Close");


        ImageIcon addIcon = new ImageIcon(new ImageIcon("C:\\Users\\Michelle A. Banwag\\Downloads\\add_icon.jpg")
                .getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));


        btnAdd = new JButton("Add New", addIcon);
        btnAdd.setHorizontalTextPosition(SwingConstants.RIGHT);


        ImageIcon clearIcon = new ImageIcon(new ImageIcon("C:\\Users\\Michelle A. Banwag\\Downloads\\clear_icon.jpg")
                .getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));


        btnClear = new JButton("Clear", clearIcon);
        btnClear.setHorizontalTextPosition(SwingConstants.RIGHT);
    }
    public void panelPetButtons() {
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 5, 4, 2));
        panelButtons.add(btnAdd);
        panelButtons.add(btnClear);


        panelButtons.add(new JLabel(""));


        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnClose);
    }
    public JPanel panelPetSearch(){
        panelSearch=new JPanel();
        lblSearch=new JLabel("Search");
        txtSearch=new JTextField(10);
        panelSearch.setLayout(new FlowLayout(FlowLayout.LEFT,2,1));
        panelSearch.add(lblSearch);
        panelSearch.add(txtSearch);
        panelSearch.setOpaque(false);
        return panelSearch;
    }


    public JPanel panelPetTable() {
        panelTable = new JPanel();
        tbl_Pet = new JTable();
        model_pet = new DefaultTableModel();


        panelTable.setLayout(new BorderLayout());
        panelTable.add(new JScrollPane(tbl_Pet), BorderLayout.CENTER);


        String cols[] = {"ID", "Name", "Gender", "Type", "Breed", "Color",
                "Price", "Month", "Day", "Year", "Age"};


        columns = new Vector<>();
        for (String val : cols) {
            columns.add(val);
        }
        model_pet.setColumnIdentifiers(columns);
        tbl_Pet.setModel(model_pet);
        tbl_Pet.setAutoResizeMode(tbl_Pet.AUTO_RESIZE_OFF);


        return panelTable;
    }


    public static void main(String[] args) {
        new MainProgram();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cboYear)) {
            age = current_year - Integer.parseInt(cboYear.getSelectedItem().toString());
            txtAge.setText(age + "");
        } else if (e.getSource().equals(btnAdd)) {
            getData();
            model_pet.addRow(rowData);
            txtID.setText(Integer.toString(model_pet.getRowCount() + 0));
            resetComponents();
        } else if (e.getSource().equals(btnClear)) {
            resetComponents();
            // Handle clear button action
        } else if (e.getSource().equals(btnUpdate)) {
            int i=tbl_Pet.getSelectedRow();


           /*tbl_Pet.setValueAt(txtName.getText(),i,1);
           tbl_Pet.setValueAt(cboGender.getSelectedItem(),i,2);
           tbl_Pet.setValueAt(cboType.getSelectedItem(),i,3);
           tbl_Pet.setValueAt(cboBreed.getSelectedItem(),i,4);
           tbl_Pet.setValueAt(txtColor.getText(),i,5);
           tbl_Pet.setValueAt(txtPrice.getText(),i,6);
           tbl_Pet.setValueAt(cboMonth.getSelectedItem(),i,7);
           tbl_Pet.setValueAt(cboDay.getSelectedItem(),i,8);
           tbl_Pet.setValueAt(cboYear.getSelectedItem(),i,9);
           tbl_Pet.setValueAt(txtAge.getText(),i,10);


            */
            getData();
            for (int col=1;col<tbl_Pet.getColumnCount();col++){
                tbl_Pet.setValueAt(rowData.get(col),i,col);
            }
            resetComponents();
            // Handle update button action
        } else if (e.getSource().equals(btnDelete)) {
            int i=tbl_Pet.getSelectedRow();
            model_pet.removeRow(i);
            resetComponents();
            // Handle delete button action
        } else if (e.getSource().equals(btnClose)) { //NO LINE OR NO CODE BELOW
            System.exit(0);
            // Handle close button action
        }
    }




    public void loadToComboBox() {
        cboGender.addItem("Male");
        cboGender.addItem("Female");


        cboType.addItem("Dog");
        cboType.addItem("Cat");
        cboType.addItem("Bird");
        cboType.addItem("Fish");


        cboBreed.addItem("Persian");
        cboBreed.addItem("Saimese");
        cboBreed.addItem("Askal");
        cboBreed.addItem("Siberian");
        cboBreed.addItem("Bulldog");
    }


    public void petInfo() {
        panelPetInfo = new JPanel();
        panelPetInfo.setBorder(BorderFactory.createTitledBorder("Pet Registration Form"));
        panelPetInfo.setLayout(new GridLayout(7, 2));
        panelPetInfo.setFont(f);
        panelPetInfo.setOpaque(false);


        panelPetInfo.add(lblID);
        panelPetInfo.add(txtID);


        panelPetInfo.add(lblName);
        panelPetInfo.add(txtName);


        panelPetInfo.add(lblGender);
        panelPetInfo.add(cboGender);


        panelPetInfo.add(lblType);
        panelPetInfo.add(cboType);


        panelPetInfo.add(lblBreed);
        panelPetInfo.add(cboBreed);


        panelPetInfo.add(lblColor);
        panelPetInfo.add(txtColor);


        panelPetInfo.add(lblPrice);
        panelPetInfo.add(txtPrice);


        add(panelPetInfo);
    }


    public void panelPetBirthDate() {
        panelBirthdate = new JPanel();
        lblAge = new JLabel("Age");
        txtAge = new JTextField("0", 5);
        txtAge.setEditable(false);
        txtAge.setToolTipText("Age");


        cboMonth = new JComboBox(Month.values());
        cboDay = new JComboBox();
        cboYear = new JComboBox();


        panelBirthdate.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
        panelBirthdate.setBorder(BorderFactory.createTitledBorder("Birthdate"));
        panelBirthdate.add(cboMonth);
        panelBirthdate.add(cboDay);
        panelBirthdate.add(cboYear);
        panelBirthdate.add(lblAge);
        panelBirthdate.add(txtAge);


        cboYear.addActionListener(this);


        for (int i = 1; i <= 31; i++) {
            cboDay.addItem(i);
            cboYear.addItem(i + 1970);
        }
        cboYear.setEditable(true);
    }


    public void getData() {
        rowData=new Vector<String>();
        rowData.add(txtID.getText());
        rowData.add(txtName.getText());
        rowData.add(cboGender.getSelectedItem().toString());
        rowData.add(cboType.getSelectedItem().toString());
        rowData.add(cboBreed.getSelectedItem().toString());
        rowData.add(txtColor.getText());
        rowData.add(txtPrice.getText());
        rowData.add(cboMonth.getSelectedItem().toString());
        rowData.add(cboDay.getSelectedItem().toString());
        rowData.add(cboYear.getSelectedItem().toString());
        rowData.add(txtAge.getText());
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int i=tbl_Pet.getSelectedRow();
        //JOptionPane.showMessageDialog(null, "Row"+i+" is selected");
        txtID.setText(tbl_Pet.getValueAt(i,0)+"");
        txtName.setText(tbl_Pet.getValueAt(i,1)+"");
        cboGender.setSelectedItem(tbl_Pet.getValueAt(i,2)+"");
        cboType.setSelectedItem(tbl_Pet.getValueAt(i,3)+"");
        cboBreed.setSelectedItem(tbl_Pet.getValueAt(i,4)+"");
        txtColor.setText(tbl_Pet.getValueAt(i,5)+"");
        txtPrice.setText(tbl_Pet.getValueAt(i,6)+"");
        cboMonth.setSelectedItem(tbl_Pet.getValueAt(i,7)+"");
        cboDay.setSelectedItem(tbl_Pet.getValueAt(i,7)+"");
        cboYear.setSelectedItem(tbl_Pet.getValueAt(i,9)+"");
        txtAge.setText(tbl_Pet.getValueAt(i,10)+"");


        tableClick();
    }


    @Override
    public void mousePressed(MouseEvent e) {


    }


    @Override
    public void mouseReleased(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {


    }


    @Override
    public void mouseExited(MouseEvent e) {


    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(txtPrice)) {
            if ((e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z')) {
                e.consume();
            }
        } else if (e.getSource().equals(txtName) || e.getSource().equals(txtColor)) {
            char ch = e.getKeyChar();
            if (!((Character.isWhitespace(ch) || e.getKeyChar() >= 'a' || e.getKeyChar() >= 'A') && (e.getKeyChar() <= 'z' || e.getKeyChar() <= 'z'))) {
                e.consume();
            }


        }
    }


    @Override
    public void keyPressed(KeyEvent e) {


    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(txtSearch)){
            String search=txtSearch.getText();
            tbl_sort=new TableRowSorter<>(model_pet);


            tbl_Pet.setRowSorter(tbl_sort);
            tbl_sort.setRowFilter(RowFilter.regexFilter(search,0,1));
        }
    }


}
