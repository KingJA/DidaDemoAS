package com.dida.first.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dida.first.R;
import com.dida.first.dialog.DialogUploadIcon;
import com.dida.first.dialog.DialogUploadIcon.OnUploadHeadListener;
import com.dida.first.factory.ActivityFactory;
import com.dida.first.utils.CustomConstants;
import com.dida.first.utils.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author KingJA
 * @data 2015-9-18 下午4:28:24
 * @use
 * 
 */
public class Mine_Personal_Activity extends BackTitleActivity {
	private Bitmap bmp;
	private TextView tv_mine_personal_nick;
	private TextView tv_mine_personal_gender;
	private RelativeLayout rl_mine_personal_icon;
	private RelativeLayout rl_mine_personal_nick;
	private RelativeLayout rl_mine_personal_gender;
	private RelativeLayout rl_mine_personal_safe;
	private RelativeLayout rl_mine_personal_address;
	private SimpleDraweeView sdv_userInfo_icon;
	private final int REQUEST_NICK = 10;
	private final int REQUEST_GENDER = 20;
	private final int REQUEST_PHOTO = 30;
	private final int REQUEST_ZOOM = 40;
	private final int REQUEST_SAVE_IMG = 50;

	@Override
	public View setView() {
		view = view.inflate(this, R.layout.activity_mine_personal, null);
		return view;
	}

	@Override
	public void initView() {
		tv_mine_personal_nick = (TextView) view
				.findViewById(R.id.tv_mine_personal_nick);
		tv_mine_personal_gender = (TextView) view
				.findViewById(R.id.tv_mine_personal_gender);
		rl_mine_personal_icon = (RelativeLayout) view
				.findViewById(R.id.rl_mine_personal_icon);
		rl_mine_personal_nick = (RelativeLayout) view
				.findViewById(R.id.rl_mine_personal_nick);
		rl_mine_personal_gender = (RelativeLayout) view
				.findViewById(R.id.rl_mine_personal_gender);
		rl_mine_personal_safe = (RelativeLayout) view
				.findViewById(R.id.rl_mine_personal_safe);
		rl_mine_personal_address = (RelativeLayout) view
				.findViewById(R.id.rl_mine_personal_address);
		sdv_userInfo_icon = (SimpleDraweeView) view
				.findViewById(R.id.sdv_userInfo_icon);
	}

	@Override
	public void initEvent() {
		rl_mine_personal_icon.setOnClickListener(this);
		rl_mine_personal_nick.setOnClickListener(this);
		rl_mine_personal_gender.setOnClickListener(this);
		rl_mine_personal_safe.setOnClickListener(this);
		rl_mine_personal_address.setOnClickListener(this);
	}

	@Override
	public void initDoNet() {

	}

	@Override
	public void initData() {
		setBackTitle("个人资料");
	}

	@Override
	public void setBackClick() {
		finish();
	}

	@Override
	public void onChildClick(View v) {
		switch (v.getId()) {
		case R.id.rl_mine_personal_icon:
			ToastUtil.showMyToast("头像");
			DialogUploadIcon dialogUploadIcon = new DialogUploadIcon(
					Mine_Personal_Activity.this);
			dialogUploadIcon
					.setOnUploadHeadListener(new OnUploadHeadListener() {

						@Override
						public void OnImage() {
							selectPictrue();
						}

						@Override
						public void OnCamera() {
							camera();
						}
					});
			break;
		case R.id.rl_mine_personal_nick:
			ToastUtil.showMyToast("昵称");
			goActivityForResult(Personal_EditNick_Activity.class, 10);
			break;
		case R.id.rl_mine_personal_gender:
			ToastUtil.showMyToast("性别");
			goActivityForResult(Personal_EditGenderk_Activity.class, 20);
			break;
		case R.id.rl_mine_personal_safe:
			ToastUtil.showMyToast("账号安全");
			break;
		case R.id.rl_mine_personal_address:
			ToastUtil.showMyToast("地址");
			goActivity(Personal_Address_Activity.class);
			break;

		default:
			break;
		}

	}

	private void goActivity(Class clazz) {
		Intent intent = new Intent(Mine_Personal_Activity.this, clazz);
		startActivity(intent);
	}

	private void goActivityForResult(Class clazz, int requestCode) {
		Intent intent = new Intent(Mine_Personal_Activity.this, clazz);
		startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("onActivityResult", "onActivityResult");

		switch (requestCode) {
		case REQUEST_NICK:
			if (data == null) {
				return;
			}
			String nick = data.getStringExtra("nick");
			tv_mine_personal_nick.setText(nick);
			break;
		case REQUEST_GENDER:
			if (data == null) {
				return;
			}
			int gender = data.getIntExtra("gender",1);
			tv_mine_personal_gender.setText(gender==1?"男":"女");
			break;
		case REQUEST_PHOTO:
			bmp = BitmapFactory.decodeFile(selectPhontFile.getAbsolutePath());

			sdv_userInfo_icon.setImageBitmap(bmp);
			uploadImage(selectPhontFile);
			break;
		case REQUEST_ZOOM:
			startPhotoZoom(Uri.fromFile(cacheFile));
			
			break;
		case REQUEST_SAVE_IMG:
			if (data != null)
				uploadImage(takePictureFile);
				sentPicToNext(data);
			break;

		default:
			break;
		}
	}

	/**
	 * 图片上传
	 * 
	 * @param file
	 */
	private void uploadImage(File file) {
		Log.i("file", file.getAbsolutePath());
		if (file.exists() && file.length() > 0) {
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			try {
				params.put("file", file);
				params.put("mobile", 1);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			client.post("http://192.168.1.173:8080/image/uploadImage", params, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode, String content) {
					ToastUtil.showMyToast( "头像上传成功!");
					Log.i("content", content);
					Log.i("statusCode", statusCode+"");
				}

				@Override
				public void onFailure(Throwable e, String data) {
					ToastUtil.showMyToast( "头像上传失败!");
				}
			});

		} else {
			ToastUtil.showMyToast("文件不存在");
		}

	}

	private int crop = 300;
	private File selectPhontFile = new File(CustomConstants.CACHE_IMG,
			"select_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");
	private File takePictureFile = new File(CustomConstants.CACHE_IMG,
			"picture_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");
	private File cacheFile = new File(CustomConstants.CACHE_ROOT_URL,
			getPhotoFileName());
	private File cacheRootFile = new File(CustomConstants.CACHE_ROOT_URL);
	public void selectPictrue() {
		Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
		intent.setType("image/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);

		/* 截图 */
		intent.putExtra("output", Uri.fromFile(selectPhontFile));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", crop);// 输出图片大小
		intent.putExtra("outputY", crop);

		/* 取得相片后返回本画面 */
		startActivityForResult(intent, REQUEST_PHOTO);
	}

	/**
	 * 选择拍照
	 */
	public void camera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (!cacheRootFile.exists()) {
			// 若不存在，创建目录，可以在应用启动的时候创建
			cacheRootFile.mkdirs();
		}
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cacheFile));
		startActivityForResult(intent, REQUEST_ZOOM);
		/**
		 * 刷新相册
		 */
		MediaScannerConnection.scanFile(ActivityFactory.mainActivity,
				new String[] { cacheFile.toString() }, null, null);
	}

	/**
	 * 使用系统当前日期加以调整作为照片的名称
	 * 
	 * @return
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	// 将进行剪裁后的图片传递到下一个界面上
	private void sentPicToNext(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			if (photo != null) {
				sdv_userInfo_icon.setImageBitmap(photo);
			}

		}
	}

	/**
	 * 存储剪切过的图片
	 * 
	 * @param mBitmap
	 */
	public void saveBitmap(Bitmap mBitmap) {
		File f = new File(CustomConstants.CACHE_IMG, "tmp_pic_"
				+ SystemClock.currentThreadTimeMillis() + ".jpg");
		try {
			f.createNewFile();
			FileOutputStream fOut = null;
			fOut = new FileOutputStream(f);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 剪切图片
	 * 
	 * @param uri
	 */
	private void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		//保存截图
		intent.putExtra("output", Uri.fromFile(takePictureFile));
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		intent.putExtra("noFaceDetection", true);
		// 剪切图片后调回主界面
		startActivityForResult(intent, REQUEST_SAVE_IMG);
		
	}
}
