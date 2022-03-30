package com.tatvasoftassignment.assignment_11.AsycTask;

import static com.tatvasoftassignment.assignment_11.Fragment.ContactsFragment.binding;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoftassignment.assignment_11.Adapter.ContactAdapter;
import com.tatvasoftassignment.assignment_11.Model.Contacts;
import com.tatvasoftassignment.assignment_11.R;

import java.util.ArrayList;

public class ContactAsyncTask extends ContactsBackGroundTask {


    ProgressDialog progressDialog;
    Context context;
    ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    ContactAdapter contactAdapter;

    public ContactAsyncTask(Context context){

        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.title));
        progressDialog.setMessage(context.getString(R.string.message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @SuppressLint("Range")
    @Override
    protected ArrayList doInBackground(Void... voids) {

        Uri uri = ContactsContract.Contacts.CONTENT_URI;


        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";


        Cursor cursor = context.getContentResolver().query(
                uri, null, null, null, sort
        );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = context.getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null
                );
                if (phoneCursor.moveToNext()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contacts contacts = new Contacts();
                    contacts.setContactName(name);
                    contacts.setContactNumber(number);

                    contactsArrayList.add(contacts);
                    phoneCursor.close();
                }
            }
            cursor.close();
        }
        return contactsArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        progressDialog.dismiss();
        binding.contactsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        contactAdapter = new ContactAdapter(arrayList, context);
        binding.contactsRecyclerView.setAdapter(contactAdapter);

    }
}
