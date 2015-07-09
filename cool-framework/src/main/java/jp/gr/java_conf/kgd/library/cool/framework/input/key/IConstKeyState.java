package jp.gr.java_conf.kgd.library.cool.framework.input.key;

public interface IConstKeyState
{
	public static final int HOLD_TIME_LIMIT = 100000;
	public static final int STATE_PRESSED = 1;
	public static final int STATE_RELEASED = 0;
	
	//
	IConstKeyStateConfig getBindedKeyStateConfig();
	
	//
	boolean isDown();
	boolean isUp();
	
	//
	int getHoldTime();
	int getPrevHoldTime();
	
	//	
	boolean isPressed();
	boolean isPressed(boolean isKeyRepeatEnabled);
	
	boolean isReleased();
	
	boolean isHold(int time);
	boolean isLeave(int time);
	
	
	//特殊↓
	
	//押した時間に関わらず、一度離してインターバル以内にもう一度押した瞬間。
	//（連ジの空中ダッシュ）
	boolean isPressedAgain();
	boolean isPressedAgain(int interval);
	
	//タップ時間以下の時間押した（押し続けた）後に離した瞬間。
	//引数有りの方は「溜め」解放の判定に使える。
	boolean isTapped();
	boolean isTapped(int tapTime);
	
	//タップ後に、インターバル以内にもう一度押した瞬間。
	//地上ステップ・ダッシュに良さそう。
	boolean isDoubleTapStarted();
	boolean isDoubleTapStarted(int tapInterval);
	
	//タップ後に、インターバル以内にもう一度押して、その後タップ時間内に離した瞬間。
	//離すまでがワンセット。
	boolean isDoubleTapped();
	boolean isDoubleTapped(int tapTime, int tapInterval);
}
