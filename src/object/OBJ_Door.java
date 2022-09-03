package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	
public OBJ_Door(GamePanel gp) {
		
	super(gp);
	
		name = "Door";
		down1 = setup("/objects/door",gp.tileSize,gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
	}
}

/**
public class OBJ_Door extends SuperObject{

	GamePanel gp;
	
public OBJ_Door(GamePanel gp) {
		
	this.gp = gp;
	
		name = "Door";
		try {
		
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
*/