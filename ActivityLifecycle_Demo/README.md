Activity生命周期的简化图 
-
![alt sdfasdf](./basic-lifecycle.png)


正确使用你的**Activity生命周期函数**去保障应用程序的良好表现，必须注意很多方面，包括下面的内容：

1. 当用户接听电话或者转去另外一个应用程序时，不要让程序崩溃。
1. 当用户没有激活使用它时，不消耗宝贵的系统资源。
1. 当用户离开你的应用程序并在稍后的时间返回，不会丢失用户的进度。
1. 当用户屏幕在横向与纵向旋转切换时，不会崩溃或者丢失用户进度。



**Resumed状态**
- 
在这种状态下，该Activity在前台运行，用户可以与它进行交互。（有时也简称为“running”状态。）

**Paused状态**
-
在这种状态下，该Activity被部分遮蔽（被其他在前台的半透明或不覆盖整个屏幕的活动遮住）。此状态不接受用户输入，并且不能执行任何代码。
**Stopped状态**
-
在这种状态下，该活动是完全隐藏，不可见的，它被认为是在后台。虽然停止，活动实例和所有成员变量如状态信息将被保留，但不能执行任何代码。
**其他的状态（created状态和started状态）**
-
都是非常短暂而且系统通过调用函数使得非常快地转到下一状态。
因此，当系统调用了onCreated()之后，非常快地就调用了onStart()方法使得进入下一状态，而又马上调用了onResumed又进入了下一状态。



Activity状态的保持与恢复
=
![alt 图2](./Basic-lifecycle-savestate.png)


###保存Activity状态：
当Activity开始 **Stop** 时，系统会调用 **onSaveInstanceState()** ，因此Activity可以用键值对的集合来保存状态信息。  
这个方法会默认保存Activity视图的状态信息，例如在EditText组件中的文本或者是ListView的滑动位置。   
为了给Activity保存额外的状态信息，必须实现onSaveInstanceState()并增加键值对到Bundle对象中，例如：

		static final String STATE_SCORE = "playerScore";
		static final String STATE_LEVEL = "playerLevel";
		...
		 
		@Override
		public void onSaveInstanceState(Bundle savedInstanceState) {
		    // Save the user's current game state
		    savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
		    savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);
		 
		    // Always call the superclass so it can save the view hierarchy state
		    super.onSaveInstanceState(savedInstanceState);
		}
>**警告**:必须要调用onSaveInstanceState()方法的父类实现，这样默认的父类实现才能保存视图状态的信息。

---

###恢复Activity状态：
当Activity在被Destory后进行重建，可以从系统传递给Activity的Bundle中恢复保存的状态。  
onCreate()与onRestoreInstanceState()回调方法都接收到了同样的Bundle，里面包含了同样的实例状态信息。
因为onCreate()方法会在第一次创建新的Activity实例与重新创建之前被Destory的实例时都被调用，  
所以必须在尝试读取Bundle对象前检查它是否为空。如果它为空，系统则是创建一个新的Activity实例，  
而不是恢复之前被Destory的Activity。 下面是一个示例：演示在onCreate方法里面恢复一些数据：

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState); // Always call the superclass first
	 
	    // Check whether we're recreating a previously destroyed instance
	    if (savedInstanceState != null) {
	        // Restore value of members from saved state
	        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
	        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
	    } else {
	        // Probably initialize members with default values for a new instance
	    }
	    ...
	}
与此同时，也可以选择实现onRestoreInstanceState方法而不是在onCreate方法里面恢复数据。  
**onRestoreInstanceState()方法会在onStart()方法之后执行。**  
系统仅仅会在存在需要恢复的状态信息时才会调用onRestoreInstanceState()，因此不需要检查Bundle是否为空:

		public void onRestoreInstanceState(Bundle savedInstanceState) {
		    // Always call the superclass so it can restore the view hierarchy
		    super.onRestoreInstanceState(savedInstanceState);
		 
		    // Restore state members from saved instance
		    mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
		    mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
		}
>**警告**:与前面的一样，总是需要调用onRestoreInstanceState()方法的父类实现，这样默认的父类实现才能保存视图状态的信息。		 

