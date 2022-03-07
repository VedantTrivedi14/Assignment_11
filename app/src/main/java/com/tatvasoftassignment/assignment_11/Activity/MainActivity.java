package com.tatvasoftassignment.assignment_11.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tatvasoftassignment.assignment_11.Fragment.AudioFragment;
import com.tatvasoftassignment.assignment_11.Fragment.ContactsFragment;
import com.tatvasoftassignment.assignment_11.R;
import com.tatvasoftassignment.assignment_11.Adapter.ViewPagerAdapter;
import com.tatvasoftassignment.assignment_11.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    ContactsFragment contactsFragment;
    AudioFragment audioFilesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tabLayout.setupWithViewPager(binding.viewPager);
        ViewPagerAdapter viewPager  = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.addFragment(new AudioFragment(this),getString(R.string.audio));
        viewPager.addFragment(new ContactsFragment(this),getString(R.string.contacts));
        binding.viewPager.setAdapter(viewPager);

        if (checkAndRequestPermissions()) {
            contactsFragment = new ContactsFragment(this);
            audioFilesFragment = new AudioFragment(this);

        }

    }
    private boolean checkAndRequestPermissions() {

        int permissionAudio = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionContact = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (permissionContact != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {

            Map<String, Integer> permissionHashMap = new HashMap<>();

            permissionHashMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            permissionHashMap.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);

            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++)
                    permissionHashMap.put(permissions[i], grantResults[i]);

                if (permissionHashMap.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && permissionHashMap.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

                    audioFilesFragment = new AudioFragment(this);
                    contactsFragment = new ContactsFragment(this);

                } else {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS)) {
                        showDialogOK(getString(R.string.alert_title),
                                getString(R.string.alert_message),
                                (dialog, which) -> {
                                    if (which == DialogInterface.BUTTON_POSITIVE) {
                                        checkAndRequestPermissions();
                                    }
                                });
                    }
                    else {
                        Toast.makeText(MainActivity.this, getString(R.string.denied_permissions), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    private void showDialogOK(String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, okListener)
                .create()
                .show();
    }
}