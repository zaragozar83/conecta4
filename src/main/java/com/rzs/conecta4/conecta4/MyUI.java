package com.rzs.conecta4.conecta4;

import javax.servlet.annotation.WebServlet;

import com.rzs.conecta4.component.BoardPlay;
import com.rzs.conecta4.domain.ComponentPlayer;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        
        final Button btnThrow1 = new Button("Tirar");
        final Button btnThrow2 = new Button("Tirar");
        final Button btnReset = new Button("Volver a Jugar");
        
        final ComponentPlayer cPlayer1 = new ComponentPlayer();
        VerticalLayout vComponentPlayer1 = new VerticalLayout();
        vComponentPlayer1.addComponent(cPlayer1);
        vComponentPlayer1.addComponent(btnThrow1);
        
        final ComponentPlayer cPlayer2 = new ComponentPlayer();
        VerticalLayout vComponentPlayer2 = new VerticalLayout();
        vComponentPlayer2.addComponent(cPlayer2);
        vComponentPlayer2.addComponent(btnThrow2);
        
        HorizontalLayout hPlayers = new HorizontalLayout();

        BoardPlay boardPlay = new BoardPlay();
        boardPlay.setEnabled(Boolean.FALSE);
        
        
        hPlayers.addComponent(vComponentPlayer1);
        hPlayers.addComponent(vComponentPlayer2);
        
        layout.addComponent(hPlayers);
        layout.addComponent(btnReset);
        
        boardPlay.setHeight(100, Unit.PERCENTAGE);
        layout.addComponent(boardPlay);
        
        setContent(layout);
        setSizeFull();
        
        
        btnThrow1.addClickListener(new Button.ClickListener() {

        	private static final long serialVersionUID = 7349854510152208111L;

			@Override
			public void buttonClick(ClickEvent event) {
				int col = cPlayer1.getCmbChoose().getValue().intValue();
				if(col >= 0){
					if(boardPlay.insertChip(col, cPlayer1.getTypeSymbol())){
						btnThrow1.setEnabled(Boolean.FALSE);
						btnThrow2.setEnabled(Boolean.TRUE);
					}else{
						if(boardPlay.isWinner()){
							Notification.show("Juego Terminado", "El juego ha terminado, si deseas volver a Jugar da clic en Volver a Jugar", Notification.TYPE_HUMANIZED_MESSAGE);
							btnReset.setEnabled(Boolean.TRUE);
						}else{
							Notification.show("Error", "Esta columna ya se encuentra llena, debes seleccionar otra Columna", Notification.TYPE_WARNING_MESSAGE);							
						}
					}
						
				}
				
			}
		});
        
        btnThrow2.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				int col = cPlayer2.getCmbChoose().getValue().intValue();
				if(col >= 0){
					if(boardPlay.insertChip(col, cPlayer2.getTypeSymbol())){
						btnThrow2.setEnabled(Boolean.FALSE);
						btnThrow1.setEnabled(Boolean.TRUE);
					}else{
						if(boardPlay.isWinner()){
							Notification.show("Juego Terminado", "El juego ha terminado, si deseas volver a Jugar da clic en Volver a Jugar", Notification.TYPE_HUMANIZED_MESSAGE);
							btnReset.setEnabled(Boolean.TRUE);
						}else{
							Notification.show("Error", "Esta columna ya se encuentra llena, debes seleccionar otra Columna", Notification.TYPE_WARNING_MESSAGE);							
						}
					}
						
				}
				
			}
		});
        
        btnReset.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				cPlayer1.reset();
				cPlayer2.reset();
				boardPlay.reset();
				btnReset.setEnabled(Boolean.FALSE);
			}
		});
        
        btnReset.setEnabled(Boolean.FALSE);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
