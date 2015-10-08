package etUtils.jogl;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static void setBTrans(BufferedImage name,int temp){
		for (int _yi = 0; _yi<name.getHeight();_yi++){
			for (int _xi = 0; _xi<name.getWidth();_xi++){
				if (name.getRGB(_xi,_yi)==temp)
					name.setRGB(_xi,_yi,0);
			}
		}
	}
	   
	private static int fixRGB(int num){
		int Alpha = 0xFF000000;
		int Red   = 0x00FF0000;
		int Green = 0x0000FF00;
		int Blue  = 0x000000FF;
		int finalR = 0;
		
		Red   =   num & Red;
		Red   =   Red << 8;
		
		Green   =   num & Green;
		Green   =   Green << 8;
		
		Blue   =   num & Blue;
		Blue   =   Blue << 8;
		
		Alpha  =   num & Alpha;
		Alpha  =   Alpha >>> 24;
		
		finalR = Red | Green | Blue | Alpha;
		
		return finalR;
	}
	   
	public static ByteBuffer loadImage(File f, GraphicsDevice GD, boolean trans){
		try{
			Image imgTemp;
			imgTemp = (Image)ImageIO.read(f);
			ByteBuffer buf;
			
			BufferedImage Bimg = GD.getDefaultConfiguration().createCompatibleImage(imgTemp.getWidth(null),imgTemp.getHeight(null),Color.BITMASK);
			Bimg.getGraphics().drawImage(imgTemp,0,0,null);
			
			if (trans)
				setBTrans(Bimg,Bimg.getRGB(0,Bimg.getHeight()-1));
			
			buf = ByteBuffer.allocateDirect((Bimg.getWidth()*Bimg.getHeight())*4);
			
			for (int y = 0; y< Bimg.getHeight();y++){
			   for (int x = 0; x< Bimg.getWidth();x++)
				   buf.putInt(fixRGB(Bimg.getRGB(x, Bimg.getHeight()-y-1)));
			}
			
			return buf;
			
		}catch(IOException ie){
			System.out.println("Error Reading Image");
		}
		return null;
	}
	
}
