package etUtils.jogl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GL4;

import com.jogamp.common.nio.Buffers;

public class Texture {
	
	int textureID[] = new int[1];

	public Texture(GL2 gl, String filename)
	{
		BufferedImage img = readPNGImage(filename);
		ByteBuffer buffer = makeRGBTexture(img);
		buffer.rewind();
	
		System.out.println(img.getWidth()+" "+img.getHeight());
		
		gl.glGenTextures(1, textureID, 0);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textureID[0]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, 3, img.getWidth(), img.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, buffer);
	
		gl.glTexParameteri(GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_MIN_FILTER,GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_MAG_FILTER,GL2.GL_NEAREST);
	}
	
	private BufferedImage readPNGImage(String filename)
	{
		try
		{
			BufferedImage img = ImageIO.read(new File(filename));
			/*
			Mirroring the Image
			java.awt.geom.AffineTransform tx = java.awt.geom.AffineTransform.getScaleInstance(1, -1);
			tx.translate(0, -img.getHeight(null));
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img = op.filter(img, null);*/
			return img;
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private ByteBuffer makeRGBTexture(BufferedImage img)
	{
		ByteBuffer dest = null;
		switch (img.getType())
		{
			case BufferedImage.TYPE_3BYTE_BGR:
			case BufferedImage.TYPE_CUSTOM:
			{
				byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		
				dest = ByteBuffer.allocateDirect(data.length); // If multiplied by 2 shows a dark cube
				
				dest.order(ByteOrder.nativeOrder());
				dest.put(data, 0, data.length);
				break;
			}
			case BufferedImage.TYPE_INT_RGB:
			{
				int[] data = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
				dest = ByteBuffer.allocateDirect(data.length * Buffers.SIZEOF_INT);
				dest.order(ByteOrder.nativeOrder());
				dest.asIntBuffer().put(data, 0, data.length);
				break;
			}
			default:
				throw new RuntimeException("Unsupported image type " + img.getType());
		}
		return dest;
	}

	public int getTextureId() {
		return textureID[0];
	}
	
}

