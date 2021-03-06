package info.kghost.android.openvpn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

class AuthDialog extends DialogFragment {
	public static interface Callback {
		public void onSuccess(boolean save, CharSequence username,
				CharSequence password);
	}

	private Callback cb;
	private String username;
	private TextView usernameView;
	private TextView passwordView;
	private CheckBox saveUsername;

	public AuthDialog(String username) {
		this.username = username;
	}

	public void setCallback(Callback callback) {
		cb = callback;
	}

	private View createConnectView() {
		View v = View.inflate(getActivity(), R.layout.vpn_connect_dialog_view,
				null);
		usernameView = (TextView) v.findViewById(R.id.username_value);
		passwordView = (TextView) v.findViewById(R.id.password_value);
		saveUsername = (CheckBox) v.findViewById(R.id.save_username);

		if (username != null && username.length() > 0) {
			usernameView.setText(username);
			saveUsername.setChecked(true);
		}
		return v;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setTitle(getString(R.string.vpn_username_dialog_title))
				.setView(createConnectView())
				.setPositiveButton(R.string.vpn_connect_button,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								cb.onSuccess(saveUsername.isChecked(),
										usernameView.getText(),
										passwordView.getText());
							}
						}).create();
	}
}
