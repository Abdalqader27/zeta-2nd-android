
package com.Elkood.ling_en4.Views.SecondYear.English_4.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.About;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.Settings;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.old_version;
import com.Elkood.ling_en4.Views.SecondYear.English_4.Header_Elements.statistics;
import com.Elkood.ling_en4.R;
import com.google.android.material.navigation.NavigationView;
import com.instabug.library.Instabug;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class MenuListFragment extends Fragment {
    private static SharedPreferences preferences;
    private static TextView navUsername;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        preferences = view.getContext().getSharedPreferences("saveData", MODE_PRIVATE);
        NavigationView vNavigation = view.findViewById(R.id.vNavigation);
        View headerView = vNavigation.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.theDonkey);
        setName();
        vNavigation.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.menu_about) {

                Intent intent = new Intent(requireActivity().getApplicationContext(), About.class);
                requireActivity().startActivity(intent);
                requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else if (menuItem.getItemId() == R.id.menu_share) {
                sendApplication(requireActivity());

            } else if (menuItem.getItemId() == R.id.menu_help) {
                Instabug.enable();
                Instabug.show();


            } else if (menuItem.getItemId() == R.id.menu_settings) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), Settings.class);
                requireActivity().startActivity(intent);
                requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            } else if (menuItem.getItemId() == R.id.eng3) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), old_version.class);
                requireActivity().startActivity(intent);
                requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            } else if (menuItem.getItemId() == R.id.scores) {

                Intent intent = new Intent(requireActivity().getApplicationContext(), statistics.class);
                requireActivity().startActivity(intent);
                requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
            return false;
        });
        return view;
    }

    private void sendApplication(Activity activity) {
        ApplicationInfo app = activity.getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        intent.setType("*/*");

        // Append file and send Intent
        File originalApk = new File(filePath);

        try {
            //Make new directory in new location
            File tempFile = new File(activity.getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory()) {
                if (!tempFile.mkdirs()) {
                    return;
                }
            }
            //Get application's name and convert to lowercase
            tempFile = new File(tempFile.getPath() + "/" + activity.getString(app.labelRes).replace(" ", "").toLowerCase() + ".apk");
            //If file doesn't exists create new
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            //Copy file to new location
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            //Open share dialog
            Context context = getActivity();

            Uri uri = FileProvider.getUriForFile(Objects.requireNonNull(context), activity.getPackageName(), tempFile);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            activity.grantUriPermission(activity.getPackageManager().toString(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            activity.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setName() {
        navUsername.setText(preferences.getString("Name", ""));

    }
}
