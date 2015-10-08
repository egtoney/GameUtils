package etUtils.math;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class EtMath {
	
	public static void main(String[] args){
		System.out.println(distanceOffPath(0,10,10,0,0,0));
	}
	
	public static double DistanceEquation(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	
	public static double distanceEquation(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	
	public static float distanceEquation(float x1, float y1, float x2, float y2){
		return (float) Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	
	public static double[] convertPolarToEuclidian(double theta, double a){
		double[] point = new double[2];
		point[0] = Math.sin(theta)*a;
		point[1] = Math.cos(theta)*a;
		return point;
	}

	public static boolean firstAndSecondContainThird(int[] p1, int[] p2, int[] p3) {
		boolean inX = false;
		boolean inY = false;
		
		if(p1[0]<p2[0]){ //then the first point is to the left
			inX = (p1[0]<=p3[0] && p2[0]>=p3[0]) ? true : false ; 
		}else{ //then the first point is to the right
			inX = (p2[0]<=p3[0] && p1[0]>=p3[0]) ? true : false ; 
		}

		if(p1[1]<p2[1]){ //then the first point is on the top
			inY = (p1[1]<=p3[1] && p2[1]>=p3[1]) ? true : false ; 
		}else{ //then the first point is on the bottom
			inY = (p2[1]<=p3[1] && p1[1]>=p3[1]) ? true : false ; 
		}
		
		return (inX && inY) ? true : false ;
	}
	
	public static double distanceOffPath(double fX, double fY, double sX, double sY, double tX, double tY){
		double dY = sY-fY;
		double dX = sX-fX;
		double m1 = (sY-fY)/(sX-fX);
		double m2 = (tY-fY)/(tX-fX);
		
		System.out.println("m1:"+m1+" m2:"+m2);
		
		double angle = Math.atan(Math.max(m1, m2))-Math.atan(Math.min(m1, m2));
		
		System.out.println("angle:"+angle);
		
		if(dX>0){
			if(dY<0){ // X+ Y-
				angle+=2*Math.PI;
			}
		}else{ // X- Y+, X- Y-
			angle+=Math.PI;
		}
		
		System.out.println("angle:"+angle);
		
		double dist1 = DistanceEquation(fX,fY,tX,tY);
		
		return Math.abs(dist1*Math.sin(angle));
	}

	public static float min(int x, int x2, int x3) {
		if(x>x2 && x>x3)
			return x;
		if(x2>x && x2>x3)
			return x2;
		if(x3>x && x3>x2)
			return x3;
		return 0;
	}
	
}
