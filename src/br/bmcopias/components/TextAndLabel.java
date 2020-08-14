package br.bmcopias.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class TextAndLabel extends JPanel
		implements ActionListener, FocusListener, KeyListener
	{
		/**
	 * 
	 */
	private static final long serialVersionUID = 4040947564716794903L;
		boolean bMultiple = false;
		boolean bResync = false;
		
		int maxwidth = 0;

		private JDialog parentDialog = null;
		private JFrame parentFrame = null;

		private boolean fAutoFocus = true;
	        public boolean bPickingItem = false;

		private JTextField textField;
		private JButton btnPesquisar;
		private JTextField lblNome;
		private myMatrix matrix = null;

		private boolean bDirty = false;

		private boolean bLabelEnabled = false;
		private boolean bClearTextOnLabelChange = true;
		private char selChar = 0;

		private Vector<ActionListener> actListener = null;

		private javax.swing.border.Border labelBorder = null;

		public TextAndLabel(String plabelPrompt,
							  int ptextWidth,
							  boolean pbuttonVisible,
							  int pnameWidth)
	  {
	    this(plabelPrompt, ptextWidth, pbuttonVisible, pnameWidth, '\0');
	  }

		public TextAndLabel(String plabelPrompt,
							  int ptextWidth,
							  boolean pbuttonVisible,
							  int pnameWidth,
	              char pselChar)
		{
			super();

			selChar = pselChar;

			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			if (plabelPrompt != null && plabelPrompt.length() > 0)
			{
				JLabel lbl = new JLabel(plabelPrompt, JLabel.LEFT);
				add(lbl);
			}

	    if (selChar != 0)
	      textField = new JTextField(ptextWidth)
	        {
	          /**
				 * 
				 */
				private static final long serialVersionUID = 3490003583249184504L;

			protected void processFocusEvent(FocusEvent e)
	          {
	            super.processFocusEvent(e);
	            verifySelect();
	          }

	          protected void processKeyEvent(KeyEvent e)
	          {
	            verifySelect();
	            super.processKeyEvent(e);
	          }

	          private void verifySelect()
	          {
	            String txt = this.getText();

	            if (txt == null)
	              return;

	            for (int i = 0; i < txt.length(); ++i)
	              if (txt.charAt(i) == selChar)
	              {
	                this.select(i, i + 1);
	                break;
	              }
	          }
	        };
	    else
	      textField = new JTextField(ptextWidth);

			textField.addFocusListener(this);
			textField.addKeyListener(this);

			add(textField);

			if (pbuttonVisible)
			{
				btnPesquisar = new JButton("...")
					{
						/**
						 * 
						 */
						private static final long serialVersionUID = -8293170337605154101L;

						public boolean isFocusTraversable()
						{
							return false;
						}
					};

				btnPesquisar.addActionListener(this);
				Dimension d = textField.getSize();
				btnPesquisar.setMargin(new Insets(0, 0, 0, 0));
				d.width = btnPesquisar.getPreferredSize().width;
				btnPesquisar.setPreferredSize(d);
				add(btnPesquisar);
			}

			lblNome = new JTextField(pnameWidth);
			prepareNome();
			lblNome.setBackground(getBackground());

			if (pnameWidth > 0)
				add(lblNome);
		}

		private void prepareNome()
		{
			lblNome.setEditable(bLabelEnabled);
			lblNome.setEnabled(bLabelEnabled);
			lblNome.addKeyListener(this);
			lblNome.addFocusListener(this);

			labelBorder = lblNome.getBorder();

			if (!bLabelEnabled)
				lblNome.setBorder(null);
		}
		
		//métodos desta classe

		public void setMaxWidth(int maxwidth)
		{
			this.maxwidth = maxwidth;
		}

		public int getMaxWidth()
		{
			return maxwidth;
		}

		public void setMultiple(boolean p1)
		{
			bMultiple = p1;
		}
		
		public boolean getMultiple()
		{
			return bMultiple;
		}
		
		public void setAutoFocus(boolean p1)
		{
			fAutoFocus = p1;
		}

		public boolean getAutoFocus()
		{
			return fAutoFocus;
		}

		public void requestFocus()
		{
			textField.requestFocus();
		}

		protected void processKeyEvent(KeyEvent e)
		{
			textField.setText(String.valueOf(e.getKeyChar()));
		}

		@SuppressWarnings("unchecked")
		public void addActionListener(ActionListener act)
		{
			if (actListener == null)
				actListener = new Vector();

			actListener.addElement(act);
		}

		public void removeActionListener(ActionListener act)
		{
			if (actListener == null)
				return;

			actListener.removeElement(act);
		}

		public void setLabelEnabled(boolean b)
		{
			bLabelEnabled = b;

			if (b == true)
				if (labelBorder != null)
					lblNome.setBorder(labelBorder);

			lblNome.setEditable(b);
			lblNome.setEnabled(b);
		}

		public void setEnabled(boolean enabled)
		{
			textField.setEnabled(enabled);
			btnPesquisar.setEnabled(enabled);
			
			if (!enabled)
			  lblNome.setEnabled(false);
			else
			  lblNome.setEnabled(bLabelEnabled);
		}
		
		public void setClearCodeOnLabelChange(boolean b)
		{
			bClearTextOnLabelChange = b;
		}

		public void addDocumentListener(DocumentListener p1)
		{
			textField.getDocument().addDocumentListener(p1);
			lblNome.getDocument().addDocumentListener(p1);
		}

		//métodos sobrepostos
		public void addKeyListener(KeyListener p1)
		{
			textField.addKeyListener(p1);
			super.addKeyListener(p1);
		}

		//métodos internos

		private void notifyActionListener(boolean itemPicked)
		{
			if (actListener == null)
				return;

			for (int i = 0; i < actListener.size(); ++i)
			{
				ActionListener act = (ActionListener)actListener.elementAt(i);

				act.actionPerformed(new ActionEvent(this, itemPicked ? 1 : 0, getText()));
			}
		}

		/**
		 * Retorna os textos referentes aos codigos informados (separados por virgula)
		 */
		public String getMultipleLabels(String codes)
		{
			if (codes == null)
			  return null;
				
			StringTokenizer st = new StringTokenizer(codes, ", ");
			StringBuffer sb = new StringBuffer();

			while (st.hasMoreTokens())
			{
				String scode = st.nextToken();
				String stext = matrix.searchCode(scode);
				
				if (stext == null)
				  sb.append(", ??" + scode + "??");
				else
				  sb.append(", " + stext);
			}
			
			if (sb.length() > 0)
			  return sb.substring(2);
			else
			  return null;
		}
		
		/**
		 * Retorna os códigos entre vírgula
		 */
		public String getMultipleCodes()
		{
			String codes = textField.getText();
			
			if (codes == null)
			  return null;
				
			StringTokenizer st = new StringTokenizer(codes, ", ");
			StringBuffer sb = new StringBuffer();

			while (st.hasMoreTokens())
				sb.append(", " + st.nextToken());
			
			if (sb.length() > 0)
			  return sb.substring(2);
			else
			  return null;
		}
		
		public void setCurrentMatrix()
		{
			if (matrix == null)
				lblNome.setText(null);
			else if (!bMultiple)
				lblNome.setText(matrix.searchCode(textField.getText()));
			else
			  lblNome.setText(getMultipleLabels(textField.getText()));
		}

		//Métodos

		public void setParent(JFrame pparentFrame)
		{
			parentFrame = pparentFrame;
		}

		public void setParent(JDialog pparentDialog)
		{
			parentDialog = pparentDialog;
		}

		public void setMatrix(myMatrix pmatrix)
		{
			matrix = pmatrix;
		}

		public void setText(String pText)
		{
			textField.setText(pText);
			setCurrentMatrix();
		}

		public String getText()
		{
			return textField.getText();
		}

		public JTextField getTextField()
		{
			return textField;
		}

		public String getLabel()
		{
			return lblNome.getText();
		}

		public JTextField getLabelField()
		{
			return lblNome;
		}

		public void setLabelField(JTextField newlblNome)
		{
			remove(lblNome);
			lblNome = newlblNome;
			prepareNome();
		}
		
		public JButton getSearchButton()
		{
			return btnPesquisar;
		}

		public void storeValues(String text, String label)
		{
			lblNome.setText(label);
			textField.setText(text);
		}

		public void resyncMatrix()
		{
			if (matrix == null)
			  return;
				
			matrix.resync(textField.getText());
		}
		
		public boolean pickItem()
		{
			boolean bResult = false;

			if (matrix == null)
				return false;

			bPickingItem = true;

			if (parentFrame != null)
				bResult = matrix.pickItem(parentFrame);
			else
				bResult = matrix.pickItem(parentDialog);

			bPickingItem = false;

			return bResult;
		}

		public String pickItemText()
		{
			if (matrix == null)
				return null;
			else if (!matrix.getMultiple())
				return matrix.getSubItem(matrix.getSelectedIndex(), 0);
			else
			  return matrix.getMultipleCodes();
		}

		public String pickItemLabel()
		{
			if (matrix == null)
				return null;
			else if (!matrix.getMultiple())
				return matrix.getSubItem(matrix.getSelectedIndex(), 1);
			else
			  return matrix.getMultipleLabels();
		}

		public void runPickItem()
		{
			if (bResync)
			  resyncMatrix();
				
			if (pickItem())
			{
				String scode = pickItemText();
				String stext = pickItemLabel();
				
				if (bMultiple)
				{
					String s = textField.getText();
					if (s != null && s.length() > 0 && (matrix == null || !matrix.getMultiple()))
					{
					  scode = s + ", " + scode;
						stext = lblNome.getText() + ", " + stext;
					}
				}

				textField.setText(scode);
				lblNome.setText(stext);

				notifyActionListener(true);
				
				transferFocus();
			}
			else
			  textField.requestFocus();
		}
		//Eventos

		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();

			if (source == btnPesquisar)
			{
				runPickItem();
			}
		}

		public void focusGained(FocusEvent e)
		{
	                Object source = e.getSource();

			if (fAutoFocus)
			{
				if (source instanceof JTextComponent)
					((JTextComponent)source).selectAll();

			}
		}

		public void focusLost(FocusEvent e)
		{
			Object source = e.getSource();

			if (source == textField)
				verifyUpdate();
		}

		public void verifyUpdate()
		{
			if (bDirty)
			{
				bDirty = false;
				setCurrentMatrix();
				notifyActionListener(false);
			}
		}

		public void keyPressed(KeyEvent e)
		{
			int c = e.getKeyCode();

			if (c == KeyEvent.VK_DELETE)
				keyTyped(e);
			else if (c == KeyEvent.VK_F2)
				runPickItem();
			else if (c == KeyEvent.VK_ENTER)
			{
				final Object source = e.getSource();
				if (source instanceof JComponent)
				{
					Thread t = new Thread(
					  new Runnable()
						{
							public void run()
							{
								SwingUtilities.invokeLater(
									new Runnable()
									{
										public void run()
										{
											JComponent jsource = (JComponent)source;
											jsource.transferFocus();
										}
									}
								);
							}
						}
					);
					t.start();
				}
			}
		}

		public void keyReleased(KeyEvent e)
		{
		}

		public void keyTyped(KeyEvent e)
		{
			Object source = e.getSource();

			if (source == textField)
			{
			  bDirty = true;

				if (maxwidth != 0)
				{
					String s = textField.getText();

					if (s != null && s.length() > 0)
					{
						if (s.length() > maxwidth)
						{
							textField.setText(s.substring(0, maxwidth));
							Toolkit.getDefaultToolkit().beep();
						}
					}
				}
			}
			else if (source == lblNome)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					return;

				if (bLabelEnabled && bClearTextOnLabelChange)
				{
					textField.setText(null);
				}
			}
		}

		public void setResync(boolean pbResync)
		{
			bResync = pbResync;
		}
		
		public boolean getResync()
		{
			return bResync;
		}
		
		
		/******************************************************/

		public class myMatrix {
			private boolean bMultiple = false;
			private String pickHeader = "Seleção";
			private Dimension pickDimension = null;
			private Point pickLocation = null;
			private String colHeaders[] = null;
			private int currentItem = -1;
			private Vector<String[]> items = null; //cada elemento é string[]
			private Vector<Boolean> itemsChk = null;
			//Vetor de Boolean identificando se o correspondente elemento em items está selecionado ou não (utilizado apenas no caso de bMultiple = true)
			private String query = null; //um comando com o paramSub dentro, indicando onde será feita a substituição por 'queryValue'
			private String queryValue = null;
			private String queryName = null; //query utilizada para pesquisar o nome quando digitado o código
			public static final int KEYTYPE_NUMERIC = 1;
			public static final int KEYTYPE_TEXT = 2;
			public static final int KEYTYPE_DATE = 3;
			private int keyType = KEYTYPE_NUMERIC;
//			private final char paramSub = '@';
			private char wildChar = '%';
			private boolean bLeftWild = false;
			private boolean bRightWild = false;
			private java.sql.Connection Conn = null;
			private Component lastParent = null;
			private cpickItem dlg = null;
			private int listElementToText = 1;

			//Variáveis utilizadas na classe pickItem
			private String lastSearch = null;

			public myMatrix() {
			}

			public myMatrix(String ppickHeader, String[] pcolHeaders) {
				pickHeader = ppickHeader;
				colHeaders = pcolHeaders;
			}

			public myMatrix(String ppickHeader, String[] pcolHeaders, java.sql.ResultSet rs) throws java.sql.SQLException {
				pickHeader = ppickHeader;
				colHeaders = pcolHeaders;
				loadItems(rs);
			}

			public myMatrix(java.sql.ResultSet rs) throws java.sql.SQLException {
				loadItems(rs);
			}

			public void setPickHeader(String ppickHeader) {
				pickHeader = ppickHeader;
			}

			public String getPickHeader() {
				return pickHeader;
			}

			public void setLeftWild(boolean pbLeftWild) {
				bLeftWild = pbLeftWild;
			}

			public boolean getLeftWild() {
				return bLeftWild;
			}

			public void setRightWild(boolean pbRightWild) {
				bRightWild = pbRightWild;
			}

			public boolean getRightWild() {
				return bRightWild;
			}

			public void setWildChar(char pwildChar) {
				wildChar = pwildChar;
			}

			public char getWildChar() {
				return wildChar;
			}

			public void setQueryName(String pqueryName) {
				queryName = pqueryName;
			}

			public String getQueryName() {
				return queryName;
			}

			public void setNumericKey(boolean pbNumericKey) {
				if (pbNumericKey)
					keyType = myMatrix.KEYTYPE_NUMERIC;
				else
					keyType = myMatrix.KEYTYPE_TEXT;
			}

			public boolean getNumericKey() {
				return keyType == myMatrix.KEYTYPE_NUMERIC;
			}

			public void setKeyType(int pkeyType) {
				keyType = pkeyType;
			}

			public int getKeyType() {
				return keyType;
			}

			public void setConnection(java.sql.Connection pConn) {
				Conn = pConn;
			}

			public java.sql.Connection getConnection() {
				return Conn;
			}

			public void setPickDimension(Dimension ppickDimension) {
				pickDimension = ppickDimension;
			}

			public Dimension getPickDimension() {
				return pickDimension;
			}

			public void setPickLocation(Point ppickLocation) {
				pickLocation = ppickLocation;
			}

			public Point getPickLocation() {
				return pickLocation;
			}

			public void setColHeaders(String pcolHeaders[]) {
				colHeaders = pcolHeaders;
			}

			public String[] getColHeaders() {
				return colHeaders;
			}

			public void clear() {
				items = null;
				itemsChk = null;
				currentItem = -1;
				lastSearch = null;
			}

			public void addItem(String pitem[]) {
				if (items == null) {
					items = new Vector<String[]>();
					itemsChk = new Vector<Boolean>();
				}

				items.addElement(pitem);

				if (bMultiple)
					itemsChk.addElement(new Boolean(false));
			}

			public void insertItem(String pitem[], int pos) {
				if (items == null) {
					items = new Vector<String[]>();
					itemsChk = new Vector<Boolean>();
				}

				items.insertElementAt(pitem, pos);

				if (bMultiple)
					itemsChk.insertElementAt(new Boolean(false), pos);
			}

			public void setQuery(String pquery) {
				query = pquery;
			}

			public void loadItems(java.sql.ResultSet rs) throws java.sql.SQLException {
				java.sql.ResultSetMetaData mt = rs.getMetaData();

				int colCount = mt.getColumnCount();

				if (colHeaders == null || colCount != colHeaders.length) {
					colHeaders = new String[colCount];
					for (int i = 1; i <= colCount; ++i)
						colHeaders[i - 1] = mt.getColumnName(i);
				}

				items = new Vector<String[]>();
				itemsChk = new Vector<Boolean>();
				currentItem = -1;
				lastSearch = null;

				while (rs.next()) {
					String[] item = new String[colCount];

					for (int i = 1; i <= colCount; ++i)
						item[i - 1] = rs.getString(i);

					items.addElement(item);

					if (bMultiple)
						itemsChk.addElement(new Boolean(false));
				}
			}

			public int getItemCount() {
				if (items == null)
					return 0;
				else
					return items.size();
			}

			public int getSelectedIndex() {
				return currentItem;
			}

			public void select(int s) {
				if (s >= 0 && items != null && s < items.size())
					currentItem = s;
			}

			public String[] getItem(int s) {
				if (s >= 0 && items != null && s < items.size())
					return (String[]) items.elementAt(s);
				else
					return null;
			}

			public String[] getSelectedItem() {
				return getItem(currentItem);
			}

			public String getSubItem(int s, int si) {
				String item[] = getItem(s);

				if (item == null || si < 0 || si >= item.length)
					return null;
				else
					return item[si];
			}

			public String[] getSubItemList(int si) {
				String item[] = new String[getItemCount()];

				for (int i = 0; i < item.length; ++i) {
					String subitem[] = (String[]) items.elementAt(i);
					item[i] = subitem[si];
				}

				return item;
			}

			public int getHeaderCount() {
				if (colHeaders == null)
					return 0;
				else
					return colHeaders.length;
			}

			public int searchKey(String code) {
				if (code == null || code.length() == 0 || items == null)
					return -1;

				int rows = items.size();
				for (int i = 0; i < rows; ++i) {
					String item[] = (String[]) items.elementAt(i);
					if (code.compareToIgnoreCase(item[0]) == 0)
						return i;
				}

				return -1;
			}

			public String getName(String name) {
				int ndx = searchName(name);

				if (ndx == -1)
					return null;
				else
					return this.getSubItem(ndx, 0);
			}

			public int searchName(String code) {
				if (code == null || code.length() == 0 || items == null)
					return -1;

				int rows = items.size();
				for (int i = 0; i < rows; ++i) {
					String item[] = (String[]) items.elementAt(i);
					if (code.compareToIgnoreCase(item[1]) == 0)
						return i;
				}

				return -1;
			}

			public String searchCode(String code) {
				if (code == null || code.length() == 0)
					return null;

				if (queryName == null || queryName.length() == 0) {
					if (items == null)
						return null;

					int rows = items.size();
					for (int i = 0; i < rows; ++i) {
						String item[] = (String[]) items.elementAt(i);
						if (code.compareToIgnoreCase(item[0]) == 0)
							return item[1];
					}
				} else if (Conn != null) {
					java.sql.Statement st;
					String msg = "";
					int j;

					for (j = 0; j < queryName.length(); ++j){
//						if (queryName.charAt(j) == paramSub)
//							if (keyType == KEYTYPE_NUMERIC)
////								msg += myUtil.toSQLInteger(code);
//							else if (keyType == KEYTYPE_DATE)
//								msg += myUtil.toSQLDate(code);
//							else
//								msg += myUtil.toSQLText(code);
//						else
//							msg += queryName.substring(j, j + 1);
						System.out.println(j);
					}

					try {
						st = Conn.createStatement();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
						return null;
					}

					try {
		                
		                System.out.println("myMatrix-378 :" + msg);
						java.sql.ResultSet rs = st.executeQuery(msg);

						String r = null;

						if (rs.next())
							r = rs.getString(1);

						rs.close();

						return r;
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
						return null;
					} finally {
						try {
							st.close();
						} catch (Exception ex) {
						}
					}
				}

				return null;
			}

			@SuppressWarnings("deprecation")
			public boolean pickItem(Frame f) {
				if (f != lastParent) {
					dlg = new cpickItem(f, pickHeader);
					lastParent = f;
				}

				dlg.show();

				return dlg.idResult == dlg.IDOK;
			}

			@SuppressWarnings("deprecation")
			public boolean pickItem(Dialog f) {
				if (f != lastParent) {
					dlg = new cpickItem(f, pickHeader);
					lastParent = f;
				}

				dlg.show();

				return dlg.idResult == dlg.IDOK;
			}

			private void requery(String value, cpickItem pick) {
//				String msg = "";
//				int j;
//
//				if (query == null || value == null || Conn == null)
//					return;
//
//				queryValue = value;
//
//				if (bLeftWild)
//					value = "*" + value;
//
//				if (bRightWild)
//					value += "*";
//
//				for (j = 0; j < query.length(); ++j)
//					if (query.charAt(j) == paramSub)
//						msg += myUtil.toSQLToken(value, wildChar);
//					else
//						msg += query.substring(j, j + 1);
//
//				java.sql.Statement st;
//
//				try {
//					st = Conn.createStatement();
//				} catch (Exception e) {
//					JOptionPane.showMessageDialog(pick, e);
//					return;
//				}
//
//				try {
//					if (dlg != null)
//						dlg.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//
//					java.sql.ResultSet rs = st.executeQuery(msg);
//
//					loadItems(rs);
//
//					rs.close();
//
//					if (getItemCount() > 0)
//						pick.searchList.setSelectedIndex(0);
//
//					pick.lmList.reset();
//
//					JScrollBar vsb = pick.searchListPanel.getVerticalScrollBar();
//					vsb.setValue(vsb.getMinimum());
//				} catch (Exception e) {
//					JOptionPane.showMessageDialog(pick, e);
//					return;
//				} finally {
//					try {
//						st.close();
//					} catch (Exception ex) {
//					}
//
//					if (dlg != null)
//						dlg.setCursor(Cursor.getDefaultCursor());
//				}

			}

			private class cpickItem
				extends JDialog
				implements ActionListener, KeyListener, FocusListener, ListSelectionListener, DocumentListener {
				/**
				 * 
				 */
				private static final long serialVersionUID = 3593490641372706856L;

				private boolean bSkipTextEvent = false;

				public final int IDUNDEFINED = -1;
				public final int IDOK = 0;
				public final int IDCANCEL = 1;
				public int idResult = IDUNDEFINED;

				private JTextField filterField = null;
				private JTextField searchField;
				LmList lmList;
				JList searchList;
				JScrollPane searchListPanel;
				private JButton btnOk;
				private JButton btnCancel;

				private boolean bFirstTime = true;

//				private boolean prvIsEnabled;
				private boolean bRequery = false;

				//private Font fontNormal = null;

				public cpickItem(Dialog powner, String ppickHeader) {
					super(powner, ppickHeader, true);

					//if (powner != null)
					//	fontNormal = powner.getFont();

					cpickItemInternal();
				}

				public cpickItem(Frame powner, String ppickHeader) {
					super(powner, ppickHeader, true);

					//if (powner != null)
					//	fontNormal = powner.getFont();

					cpickItemInternal();
				}

				private void cpickItemInternal() {
					setResizable(true);

					//if (fontNormal == null)
					//	fontNormal = new Font("Helvetica", Font.PLAIN, 12);

					//setFont(fontNormal);

					addKeyListener(this);

					btnOk = new JButton("Ok");
					btnOk.setDefaultCapable(true);
					btnOk.addActionListener(this);
					btnOk.setDefaultCapable(true);

					btnCancel = new JButton("Cancelar");
					btnCancel.addActionListener(this);

					lmList = new LmList();
					searchList = new JList(lmList);
					searchList.addListSelectionListener(this);
					searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					if (bMultiple) {
						searchList.setCellRenderer(new CrList());
						searchList.setPrototypeCellValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

						searchList.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								lmList.toggleItem(searchList.getSelectedIndex());
							}
						});

						searchList.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_SPACE) {
									lmList.toggleItem(searchList.getSelectedIndex());
								}
							}
						});
					}

					//searchList.setFont(fontNormal);

					searchListPanel = new JScrollPane(searchList);

					searchList.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 2) //double click
								actionPerformed(new ActionEvent(searchList, ActionEvent.ACTION_PERFORMED, "Double_Clicked"));
						}
					});

					bSkipTextEvent = true;

					searchField = new JTextField();
					//searchField.setFont(fontNormal);
					searchField.addActionListener(this);
					searchField.getDocument().addDocumentListener(this);
					searchField.addFocusListener(this);
					searchField.setText(lastSearch);
					searchField.addKeyListener(this);

					bSkipTextEvent = false;

					Container c = getContentPane();

					c.setLayout(new BorderLayout(0, 0));

					JPanel pnl = new JPanel(new BorderLayout(0, 0));
					//pnl.setFont(fontNormal);

					if (query != null && query.length() > 0) {
						filterField = new JTextField(30);
						//filterField.setFont(fontNormal);
						filterField.setText(queryValue);
						filterField.getDocument().addDocumentListener(this);
						filterField.addActionListener(this);
						filterField.addFocusListener(this);

						JPanel pnlFilter = new JPanel(new BorderLayout(0, 0));
						//pnlFilter.setFont(fontNormal);
						JPanel pnlFilterA = new JPanel(new BorderLayout(0, 0));
						//pnlFilterA.setFont(fontNormal);
						pnlFilterA.add(BorderLayout.WEST, new JLabel("Filtrar:", JLabel.RIGHT));
						pnlFilterA.add(BorderLayout.CENTER, filterField);
						pnlFilter.add(BorderLayout.NORTH, pnlFilterA);
						pnlFilter.add(BorderLayout.SOUTH, searchField);

						pnl.add(BorderLayout.NORTH, pnlFilter);
					} else
						pnl.add(BorderLayout.NORTH, searchField);

					pnl.add(BorderLayout.CENTER, searchListPanel);

					c.add(BorderLayout.CENTER, pnl);

					pnl = new JPanel();
					pnl.add(btnOk);
					pnl.add(btnCancel);

					c.add(BorderLayout.SOUTH, pnl);

					this.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							cpickItem.this.dispose();
						}

						public void windowActivated(WindowEvent event) {
							getRootPane().setDefaultButton(btnOk);
							searchField.requestFocus();
						}
					});

					pack();

					if (pickLocation == null) {
						Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
						Dimension ts = getSize();
						setLocation((ss.width - ts.width) / 2, (ss.height - ts.height) / 2);
					} else
						setLocation(pickLocation);

					bSkipTextEvent = true;

					if (currentItem >= 0)
						searchList.setSelectedValue(getSubItem(currentItem, 1), true);

					searchField.requestFocus();

					bSkipTextEvent = false;
				}

				private int search(String txt) {
					if (txt == null || txt.length() == 0)
						return -1;

					String utxt = txt.toUpperCase();

					for (int i = 0; i < getItemCount(); ++i) {
						String s = getSubItem(i, listElementToText);

						if (s != null)
							s = s.toUpperCase();

						if (s != null && s.startsWith(utxt))
							return i;
					}

					return -1;
				}

				public Dimension getPreferredSize() {
					if (pickDimension == null)
						return super.getPreferredSize();
					else
						return pickDimension;
				}

				public void dispose() {
					super.dispose();

					pickDimension = getSize();
					pickLocation = getLocation();
				}

				private void syncList() {
					if (!bSkipTextEvent) {
						bSkipTextEvent = true;

						int ndx = search(searchField.getText());

						if (ndx >= 0)
							searchList.setSelectedValue(getSubItem(ndx, 1), true);
						else
							searchList.setSelectedIndex(-1);

						bSkipTextEvent = false;
					}
				}

				public void insertUpdate(DocumentEvent e) {
					Document source = e.getDocument();

					if (bSkipTextEvent)
						return;

					if (searchField != null && source == searchField.getDocument()) {
						syncList();
					} else if (filterField != null && source == filterField.getDocument()) {
						bRequery = true;
					}
				}

				public void removeUpdate(DocumentEvent e) {
					Document source = e.getDocument();

					if (bSkipTextEvent)
						return;

					if (searchField != null && source == searchField.getDocument()) {
						syncList();
					} else if (filterField != null && source == filterField.getDocument()) {
						bRequery = true;
					}
				}

				public void changedUpdate(DocumentEvent e) {
					Document source = e.getDocument();

					if (bSkipTextEvent)
						return;

					if (searchField != null && source == searchField.getDocument()) {
						syncList();
					} else if (filterField != null && source == filterField.getDocument()) {
						bRequery = true;
					}
				}

				public void valueChanged(ListSelectionEvent e) {
					Object source = e.getSource();

					if (e.getValueIsAdjusting())
						return;

					if (source == searchList) {
						if (!bSkipTextEvent) {
							bSkipTextEvent = true;
							String[] item = getItem(searchList.getSelectedIndex());
							if (item != null)
								searchField.setText(item[listElementToText]);
							bSkipTextEvent = false;
						}
					}
				}

				public void actionPerformed(ActionEvent evt) {
					Object source = evt.getSource();

					if (source == btnOk || source == btnCancel || source == searchField || source == searchList) {
						if (source == searchList) {
							String ac = evt.getActionCommand();
							if (!ac.equalsIgnoreCase("Double_Clicked"))
								return;
						}

						if (source != btnCancel) {
							int sel = searchList.getSelectedIndex();

							if (sel == -1)
								return;

							idResult = IDOK;
							currentItem = sel;
							lastSearch = getSubItem(sel, 1);
						} else
							idResult = IDCANCEL;

						dispose();
					} else if (filterField != null && source == filterField) {
						String value = filterField.getText();
						if (value != null && value.length() > 0) {
							searchField.setText(null);
							requery(value, this);
							searchField.requestFocus();
						}
					}
				}

				public void focusGained(FocusEvent e) {
					Object source = e.getSource();

					if (source == filterField) {
						if (bFirstTime) {
							bFirstTime = false;
							searchField.requestFocus();
						}
					} else if (source == searchField) {
						String s = searchField.getText();
						if (s != null && s.length() > 0)
							searchField.selectAll();
					}
				}

				public void focusLost(FocusEvent e) {
					Object source = e.getSource();

					if (source == filterField) {
						if (bRequery) {
							bRequery = false;

							String value = filterField.getText();
							if (value != null && value.length() > 0) {
								searchField.setText(null);
								requery(value, this);
								searchField.requestFocus();
							}
						}
					}
				}

				public void keyPressed(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
					Object source = e.getSource();

					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) //escape
						{
						btnCancel.doClick();
						return;
					}

					if (source == searchField) {
						int key = e.getKeyCode();
						switch (key) {
							case KeyEvent.VK_DOWN :
							case KeyEvent.VK_UP :
							case KeyEvent.VK_PAGE_DOWN :
							case KeyEvent.VK_PAGE_UP :
								searchList.requestFocus();
								break;
						}
					}
				}

				public void keyTyped(KeyEvent e) {
				}

			}

			private class LmList extends AbstractListModel {
				/**
				 * 
				 */
				private static final long serialVersionUID = -1045496877598667201L;
				int ListCount = 0;

				private void toggleItem(int index) {
					if (itemsChk == null || index < 0 || index >= itemsChk.size())
						return;

					boolean f = !((Boolean) itemsChk.get(index)).booleanValue();

					itemsChk.setElementAt(new Boolean(f), index);

					fireContentsChanged(this, index, index);
				}

				public Object getElementAt(int index) {
					String[] item = getItem(index);

					if (item != null)
						return item[1];
					else
						return null;
				}

				public int getSize() {
					return getItemCount();
				}

				public void reset() {
					if (ListCount > 0)
						this.fireIntervalRemoved(this, 0, ListCount - 1);

					ListCount = getSize();

					if (ListCount > 0)
						fireIntervalAdded(this, 0, ListCount - 1);
				}
			}

			public void setMultiple(boolean p1) {
				bMultiple = p1;
			}

			public boolean getMultiple() {
				return bMultiple;
			}

			private class CrList extends DefaultListCellRenderer {
				/**
				 * 
				 */
				private static final long serialVersionUID = -5660080965191653555L;
				JPanel pnl = null;
				JCheckBox chk = null;
				JLabel lbl = null;

				public CrList() {
					pnl = new JPanel(new BorderLayout(0, 0));

					chk = new JCheckBox();
					chk.setOpaque(false);

					lbl = new JLabel();
					lbl.setHorizontalAlignment(JLabel.LEFT);
					lbl.setOpaque(false);

					pnl.add(BorderLayout.WEST, chk);
					pnl.add(BorderLayout.CENTER, lbl);
				}

				public Component getListCellRendererComponent(
					JList list,
					Object value,
					int index,
					boolean isSelected,
					boolean cellHasFocus) {
					boolean f = false;

					if (itemsChk != null && index < itemsChk.size())
						f = ((Boolean) itemsChk.get(index)).booleanValue();

					chk.setSelected(f);
					lbl.setText(getSubItem(index, 1));

					if (isSelected) {
						pnl.setBackground(list.getSelectionBackground());
						pnl.setForeground(list.getSelectionForeground());
					} else {
						pnl.setBackground(list.getBackground());
						pnl.setForeground(list.getForeground());
					}

					lbl.setFont(list.getFont());
					lbl.setForeground(pnl.getForeground());

					return pnl;
				}
			}

			/**
			 * Retorna os códigos entre vírgula
			 */
			public String getMultipleCodes() {
				String s = "";

				if (itemsChk == null)
					return null;

				for (int i = 0; i < itemsChk.size(); ++i) {
					if (!((Boolean) itemsChk.get(i)).booleanValue())
						continue;

					s += ", " + getSubItem(i, 0);
				}

				if (s.length() > 0)
					return s.substring(2);
				else
					return null;
			}

			/**
			 * Retorna os labels entre vírgula
			 */
			public String getMultipleLabels() {
				String s = "";

				if (itemsChk == null)
					return null;

				for (int i = 0; i < itemsChk.size(); ++i) {
					if (!((Boolean) itemsChk.get(i)).booleanValue())
						continue;

					s += ", " + getSubItem(i, 1);
				}

				if (s.length() > 0)
					return s.substring(2);
				else
					return null;
			}

			/**
			 * Resincroniza a matriz conforme os códigos informados entre vírgulas
			 */
			public void resync(String codes) {
				if (codes == null || itemsChk == null)
					return;

				//Zera todos os booleanos
				for (int i = 0; i < itemsChk.size(); ++i)
					itemsChk.setElementAt(new Boolean(false), i);

				StringTokenizer st = new StringTokenizer(codes, ", ");

				while (st.hasMoreTokens()) {
					String scode = st.nextToken();

					int index = searchKey(scode);

					if (index == -1)
						continue;

					itemsChk.setElementAt(new Boolean(true), index);
				}

				if (dlg != null)
					dlg.lmList.reset();
			}

			public void setListElementToText(int listElementToText) {
				this.listElementToText = listElementToText;
			}

			public int getListElementToText() {
				return listElementToText;
			}
		}
		

}
