import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class LibrarySoftware implements MouseListener{

    public static String DB_NAME = "Library_System";
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USERNAME = "YOUR_DATABASE_USERNAME";
    private static final String DB_PASSWORD = "YOUR_DATABASE_PASSWORD";
    public static String admin="ADMIN_NAME";
    public static String Pass="ADMIN_PASSWORD";
    public static String Username="";
    public static String User_Id="";


    private JFrame mainFrame;
    private JFrame adminFrame;
    private JFrame userFrame;
    JButton adminButton;
    JButton userButton;

    LibrarySoftware() {
        System.out.println("SKP");
        initializeMainUI();

    }

    private static JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.ORANGE);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 10, 10);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.BLACK);
                g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 10, 10);
            }
        };
        
        button.setBackground(Color.white);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        return button;
    }

    private void initializeMainUI() {


        mainFrame = new JFrame("Library Software");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLayout(null);
        mainFrame.setLocation(200,50);

        JPanel panel=new JPanel();
        panel.setPreferredSize(new Dimension(1000, 1000));
        
        panel.setBounds(0, 0, 1000, 700);
        panel.setLayout(null);

        Font font = new Font("Arial",Font.BOLD,12);

        
        adminButton=createRoundedButton("Admin Login");
        adminButton.setBounds(400, 220, 120, 25);
        adminButton.setBackground(Color.yellow);
        adminButton.setFocusable(false);
        adminButton.setFont(font);
        adminButton.addMouseListener(this);

        userButton = createRoundedButton("User Login");
        userButton.setBounds(400, 270, 120, 25);
        userButton.setBackground(Color.yellow);
        userButton.setFocusable(false);
        userButton.setFont(font);
        userButton.addMouseListener(this);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openadminlogin();
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openuserlogin();
            }
        });

        JLabel label1 = new JLabel("National Institute of Technology, Karnataka");
        label1.setBounds(150,70,700,35);
        Font font1 = new Font("Arial",Font.BOLD,30);
        label1.setFont(font1);

        JLabel label2 = new JLabel("IT Departmental Library");
        label2.setBounds(350,140,500,35);
        Font font2 = new Font("Arial",Font.BOLD,27);
        label2.setFont(font2);

        panel.add(label1);
        panel.add(label2);
        panel.add(adminButton);
        panel.add(userButton);
        
        mainFrame.setResizable(false);
        mainFrame.add(panel);
        panel.setBackground(Color.CYAN);
        mainFrame.setVisible(true);

    }

    private void openAdminUI()
    {
        JPanel panel0=new JPanel();

        JLabel label1 = new JLabel("National Institute of Technology, Karnataka");
        label1.setBounds(150,70,700,35);
        Font font1 = new Font("Arial",Font.BOLD,30);
        label1.setFont(font1);

        JLabel label2 = new JLabel("IT Departmental Library");
        label2.setBounds(350,140,500,35);
        Font font2 = new Font("Arial",Font.BOLD,27);
        label2.setFont(font2);

        panel0.add(label1);
        panel0.add(label2);

        panel0.setBounds(0, 0, 1000, 200);
        panel0.setLayout(null);

        adminFrame = new JFrame("Admin Panel");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(1000, 700);
        adminFrame.setLayout(null);
        adminFrame.setLocation(200,50);
        


        JPanel panel=new JPanel();
        panel.setBounds(280, 240, 1000, 500);
        panel.setLayout(null);

        JButton viewBooksButton =createRoundedButton("View Books");
        viewBooksButton.setBounds(0, 10, 150, 25);
        viewBooksButton.setBackground(Color.ORANGE);
        viewBooksButton.setFocusable(false);

        JButton addBooksButton=createRoundedButton("Add Books");
        addBooksButton.setBounds(250,10,150,25);
        addBooksButton.setBackground(Color.ORANGE);
        addBooksButton.setFocusable(false);

        JButton adduserButton=createRoundedButton("Add User");
        adduserButton.setBounds(0,60,150,25);
        adduserButton.setBackground(Color.ORANGE);
        adduserButton.setFocusable(false);

        JButton issuedBooksButton = createRoundedButton("Issued Books");
        issuedBooksButton.setBounds(250,60,150,25);
        issuedBooksButton.setBackground(Color.ORANGE);
        issuedBooksButton.setFocusable(false);

        JButton returnedBooksButton = createRoundedButton("Returned Books");
        returnedBooksButton.setBounds(0,110,150,25);
        returnedBooksButton.setBackground(Color.ORANGE);
        returnedBooksButton.setFocusable(false);

        JButton histroyBooksButton = createRoundedButton("History Of The Book");
        histroyBooksButton.setBounds(250,110,150,25);
        histroyBooksButton.setBackground(Color.ORANGE);
        histroyBooksButton.setFocusable(false);


        JButton histroyuserButton = createRoundedButton("History Of The User");
        histroyuserButton.setBounds(0,160,150,25);
        histroyuserButton.setBackground(Color.ORANGE);
        histroyuserButton.setFocusable(false);


        JButton fine_details= createRoundedButton("Fine Details");
        fine_details.setBounds(250,160,150,25);
        fine_details.setBackground(Color.ORANGE);
        fine_details.setFocusable(false);

        JButton exit=createRoundedButton("Exit");
        exit.setBounds(170, 380, 70, 25);
        exit.setBackground(Color.RED);
        exit.setFocusable(false);


        panel.add(viewBooksButton);
        panel.add(addBooksButton);
        panel.add(adduserButton);
        panel.add(issuedBooksButton);
        panel.add(returnedBooksButton);
        panel.add(histroyBooksButton);
        panel.add(histroyuserButton);
        panel.add(fine_details);
        panel.add(exit);


        panel.setBackground(Color.CYAN);
        panel0.setBackground(Color.CYAN);
        adminFrame.add(panel0);
        adminFrame.add(panel);
        adminFrame.getContentPane().setBackground(Color.CYAN);
        adminFrame.setVisible(true);

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBooks();
            }
        });

        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addbook();
            }
        });

        adduserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adduser();
            }
        });

        issuedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showissuedbooks();
            }
        });

        returnedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
               retunertable();
            }
        });

        histroyBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                historybook();
            }
        });

        histroyuserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                historyuser();
            }
        });

        fine_details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                penalty();
            }
        });

        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                initializeMainUI();
                adminFrame.dispose();
            }
        });
    }


    public void checkadmin(String adminname,String pasword,JFrame frame)
    {

        if(adminname.equals(admin) && pasword.equals(Pass))
        {
            openAdminUI();
            frame.dispose();
        }

        else
        {
            JOptionPane.showMessageDialog(adminFrame,"Wrong Admin Name or Password","Error",JOptionPane.ERROR_MESSAGE);
        }

        
    }

    public void openadminlogin()
    {

        JFrame adminloginframe=new JFrame("login");
        adminloginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminloginframe.setSize(300, 400);
        adminloginframe.setLocation(550, 280);
        adminloginframe.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBounds(75,50, 750, 500);
        panel1.setLayout(null);

        Font font1 = new Font("Arial",Font.BOLD,15);
        Font font2 = new Font("Arial",Font.BOLD,13);

        JLabel label1=new JLabel("Username");
        label1.setBounds(0, 0, 150, 30);
        label1.setFont(font1);

        JTextField nameField = new JTextField();
        nameField.setBounds(0, 30, 150, 30);

        JLabel label2=new JLabel("Password");
        label2.setBounds(0, 80, 150, 30);
        label2.setFont(font1);

        JPasswordField password = new JPasswordField();
        password.setBounds(0, 110, 150, 30);

        
        panel1.add(label1);
        panel1.add(nameField);
        panel1.add(label2);
        panel1.add(password);

        JButton login=createRoundedButton("Login");;
        login.setBounds(30, 180, 90,30);
        login.setFont(font2);
        login.setFocusable(false);
        login.setBackground(Color.ORANGE);
        panel1.add(login);
        adminloginframe.add(panel1);
        adminloginframe.setResizable(false);
        adminloginframe.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String adminname=nameField.getText();
                char[] passwordChars = password.getPassword();
                String pass = new String(passwordChars);
                checkadmin(adminname,pass,adminloginframe);
                //openAdminUI();

            }});
        
    }



    private void showBooks() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectHistoryQuery = "SELECT  book_id,book_name,author FROM books";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectHistoryQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("book_id");
                tableModel.addColumn("book_name");
                tableModel.addColumn("author");

                while (resultSet.next()) {
                    int book_id = resultSet.getInt("book_id");
                    String book_name = resultSet.getString("book_name");
                    String author=resultSet.getString("author");

                    Object[] row = { book_id,book_name,author};
                    tableModel.addRow(row);
                }

                JTable table = new JTable(tableModel);
                JOptionPane.showMessageDialog(mainFrame,new JScrollPane(table));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    private void addbook() 
    {
        JTextField bookidField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField authorField = new JTextField(10);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
         panel.add(new JLabel("Book_ID"));
        panel.add(bookidField);
        panel.add(new JLabel("Book_Name"));
        panel.add(nameField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);


        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "ADD BOOK", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String bookId = bookidField.getText();
            String bookname = nameField.getText();
            String author = authorField.getText();
            uploadbook(bookId,bookname,author);
        }

    }



    private void uploadbook(String book_id,String book_name, String author ) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String insertProductQuery = "INSERT INTO books (book_id,book_name,author) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductQuery)) {
                preparedStatement.setString(1,book_id);
                preparedStatement.setString(2,book_name);
                preparedStatement.setString(3,author);
               

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Books Uploaded successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed to upload book.");
        }
    }


    private void uploaduser(String user_name, String registerNO,String password,String Year ) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String insertProductQuery = "INSERT INTO users (username,user_id,Year,password) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductQuery)) {
                preparedStatement.setString(1,user_name);
                preparedStatement.setString(2,registerNO);
                preparedStatement.setString(3,Year);
                preparedStatement.setString(4,password);
               

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "User Uploaded successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed to upload User.");
        }
    }


    private void adduser() 
    {
        List<String> studentYear = new ArrayList<>(List.of("First Year","Second Year","Third Year","Fourth Year","M.Tech","P.H.D"));
        JComboBox<String> year = new JComboBox<>(studentYear.toArray(new String[0]));
        JTextField nameField = new JTextField(10);
        JTextField password = new JTextField(10);
        JTextField registerno = new JTextField(10);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Student_Name"));
        panel.add(nameField);
        panel.add(new JLabel("Sudent_id"));
        panel.add(registerno);
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(new JLabel("Year:"));
        panel.add(year);



        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "ADD USER", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = nameField.getText();
            String registerNO=registerno.getText();
            String pasword = password.getText();
            String Year= (String) year.getSelectedItem();
            uploaduser(username,registerNO, pasword, Year);

        }

    }





    private void showissuedbooks() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectHistoryQuery = "SELECT  book_name,username,user_id,issue_date FROM issued_books ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectHistoryQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("book_name");
                tableModel.addColumn("username");
                tableModel.addColumn("user_id");
                tableModel.addColumn("issue_date");
                tableModel.addColumn("Due_date");

                while (resultSet.next()) {
                    String book_name= resultSet.getString("book_name");
                    String username = resultSet.getString("username");
                    String user_id=resultSet.getString("user_id");
                    Date issue_date= resultSet.getDate("issue_date");
                

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(issue_date); 
                    calendar.add(Calendar.DAY_OF_MONTH, 14);
                    Date due_date = calendar.getTime();


                    Object[] row = { book_name,username,user_id,issue_date,due_date};
                    tableModel.addRow(row);
                }

                JTable table = new JTable(tableModel);
                JOptionPane.showMessageDialog(mainFrame,new JScrollPane(table));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    private void retunertable()
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectHistoryQuery = "SELECT username,user_id,book_name,return_date FROM returned_books ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectHistoryQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("username");
                tableModel.addColumn("user_id");
                tableModel.addColumn("book_name");
                tableModel.addColumn("return_date");

                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String user_id=resultSet.getString("user_id");
                    String book_name= resultSet.getString("book_name");
                    Date return_date= resultSet.getDate("return_date");

                    Object[] row = {username,user_id,book_name,return_date};
                    tableModel.addRow(row);
                }

                JTable table = new JTable(tableModel);
                JOptionPane.showMessageDialog(mainFrame,new JScrollPane(table));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public void showhistroybook(String book)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
             
        String selectHistoryQuery = "SELECT book_name, username, user_id, issue_date FROM issued_books WHERE book_name = '" + book + "'";
        
        ResultSet resultSet = connection.createStatement().executeQuery(selectHistoryQuery);

                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("book_name");
                tableModel.addColumn("username");
                tableModel.addColumn("user_id");
                tableModel.addColumn("issue_date");
                tableModel.addColumn("returDate");


                while (resultSet.next()) {
                    String book_name= resultSet.getString("book_name");
                    String usernam = resultSet.getString("username");
                    String users_id=resultSet.getString("user_id");
                    Date issue_date= resultSet.getDate("issue_date");
                    String selectHistoryQuery1 = "SELECT return_date FROM returned_books WHERE book_name = '" + book + "' AND username='" + usernam +"'";
                    ResultSet resultSet1 = connection.createStatement().executeQuery(selectHistoryQuery1);
                    String return_date="Not Returned";
                    while(resultSet1.next())
                    {
                         return_date=resultSet1.getString("return_date");
                    }
                    Object[] row = { book_name,usernam,users_id,issue_date,return_date};
                    tableModel.addRow(row);
                }

                JTable table = new JTable(tableModel);
                JOptionPane.showMessageDialog(mainFrame,new JScrollPane(table));
            }
        catch (SQLException e) {
          e.printStackTrace();
        }



    }



    private void historybook() {
        List<String> books = getAvailablebooks();
        JComboBox<String> booksComboBox = new JComboBox<>(books.toArray(new String[0]));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(new JLabel("Book Name"));
        panel.add(booksComboBox);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "History Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) 
        {
            String book=booksComboBox.getSelectedItem().toString();
            showhistroybook(book);
        }

    }


    public JTable showHistoryUser(String username, String rollno) {
     try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        String selectHistoryQuery = "SELECT book_name, issue_date FROM issued_books WHERE username = '" + username + "' AND user_id='" + rollno + "'";
        ResultSet resultSet = connection.createStatement().executeQuery(selectHistoryQuery);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("book_name");
        tableModel.addColumn("issue_date");
        tableModel.addColumn("returnDate");
        tableModel.addColumn("Fine");

        int TFine=0;

        while (resultSet.next()) {
            String book_name = resultSet.getString("book_name");
            java.sql.Timestamp issue_timestamp = resultSet.getTimestamp("issue_date");
            String selectHistoryQuery1 = "SELECT return_date FROM returned_books WHERE book_name = '" + book_name + "' AND username='" + username + "' AND user_id='" + rollno + "'";
            ResultSet resultSet1 = connection.createStatement().executeQuery(selectHistoryQuery1);
            String return_date = "Not Returned";
            LocalDate returnLocalDate = LocalDate.now();

            

            while (resultSet1.next()) {
                return_date = resultSet1.getString("return_date");
                returnLocalDate = LocalDate.parse(return_date);
            }

            int fine = 0;

            LocalDate issueLocalDate = issue_timestamp.toLocalDateTime().toLocalDate();

            long daysDifference = ChronoUnit.DAYS.between(issueLocalDate, returnLocalDate);
            if (daysDifference > 14) {
                long daysOverdue = daysDifference - 14;
                fine = (int) (daysOverdue * 10);
                TFine=TFine+fine;
            }

            Object[] row = {book_name, issue_timestamp, return_date, fine};
            tableModel.addRow(row);
        }

        
        Object[] totalFineRow = {"","","Total Fine",TFine};
        tableModel.addRow(totalFineRow);

        JTable table = new JTable(tableModel);
        JOptionPane.showMessageDialog(mainFrame, new JScrollPane(table));

        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    return resultTable;
   }



    private void historyuser() {
        
        JPanel panel = new JPanel();
        JTextField namField=new JTextField(10);
        JTextField rollnoField=new JTextField(10);
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Student Name"));
        panel.add(namField);
        panel.add(new JLabel("Roll No"));
        panel.add(rollnoField);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "History User", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) 
        {
            String name=namField.getText();
            String rollNo=rollnoField.getText();
            showHistoryUser(name, rollNo);
        }

    }

    


    public void penalty() {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        String selectQuery = "SELECT book_name, username, user_id, issue_date FROM issued_books";

        ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("book_name");
        tableModel.addColumn("username");
        tableModel.addColumn("user_id");
        tableModel.addColumn("issue_date");
        tableModel.addColumn("return_date");
        tableModel.addColumn("fine");

        int TotalFine=0;
        while (resultSet.next()) {
            String bookName = resultSet.getString("book_name");
            String username = resultSet.getString("username");
            String userId = resultSet.getString("user_id");
            String issueDate = resultSet.getString("issue_date");

            String selectReturnDateQuery = "SELECT return_date FROM returned_books WHERE book_name = ? AND username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectReturnDateQuery)) {
                preparedStatement.setString(1, bookName);
                preparedStatement.setString(2, username);
                ResultSet returnDateResultSet = preparedStatement.executeQuery();

                String returnDate ="Not Returned";
                LocalDate returnLocalDate=LocalDate.now();
                int fine = 0;
                if (returnDateResultSet.next()) {
                    returnDate = returnDateResultSet.getString("return_date");
                    returnLocalDate = LocalDate.parse(returnDate);
                }

        
                LocalDate issueLocalDate = LocalDate.parse(issueDate);
                    
                long daysDifference = ChronoUnit.DAYS.between(issueLocalDate, returnLocalDate);
                if (daysDifference > 14) {
                    long daysOverdue = daysDifference - 14;
                    fine = (int) (daysOverdue * 10); 
                    TotalFine=TotalFine+fine;
                }
                

                Object[] row = {bookName, username, userId, issueDate, returnDate, fine};
                tableModel.addRow(row);
            }
        }

        Object[] totalFineRow = {"","","","", "Total Fine",TotalFine};
        tableModel.addRow(totalFineRow);


        JTable table = new JTable(tableModel);
        JOptionPane.showMessageDialog(mainFrame, new JScrollPane(table));
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    private void openUserUI() {


        JPanel panel0=new JPanel();

        JLabel label1 = new JLabel("National Institute of Technology, Karnataka");
        label1.setBounds(150,70,700,35);
        Font font1 = new Font("Arial",Font.BOLD,30);
        label1.setFont(font1);

        JLabel label2 = new JLabel("IT Departmental Library");
        label2.setBounds(350,140,500,35);
        Font font2 = new Font("Arial",Font.BOLD,27);
        label2.setFont(font2);

        panel0.add(label1);
        panel0.add(label2);

        panel0.setBounds(0, 0, 1000, 200);
        panel0.setLayout(null);
        panel0.setBackground(Color.CYAN);

        userFrame = new JFrame("User Panel");
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setSize(1000, 700);
        userFrame.setLayout(null);
        userFrame.setLocation(200, 50);

        JPanel panel=new JPanel();
        panel.setBounds(400, 240, 1000, 500);
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);


        JButton myAccountButton = createRoundedButton("My Account");
        myAccountButton.setBounds(0,0,150,25);
        myAccountButton.setBackground(Color.ORANGE);

        JButton viewBooksButton = createRoundedButton("View Books");
        viewBooksButton.setBounds(0,50,150,25);
        viewBooksButton.setBackground(Color.ORANGE);

        JButton searchBooksButton = createRoundedButton("Search Books");
        searchBooksButton.setBounds(0,100,150,25);
        searchBooksButton.setBackground(Color.ORANGE);

        JButton lendBookButton = createRoundedButton("Lend Book");
        lendBookButton.setBounds(0,150,150,25);
        lendBookButton.setBackground(Color.ORANGE);

        JButton returnBookButton = createRoundedButton("Return Book");
        returnBookButton.setBounds(0,200,150,25);
        returnBookButton.setBackground(Color.ORANGE);

        JButton exit=createRoundedButton("Exit");
        exit.setBounds(30, 380, 70, 25);
        exit.setBackground(Color.RED);

        panel.add(myAccountButton);
        panel.add(viewBooksButton);
        panel.add(searchBooksButton);
        panel.add(lendBookButton);
        panel.add(returnBookButton);
        panel.add(exit);
        panel.setVisible(true);

        userFrame.add(panel0);
        userFrame.add(panel);
        userFrame.setResizable(false);
        userFrame.getContentPane().setBackground(Color.CYAN);
        userFrame.setVisible(true);

        myAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserAccount(User_Id, Username);
            }
        });
                

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  showBooks();
            }
        });

        searchBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  searchbooks();
            }
        });


        lendBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                lendbook();
                
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returningbook();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeMainUI();
                userFrame.dispose();
            }
        });
    }

    public void checkuser(String name,String pasword,JFrame frame)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
             
        String selectHistoryQuery = "SELECT username,password FROM users WHERE username= '" + name+ "' AND password='"+ pasword +"'";

        ResultSet resultSet = connection.createStatement().executeQuery(selectHistoryQuery);

                int c=0;
                while (resultSet.next()) 
                {
                    c++;
                    Username=name;
                    User_Id=pasword;
                    openUserUI();
                    frame.dispose();
                }

                if(c==0)
                {
                  JOptionPane.showMessageDialog(userFrame,"Wrong User Name or Password","Error",JOptionPane.ERROR_MESSAGE);   
                }

            }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void openuserlogin()
    {

        JFrame adminloginframe=new JFrame("login");
        adminloginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminloginframe.setSize(300, 400);
        adminloginframe.setLocation(550, 280);
        adminloginframe.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBounds(75,50, 750, 500);
        panel1.setLayout(null);

        Font font1 = new Font("Arial",Font.BOLD,15);
        Font font2 = new Font("Arial",Font.BOLD,13);

        JLabel label1=new JLabel("Student Name");
        label1.setBounds(0, 0, 150, 30);
        label1.setFont(font1);

        JTextField nameField = new JTextField();
        nameField.setBounds(0, 30, 150, 30);

        JLabel label2=new JLabel("Password");
        label2.setBounds(0, 80, 150, 30);
        label2.setFont(font1);

        JPasswordField password = new JPasswordField();
        password.setBounds(0, 110, 150, 30);

        
        panel1.add(label1);
        panel1.add(nameField);
        panel1.add(label2);
        panel1.add(password);

        JButton login=createRoundedButton("Login");
        login.setBounds(30, 180, 90,30);
        login.setFont(font2);
        login.setFocusable(false);
        login.setBackground(Color.ORANGE);
        panel1.add(login);
        adminloginframe.add(panel1);
        adminloginframe.setResizable(false);
        adminloginframe.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name=nameField.getText();
                char[] passwordChars = password.getPassword();
                String pass = new String(passwordChars);
                checkuser(name, pass,adminloginframe);

            }});
        
    }

    private void openUserAccount(String userId, String userName) {
        JFrame userAccountFrame = new JFrame("User Account");
        userAccountFrame.setSize(600, 400);
        userAccountFrame.setLocation(380, 280);
        userAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new GridLayout(1, 1));
    
        String userInfoText = "<html><div style='text-align: center; width: 200px;'>"
                + "<b>Name:</b> " + userName 
                + "<hr style='margin: 3px;'>"
                + "<b>User ID:</b> " + userId
                + "<hr style='margin: 3px;'>"
                + "</div></html>";
    
        JLabel userInfoLabel = new JLabel(userInfoText);
        userInfoLabel.setHorizontalAlignment(JLabel.CENTER);  // Center-align the label
        userInfoPanel.add(userInfoLabel);
    
        JTable historyTable = showHistory(userName, userId);
    
        userAccountFrame.add(userInfoPanel, BorderLayout.NORTH);
        userAccountFrame.add(new JScrollPane(historyTable), BorderLayout.CENTER);
    
        userAccountFrame.setVisible(true);
    }
    


    public JTable showHistory(String username, String rollno) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book Name");
        tableModel.addColumn("Issued Date");
        tableModel.addColumn("Due Date");
        tableModel.addColumn("Returned Date");
        tableModel.addColumn("Fine");
    
        int totalFine = 0;
    
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectHistoryQuery = "SELECT book_name, issue_date FROM issued_books WHERE username = '" + username + "' AND user_id='" + rollno + "'";
            ResultSet resultSet = connection.createStatement().executeQuery(selectHistoryQuery);
    
            while (resultSet.next()) {
                String book_name = resultSet.getString("book_name");
                java.sql.Timestamp issue_timestamp = resultSet.getTimestamp("issue_date");
                LocalDate issueLocalDate = issue_timestamp.toLocalDateTime().toLocalDate();
                LocalDate dueLocalDate = issueLocalDate.plusDays(14);
                String dueDate = dueLocalDate.toString();
    
                String selectHistoryQuery1 = "SELECT return_date FROM returned_books WHERE book_name = '" + book_name + "' AND username='" + username + "' AND user_id='" + rollno + "'";
                ResultSet resultSet1 = connection.createStatement().executeQuery(selectHistoryQuery1);
                String return_date = "Not Returned";
                LocalDate returnLocalDate = LocalDate.now();
    
                while (resultSet1.next()) {
                    return_date = resultSet1.getString("return_date");
                    returnLocalDate = LocalDate.parse(return_date);
                }
    
                int fine = 0;
    
                long daysDifference = ChronoUnit.DAYS.between(issueLocalDate, returnLocalDate);
                if (daysDifference > 14) {
                    long daysOverdue = daysDifference - 14;
                    fine = (int) (daysOverdue * 10);
                    totalFine += fine;
                }
    
                Object[] row = {book_name, issue_timestamp, dueDate, return_date, fine};
                tableModel.addRow(row);
            }
    
            Object[] totalFineRow = {"", "", "", "Total Fine", totalFine};
            tableModel.addRow(totalFineRow);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        JTable table = new JTable(tableModel);
    
        return table;
    }
    


    

    private JTextField nameField = new JTextField(10);
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable resultTable = new JTable(tableModel);

    public void searchbooks() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel resultPanel = new JPanel(new BorderLayout());

        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch(nameField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch(nameField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is not relevant for plain text fields
            }
        });

        searchPanel.add(new JLabel("Search Books:"), BorderLayout.NORTH);
        searchPanel.add(nameField, BorderLayout.CENTER);

        tableModel.addColumn("Book ID");
        tableModel.addColumn("Book Name");
        tableModel.addColumn("Author");
        resultPanel.add(new JLabel("Search Results:"), BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(mainFrame, mainPanel, "Search_BOOK", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // No need to call performSearch here
        }
        else{
            tableModel.setRowCount(0);
        }
    }

    public void performSearch(String keyword) {
        tableModel.setRowCount(0); // Clear previous results
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectHistoryQuery = "SELECT book_id,book_name,author FROM books WHERE book_name LIKE '%" + keyword + "%'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectHistoryQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String book_id = resultSet.getString("book_id");
                    String book_name = resultSet.getString("book_name");
                    String author = resultSet.getString("author");

                    Object[] row = {book_id, book_name, author};
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    private void issuedbook(String book, String username,String userid) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String insertPurchaseQuery = "INSERT INTO  issued_books(book_name,username,user_id,issue_date) VALUES (?, ?,?, CURRENT_DATE)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPurchaseQuery)) {
                preparedStatement.setString(1, book);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, userid);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Book Borrowed Succesfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed .");
        }
    }

    private void lendbook() {
        List<String> books = getAvailablebooks();
        JComboBox<String> booksComboBox = new JComboBox<>(books.toArray(new String[0]));
        JTextField namefeild= new JTextField(10);
        JTextField useridField = new JTextField(10);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Book Name"));
        panel.add(booksComboBox);
        panel.add(new JLabel("User Name:"));
        panel.add(namefeild);
        panel.add(new JLabel("User Id :"));
        panel.add(useridField);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Lend_BOOK", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String book= (String) booksComboBox.getSelectedItem();
            String username = namefeild.getText();
            String userid=useridField.getText();
            issuedbook(book,username,userid);
        }

    }



    

    private List<String> getAvailablebooks() {
        List<String> books = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectProductsQuery = "SELECT book_name FROM books";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectProductsQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String productName = resultSet.getString("book_name");
                    books.add(productName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }



    public void returnbook(String book,String username,String user_id)
    {
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String insertPurchaseQuery = "INSERT INTO  returned_books(username,user_id,book_name,return_date) VALUES (?, ?,?, CURRENT_DATE)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPurchaseQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, user_id);
                preparedStatement.setString(3, book);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Book returned Succesfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Failed .");
        }
    }

    private List<String> getbooks(int userid) {
        List<String> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
             
        String selectHistoryQuery = "SELECT book_name FROM issued_books WHERE user_id= '" + userid + "'";

        ResultSet resultSet = connection.createStatement().executeQuery(selectHistoryQuery);

                while (resultSet.next()) {
                    String productName = resultSet.getString("book_name");
                    books.add(productName);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    

    public void returningbook()
    {
       
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        JTextField namefeild= new JTextField(10);
        JTextField useridField = new JTextField(10);
        panel.add(new JLabel("User Name:"));
        panel.add(namefeild);
        panel.add(new JLabel("User Id :"));
        panel.add(useridField);
    
        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Returned Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) 
        {
            String inputText = useridField.getText();

            int inputNumber=0;
            try
            {
                inputNumber = Integer.parseInt(inputText);
                
            } 

            catch (NumberFormatException e) {
                
            }


            List<String> books = getbooks(inputNumber);
            JComboBox<String> booksComboBox = new JComboBox<>(books.toArray(new String[0]));
            booksComboBox.setPreferredSize(new Dimension(100, 25));;
            JPanel panel1 = new JPanel();
            panel1.add(new JLabel("Book Name"));
            panel1.add(booksComboBox);
            String book= (String) booksComboBox.getSelectedItem();
            String username = namefeild.getText();
            String userid=useridField.getText();
            int res = JOptionPane.showConfirmDialog(mainFrame, panel1, "Your Issued BOOK", JOptionPane.OK_CANCEL_OPTION);
            if(res==JOptionPane.OK_OPTION)
            {
                returnbook(book,username,userid);
            }
        }

    }


    

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD))
        {
            
            String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            System.out.println("Database created (if not exists) successfully.");

            String useDatabaseSql = "USE " + DB_NAME;
            System.out.println("Using database: " + DB_NAME);

            Statement statement=connection.createStatement();
            statement.executeUpdate(createDatabaseSql);

            Statement statement0=connection.createStatement();
            statement0.executeUpdate(useDatabaseSql);

            DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
        
            String createTable1 ="CREATE TABLE IF NOT EXISTS books (" +
            "book_id INT NOT NULL UNIQUE," +
            "book_name VARCHAR(100) NOT NULL," +
            "author VARCHAR(100) NOT NULL" +
            ")";

            String createTable2="CREATE TABLE IF NOT EXISTS users (" +
                "user_id VARCHAR(20) PRIMARY KEY," +
                "username VARCHAR(100) NOT NULL," +
                "Year VARCHAR(100) NOT NULL," +
                "password VARCHAR(100) NOT NULL"+
                ")";

            String createTable3="CREATE TABLE IF NOT EXISTS issued_books (" +
                "book_name VARCHAR(100) NOT NULL," +
                "username VARCHAR(100) NOT NULL," +
                "user_id INT NOT NULL," +
                "issue_date DATE"+
                ")";

            String createTable4="CREATE TABLE IF NOT EXISTS returned_books (" +
                "username VARCHAR(100) NOT NULL," +
                "user_id INT NOT NULL," +
                "book_name VARCHAR(100) NOT NULL," +
                "return_date DATE"+
                ")";


            
            Statement statement1 = connection.createStatement();
        
            statement1.executeUpdate(createTable1);
            statement1.executeUpdate(createTable2);
            statement1.executeUpdate(createTable3);
            statement1.executeUpdate(createTable4);

            System.out.println("Table created (if not exists) successfully.");

            statement1.close();
            connection.close();

        }

        catch (SQLException e) {
            e.printStackTrace();
        }


        new test();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==adminButton)
        {
            adminButton.setBackground(Color.green);
        }
        else
        {
            userButton.setBackground(Color.green);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==adminButton)
        {
            adminButton.setBackground(Color.yellow);
        }
        else
        {
            userButton.setBackground(Color.yellow);
        }
        
    }
    
}
