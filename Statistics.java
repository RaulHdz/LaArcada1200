package reader;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raúl
 */
public class Statistics extends javax.swing.JFrame {

    private String inputFile; // String used to insert excel file name
    public static String[] Name = new String[583]; // Array where names imported from excel are saved
    public static String[][] Quantity = new String[17][583]; // Matrix where quantities imported from excel are saved
    public static String[][] Cost = new String[17][583]; // Matrix where costs imported from excel are saved
    public static String[][] Price = new String[17][583]; // Matrix where prices imported from excel are saved
    public static String[] SN = new String[583]; // Array where names that matches the search results are saved
    public static String[][] SQ = new String[17][583]; // Matrix where quantities that matches the search results are saved
    public static String[][] SC = new String[17][583]; // Matrix where costs that matches the search results are saved
    public static String[][] SP = new String[17][583]; // Matrix where prices that matches the search results are saved
    public static String[] AN = new String[583]; // Array where names that are added to the table are saved
    public static String[][] AQ = new String[17][583]; // Matrix where quantities that are added to the table are saved
    public static String[][] AC = new String[17][583]; // Matrix where costs that are added to the table are saved
    public static String[][] AP = new String[17][583]; // Matrix where prices that are added to the table are saved
    public static int a = 0; // Integer used to organize the data in the table by indicating the amount of dishes that are currently being displayed in the table
    public static int c = 0; // Integer used to organize the data in the list by indicating the amount of dishes that are currently being displayed in the list

    public void setInputFile(String inputFile) { // Method used to name the file that is being imported
        this.inputFile = inputFile;
    }

    public void read() throws IOException { // Method used to read the excel file
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(12); // Reads the 13th sheet
            for (int i = 4; i < 587; i++) { // Reads from row 5 to 587
                Cell cell = sheet.getCell(1, i); // Names appear in column 2
                Name[i - 4] = cell.getContents(); // Imports names
            }
            for (int i = 4; i < 587; i++) { // Reads from row 5 to 587
                for (int j = 15; j < 96; j = j + 5) { // Quantities for each month appear every 5 columns
                    Cell cell = sheet.getCell(j, i);
                    Quantity[j / 5 - 3][i - 4] = cell.getContents(); // Imports quantities
                }
            }
            for (int i = 4; i < 587; i++) { // Reads from row 5 to 587
                for (int j = 18; j < 99; j = j + 5) { // Quantities for each month appear every 5 columns
                    Cell cell = sheet.getCell(j, i);
                    Cost[(j - 3) / 5 - 3][i - 4] = cell.getContents(); // Imports costs
                }
            }
            for (int i = 4; i < 587; i++) { // Reads from row 5 to 587
                for (int j = 19; j < 100; j = j + 5) { // Quantities for each month appear every 5 columns
                    Cell cell = sheet.getCell(j, i);
                    Price[(j - 4) / 5 - 3][i - 4] = cell.getContents(); // Imports prices
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public static void stringquicksortasc(String A[], int L, int R) { // Quick sorts Strings in the array A[] in ascending order
        String P = A[L]; // Pivot
        String PN = AN[L]; // Pivot Name
        String[] PQ = new String[17]; // Pivot Quantity
        String[] PC = new String[17]; // Pivot Cost
        String[] PP = new String[17]; // Pivot Price
        for (int k = 0; k < 17; k++) {
            PQ[k] = AQ[k][L];
            PC[k] = AC[k][L];
            PP[k] = AP[k][L];
        }
        int i = L;
        int j = R;
        String B; // Helps save values of Strings in order to make changes
        while (i < j) {
            while (A[i].compareTo(P) <= 0 && i < j) {
                i++;
            }
            while (A[j].compareTo(P) > 0) {
                j--;
            }
            if (i < j) {
                B = A[i]; // Changes values
                A[i] = A[j];
                A[j] = B;
                B = AN[i]; // Changes values with the values of the table
                AN[i] = AN[j];
                AN[j] = B;
                for (int k = 0; k < 17; k++) {
                    B = AQ[k][i];
                    AQ[k][i] = AQ[k][j];
                    AQ[k][j] = B;
                    B = AC[k][i];
                    AC[k][i] = AC[k][j];
                    AC[k][j] = B;
                    B = AP[k][i];
                    AP[k][i] = AP[k][j];
                    AP[k][j] = B;
                }
            }
        }
        A[L] = A[j]; // Changes values
        A[j] = P;
        AN[L] = AN[j]; // Changes values with the values of the table
        AN[j] = PN;
        for (int k = 0; k < 17; k++) {
            AQ[k][L] = AQ[k][j];
            AQ[k][j] = PQ[k];
            AC[k][L] = AC[k][j];
            AC[k][j] = PC[k];
            AP[k][L] = AP[k][j];
            AP[k][j] = PP[k];
        }
        if (L < (j - 1)) {
            stringquicksortasc(A, L, (j - 1));
        }
        if ((j + 1) < R) {
            stringquicksortasc(A, (j + 1), R);
        }
    }

    public static void stringquicksortdes(String A[], int L, int R) { // Quick sorts Strings in the array A[] in descending order
        String P = A[L]; // Pivot
        String PN = AN[L]; // Pivot Name
        String[] PQ = new String[17]; // Pivot Quantity
        String[] PC = new String[17]; // Pivot Cost
        String[] PP = new String[17]; // Pivot Price
        for (int k = 0; k < 17; k++) {
            PQ[k] = AQ[k][L];
            PC[k] = AC[k][L];
            PP[k] = AP[k][L];
        }
        int i = L;
        int j = R;
        String B; // Helps save values of Strings in order to make changes
        while (i < j) {
            while (A[i].compareTo(P) >= 0 && i < j) {
                i++;
            }
            while (A[j].compareTo(P) < 0) {
                j--;
            }
            if (i < j) {
                B = A[i]; // Changes values
                A[i] = A[j];
                A[j] = B;
                B = AN[i]; // Changes values with the values of the table
                AN[i] = AN[j];
                AN[j] = B;
                for (int k = 0; k < 17; k++) {
                    B = AQ[k][i];
                    AQ[k][i] = AQ[k][j];
                    AQ[k][j] = B;
                    B = AC[k][i];
                    AC[k][i] = AC[k][j];
                    AC[k][j] = B;
                    B = AP[k][i];
                    AP[k][i] = AP[k][j];
                    AP[k][j] = B;
                }
            }
        }
        A[L] = A[j]; // Changes values
        A[j] = P;
        AN[L] = AN[j]; // Changes values with the values of the table
        AN[j] = PN;
        for (int k = 0; k < 17; k++) {
            AQ[k][L] = AQ[k][j];
            AQ[k][j] = PQ[k];
            AC[k][L] = AC[k][j];
            AC[k][j] = PC[k];
            AP[k][L] = AP[k][j];
            AP[k][j] = PP[k];
        }
        if (L < (i - 1)) {
            stringquicksortdes(A, L, (j - 1));
        }
        if ((j + 1) < R) {
            stringquicksortdes(A, (j + 1), R);
        }
    }

    public static void intquicksortasc(int A[], int L, int R) { // Quick sorts Integers in the array A[] in ascending order
        int P = A[L]; // Pivot
        String PN = AN[L]; // Pivot Name
        String[] PQ = new String[17]; // Pivot Quantity
        String[] PC = new String[17]; // Pivot Cost
        String[] PP = new String[17]; // Pivot Price
        for (int k = 0; k < 17; k++) {
            PQ[k] = AQ[k][L];
            PC[k] = AC[k][L];
            PP[k] = AP[k][L];
        }
        int i = L;
        int j = R;
        String B; // Helps save values of Strings in order to make changes
        int C; // Helps save values of Integers in order to make changes
        while (i < j) {
            while (A[i] <= P && i < j) {
                i++;
            }
            while (A[j] > P) {
                j--;
            }
            if (i < j) {
                C = A[i]; // Changes values
                A[i] = A[j];
                A[j] = C;
                B = AN[i]; // Changes values with the values of the table
                AN[i] = AN[j];
                AN[j] = B;
                for (int k = 0; k < 17; k++) {
                    B = AQ[k][i];
                    AQ[k][i] = AQ[k][j];
                    AQ[k][j] = B;
                    B = AC[k][i];
                    AC[k][i] = AC[k][j];
                    AC[k][j] = B;
                    B = AP[k][i];
                    AP[k][i] = AP[k][j];
                    AP[k][j] = B;
                }
            }
        }
        A[L] = A[j]; // Changes values
        A[j] = P;
        AN[L] = AN[j]; // Changes values with the values of the table
        AN[j] = PN;
        for (int k = 0; k < 17; k++) {
            AQ[k][L] = AQ[k][j];
            AQ[k][j] = PQ[k];
            AC[k][L] = AC[k][j];
            AC[k][j] = PC[k];
            AP[k][L] = AP[k][j];
            AP[k][j] = PP[k];
        }
        if (L < (j - 1)) {
            intquicksortasc(A, L, (j - 1));
        }
        if ((j + 1) < R) {
            intquicksortasc(A, (j + 1), R);
        }
    }

    public static void intquicksortdes(int A[], int L, int R) { // Quick sorts Integers in the array A[] in descending order
        int P = A[L]; // Pivot
        String PN = AN[L]; // Pivot Name
        String[] PQ = new String[17]; // Pivot Quantity
        String[] PC = new String[17]; // Pivot Cost
        String[] PP = new String[17]; // Pivot Price
        for (int k = 0; k < 17; k++) {
            PQ[k] = AQ[k][L];
            PC[k] = AC[k][L];
            PP[k] = AP[k][L];
        }
        int i = L;
        int j = R;
        String B; // Helps save values of Strings in order to make changes
        int C; // Helps save values of Integers in order to make changes
        while (i < j) {
            while (A[i] >= P && i < j) {
                i++;
            }
            while (A[j] < P) {
                j--;
            }
            if (i < j) {
                C = A[i]; // Changes values
                A[i] = A[j];
                A[j] = C;
                B = AN[i]; // Changes values with the values of the table
                AN[i] = AN[j];
                AN[j] = B;
                for (int k = 0; k < 17; k++) {
                    B = AQ[k][i];
                    AQ[k][i] = AQ[k][j];
                    AQ[k][j] = B;
                    B = AC[k][i];
                    AC[k][i] = AC[k][j];
                    AC[k][j] = B;
                    B = AP[k][i];
                    AP[k][i] = AP[k][j];
                    AP[k][j] = B;
                }
            }
        }
        A[L] = A[j]; // Changes values
        A[j] = P;
        AN[L] = AN[j]; // Changes values with the values of the table
        AN[j] = PN;
        for (int k = 0; k < 17; k++) {
            AQ[k][L] = AQ[k][j];
            AQ[k][j] = PQ[k];
            AC[k][L] = AC[k][j];
            AC[k][j] = PC[k];
            AP[k][L] = AP[k][j];
            AP[k][j] = PP[k];
        }
        if (L < (i - 1)) {
            intquicksortdes(A, L, (j - 1));
        }
        if ((j + 1) < R) {
            intquicksortdes(A, (j + 1), R);
        }
    }

    public static void doublequicksortasc(double A[], int L, int R) { // Quick sorts Doubles in the array A[] in ascending order
        double P = A[L]; // Pivot
        String PN = AN[L]; // Pivot Name
        String[] PQ = new String[17]; // Pivot Quantity
        String[] PC = new String[17]; // Pivot Cost
        String[] PP = new String[17]; // Pivot Price
        for (int k = 0; k < 17; k++) {
            PQ[k] = AQ[k][L];
            PC[k] = AC[k][L];
            PP[k] = AP[k][L];
        }
        int i = L;
        int j = R;
        String B; // Helps save values of Strings in order to make changes
        double C; // Helps save values of Doubles in order to make changes
        while (i < j) {
            while (A[i] <= P && i < j) {
                i++;
            }
            while (A[j] > P) {
                j--;
            }
            if (i < j) {
                C = A[i]; // Changes values
                A[i] = A[j];
                A[j] = C;
                B = AN[i]; // Changes values with the values of the table
                AN[i] = AN[j];
                AN[j] = B;
                for (int k = 0; k < 17; k++) {
                    B = AQ[k][i];
                    AQ[k][i] = AQ[k][j];
                    AQ[k][j] = B;
                    B = AC[k][i];
                    AC[k][i] = AC[k][j];
                    AC[k][j] = B;
                    B = AP[k][i];
                    AP[k][i] = AP[k][j];
                    AP[k][j] = B;
                }
            }
        }
        A[L] = A[j]; // Changes values
        A[j] = P;
        AN[L] = AN[j]; // Changes values with the values of the table
        AN[j] = PN;
        for (int k = 0; k < 17; k++) {
            AQ[k][L] = AQ[k][j];
            AQ[k][j] = PQ[k];
            AC[k][L] = AC[k][j];
            AC[k][j] = PC[k];
            AP[k][L] = AP[k][j];
            AP[k][j] = PP[k];
        }
        if (L < (j - 1)) {
            doublequicksortasc(A, L, (j - 1));
        }
        if ((j + 1) < R) {
            doublequicksortasc(A, (j + 1), R);
        }
    }

    public static void doublequicksortdes(double A[], int L, int R) { // Quick sorts Doubles in the array A[] in descending order
        double P = A[L]; // Pivot
        String PN = AN[L]; // Pivot Name
        String[] PQ = new String[17]; // Pivot Quantity
        String[] PC = new String[17]; // Pivot Cost
        String[] PP = new String[17]; // Pivot Price
        for (int k = 0; k < 17; k++) {
            PQ[k] = AQ[k][L];
            PC[k] = AC[k][L];
            PP[k] = AP[k][L];
        }
        int i = L;
        int j = R;
        String B; // Helps save values of Strings in order to make changes
        double C; // Helps save values of Doubles in order to make changes
        while (i < j) {
            while (A[i] >= P && i < j) {
                i++;
            }
            while (A[j] < P) {
                j--;
            }
            if (i < j) {
                C = A[i]; // Changes values
                A[i] = A[j];
                A[j] = C;
                B = AN[i]; // Changes values with the values of the table
                AN[i] = AN[j];
                AN[j] = B;
                for (int k = 0; k < 17; k++) {
                    B = AQ[k][i];
                    AQ[k][i] = AQ[k][j];
                    AQ[k][j] = B;
                    B = AC[k][i];
                    AC[k][i] = AC[k][j];
                    AC[k][j] = B;
                    B = AP[k][i];
                    AP[k][i] = AP[k][j];
                    AP[k][j] = B;
                }
            }
        }
        A[L] = A[j]; // Changes values
        A[j] = P;
        AN[L] = AN[j]; // Changes values with the values of the table
        AN[j] = PN;
        for (int k = 0; k < 17; k++) {
            AQ[k][L] = AQ[k][j];
            AQ[k][j] = PQ[k];
            AC[k][L] = AC[k][j];
            AC[k][j] = PC[k];
            AP[k][L] = AP[k][j];
            AP[k][j] = PP[k];
        }
        if (L < (i - 1)) {
            doublequicksortdes(A, L, (j - 1));
        }
        if ((j + 1) < R) {
            doublequicksortdes(A, (j + 1), R);
        }
    }

    public void action() { // Displays table information with current dishes in the current order with current time interval
        int one = 0; // Saves quantities from each month
        double two = 0; // Saves prices from each month
        double three = 0; // Saves individual costs from each month
        double four = 0; // Saves contributions from each month
        double five = 0; // Saves revenues from each month
        double six = 0; // Saves total costs from each month
        double seven = 0; // Saves profits from each month
        int x = 12 * (2014 - Integer.parseInt(jTextField3.getText())) + (8 - Integer.parseInt(jTextField2.getText())); // Translates initial date to column index
        int y = 12 * (2014 - Integer.parseInt(jTextField4.getText())) + (8 - Integer.parseInt(jTextField5.getText())); // Translates final date to column index
        if (x >= y && ((Integer.parseInt(jTextField3.getText()) == 2013 && Integer.parseInt(jTextField2.getText()) > 3 && Integer.parseInt(jTextField2.getText()) < 13) || (Integer.parseInt(jTextField3.getText()) == 2014 && Integer.parseInt(jTextField2.getText()) > 0 && Integer.parseInt(jTextField2.getText()) < 9)) && ((Integer.parseInt(jTextField4.getText()) == 2013 && Integer.parseInt(jTextField5.getText()) > 3 && Integer.parseInt(jTextField5.getText()) < 13) || (Integer.parseInt(jTextField4.getText()) == 2014 && Integer.parseInt(jTextField5.getText()) > 0 && Integer.parseInt(jTextField5.getText()) < 9))) {
            for (int i = 0; i < a; i++) {
                for (int j = y; j <= x; j++) {
                    one = one + Integer.parseInt(AQ[j][i]);
                    two = two + Double.parseDouble(AP[j][i]);
                    three = three + Double.parseDouble(AC[j][i]);
                    four = four + (Double.parseDouble(AP[j][i]) - Double.parseDouble(AC[j][i]));
                    five = five + Integer.parseInt(AQ[j][i]) * Double.parseDouble(AP[j][i]);
                    six = six + Integer.parseInt(AQ[j][i]) * Double.parseDouble(AC[j][i]);
                    seven = seven + (Integer.parseInt(AQ[j][i]) * (Double.parseDouble(AP[j][i]) - Double.parseDouble(AC[j][i])));
                }
                jTable1.setValueAt(one, i, 1); // Displays quantities
                jTable1.setValueAt(two / (x - y + 1), i, 2); // Displays average price
                jTable1.setValueAt(three / (x - y + 1), i, 3); // Displays average individual costs
                jTable1.setValueAt(four / (x - y + 1), i, 4); // Displays average contributions
                jTable1.setValueAt(five, i, 5); // Displays revenues
                jTable1.setValueAt(six, i, 6); // Displays total costs
                jTable1.setValueAt(seven, i, 7); // Displays profits
                one = 0;
                two = 0;
                three = 0;
                four = 0;
                five = 0;
                six = 0;
                seven = 0;
            }
        }
        else {
            Error obj = new Error(); // Calls Error program
            obj.setVisible(true); // Makes Error window visible
        }
    }

    /**
     * Creates new form Statistics
     */
    public Statistics() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Castellar", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LA ARCADA 1200");

        jTextField1.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Castellar", 1, 24)); // NOI18N
        jLabel2.setText("Statistics");

        jButton1.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Castellar", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jTable1.setFont(new java.awt.Font("Castellar", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Dish", "Quantity", "Price (Avg.)", "Ind. Cost (Avg.)", "Contribution (Avg.)", "Revenue", "Tot. Cost", "Profit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        jLabel3.setText("Initial Date (MM-YYYY):");

        jLabel4.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        jLabel4.setText("Final Date (MM-YYYY):");

        jButton4.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton4.setText("Enter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jTextField2.setToolTipText("");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jTextField3.setToolTipText("");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jTextField4.setToolTipText("");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jTextField5.setToolTipText("");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton6.setText("Dish Asc.");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton7.setText("Dish Des.");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton8.setText("Price Asc.");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton9.setText("Price Des.");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton10.setText("Quantity Asc.");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton11.setText("Quantity Des.");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton12.setText("Revenue Asc.");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton13.setText("Revenue Des.");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton14.setText("Ind. Cost Asc.");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton15.setText("Ind. Cost Des.");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton16.setText("Profit Asc.");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton17.setText("Profit Des.");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton18.setText("Contribution Asc.");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton19.setText("Contribution Des.");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton20.setText("Tot. Cost Asc.");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        jButton21.setText("Tot. Cost Des.");
        jButton21.setToolTipText("");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        jLabel7.setText("-");

        jLabel8.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        jLabel8.setText("-");

        jLabel5.setFont(new java.awt.Font("Castellar", 0, 15)); // NOI18N
        jLabel5.setText("Time Interval Between 04-2013 and 08-2014");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18)
                    .addComponent(jButton8)
                    .addComponent(jButton6)
                    .addComponent(jButton20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton21)
                    .addComponent(jButton7)
                    .addComponent(jButton9)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton14)
                            .addComponent(jButton10))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13)
                    .addComponent(jButton17)
                    .addComponent(jButton11)
                    .addComponent(jButton15)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton6)
                    .addComponent(jButton11)
                    .addComponent(jButton10)
                    .addComponent(jButton7)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8)
                        .addComponent(jButton15)
                        .addComponent(jButton14)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton12)
                    .addComponent(jButton13)
                    .addComponent(jButton18)
                    .addComponent(jButton19)
                    .addComponent(jLabel8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        c = 0; // Resets search counter
        for (int i = 0; i < 583; i++) { // Resets search results
            SN[i] = null;
            for (int j = 0; j < 17; j++) {
                SQ[j][i] = null;
                SC[j][i] = null;
                SP[j][i] = null;
            }
            if (Name[i].toLowerCase().contains(jTextField1.getText().toLowerCase())) { // Gets search results
                SN[c] = Name[i];
                for (int j = 0; j < 17; j++) {
                    SQ[j][c] = Quantity[j][i];
                    SC[j][c] = Cost[j][i];
                    SP[j][c] = Price[j][i];
                }
                c++; // Adds 1 to counter for each search result
            }
        }
        jList1.setListData(SN); // Displays list
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int b = 0; // Resets flag
        if (jList1.getSelectedIndex() > -1) { // Checks if dish is already in table
            for (int i = 0; i < a; i++) {
                if (SN[jList1.getSelectedIndex()].equals(AN[i])) {
                    b = 1; // If it is, raises flag
                }
            }
            if (b == 0) { // If it is not, adds dish to table
                AN[a] = SN[jList1.getSelectedIndex()];
                for (int i = 0; i < 17; i++) {
                    AQ[i][a] = SQ[i][jList1.getSelectedIndex()];
                    AC[i][a] = SC[i][jList1.getSelectedIndex()];
                    AP[i][a] = SP[i][jList1.getSelectedIndex()];
                }
                jTable1.setValueAt(AN[a], a, 0);
                a++; // Adds 1 to added counter
            }
            else {
                Warning obj = new Warning(); // Calls Warning program
                obj.setVisible(true); // Makes Warning window visible
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable1.getSelectedRow() > -1 && jTable1.getSelectedRow() < a) { // Checks row contains data
            for (int i = jTable1.getSelectedRow(); i < a; i++) { // Moves dishes one row up
                AN[i] = AN[i + 1];
                for (int j = 0; j < 17; j++) {
                    AQ[j][i] = AQ[j][i + 1];
                    AC[j][i] = AC[j][i + 1];
                    AP[j][i] = AP[j][i + 1];
                }
                for (int j = 0; j < 8; j++) {
                    jTable1.setValueAt(jTable1.getValueAt(i + 1, j), i, j);
                }
            }
            AN[a] = null; // Erases the last row
            jTable1.setValueAt(AN[a], a, 0);
            for (int i = 0; i < 8; i++) {
                jTable1.setValueAt(null, a, i);
            }
            a--; // Substracts 1 to added counter
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        a = 0; // Makes all variables 0 or null
        c = 0; // Resets search counter
        for (int i = 0; i < 583; i++) {
            SN[i] = null;
            AN[i] = null;
            for (int j = 0; j < 17; j++) {
                SQ[j][i] = null;
                SC[j][i] = null;
                SP[j][i] = null;
                AQ[j][i] = null;
                AC[j][i] = null;
                AP[j][i] = null;
            }
        }
        MainMenu obj = new MainMenu(); // Calls MainMenu program
        obj.setVisible(true); // Makes MainMenu window visible
        dispose(); // Closes Statistics window
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String[] Z = new String[a];
        for (int i = 0; i < a; i++) {
            Z[i] = jTable1.getValueAt(i, 0).toString();
        }
        stringquicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String[] Z = new String[a];
        for (int i = 0; i < a; i++) {
            Z[i] = jTable1.getValueAt(i, 0).toString();
        }
        stringquicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        doublequicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        doublequicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int[] Z = new int[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Integer.parseInt(jTable1.getValueAt(i, 1).toString());
        }
        intquicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int[] Z = new int[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Integer.parseInt(jTable1.getValueAt(i, 1).toString());
        }
        intquicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 5).toString());
        }
        doublequicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 5).toString());
        }
        doublequicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
        }
        doublequicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
        }
        doublequicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 7).toString());
        }
        doublequicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 7).toString());
        }
        doublequicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
        }
        doublequicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
        }
        doublequicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 6).toString());
        }
        doublequicksortasc(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        double[] Z = new double[a];
        for (int i = 0; i < a; i++) {
            Z[i] = Double.parseDouble(jTable1.getValueAt(i, 6).toString());
        }
        doublequicksortdes(Z, 0, a - 1);
        for (int i = 0; i < a; i++) {
            jTable1.setValueAt(AN[i], i, 0);
        }
        action();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Statistics().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}