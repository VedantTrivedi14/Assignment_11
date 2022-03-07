package com.tatvasoftassignment.assignment_11.AsycTask;

import static com.tatvasoftassignment.assignment_11.Fragment.ContactsFragment.binding;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.tatvasoftassignment.assignment_11.Adapter.ContactAdapter;
import com.tatvasoftassignment.assignment_11.Model.Contacts;
import com.tatvasoftassignment.assignment_11.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ContactAsyncTask extends AsyncTask<Void, Void, ArrayList> {

    private final WeakReference<Context> contextRef;
    ProgressDialog progressDialog;

    ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    ContactAdapter contactAdapter;

    public ContactAsyncTask(Context context) {
        contextRef = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(contextRef.get());
        progressDialog.setTitle(contextRef.get().getString(R.string.title));
        progressDialog.setMessage(contextRef.get().getString(R.string.message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @SuppressLint("Range")
    @Override
    protected ArrayList doInBackground(Void... voids) {
        //Initialize Uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //Sort by name in ascending order
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";

        //Initialize cursor
        Cursor cursor = contextRef.get().getContentResolver().query(
                uri, null, null, null, sort
        );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = contextRef.get().getContentResolver().query(
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
        binding.contactsRecyclerView.setLayoutManager(new LinearLayoutManager(contextRef.get()));
        contactAdapter = new ContactAdapter(arrayList, contextRef.get());
        binding.contactsRecyclerView.setAdapter(contactAdapter);

    }
}
