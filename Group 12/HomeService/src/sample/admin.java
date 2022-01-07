package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import javax.swing.*;


public class admin implements Initializable {

    Connection conn=mysqlconnect.ConnectDb();
    PreparedStatement pst,pst2;
    ResultSet rs;

    ObservableList<HouseOwnersTable> oblist = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label wellbl;

    @FXML
    private AnchorPane ancAdminDashboard;

    @FXML
    private AnchorPane ancAddworkers;

    @FXML
    private JFXTextField addwfirstname;

    @FXML
    private JFXComboBox<String> addwtitle;

    @FXML
    private JFXTextField addwlastname;

    @FXML
    private JFXTextField addwhomeno;

    @FXML
    private JFXTextField addwpersonalno;

    @FXML
    private JFXComboBox<String> addwuserroll;

    @FXML
    private JFXButton btnaddworkadd;

    @FXML
    private JFXButton btnaddworkclear;

    @FXML
    private JFXButton btnAddWorkers;

    @FXML
    private JFXButton btnViewWorkers;

    @FXML
    private JFXButton btnMenuChangePw_admin;


    @FXML
    private AnchorPane ancMenuChangePw_admin;

    @FXML
    private JFXPasswordField pwdCurrentPw;

    @FXML
    private JFXPasswordField pwdConfirmPw;

    @FXML
    private JFXPasswordField pwdNewpw;

    @FXML
    private JFXButton btnChangePwSubmit;

    @FXML
    private Label lblAlertCurrentPw;

    @FXML
    private Label lblAlertMatchPw;

    @FXML
    private JFXButton btnLogout;


    @FXML
    void actLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Household Service");
        btnLogout.getScene().getWindow().hide();

        Controller controller = loader.getController();

        stage.show();
    }

    @FXML
    void actMenuDashboard(ActionEvent event) {
        actMenuDashboard();
    }



    @FXML
    void add(ActionEvent event) throws SQLException {
        conn =mysqlconnect.ConnectDb();

        String sql="insert into workers(title,firstname,lastname,personalno,homeno,workerroll,username)values(?,?,?,?,?,?,?)";
        try{
            pst =conn.prepareStatement(sql);
            pst.setString(1,addwtitle.getValue().toString());
            pst.setString(2,addwfirstname.getText());
            pst.setString(3,addwlastname.getText());
            pst.setString(4,addwpersonalno.getText());
            pst.setString(5,addwhomeno.getText());
            pst.setString(6,addwuserroll.getValue().toString());
            pst.setString(7,addwfirstname.getText().trim().toLowerCase());
            pst.execute();

            String sql2="insert into users(username,password,userroll)values(?,?,?)";

            pst2 =conn.prepareStatement(sql2);
            pst2.setString(1,addwfirstname.getText().trim().toLowerCase());
            pst2.setString(2,"Abcd1234#");
            pst2.setString(3,"workers");
            pst2.execute();
            clear();
            JOptionPane.showMessageDialog(null,"The New Worker Added Successfully");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    void clear(){
        addwfirstname.clear();
        addwlastname.clear();
        addwpersonalno.clear();
        addwhomeno.clear();
    }
    @FXML
    void clear(ActionEvent event) {
        clear();
    }


    @FXML
    void workadd(ActionEvent event) {
        clickAddWorkersMenu();
    }

    void clickAddWorkersMenu(){
        ancAdminDashboard.setVisible(false);
        ancMenuChangePw_admin.setVisible(false);
        ancAddworkers.setVisible(true);
        ancMenuHouseOwners.setVisible(false);
    }

    @FXML
    void initialize() {

    }
    String uroll,uname;

    public void Details(String name, String userroll){
        wellbl.setText("WELCOME"+" "+name);
        this.uroll=userroll;
        this.uname=name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addwtitle.getItems().addAll("Mr","Miss","Mrs");
        addwuserroll.getItems().addAll("Plumber","Cleaner","Mechanic","Painter","Carpenter","Electrician");

    }

    @FXML
    void viewWorkers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("workers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("Admin");

        Workers_Controller workers_Controller = loader.getController();
        workers_Controller.getUserroll("admin","admin");
        stage1.show();
        btnViewWorkers.getScene().getWindow().hide();
    }

    public void actMenuDashboard() {
        ancAdminDashboard.setVisible(true);
        ancAddworkers.setVisible(false);
        ancMenuChangePw_admin.setVisible(false);
        ancMenuHouseOwners.setVisible(false);
    }



    @FXML
    void actMenuChangePw_admin(ActionEvent event) {
        actMenuChangePw_admin();
    }

    String curPwtyped,newPw,confirmPw,oldPw;

    @FXML
    void checkOldPwWithDb(MouseEvent event) {
        lblAlertCurrentPw.setText("");
        pwdNewpw.setDisable(true);
        pwdConfirmPw.setDisable(true);
        lblAlertMatchPw.setText("");
        btnChangePwSubmit.setDisable(true);

        curPwtyped=pwdCurrentPw.getText();
        String sql="Select * from users where username='"+uname+"'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                this.oldPw = rs.getString("password");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

        if(!curPwtyped.equals(oldPw)){
            lblAlertCurrentPw.setText("Not match with your current password");
        }
        else{
            pwdNewpw.setDisable(false);
            pwdConfirmPw.setDisable(false);
            btnChangePwSubmit.setDisable(false);
        }

    }

    @FXML
    void actChangePwSubmit(ActionEvent event) {
        newPw = pwdNewpw.getText();
        confirmPw = pwdConfirmPw.getText();
        if (pwdNewpw.getText().equals("") || pwdConfirmPw.getText().equals("") || (!pwdCurrentPw.getText().equals(oldPw))) {
            lblAlertMatchPw.setText("Fill all required fields correctly.");
        } else if (newPw.equals(confirmPw)) {
            String sql = "Update users SET password='" + newPw + "' where username='" + uname + "'";
            try {
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            lblAlertMatchPw.setText("Your new password updated successfully.");
            JOptionPane.showMessageDialog(null, "Your new password updated successfully.");
            pwdCurrentPw.setText("");
            pwdNewpw.setText("");
            pwdConfirmPw.setText("");
            pwdNewpw.setDisable(true);
            pwdConfirmPw.setDisable(true);
        }
        else{
            lblAlertMatchPw.setText("New password does not match with confirm password.");
        }
    }

    void actMenuChangePw_admin(){
        ancAdminDashboard.setVisible(false);
        ancAddworkers.setVisible(false);
        ancMenuHouseOwners.setVisible(false);
        ancMenuChangePw_admin.setVisible(true);

        lblAlertCurrentPw.setText("");
        lblAlertMatchPw.setText("");
        pwdCurrentPw.setText("");
        pwdNewpw.setText("");
        pwdConfirmPw.setText("");
        pwdNewpw.setDisable(true);
        pwdConfirmPw.setDisable(true);
        btnChangePwSubmit.setDisable(true);
    }

    @FXML
    private JFXButton btnMenuHouseOwners;

    @FXML
    private AnchorPane ancMenuHouseOwners;

    @FXML
    private TableView<HouseOwnersTable> tabHouseOwners;

    @FXML
    private TableColumn<HouseOwnersTable, Integer> colHoid;

    @FXML
    private TableColumn<HouseOwnersTable, String> colHoFullname;

    @FXML
    private TableColumn<HouseOwnersTable, Integer> colHoMobileno;

    @FXML
    private TableColumn<HouseOwnersTable, Integer> colHoHomeno;

    @FXML
    private TableColumn<HouseOwnersTable, String> colHoEmail;

    @FXML
    private TableColumn<HouseOwnersTable, String> colHoAddress;

    @FXML
    void actMenuHouseOwners(ActionEvent event) throws SQLException {
        actMenuHouseOwners();
    }

    void tableView() throws SQLException {
        //Connection conn = mysqlconnect.ConnectDb();
        String hoTitle,hoFname,hoLname,hoFullname;

        //Table view//
        String sql1 = "SELECT * FROM home_owners";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql1);

            while (rs.next()) {
                hoTitle= rs.getString("title");
                hoFname=rs.getString("firstname");
                hoLname=rs.getString("lastname");
                hoFullname=hoTitle+". "+hoFname+" "+hoLname;

                oblist.addAll(new HouseOwnersTable(rs.getInt("house_ownersID"),hoFullname, rs.getInt("personal_TelephoneNO"), rs.getInt("home_telephone_No"), rs.getString("email"), rs.getString("address")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
        colHoid.setCellValueFactory(new PropertyValueFactory<>("ho_id"));
        colHoFullname.setCellValueFactory(new PropertyValueFactory<>("ho_fullname"));
        colHoMobileno.setCellValueFactory(new PropertyValueFactory<>("ho_mobile"));
        colHoHomeno.setCellValueFactory(new PropertyValueFactory<>("ho_homeno"));
        colHoEmail.setCellValueFactory(new PropertyValueFactory<>("ho_email"));
        colHoAddress.setCellValueFactory(new PropertyValueFactory<>("ho_address"));

        tabHouseOwners.setItems(oblist);
    }
    void actMenuHouseOwners() throws SQLException {
        ancMenuHouseOwners.setVisible(true);
        ancAdminDashboard.setVisible(false);
        ancAddworkers.setVisible(false);
        ancMenuChangePw_admin.setVisible(false);
        tableView();
    }

    @FXML
    void clickRow(MouseEvent event) {

    }

}
