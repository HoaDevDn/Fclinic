package com.framgia.capstone.ui.timkiemOCR;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.data.model.PhongKham;
import com.framgia.capstone.data.model.Thuoc;
import com.framgia.capstone.data.model.ThuocRealm;
import com.framgia.capstone.ui.adapter.OnItemPhongKhamClickListener;
import com.framgia.capstone.ui.adapter.OnItemThuocClickListener;
import com.framgia.capstone.ui.adapter.ThuocAdapter;
import com.framgia.capstone.ui.chitietthuoc.ChiTietThuoc;
import com.framgia.capstone.ui.home.MainActivity;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import io.realm.Realm;
import io.realm.RealmResults;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.capstone.utils.SharedPreferencesUtils.savePhongKham;

/**
 * Created by Age on 5/3/2017.
 */

public class TimKiemOCR extends AppCompatActivity {
    private static final int PHOTO_REQUEST = 0;
    private TextView scanResults;
    private Uri imageUri ;
    private TextRecognizer detector;
    private Button button;
    private static final int REQUEST_WRITE_PERMISSION = 1;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_RESULT = "result";
    private List<Thuoc> mThuocs = new ArrayList<>();
    private ThuocAdapter mAdapter;
    private Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ocr);

        Realm.init(this);
        mRealm = Realm.getDefaultInstance();

        setTitle("Scan");
        button = (Button) findViewById(R.id.button_scan);
        scanResults = (TextView) findViewById(R.id.results_scan);
        if (savedInstanceState != null) {
            imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
            scanResults.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
        }
        detector = new TextRecognizer.Builder(getApplicationContext()).build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(TimKiemOCR.this, new
                        String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
            }
        });
        mAdapter=new ThuocAdapter(this, mThuocs);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(TimKiemOCR.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            launchMediaScanIntent();
            try {
                Bitmap bitmap = decodeBitmapUri(this, imageUri);
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> textBlocks = detector.detect(frame);
                    String blocks = "";
                    for (int index = 0; index < textBlocks.size(); index++) {
                        TextBlock tBlock = textBlocks.valueAt(index);
                        blocks = blocks + tBlock.getValue() + "\n" + "\n";
                    }
                    if (textBlocks.size() == 0) {
                        scanResults.setText("Scan thất bại: không có gì để scan");
                    } else {
//                        loadThuoc();
                        loadListThuoc(getListThuoc());
                        for (int i = 0; i < mThuocs.size(); i++){
                            if(blocks.toLowerCase().contains(mThuocs.get(i).getTenThuoc().toLowerCase())){
                                scanResults.setText("Đã tìm thấy "+mThuocs.get(i).getTenThuoc());
                                startActivity(new Intent(ChiTietThuoc.getThuocIntent(getApplication(), mThuocs.get(i))));
                            }
                            else {
                                scanResults.setText("Không tìm thấy thuốc");
                            }
                        }
                    }
                } else {
                    scanResults.setText("Không thể thiết lập máy dò!");
                }
            } catch (Exception e) {
                Toast.makeText(this, "Không thể tải hình ảnh", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        imageUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, scanResults.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }

    public void loadThuoc(){
        List<Thuoc> list=new ArrayList<>();
        list.add(new Thuoc("1","1","Levothyroxine","http://www.chuatriviemdaitrang.com/wp-content/uploads/2016/08/thuoc-1.jpg","Hormone Thyroid","893460200107","893460200107", "phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("2","2","Memantine","http://www.viemgan.com.vn/js/libs/kcfinder/upload/images/thuoc-gay-hai-cho-gan.jpg","Alzheimers","893460200107","893460200107", "phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("3","3","Donepezil","http://tamlyhoctoipham.com/uploads/images/luu-y-khi-dung-thuoc-ha-huyet-ap-1.jpg","Alzheimers","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("4","1","Zolpidem","http://mediplantex.com/Uploads/images/42/2013/12/12/business.jpg","An thần","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("5","2","Eszopiclone","http://thoibaotaichinhvietnam.vn/Pictures102015/nguyenthiphuong-tbtc/thuocngoai.jpg","An thần","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("6","2","Temazepam","http://www.yeutretho.vn/files/2015/10/31/14-sai-lam-te-hai-khi-uong-thuoc-khien-benh-them-nang-7.jpg","An thần","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("7","3","Ticagrelor","http://thoatvidiadem.net/wp-content/uploads/thuoc-ho-tro-dieu-tri-thoai-hoa-cot-song.jpg","Bệnh tim mạch","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("8","3","Etonogestrel +","https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQFdVNRvDIeMhHyryZXzA1zp1VjtcUkI1on-gwFFaIvFJIzILHi","Biện pháp tránh thai","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("9","1","Folic Acid","http://giaoduc.net.vn/Uploaded/phamlieu/2013_05_15/luu-y-khi-uong-thuoc-giaoduc.net.vn2.jpg","Bổ sung acid folic","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        list.add(new Thuoc("10","3","Testosterone","http://baohaiphong.com.vn/dataimages/201511/original/images1200436_sleepingpills.jpg","Bổ sung vitamin D","893460200107","893460200107","phụ nữ có thai, phụ nữ cho con bú, trẻ em, trẻ vị thành niên dưới 18 tuổi do chưa có số liệu về độ an toàn và hiệu quả điều trị.","Thuốc trị cảm","5000"));
        updateData(list);
    }

    public void updateData(List<Thuoc> thuocs) {
        mThuocs.addAll(thuocs);
        mAdapter.notifyDataSetChanged();
    }

    public void loadListThuoc(List<ThuocRealm> thuocRealms) {
        if (thuocRealms == null) {
            return;
        }
        List<Thuoc> list = new ArrayList<>();
        for (int i = 0; i < thuocRealms.size(); i++) {
            list.add(new Thuoc(thuocRealms.get(i).getMaThuoc(), thuocRealms.get(i).getMaLoaiThuoc(),
                    thuocRealms.get(i).getTenThuoc(), thuocRealms.get(i).getHinhAnh(),
                    thuocRealms.get(i).getGia(), thuocRealms.get(i).getMaVach(),
                    thuocRealms.get(i).getMaHinh(), thuocRealms.get(i).getTacDung(),
                    thuocRealms.get(i).getChongChiDinh(), thuocRealms.get(i).getTenLoaiThuoc()));
        }
        mAdapter.updateData(list);
    }

    public RealmResults<ThuocRealm> getListThuoc() {
        RealmResults<ThuocRealm> realms = mRealm.where(ThuocRealm.class).findAll();
        return realms;
    }
}
