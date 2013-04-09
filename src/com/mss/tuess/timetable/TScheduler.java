package com.mss.tuess.timetable;

/**
 * Reference from http://sourceforge.net/projects/seyongj/
 *
 * @author seyongj
 * @project TScheduler v0.2
 * @last Update: 2013-03-19
 * @reference date 2013-04-08
 */
/**
 * TScheduler v0.2
 */
import com.mss.tuess.entity.SectionClass;
import com.mss.tuess.entitylist.SectionClassList;
import com.mss.tuess.util.State;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class running main method
 */
/**
 * Class for main frame which appears when a program first runs
 */
public class TScheduler extends JFrame {

    Container con = this.getContentPane();
    JList jlShow = new JList();
    JPanel jpEdit = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    JTextField jfClass = new JTextField(20);
    JTextField jfRoom = new JTextField(6);
    JComboBox jcDay = new JComboBox();
    JComboBox jcStartTime;
    JComboBox jcEndTime;
    JComboBox jcMini = new JComboBox();
    JButton jbUpdate = new JButton("Update");
    JButton jbDelete = new JButton("Delete");
    JButton jbClear = new JButton("FormClear");
    JButton jbShow = new JButton("Show Schedule");
    JButton jbEnd = new JButton("End");
    JLabel jlClass = new JLabel("Title");
    JLabel jlRoom = new JLabel("Room");
    JLabel jlDay = new JLabel("Day");
    JLabel jlStartTime = new JLabel("Starting Time");
    JLabel jlEndTime = new JLabel("Ending Time");
    JLabel jlMini = new JLabel("Semester");
    ArrayList alData = new ArrayList();
    DataControl dcData = new DataControl();
    boolean fDouble = false;
    String[] timeList;
    String[] strDay = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    boolean[][] body1;
    boolean[][] body2;
    JMenuBar jmb = new JMenuBar();
    JMenu jmFile = new JMenu("File");
    JMenu jmEdit = new JMenu("Edit");
    JMenuItem jmClear = new JMenuItem("Clear All List");
    JMenuItem jmOpen = new JMenuItem("Open");
    JMenuItem jmSave = new JMenuItem("Save");
    JMenuItem jmExit = new JMenuItem("Exit");
    String[] data = new String[12];

    public void setFrameLocation() {
        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public void setFrameSize() {
        String temp[] = new String[15];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new String(" ");
        }
        jlShow.setListData(temp);
        pack();
        jlShow.removeAll();
        jlShow.updateUI();
    }

    /**
     * Initialize components
     */
    void start() {
        jcStartTime = new JComboBox(makeTime());
        jcEndTime = new JComboBox(makeTime());
        //add Middle Edit Frame
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JPanel jp6 = new JPanel();
        JPanel jp7 = new JPanel();
        jp1.setLayout(new GridLayout(2, 1));
        jp2.setLayout(new GridLayout(2, 1));
        jp3.setLayout(new GridLayout(2, 1));
        jp4.setLayout(new GridLayout(2, 1));
        jp5.setLayout(new GridLayout(2, 1));
        jp6.setLayout(new GridLayout(2, 1));
        jp7.setLayout(new GridLayout(2, 1));
        jp1.add(jlClass);
        jp1.add(jfClass);
        jp2.add(jlRoom);
        jp2.add(jfRoom);
        jp3.add(jlDay);
        jp3.add(jcDay);
        jp4.add(jlStartTime);
        jp4.add(jcStartTime);
        jp5.add(jlEndTime);
        jp5.add(jcEndTime);
        jp6.add(jlMini);
        jp6.add(jcMini);
        jp7.add(new JLabel(" "));
        jp7.add(jbUpdate);
        jpEdit.add(jp1);
        jpEdit.add(jp2);
        jpEdit.add(jp3);
        jpEdit.add(jp4);
        jpEdit.add(jp5);
        jpEdit.add(jp6);
        jpEdit.add(jp7);

        JPanel jpEdit2 = new JPanel();
        jpEdit2.setLayout(new GridLayout(1, 4));
        jpEdit2.add(jbDelete);
        jpEdit2.add(jbClear);
        jpEdit2.add(jbShow);
        jpEdit2.add(jbEnd);

        JPanel jpEdit3 = new JPanel();
        jpEdit3.setLayout(new BorderLayout());
        jpEdit3.add("Center", jpEdit);
        jpEdit3.add("South", jpEdit2);

        //ComboBox content
        jcDay.addItem("Monday");
        jcDay.addItem("Tuesday");
        jcDay.addItem("Wednesday");
        jcDay.addItem("Thursday");
        jcDay.addItem("Friday");
        jcDay.addItem("Saturday");
        jcDay.addItem("Sunday");

        jcMini.addItem("Mini 1");
        jcMini.addItem("Mini 2");
        jcMini.addItem("Full");

        //Button setting
        jbDelete.setEnabled(false);
        jbClear.setEnabled(false);
        jbShow.setEnabled(false);
        jcStartTime.setSelectedIndex(4);
        jcEndTime.setSelectedIndex(7);
        jcMini.setSelectedIndex(2);
        jcStartTime.setMaximumRowCount(20);
        jcEndTime.setMaximumRowCount(20);

        //Menubar Setting
        jmFile.add(jmOpen);
        jmFile.add(jmSave);
        jmFile.addSeparator();
        jmFile.add(jmExit);
        jmEdit.add(jmClear);
        jmb.add(jmFile);
        jmb.add(jmEdit);
        jmSave.setEnabled(false);
        setJMenuBar(jmb);

        //Add to ContentPane
        con.setLayout(new BorderLayout());
        JScrollPane jsp1 = new JScrollPane(jlShow);
        con.add("Center", jsp1);
        //con.add("Center",jlShow);
        con.add("South", jpEdit3);

        //initialize
        timeList = new String[jcStartTime.getItemCount()];
        body1 = new boolean[timeList.length][strDay.length];
        body2 = new boolean[timeList.length][strDay.length];

        for (int i = 0; i < timeList.length; i++) {
            timeList[i] = new String(jcStartTime.getItemAt(i).toString().trim());
        }

        for (int i = 0; i < timeList.length; i++) {
            for (int j = 0; j < strDay.length; j++) {
                body1[i][j] = false;
                body2[i][j] = false;
            }
        }

    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    /**
     * Initailize a JCombobox which contains time range
     */
    public String[] makeTime() {
        String strTime[] = new String[34];

        for (int i = 0; i < 17; i++) {
            strTime[i * 2] = new String();
            //strTime[i*2] = (i+7) + ":00-" + (i+7) +":30";
            if (i < 3) {
                strTime[i * 2] = "0" + (i + 7) + ":00";
            } else {
                strTime[i * 2] = (i + 7) + ":00";
            }
        }
        for (int i = 0; i < 17; i++) {
            strTime[i * 2 + 1] = new String();
            //strTime[i*2+1] = (i+7) + ":30-" + (i+8) +":00";
            if (i < 3) {
                strTime[i * 2 + 1] = "0" + (i + 7) + ":30";
            } else {
                strTime[i * 2 + 1] = (i + 7) + ":30";
            }
        }
        return strTime;
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Update Schedule which the user inputs
     */
    public void update() {

        {
            try {
                SectionClassList.fetch();
                ArrayList<SectionClass> registeredSectionClassList = SectionClassList.getAll();
                int total = registeredSectionClassList.size();
                int num = 0;
                System.out.println("\nTotal=" + total);
                while (num < total) {
                    DataControl dc = new DataControl();
                    dc.setTitle((registeredSectionClassList.get(num).getCourseDept() + registeredSectionClassList.get(num).getCourseNum()).trim());
                    dc.setRoom(registeredSectionClassList.get(num).getLocation().trim());
                    dc.setDay(registeredSectionClassList.get(num).getDay().trim());
                    dc.setStart(registeredSectionClassList.get(num).getDisplayStartTime().trim());
                    dc.setEnd(registeredSectionClassList.get(num).getDisplayEndTime().trim());
                    dc.setSemester(("Full").trim());
                    System.out.println(dc.getStart() + "_~_" + dc.getEnd() + "_" + registeredSectionClassList.get(num).getCourseDept() + "_"
                            + registeredSectionClassList.get(num).getDay() + "_"
                            + registeredSectionClassList.get(num).getCourseNum() + "_" + registeredSectionClassList.get(num).getType()
                            + "_" + registeredSectionClassList.get(num).getClassID());
                    alData.add(dc.getData());

                    num++;
                }
            } catch (SQLException ex) {
            }

            ShowFrame sf = new ShowFrame(alData);
            sf.setVisible(true);
        }
    }

    /**
     * check whether current schedule conflicts with existing schedule return
     * true means that there is no confliction return false means that there is
     * a confliction
     */
    private boolean checkConflict(DataControl dc) {

        return true;
    }

    /**
     * return the index number of parameter d2 in parameter d1
     */
    private int getDayNum(String[] d1, String d2) {

        for (int j = 0; j < d1.length; j++) {
            if (d2.trim().equals(d1[j].trim())) {
                return j;
            }
        }
        return 0;
    }

    /**
     * return the difference between starting time of schedule and ending time
     * of schedule return value is hour * 2 getTimeDifference("09:00", "10:00")
     * will return 2 getTimeDifference("09:00", "11:30") will return 5
     */
    private int getTimeDifference(String start, String end) {
        //all full semester
        int difference = (Integer.parseInt(end.substring(0, 2))
                - Integer.parseInt(start.substring(0, 2))) * 2;
        int minute = Integer.parseInt(end.substring(3, 5))
                - Integer.parseInt(start.substring(3, 5));

        if (minute > 0) {
            difference++;
        } else if (minute < 0) {
            difference--;
        }

        return difference;
    }

    private void update(DataControl dc) {
        if (checkConflict(dc)) {
            jlShow.setFont(new Font("Courier New", Font.PLAIN, 12));
            alData.add(dc.getData());
            jlShow.removeAll();
            jlShow.setListData(alData.toArray());
            jlShow.updateUI();
            if (alData.size() > 0) {
                jbDelete.setEnabled(true);
                jbClear.setEnabled(true);
                jbShow.setEnabled(true);
                jmSave.setEnabled(true);
            }
            jfClass.setText("");
            jfRoom.setText("");
            jcDay.setSelectedIndex(0);
            jcStartTime.setSelectedIndex(4);
            jcEndTime.setSelectedIndex(7);
            jcMini.setSelectedIndex(2);
            jfClass.requestFocus();
        }
    }
}
