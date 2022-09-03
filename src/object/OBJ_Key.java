package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	
	public OBJ_Key(GamePanel gp) {
		
	super(gp);
		
		name = "Key";
		down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
		
		
		
	}

}

//old code
/**

public class OBJ_Key extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Key";
		try {
		
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

*/