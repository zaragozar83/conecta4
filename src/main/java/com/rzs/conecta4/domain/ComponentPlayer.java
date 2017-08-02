package com.rzs.conecta4.domain;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ComponentPlayer extends CustomComponent {

	private static final long serialVersionUID = 1L;
	public final static int maxTurns = 21;
	private boolean turn = true;
	private String typeSymbol;
	private TextField txtName = new TextField();
	private TextField txtSymbol = new TextField();
	private ComboBox<Integer> cmbChoose = new ComboBox<>("Selecciona la Columna donde desea realizar tu siguiente tiro");
	private Button btnSave = new Button("Guardar");
	private VerticalLayout vComponent;
	private HorizontalLayout hComponent;
	private Panel panel;
	
	public ComponentPlayer(){
		
		init();
		
		setCompositionRoot(panel);
	
	}
	
	public void init(){
		panel = new Panel();
		
		txtName.setCaption("Agrega el nombre del Jugador");
		
		
		txtSymbol.setCaption("Agrega el simbolo del Jugador");
		
		
		vComponent = new VerticalLayout();
		
		hComponent = new HorizontalLayout();
		hComponent.addComponent(txtName);
		hComponent.addComponent(txtSymbol);
		
		vComponent.addComponent(hComponent);
		vComponent.addComponent(btnSave);
		
		
		hComponent.setEnabled(turn);
		
		btnSave.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(!txtName.getValue().isEmpty()
						&& txtName.getValue() != ""){
					
					if(!txtSymbol.getValue().isEmpty()
							&& txtSymbol.getValue() != ""){
						typeSymbol = txtSymbol.getValue();
						
						hComponent.setVisible(Boolean.FALSE);
						btnSave.setVisible(Boolean.FALSE);
						cmbChoose.setEnabled(Boolean.TRUE);
					}else{
						Notification.show("Revisar los valores en el cambo de Simbolo" ,"Es necesario que ingreses un symbolo para poder jugar", Notification.TYPE_WARNING_MESSAGE);
					}
					
					panel.setCaption(txtName.getValue());
					
				}else{
					Notification.show("Revisar los valores en el cambo de Simbolo" , "Es necesario que ingreses el nombre del Jugador", Notification.TYPE_WARNING_MESSAGE);
				}
				
				
			}
		});
				
		Collection<Integer> cols = new ArrayList<>();
		cols.add(0);
		cols.add(1);
		cols.add(2);
		cols.add(3);
		cols.add(4);
		cols.add(5);
		cmbChoose.setItems(cols);
		cmbChoose.setEmptySelectionAllowed(Boolean.FALSE);
		cmbChoose.setValue(0);
		
		cmbChoose.setEnabled(!turn);
		
		vComponent.addComponent(cmbChoose);
		
		panel.setContent(vComponent);
	}
	
	public void reset(){
		hComponent.setVisible(Boolean.TRUE);
		btnSave.setVisible(Boolean.TRUE);
		cmbChoose.setValue(0);
		cmbChoose.setEnabled(Boolean.FALSE);
		panel.setCaption("");
		txtName.setValue("");
		txtSymbol.setValue("");
		btnSave.setEnabled(Boolean.TRUE);
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public String getTypeSymbol() {
		return typeSymbol;
	}

	public void setTypeSymbol(String typeSymbol) {
		this.typeSymbol = typeSymbol;
	}

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(TextField txtName) {
		this.txtName = txtName;
	}

	public TextField getTxtSymbol() {
		return txtSymbol;
	}

	public void setTxtSymbol(TextField txtSymbol) {
		this.txtSymbol = txtSymbol;
	}

	public ComboBox<Integer> getCmbChoose() {
		return cmbChoose;
	}

	public void setCmbChoose(ComboBox<Integer> cmbChoose) {
		this.cmbChoose = cmbChoose;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public VerticalLayout getvComponent() {
		return vComponent;
	}

	public void setvComponent(VerticalLayout vComponent) {
		this.vComponent = vComponent;
	}

	public HorizontalLayout gethComponent() {
		return hComponent;
	}

	public void sethComponent(HorizontalLayout hComponent) {
		this.hComponent = hComponent;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
}
