package edu.usfca.vas.layout;

import edu.usfca.xj.appkit.frame.XJWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas on 1/24/2017.
 * The class representing the main window of the program, including the left-sidebar and the capability to add Containers
 * to the LeftSideBar. It extends XJWindow so that window.WindowAbstract can extend it, thus maintaining support
 * for the XJ library, but this is something of an idiosyncrasy -- only the model view is actually generated by subclassing
 * this class, while the rest of the views will be instantiated within this class.
 * I'm unaware of any better way to approach this.
 */
public abstract class MainWindow extends XJWindow {
    private LeftSideBar leftSideBar;
    private Container subFrame;

    /**
     * Creates a sub-JPanel within the main JFrame window, and adds the LeftSideBar and its contents to it
     */
    public MainWindow() {
        subFrame = new JPanel();
        subFrame.setPreferredSize(getJFrame().getPreferredSize());
        getJFrame().add(subFrame);
        leftSideBar = new LeftSideBar(JTabbedPane.LEFT);
        getJFrame().add(leftSideBar);
        addSideTab(new JPanel(), "Logo", false, 70, 25);
        addSideTab(subFrame, "Model", 25, 25);
        // The below lines will be replaced with additions of the actual new views
        addSideTab(new JPanel(), "Analytics", 25, 25);
        addSideTab(new JPanel(), "Map", 25, 25);
        leftSideBar.setSelectedIndex(1);
        leftSideBar.setVisible(true);
    }

    /**
     * Adds a JPanel whose image can be found under the given name in sidebar.json to the sidebar of main window
     * @param tab The JPanel to display upon clicking the tab
     * @param name The name to display under the tab and the string under which to look up the image in sidebar.json
     */
    public void addSideTab(Container tab, String name, int width, int height) {
        leftSideBar.addTab(tab, name, width, height);
    }

    /**
     * Adds a JPanel whose image can be found under the given name in sidebar.json to the sidebar of main window,
     * displaying its name if displayText is set to true
     * @param tab The JPanel to display upon clicking the tab
     * @param name The name to display under the tab if displayText is true and the string under which to look up the
     *             image in sidebar.json
     */
    public void addSideTab(Container tab, String name, boolean displayText, int width, int height) {
        if(displayText) {
            leftSideBar.addTab(tab, name, width, height);
        } else {
            leftSideBar.addTab(tab, name, false, width, height);
        }
    }

    /**
     * Instead of returning the base JFrame, the content pane is now set to a JPanel within the LeftSideBar for
     * creation of model view.
     * @return The pane to place model view in
     */
    @Override
    public Container getContentPane() {
        return subFrame;
    }
}