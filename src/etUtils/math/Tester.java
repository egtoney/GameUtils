package etUtils.math;

public class Tester {

	public static float[] a = {
		10,23,43,55,
		32,45,76,15,
		43,67,86,65,
		12,76,89,72
	};

	public static float[] e = {
		90,91,2,3,
		4,5,6,7,
		8,9,10,11,
		12,13,14,15
	};

	public static float[] g = {
		0,0,0,0,	
		0,0,0,0,	
		0,0,0,0,	
		0,0,0,0	
	};

	public static float[] h = {
		1,1,1,1,
		1,1,1,1,
		1,1,1,1,
		1,1,1,1
	};
	
	public static float[] c = {
		1,1,1,0.5f
	};
	
	public static void main(String[] args){
		mat4 b = new mat4(a);
		mat4 f = new mat4(e);
		
		mat4 t = new mat4(g);
		mat4 y = new mat4(h);

		vec4 d = new vec4(c);
		
		System.out.println("multiplcation "+mat4.multiplyByMatrix(b, f));
		System.out.println("addition "+mat4.add(t, y));
		System.out.println("constant "+mat4.multiplyByScalar(0.4f, y));
		System.out.println("vector transform "+mat4.multiplyByVertex(y, d));
	}
	
}
