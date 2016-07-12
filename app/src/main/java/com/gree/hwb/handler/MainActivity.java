package com.gree.hwb.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.gree.hwb.handler.com.gree.hwb.handler.bean.Person;

public class MainActivity extends AppCompatActivity
{
	TextView tv_Num1;
	private int image[] = {R.mipmap.rabbit,R.mipmap.yellowp};
	private int index = 0;
	ImageView iv_photo;
	Button btn_stop;
	ChangePhotoThread changePhotoThread = new ChangePhotoThread();
	/*//handler的sendmessage方法
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			tv_Num1.setText(msg.obj.toString());
		}
	};*/
	//handler.postDelayed()使用方法，new Handler.Callback()用于信息的截获，返回true则后面的handlerMessage方法不执行
	Handler handlerChangePhoto = new Handler(new Handler.Callback()
	{
		@Override
		public boolean handleMessage(Message message)
		{
			Toast.makeText(MainActivity.this,"截获",Toast.LENGTH_SHORT).show();
			return false;
		}
	})
	{
		@Override
		public void handleMessage(Message message)
		{
			Toast.makeText(MainActivity.this,"未截获",Toast.LENGTH_SHORT).show();
		}
	};

	class ChangePhotoThread implements Runnable
	{
		@Override
		public void run()
		{
			index++;
			if(index == 2)
			{
				index = 0;
			}
			handlerChangePhoto.postDelayed(new Runnable()
			{
				@Override
				public void run()
				{
					iv_photo.setImageResource(image[index]);
				}
			}, 1000);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_Num1 = (TextView) findViewById(R.id.tv_Num1);
		iv_photo = (ImageView) findViewById(R.id.iv_photo);
		btn_stop = (Button) findViewById(R.id.btn_stop);
		handlerChangePhoto.postDelayed(changePhotoThread,1000);
		btn_stop.setOnClickListener(new MyClickListener());

		/*//sendMessage方法使用
		new Thread()
		{
			Person person = new Person("张三",20);
			@Override
			public void run()
			{
				Message message = new Message();
				message.arg1 = 80;
				message.arg2 = 100;
				message.obj = person;
				handler.sendMessageDelayed(message,10000);
			}
		}.start();*/

		/*//非法线程更新UI异常,UI只能在UI线程中更新
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					sleep(1000);
					tv_Num1.setText("你好");
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}.start();*/
	}

	class MyClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View view)
		{
			switch (view.getId())
			{
				case R.id.btn_stop:
					handlerChangePhoto.removeCallbacks(changePhotoThread);
					handlerChangePhoto.sendEmptyMessage(1);
					break;

				default:
					break;
			}
		}
	}
}




























