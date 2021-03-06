/*
 * Created on 17.03.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import util.Font;
import util.ImageTransferHandler;

/**
 * @author Fabian Maximilian Thiele
 */
public class FontEditor extends JInternalFrame implements InternalFrameListener {
	private JScrollPane editorScrollPane;
	private CharEditor charEditor;
	private CharTable charTable;
	private JComboBox colorChooser;
	private JSplitPane splitPane;
	private JTextField widthTxt;
	private Font font;
	private MainWindow mainWnd;
	
	public FontEditor(Font font, MainWindow mainWnd) {
		this.font = font;
		this.mainWnd = mainWnd;
		initGUI();
		initInternalFrame();
		addInternalFrameListener(this);
	}

	private void initGUI() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		ImageTransferHandler handler = new ImageTransferHandler();
		charEditor = new CharEditor(font.getChar(0), this, font.getEditorColorModel(), mainWnd);
		charEditor.setChar(font.getChar(0));
		charEditor.setTransferHandler(handler);
		editorScrollPane = new JScrollPane(charEditor);
		editorScrollPane.setPreferredSize(new Dimension(450,450));
		
		charTable = new CharTable(font, this);
		JTable table = charTable.getTable();
		JScrollPane sp2 = new JScrollPane(table);
		sp2.setPreferredSize(new Dimension(232,100));
		sp2.setMinimumSize(new Dimension(232,100));
		
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(editorScrollPane);
		splitPane.setRightComponent(sp2);
		splitPane.setResizeWeight(1.0);
		splitPane.setOneTouchExpandable(true);
		
		c.add(BorderLayout.CENTER, splitPane);
	}
	
	private void initInternalFrame() {
		pack();
		setTitle(font.getName());
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
	}

	public Font getEditedFont() {
		return font;
	}

	public void setEditingChar(int index) {
		charEditor.setChar(font.getChar(index));
		editingCharSizeChanged();
	}
	
	public void setZoom(int zoom) {
		charEditor.setZoom(zoom);
		editingCharSizeChanged();
	}
	
	public void editingCharSizeChanged() {
		editorScrollPane = new JScrollPane(charEditor);
		splitPane.setLeftComponent(editorScrollPane);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameOpened(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// unused
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameClosing(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// unused
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameClosed(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// unused
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameIconified(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// unused
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameDeiconified(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// unused
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameActivated(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameActivated(InternalFrameEvent arg0) {
		mainWnd.selectedEditorChanged(charEditor);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.InternalFrameListener#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
	 */
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// unused
	}
}
