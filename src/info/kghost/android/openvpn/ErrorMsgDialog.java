package info.kghost.android.openvpn;

import java.util.concurrent.Callable;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class ErrorMsgDialog extends DialogFragment {
	private CharSequence msg;
	private Callable<Object> cb;

	public ErrorMsgDialog(String message, Callable<Object> callable) {
		msg = message;
		cb = callable;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return new AlertDialog.Builder(getActivity())
				.setTitle(getString(R.string.openvpn_install_error_title)).setMessage(msg)
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						try {
							cb.call();
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}).create();
	}
}
