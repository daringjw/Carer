package com.jkkc.carer.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jkkc.carer.BuildConfig;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.bean.WorkerBaseInfo;
import com.jkkc.carer.utils.FileUtil;
import com.jkkc.carer.utils.PrefUtils;
import com.jkkc.carer.view.CircleImageView;
import com.jkkc.carer.view.ClipImageActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

import static com.jkkc.carer.utils.FileUtil.getRealFilePathFromUri;

/**
 * Created by Guan on 2018/5/24.
 */

public class PersonalInfoActivity extends AppCompatActivity {


    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;


    private static final String TAG1 = PersonalInfoActivity.class.getSimpleName();


    //头像1
    private CircleImageView headImage1;
    //头像2
    private ImageView headImage2;
    //调用照相机返回图片文件
    private File tempFile;
    // 1: qq, 2: weixin
    private int type;

    // 编辑个人信息结果
    private static final int Edit_result = 105;

    LoginBean mLoginBean;
    private TextView mTvName;
    private TextView mTvSex;
    private TextView mTvAge;
    private TextView mTvPhone;
    private TextView mTvHomeAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String result = PrefUtils.getString(this, "loginBean", null);
        Gson gson = new Gson();
        mLoginBean = gson.fromJson(result, LoginBean.class);

        setContentView(R.layout.activity_personal_info);

        TextView tvEdit = (TextView) findViewById(R.id.tvEdit);
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EditPersonInfoActivity.class);
                startActivityForResult(intent, Edit_result);

            }
        });


        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        headImage1 = (CircleImageView) findViewById(R.id.head_image1);

        headImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //上传头像
                type = 1;
                uploadHeadImage();


            }
        });


        mTvName = (TextView) findViewById(R.id.tvName);
        mTvSex = (TextView) findViewById(R.id.tvSex);
        mTvAge = (TextView) findViewById(R.id.tvAge);
        mTvPhone = (TextView) findViewById(R.id.tvPhone);
        mTvHomeAddress = (TextView) findViewById(R.id.tvHomeAddress);


        //获取个人信息
        OkGo.<String>get(Constants.getCare)
                .tag(this)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("userAccount", mLoginBean.getUserAccount())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String result = response.body().toString();
                        Gson gson1 = new Gson();
                        WorkerBaseInfo workerBaseInfo = gson1.fromJson(result, WorkerBaseInfo.class);
                        String code = workerBaseInfo.getCode();
                        if (code.contains("success")) {

                            mTvName.setText(workerBaseInfo.getWorkerBaseInfo().getPeopleName());
                            mTvSex.setText(workerBaseInfo.getWorkerBaseInfo().getGender());
                            mTvAge.setText(workerBaseInfo.getWorkerBaseInfo().getBirthday());
                            mTvPhone.setText(workerBaseInfo.getWorkerBaseInfo().getPhoneNum());
                            mTvHomeAddress.setText(workerBaseInfo.getWorkerBaseInfo().getAddressCity()
                                    + workerBaseInfo.getWorkerBaseInfo().getAddressCounty()
                                    + workerBaseInfo.getWorkerBaseInfo().getAddressDetail());

                            String peopleImage = workerBaseInfo.getWorkerBaseInfo().getPeopleImage();
                            peopleImage = Constants.base_url1 + peopleImage;
                            Log.d(TAG1, "peopleImage=" + peopleImage);

                            OkGo.<File>get(peopleImage)
                                    .tag(this)
                                    .execute(new FileCallback() {
                                        @Override
                                        public void onSuccess(Response<File> response) {

                                            String path = response.body().getAbsolutePath();
                                            Uri uri = Uri.parse(path);
                                            headImage1.setImageURI(uri);


                                        }
                                    });


                        }


                    }
                });


    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_personal_info, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(PersonalInfoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(PersonalInfoActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamera();
                }

                popupWindow.dismiss();

            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(PersonalInfoActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(PersonalInfoActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath()
                + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(PersonalInfoActivity.this,
                    BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }

        startActivityForResult(intent,
                REQUEST_CAPTURE);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {

            case Edit_result:
                if (resultCode == RESULT_OK) {

                    //刷新数据
                    //获取个人信息
                    OkGo.<String>get(Constants.getCare)
                            .tag(this)
                            .params("token", mLoginBean.getToken())
                            .params("peopleId", mLoginBean.getPeopleId())
                            .params("userAccount", mLoginBean.getUserAccount())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {

                                    String result = response.body().toString();
                                    Gson gson1 = new Gson();
                                    WorkerBaseInfo workerBaseInfo = gson1.fromJson(result, WorkerBaseInfo.class);
                                    String code = workerBaseInfo.getCode();
                                    if (code.contains("success")) {

                                        mTvName.setText(workerBaseInfo.getWorkerBaseInfo().getPeopleName());
                                        mTvSex.setText(workerBaseInfo.getWorkerBaseInfo().getGender());
                                        mTvAge.setText(workerBaseInfo.getWorkerBaseInfo().getBirthday());
                                        mTvPhone.setText(workerBaseInfo.getWorkerBaseInfo().getPhoneNum());
                                        mTvHomeAddress.setText(
                                                workerBaseInfo.getWorkerBaseInfo().getAddressCity()
                                                        + workerBaseInfo.getWorkerBaseInfo().getAddressCounty()
                                                        + workerBaseInfo.getWorkerBaseInfo().getAddressDetail());

                                    }


                                }
                            });

                }
                break;

            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (type == 1) {
                        headImage1.setImageBitmap(bitMap);
                    } else {
                        headImage2.setImageBitmap(bitMap);
                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......
                    File file = new File(cropImagePath);


                    Log.d(TAG1, "token=" + mLoginBean.getToken());
                    Log.d(TAG1, "peopleId=" + mLoginBean.getPeopleId());
                    Log.d(TAG1, "phoneNum=" + mLoginBean.getUserAccount());
                    Log.d(TAG1, file.exists() + ",path=" + file.getAbsolutePath());

                    //上传头像
                    OkGo.<String>post(Constants.headImgUrl)
                            .tag(this)
                            .params("token", mLoginBean.getToken())
                            .params("peopleId", mLoginBean.getPeopleId())
                            .params("userAccount", mLoginBean.getUserAccount())
                            .params("uploadFile", file)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {

                                    String result = response.body().toString();
                                    Log.d(TAG1, "result=" + result);


                                }
                            });


                }

                break;
        }
    }


    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {

        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);

    }

}
