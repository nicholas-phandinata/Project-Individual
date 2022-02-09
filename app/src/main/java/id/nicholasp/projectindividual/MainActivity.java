package id.nicholasp.projectindividual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import id.nicholasp.projectindividual.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    private OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        // clickable pada button di custom header
        NavigationView navigationView = findViewById(R.id.navView);
        View headerView = getLayoutInflater().inflate(R.layout.nav_header_layout,
                navigationView, false);
        navigationView.addHeaderView(headerView);

        Button btn_go_to_web = headerView.findViewById(R.id.btn_go_to_web);
        btn_go_to_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Test Header Click",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.inixindo.id"));
                startActivity(intent);
            }
        });

        // custom toolbar
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(Color.WHITE);

        // default fragment dibuka pertama kali
        getSupportActionBar().setTitle("Instruktur Fragment");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new InstrukturFragment()).commit();
        binding.navView.setCheckedItem(R.id.nav_instruktur);

        // membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
                R.string.open, R.string.close);
        binding.drawer.addDrawerListener(toggle);
        // membuat anak panah drawer
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // sinkronisasi drawer
        toggle.syncState();

        // salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_instruktur:
                        fragment = new InstrukturFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_materi:
                        fragment = new MateriFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_peserta:
                        fragment = new PesertaFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_kelas:
                        fragment = new KelasFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_detail_kelas:
                        fragment = new DetailKelasFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                }
                return true;
            }
        });
    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // mengantisipasi tombol backpressed
    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            getSupportActionBar().setTitle("Instruktur Fragment");
            binding.navView.setCheckedItem(R.id.nav_instruktur);
            onBackPressedListener.doBack();
            binding.drawer.closeDrawer(GravityCompat.START);
        } else if (onBackPressedListener == null) {
            finish();
            super.onBackPressed();
        }
    }

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.option_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_1:
//                Toast.makeText(this, "New Menu is selected.", Toast.LENGTH_LONG).show();
//                return true;
//
//            case R.id.menu_3:
//                Toast.makeText(this, "Exit is selected.", Toast.LENGTH_LONG).show();
//                return true;
//
//            case R.id.sub_menu1:
//                Toast.makeText(this, "Text Size is selected.", Toast.LENGTH_LONG).show();
//                return true;
//
//            case R.id.sub_menu2:
//                Toast.makeText(this, "Text Style is selected.", Toast.LENGTH_LONG).show();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}