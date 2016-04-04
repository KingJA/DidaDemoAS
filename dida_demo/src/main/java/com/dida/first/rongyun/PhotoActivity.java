package com.dida.first.rongyun;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import io.rong.imkit.tools.PhotoFragment;

import com.dida.first.R;
import com.sea_monster.resource.Resource;
import com.sea_monster.resource.ResourceHandler;

/**
 * Created by DragonJ on 15/4/13.
 */
public class PhotoActivity extends FragmentActivity {
    PhotoFragment mPhotoFragment;
    Uri mUri;
    Uri mDownloaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.de_ac_photo);
        initView();
        initData();
    }


	private void  copyPic() {
		if(mDownloaded == null) {
		    Toast.makeText(this, "正在下载，请稍后保存！", Toast.LENGTH_SHORT).show();
		    return;
		}

		File path = Environment.getExternalStorageDirectory();
		File dir = new File(path, "RongCloud/Image");
		if(!dir.exists())
		    dir.mkdirs();

		File from = new File(mDownloaded.getPath());
		String name = from.getName() + ".jpg";
		File to = new File(dir.getAbsolutePath(), name);
		if(to.exists()) {
		    Toast.makeText(this, "文件保存成功！", Toast.LENGTH_SHORT).show();
		    return ;
		}
		copyFile(from.getAbsolutePath(), to.getAbsolutePath());
	}

    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "文件保存出错！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            Toast.makeText(this, "文件保存成功！", Toast.LENGTH_SHORT).show();
        }
    }


    protected void initView() {
        mPhotoFragment = (PhotoFragment) getSupportFragmentManager().getFragments().get(0);

    }

    protected void initData() {
        Uri uri = getIntent().getParcelableExtra("photo");
        Uri thumbUri = getIntent().getParcelableExtra("thumbnail");

        mUri = uri;
        if (uri != null)
            mPhotoFragment.initPhoto(uri, thumbUri, new PhotoFragment.PhotoDownloadListener() {
                @Override
                public void onDownloaded(Uri uri) {
                    mDownloaded = uri;
                }

                @Override
                public void onDownloadError() {

                }
            });
    }
}
