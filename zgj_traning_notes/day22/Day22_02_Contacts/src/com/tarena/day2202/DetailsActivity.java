package com.tarena.day2202;

import java.util.ArrayList;

import com.tarena.biz.ContactBiz;
import com.tarena.entity.Contact;
import com.tarena.entity.EmailInfo;
import com.tarena.entity.PhoneInfo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	
	private ImageView ivPhoto;
	private TextView tvName, tvContent;
	private ContactBiz biz;

	private void setupView() {
		
		ivPhoto = (ImageView) findViewById(R.id.ivPhoto_Details);
		tvName = (TextView) findViewById(R.id.tvName_Details);
		tvContent = (TextView) findViewById(R.id.tvContent);

		// 获取联系人信息
		Contact contact = (Contact) getIntent().getSerializableExtra("contact");//获取 用 putExtra()发送的 键contact 对应的 类
		Bitmap bm = biz.getPhoto(contact.getId());
		if (bm != null)
			ivPhoto.setImageBitmap(bm);
		else
			ivPhoto.setImageResource(R.drawable.ic_launcher);
		
		tvName.setText(contact.getName());

		// 获取邮箱、电话信息
		ArrayList<PhoneInfo> phones = biz.getPhones(contact.getId());
		ArrayList<EmailInfo> emails = biz.getEmails(contact.getId());

		StringBuilder sb = new StringBuilder();
		if (phones != null) {
			sb.append("电话信息:\n");
			for (PhoneInfo phone : phones) {
				switch (phone.getType()) {
				case Phone.TYPE_HOME:
					sb.append("家庭电话:").append(phone.getNumber()).append('\n');
					break;
				case Phone.TYPE_MOBILE:
					sb.append("移动电话：").append(phone.getNumber()).append('\n');
					break;
				default:
					sb.append("其它电话：").append(phone.getNumber()).append('\n');
					break;
				}
			}
		}
		
		if (emails != null) {
			sb.append("邮箱信息:\n");
			for (EmailInfo email : emails) {
				switch (email.getType()) {
				case Email.TYPE_HOME:
					sb.append("家庭邮箱:").append(email.getEmail()).append('\n');
					break;
				case Email.TYPE_WORK:
					sb.append("工作邮箱：").append(email.getEmail()).append('\n');
					break;
				default:
					sb.append("其它邮箱：").append(email.getEmail()).append('\n');
					break;
				}
			}
		}

		tvContent.setText(sb.toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_details);
		biz = new ContactBiz(this);
		setupView();
	}
}
