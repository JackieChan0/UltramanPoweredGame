package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entity.Entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	//BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	//public String message = "";
	//int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0;	//0: the first screen
	
	
	
	//double playTime;
	//DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		 arial_40 = new Font("Ariel", Font.PLAIN, 40);
		 arial_80B = new Font("Ariel", Font.BOLD, 80);
		 //OBJ_Key key = new OBJ_Key(gp);
		 //keyImage = key.image;

		 //SuperObject heart = new OBJ_Heart(gp);
		 Entity heart = new OBJ_Heart(gp);
		 heart_full = heart.image;
		 heart_half = heart.image2;
		 heart_blank = heart.image3;
		 
	}
	public void addMessage(String text) {
		
		//showMessage
		//message = text;
		//messageOn = true;
		
		message.add(text);
		messageCounter.add(0);
		
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		
		//play state
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		
		//pause state
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		//dialogue state
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		//character state
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
		}
	/***
	 //archival purposes
	 if(gameFinished == true){
	 
	 g2.setFont(arial_40
	 g2.setColor(Color.white);
	 
	 String text;
	 int textLength;
	 int x;
	 int y;
	 
	 text = "You found the treasure!";
	 textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	 x = gp.screenWidth/2 - textLength/2;
	 y = gp.screenHeight/2 - (gp.tileSize*3);
	 g2.drawString(text, x, y);
	  
	 text = "Your time is " + dFormat.format(playTime) + "!";
	 textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	 x = gp.screenWidth/2 - textLength/2;
	 y = gp.screenHeight/2 + (gp.tileSize*4);
	 g2.drawString(text, x, y);
	  
	 g2.setFont(arial_80B);
	 g2.setColor(Color.yellow);
	 text = "Congratulations!";
	 textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	 x = gp.screenWidth/2 - textLength/2;
	 y = gp.screenHeight/2 + (gp.tileSize*2);
	 g2.drawString(text, x, y);
	 
	 gp.gameThread = null;
	 
	 }
	 else{
	 
	 g2.setFont(arial_40);
	 g2.setColor(Color.white);
	 g2.drawString("Key = " + gp.player.hasKey, 25, 50); 
	 g2.drawString(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
	 g2.drawString("x " + gp.player.hasKey, 74, 65);
	 
	 //TIME
	 playTime +=(double)1/60;
	 g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);
	 g2.drawString("Time:" + dFormat.format(37.14), gp.tileSize*11, 65);
	 
	 //message
	 if(messageOn == true){
	 	g2.setFont(g2.getFont().deriveFont(30F));
	 	g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
	 	
	 	messageCounter++;
	 	
	 	if(messageCounter > 120){
	 		messageCounter = 0;
	 		messageOn = false;
	 	}
	 }
	  
	 }
	 	  
	 
	 * 
	 */
		
	}
	public void drawPlayerLife() {
		//gp.player.life = 6;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// draw blank heart
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		//reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//current life
		while(i < gp.player.life) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
	}
	
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
				
			}
			
		}
		
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			//title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));
			String text = "Ultraman Powered's Quest";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			//shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+3, y+3);
			//main color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// character image
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
			//menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48f));
			
			text = "NEW GAME";		
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum ==0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "LOAD GAME";		
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "QUIT";		
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		
		else if(titleScreenState == 1) {
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select Your Class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Thief";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
					
		}
		
		/**		
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));
		String text = "Ultraman Powered's Quest";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		//shadow
		g2.setColor(Color.gray);
		g2.drawString(text, x+3, y+3);
		//main color
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// character image
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
	
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48f));
		
		text = "NEW GAME";		
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if(commandNum ==0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";		
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";		
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		*/
		
	}
	
	public void drawPauseScreen() {
		
		//g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";	
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		}
	
	public void drawDialogueScreen() {
		
		//window
		int x = gp.tileSize*2;;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
			
		}
		//g2.drawString(currentDialogue, x, y);
	}
	
	public void drawCharacterScreen() {
		
		//create a frame
		final int frameX = gp.tileSize*2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//text
				g2.setColor(Color.white);
				g2.setFont(g2.getFont().deriveFont(32F));
				
				int textX = frameX + 20;
				int textY = frameY + gp.tileSize;
				final int lineHeight = 35;
				
				//names
				g2.drawString("Level", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Life", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Strength", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Dexterity", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Attack", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Defense", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Exp", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Next Level", textX, textY);
				textY += lineHeight;
				
				g2.drawString("Coin", textX, textY);
				textY += lineHeight + 20;
				
				g2.drawString("Weapon", textX, textY);
				textY += lineHeight + 15;
				
				g2.drawString("Shield", textX, textY);
				textY += lineHeight;
				
				//values
				int tailX = (frameX + frameWidth) - 30;
				textY = frameY + gp.tileSize;
				String value;
				
				value = String.valueOf(gp.player.level);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.strength);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.dexterity);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.attack);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.defense);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.exp);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.nextLevelExp);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				value = String.valueOf(gp.player.coin);
				textX = getXforAlignToRightText(value, tailX);
				g2.drawString(value, textX, textY);
				textY += lineHeight;
				
				g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 14, null);
				textY += gp.tileSize;
				g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14, null);
				
				
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0,150);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);		
	}
	
	
	
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
		
	}
	
public int getXforAlignToRightText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
		
	}
}
