package etUtils.math;

import java.nio.FloatBuffer;

public class mat4 {

	private FloatBuffer values = FloatBuffer.allocate(16);

	/**
	 * Constructs a 4x4 float matrix
	 * @param vals - the floats in row-major order
	 * @return This matrix
	 */
	public mat4(FloatBuffer buf){
		if(buf.limit()==16){
			values = buf;
		}
	}
	
	/**
	 * Constructs a 4x4 float matrix
	 * @param vals - the floats in row-major order
	 * @return This matrix
	 */
	public mat4(float[] vals){
		if(vals.length==16){
			for(float f : vals){
				values.put(f);
			}
		}
	}
	
	@Override
	public String toString(){
		String str = "mat4[";
		for(int i=0 ; i<16 ; i++){
			str+=values.get(i)+",";
		}
		str=str.substring(0,str.length()-1);
		str+="]";
		return str;
	}
	
	/**
	 * Get the direct FloatBuffer of the matrix in row-major order
	 * @return A FloatBuffer in row-major order
	 */
	private FloatBuffer getValues(){
		return values;
	}
	
	/**
	 * This multiplies a matrix by a scalar and directly modifies the values of mat4 "b"
	 * @param a - the scalar being applied to the matrix
	 * @param b - the matrix that the scalar is going applied
	 */
	public static mat4 multiplyByScalar(float a, mat4 b){
		FloatBuffer c = b.getValues();
		float[] newVals={
			a*c.get(0),a*c.get(1),a*c.get(2),a*c.get(3),
			a*c.get(4),a*c.get(5),a*c.get(6),a*c.get(7),
			a*c.get(8),a*c.get(9),a*c.get(10),a*c.get(11),
			a*c.get(12),a*c.get(13),a*c.get(14),a*c.get(15)
		};
		return new mat4(newVals);
	}
	
	/**
	 * This method multiplies two mat4s together</br></br>
	 * The order of algebra is as follows since the order matrices are multiplied together in <b>does</b> matter</br></br>
	 * <b>[a]*[b]=result</b>
	 * @param a - the first matrix to be multiplied
	 * @param b - the second matrix to be multiplied
	 * @return A new matrix that resulted from the operation
	 */
	public static mat4 multiplyByMatrix(mat4 a, mat4 b){
		FloatBuffer c = a.getValues();
		FloatBuffer d = b.getValues();
		float[] newVals={
			d.get(0) * c.get(0) + d.get(4) * c.get(1) + d.get(8) * c.get(2) + d.get(12) * c.get(3) , d.get(1) * c.get(0) + d.get(5) * c.get(1) + d.get(9) * c.get(2) + d.get(13) * c.get(3) , d.get(2) * c.get(0) + d.get(6) * c.get(1) + d.get(10) * c.get(2) + d.get(14) * c.get(3) , d.get(3) * c.get(0) + d.get(7) * c.get(1) + d.get(11) * c.get(2) + d.get(15) * c.get(3) ,
			d.get(0) * c.get(4) + d.get(4) * c.get(5) + d.get(8) * c.get(6) + d.get(12) * c.get(7) , d.get(1) * c.get(4) + d.get(5) * c.get(5) + d.get(9) * c.get(6) + d.get(13) * c.get(7) , d.get(2) * c.get(4) + d.get(6) * c.get(5) + d.get(10) * c.get(6) + d.get(14) * c.get(7) , d.get(3) * c.get(4) + d.get(7) * c.get(5) + d.get(11) * c.get(6) + d.get(15) * c.get(7) ,
			d.get(0) * c.get(8) + d.get(4) * c.get(9) + d.get(8) * c.get(10) + d.get(12) * c.get(11) , d.get(1) * c.get(8) + d.get(5) * c.get(9) + d.get(9) * c.get(10) + d.get(13) * c.get(11) , d.get(2) * c.get(8) + d.get(6) * c.get(9) + d.get(10) * c.get(10) + d.get(14) * c.get(11) , d.get(3) * c.get(8) + d.get(7) * c.get(9) + d.get(11) * c.get(10) + d.get(15) * c.get(11) ,
			d.get(0) * c.get(12) + d.get(4) * c.get(13) + d.get(8) * c.get(14) + d.get(12) * c.get(15) , d.get(1) * c.get(12) + d.get(5) * c.get(13) + d.get(9) * c.get(14) + d.get(13) * c.get(15) , d.get(2) * c.get(12) + d.get(6) * c.get(13) + d.get(10) * c.get(14) + d.get(14) * c.get(15) , d.get(3) * c.get(12) + d.get(7) * c.get(13) + d.get(11) * c.get(14) + d.get(15) * c.get(15) 
		};
		return new mat4(newVals);
	}
	
	/**
	 * This method multiplies a vec4 by a mat4</br></br>
	 * The order of algebra is as follows assuming that the vec4 is a 1x4 matrix</br></br>
	 * <b>a*[b]=result</b>
	 * @param a - the vector to be multiplied by the matrix
	 * @param b - the matrix that the vector is being multiplied by
	 * @return A row-major vec4
	 */
	public static vec4 multiplyByVertex(mat4 a, vec4 b){
		FloatBuffer c = a.getValues();
		float[] newVals={
			c.get(0)*b.getX()+c.get(1)*b.getY()+c.get(2)*b.getZ()+c.get(3)*b.getW(),
			c.get(4)*b.getX()+c.get(5)*b.getY()+c.get(6)*b.getZ()+c.get(7)*b.getW(),
			c.get(8)*b.getX()+c.get(9)*b.getY()+c.get(10)*b.getZ()+c.get(11)*b.getW(),
			c.get(12)*b.getX()+c.get(13)*b.getY()+c.get(14)*b.getZ()+c.get(15)*b.getW()
		};
		return new vec4(newVals);
	}

	/**
	 * 
	 * @param a - the first mat4 to be added
	 * @param b - the second mat4 to be added
	 * @return A row-major mat4
	 */
	public static mat4 add(mat4 a,mat4 b) {
		FloatBuffer c = a.getValues();
		FloatBuffer d = b.getValues();
		float[] newVals={
			c.get(0)+d.get(0),c.get(1)+d.get(1),c.get(2)+d.get(2),c.get(3)+d.get(3),
			c.get(4)+d.get(4),c.get(5)+d.get(5),c.get(6)+d.get(6),c.get(7)+d.get(7),
			c.get(8)+d.get(8),c.get(9)+d.get(9),c.get(10)+d.get(10),c.get(11)+d.get(11),
			c.get(12)+d.get(12),c.get(13)+d.get(13),c.get(14)+d.get(14),c.get(15)+d.get(15)
		};
		return new mat4(newVals);
	}

	public void reduce4DRoundingErrors() {
		if(values.get(15)!=1.0f){
			values.position(0);
			for(int i=0 ; i<16 ; i++){
				values.put(values.get(i)/values.get(15));
			}
		}
	}
	
}
