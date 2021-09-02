package cl.chilllanubb.carnetvirtual.ui.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cl.chilllanubb.carnetvirtual.R;
import cl.chilllanubb.carnetvirtual.utils.models.Usuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private Usuario user;
    private ImageView qr;
    private TextView title;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(),getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        user = gson.fromJson(sharedPreferences.getString("user", "{}"), Usuario.class);

        qr = root.findViewById(R.id.qr);
        title = root.findViewById(R.id.title2);

        String text = String.valueOf(user.getId());

        String ttl = String.format("Bienvenido %s, te informamos que tu código QR es el que se muestra a continuación, se te solicitará al retirar medicamentos y/o pedir cita médica.", user.getPaciente().getNombres());
        title.setText(ttl);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}