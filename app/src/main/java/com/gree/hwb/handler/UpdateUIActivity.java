package com.gree.hwb.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/12.
 * 更新UI的4种方法
 */
public class UpdateUIActivity extends AppCompatActivity
{

	private TextView tvUpdateUI;
	Handler updateUIhandler1 = new Handler()
	{
		//使用方法1时候Hander不需要有主体，方法2中使用此方法进行更新UI
		/*@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if (msg.arg1 == 1)
			{
				tvUpdateUI.setText("更新UI成功1，使用sendMessage方法在子线程中更新UI");
			}
			else if (msg.arg1 == 2)
			{
				tvUpdateUI.setText("更新UI成功2，使用sendMessage方法在子线程中更新UI");
			}
			else
			{
				Toast.makeText(UpdateUIActivity.this,"传输信息有误",Toast.LENGTH_SHORT).show();
			}
		}*/
	};
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updateui);
		tvUpdateUI = (TextView) findViewById(R.id.tv_update);



		/*//1.使用post(Runnable方法)在子线程中更新UI
		UpdateUIRunnable updateUIRunnable = new UpdateUIRunnable();
		Thread updateUIThread = new Thread(updateUIRunnable);
		updateUIThread.start();*/

		/*new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					updateUIhandler1.postDelayed(new Runnable()
					{
						@Override
						public void run()
						{
							tvUpdateUI.setText("更新UI成功");
						}
					},5000);
				}
			}
		}.start();*/

		//2.使用sendMessage方法在子线程中更新UI
		/*new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					Message upUIms = new Message();
					upUIms.arg1 = 2;
					updateUIhandler1.sendMessage(upUIms);
				}
			}
		}.start();*/

		//3.使用runOnUIThread()方法更新UI
		/*new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							tvUpdateUI.setText("更新UI成功,使用runOnUIThread()方法更新UI");
						}
					});
				}
			}
		}.start();*/

		//4.使用View本身的post()方法在子线程中去更新UI
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					tvUpdateUI.post(new Runnable()
					{
						@Override
						public void run()
						{
							tvUpdateUI.setText("更新UI成功，使用View本身的post()方法在子线程中去更新UI");
						}
					});
				}
			}
		}.start();


	}


	/*//1.使用post(Runnable方法)在子线程中更新UI,此子线程实现Runnable接口
	class UpdateUIRunnable implements Runnable
	{

		@Override
		public void run()
		{
			try
			{
				Thread.sleep(5000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				updateUIhandler1.post(new Runnable()
				{
					@Override
					public void run()
					{
						tvUpdateUI.setText("更新UI成功，post(Runnable方法)在子线程中更新UI");
					}
				});
			}

		}
	}*/
	//使用post(Runnable方法)在子线程中更新UI,此子线程继承Theead类
}






















