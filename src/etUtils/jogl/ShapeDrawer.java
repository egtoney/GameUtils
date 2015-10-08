package etUtils.jogl;

import javax.media.opengl.GL2;

public class ShapeDrawer {
	private static final float DEG2RAD = 3.14159f/180f;
	
	public static void drawLine(GL2 gl, float x1, float y1, float x2, float y2){
		gl.glBegin(GL2.GL_LINE);
		
		gl.glVertex2f(x1, y1);
		gl.glVertex2f(x2, y2);
		
		gl.glEnd();
	}
	
	public static void fillCircle(GL2 gl,float x, float y, float radius){
		   gl.glBegin(GL2.GL_POLYGON);
		 
		   for (float i=0; i<360f; i+=10)
		   {
		      float degInRad = i*DEG2RAD;
		      gl.glVertex2f((float)(x+Math.cos(degInRad)*radius), (float)(y+Math.sin(degInRad)*radius));
		   }
		 
		   gl.glEnd();
	}
	
	public static void drawCircle(GL2 gl,float x, float y, float radius){
		   gl.glBegin(GL2.GL_LINE_LOOP);
		 
		   for (float i=0; i<360f; i+=5)
		   {
		      float degInRad = i*DEG2RAD;
		      gl.glVertex2f((float)(x+Math.cos(degInRad)*radius), (float)(y+Math.sin(degInRad)*radius));
		   }
		 
		   gl.glEnd();
	}
	
	public static void fillRectangle(GL2 gl,float x, float y, float width, float height){
		   gl.glBegin(GL2.GL_POLYGON);

		   gl.glVertex2f(x, y);
		   gl.glVertex2f(x, y+height);
		   gl.glVertex2f(x+width, y+height);
		   gl.glVertex2f(x+width, y);
		   
		   gl.glEnd();
	}
	
	public static void drawRectangle(GL2 gl,float x, float y, float width, float height){
		   gl.glBegin(GL2.GL_LINE_LOOP);

		   gl.glVertex2f(x, y);
		   gl.glVertex2f(x, y+height);
		   gl.glVertex2f(x+width, y+height);
		   gl.glVertex2f(x+width, y);
		 
		   gl.glEnd();
	}
	
}
