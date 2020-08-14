package br.bmcopias.components;


import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

//Referenced classes of package com.javio.joutlookbar:
//         CardPanel, SimpleTabRenderer, TabData, TabRenderer

public class JOutlookToolBar extends JPanel
 implements MouseListener, MouseMotionListener
{
 /* member class not found */
 class SlidingThread {}


 public JOutlookToolBar()
 {
     m_tabs = new Vector();
     m_insets = new Insets(0, 0, 0, 0);
     m_padding = 0;
     m_slideY = 0;
     m_slideYEnd = 0;
     m_slideX = 0;
     m_slideYStart = 0;
     m_speed = 5;
     m_incrementHeight = 10;
     m_newIndex = -1;
     m_oldIndex = -1;
     m_border = new EtchedBorder();
     setOpaque(false);
     m_listeners = new Vector();
     setLayout(null);
     addMouseListener(this);
     addMouseMotionListener(this);
     m_cardPnl = new CardPanel();
     add(m_cardPnl);
     m_tabRenderer = new SimpleTabRenderer();
 }

 public void setTabSpacing(int space)
 {
     m_padding = space;
     revalidate();
 }

 public int getTabSpacing()
 {
     return m_padding;
 }

 public void setSpeed(int speed)
 {
     m_speed = speed;
 }

 public int getSpeed()
 {
     return m_speed;
 }

 public int getSelectedIndex()
 {
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         if(data.m_bSelected)
             return i;
     }

     return -1;
 }

 public void setSelectedIndex(int idx)
 {
     int numTabs = m_tabs.size();
     if(numTabs > 0 && idx >= 0 && idx < numTabs)
         if(getSelectedIndex() != idx)
         {
             TabData data = (TabData)m_tabs.elementAt(idx);
             openTab(data);
         } else
         {
             return;
         }
     throw new IllegalArgumentException("Invalid index!");
 }

 public Component getSelectedComponent()
 {
     TabData data = getSelectedTab();
     if(data != null)
         return data.m_component;
     else
         return null;
 }

 public Dimension getPreferredSize()
 {
     Object obj = getTreeLock();
     int w;
     int h;
     w = m_cardPnl.getPreferredSize().width;
     h = 0;
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         Dimension d = m_tabRenderer.getDimension(data);
         w = Math.max(w, d.width);
         h += d.height;
         if(i > 0)
             h += m_padding;
     }

     h += m_cardPnl.getPreferredSize().height;
     return new Dimension(w, h);
 }

 public Dimension getMinimumSize()
 {
     Object obj = getTreeLock();
     int w;
     int h;
     w = m_cardPnl.getMinimumSize().width;
     h = 0;
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         Dimension d = m_tabRenderer.getDimension(data);
         w = Math.max(w, d.width);
         h += d.height;
         if(i > 0)
             h += m_padding;
     }

     h += m_cardPnl.getMinimumSize().height;
     return new Dimension(w, h);
 }

 public void addPropertyChangeListener(PropertyChangeListener l)
 {
//     m_listeners.addElement(l);
 }

 public void removePropertyChangeListener(PropertyChangeListener l)
 {
//     m_listeners.removeElement(l);
 }

 public void setTabRenderer(TabRenderer r)
 {
     m_tabRenderer = r;
 }

 public TabRenderer getTabRenderer()
 {
     return m_tabRenderer;
 }

 public void addTab(String title, Icon icon, Component c)
 {
     synchronized(getTreeLock())
     {
         TabData data = new TabData(title, c, icon);
         m_tabs.addElement(data);
         data.m_id = "TABID" + m_tabs.indexOf(data);
         m_cardPnl.add(data.m_id, data.m_component);
         if(getSelectedTab() == null)
             data.m_bSelected = true;
         revalidate();
         repaint();
     }
 }

 public int getTabCount()
 {
     return m_tabs.size();
 }

 public int getTabIndex(Component c)
 {
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         if(data.m_component == c)
             return i;
     }

     return -1;
 }

 private TabData getSelectedTab()
 {
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         if(data.m_bSelected)
             return data;
     }

     return null;
 }

 public void removeTab(int index)
 {
     synchronized(getTreeLock())
     {
         if(index < 0 || index > m_tabs.size() - 1)
             throw new IllegalArgumentException("Invalid index for removal from JOutlookBar!");
         TabData data = getTabData(index);
         if(data != null && data.m_bSelected)
             data.m_bSelected = false;
         m_tabs.removeElementAt(index);
         updateIDs();
         doLayout();
         repaint();
     }
 }

 public void insertTab(String title, Icon icon, Component c, int index)
 {
     synchronized(getTreeLock())
     {
         if(index < 0 || index > m_tabs.size())
             throw new IllegalArgumentException("Invalid index for insertion into JOutlookBar!");
         TabData data = new TabData(title, c, icon);
         m_tabs.insertElementAt(data, index);
         updateIDs();
         doLayout();
         repaint();
     }
 }

 private void updateIDs()
 {
     m_cardPnl.removeAll();
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         data.m_id = "TABID" + i;
         m_cardPnl.add(data.m_id, data.m_component);
     }

 }

 public void doLayout()
 {
     synchronized(getTreeLock())
     {
         int tabWidth = getWidth() - m_insets.left - m_insets.right;
         int xPos = m_insets.left;
         int yPos = m_insets.top;
         int numTabs = m_tabs.size();
         int tabYSpace = 0;
         for(int i = 0; i < numTabs; i++)
         {
             TabData data = (TabData)m_tabs.elementAt(i);
             Dimension d = m_tabRenderer.getDimension(data);
             tabYSpace += d.height;
             if(i > 0)
                 tabYSpace += m_padding;
         }

         int compHeight = Math.max(0, getHeight() - tabYSpace - m_insets.top - m_insets.bottom - 1);
         for(int i = 0; i < numTabs; i++)
         {
             TabData data = (TabData)m_tabs.elementAt(i);
             Dimension d = m_tabRenderer.getDimension(data);
             data.m_bounds.x = xPos;
             data.m_bounds.y = yPos;
             data.m_bounds.height = d.height;
             data.m_bounds.width = tabWidth;
             yPos += data.m_bounds.height;
             if(data.m_bSelected)
                 yPos += compHeight;
             else
                 yPos += m_padding;
         }

         TabData selectedTab = getSelectedTab();
         if(selectedTab != null)
         {
             m_cardPnl.setBounds(selectedTab.m_bounds.x, selectedTab.m_bounds.y + selectedTab.m_bounds.height, tabWidth, compHeight);
             m_cardPnl.show(selectedTab.m_id);
             m_cardPnl.setVisible(true);
         } else
         {
             m_cardPnl.setVisible(false);
         }
     }
 }

 public void paint(Graphics g)
 {
     if(m_capturedImg != null)
         g.drawImage(m_capturedImg, 0, 0, null);
     boolean bSliding = m_slideImg != null;
     Rectangle origClip = g.getClipBounds();
     if(bSliding)
         if(m_bSlideDown)
             g.setClip(0, 0, getWidth(), m_slideY);
         else
         if(m_slideImg != null)
         {
             int imgHeight = m_slideImg.getHeight(null);
             int startY = m_slideYStart + imgHeight;
             g.setClip(0, m_slideY, getWidth(), startY - m_slideY);
         }
     paintUI(g);
     g.setClip(origClip);
     m_border.paintBorder(this, g, 0, 0, getWidth(), getHeight());
     if(m_slideImg != null)
         g.drawImage(m_slideImg, m_slideX, m_slideY, null);
 }

 private TabData getTabData(int index)
 {
     if(index >= 0 && index < m_tabs.size())
         return (TabData)m_tabs.elementAt(index);
     else
         return null;
 }

 private Image createTransparentImage(int w, int h)
 {
     BufferedImage img = new BufferedImage(w, h, 2);
     clearImage(img);
     return img;
 }

 private void clearImage(Image img)
 {
     Graphics2D g2D = ((BufferedImage)img).createGraphics();
     g2D.setComposite(AlphaComposite.getInstance(1, 0.0F));
     java.awt.geom.Rectangle2D.Double rect = new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, img.getWidth(null), img.getHeight(null));
     g2D.fill(rect);
 }

 private void captureScreen()
 {
     Dimension d = getSize();
     if(m_capturedImg == null || m_capturedImg.getWidth(null) != d.width || m_capturedImg.getHeight(null) != d.height)
         m_capturedImg = createTransparentImage(d.width, d.height);
     else
         clearImage(m_capturedImg);
     Graphics g = m_capturedImg.getGraphics();
     g.setClip(0, 0, d.width, d.height);
     paintUI(g);
 }

 private void paintScrollUpImage(int newIndex, int oldIndex)
 {
     Dimension d = getSize();
     int imgWidth = d.width;
     int imgHeight = 0;
     for(int i = newIndex; i > oldIndex; i--)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         imgHeight += data.m_bounds.height;
         imgHeight += m_padding;
     }

     if(m_slideImg == null || m_slideImg.getWidth(null) != imgWidth || m_slideImg.getHeight(null) != imgHeight)
         m_slideImg = createTransparentImage(imgWidth, imgHeight);
     else
         clearImage(m_slideImg);
     Graphics g = m_slideImg.getGraphics();
     g.setClip(0, 0, imgWidth, imgHeight);
     if(isOpaque())
     {
         g.setColor(getBackground());
         g.fillRect(0, 0, imgWidth, imgHeight);
     }
     Rectangle r = new Rectangle();
     int yPos = 0;
     int xPos = 0;
     for(int i = oldIndex + 1; i <= newIndex; i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         Rectangle tabRect = data.m_bounds;
         r.x = xPos;
         r.y = yPos;
         r.width = tabRect.width;
         r.height = tabRect.height;
         m_tabRenderer.paintTab(this, g, data, r);
         yPos += tabRect.height + m_padding;
     }

 }

 private void paintScrollDownImage(int newIndex, int oldIndex)
 {
     Dimension d = getSize();
     int imgWidth = d.width;
     int imgHeight = 0;
     for(int i = newIndex + 1; i <= oldIndex; i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         imgHeight += data.m_bounds.height;
         imgHeight += m_padding;
     }

     if(m_slideImg == null || m_slideImg.getWidth(null) != imgWidth || m_slideImg.getHeight(null) != imgHeight)
         m_slideImg = createTransparentImage(imgWidth, imgHeight);
     else
         clearImage(m_slideImg);
     Graphics g = m_slideImg.getGraphics();
     g.setClip(0, 0, imgWidth, imgHeight);
     if(isOpaque())
     {
         g.setColor(getBackground());
         g.fillRect(0, 0, imgWidth, imgHeight);
     }
     Rectangle r = new Rectangle();
     int xPos = 0;
     int yPos = 0;
     for(int i = newIndex + 1; i <= oldIndex; i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         Rectangle tabRect = data.m_bounds;
         r.x = xPos;
         r.y = yPos;
         r.width = tabRect.width;
         r.height = tabRect.height;
         m_tabRenderer.paintTab(this, g, data, r);
         yPos += tabRect.height + m_padding;
     }

 }

 private void paintUI(Graphics g)
 {
     Dimension d = getSize();
     if(isOpaque())
     {
         g.setColor(getBackground());
         g.fillRect(0, 0, d.width, d.height);
     }
     super.paintChildren(g);
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         if(data.m_bSelected || m_newIndex == -1 || m_oldIndex == -1 || i < m_newIndex || i > m_oldIndex)
             m_tabRenderer.paintTab(this, g, data, data.m_bounds);
     }

 }

 private void openTab(TabData data)
 {
//     (new SlidingThread(data)).start();
 }

 private TabData getTabDataAtPosition(int x, int y)
 {
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         if(data.m_bounds.contains(x, y))
             return data;
     }

     return null;
 }

 private void fireEvent(PropertyChangeEvent evt)
 {
     PropertyChangeListener l;
     for(Enumeration e = m_listeners.elements(); e.hasMoreElements(); l.propertyChange(evt))
         l = (PropertyChangeListener)e.nextElement();

 }

 private boolean isSliding()
 {
     return m_slideImg != null;
 }

 private void clearRollover()
 {
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         data.m_bMouseOver = false;
     }

 }

 private void clearPressed()
 {
     for(int i = 0; i < m_tabs.size(); i++)
     {
         TabData data = (TabData)m_tabs.elementAt(i);
         data.m_bPressed = false;
     }

 }

 public void mouseClicked(MouseEvent mouseevent)
 {
 }

 public void mouseEntered(MouseEvent mouseevent)
 {
 }

 public void mouseExited(MouseEvent e)
 {
     setCursor(Cursor.getDefaultCursor());
     clearRollover();
     repaint();
 }

 public void mousePressed(MouseEvent e)
 {
     if(isSliding())
         return;
     TabData data = getTabDataAtPosition(e.getX(), e.getY());
     if(data != null)
     {
         data.m_bPressed = true;
         repaint();
     }
 }

 public void mouseReleased(MouseEvent e)
 {
label0:
     {
         synchronized(this)
         {
             if(!isSliding())
                 break label0;
         }
         return;
     }
     TabData data = getTabDataAtPosition(e.getX(), e.getY());
     if(data != null)
     {
         data.m_bPressed = false;
         if(getSelectedTab() != data)
             openTab(data);
     } else
     {
         clearPressed();
         repaint();
     }
 }

 public void mouseDragged(MouseEvent mouseevent)
 {
 }

 public void mouseMoved(MouseEvent e)
 {
label0:
     {
         synchronized(this)
         {
             if(!isSliding())
                 break label0;
         }
         return;
     }
     clearRollover();
     TabData data = getTabDataAtPosition(e.getX(), e.getY());
     if(data != null)
     {
         data.m_bMouseOver = true;
         setCursor(Cursor.getPredefinedCursor(12));
         repaint();
     } else
     {
         setCursor(Cursor.getDefaultCursor());
     }
     
 }

 private static final String TAB_ID_PREFIX = "TABID";
 private CardPanel m_cardPnl;
 private TabRenderer m_tabRenderer;
 private Image m_slideImg;
 private Image m_capturedImg;
 private Vector m_tabs;
 private Insets m_insets;
 private int m_padding;
 private int m_slideY;
 private int m_slideYEnd;
 private int m_slideX;
 private int m_slideYStart;
 private int m_speed;
 private int m_incrementHeight;
 private Vector m_listeners;
 private int m_newIndex;
 private int m_oldIndex;
 private boolean m_bSlideDown;
 private Border m_border;
















 public interface TabRenderer
 {

     public abstract void paintTab(Component component, Graphics g, TabData tabdata, Rectangle rectangle);

     public abstract Dimension getDimension(TabData tabdata);
 }
 

 public class TabData
 {

     public TabData(String name, Component c, Icon icon)
     {
         m_component = c;
         m_bounds = new Rectangle();
         m_name = name;
         m_icon = icon;
     }

     public String getName()
     {
         return m_name;
     }

     public Icon getIcon()
     {
         return m_icon;
     }

     public String m_id;
     public boolean m_bSelected;
     public Component m_component;
     public Rectangle m_bounds;
     public boolean m_bPressed;
     public boolean m_bMouseOver;
     Icon m_icon;
     String m_name;
 }
 

 class CardPanel extends JPanel
 {

     public CardPanel()
     {
         setLayout(new CardLayout());
     }

     public void show(String s)
     {
         CardLayout lm = (CardLayout)getLayout();
         lm.show(this, s);
     }
 }
 

 public class SimpleTabRenderer
     implements TabRenderer
 {

     public SimpleTabRenderer()
     {
         m_font = new Font("Verdana", 0, 10);
         m_insets = new Insets(5, 10, 5, 10);
         border = new EtchedBorder();
     }

     public Dimension getDimension(TabData data)
     {
         String name = data.getName();
         FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(m_font);
         int h = m_insets.top + fm.getHeight() + m_insets.bottom;
         int w = m_insets.left + fm.stringWidth(name) + m_insets.right;
         return new Dimension(w, h);
     }

     public void paintTab(Component comp, Graphics g, TabData data, Rectangle bounds)
     {
         String title = data.getName();
         g.setFont(m_font);
         FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(m_font);
         int x = bounds.x;
         int y = bounds.y;
         int w = bounds.width;
         int h = bounds.height;
         Graphics2D g2D = (Graphics2D)g;
         g2D.setColor((Color)UIManager.get("Panel.background"));
         g2D.fillRect(x, y, w, h);
         Color c = Color.white;
         border.paintBorder(comp, g, x, y, w, h);
         g.setColor(Color.black);
         int xOffset = 0;
         int space = w - fm.stringWidth(title);
         if(space > 0)
             xOffset = Math.max(0, space / 2);
         g.drawString(title, x + xOffset, y + m_insets.top + fm.getAscent());
     }

     Font m_font;
     Insets m_insets;
     Border border;
 }

public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.add(new JOutlookToolBar());
}










}