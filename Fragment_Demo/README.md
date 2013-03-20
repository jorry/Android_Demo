Fragment
=
---
###WHAT:
>*  可以认为fragment是activity的模块化组件，它拥有自己的生命周期，接受它自己的输入事件。   
*  一个Fragment必须总是**嵌入**到一个活动（activity）中，并且它的生命周期直接受到主活动的生命周期的影响。    
*  也可以在运行activity的时候添加或者移除它(有点像“子activity”你可以在不同的activity中重用),  
*  可以把几个Fragment混合到一个Activity中,这样你就可以创建一个多个页面的UI并可以在多个Activity中**复用**一个Fragment。

---
###HOW:
>*  **创建Fragment:**  
	只要extends Fragment的就可作为Fragment，内置有DialogFragment，ListFragment，PreferenceFragment。  
	Fragment重写onCreateView，可定义其视图
>*  **加到activity中的方式:**(每一个Fragment实例必须与父类FragmentActivity相关联)
  1.  使用XML添加Fragment到Activity:  
  	 使用<fragment>标签在布局文件中为activity定义一个fragment组件  
  **注意：**	此时，你不能在运行时移除此fragment。Fragment初始化先于Activity的初始化完成  
  1.  在activity运行时添加:  
	更好的方式是在activity运行时添加，如果想在activity的生命周期中变换fragment的话，这样做是必须的。
>	
			getSupportFragmentManager().beginTransaction()
	                    .add(R.id.fragment_container, firstFragment).commit();

---
####Communicating with Other Fragments:(对外的交互)
为了重用Fragment UI组件，你应该将Fragment建立成**完全独立**，模块化并且定义了自己布局和行为的组件。  
一旦你定义了这些可重用的Fragment， 你可以通过activity，应用程序逻辑使它们关联，交互以组成一个整体复合型UI。  
 通常情况下，你希望一个Fragment可以与另一个交互。 比如在用户事件的基础上去修改内容，  
 **所有Fragment到Fragment的交互都是通过相关联的activity来做的，两个fragment应该从不直接交互。**  

* **定义一个接口 Define an Interface**  
为了**允许Fragment与它的activity交互，你可以在fragment类中定义一个接口并且在activity中实现它**。fragment可以在onAttach()方法中获取接口的实现并调用接口的方法与activity交互。 以下是fragment到activity的交互例子：

		public class HeadlinesFragment extends ListFragment {
		    OnHeadlineSelectedListener mCallback;
		
		    // Container Activity must implement this interface
		    public interface OnHeadlineSelectedListener {
		        public void onArticleSelected(int position);
		    }
		
		    @Override
		    public void onAttach(Activity activity) {
		        super.onAttach(activity);
		        
		        // This makes sure that the container activity has implemented
		        // the callback interface. If not, it throws an exception
		        try {
		            mCallback = (OnHeadlineSelectedListener) activity;
		        } catch (ClassCastException e) {
		            throw new ClassCastException(activity.toString()
		                    + " must implement OnHeadlineSelectedListener");
		        }
		    }
		    
		    ...
		}
现在fragment可以使用OnHeadlineSelectedListener的实例mCallback调用onArticleSelected()方法(或者其他接口内的方法)提供信息给activity了。 例如，当 用户点击list item(list子项)时就会调用下面在fragment的方法。fragment使用回调接口提供事件到父的activity。

		@Override
		    public void onListItemClick(ListView l, View v, int position, long id) {
		        // Send the event to the host activity
		        mCallback.onArticleSelected(position);
		    }
实现接口 Implement the Interface
为了接收来自fragment的事件回调，主activity(你需要用来与fragment交互的activity)必须实现定义在fragment类中的接口。 例如：下面这个activity就实现了上一例子中的接口：

		public static class MainActivity extends Activity
		        implements HeadlinesFragment.OnHeadlineSelectedListener{
		    ...
		    
		    public void onArticleSelected(Uri articleUri) {
		        // The user selected the headline of an article from the HeadlinesFragment
		        // Do something here to display that article
		    }
		}
* **Deliver a Message to a Fragment**  
主activity可以使用findFragmentById()方法获取Fragment实例，然后直接调用fragment的公共方法提供信息给fragment。
例如,想象一下在上面显示的那个activity中可能包含另外一个fragment，并且用来显示由上面那个回调方法返回的数据指定的项目 在这个案例中，这个activity可以从回调函数中获得信息并且传递给其他显示项目的fragment




			public static class MainActivity extends Activity
			        implements HeadlinesFragment.OnHeadlineSelectedListener{
			    ...
			
			    public void onArticleSelected(int position) {
			        // The user selected the headline of an article from the HeadlinesFragment
			        // Do something here to display that article
			
			        ArticleFragment articleFrag = (ArticleFragment)
			                getSupportFragmentManager().findFragmentById(R.id.article_fragment);
			
			        if (articleFrag != null) {
			            // If article frag is available, we're in two-pane layout...
			
			            // Call a method in the ArticleFragment to update its content
			            articleFrag.updateArticleView(position);
			        } else {
			            // Otherwise, we're in the one-pane layout and must swap frags...
			
			            // Create fragment and give it an argument for the selected article
			            ArticleFragment newFragment = new ArticleFragment();
			            Bundle args = new Bundle();
			            args.putInt(ArticleFragment.ARG_POSITION, position);
			            newFragment.setArguments(args);
			        
			            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			
			            // Replace whatever is in the fragment_container view with this fragment,
			            // and add the transaction to the back stack so the user can navigate back
			            transaction.replace(R.id.fragment_container, newFragment);
			            transaction.addToBackStack(null);
			
			            // Commit the transaction
			            transaction.commit();
			        }
			    }
			}
			
			
---
####注意：
>
>*  当Fragment在XML中定义的时候，Fragment初始化先于Activity的初始化完成
>*  Fragment的交互主要通过Fragment中定义Interface,可在Fragment中安排该动作的调用时机，然后在别处实现动作。  
	    	形象说就是在Fragment声明该动作以及安排调用，在别处实现该动作.
