package com.example.user.pelapor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class PublishActivity extends Activity {

   /* private Uri UrlGambar;*/
    private ImageView SetImageView;
    private Button button;

    private static final int CAMERA = 1;
    private static final int FILE = 2;
    private String [] items =  {"Camera","Gallery"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_publish);

        /*final String [] pilih = new String [] {"Camera", "SD Card"};*/

        button = (Button) findViewById(R.id.btnSelectPhoto);
        SetImageView = (ImageView) findViewById(R.id.ivImage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });
    }

    /**
     * this method used to open image directory or open from camera
     */
    private void openImage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera")){
                    EasyImage.openCamera(PublishActivity.this,CAMERA);
                }else if(items[i].equals("Gallery")){
                    EasyImage.openGallery(PublishActivity.this, FILE);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type){
                    case CAMERA:
                        Glide.with(PublishActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(SetImageView);
                        break;
                    case FILE:
                        Glide.with(PublishActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(SetImageView);
                        break;
                }
            }
        });


        /*ArrayAdapter<String> arr_adapter	= new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,pilih);
        AlertDialog.Builder builder		= new AlertDialog.Builder(this);

        builder.setTitle("Pilih Gambar");
        builder.setAdapter( arr_adapter, new DialogInterface.OnClickListener()
        {
            public void onClick( DialogInterface dialog, int pilihan )
            {
                if (pilihan == 0)
                {
                    Intent intent 	 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file		 = new File(Environment.getExternalStorageDirectory(),
                            "image_picker/img_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    UrlGambar = Uri.fromFile(file);

                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, UrlGambar);
                        intent.putExtra("return-data", true);

                        startActivityForResult(intent, CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dialog.cancel();
                }
                else if(pilihan == 1)
                {
                    Intent intent = new Intent();


                    intent.setType("img/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    startActivityForResult(Intent.createChooser(intent, "Pilih Aplikasi"), FILE);
                }
            }
        } );

        final AlertDialog dialog = builder.create();

        SetImageView = (ImageView) findViewById(R.id.ivImage);

        Button tmb_pilih = (Button) findViewById(R.id.btnSelectPhoto);
        tmb_pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        Bitmap bitmap 	= null;
        String path		= "";

        if (requestCode == FILE)
        {
            UrlGambar = data.getData();
            path = getRealPath(UrlGambar);

            if (path == null)
            {
                path = UrlGambar.getPath();
            }
            else
            {
                bitmap 	= BitmapFactory.decodeFile(path);
            }
        }
        else
        {
            path	= UrlGambar.getPath();
            bitmap  = BitmapFactory.decodeFile(path);
        }

        Toast.makeText(this, path,Toast.LENGTH_SHORT).show();
        SetImageView.setImageBitmap(bitmap);
    }

    public String getRealPath(Uri contentUri)
    {
        String path = null;
        String[] images_data = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, images_data, null, null, null);
        if(cursor.moveToFirst())
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;*/
    }
}