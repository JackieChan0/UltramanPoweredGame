package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

	public OBJ_Chest(GamePanel gp) {
		
		super(gp);
		
		name = "Chest";
		down1 = setup("/objects/chest",gp.tileSize,gp.tileSize);
	}
}

/**
public class OBJ_Chest extends SuperObject {

	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Chest";
		try {
		
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
*/