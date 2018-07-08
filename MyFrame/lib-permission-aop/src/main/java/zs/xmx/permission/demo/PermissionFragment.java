package zs.xmx.permission.demo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import zs.xmx.permission.R;
import zs.xmx.permission.annotation.NeedPermission;
import zs.xmx.permission.annotation.PermissionCanceled;
import zs.xmx.permission.annotation.PermissionDenied;
import zs.xmx.permission.bean.DenyBean;
import zs.xmx.permission.utils.SettingUtils;


public class PermissionFragment extends Fragment {

    private Button btn_permission;

    public PermissionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_permission, container, false);
        btn_permission = (Button) view.findViewById(R.id.btn_permission);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    @NeedPermission(value = {Manifest.permission.RECORD_AUDIO})
    private void requestPermission() {
        Toast.makeText(getActivity(), "录音权限申请通过", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied
    public void dealDenyPermission(DenyBean bean) {
        Toast.makeText(getActivity(), "录音权限申请被禁止", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("录音权限被禁止，需要手动打开")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SettingUtils.go2Setting(getActivity());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    @PermissionCanceled
    public void dealCancelPermission() {
        Toast.makeText(getActivity(), "录音权限申请被取消", Toast.LENGTH_SHORT).show();
    }
}
