package Drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Circle;
import geometry.Donut;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;


public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup btnGroup = new ButtonGroup();
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnSelection;
	private JButton btnModify;
	private JButton btnDelete;
	private PnlDrawing pnlCenter = new PnlDrawing(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setTitle("Vukasin Stanisic IT26");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		PnlDrawing pnlCenter = new PnlDrawing(this);
		contentPane.add(this.pnlCenter, BorderLayout.CENTER);
		this.pnlCenter.setLayout(new GridLayout(0, 8, 0, 0));
		panel.setLayout(new GridLayout(0, 8, 0, 0));
		
		tglbtnPoint = new JToggleButton("Point");
		panel.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		panel.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		panel.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		panel.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		panel.add(tglbtnDonut);
		
		tglbtnSelection = new JToggleButton("Selection");
		panel.add(tglbtnSelection);
		
		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		btnGroup.add(tglbtnRectangle);
		btnGroup.add(tglbtnCircle);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnSelection);
		
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					modify();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		panel.add(btnDelete);
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}
	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}
	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}
	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}
	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}
	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}
	public JButton getBtnModify() {
		return btnModify;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	protected void delete(){
		Shape selected=pnlCenter.getSelected();
		if(selected!=null){
			int reply=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this?","Delete?",JOptionPane.YES_NO_OPTION);
			if(reply==JOptionPane.YES_OPTION)
			{
				pnlCenter.getShapes().remove(selected);
				pnlCenter.deleteSelected();
				pnlCenter.repaint();
			}
			
		}
			
		
	}
	protected void modify() throws Exception{
		Shape selected=pnlCenter.getSelected();
		if(selected!=null){
			if(selected instanceof Point){
				Point point=(Point)selected;
				DlgPoint dlg=new DlgPoint();
				dlg.setPoint(point);
				dlg.setModal(true);
				dlg.setVisible(true);
				if(!dlg.isCommited())
					return;
                selected=dlg.getPoint();
				
			}else if(selected instanceof Line){
				Line line=(Line)selected;
				DlgLine dlg=new DlgLine();
				dlg.setLine(line);
				dlg.setModal(true);
				dlg.setVisible(true);
				if(!dlg.isCommited())
					return;
				 int i=pnlCenter.getShapes().indexOf(selected);
	                pnlCenter.getShapes().remove(selected);
	                pnlCenter.getShapes().add(i, dlg.getLine());
			}else if(selected instanceof Rectangle){
				Rectangle rec=(Rectangle)selected;
				DlgRectangle dlg=new DlgRectangle();
				dlg.setRectangle(rec);
				dlg.setModal(true);
				dlg.setVisible(true);
				if(!dlg.isCommited())
					return;
				 int i=pnlCenter.getShapes().indexOf(selected);
	                pnlCenter.getShapes().remove(selected);
	                pnlCenter.getShapes().add(i, dlg.getRectangle());
		}else if(selected instanceof Donut){
			Donut d=(Donut)selected;
			DlgDonut dlg=new DlgDonut();
			dlg.setDonut(d);
			dlg.setModal(true);
			dlg.setVisible(true);
			if(!dlg.isCommited())
				return;
			 int i=pnlCenter.getShapes().indexOf(selected);
             pnlCenter.getShapes().remove(selected);
             pnlCenter.getShapes().add(i, dlg.getDonut());
	}else if(selected instanceof Circle){
			Circle c=(Circle)selected;
			DlgCircle dlg=new DlgCircle();
			dlg.setCircle(c);
			dlg.setModal(true);
			dlg.setVisible(true);
			if(!dlg.isCommited())
				return;
			 int i=pnlCenter.getShapes().indexOf(selected);
             pnlCenter.getShapes().remove(selected);
             pnlCenter.getShapes().add(i, dlg.getCircle());
	}
}pnlCenter.repaint();
}
}