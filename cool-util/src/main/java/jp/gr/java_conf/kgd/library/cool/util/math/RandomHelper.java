package jp.gr.java_conf.kgd.library.cool.util.math;

import java.util.List;
import java.util.Random;

public class RandomHelper
{
	protected RandomHelper()
	{
	}
	
	private static final Random COMMON_RAND = new Random();
	
	//
	public static boolean isWin(double rate, Random rand)
	{
		double percentage = MathHelper.toPercentage(rate, 1);
		
		int randValue = rand.nextInt(MathHelper.PERCENTAGE_DENOMINATOR);
		
		return randValue < percentage;
		
//		return rand.nextDouble() < rate;
	}
	
	public static boolean isWin(double rate)
	{
		return isWin(rate, COMMON_RAND);
	}
	
	public static boolean isWin(float rate, Random rand)
	{
		double percentage = MathHelper.toPercentage(rate, 1);
		
		int randValue = rand.nextInt(MathHelper.PERCENTAGE_DENOMINATOR);
		
		return randValue < percentage;
		
//		return rand.nextDouble() < rate;
	}
	
	public static boolean isWin(float rate)
	{
		return isWin(rate, COMMON_RAND);
	}
	
	public static boolean isWin(int numerator, int denominator, Random rand)
	{
		return rand.nextInt(denominator) < numerator;
	}
	
	public static boolean isWin(int numerator)
	{
		return isWin(numerator, MathHelper.PERCENTAGE_DENOMINATOR, COMMON_RAND);
	}
	
	//
	/**
	 * 
	 * @param min
	 * @param max これを含む値を返す。
	 * @param rand
	 * @return
	 */
	public static int nextInt(int min, int max, Random rand)
	{
		int value = rand.nextInt((max + 1) - min);
		
		return value + min;
	}
	
	public static int nextInt(int min, int max)
	{
		return nextInt(min, max, COMMON_RAND);
	}
	
	
	//
	public static double getWaveValue(double value, double waveRate, Random rand)
	{
		if(MathHelper.isZero(value) || MathHelper.isZero(waveRate))
		{
			return value;
		}
		
		double diff = value * (rand.nextDouble() * 2 - 1) * waveRate;
		
		return value + diff;
	}
	
	public static double getWaveValue(double value, double waveRate)
	{
		return getWaveValue(value, waveRate, COMMON_RAND);
	}
	
	
	/**
	 * 対象リストからランダムに抽出するだけのヘルパ関数です。
	 * 抽出した値はリストから削除されます。
	 * @param list
	 * @param rand
	 * @return
	 */
	public static <E> E extractRandomElement(List<? extends E> list, Random rand)
	{
		return list.remove(rand.nextInt(list.size()));
	}
	
	public static <E> E extractRandomElement(List<? extends E> list)
	{
		return extractRandomElement(list, COMMON_RAND);
	}
}
