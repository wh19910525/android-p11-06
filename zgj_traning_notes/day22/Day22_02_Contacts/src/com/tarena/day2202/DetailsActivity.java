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

		// ��ȡ��ϵ����Ϣ
		Contact contact = (Contact) getIntent().getSerializableExtra("contact");//��ȡ �� putExtra()���͵� ��contact ��Ӧ�� ��
		Bitmap bm = biz.getPhoto(contact.getId());
		if (bm != null)
			ivPhoto.setImageBitmap(bm);
		else
			ivPhoto.setImageResource(R.drawable.ic_launcher);
		
		tvName.setText(contact.getName());

		// ��ȡ���䡢�绰��Ϣ
		ArrayList<PhoneInfo> phones = biz.getPhones(contact.getId());
		ArrayList<EmailInfo> emails = biz.getEmails(contact.getId());

		StringBuilder sb = new StringBuilder();
		if (phones != null) {
			sb.append("�绰��Ϣ:\n");
			for (PhoneInfo phone : phones) {
				switch (phone.getType()) {
				case Phone.TYPE_HOME:
					sb.append("��ͥ�绰:").append(phone.getNumber()).append('\n');
					break;
				case Phone.TYPE_MOBILE:
					sb.append("�ƶ��绰��").append(phone.getNumber()).append('\n');
					break;
				default:
					sb.append("�����绰��").append(phone.getNumber()).append('\n');
					break;
				}
			}
		}
		
		if (emails != null) {
			sb.append("������Ϣ:\n");
			for (EmailInfo email : emails) {
				switch (email.getType()) {
				case Email.TYPE_HOME:
					sb.append("��ͥ����:").append(email.getEmail()).append('\n');
					break;
				case Email.TYPE_WORK:
					sb.append("�������䣺").append(email.getEmail()).append('\n');
					break;
				default:
					sb.append("�������䣺").append(email.getEmail()).append('\n');
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
