package com.example.app_sellairlineticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetForHomeFragment extends BottomSheetDialogFragment {
    public interface OnOptionSelectedListener {
        void onOptionSelected(String option);
    }

    private OnOptionSelectedListener listener;

    // Gán callback từ Fragment cha
    public void setOnOptionSelectedListener(OnOptionSelectedListener listener) {
        this.listener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        TextView tvOption1 = view.findViewById(R.id.tvOption1);
        tvOption1.setOnClickListener(v -> {
            if (listener != null) {
                listener.onOptionSelected("Phổ thông");
            }
            dismiss(); // đóng BottomSheet sau khi chọn
        });
        TextView tvOption2 = view.findViewById(R.id.tvOption2);
        tvOption2.setOnClickListener(v -> {
            if (listener != null) {
                listener.onOptionSelected("Thương gia");
            }
            dismiss(); // đóng BottomSheet sau khi chọn
        });
        return view;
    }
}
