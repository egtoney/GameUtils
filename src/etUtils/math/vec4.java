package etUtils.math;

import java.nio.FloatBuffer;

public class vec4 {

	private FloatBuffer values = FloatBuffer.allocate(4);

	public static vec4 add(vec4 a, vec4 b){
		float[] newVals = new float[4];

		newVals[0] = a.getX() + b.getX();
		newVals[0] = a.getY() + b.getY();
		newVals[0] = a.getZ() + b.getZ();
		newVals[0] = a.getW() + b.getW();
		
		return new vec4(newVals);
	}

	public static vec4 subtract(vec4 a, vec4 b){
		float[] newVals = new float[4];

		newVals[0] = a.getX() - b.getX();
		newVals[0] = a.getY() - b.getY();
		newVals[0] = a.getZ() - b.getZ();
		newVals[0] = a.getW() - b.getW();
		
		return new vec4(newVals);
	}
	
	public vec4(){
		values.put(new float[]{0f,0f,0f,0f});
	}
	
	public vec4(float x, float y, float z){
		values.put(x);
		values.put(y);
		values.put(z);
		values.put(1);
	}

	public vec4(float x, float y, float z, float w){
		values.put(x);
		values.put(y);
		values.put(z);
		values.put(w);
	}
	
	public vec4(float[] vals){
		if(vals.length==4){
			for(float f : vals){
				values.put(f);
			}
		}else if(vals.length==3){
			for(float f : vals){
				values.put(f);
			}
			values.put(1);
		}
	}
	
	public vec4(FloatBuffer buf){
		values = buf;
	}

	public float getX(){return values.get(0);}
	public float getY(){return values.get(1);}
	public float getZ(){return values.get(2);}
	public float getW(){return values.get(3);}
	
	public void setX(float x){
		values.position(0);
		values.put(x);
	}
	
	public void setY(float y){
		values.position(1);
		values.put(y);
	}
	
	public void setZ(float z){
		values.position(2);
		values.put(z);
	}
	
	public void setW(float w){
		values.position(3);
		values.put(w);
	}
	
	@Override
	public String toString(){
		String str = "vert4[";
		for(int i=0 ; i<4 ; i++){
			str+=values.get(i)+",";
		}
		str=str.substring(0,str.length()-1);
		str+="]";
		return str;
	}
	
}
