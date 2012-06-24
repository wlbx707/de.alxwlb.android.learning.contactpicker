/**
 * 
 */
package de.alxwlb.android.learning.contactpicker;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author alewal
 *
 */
public class ContactPickerTest extends Activity {
	
	public static final int PICK_CONTACT = 1;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contentpickertest);
		
		Button button = (Button)findViewById(R.id.pick_contact_button);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View _view) {
				Intent intent = new Intent(Intent.ACTION_PICK,
						Uri.parse("content://contacts/"));
							startActivityForResult(intent, PICK_CONTACT); 
			} 
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode) {
			case (PICK_CONTACT) : {
				if (resultCode == Activity.RESULT_OK) {
					Uri contactData = data.getData();
					Cursor c = managedQuery(contactData, null, null, null, null);
					c.moveToFirst();
					String name;
					name = c.getString(c.getColumnIndexOrThrow(People.NAME));
					TextView tv;
					tv = (TextView)findViewById(R.id.selected_contact_textview);
					tv.setText(name);
				}
				break;
			}
		}
	}

}
