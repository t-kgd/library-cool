package jp.gr.java_conf.kgd.library.cool.util.math;

public class MathHelper
{
	protected MathHelper()
	{
	}
	
	//
	public static int PI_DEGREE = 180;
	public static int PI2_DEGREE = 360;
	
	public static final float EPS_FLOAT = 1e-5f;
	public static final double EPS_DOUBLE = 1e-7;
	
	public static final int PERCENTAGE_DENOMINATOR = 100;
	
	//
	public static int minMax(int min, int value, int max)
	{
		return Math.max(min, Math.min(max, value));
	}
	
	public static float minMax(float min, float value, float max)
	{
		return Math.max(min, Math.min(max, value));
	}
	
	public static double minMax(double min, double value, double max)
	{
		return Math.max(min, Math.min(max, value));
	}
	
	//
	public static int getSign(int value)
	{
		if(value == 0) return 0;
		
		return value < 0 ? -1 : 1;
	}
	
	public static int getSign(float value)
	{
		if(value == 0) return 0;
		
		return value < 0 ? -1 : 1;
	}
	
	public static int getSign(double value)
	{
		if(value == 0) return 0;
		
		return value < 0 ? -1 : 1;
	}
	
	public static int getSign(boolean isPlus)
	{
		return isPlus ? 1 : -1;
	}
	
	
	
	
	
	//
	public static boolean equals(float a, float b)
	{
		return equals(a, b, EPS_FLOAT);
	}
	
	public static boolean equals(float a, float b, float eps)
	{
		return a - eps < b && b < a + eps;
	}
	
	public static boolean isZero(float a)
	{
		return equals(a, 0);
	}
	
	public static boolean isZero(float a, float eps)
	{
		return equals(a, 0, eps);
	}
	
	
	public static boolean equals(double a, double b)
	{
		return equals(a, b, EPS_DOUBLE);
	}
	
	public static boolean equals(double a, double b, double eps)
	{
		return a - eps < b && b < a + eps;
	}
	
	public static boolean isZero(double a)
	{
		return equals(a, 0);
	}
	
	public static boolean isZero(double a, double eps)
	{
		return equals(a, 0, eps);
	}
	
	
	
	//
	public static float round(float a, int pos)
	{
		int posBuf = Math.max(1, pos);
		
		int result = ((int)(a * posBuf * 10 + 5)) / 10;
		
		return (float)result / posBuf;
	}
	
	public static double round(double a, int pos)
	{
		int posBuf = Math.max(1, pos);
		
		int result = ((int)(a * posBuf * 10 + 5)) / 10;
		
		return (double)result / posBuf;
	}
	
	public static int modToPlus(int a, int b)
	{
		int buf = a % b;
		
		if(buf < 0)
		{
			buf += b;
		}
		
		return buf;
	}
	
	public static float modToPlus(float a, float b)
	{
		float buf = a % b;
		
		if(buf < 0)
		{
			buf += b;
		}
		
		return buf;
	}
	
	public static double modToPlus(double a, double b)
	{
		double buf = a % b;
		
		if(buf < 0)
		{
			buf += b;
		}
		
		return buf;
	}
	
	public static int getDigits(int num)
	{
		return String.valueOf(num).length();
	}
	
	public static double toPercentage(double rate, int pos)
	{
		double result = rate * PERCENTAGE_DENOMINATOR;
		
		return round(result, pos);
	}
	
	public static double toPercentage(float rate, int pos)
	{
		float result = rate * PERCENTAGE_DENOMINATOR;
		
		return round(result, pos);
	}
}
