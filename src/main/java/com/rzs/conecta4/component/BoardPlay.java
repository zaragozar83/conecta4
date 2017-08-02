package com.rzs.conecta4.component;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class BoardPlay extends CustomComponent {

	private static final long serialVersionUID = 3590514266898131300L;
	private String[][] bookmark;
	private GridLayout board = new GridLayout(7, 6);;
	private boolean winner = Boolean.FALSE;
	
	public BoardPlay() {
		board.setSizeFull();
		setHeight(100, Unit.PERCENTAGE);
		setWidth(100, Unit.PERCENTAGE);
		
		init();
		
        setCompositionRoot(board);
        setSizeFull();
		
	}
	
	private void init(){
		bookmark = new String[6][7];
		
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				bookmark[i][j] = "";
			}
		}
	}
	
	public void reset(){
		init();
		board.removeAllComponents();
		winner = Boolean.FALSE;
	}
	
	public boolean insertChip(int col, String symbol){
		while(!winner){
			int row = validSpace(col);
			if(row >= 0){
				bookmark[row][col] = symbol;
				board.addComponent(new Label(symbol), col, row);
				validatePlay(symbol);
				return true;
			}else{
				validatePlay(symbol);
				return false;
			}
		}
		return false;
	}
	
	private int validSpace(int col){
		
		for(int i = 5; i >= 0; i--){
			if(bookmark[i][col] == ""){
				return i;
			}
		}
		return -1;
	}
	
	private void validatePlay(String symbol){
		validateFormVertical(symbol);
		validateFormHorizontal(symbol);
		valiateDiagonal(symbol);
	}
	
	private void validateFormVertical(String symbol){
		for(int i = 0; i < 7; i++){
			int count = 0;
			for(int j = 0; j < 6; j++){
				if(bookmark[j][i] == symbol){
					++count;
					if(count == 4){
						showMessageSuccess();
						break;
					}
				}else{
					count = 0;
				}
			}
		}
	}
	
	private void validateFormHorizontal(String symbol){
		for(int i = 0; i < 6; i++){
			int count = 0;
			for(int j = 0; j < 7; j++){
				if(bookmark[i][j] == symbol){
					++count;
					if(count == 4){
						showMessageSuccess();
						break;
					}
				}else{
					count = 0;
				}
			}
		}
	}
	
	private void valiateDiagonal(String symbol){
		int count = 0;
		int countD3 = 0;
		int countD4 = 0;
		int countD5 = 0;
		int countD6 = 0;
		int countD7 = 0;
		int countD8 = 0;
		
		int countLess1 = 0;
		int countLess2 = 0;
		int countLess3 = 0;
		int countLess4 = 0;
		int countLess5 = 0;
		
		
		for(int i = 5; i >= 0; i--){
			for(int j = 0; j < 7; j++){
				if(i == j){
					if(bookmark[i][j] == symbol){
						++count;
						if(count == 4){
							showMessageSuccess();
							break;
						}
					}
				}else if((i + j) == 3){
					if(bookmark[i][j] == symbol){
						++countD3;
						if(countD3 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countD3 = 0;
					}
				}else if((i + j) == 4){
					if(bookmark[i][j] == symbol){
						++countD4;
						if(countD4 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countD4 = 0;
					}
				}else if((i + j) == 5){
					if(bookmark[i][j] == symbol){
						++countD5;
						if(countD5 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countD5 = 0;
					}
				}else if((i + j) == 6){
					if(bookmark[i][j] == symbol){
						++countD6;
						if(countD6 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countD6 = 0;
					}
				}else if((i + j) == 7){
					if(bookmark[i][j] == symbol){
						++countD7;
						if(countD7 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countD7 = 0;
					}
				}else if((i + j) == 8){
					if(bookmark[i][j] == symbol){
						++countD8;
						if(countD8 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countD8 = 0;
					}
				}
				
				if((i - j) == 2){
					if(bookmark[i][j] == symbol){
						++countLess2;
						if(countLess2 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countLess2 = 0;
					}
				}
				if((i - j) == 1){
					if(bookmark[i][j] == symbol){
						++countLess1;
						if(countLess1 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countLess1 = 0;
					}
				}
				if((j - 1) == 1){
					if(bookmark[i][j] == symbol){
						++countLess3;
						if(countLess3 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countLess3 = 0;
					}
				}
				
				if((j - 1) == 2){
					if(bookmark[i][j] == symbol){
						++countLess4;
						if(countLess4 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countLess4 = 0;
					}
				}
				
				if((j - 1) == 3){
					if(bookmark[i][j] == symbol){
						++countLess5;
						if(countLess5 == 4){
							showMessageSuccess();
							break;
						}
					}else{
						countLess5 = 0;
					}
				}
			}
		}
	}
	
	private void showMessageSuccess(){
		Notification.show("Ganador", "Felicidades haz Ganado!!!", Notification.TYPE_HUMANIZED_MESSAGE);
		winner = Boolean.TRUE;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
