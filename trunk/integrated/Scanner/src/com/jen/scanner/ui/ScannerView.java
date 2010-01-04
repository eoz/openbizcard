/*
 * ScannerView.java
 */

package com.jen.scanner.ui;

import com.hideoaki.scanner.db.manager.CardLocalManager;
import com.hideoaki.scanner.db.model.Card;
import com.hideoaki.scanner.db.model.Group;
import com.hideoaki.scanner.db.utils.ScannerDBException;
import com.jen.scanner.ui.util.DBFileFilter;
import com.jen.scanner.ui.util.JPGFileFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 * The application's main frame.
 */
public class ScannerView extends FrameView {

    public ScannerView(SingleFrameApplication app) {
        super(app);
  
        initComponents();
        
        menuTab.remove(6);
        menuTab.remove(5);
        menuTab.remove(4);
        menuTab.remove(3);
        menuTab.remove(2);
        menuTab.remove(1);
        
        try {
            localCardList = CardLocalManager.loadLocalCard(defaultcard.getAbsolutePath());
        } catch (ScannerDBException ex) {
            Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
        }
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();

        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ScannerApp.getApplication().getMainFrame();
            aboutBox = new ScannerAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ScannerApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        menuTab = new javax.swing.JTabbedPane();
        loginTab = new javax.swing.JPanel();
        loginP = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        usernameTf = new javax.swing.JTextField();
        pwdTf = new javax.swing.JPasswordField();
        usernameLb = new javax.swing.JLabel();
        pwdLb = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        settingPanel = new javax.swing.JPanel();
        databaseTf = new javax.swing.JTextField();
        databaseBtn = new javax.swing.JButton();
        setRefBtn = new javax.swing.JButton();
        scannerTab = new javax.swing.JPanel();
        upLeftT1 = new javax.swing.JPanel();
        nameLbT1 = new javax.swing.JLabel();
        titleLbT1 = new javax.swing.JLabel();
        companyLbT1 = new javax.swing.JLabel();
        stateLbT1 = new javax.swing.JLabel();
        codeLbT1 = new javax.swing.JLabel();
        mobileLbT1 = new javax.swing.JLabel();
        faxLbT1 = new javax.swing.JLabel();
        adsLbT1 = new javax.swing.JLabel();
        lastnameLbT1 = new javax.swing.JLabel();
        emailLbT1 = new javax.swing.JLabel();
        webLbT1 = new javax.swing.JLabel();
        cityLbT1 = new javax.swing.JLabel();
        countryLbT1 = new javax.swing.JLabel();
        phoneLbT1 = new javax.swing.JLabel();
        noteLbT1 = new javax.swing.JLabel();
        nameTfT1 = new javax.swing.JTextField();
        titleTfT1 = new javax.swing.JTextField();
        companyTfT1 = new javax.swing.JTextField();
        stateTfT1 = new javax.swing.JTextField();
        codeTfT1 = new javax.swing.JTextField();
        mobileTfT1 = new javax.swing.JTextField();
        faxTfT1 = new javax.swing.JTextField();
        lastnameTfT1 = new javax.swing.JTextField();
        emailTfT1 = new javax.swing.JTextField();
        webTfT1 = new javax.swing.JTextField();
        cityTfT1 = new javax.swing.JTextField();
        countryTfT1 = new javax.swing.JTextField();
        phoneTfT1 = new javax.swing.JTextField();
        adsPanel = new javax.swing.JPanel();
        adsScr = new javax.swing.JScrollPane();
        adsTaT1 = new javax.swing.JTextArea();
        notePanel = new javax.swing.JPanel();
        adsScr1 = new javax.swing.JScrollPane();
        noteTaT1 = new javax.swing.JTextArea();
        attachPanel = new javax.swing.JPanel();
        blankPanel0 = new javax.swing.JPanel();
        blankPanel1 = new javax.swing.JPanel();
        blankPanel2 = new javax.swing.JPanel();
        upRightT1 = new javax.swing.JPanel();
        scannerPanel = new javax.swing.JPanel();
        scannerLbT1 = new javax.swing.JLabel();
        scannerBtnT1 = new javax.swing.JButton();
        scannerCmbT1 = new javax.swing.JComboBox();
        brightPanelT1 = new javax.swing.JPanel();
        brightLbT1 = new javax.swing.JLabel();
        brightSldT1 = new javax.swing.JSlider();
        doubleSideBtnT1 = new javax.swing.JToggleButton();
        blackWhiteBtn = new javax.swing.JToggleButton();
        cropBtnT1 = new javax.swing.JToggleButton();
        rotateBtnT1 = new javax.swing.JButton();
        emailBtnT1 = new javax.swing.JButton();
        saveToDbBtnT1 = new javax.swing.JButton();
        scanBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        low = new javax.swing.JPanel();
        lowLeftT1 = new javax.swing.JPanel();
        frontPanelT1 = new javax.swing.JPanel();
        frontTfT1 = new javax.swing.JTextField();
        frontBtnT1 = new javax.swing.JButton();
        frontLbT1 = new JLabel();
        frontSpT1 = new javax.swing.JScrollPane(frontLbT1);
        lowRightT1 = new javax.swing.JPanel();
        backTfT1 = new javax.swing.JTextField();
        backBtnT1 = new javax.swing.JButton();
        backLbT1 = new JLabel();
        backSpT1 = new javax.swing.JScrollPane(backLbT1);
        queryTab = new javax.swing.JPanel();
        upLeftT2 = new javax.swing.JPanel();
        nameLbT2 = new javax.swing.JLabel();
        titleLbT2 = new javax.swing.JLabel();
        companyLbT2 = new javax.swing.JLabel();
        stateLbT2 = new javax.swing.JLabel();
        codeLbT2 = new javax.swing.JLabel();
        mobileLbT2 = new javax.swing.JLabel();
        faxLbT2 = new javax.swing.JLabel();
        adsLbT2 = new javax.swing.JLabel();
        lastnameLbT2 = new javax.swing.JLabel();
        emailLbT2 = new javax.swing.JLabel();
        webLbT2 = new javax.swing.JLabel();
        cityLbT2 = new javax.swing.JLabel();
        countryLbT2 = new javax.swing.JLabel();
        phoneLbT2 = new javax.swing.JLabel();
        nameTfT2 = new javax.swing.JTextField();
        blankPanel3 = new javax.swing.JPanel();
        lastnameTfT2 = new javax.swing.JTextField();
        titleTfT2 = new javax.swing.JTextField();
        emailTfT2 = new javax.swing.JTextField();
        companyTfT2 = new javax.swing.JTextField();
        webTfT2 = new javax.swing.JTextField();
        stateTfT2 = new javax.swing.JTextField();
        cityTfT2 = new javax.swing.JTextField();
        codeTfT2 = new javax.swing.JTextField();
        countryTfT2 = new javax.swing.JTextField();
        mobileTfT2 = new javax.swing.JTextField();
        phoneTfT2 = new javax.swing.JTextField();
        faxTfT2 = new javax.swing.JTextField();
        adsPanel1 = new javax.swing.JPanel();
        adsScr2 = new javax.swing.JScrollPane();
        adsTaT2 = new javax.swing.JTextArea();
        blankPanel4 = new javax.swing.JPanel();
        searchT2 = new javax.swing.JButton();
        blankPanel0T2 = new javax.swing.JPanel();
        blankPanel1T2 = new javax.swing.JPanel();
        upRightT2 = new javax.swing.JPanel();
        exportPanelT2 = new javax.swing.JPanel();
        exportLbT2 = new javax.swing.JLabel();
        exportTfT2 = new javax.swing.JTextField();
        exBrowseBtnT2 = new javax.swing.JButton();
        exportBtnT2 = new javax.swing.JButton();
        genSearchPanelT2 = new javax.swing.JPanel();
        genSearchLbT2 = new javax.swing.JLabel();
        genSearchTfT2 = new javax.swing.JTextField();
        genSearchT2 = new javax.swing.JButton();
        quickPanelT2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnPanel0T2 = new javax.swing.JPanel();
        engBtn0T2 = new javax.swing.JToggleButton();
        engBtn1T2 = new javax.swing.JToggleButton();
        engBtn2T2 = new javax.swing.JToggleButton();
        engBtn3T2 = new javax.swing.JToggleButton();
        engBtn4T2 = new javax.swing.JToggleButton();
        engBtn5T2 = new javax.swing.JToggleButton();
        thBtn0T2 = new javax.swing.JToggleButton();
        thBtn1T2 = new javax.swing.JToggleButton();
        thBtn2T2 = new javax.swing.JToggleButton();
        thBtn3T2 = new javax.swing.JToggleButton();
        thBtn4T2 = new javax.swing.JToggleButton();
        thBtn5T2 = new javax.swing.JToggleButton();
        btnPanel1T2 = new javax.swing.JPanel();
        engBtn6T2 = new javax.swing.JToggleButton();
        engBtn7T2 = new javax.swing.JToggleButton();
        engBtn8T2 = new javax.swing.JToggleButton();
        thBtn8T2 = new javax.swing.JToggleButton();
        thBtn7T2 = new javax.swing.JToggleButton();
        thBtn6T2 = new javax.swing.JToggleButton();
        lowPanelT2 = new javax.swing.JPanel();
        tablePanelT2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        importTableT2 = new javax.swing.JTable();
        deleteEditPanelT2 = new javax.swing.JPanel();
        deletedBtnT2 = new javax.swing.JButton();
        editBtnT2 = new javax.swing.JButton();
        resultTab = new javax.swing.JPanel();
        upLeftT3 = new javax.swing.JPanel();
        nameLbT3 = new javax.swing.JLabel();
        titleLbT3 = new javax.swing.JLabel();
        companyLbT3 = new javax.swing.JLabel();
        stateLbT3 = new javax.swing.JLabel();
        codeLbT3 = new javax.swing.JLabel();
        mobileLbT3 = new javax.swing.JLabel();
        faxLbT3 = new javax.swing.JLabel();
        adsLbT3 = new javax.swing.JLabel();
        lastnameLbT3 = new javax.swing.JLabel();
        emailLbT3 = new javax.swing.JLabel();
        webLbT3 = new javax.swing.JLabel();
        cityLbT3 = new javax.swing.JLabel();
        countryLbT3 = new javax.swing.JLabel();
        phoneLbT3 = new javax.swing.JLabel();
        noteLbT2 = new javax.swing.JLabel();
        nameTfT3 = new javax.swing.JTextField();
        titleTfT3 = new javax.swing.JTextField();
        companyTfT3 = new javax.swing.JTextField();
        stateTfT3 = new javax.swing.JTextField();
        codeTfT3 = new javax.swing.JTextField();
        mobileTfT3 = new javax.swing.JTextField();
        faxTfT3 = new javax.swing.JTextField();
        lastnameTfT3 = new javax.swing.JTextField();
        emailTfT3 = new javax.swing.JTextField();
        webTfT3 = new javax.swing.JTextField();
        cityTfT3 = new javax.swing.JTextField();
        countryTfT3 = new javax.swing.JTextField();
        phoneTfT3 = new javax.swing.JTextField();
        adsPanel2 = new javax.swing.JPanel();
        adsScr3 = new javax.swing.JScrollPane();
        adsTaT3 = new javax.swing.JTextArea();
        notePanel1 = new javax.swing.JPanel();
        adsScr4 = new javax.swing.JScrollPane();
        noteTaT3 = new javax.swing.JTextArea();
        attachPanel1 = new javax.swing.JPanel();
        blankPanel5 = new javax.swing.JPanel();
        blankPanel6 = new javax.swing.JPanel();
        blankPanel7 = new javax.swing.JPanel();
        blankPanel0T3 = new javax.swing.JPanel();
        upRightT3 = new javax.swing.JPanel();
        scannerPanel2 = new javax.swing.JPanel();
        groupLbT2 = new javax.swing.JLabel();
        grouprCmbT2 = new javax.swing.JComboBox();
        brightPanelT3 = new javax.swing.JPanel();
        brightLbT3 = new javax.swing.JLabel();
        brightSldT3 = new javax.swing.JSlider();
        blackWhiteBtnT3 = new javax.swing.JToggleButton();
        cropBtnT3 = new javax.swing.JToggleButton();
        rotateBtnT3 = new javax.swing.JButton();
        emailBtnT3 = new javax.swing.JButton();
        saveBtnT3 = new javax.swing.JButton();
        lowT3 = new javax.swing.JPanel();
        lowLeftT3 = new javax.swing.JPanel();
        frontTfT3 = new javax.swing.JTextField();
        frontBtnT3 = new javax.swing.JButton();
        frontLbT3 = new JLabel();
        frontSpT3 = new javax.swing.JScrollPane(frontLbT3);
        lowRightT3 = new javax.swing.JPanel();
        backTfT3 = new javax.swing.JTextField();
        backBtnT3 = new javax.swing.JButton();
        backLbT3 = new JLabel();
        backSpT3 = new javax.swing.JScrollPane(backLbT3);
        upLabelT3 = new javax.swing.JPanel();
        idLbT3 = new javax.swing.JLabel();
        idNameLbT3 = new javax.swing.JLabel();
        importExportTab = new javax.swing.JPanel();
        importPanel = new javax.swing.JPanel();
        importDesLbT4 = new javax.swing.JLabel();
        importPanelT4 = new javax.swing.JPanel();
        importLbT4 = new javax.swing.JLabel();
        importTfT4 = new javax.swing.JTextField();
        importBrowseBtnT4 = new javax.swing.JButton();
        importBtnT4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        importTableT4 = new javax.swing.JTable();
        exportPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        exportLbT4 = new javax.swing.JLabel();
        exportTfT4 = new javax.swing.JTextField();
        browseExportBtnT4 = new javax.swing.JButton();
        exportBtnT4 = new javax.swing.JButton();
        blankPanelT4 = new javax.swing.JPanel();
        groupTab = new javax.swing.JPanel();
        editGroupPanelT5 = new javax.swing.JPanel();
        importPanelT5 = new javax.swing.JPanel();
        groupSearchLbT5 = new javax.swing.JLabel();
        groupSearchTfT5 = new javax.swing.JTextField();
        groupSearchBtnT5 = new javax.swing.JButton();
        groupResultPanelT5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultTableT5 = new javax.swing.JTable();
        resultLbT5 = new javax.swing.JLabel();
        addNewGroupPanelT5 = new javax.swing.JPanel();
        groupPanelT5 = new javax.swing.JPanel();
        groupLb = new javax.swing.JLabel();
        groupTfT5 = new javax.swing.JTextField();
        groupBtnT5 = new javax.swing.JButton();
        blankPanelT5 = new javax.swing.JPanel();
        userEditTab = new javax.swing.JPanel();
        addUserPanelT6 = new javax.swing.JPanel();
        usernameLbT6 = new javax.swing.JLabel();
        pwdLbT6 = new javax.swing.JLabel();
        groupLbT6 = new javax.swing.JLabel();
        usernameTfT6 = new javax.swing.JTextField();
        pwdTfT6 = new javax.swing.JPasswordField();
        groupT6 = new javax.swing.JComboBox();
        addBtnT6 = new javax.swing.JButton();
        editUserPanelT6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnPanel0T3 = new javax.swing.JPanel();
        engBtn0T3 = new javax.swing.JToggleButton();
        engBtn1T3 = new javax.swing.JToggleButton();
        engBtn2T3 = new javax.swing.JToggleButton();
        engBtn3T3 = new javax.swing.JToggleButton();
        engBtn4T3 = new javax.swing.JToggleButton();
        engBtn5T3 = new javax.swing.JToggleButton();
        thBtn0T3 = new javax.swing.JToggleButton();
        thBtn1T3 = new javax.swing.JToggleButton();
        thBtn2T3 = new javax.swing.JToggleButton();
        thBtn3T3 = new javax.swing.JToggleButton();
        thBtn4T3 = new javax.swing.JToggleButton();
        thBtn5T3 = new javax.swing.JToggleButton();
        engBtn6T3 = new javax.swing.JToggleButton();
        engBtn7T3 = new javax.swing.JToggleButton();
        engBtn8T3 = new javax.swing.JToggleButton();
        thBtn8T3 = new javax.swing.JToggleButton();
        thBtn7T3 = new javax.swing.JToggleButton();
        thBtn6T3 = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        editUserTbT6 = new javax.swing.JTable();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        databaseLb = new javax.swing.JLabel();
        databaseNameLb = new javax.swing.JLabel();

        mainPanel.setMaximumSize(new java.awt.Dimension(704, 660));
        mainPanel.setMinimumSize(new java.awt.Dimension(704, 660));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(704, 660));

        menuTab.setMaximumSize(null);
        menuTab.setName("menuTab"); // NOI18N
        menuTab.setPreferredSize(new java.awt.Dimension(704, 660));

        loginTab.setToolTipText(""); // NOI18N
        loginTab.setName("loginTab"); // NOI18N
        loginTab.setLayout(new java.awt.GridBagLayout());

        loginP.setMaximumSize(new java.awt.Dimension(350, 300));
        loginP.setMinimumSize(new java.awt.Dimension(350, 250));
        loginP.setName("loginP"); // NOI18N
        loginP.setPreferredSize(new java.awt.Dimension(350, 300));
        loginP.setLayout(new java.awt.GridBagLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.jen.scanner.ui.ScannerApp.class).getContext().getResourceMap(ScannerView.class);
        loginPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("loginPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("loginPanel.border.titleColor"))); // NOI18N
        loginPanel.setMaximumSize(new java.awt.Dimension(236, 160));
        loginPanel.setMinimumSize(new java.awt.Dimension(236, 160));
        loginPanel.setName("loginPanel"); // NOI18N
        loginPanel.setPreferredSize(new java.awt.Dimension(236, 160));

        usernameTf.setColumns(10);
        usernameTf.setText(resourceMap.getString("usernameTf.text")); // NOI18N
        usernameTf.setName("usernameTf"); // NOI18N

        pwdTf.setColumns(10);
        pwdTf.setText(resourceMap.getString("pwdTf.text")); // NOI18N
        pwdTf.setName("pwdTf"); // NOI18N

        usernameLb.setText(resourceMap.getString("usernameLb.text")); // NOI18N
        usernameLb.setName("usernameLb"); // NOI18N

        pwdLb.setText(resourceMap.getString("pwdLb.text")); // NOI18N
        pwdLb.setName("pwdLb"); // NOI18N

        loginBtn.setText(resourceMap.getString("loginBtn.text")); // NOI18N
        loginBtn.setName("loginBtn"); // NOI18N
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        logoutBtn.setText(resourceMap.getString("logoutBtn.text")); // NOI18N
        logoutBtn.setEnabled(false);
        logoutBtn.setName("logoutBtn"); // NOI18N
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loginBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutBtn))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwdLb)
                            .addComponent(usernameLb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTf, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(pwdTf, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addGap(38, 38, 38))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwdLb)
                    .addComponent(pwdTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn)
                    .addComponent(logoutBtn))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        loginP.add(loginPanel, gridBagConstraints);
        loginPanel.getAccessibleContext().setAccessibleName(resourceMap.getString("loginPanel.AccessibleContext.accessibleName")); // NOI18N
        loginPanel.getAccessibleContext().setAccessibleDescription(resourceMap.getString("loginPanel.AccessibleContext.accessibleDescription")); // NOI18N

        settingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("settingPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("settingPanel.border.titleColor"))); // NOI18N
        settingPanel.setMaximumSize(new java.awt.Dimension(236, 100));
        settingPanel.setMinimumSize(new java.awt.Dimension(236, 100));
        settingPanel.setName("settingPanel"); // NOI18N
        settingPanel.setPreferredSize(new java.awt.Dimension(236, 100));
        settingPanel.setLayout(new java.awt.GridBagLayout());

        databaseTf.setText(resourceMap.getString("databaseTf.text")); // NOI18N
        databaseTf.setMaximumSize(new java.awt.Dimension(100, 20));
        databaseTf.setMinimumSize(new java.awt.Dimension(100, 20));
        databaseTf.setName("databaseTf"); // NOI18N
        databaseTf.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        settingPanel.add(databaseTf, gridBagConstraints);

        databaseBtn.setText(resourceMap.getString("databaseBtn.text")); // NOI18N
        databaseBtn.setName("databaseBtn"); // NOI18N
        databaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databaseBtnActionPerformed(evt);
            }
        });
        settingPanel.add(databaseBtn, new java.awt.GridBagConstraints());

        setRefBtn.setText(resourceMap.getString("setRefBtn.text")); // NOI18N
        setRefBtn.setMaximumSize(new java.awt.Dimension(100, 23));
        setRefBtn.setMinimumSize(new java.awt.Dimension(100, 23));
        setRefBtn.setName("setRefBtn"); // NOI18N
        setRefBtn.setOpaque(false);
        setRefBtn.setPreferredSize(new java.awt.Dimension(100, 23));
        setRefBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRefBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        settingPanel.add(setRefBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        loginP.add(settingPanel, gridBagConstraints);

        loginTab.add(loginP, new java.awt.GridBagConstraints());

        menuTab.addTab(resourceMap.getString("loginTab.TabConstraints.tabTitle"), loginTab); // NOI18N
        loginTab.getAccessibleContext().setAccessibleDescription(resourceMap.getString("loginTab.AccessibleContext.accessibleDescription")); // NOI18N

        scannerTab.setName("scannerTab"); // NOI18N
        scannerTab.setLayout(new java.awt.GridBagLayout());

        upLeftT1.setName("upLeftT1"); // NOI18N
        upLeftT1.setPreferredSize(new java.awt.Dimension(350, 250));
        upLeftT1.setLayout(new java.awt.GridBagLayout());

        nameLbT1.setText(resourceMap.getString("nameLbT1.text")); // NOI18N
        nameLbT1.setName("nameLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(nameLbT1, gridBagConstraints);

        titleLbT1.setText(resourceMap.getString("titleLbT1.text")); // NOI18N
        titleLbT1.setName("titleLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(titleLbT1, gridBagConstraints);

        companyLbT1.setText(resourceMap.getString("companyLbT1.text")); // NOI18N
        companyLbT1.setName("companyLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(companyLbT1, gridBagConstraints);

        stateLbT1.setText(resourceMap.getString("stateLbT1.text")); // NOI18N
        stateLbT1.setName("stateLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(stateLbT1, gridBagConstraints);

        codeLbT1.setText(resourceMap.getString("codeLbT1.text")); // NOI18N
        codeLbT1.setName("codeLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(codeLbT1, gridBagConstraints);

        mobileLbT1.setText(resourceMap.getString("mobileLbT1.text")); // NOI18N
        mobileLbT1.setName("mobileLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(mobileLbT1, gridBagConstraints);

        faxLbT1.setText(resourceMap.getString("faxLbT1.text")); // NOI18N
        faxLbT1.setName("faxLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(faxLbT1, gridBagConstraints);

        adsLbT1.setText(resourceMap.getString("adsLbT1.text")); // NOI18N
        adsLbT1.setName("adsLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(adsLbT1, gridBagConstraints);

        lastnameLbT1.setText(resourceMap.getString("lastnameLbT1.text")); // NOI18N
        lastnameLbT1.setName("lastnameLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        upLeftT1.add(lastnameLbT1, gridBagConstraints);

        emailLbT1.setText(resourceMap.getString("emailLbT1.text")); // NOI18N
        emailLbT1.setName("emailLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(emailLbT1, gridBagConstraints);

        webLbT1.setText(resourceMap.getString("webLbT1.text")); // NOI18N
        webLbT1.setName("webLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(webLbT1, gridBagConstraints);

        cityLbT1.setText(resourceMap.getString("cityLbT1.text")); // NOI18N
        cityLbT1.setName("cityLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(cityLbT1, gridBagConstraints);

        countryLbT1.setText(resourceMap.getString("countryLbT1.text")); // NOI18N
        countryLbT1.setName("countryLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(countryLbT1, gridBagConstraints);

        phoneLbT1.setText(resourceMap.getString("phoneLbT1.text")); // NOI18N
        phoneLbT1.setName("phoneLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(phoneLbT1, gridBagConstraints);

        noteLbT1.setText(resourceMap.getString("noteLbT1.text")); // NOI18N
        noteLbT1.setName("noteLbT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT1.add(noteLbT1, gridBagConstraints);

        nameTfT1.setColumns(10);
        nameTfT1.setText(resourceMap.getString("nameTfT1.text")); // NOI18N
        nameTfT1.setName("nameTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(nameTfT1, gridBagConstraints);

        titleTfT1.setColumns(10);
        titleTfT1.setText(resourceMap.getString("titleTfT1.text")); // NOI18N
        titleTfT1.setName("titleTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(titleTfT1, gridBagConstraints);

        companyTfT1.setColumns(10);
        companyTfT1.setText(resourceMap.getString("companyTfT1.text")); // NOI18N
        companyTfT1.setName("companyTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(companyTfT1, gridBagConstraints);

        stateTfT1.setColumns(10);
        stateTfT1.setText(resourceMap.getString("stateTfT1.text")); // NOI18N
        stateTfT1.setName("stateTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(stateTfT1, gridBagConstraints);

        codeTfT1.setColumns(10);
        codeTfT1.setText(resourceMap.getString("codeTfT1.text")); // NOI18N
        codeTfT1.setName("codeTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(codeTfT1, gridBagConstraints);

        mobileTfT1.setColumns(10);
        mobileTfT1.setText(resourceMap.getString("mobileTfT1.text")); // NOI18N
        mobileTfT1.setName("mobileTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(mobileTfT1, gridBagConstraints);

        faxTfT1.setColumns(10);
        faxTfT1.setText(resourceMap.getString("faxTfT1.text")); // NOI18N
        faxTfT1.setName("faxTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(faxTfT1, gridBagConstraints);

        lastnameTfT1.setColumns(10);
        lastnameTfT1.setName("lastnameTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(lastnameTfT1, gridBagConstraints);

        emailTfT1.setColumns(10);
        emailTfT1.setName("emailTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(emailTfT1, gridBagConstraints);

        webTfT1.setColumns(10);
        webTfT1.setName("webTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(webTfT1, gridBagConstraints);

        cityTfT1.setColumns(10);
        cityTfT1.setName("cityTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(cityTfT1, gridBagConstraints);

        countryTfT1.setColumns(10);
        countryTfT1.setName("countryTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(countryTfT1, gridBagConstraints);

        phoneTfT1.setColumns(10);
        phoneTfT1.setName("phoneTfT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT1.add(phoneTfT1, gridBagConstraints);

        adsPanel.setName("adsPanel"); // NOI18N
        adsPanel.setPreferredSize(new java.awt.Dimension(99, 70));

        adsScr.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        adsScr.setHorizontalScrollBar(null);
        adsScr.setName("adsScr"); // NOI18N

        adsTaT1.setColumns(30);
        adsTaT1.setFont(resourceMap.getFont("adsTaT1.font")); // NOI18N
        adsTaT1.setRows(3);
        adsTaT1.setAutoscrolls(false);
        adsTaT1.setName("adsTaT1"); // NOI18N
        adsScr.setViewportView(adsTaT1);

        javax.swing.GroupLayout adsPanelLayout = new javax.swing.GroupLayout(adsPanel);
        adsPanel.setLayout(adsPanelLayout);
        adsPanelLayout.setHorizontalGroup(
            adsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adsScr, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        adsPanelLayout.setVerticalGroup(
            adsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adsScr, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        upLeftT1.add(adsPanel, gridBagConstraints);

        notePanel.setName("notePanel"); // NOI18N
        notePanel.setPreferredSize(new java.awt.Dimension(120, 70));

        adsScr1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        adsScr1.setHorizontalScrollBar(null);
        adsScr1.setName("adsScr1"); // NOI18N

        noteTaT1.setColumns(30);
        noteTaT1.setFont(resourceMap.getFont("noteTaT1.font")); // NOI18N
        noteTaT1.setRows(3);
        noteTaT1.setAutoscrolls(false);
        noteTaT1.setName("noteTaT1"); // NOI18N
        adsScr1.setViewportView(noteTaT1);

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanelLayout.createSequentialGroup()
                .addComponent(adsScr1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        notePanelLayout.setVerticalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adsScr1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        upLeftT1.add(notePanel, gridBagConstraints);

        attachPanel.setName("attachPanel"); // NOI18N
        attachPanel.setPreferredSize(new java.awt.Dimension(125, 20));

        javax.swing.GroupLayout attachPanelLayout = new javax.swing.GroupLayout(attachPanel);
        attachPanel.setLayout(attachPanelLayout);
        attachPanelLayout.setHorizontalGroup(
            attachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );
        attachPanelLayout.setVerticalGroup(
            attachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        upLeftT1.add(attachPanel, gridBagConstraints);

        blankPanel0.setName("blankPanel0"); // NOI18N

        javax.swing.GroupLayout blankPanel0Layout = new javax.swing.GroupLayout(blankPanel0);
        blankPanel0.setLayout(blankPanel0Layout);
        blankPanel0Layout.setHorizontalGroup(
            blankPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        blankPanel0Layout.setVerticalGroup(
            blankPanel0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        upLeftT1.add(blankPanel0, gridBagConstraints);

        blankPanel1.setName("blankPanel1"); // NOI18N

        javax.swing.GroupLayout blankPanel1Layout = new javax.swing.GroupLayout(blankPanel1);
        blankPanel1.setLayout(blankPanel1Layout);
        blankPanel1Layout.setHorizontalGroup(
            blankPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        blankPanel1Layout.setVerticalGroup(
            blankPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        upLeftT1.add(blankPanel1, gridBagConstraints);

        blankPanel2.setName("blankPanel2"); // NOI18N

        javax.swing.GroupLayout blankPanel2Layout = new javax.swing.GroupLayout(blankPanel2);
        blankPanel2.setLayout(blankPanel2Layout);
        blankPanel2Layout.setHorizontalGroup(
            blankPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        blankPanel2Layout.setVerticalGroup(
            blankPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        upLeftT1.add(blankPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        scannerTab.add(upLeftT1, gridBagConstraints);

        upRightT1.setMinimumSize(new java.awt.Dimension(250, 250));
        upRightT1.setName("upRightT1"); // NOI18N
        upRightT1.setPreferredSize(new java.awt.Dimension(250, 250));
        upRightT1.setLayout(new java.awt.GridBagLayout());

        scannerPanel.setName("scannerPanel"); // NOI18N

        scannerLbT1.setText(resourceMap.getString("scannerLbT1.text")); // NOI18N
        scannerLbT1.setName("scannerLbT1"); // NOI18N

        scannerBtnT1.setText(resourceMap.getString("scannerBtnT1.text")); // NOI18N
        scannerBtnT1.setMaximumSize(new java.awt.Dimension(90, 23));
        scannerBtnT1.setName("scannerBtnT1"); // NOI18N

        scannerCmbT1.setName("scannerCmbT1"); // NOI18N

        javax.swing.GroupLayout scannerPanelLayout = new javax.swing.GroupLayout(scannerPanel);
        scannerPanel.setLayout(scannerPanelLayout);
        scannerPanelLayout.setHorizontalGroup(
            scannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scannerPanelLayout.createSequentialGroup()
                .addComponent(scannerLbT1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scannerCmbT1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scannerBtnT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        scannerPanelLayout.setVerticalGroup(
            scannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(scannerLbT1)
                .addComponent(scannerBtnT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(scannerCmbT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        upRightT1.add(scannerPanel, gridBagConstraints);

        brightPanelT1.setMinimumSize(new java.awt.Dimension(242, 40));
        brightPanelT1.setName("brightPanelT1"); // NOI18N
        brightPanelT1.setPreferredSize(new java.awt.Dimension(242, 40));
        brightPanelT1.setRequestFocusEnabled(false);
        brightPanelT1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        brightLbT1.setText(resourceMap.getString("brightLbT1.text")); // NOI18N
        brightLbT1.setName("brightLbT1"); // NOI18N
        brightPanelT1.add(brightLbT1);

        brightSldT1.setMajorTickSpacing(1);
        brightSldT1.setMaximum(5);
        brightSldT1.setMinimum(1);
        brightSldT1.setMinorTickSpacing(1);
        brightSldT1.setPaintLabels(true);
        brightSldT1.setSnapToTicks(true);
        brightSldT1.setValue(3);
        brightSldT1.setMaximumSize(new java.awt.Dimension(32767, 35));
        brightSldT1.setMinimumSize(new java.awt.Dimension(50, 35));
        brightSldT1.setName("brightSldT1"); // NOI18N
        brightSldT1.setPreferredSize(new java.awt.Dimension(150, 35));
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 1 ), new JLabel("1") );
        labelTable.put( new Integer( 2 ), new JLabel("2") );
        labelTable.put( new Integer( 3 ), new JLabel("3") );
        labelTable.put( new Integer( 4 ), new JLabel("4") );
        labelTable.put( new Integer( 5 ), new JLabel("5") );
        brightSldT1.setLabelTable( labelTable );
        brightPanelT1.add(brightSldT1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        upRightT1.add(brightPanelT1, gridBagConstraints);

        doubleSideBtnT1.setText(resourceMap.getString("doubleSideBtnT1.text")); // NOI18N
        doubleSideBtnT1.setName("doubleSideBtnT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT1.add(doubleSideBtnT1, gridBagConstraints);

        blackWhiteBtn.setText(resourceMap.getString("blackWhiteBtn.text")); // NOI18N
        blackWhiteBtn.setName("blackWhiteBtn"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT1.add(blackWhiteBtn, gridBagConstraints);

        cropBtnT1.setText(resourceMap.getString("cropBtnT1.text")); // NOI18N
        cropBtnT1.setName("cropBtnT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT1.add(cropBtnT1, gridBagConstraints);

        rotateBtnT1.setText(resourceMap.getString("rotateBtnT1.text")); // NOI18N
        rotateBtnT1.setMaximumSize(new java.awt.Dimension(110, 23));
        rotateBtnT1.setMinimumSize(new java.awt.Dimension(110, 23));
        rotateBtnT1.setName("rotateBtnT1"); // NOI18N
        rotateBtnT1.setPreferredSize(new java.awt.Dimension(110, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 1, 0);
        upRightT1.add(rotateBtnT1, gridBagConstraints);

        emailBtnT1.setText(resourceMap.getString("emailBtnT1.text")); // NOI18N
        emailBtnT1.setName("emailBtnT1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 1, 0);
        upRightT1.add(emailBtnT1, gridBagConstraints);

        saveToDbBtnT1.setText(resourceMap.getString("saveToDbBtnT1.text")); // NOI18N
        saveToDbBtnT1.setName("saveToDbBtnT1"); // NOI18N
        saveToDbBtnT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToDbBtnT1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT1.add(saveToDbBtnT1, gridBagConstraints);

        scanBtn.setText(resourceMap.getString("scanBtn.text")); // NOI18N
        scanBtn.setName("scanBtn"); // NOI18N
        scanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT1.add(scanBtn, gridBagConstraints);

        saveBtn.setText(resourceMap.getString("saveBtn.text")); // NOI18N
        saveBtn.setName("saveBtn"); // NOI18N
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT1.add(saveBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        scannerTab.add(upRightT1, gridBagConstraints);

        low.setMaximumSize(null);
        low.setName("low"); // NOI18N
        low.setPreferredSize(new java.awt.Dimension(200, 300));
        low.setLayout(new java.awt.GridLayout(1, 2));

        lowLeftT1.setMaximumSize(null);
        lowLeftT1.setName("lowLeftT1"); // NOI18N

        frontPanelT1.setMaximumSize(null);
        frontPanelT1.setMinimumSize(new java.awt.Dimension(100, 50));
        frontPanelT1.setName("frontPanelT1"); // NOI18N
        frontPanelT1.setPreferredSize(new java.awt.Dimension(300, 50));

        frontTfT1.setText(resourceMap.getString("frontTfT1.text")); // NOI18N
        frontTfT1.setName("frontTfT1"); // NOI18N

        frontBtnT1.setText(resourceMap.getString("frontBtnT1.text")); // NOI18N
        frontBtnT1.setName("frontBtnT1"); // NOI18N
        frontBtnT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frontBtnT1ActionPerformed(evt);
            }
        });

        frontSpT1.setName("frontSpT1"); // NOI18N
        frontSpT1.setPreferredSize(new java.awt.Dimension(100, 260));

        javax.swing.GroupLayout frontPanelT1Layout = new javax.swing.GroupLayout(frontPanelT1);
        frontPanelT1.setLayout(frontPanelT1Layout);
        frontPanelT1Layout.setHorizontalGroup(
            frontPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frontPanelT1Layout.createSequentialGroup()
                .addGroup(frontPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frontPanelT1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(frontTfT1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frontBtnT1))
                    .addComponent(frontSpT1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                .addContainerGap())
        );
        frontPanelT1Layout.setVerticalGroup(
            frontPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frontPanelT1Layout.createSequentialGroup()
                .addGroup(frontPanelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frontTfT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frontBtnT1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frontSpT1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout lowLeftT1Layout = new javax.swing.GroupLayout(lowLeftT1);
        lowLeftT1.setLayout(lowLeftT1Layout);
        lowLeftT1Layout.setHorizontalGroup(
            lowLeftT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frontPanelT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        lowLeftT1Layout.setVerticalGroup(
            lowLeftT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frontPanelT1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        low.add(lowLeftT1);

        lowRightT1.setMaximumSize(null);
        lowRightT1.setName("lowRightT1"); // NOI18N

        backTfT1.setText(resourceMap.getString("backTfT1.text")); // NOI18N
        backTfT1.setName("backTfT1"); // NOI18N

        backBtnT1.setText(resourceMap.getString("backBtnT1.text")); // NOI18N
        backBtnT1.setName("backBtnT1"); // NOI18N
        backBtnT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnT1ActionPerformed(evt);
            }
        });

        backSpT1.setName("backSpT1"); // NOI18N
        backSpT1.setPreferredSize(new java.awt.Dimension(100, 260));

        javax.swing.GroupLayout lowRightT1Layout = new javax.swing.GroupLayout(lowRightT1);
        lowRightT1.setLayout(lowRightT1Layout);
        lowRightT1Layout.setHorizontalGroup(
            lowRightT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowRightT1Layout.createSequentialGroup()
                .addGroup(lowRightT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lowRightT1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(backTfT1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBtnT1))
                    .addGroup(lowRightT1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backSpT1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lowRightT1Layout.setVerticalGroup(
            lowRightT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowRightT1Layout.createSequentialGroup()
                .addGroup(lowRightT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backTfT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtnT1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backSpT1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        low.add(lowRightT1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        scannerTab.add(low, gridBagConstraints);

        menuTab.addTab(resourceMap.getString("scannerTab.TabConstraints.tabTitle"), scannerTab); // NOI18N

        queryTab.setName("queryTab"); // NOI18N
        queryTab.setLayout(new java.awt.GridBagLayout());

        upLeftT2.setName("upLeftT2"); // NOI18N
        upLeftT2.setPreferredSize(new java.awt.Dimension(330, 250));
        upLeftT2.setLayout(new java.awt.GridBagLayout());

        nameLbT2.setText(resourceMap.getString("nameLbT2.text")); // NOI18N
        nameLbT2.setName("nameLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(nameLbT2, gridBagConstraints);

        titleLbT2.setText(resourceMap.getString("titleLbT2.text")); // NOI18N
        titleLbT2.setName("titleLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(titleLbT2, gridBagConstraints);

        companyLbT2.setText(resourceMap.getString("companyLbT2.text")); // NOI18N
        companyLbT2.setName("companyLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(companyLbT2, gridBagConstraints);

        stateLbT2.setText(resourceMap.getString("stateLbT2.text")); // NOI18N
        stateLbT2.setName("stateLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(stateLbT2, gridBagConstraints);

        codeLbT2.setText(resourceMap.getString("codeLbT2.text")); // NOI18N
        codeLbT2.setName("codeLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(codeLbT2, gridBagConstraints);

        mobileLbT2.setText(resourceMap.getString("mobileLbT2.text")); // NOI18N
        mobileLbT2.setName("mobileLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(mobileLbT2, gridBagConstraints);

        faxLbT2.setText(resourceMap.getString("faxLbT2.text")); // NOI18N
        faxLbT2.setName("faxLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(faxLbT2, gridBagConstraints);

        adsLbT2.setText(resourceMap.getString("adsLbT2.text")); // NOI18N
        adsLbT2.setName("adsLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(adsLbT2, gridBagConstraints);

        lastnameLbT2.setText(resourceMap.getString("lastnameLbT2.text")); // NOI18N
        lastnameLbT2.setName("lastnameLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        upLeftT2.add(lastnameLbT2, gridBagConstraints);

        emailLbT2.setText(resourceMap.getString("emailLbT2.text")); // NOI18N
        emailLbT2.setName("emailLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(emailLbT2, gridBagConstraints);

        webLbT2.setText(resourceMap.getString("webLbT2.text")); // NOI18N
        webLbT2.setName("webLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(webLbT2, gridBagConstraints);

        cityLbT2.setText(resourceMap.getString("cityLbT2.text")); // NOI18N
        cityLbT2.setName("cityLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(cityLbT2, gridBagConstraints);

        countryLbT2.setText(resourceMap.getString("countryLbT2.text")); // NOI18N
        countryLbT2.setName("countryLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(countryLbT2, gridBagConstraints);

        phoneLbT2.setText(resourceMap.getString("phoneLbT2.text")); // NOI18N
        phoneLbT2.setName("phoneLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT2.add(phoneLbT2, gridBagConstraints);

        nameTfT2.setColumns(10);
        nameTfT2.setName("nameTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(nameTfT2, gridBagConstraints);

        blankPanel3.setName("blankPanel3"); // NOI18N

        javax.swing.GroupLayout blankPanel3Layout = new javax.swing.GroupLayout(blankPanel3);
        blankPanel3.setLayout(blankPanel3Layout);
        blankPanel3Layout.setHorizontalGroup(
            blankPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        blankPanel3Layout.setVerticalGroup(
            blankPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        upLeftT2.add(blankPanel3, gridBagConstraints);

        lastnameTfT2.setColumns(10);
        lastnameTfT2.setName("lastnameTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(lastnameTfT2, gridBagConstraints);

        titleTfT2.setColumns(10);
        titleTfT2.setName("titleTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(titleTfT2, gridBagConstraints);

        emailTfT2.setColumns(10);
        emailTfT2.setName("emailTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(emailTfT2, gridBagConstraints);

        companyTfT2.setColumns(10);
        companyTfT2.setName("companyTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(companyTfT2, gridBagConstraints);

        webTfT2.setColumns(10);
        webTfT2.setName("webTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(webTfT2, gridBagConstraints);

        stateTfT2.setColumns(10);
        stateTfT2.setName("stateTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(stateTfT2, gridBagConstraints);

        cityTfT2.setColumns(10);
        cityTfT2.setName("cityTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(cityTfT2, gridBagConstraints);

        codeTfT2.setColumns(10);
        codeTfT2.setName("codeTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(codeTfT2, gridBagConstraints);

        countryTfT2.setColumns(10);
        countryTfT2.setName("countryTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(countryTfT2, gridBagConstraints);

        mobileTfT2.setColumns(10);
        mobileTfT2.setName("mobileTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(mobileTfT2, gridBagConstraints);

        phoneTfT2.setColumns(10);
        phoneTfT2.setName("phoneTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(phoneTfT2, gridBagConstraints);

        faxTfT2.setColumns(10);
        faxTfT2.setName("faxTfT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT2.add(faxTfT2, gridBagConstraints);

        adsPanel1.setName("adsPanel1"); // NOI18N
        adsPanel1.setPreferredSize(new java.awt.Dimension(99, 70));

        adsScr2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        adsScr2.setHorizontalScrollBar(null);
        adsScr2.setName("adsScr2"); // NOI18N

        adsTaT2.setColumns(30);
        adsTaT2.setFont(resourceMap.getFont("adsTaT2.font")); // NOI18N
        adsTaT2.setRows(3);
        adsTaT2.setAutoscrolls(false);
        adsTaT2.setName("adsTaT2"); // NOI18N
        adsScr2.setViewportView(adsTaT2);

        javax.swing.GroupLayout adsPanel1Layout = new javax.swing.GroupLayout(adsPanel1);
        adsPanel1.setLayout(adsPanel1Layout);
        adsPanel1Layout.setHorizontalGroup(
            adsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adsScr2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        adsPanel1Layout.setVerticalGroup(
            adsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adsScr2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        upLeftT2.add(adsPanel1, gridBagConstraints);

        blankPanel4.setName("blankPanel4"); // NOI18N

        javax.swing.GroupLayout blankPanel4Layout = new javax.swing.GroupLayout(blankPanel4);
        blankPanel4.setLayout(blankPanel4Layout);
        blankPanel4Layout.setHorizontalGroup(
            blankPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        blankPanel4Layout.setVerticalGroup(
            blankPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        upLeftT2.add(blankPanel4, gridBagConstraints);

        searchT2.setText(resourceMap.getString("searchT2.text")); // NOI18N
        searchT2.setName("searchT2"); // NOI18N
        searchT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchT2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 2;
        upLeftT2.add(searchT2, gridBagConstraints);

        blankPanel0T2.setName("blankPanel0T2"); // NOI18N

        javax.swing.GroupLayout blankPanel0T2Layout = new javax.swing.GroupLayout(blankPanel0T2);
        blankPanel0T2.setLayout(blankPanel0T2Layout);
        blankPanel0T2Layout.setHorizontalGroup(
            blankPanel0T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        blankPanel0T2Layout.setVerticalGroup(
            blankPanel0T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        upLeftT2.add(blankPanel0T2, gridBagConstraints);

        blankPanel1T2.setName("blankPanel1T2"); // NOI18N

        javax.swing.GroupLayout blankPanel1T2Layout = new javax.swing.GroupLayout(blankPanel1T2);
        blankPanel1T2.setLayout(blankPanel1T2Layout);
        blankPanel1T2Layout.setHorizontalGroup(
            blankPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        blankPanel1T2Layout.setVerticalGroup(
            blankPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        upLeftT2.add(blankPanel1T2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        queryTab.add(upLeftT2, gridBagConstraints);

        upRightT2.setMinimumSize(new java.awt.Dimension(250, 250));
        upRightT2.setName("upRightT2"); // NOI18N
        upRightT2.setPreferredSize(new java.awt.Dimension(320, 250));
        upRightT2.setLayout(new java.awt.GridBagLayout());

        exportPanelT2.setName("exportPanelT2"); // NOI18N
        exportPanelT2.setPreferredSize(new java.awt.Dimension(300, 60));

        exportLbT2.setText(resourceMap.getString("exportLbT2.text")); // NOI18N
        exportLbT2.setName("exportLbT2"); // NOI18N

        exportTfT2.setText(resourceMap.getString("exportTfT2.text")); // NOI18N
        exportTfT2.setName("exportTfT2"); // NOI18N

        exBrowseBtnT2.setText(resourceMap.getString("exportBtnT2.text")); // NOI18N
        exBrowseBtnT2.setName("exportBtnT2"); // NOI18N

        exportBtnT2.setText(resourceMap.getString("exportBtnT2.text")); // NOI18N
        exportBtnT2.setName("exportBtnT2"); // NOI18N

        javax.swing.GroupLayout exportPanelT2Layout = new javax.swing.GroupLayout(exportPanelT2);
        exportPanelT2.setLayout(exportPanelT2Layout);
        exportPanelT2Layout.setHorizontalGroup(
            exportPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exportPanelT2Layout.createSequentialGroup()
                .addComponent(exportLbT2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTfT2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(exportPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exBrowseBtnT2)
                    .addComponent(exportBtnT2))
                .addGap(29, 29, 29))
        );
        exportPanelT2Layout.setVerticalGroup(
            exportPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exportLbT2)
            .addGroup(exportPanelT2Layout.createSequentialGroup()
                .addGroup(exportPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportTfT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exBrowseBtnT2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportBtnT2))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upRightT2.add(exportPanelT2, gridBagConstraints);

        genSearchPanelT2.setName("genSearchT2"); // NOI18N
        genSearchPanelT2.setPreferredSize(new java.awt.Dimension(300, 35));

        genSearchLbT2.setText(resourceMap.getString("genSearchLbT2.text")); // NOI18N
        genSearchLbT2.setName("genSearchLbT2"); // NOI18N

        genSearchTfT2.setText(resourceMap.getString("genSearchTfT2.text")); // NOI18N
        genSearchTfT2.setName("genSearchTfT2"); // NOI18N

        genSearchT2.setText(resourceMap.getString("genSearchT2.text")); // NOI18N
        genSearchT2.setName("genSearchT2"); // NOI18N

        javax.swing.GroupLayout genSearchPanelT2Layout = new javax.swing.GroupLayout(genSearchPanelT2);
        genSearchPanelT2.setLayout(genSearchPanelT2Layout);
        genSearchPanelT2Layout.setHorizontalGroup(
            genSearchPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genSearchPanelT2Layout.createSequentialGroup()
                .addComponent(genSearchLbT2)
                .addGap(26, 26, 26)
                .addComponent(genSearchTfT2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genSearchT2)
                .addGap(29, 29, 29))
        );
        genSearchPanelT2Layout.setVerticalGroup(
            genSearchPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genSearchPanelT2Layout.createSequentialGroup()
                .addGroup(genSearchPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genSearchLbT2)
                    .addComponent(genSearchTfT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genSearchT2))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upRightT2.add(genSearchPanelT2, gridBagConstraints);

        quickPanelT2.setName("quickPanelT2"); // NOI18N
        quickPanelT2.setPreferredSize(new java.awt.Dimension(320, 150));
        quickPanelT2.setLayout(new java.awt.GridLayout(3, 1));

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 14));
        quickPanelT2.add(jLabel1);

        btnPanel0T2.setMinimumSize(new java.awt.Dimension(300, 65));
        btnPanel0T2.setName("btnPanel0T2"); // NOI18N
        btnPanel0T2.setPreferredSize(new java.awt.Dimension(300, 65));
        btnPanel0T2.setLayout(new java.awt.GridLayout(2, 9));

        engBtn0T2.setText(resourceMap.getString("engBtn0T2.text")); // NOI18N
        engBtn0T2.setName("engBtn0T2"); // NOI18N
        engBtn0T2.setPreferredSize(new java.awt.Dimension(39, 23));
        btnPanel0T2.add(engBtn0T2);

        engBtn1T2.setText(resourceMap.getString("engBtn1T2.text")); // NOI18N
        engBtn1T2.setName("engBtn1T2"); // NOI18N
        btnPanel0T2.add(engBtn1T2);

        engBtn2T2.setText(resourceMap.getString("engBtn2T2.text")); // NOI18N
        engBtn2T2.setName("engBtn2T2"); // NOI18N
        btnPanel0T2.add(engBtn2T2);

        engBtn3T2.setText(resourceMap.getString("engBtn3T2.text")); // NOI18N
        engBtn3T2.setName("engBtn3T2"); // NOI18N
        btnPanel0T2.add(engBtn3T2);

        engBtn4T2.setText(resourceMap.getString("engBtn4T2.text")); // NOI18N
        engBtn4T2.setName("engBtn4T2"); // NOI18N
        btnPanel0T2.add(engBtn4T2);

        engBtn5T2.setText(resourceMap.getString("engBtn5T2.text")); // NOI18N
        engBtn5T2.setName("engBtn5T2"); // NOI18N
        btnPanel0T2.add(engBtn5T2);

        thBtn0T2.setText(resourceMap.getString("thBtn0T2.text")); // NOI18N
        thBtn0T2.setName("thBtn0T2"); // NOI18N
        btnPanel0T2.add(thBtn0T2);

        thBtn1T2.setText(resourceMap.getString("thBtn1T2.text")); // NOI18N
        thBtn1T2.setName("thBtn1T2"); // NOI18N
        btnPanel0T2.add(thBtn1T2);

        thBtn2T2.setText(resourceMap.getString("thBtn2T2.text")); // NOI18N
        thBtn2T2.setName("thBtn2T2"); // NOI18N
        btnPanel0T2.add(thBtn2T2);

        thBtn3T2.setText(resourceMap.getString("thBtn3T2.text")); // NOI18N
        thBtn3T2.setName("thBtn3T2"); // NOI18N
        btnPanel0T2.add(thBtn3T2);

        thBtn4T2.setText(resourceMap.getString("thBtn4T2.text")); // NOI18N
        thBtn4T2.setName("thBtn4T2"); // NOI18N
        btnPanel0T2.add(thBtn4T2);

        thBtn5T2.setText(resourceMap.getString("thBtn5T2.text")); // NOI18N
        thBtn5T2.setName("thBtn5T2"); // NOI18N
        btnPanel0T2.add(thBtn5T2);

        quickPanelT2.add(btnPanel0T2);

        btnPanel1T2.setName("btnPanel1T2"); // NOI18N
        btnPanel1T2.setPreferredSize(new java.awt.Dimension(300, 61));

        engBtn6T2.setText(resourceMap.getString("engBtn6T2.text")); // NOI18N
        engBtn6T2.setName("engBtn6T2"); // NOI18N
        engBtn6T2.setPreferredSize(new java.awt.Dimension(39, 23));

        engBtn7T2.setText(resourceMap.getString("engBtn7T2.text")); // NOI18N
        engBtn7T2.setName("engBtn7T2"); // NOI18N

        engBtn8T2.setText(resourceMap.getString("engBtn8T2.text")); // NOI18N
        engBtn8T2.setName("engBtn8T2"); // NOI18N

        thBtn8T2.setText(resourceMap.getString("thBtn8T2.text")); // NOI18N
        thBtn8T2.setName("thBtn8T2"); // NOI18N

        thBtn7T2.setText(resourceMap.getString("thBtn7T2.text")); // NOI18N
        thBtn7T2.setName("thBtn7T2"); // NOI18N

        thBtn6T2.setText(resourceMap.getString("thBtn6T2.text")); // NOI18N
        thBtn6T2.setName("thBtn6T2"); // NOI18N

        javax.swing.GroupLayout btnPanel1T2Layout = new javax.swing.GroupLayout(btnPanel1T2);
        btnPanel1T2.setLayout(btnPanel1T2Layout);
        btnPanel1T2Layout.setHorizontalGroup(
            btnPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanel1T2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(btnPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnPanel1T2Layout.createSequentialGroup()
                        .addComponent(engBtn6T2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(engBtn7T2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(engBtn8T2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(btnPanel1T2Layout.createSequentialGroup()
                        .addComponent(thBtn6T2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(thBtn7T2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(thBtn8T2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        btnPanel1T2Layout.setVerticalGroup(
            btnPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanel1T2Layout.createSequentialGroup()
                .addGroup(btnPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(engBtn6T2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engBtn7T2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engBtn8T2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(btnPanel1T2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thBtn6T2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thBtn7T2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thBtn8T2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        quickPanelT2.add(btnPanel1T2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        upRightT2.add(quickPanelT2, gridBagConstraints);

        queryTab.add(upRightT2, new java.awt.GridBagConstraints());

        lowPanelT2.setName("lowPanelT2"); // NOI18N
        lowPanelT2.setPreferredSize(new java.awt.Dimension(650, 340));
        lowPanelT2.setLayout(new java.awt.GridBagLayout());

        tablePanelT2.setMinimumSize(new java.awt.Dimension(640, 270));
        tablePanelT2.setName("tablePanelT2"); // NOI18N
        tablePanelT2.setPreferredSize(new java.awt.Dimension(640, 270));

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        importTableT2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "selected", "Name", "Lastname", "Title", "E-mail", "Company", "Mobile", "Phone", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        importTableT2.setName("importTableT2"); // NOI18N
        jScrollPane4.setViewportView(importTableT2);

        javax.swing.GroupLayout tablePanelT2Layout = new javax.swing.GroupLayout(tablePanelT2);
        tablePanelT2.setLayout(tablePanelT2Layout);
        tablePanelT2Layout.setHorizontalGroup(
            tablePanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        tablePanelT2Layout.setVerticalGroup(
            tablePanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelT2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        lowPanelT2.add(tablePanelT2, gridBagConstraints);

        deleteEditPanelT2.setName("deleteEditPanelT2"); // NOI18N
        deleteEditPanelT2.setPreferredSize(new java.awt.Dimension(250, 35));

        deletedBtnT2.setText(resourceMap.getString("deletedBtnT2.text")); // NOI18N
        deletedBtnT2.setName("deletedBtnT2"); // NOI18N
        deletedBtnT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedBtnT2ActionPerformed(evt);
            }
        });

        editBtnT2.setText(resourceMap.getString("editBtnT2.text")); // NOI18N
        editBtnT2.setName("editBtnT2"); // NOI18N
        editBtnT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnT2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteEditPanelT2Layout = new javax.swing.GroupLayout(deleteEditPanelT2);
        deleteEditPanelT2.setLayout(deleteEditPanelT2Layout);
        deleteEditPanelT2Layout.setHorizontalGroup(
            deleteEditPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteEditPanelT2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(deletedBtnT2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(editBtnT2)
                .addGap(33, 33, 33))
        );
        deleteEditPanelT2Layout.setVerticalGroup(
            deleteEditPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteEditPanelT2Layout.createSequentialGroup()
                .addGroup(deleteEditPanelT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletedBtnT2)
                    .addComponent(editBtnT2))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        lowPanelT2.add(deleteEditPanelT2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        queryTab.add(lowPanelT2, gridBagConstraints);

        menuTab.addTab(resourceMap.getString("queryTab.TabConstraints.tabTitle"), queryTab); // NOI18N

        resultTab.setName("resultTab"); // NOI18N
        resultTab.setLayout(new java.awt.GridBagLayout());

        upLeftT3.setName("upLeftT3"); // NOI18N
        upLeftT3.setPreferredSize(new java.awt.Dimension(350, 250));
        upLeftT3.setLayout(new java.awt.GridBagLayout());

        nameLbT3.setText(resourceMap.getString("nameLbT3.text")); // NOI18N
        nameLbT3.setName("nameLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(nameLbT3, gridBagConstraints);

        titleLbT3.setText(resourceMap.getString("titleLbT3.text")); // NOI18N
        titleLbT3.setName("titleLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(titleLbT3, gridBagConstraints);

        companyLbT3.setText(resourceMap.getString("companyLbT3.text")); // NOI18N
        companyLbT3.setName("companyLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(companyLbT3, gridBagConstraints);

        stateLbT3.setText(resourceMap.getString("stateLbT3.text")); // NOI18N
        stateLbT3.setName("stateLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(stateLbT3, gridBagConstraints);

        codeLbT3.setText(resourceMap.getString("codeLbT3.text")); // NOI18N
        codeLbT3.setName("codeLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(codeLbT3, gridBagConstraints);

        mobileLbT3.setText(resourceMap.getString("mobileLbT3.text")); // NOI18N
        mobileLbT3.setName("mobileLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(mobileLbT3, gridBagConstraints);

        faxLbT3.setText(resourceMap.getString("faxLbT3.text")); // NOI18N
        faxLbT3.setName("faxLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(faxLbT3, gridBagConstraints);

        adsLbT3.setText(resourceMap.getString("adsLbT3.text")); // NOI18N
        adsLbT3.setName("adsLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(adsLbT3, gridBagConstraints);

        lastnameLbT3.setText(resourceMap.getString("lastnameLbT3.text")); // NOI18N
        lastnameLbT3.setName("lastnameLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        upLeftT3.add(lastnameLbT3, gridBagConstraints);

        emailLbT3.setText(resourceMap.getString("emailLbT3.text")); // NOI18N
        emailLbT3.setName("emailLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(emailLbT3, gridBagConstraints);

        webLbT3.setText(resourceMap.getString("webLbT3.text")); // NOI18N
        webLbT3.setName("webLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(webLbT3, gridBagConstraints);

        cityLbT3.setText(resourceMap.getString("cityLbT3.text")); // NOI18N
        cityLbT3.setName("cityLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(cityLbT3, gridBagConstraints);

        countryLbT3.setText(resourceMap.getString("countryLbT3.text")); // NOI18N
        countryLbT3.setName("countryLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(countryLbT3, gridBagConstraints);

        phoneLbT3.setText(resourceMap.getString("phoneLbT3.text")); // NOI18N
        phoneLbT3.setName("phoneLbT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(phoneLbT3, gridBagConstraints);

        noteLbT2.setText(resourceMap.getString("noteLbT2.text")); // NOI18N
        noteLbT2.setName("noteLbT2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upLeftT3.add(noteLbT2, gridBagConstraints);

        nameTfT3.setColumns(10);
        nameTfT3.setName("nameTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(nameTfT3, gridBagConstraints);

        titleTfT3.setColumns(10);
        titleTfT3.setName("titleTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(titleTfT3, gridBagConstraints);

        companyTfT3.setColumns(10);
        companyTfT3.setName("companyTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(companyTfT3, gridBagConstraints);

        stateTfT3.setColumns(10);
        stateTfT3.setName("stateTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(stateTfT3, gridBagConstraints);

        codeTfT3.setColumns(10);
        codeTfT3.setName("codeTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(codeTfT3, gridBagConstraints);

        mobileTfT3.setColumns(10);
        mobileTfT3.setName("mobileTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(mobileTfT3, gridBagConstraints);

        faxTfT3.setColumns(10);
        faxTfT3.setName("faxTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(faxTfT3, gridBagConstraints);

        lastnameTfT3.setColumns(10);
        lastnameTfT3.setName("lastnameTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(lastnameTfT3, gridBagConstraints);

        emailTfT3.setColumns(10);
        emailTfT3.setName("emailTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(emailTfT3, gridBagConstraints);

        webTfT3.setColumns(10);
        webTfT3.setName("webTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(webTfT3, gridBagConstraints);

        cityTfT3.setColumns(10);
        cityTfT3.setName("cityTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(cityTfT3, gridBagConstraints);

        countryTfT3.setColumns(10);
        countryTfT3.setName("countryTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(countryTfT3, gridBagConstraints);

        phoneTfT3.setColumns(10);
        phoneTfT3.setName("phoneTfT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upLeftT3.add(phoneTfT3, gridBagConstraints);

        adsPanel2.setName("adsPanel2"); // NOI18N
        adsPanel2.setPreferredSize(new java.awt.Dimension(99, 70));

        adsScr3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        adsScr3.setHorizontalScrollBar(null);
        adsScr3.setName("adsScr3"); // NOI18N

        adsTaT3.setColumns(30);
        adsTaT3.setFont(resourceMap.getFont("adsTaT3.font")); // NOI18N
        adsTaT3.setRows(3);
        adsTaT3.setAutoscrolls(false);
        adsTaT3.setName("adsTaT3"); // NOI18N
        adsScr3.setViewportView(adsTaT3);

        javax.swing.GroupLayout adsPanel2Layout = new javax.swing.GroupLayout(adsPanel2);
        adsPanel2.setLayout(adsPanel2Layout);
        adsPanel2Layout.setHorizontalGroup(
            adsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adsScr3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        adsPanel2Layout.setVerticalGroup(
            adsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adsPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adsScr3, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        upLeftT3.add(adsPanel2, gridBagConstraints);

        notePanel1.setName("notePanel1"); // NOI18N
        notePanel1.setPreferredSize(new java.awt.Dimension(120, 70));

        adsScr4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        adsScr4.setHorizontalScrollBar(null);
        adsScr4.setName("adsScr4"); // NOI18N

        noteTaT3.setColumns(30);
        noteTaT3.setFont(resourceMap.getFont("noteTaT3.font")); // NOI18N
        noteTaT3.setRows(3);
        noteTaT3.setAutoscrolls(false);
        noteTaT3.setName("noteTaT3"); // NOI18N
        adsScr4.setViewportView(noteTaT3);

        javax.swing.GroupLayout notePanel1Layout = new javax.swing.GroupLayout(notePanel1);
        notePanel1.setLayout(notePanel1Layout);
        notePanel1Layout.setHorizontalGroup(
            notePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanel1Layout.createSequentialGroup()
                .addComponent(adsScr4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        notePanel1Layout.setVerticalGroup(
            notePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adsScr4, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        upLeftT3.add(notePanel1, gridBagConstraints);

        attachPanel1.setName("attachPanel1"); // NOI18N
        attachPanel1.setPreferredSize(new java.awt.Dimension(125, 20));

        javax.swing.GroupLayout attachPanel1Layout = new javax.swing.GroupLayout(attachPanel1);
        attachPanel1.setLayout(attachPanel1Layout);
        attachPanel1Layout.setHorizontalGroup(
            attachPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );
        attachPanel1Layout.setVerticalGroup(
            attachPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        upLeftT3.add(attachPanel1, gridBagConstraints);

        blankPanel5.setName("blankPanel5"); // NOI18N

        javax.swing.GroupLayout blankPanel5Layout = new javax.swing.GroupLayout(blankPanel5);
        blankPanel5.setLayout(blankPanel5Layout);
        blankPanel5Layout.setHorizontalGroup(
            blankPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        blankPanel5Layout.setVerticalGroup(
            blankPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        upLeftT3.add(blankPanel5, gridBagConstraints);

        blankPanel6.setName("blankPanel6"); // NOI18N

        javax.swing.GroupLayout blankPanel6Layout = new javax.swing.GroupLayout(blankPanel6);
        blankPanel6.setLayout(blankPanel6Layout);
        blankPanel6Layout.setHorizontalGroup(
            blankPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        blankPanel6Layout.setVerticalGroup(
            blankPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        upLeftT3.add(blankPanel6, gridBagConstraints);

        blankPanel7.setName("blankPanel7"); // NOI18N

        javax.swing.GroupLayout blankPanel7Layout = new javax.swing.GroupLayout(blankPanel7);
        blankPanel7.setLayout(blankPanel7Layout);
        blankPanel7Layout.setHorizontalGroup(
            blankPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        blankPanel7Layout.setVerticalGroup(
            blankPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        upLeftT3.add(blankPanel7, gridBagConstraints);

        blankPanel0T3.setName("blankPanel0T3"); // NOI18N

        javax.swing.GroupLayout blankPanel0T3Layout = new javax.swing.GroupLayout(blankPanel0T3);
        blankPanel0T3.setLayout(blankPanel0T3Layout);
        blankPanel0T3Layout.setHorizontalGroup(
            blankPanel0T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        blankPanel0T3Layout.setVerticalGroup(
            blankPanel0T3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        upLeftT3.add(blankPanel0T3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        resultTab.add(upLeftT3, gridBagConstraints);

        upRightT3.setMaximumSize(new java.awt.Dimension(250, 250));
        upRightT3.setMinimumSize(new java.awt.Dimension(250, 250));
        upRightT3.setName("upRightT3"); // NOI18N
        upRightT3.setPreferredSize(new java.awt.Dimension(250, 250));
        upRightT3.setLayout(new java.awt.GridBagLayout());

        scannerPanel2.setName("scannerPanel2"); // NOI18N
        scannerPanel2.setPreferredSize(new java.awt.Dimension(250, 20));

        groupLbT2.setText(resourceMap.getString("groupLbT2.text")); // NOI18N
        groupLbT2.setName("groupLbT2"); // NOI18N

        grouprCmbT2.setName("grouprCmbT2"); // NOI18N

        javax.swing.GroupLayout scannerPanel2Layout = new javax.swing.GroupLayout(scannerPanel2);
        scannerPanel2.setLayout(scannerPanel2Layout);
        scannerPanel2Layout.setHorizontalGroup(
            scannerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scannerPanel2Layout.createSequentialGroup()
                .addComponent(groupLbT2)
                .addGap(33, 33, 33)
                .addComponent(grouprCmbT2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        scannerPanel2Layout.setVerticalGroup(
            scannerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scannerPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(groupLbT2)
                .addComponent(grouprCmbT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        upRightT3.add(scannerPanel2, gridBagConstraints);

        brightPanelT3.setMinimumSize(new java.awt.Dimension(242, 40));
        brightPanelT3.setName("brightPanelT3"); // NOI18N
        brightPanelT3.setPreferredSize(new java.awt.Dimension(242, 40));
        brightPanelT3.setRequestFocusEnabled(false);
        brightPanelT3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        brightLbT3.setText(resourceMap.getString("brightLbT3.text")); // NOI18N
        brightLbT3.setName("brightLbT3"); // NOI18N
        brightPanelT3.add(brightLbT3);

        brightSldT3.setMajorTickSpacing(1);
        brightSldT3.setMaximum(5);
        brightSldT3.setMinimum(1);
        brightSldT3.setMinorTickSpacing(1);
        brightSldT3.setPaintLabels(true);
        brightSldT3.setSnapToTicks(true);
        brightSldT3.setValue(3);
        brightSldT3.setMaximumSize(new java.awt.Dimension(32767, 35));
        brightSldT3.setMinimumSize(new java.awt.Dimension(50, 35));
        brightSldT3.setName("brightSldT3"); // NOI18N
        brightSldT3.setPreferredSize(new java.awt.Dimension(150, 35));
        brightSldT3.setLabelTable( labelTable );
        brightPanelT3.add(brightSldT3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        upRightT3.add(brightPanelT3, gridBagConstraints);

        blackWhiteBtnT3.setText(resourceMap.getString("blackWhiteBtnT3.text")); // NOI18N
        blackWhiteBtnT3.setName("blackWhiteBtnT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT3.add(blackWhiteBtnT3, gridBagConstraints);

        cropBtnT3.setText(resourceMap.getString("cropBtnT3.text")); // NOI18N
        cropBtnT3.setName("cropBtnT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT3.add(cropBtnT3, gridBagConstraints);

        rotateBtnT3.setText(resourceMap.getString("rotateBtnT3.text")); // NOI18N
        rotateBtnT3.setMaximumSize(new java.awt.Dimension(110, 23));
        rotateBtnT3.setMinimumSize(new java.awt.Dimension(110, 23));
        rotateBtnT3.setName("rotateBtnT3"); // NOI18N
        rotateBtnT3.setPreferredSize(new java.awt.Dimension(110, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 1, 0);
        upRightT3.add(rotateBtnT3, gridBagConstraints);

        emailBtnT3.setText(resourceMap.getString("emailBtnT3.text")); // NOI18N
        emailBtnT3.setName("emailBtnT3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 1, 0);
        upRightT3.add(emailBtnT3, gridBagConstraints);

        saveBtnT3.setText(resourceMap.getString("saveBtnT3.text")); // NOI18N
        saveBtnT3.setEnabled(false);
        saveBtnT3.setName("saveBtnT3"); // NOI18N
        saveBtnT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnT3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        upRightT3.add(saveBtnT3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        resultTab.add(upRightT3, gridBagConstraints);

        lowT3.setName("lowT3"); // NOI18N
        lowT3.setPreferredSize(new java.awt.Dimension(650, 300));
        lowT3.setLayout(new java.awt.GridBagLayout());

        lowLeftT3.setName("lowLeftT3"); // NOI18N

        frontTfT3.setName("frontTfT3"); // NOI18N

        frontBtnT3.setText(resourceMap.getString("frontBtnT3.text")); // NOI18N
        frontBtnT3.setName("frontBtnT3"); // NOI18N
        frontBtnT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frontBtnT3ActionPerformed(evt);
            }
        });

        frontSpT3.setName("frontSpT3"); // NOI18N
        frontSpT3.setPreferredSize(new java.awt.Dimension(100, 260));

        javax.swing.GroupLayout lowLeftT3Layout = new javax.swing.GroupLayout(lowLeftT3);
        lowLeftT3.setLayout(lowLeftT3Layout);
        lowLeftT3Layout.setHorizontalGroup(
            lowLeftT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowLeftT3Layout.createSequentialGroup()
                .addGroup(lowLeftT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lowLeftT3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(frontTfT3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frontBtnT3))
                    .addGroup(lowLeftT3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(frontSpT3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lowLeftT3Layout.setVerticalGroup(
            lowLeftT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowLeftT3Layout.createSequentialGroup()
                .addGroup(lowLeftT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frontTfT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frontBtnT3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frontSpT3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        lowT3.add(lowLeftT3, gridBagConstraints);

        lowRightT3.setName("lowRightT3"); // NOI18N

        backTfT3.setName("backTfT3"); // NOI18N

        backBtnT3.setText(resourceMap.getString("backBtnT3.text")); // NOI18N
        backBtnT3.setName("backBtnT3"); // NOI18N

        backSpT3.setName("backSpT3"); // NOI18N
        backSpT3.setPreferredSize(new java.awt.Dimension(100, 260));

        javax.swing.GroupLayout lowRightT3Layout = new javax.swing.GroupLayout(lowRightT3);
        lowRightT3.setLayout(lowRightT3Layout);
        lowRightT3Layout.setHorizontalGroup(
            lowRightT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowRightT3Layout.createSequentialGroup()
                .addGroup(lowRightT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lowRightT3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(backTfT3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBtnT3))
                    .addGroup(lowRightT3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backSpT3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lowRightT3Layout.setVerticalGroup(
            lowRightT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowRightT3Layout.createSequentialGroup()
                .addGroup(lowRightT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backTfT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtnT3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backSpT3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        lowT3.add(lowRightT3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        resultTab.add(lowT3, gridBagConstraints);

        upLabelT3.setMinimumSize(new java.awt.Dimension(200, 25));
        upLabelT3.setName("upLabelT3"); // NOI18N
        upLabelT3.setPreferredSize(new java.awt.Dimension(500, 25));

        idLbT3.setText(resourceMap.getString("idLbT3.text")); // NOI18N
        idLbT3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        idLbT3.setName("idLbT3"); // NOI18N

        idNameLbT3.setText(resourceMap.getString("idNameLbT3.text")); // NOI18N
        idNameLbT3.setMaximumSize(new java.awt.Dimension(400, 20));
        idNameLbT3.setMinimumSize(new java.awt.Dimension(300, 20));
        idNameLbT3.setName("idNameLbT3"); // NOI18N
        idNameLbT3.setPreferredSize(new java.awt.Dimension(400, 20));

        javax.swing.GroupLayout upLabelT3Layout = new javax.swing.GroupLayout(upLabelT3);
        upLabelT3.setLayout(upLabelT3Layout);
        upLabelT3Layout.setHorizontalGroup(
            upLabelT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upLabelT3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idLbT3)
                .addGap(18, 18, 18)
                .addComponent(idNameLbT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        upLabelT3Layout.setVerticalGroup(
            upLabelT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upLabelT3Layout.createSequentialGroup()
                .addGroup(upLabelT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLbT3)
                    .addComponent(idNameLbT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        resultTab.add(upLabelT3, gridBagConstraints);

        menuTab.addTab(resourceMap.getString("resultTab.TabConstraints.tabTitle"), resultTab); // NOI18N

        importExportTab.setName("importExportTab"); // NOI18N
        importExportTab.setLayout(new java.awt.GridBagLayout());

        importPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("importPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("importPanel.border.titleColor"))); // NOI18N
        importPanel.setName("importPanel"); // NOI18N
        importPanel.setPreferredSize(new java.awt.Dimension(600, 300));
        importPanel.setLayout(new java.awt.GridBagLayout());

        importDesLbT4.setText(resourceMap.getString("importDesLbT4.text")); // NOI18N
        importDesLbT4.setName("importDesLbT4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        importPanel.add(importDesLbT4, gridBagConstraints);

        importPanelT4.setMinimumSize(new java.awt.Dimension(100, 40));
        importPanelT4.setName("importPanelT4"); // NOI18N
        importPanelT4.setPreferredSize(new java.awt.Dimension(344, 40));

        importLbT4.setText(resourceMap.getString("importLbT4.text")); // NOI18N
        importLbT4.setName("importLbT4"); // NOI18N

        importTfT4.setText(resourceMap.getString("importTfT4.text")); // NOI18N
        importTfT4.setName("importTfT4"); // NOI18N

        importBrowseBtnT4.setText(resourceMap.getString("importBrowseBtnT4.text")); // NOI18N
        importBrowseBtnT4.setName("importBrowseBtnT4"); // NOI18N

        importBtnT4.setText(resourceMap.getString("importBtnT4.text")); // NOI18N
        importBtnT4.setName("importBtnT4"); // NOI18N

        javax.swing.GroupLayout importPanelT4Layout = new javax.swing.GroupLayout(importPanelT4);
        importPanelT4.setLayout(importPanelT4Layout);
        importPanelT4Layout.setHorizontalGroup(
            importPanelT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importPanelT4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(importLbT4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importTfT4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importBrowseBtnT4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(importBtnT4)
                .addContainerGap())
        );
        importPanelT4Layout.setVerticalGroup(
            importPanelT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importPanelT4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(importPanelT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importTfT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importBrowseBtnT4)
                    .addComponent(importLbT4)
                    .addComponent(importBtnT4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        importPanel.add(importPanelT4, gridBagConstraints);

        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        importTableT4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Lastname", "Title", "E-mail", "Company", "Web", "Mobile", "Phone", "Fax", "Country"
            }
        ));
        importTableT4.setName("importTableT4"); // NOI18N
        importTableT4.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(importTableT4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        importPanel.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        importExportTab.add(importPanel, gridBagConstraints);

        exportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("exportPanel.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("exportPanel.border.titleColor"))); // NOI18N
        exportPanel.setName("exportPanel"); // NOI18N
        exportPanel.setPreferredSize(new java.awt.Dimension(600, 100));

        jPanel5.setMinimumSize(new java.awt.Dimension(100, 40));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(344, 40));

        exportLbT4.setText(resourceMap.getString("exportLbT4.text")); // NOI18N
        exportLbT4.setName("exportLbT4"); // NOI18N

        exportTfT4.setName("exportTfT4"); // NOI18N

        browseExportBtnT4.setText(resourceMap.getString("browseExportBtnT4.text")); // NOI18N
        browseExportBtnT4.setName("browseExportBtnT4"); // NOI18N
        browseExportBtnT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseExportBtnT4ActionPerformed(evt);
            }
        });

        exportBtnT4.setText(resourceMap.getString("exportBtnT4.text")); // NOI18N
        exportBtnT4.setName("exportBtnT4"); // NOI18N
        exportBtnT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnT4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportLbT4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTfT4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseExportBtnT4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportBtnT4)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportTfT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseExportBtnT4)
                    .addComponent(exportLbT4)
                    .addComponent(exportBtnT4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout exportPanelLayout = new javax.swing.GroupLayout(exportPanel);
        exportPanel.setLayout(exportPanelLayout);
        exportPanelLayout.setHorizontalGroup(
            exportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exportPanelLayout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        exportPanelLayout.setVerticalGroup(
            exportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        importExportTab.add(exportPanel, gridBagConstraints);

        blankPanelT4.setName("blankPanelT4"); // NOI18N
        blankPanelT4.setPreferredSize(new java.awt.Dimension(100, 150));

        javax.swing.GroupLayout blankPanelT4Layout = new javax.swing.GroupLayout(blankPanelT4);
        blankPanelT4.setLayout(blankPanelT4Layout);
        blankPanelT4Layout.setHorizontalGroup(
            blankPanelT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        blankPanelT4Layout.setVerticalGroup(
            blankPanelT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        importExportTab.add(blankPanelT4, gridBagConstraints);

        menuTab.addTab(resourceMap.getString("importExportTab.TabConstraints.tabTitle"), importExportTab); // NOI18N

        groupTab.setName("groupTab"); // NOI18N
        groupTab.setLayout(new java.awt.GridBagLayout());

        editGroupPanelT5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("editGroupPanelT5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("editGroupPanelT5.border.titleColor"))); // NOI18N
        editGroupPanelT5.setName("editGroupPanelT5"); // NOI18N
        editGroupPanelT5.setPreferredSize(new java.awt.Dimension(600, 300));
        editGroupPanelT5.setLayout(new java.awt.GridBagLayout());

        importPanelT5.setMinimumSize(new java.awt.Dimension(100, 40));
        importPanelT5.setName("importPanelT5"); // NOI18N
        importPanelT5.setPreferredSize(new java.awt.Dimension(344, 40));

        groupSearchLbT5.setText(resourceMap.getString("groupSearchLbT5.text")); // NOI18N
        groupSearchLbT5.setName("groupSearchLbT5"); // NOI18N

        groupSearchTfT5.setName("groupSearchTfT5"); // NOI18N

        groupSearchBtnT5.setText(resourceMap.getString("groupSearchBtnT5.text")); // NOI18N
        groupSearchBtnT5.setName("groupSearchBtnT5"); // NOI18N

        javax.swing.GroupLayout importPanelT5Layout = new javax.swing.GroupLayout(importPanelT5);
        importPanelT5.setLayout(importPanelT5Layout);
        importPanelT5Layout.setHorizontalGroup(
            importPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importPanelT5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(groupSearchLbT5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupSearchTfT5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupSearchBtnT5)
                .addGap(23, 23, 23))
        );
        importPanelT5Layout.setVerticalGroup(
            importPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importPanelT5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(importPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupSearchTfT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupSearchLbT5)
                    .addComponent(groupSearchBtnT5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editGroupPanelT5.add(importPanelT5, new java.awt.GridBagConstraints());

        groupResultPanelT5.setName("groupResultPanelT5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        resultTableT5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Group"
            }
        ));
        resultTableT5.setName("resultTableT5"); // NOI18N
        resultTableT5.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(resultTableT5);

        resultLbT5.setText(resourceMap.getString("resultLbT5.text")); // NOI18N
        resultLbT5.setName("resultLbT5"); // NOI18N

        javax.swing.GroupLayout groupResultPanelT5Layout = new javax.swing.GroupLayout(groupResultPanelT5);
        groupResultPanelT5.setLayout(groupResultPanelT5Layout);
        groupResultPanelT5Layout.setHorizontalGroup(
            groupResultPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupResultPanelT5Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(groupResultPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(groupResultPanelT5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(resultLbT5))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        groupResultPanelT5Layout.setVerticalGroup(
            groupResultPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupResultPanelT5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultLbT5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 1, 0);
        editGroupPanelT5.add(groupResultPanelT5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        groupTab.add(editGroupPanelT5, gridBagConstraints);

        addNewGroupPanelT5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("addNewGroupPanelT5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("addNewGroupPanelT5.border.titleColor"))); // NOI18N
        addNewGroupPanelT5.setName("addNewGroupPanelT5"); // NOI18N
        addNewGroupPanelT5.setPreferredSize(new java.awt.Dimension(600, 100));

        groupPanelT5.setMinimumSize(new java.awt.Dimension(100, 40));
        groupPanelT5.setName("groupPanelT5"); // NOI18N
        groupPanelT5.setPreferredSize(new java.awt.Dimension(344, 40));

        groupLb.setText(resourceMap.getString("groupLb.text")); // NOI18N
        groupLb.setName("groupLb"); // NOI18N

        groupTfT5.setName("groupTfT5"); // NOI18N

        groupBtnT5.setText(resourceMap.getString("groupBtnT5.text")); // NOI18N
        groupBtnT5.setName("groupBtnT5"); // NOI18N

        javax.swing.GroupLayout groupPanelT5Layout = new javax.swing.GroupLayout(groupPanelT5);
        groupPanelT5.setLayout(groupPanelT5Layout);
        groupPanelT5Layout.setHorizontalGroup(
            groupPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPanelT5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(groupLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupTfT5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupBtnT5)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        groupPanelT5Layout.setVerticalGroup(
            groupPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPanelT5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupTfT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupBtnT5)
                    .addComponent(groupLb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addNewGroupPanelT5Layout = new javax.swing.GroupLayout(addNewGroupPanelT5);
        addNewGroupPanelT5.setLayout(addNewGroupPanelT5Layout);
        addNewGroupPanelT5Layout.setHorizontalGroup(
            addNewGroupPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewGroupPanelT5Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(groupPanelT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        addNewGroupPanelT5Layout.setVerticalGroup(
            addNewGroupPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewGroupPanelT5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupPanelT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        groupTab.add(addNewGroupPanelT5, gridBagConstraints);

        blankPanelT5.setName("blankPanelT5"); // NOI18N
        blankPanelT5.setPreferredSize(new java.awt.Dimension(100, 150));

        javax.swing.GroupLayout blankPanelT5Layout = new javax.swing.GroupLayout(blankPanelT5);
        blankPanelT5.setLayout(blankPanelT5Layout);
        blankPanelT5Layout.setHorizontalGroup(
            blankPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        blankPanelT5Layout.setVerticalGroup(
            blankPanelT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        groupTab.add(blankPanelT5, gridBagConstraints);

        menuTab.addTab(resourceMap.getString("groupTab.TabConstraints.tabTitle"), groupTab); // NOI18N

        userEditTab.setName("userEditTab"); // NOI18N
        userEditTab.setLayout(new java.awt.GridBagLayout());

        addUserPanelT6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("addUserPanelT6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("addUserPanelT6.border.titleColor"))); // NOI18N
        addUserPanelT6.setName("addUserPanelT6"); // NOI18N
        addUserPanelT6.setPreferredSize(new java.awt.Dimension(650, 100));
        addUserPanelT6.setLayout(new java.awt.GridBagLayout());

        usernameLbT6.setText(resourceMap.getString("usernameLbT6.text")); // NOI18N
        usernameLbT6.setName("usernameLbT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        addUserPanelT6.add(usernameLbT6, gridBagConstraints);

        pwdLbT6.setText(resourceMap.getString("pwdLbT6.text")); // NOI18N
        pwdLbT6.setName("pwdLbT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 4);
        addUserPanelT6.add(pwdLbT6, gridBagConstraints);

        groupLbT6.setText(resourceMap.getString("groupLbT6.text")); // NOI18N
        groupLbT6.setName("groupLbT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 4);
        addUserPanelT6.add(groupLbT6, gridBagConstraints);

        usernameTfT6.setColumns(10);
        usernameTfT6.setText(resourceMap.getString("usernameTfT6.text")); // NOI18N
        usernameTfT6.setMinimumSize(new java.awt.Dimension(10, 20));
        usernameTfT6.setName("usernameTfT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        addUserPanelT6.add(usernameTfT6, gridBagConstraints);

        pwdTfT6.setColumns(10);
        pwdTfT6.setText(resourceMap.getString("pwdTfT6.text")); // NOI18N
        pwdTfT6.setName("pwdTfT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        addUserPanelT6.add(pwdTfT6, gridBagConstraints);

        groupT6.setName("groupT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        addUserPanelT6.add(groupT6, gridBagConstraints);

        addBtnT6.setLabel(resourceMap.getString("addBtnT6.label")); // NOI18N
        addBtnT6.setName("addBtnT6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        addUserPanelT6.add(addBtnT6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        userEditTab.add(addUserPanelT6, gridBagConstraints);

        editUserPanelT6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("editUserPanelT6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), resourceMap.getColor("editUserPanelT6.border.titleColor"))); // NOI18N
        editUserPanelT6.setName("editUserPanelT6"); // NOI18N
        editUserPanelT6.setPreferredSize(new java.awt.Dimension(650, 500));
        editUserPanelT6.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 50));

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        editUserPanelT6.add(jPanel1, gridBagConstraints);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 90));

        btnPanel0T3.setMinimumSize(new java.awt.Dimension(300, 65));
        btnPanel0T3.setName("btnPanel0T3"); // NOI18N
        btnPanel0T3.setPreferredSize(new java.awt.Dimension(500, 100));
        btnPanel0T3.setLayout(new java.awt.GridLayout(2, 9));

        engBtn0T3.setText(resourceMap.getString("engBtn0T3.text")); // NOI18N
        engBtn0T3.setName("engBtn0T3"); // NOI18N
        engBtn0T3.setPreferredSize(new java.awt.Dimension(39, 23));
        btnPanel0T3.add(engBtn0T3);

        engBtn1T3.setText(resourceMap.getString("engBtn1T3.text")); // NOI18N
        engBtn1T3.setName("engBtn1T3"); // NOI18N
        btnPanel0T3.add(engBtn1T3);

        engBtn2T3.setText(resourceMap.getString("engBtn2T3.text")); // NOI18N
        engBtn2T3.setName("engBtn2T3"); // NOI18N
        btnPanel0T3.add(engBtn2T3);

        engBtn3T3.setText(resourceMap.getString("engBtn3T3.text")); // NOI18N
        engBtn3T3.setName("engBtn3T3"); // NOI18N
        btnPanel0T3.add(engBtn3T3);

        engBtn4T3.setText(resourceMap.getString("engBtn4T3.text")); // NOI18N
        engBtn4T3.setName("engBtn4T3"); // NOI18N
        btnPanel0T3.add(engBtn4T3);

        engBtn5T3.setText(resourceMap.getString("engBtn5T3.text")); // NOI18N
        engBtn5T3.setName("engBtn5T3"); // NOI18N
        btnPanel0T3.add(engBtn5T3);

        thBtn0T3.setText(resourceMap.getString("thBtn0T3.text")); // NOI18N
        thBtn0T3.setName("thBtn0T3"); // NOI18N
        btnPanel0T3.add(thBtn0T3);

        thBtn1T3.setText(resourceMap.getString("thBtn1T3.text")); // NOI18N
        thBtn1T3.setName("thBtn1T3"); // NOI18N
        btnPanel0T3.add(thBtn1T3);

        thBtn2T3.setText(resourceMap.getString("thBtn2T3.text")); // NOI18N
        thBtn2T3.setName("thBtn2T3"); // NOI18N
        btnPanel0T3.add(thBtn2T3);

        thBtn3T3.setText(resourceMap.getString("thBtn3T3.text")); // NOI18N
        thBtn3T3.setName("thBtn3T3"); // NOI18N
        btnPanel0T3.add(thBtn3T3);

        thBtn4T3.setText(resourceMap.getString("thBtn4T3.text")); // NOI18N
        thBtn4T3.setName("thBtn4T3"); // NOI18N
        btnPanel0T3.add(thBtn4T3);

        thBtn5T3.setText(resourceMap.getString("thBtn5T3.text")); // NOI18N
        thBtn5T3.setName("thBtn5T3"); // NOI18N
        btnPanel0T3.add(thBtn5T3);

        engBtn6T3.setText(resourceMap.getString("engBtn6T3.text")); // NOI18N
        engBtn6T3.setName("engBtn6T3"); // NOI18N
        engBtn6T3.setPreferredSize(new java.awt.Dimension(39, 23));
        btnPanel0T3.add(engBtn6T3);

        engBtn7T3.setText(resourceMap.getString("engBtn7T3.text")); // NOI18N
        engBtn7T3.setName("engBtn7T3"); // NOI18N
        btnPanel0T3.add(engBtn7T3);

        engBtn8T3.setText(resourceMap.getString("engBtn8T3.text")); // NOI18N
        engBtn8T3.setName("engBtn8T3"); // NOI18N
        btnPanel0T3.add(engBtn8T3);

        thBtn8T3.setText(resourceMap.getString("thBtn8T3.text")); // NOI18N
        thBtn8T3.setName("thBtn8T3"); // NOI18N
        btnPanel0T3.add(thBtn8T3);

        thBtn7T3.setText(resourceMap.getString("thBtn7T3.text")); // NOI18N
        thBtn7T3.setName("thBtn7T3"); // NOI18N
        btnPanel0T3.add(thBtn7T3);

        thBtn6T3.setText(resourceMap.getString("thBtn6T3.text")); // NOI18N
        thBtn6T3.setName("thBtn6T3"); // NOI18N
        btnPanel0T3.add(thBtn6T3);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(btnPanel0T3, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPanel0T3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        editUserPanelT6.add(jPanel6, gridBagConstraints);

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(500, 300));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setName("jScrollPane3"); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 200));

        editUserTbT6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Group", "Last Accessed"
            }
        ));
        editUserTbT6.setName("editUserTbT6"); // NOI18N
        editUserTbT6.setRowHeight(20);
        // These are the combobox values
        String[] values = new String[]{"item1", "item2", "item3"};

        // Set the combobox editor on the 1st visible column
        int vColIndex = 1;
        javax.swing.table.TableColumn col = editUserTbT6.getColumnModel().getColumn(vColIndex);
        col.setCellEditor(new javax.swing.DefaultCellEditor(new javax.swing.JComboBox(values)));

        // If the cell should appear like a combobox in its
        // non-editing state, also set the combobox renderer
        col.setCellRenderer(new MyComboBoxRenderer(values));
        jScrollPane3.setViewportView(editUserTbT6);

        jPanel7.add(jScrollPane3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        editUserPanelT6.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        userEditTab.add(editUserPanelT6, gridBagConstraints);

        menuTab.addTab(resourceMap.getString("userEditTab.TabConstraints.tabTitle"), userEditTab); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        menuTab.getAccessibleContext().setAccessibleName(resourceMap.getString("jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N
        menuTab.getAccessibleContext().setAccessibleDescription(resourceMap.getString("jTabbedPane1.AccessibleContext.accessibleDescription")); // NOI18N

        statusPanel.setName("statusPanel"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        databaseLb.setText(resourceMap.getString("databaseLb.text")); // NOI18N
        databaseLb.setName("databaseLb"); // NOI18N

        databaseNameLb.setText(resourceMap.getString("databaseNameLb.text")); // NOI18N
        databaseNameLb.setMaximumSize(new java.awt.Dimension(800, 15));
        databaseNameLb.setMinimumSize(new java.awt.Dimension(50, 15));
        databaseNameLb.setName("databaseNameLb"); // NOI18N
        databaseNameLb.setPreferredSize(new java.awt.Dimension(800, 15));
        defaultcard = new File("defaultcard.csv");

        if(!defaultcard.exists()){
            try {
                defaultcard.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        databaseNameLb.setText(defaultcard.getAbsolutePath());

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusMessageLabel)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addComponent(databaseLb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(databaseNameLb, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusAnimationLabel)))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusMessageLabel)
                            .addComponent(statusAnimationLabel)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(databaseLb)
                        .addComponent(databaseNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        setComponent(mainPanel);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void addTab(String user,JTabbedPane tab){
        tab.addTab("Scanner", scannerTab);
        tab.addTab("Query", queryTab);
        tab.addTab("Result", resultTab);
        tab.addTab("Import & Export", importExportTab);
        tab.addTab("Group", groupTab);
        if(user.equals("admin")){
            tab.addTab("User Edit", userEditTab);
        }
    }

    private void removeTab(String user,JTabbedPane tab){
        if(user.equals("admin")){
            tab.remove(6);
        }
        tab.remove(5);
        tab.remove(4);
        tab.remove(3);
        tab.remove(2);
        tab.remove(1);
    }

    private boolean isPasswordCorrect(String usr,char[] input) {
        boolean isCorrect = true;
        char[] adminPassword = { 'a', 'd', 'm', 'i', 'n' };
        char[] usrPassword = {'u','s','e','r'};

        if(usr.equals("admin")){
            if (input.length != adminPassword.length) {
                isCorrect = false;
            } else {
                isCorrect = Arrays.equals (input, adminPassword);
            }
            //Zero out the password.
            Arrays.fill(adminPassword,'0');
        }
    
        if(usr.equals("user")){
            if (input.length != usrPassword.length) {
                isCorrect = false;
            } else {
                isCorrect = Arrays.equals (input, usrPassword);
            }
            //Zero out the password.
            Arrays.fill(usrPassword,'0');
        }
        return isCorrect;
    }

    private String validatePath(String path){
        String newPath = "";
        String[] temp = path.split("\\\\");
        for(int i=0;i<temp.length;i++){
            if(i<temp.length-1) newPath += temp[i]+"/";
            else newPath+=temp[i];
        }
        return newPath;
    }

    private Hashtable<Integer,Card> updateTable(DefaultTableModel model){
        Hashtable<Integer, Card> resultMapped = new Hashtable<Integer, Card>();
        try {
            localCardList = CardLocalManager.loadLocalCard(defaultcard.getAbsolutePath());
            Object[][] tableArray = new Object[localCardList.size()][];
            int row = 0;
            for (Card card : localCardList) {
                resultMapped.put(row, card);
                tableArray[row] = new Object[]{false, card.getFirstName(), card.getLastName(), card.getPosition(), card.getEmail(), card.getCompany(), card.getMobile(), card.getTelephone(), card.getCountry()};
                row++;
            }
            model.setDataVector(tableArray, new Object[]{"selected", "Name", "Lastname", "Position", "E-mail", "Company", "Mobile", "Telephone", "Country"});
        } catch (ScannerDBException ex) {
            Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultMapped;
    }

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        int response;//0 = yes, 1 = no
        response = javax.swing.JOptionPane.showConfirmDialog(null, "Do you want to save?","Confirm, please",javax.swing.JOptionPane.YES_NO_OPTION);
        //javax.swing.JOptionPane.showMessageDialog(null, response+"", "information", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void saveBtnT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnT3ActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        int response;//0 = yes, 1 = no
        response = javax.swing.JOptionPane.showConfirmDialog(null, "Do you want to save?","Confirm, please",javax.swing.JOptionPane.YES_NO_OPTION);
        if(response==0){
          try {
                String name = nameTfT3.getText();
                String lastName = lastnameTfT3.getText();
                String title = titleTfT3.getText();
                String email = emailTfT3.getText();
                String company = companyTfT3.getText();
                String web = webTfT3.getText();
                String state = stateTfT3.getText();
                String city = cityTfT3.getText();
                String code = codeTfT3.getText();
                String country = countryTfT3.getText();
                String mobile = mobileTfT3.getText();
                String phone = phoneTfT3.getText();
                String fax = faxTfT3.getText();
                String ads = adsTaT3.getText();
                String note = noteTaT3.getText();
                String imgFront = validatePath(frontTfT3.getText());
                String imgBack = validatePath(backTfT3.getText());

                Card editedCard = new Card(name, lastName, title, email, company, web, ads, city, state, country, code, phone, fax, mobile, note, imgFront, imgBack, new Group(), 0);
                editedCard.setId(Long.parseLong(idNameLbT3.getText()));
                
                CardLocalManager.editLocalCard(editedCard, defaultcard.getAbsolutePath());

            } catch (ScannerDBException ex) {
                Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
            }
         JOptionPane.showMessageDialog(null, "Already saved","information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnT3ActionPerformed

    private void databaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databaseBtnActionPerformed
        // TODO add your handling code here:
         //Handle open button action.
        if (evt.getSource() == databaseBtn) {
            dbChooser.addChoosableFileFilter(new DBFileFilter());
            dbChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = dbChooser.showOpenDialog(settingPanel);

            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File file = dbChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                databaseTf.setText(path);

                try {
                    localCardList = CardLocalManager.loadLocalCard(path);
                } catch (ScannerDBException ex) {
                    Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
            }
       }
    }//GEN-LAST:event_databaseBtnActionPerformed

    private void setRefBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRefBtnActionPerformed
        // TODO add your handling code here:
        String defaultPath = databaseTf.getText();
        defaultcard = new File(defaultPath);
        databaseNameLb.setText(defaultPath);
    }//GEN-LAST:event_setRefBtnActionPerformed

    private void saveToDbBtnT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToDbBtnT1ActionPerformed
        // TODO add your handling code here:
            try {
                String name = nameTfT1.getText();
                String lastName = lastnameTfT1.getText();
                String title = titleTfT1.getText();
                String email = emailTfT1.getText();
                String company = companyTfT1.getText();
                String web = webTfT1.getText();
                String state = stateTfT1.getText();
                String city = cityTfT1.getText();
                String code = codeTfT1.getText();
                String country = countryTfT1.getText();
                String mobile = mobileTfT1.getText();
                String phone = phoneTfT1.getText();
                String fax = faxTfT1.getText();
                String ads = adsTaT1.getText();
                String note = noteTaT1.getText();
                String imgFront = validatePath(frontTfT1.getText());
                String imgBack = validatePath(backTfT1.getText());

                Card newCard = new Card(name, lastName, title, email, company, web, ads, city, state, country, code, phone, fax, mobile, note, imgFront, imgBack, new Group(), 0);
                CardLocalManager.addLocalCard(newCard, defaultcard.getAbsolutePath());

            } catch (ScannerDBException ex) {
                Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
            }
        JOptionPane.showMessageDialog(null, "Already Added","information", JOptionPane.INFORMATION_MESSAGE);        
    }//GEN-LAST:event_saveToDbBtnT1ActionPerformed

    private void frontBtnT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frontBtnT1ActionPerformed
        // TODO add your handling code here:
                if (evt.getSource() == frontBtnT1) {
            imgChooser.addChoosableFileFilter(new JPGFileFilter());
            imgChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = imgChooser.showOpenDialog(null);
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File file = imgChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                frontTfT1.setText(path);
                frontLbT1.setIcon(new ImageIcon(path));
            } else {
            }
       }
    }//GEN-LAST:event_frontBtnT1ActionPerformed

    private void backBtnT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnT1ActionPerformed
        // TODO add your handling code here:
            if (evt.getSource() == backBtnT1) {
            imgChooser.addChoosableFileFilter(new JPGFileFilter());
            imgChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = imgChooser.showOpenDialog(null);
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File file = imgChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                backTfT1.setText(path);
                backLbT1.setIcon(new ImageIcon(path));
            } else {
            }
       }
    }//GEN-LAST:event_backBtnT1ActionPerformed

    private void searchT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchT2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)importTableT2.getModel();        
        idMapped = updateTable(model);        
    }//GEN-LAST:event_searchT2ActionPerformed

    private void deletedBtnT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedBtnT2ActionPerformed
        // TODO add your handling code here:
        int response;//0 = yes, 1 = no
        response = javax.swing.JOptionPane.showConfirmDialog(null, "Do you delete?","Confirm, please",javax.swing.JOptionPane.YES_NO_OPTION);
        DefaultTableModel model = (DefaultTableModel) importTableT2.getModel();
        
        if(response ==0){            
            for(int i = 0;i<model.getRowCount();i++){
                if(((Boolean)model.getValueAt(i,0)).booleanValue()==true){
                    try {
                        CardLocalManager.deleteLocalCard(idMapped.get(i).getId(), defaultcard.getAbsolutePath());
                    } catch (ScannerDBException ex) {
                        Logger.getLogger(ScannerView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        idMapped = updateTable(model);
    }//GEN-LAST:event_deletedBtnT2ActionPerformed

    private void editBtnT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnT2ActionPerformed
        // TODO add your handling code here:
        Card editCard = idMapped.get(importTableT2.getSelectedRow());
        menuTab.setSelectedIndex(3);

        String frontPath = validatePath(editCard.getImgFront());
        String backPath = validatePath(editCard.getImgBack());

        idNameLbT3.setText(editCard.getId()+"");
        nameTfT3.setText(editCard.getFirstName());
        lastnameTfT3.setText(editCard.getLastName());
        titleTfT3.setText(editCard.getPosition());
        emailTfT3.setText(editCard.getEmail());
        companyTfT3.setText(editCard.getCompany());
        webTfT3.setText(editCard.getWebsite());
        stateTfT3.setText(editCard.getState());
        cityTfT3.setText(editCard.getCity());
        codeTfT3.setText(editCard.getZip());
        countryTfT3.setText(editCard.getCountry());
        mobileTfT3.setText(editCard.getMobile());
        phoneTfT3.setText(editCard.getTelephone());
        faxTfT3.setText(editCard.getFax());
        adsTaT3.setText(editCard.getAddress());
        noteTaT3.setText(editCard.getNote());
        frontTfT3.setText(frontPath);
        backTfT3.setText(backPath);
        
        frontLbT3.setIcon(new ImageIcon(frontPath));
        backLbT3.setIcon(new ImageIcon(backPath));

        saveBtnT3.setEnabled(true);
    }//GEN-LAST:event_editBtnT2ActionPerformed

    private void frontBtnT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frontBtnT3ActionPerformed
        // TODO add your handling code here:
        if (evt.getSource() == frontBtnT3) {
            imgChooser.addChoosableFileFilter(new JPGFileFilter());
            imgChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = imgChooser.showOpenDialog(null);
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File file = imgChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                frontTfT3.setText(path);
                frontLbT3.setIcon(new ImageIcon(path));
            } else {
            }
       }
    }//GEN-LAST:event_frontBtnT3ActionPerformed

    private void browseExportBtnT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseExportBtnT4ActionPerformed
        // TODO add your handling code here:
        String wd = System.getProperty("user.dir");
        String filename = "";
        JFileChooser fc = new JFileChooser(wd);
        fc.addChoosableFileFilter(new DBFileFilter());

        int rc = fc.showDialog(null, "Select");
        if (rc == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            filename = file.getAbsolutePath();

            exportTfT4.setText(filename);
        // call your function here
        }
    }//GEN-LAST:event_browseExportBtnT4ActionPerformed

    private void exportBtnT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnT4ActionPerformed
        // TODO add your handling code here:
        try{
            CardLocalManager.saveLocalCard(localCardList, exportTfT4.getText());
        }catch(Exception e){}
    }//GEN-LAST:event_exportBtnT4ActionPerformed

    private void scanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanBtnActionPerformed
        // TODO add your handling code here:
        String path = "C:/Documents and Settings/Jenchote/Desktop/scanner/jenCard.jpg";
        frontTfT1.setText(path);
        frontLbT1.setIcon(new ImageIcon(path));

        String path1 = "C:/Documents and Settings/Jenchote/Desktop/scanner/oakCard.jpg";
        backTfT1.setText(path1);
        backLbT1.setIcon(new ImageIcon(path1));
    }//GEN-LAST:event_scanBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        if(isPasswordCorrect("admin",pwdTf.getPassword())){
            usernameTf.setEditable(false);
            pwdTf.setEditable(false);
            loginBtn.setEnabled(false);
            logoutBtn.setEnabled(true);
            addTab("admin",menuTab);
        }else{
            if(isPasswordCorrect("user", pwdTf.getPassword())){
                usernameTf.setEditable(false);
                pwdTf.setEditable(false);
                loginBtn.setEnabled(false);
                logoutBtn.setEnabled(true);
                addTab("user",menuTab);
            }else{
                JOptionPane.showMessageDialog(null,"Wrong Username or Password, Please try again");
            }
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        removeTab(usernameTf.getText(),menuTab);
        usernameTf.setText("");
        pwdTf.setText("");
        usernameTf.setEditable(true);
        pwdTf.setEditable(true);
        loginBtn.setEnabled(true);
        logoutBtn.setEnabled(false);
    }//GEN-LAST:event_logoutBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtnT6;
    private javax.swing.JPanel addNewGroupPanelT5;
    private javax.swing.JPanel addUserPanelT6;
    private javax.swing.JLabel adsLbT1;
    private javax.swing.JLabel adsLbT2;
    private javax.swing.JLabel adsLbT3;
    private javax.swing.JPanel adsPanel;
    private javax.swing.JPanel adsPanel1;
    private javax.swing.JPanel adsPanel2;
    private javax.swing.JScrollPane adsScr;
    private javax.swing.JScrollPane adsScr1;
    private javax.swing.JScrollPane adsScr2;
    private javax.swing.JScrollPane adsScr3;
    private javax.swing.JScrollPane adsScr4;
    private javax.swing.JTextArea adsTaT1;
    private javax.swing.JTextArea adsTaT2;
    private javax.swing.JTextArea adsTaT3;
    private javax.swing.JPanel attachPanel;
    private javax.swing.JPanel attachPanel1;
    private javax.swing.JButton backBtnT1;
    private javax.swing.JButton backBtnT3;
    private javax.swing.JScrollPane backSpT1;
    private javax.swing.JScrollPane backSpT3;
    private javax.swing.JTextField backTfT1;
    private javax.swing.JTextField backTfT3;
    private javax.swing.JToggleButton blackWhiteBtn;
    private javax.swing.JToggleButton blackWhiteBtnT3;
    private javax.swing.JPanel blankPanel0;
    private javax.swing.JPanel blankPanel0T2;
    private javax.swing.JPanel blankPanel0T3;
    private javax.swing.JPanel blankPanel1;
    private javax.swing.JPanel blankPanel1T2;
    private javax.swing.JPanel blankPanel2;
    private javax.swing.JPanel blankPanel3;
    private javax.swing.JPanel blankPanel4;
    private javax.swing.JPanel blankPanel5;
    private javax.swing.JPanel blankPanel6;
    private javax.swing.JPanel blankPanel7;
    private javax.swing.JPanel blankPanelT4;
    private javax.swing.JPanel blankPanelT5;
    private javax.swing.JLabel brightLbT1;
    private javax.swing.JLabel brightLbT3;
    private javax.swing.JPanel brightPanelT1;
    private javax.swing.JPanel brightPanelT3;
    private javax.swing.JSlider brightSldT1;
    private javax.swing.JSlider brightSldT3;
    private javax.swing.JButton browseExportBtnT4;
    private javax.swing.JPanel btnPanel0T2;
    private javax.swing.JPanel btnPanel0T3;
    private javax.swing.JPanel btnPanel1T2;
    private javax.swing.JLabel cityLbT1;
    private javax.swing.JLabel cityLbT2;
    private javax.swing.JLabel cityLbT3;
    private javax.swing.JTextField cityTfT1;
    private javax.swing.JTextField cityTfT2;
    private javax.swing.JTextField cityTfT3;
    private javax.swing.JLabel codeLbT1;
    private javax.swing.JLabel codeLbT2;
    private javax.swing.JLabel codeLbT3;
    private javax.swing.JTextField codeTfT1;
    private javax.swing.JTextField codeTfT2;
    private javax.swing.JTextField codeTfT3;
    private javax.swing.JLabel companyLbT1;
    private javax.swing.JLabel companyLbT2;
    private javax.swing.JLabel companyLbT3;
    private javax.swing.JTextField companyTfT1;
    private javax.swing.JTextField companyTfT2;
    private javax.swing.JTextField companyTfT3;
    private javax.swing.JLabel countryLbT1;
    private javax.swing.JLabel countryLbT2;
    private javax.swing.JLabel countryLbT3;
    private javax.swing.JTextField countryTfT1;
    private javax.swing.JTextField countryTfT2;
    private javax.swing.JTextField countryTfT3;
    private javax.swing.JToggleButton cropBtnT1;
    private javax.swing.JToggleButton cropBtnT3;
    private javax.swing.JButton databaseBtn;
    private javax.swing.JLabel databaseLb;
    private javax.swing.JLabel databaseNameLb;
    private javax.swing.JTextField databaseTf;
    private javax.swing.JPanel deleteEditPanelT2;
    private javax.swing.JButton deletedBtnT2;
    private javax.swing.JToggleButton doubleSideBtnT1;
    private javax.swing.JButton editBtnT2;
    private javax.swing.JPanel editGroupPanelT5;
    private javax.swing.JPanel editUserPanelT6;
    private javax.swing.JTable editUserTbT6;
    private javax.swing.JButton emailBtnT1;
    private javax.swing.JButton emailBtnT3;
    private javax.swing.JLabel emailLbT1;
    private javax.swing.JLabel emailLbT2;
    private javax.swing.JLabel emailLbT3;
    private javax.swing.JTextField emailTfT1;
    private javax.swing.JTextField emailTfT2;
    private javax.swing.JTextField emailTfT3;
    private javax.swing.JToggleButton engBtn0T2;
    private javax.swing.JToggleButton engBtn0T3;
    private javax.swing.JToggleButton engBtn1T2;
    private javax.swing.JToggleButton engBtn1T3;
    private javax.swing.JToggleButton engBtn2T2;
    private javax.swing.JToggleButton engBtn2T3;
    private javax.swing.JToggleButton engBtn3T2;
    private javax.swing.JToggleButton engBtn3T3;
    private javax.swing.JToggleButton engBtn4T2;
    private javax.swing.JToggleButton engBtn4T3;
    private javax.swing.JToggleButton engBtn5T2;
    private javax.swing.JToggleButton engBtn5T3;
    private javax.swing.JToggleButton engBtn6T2;
    private javax.swing.JToggleButton engBtn6T3;
    private javax.swing.JToggleButton engBtn7T2;
    private javax.swing.JToggleButton engBtn7T3;
    private javax.swing.JToggleButton engBtn8T2;
    private javax.swing.JToggleButton engBtn8T3;
    private javax.swing.JButton exBrowseBtnT2;
    private javax.swing.JButton exportBtnT2;
    private javax.swing.JButton exportBtnT4;
    private javax.swing.JLabel exportLbT2;
    private javax.swing.JLabel exportLbT4;
    private javax.swing.JPanel exportPanel;
    private javax.swing.JPanel exportPanelT2;
    private javax.swing.JTextField exportTfT2;
    private javax.swing.JTextField exportTfT4;
    private javax.swing.JLabel faxLbT1;
    private javax.swing.JLabel faxLbT2;
    private javax.swing.JLabel faxLbT3;
    private javax.swing.JTextField faxTfT1;
    private javax.swing.JTextField faxTfT2;
    private javax.swing.JTextField faxTfT3;
    private javax.swing.JButton frontBtnT1;
    private javax.swing.JButton frontBtnT3;
    private javax.swing.JPanel frontPanelT1;
    private javax.swing.JScrollPane frontSpT1;
    private javax.swing.JScrollPane frontSpT3;
    private javax.swing.JTextField frontTfT1;
    private javax.swing.JTextField frontTfT3;
    private javax.swing.JLabel genSearchLbT2;
    private javax.swing.JPanel genSearchPanelT2;
    private javax.swing.JButton genSearchT2;
    private javax.swing.JTextField genSearchTfT2;
    private javax.swing.JButton groupBtnT5;
    private javax.swing.JLabel groupLb;
    private javax.swing.JLabel groupLbT2;
    private javax.swing.JLabel groupLbT6;
    private javax.swing.JPanel groupPanelT5;
    private javax.swing.JPanel groupResultPanelT5;
    private javax.swing.JButton groupSearchBtnT5;
    private javax.swing.JLabel groupSearchLbT5;
    private javax.swing.JTextField groupSearchTfT5;
    private javax.swing.JComboBox groupT6;
    private javax.swing.JPanel groupTab;
    private javax.swing.JTextField groupTfT5;
    private javax.swing.JComboBox grouprCmbT2;
    private javax.swing.JLabel idLbT3;
    private javax.swing.JLabel idNameLbT3;
    private javax.swing.JButton importBrowseBtnT4;
    private javax.swing.JButton importBtnT4;
    private javax.swing.JLabel importDesLbT4;
    private javax.swing.JPanel importExportTab;
    private javax.swing.JLabel importLbT4;
    private javax.swing.JPanel importPanel;
    private javax.swing.JPanel importPanelT4;
    private javax.swing.JPanel importPanelT5;
    private javax.swing.JTable importTableT2;
    private javax.swing.JTable importTableT4;
    private javax.swing.JTextField importTfT4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lastnameLbT1;
    private javax.swing.JLabel lastnameLbT2;
    private javax.swing.JLabel lastnameLbT3;
    private javax.swing.JTextField lastnameTfT1;
    private javax.swing.JTextField lastnameTfT2;
    private javax.swing.JTextField lastnameTfT3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPanel loginP;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel loginTab;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel low;
    private javax.swing.JPanel lowLeftT1;
    private javax.swing.JPanel lowLeftT3;
    private javax.swing.JPanel lowPanelT2;
    private javax.swing.JPanel lowRightT1;
    private javax.swing.JPanel lowRightT3;
    private javax.swing.JPanel lowT3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane menuTab;
    private javax.swing.JLabel mobileLbT1;
    private javax.swing.JLabel mobileLbT2;
    private javax.swing.JLabel mobileLbT3;
    private javax.swing.JTextField mobileTfT1;
    private javax.swing.JTextField mobileTfT2;
    private javax.swing.JTextField mobileTfT3;
    private javax.swing.JLabel nameLbT1;
    private javax.swing.JLabel nameLbT2;
    private javax.swing.JLabel nameLbT3;
    private javax.swing.JTextField nameTfT1;
    private javax.swing.JTextField nameTfT2;
    private javax.swing.JTextField nameTfT3;
    private javax.swing.JLabel noteLbT1;
    private javax.swing.JLabel noteLbT2;
    private javax.swing.JPanel notePanel;
    private javax.swing.JPanel notePanel1;
    private javax.swing.JTextArea noteTaT1;
    private javax.swing.JTextArea noteTaT3;
    private javax.swing.JLabel phoneLbT1;
    private javax.swing.JLabel phoneLbT2;
    private javax.swing.JLabel phoneLbT3;
    private javax.swing.JTextField phoneTfT1;
    private javax.swing.JTextField phoneTfT2;
    private javax.swing.JTextField phoneTfT3;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel pwdLb;
    private javax.swing.JLabel pwdLbT6;
    private javax.swing.JPasswordField pwdTf;
    private javax.swing.JPasswordField pwdTfT6;
    private javax.swing.JPanel queryTab;
    private javax.swing.JPanel quickPanelT2;
    private javax.swing.JLabel resultLbT5;
    private javax.swing.JPanel resultTab;
    private javax.swing.JTable resultTableT5;
    private javax.swing.JButton rotateBtnT1;
    private javax.swing.JButton rotateBtnT3;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton saveBtnT3;
    private javax.swing.JButton saveToDbBtnT1;
    private javax.swing.JButton scanBtn;
    private javax.swing.JButton scannerBtnT1;
    private javax.swing.JComboBox scannerCmbT1;
    private javax.swing.JLabel scannerLbT1;
    private javax.swing.JPanel scannerPanel;
    private javax.swing.JPanel scannerPanel2;
    private javax.swing.JPanel scannerTab;
    private javax.swing.JButton searchT2;
    private javax.swing.JButton setRefBtn;
    private javax.swing.JPanel settingPanel;
    private javax.swing.JLabel stateLbT1;
    private javax.swing.JLabel stateLbT2;
    private javax.swing.JLabel stateLbT3;
    private javax.swing.JTextField stateTfT1;
    private javax.swing.JTextField stateTfT2;
    private javax.swing.JTextField stateTfT3;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel tablePanelT2;
    private javax.swing.JToggleButton thBtn0T2;
    private javax.swing.JToggleButton thBtn0T3;
    private javax.swing.JToggleButton thBtn1T2;
    private javax.swing.JToggleButton thBtn1T3;
    private javax.swing.JToggleButton thBtn2T2;
    private javax.swing.JToggleButton thBtn2T3;
    private javax.swing.JToggleButton thBtn3T2;
    private javax.swing.JToggleButton thBtn3T3;
    private javax.swing.JToggleButton thBtn4T2;
    private javax.swing.JToggleButton thBtn4T3;
    private javax.swing.JToggleButton thBtn5T2;
    private javax.swing.JToggleButton thBtn5T3;
    private javax.swing.JToggleButton thBtn6T2;
    private javax.swing.JToggleButton thBtn6T3;
    private javax.swing.JToggleButton thBtn7T2;
    private javax.swing.JToggleButton thBtn7T3;
    private javax.swing.JToggleButton thBtn8T2;
    private javax.swing.JToggleButton thBtn8T3;
    private javax.swing.JLabel titleLbT1;
    private javax.swing.JLabel titleLbT2;
    private javax.swing.JLabel titleLbT3;
    private javax.swing.JTextField titleTfT1;
    private javax.swing.JTextField titleTfT2;
    private javax.swing.JTextField titleTfT3;
    private javax.swing.JPanel upLabelT3;
    private javax.swing.JPanel upLeftT1;
    private javax.swing.JPanel upLeftT2;
    private javax.swing.JPanel upLeftT3;
    private javax.swing.JPanel upRightT1;
    private javax.swing.JPanel upRightT2;
    private javax.swing.JPanel upRightT3;
    private javax.swing.JPanel userEditTab;
    private javax.swing.JLabel usernameLb;
    private javax.swing.JLabel usernameLbT6;
    private javax.swing.JTextField usernameTf;
    private javax.swing.JTextField usernameTfT6;
    private javax.swing.JLabel webLbT1;
    private javax.swing.JLabel webLbT2;
    private javax.swing.JLabel webLbT3;
    private javax.swing.JTextField webTfT1;
    private javax.swing.JTextField webTfT2;
    private javax.swing.JTextField webTfT3;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    //file chooser
    final javax.swing.JFileChooser dbChooser = new javax.swing.JFileChooser();
    final javax.swing.JFileChooser imgChooser = new javax.swing.JFileChooser();
    private JLabel frontLbT1;
    private JLabel backLbT1;
    private JLabel frontLbT3;
    private JLabel backLbT3;
    //card local manager
    private ArrayList<Card> localCardList;
    private Hashtable<Integer,Card> idMapped;
    private File defaultcard;
    private JDialog aboutBox;
}