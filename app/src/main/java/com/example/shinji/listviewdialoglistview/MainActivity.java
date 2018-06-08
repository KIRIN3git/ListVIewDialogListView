package com.example.shinji.listviewdialoglistview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	ListView mPokemonListView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView mTrainerListView = (ListView) findViewById(R.id.mTrainerListView);

        /*
        // データを準備
        ArrayList<String> items = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            items.add("items-" + i);
        }
        */

		// 〇トレーナーリスト用
		ArrayList<Trainer> trainers = new ArrayList<>();

		int[] icons = {
				R.mipmap.ic_launcher,
				R.mipmap.ic_launcher,
				R.mipmap.ic_launcher
		};

		String[] names = {
				"サトシ",
				"カスミ",
				"タケシ"
		};

		String[] comments = {
				"あああ",
				"いいい",
				"ううう"
		};

		for (int i = 0; i < icons.length; i++) {
			Trainer trainer = new Trainer();
			trainer.setIcon(BitmapFactory.decodeResource(
					getResources(),
					icons[i]
			));
			trainer.setName(names[i]);
			trainer.setComment(comments[i]);
			trainers.add(trainer);

			Log.w( "DEBUG_DATA", "names[i] = " + names[i] );
			Log.w( "DEBUG_DATA", "comments[i] = " + comments[i] );
		}

		TrainerAdapter trai_adapter = new TrainerAdapter(this, 0, trainers);
		mTrainerListView.setAdapter(trai_adapter);



		// 〇モンスターリスト用
		mPokemonListView = new ListView(this);

		// データを準備
		ArrayList<Pokemon> pokemons = new ArrayList<>();

		int[] poke_icons = {
				R.mipmap.ic_launcher,
				R.mipmap.ic_launcher,
				R.mipmap.ic_launcher
		};

		String[] poke_names = {
				"ピカチュウ",
				"ゼニガメ",
				"イワーク"
		};

		String[] poke_comments = {
				"ピカピカ",
				"ゼニゼニ",
				"イワイワ"
		};

		for (int i = 0; i < poke_icons.length; i++) {
			Pokemon pokemon = new Pokemon();
			pokemon.setIcon(BitmapFactory.decodeResource(
					getResources(),
					poke_icons[i]
			));
			pokemon.setName(poke_names[i]);
			pokemon.setComment(poke_comments[i]);
			pokemons.add(pokemon);

			Log.w( "DEBUG_DATA", "names[i] = " + names[i] );
			Log.w( "DEBUG_DATA", "comments[i] = " + comments[i] );
		}
		PokemonAdapter poke_adapter = new PokemonAdapter(this, android.R.layout.simple_list_item_single_choice, pokemons); // ☆simple_list_item_single_choiceを設定
		mPokemonListView.setAdapter(poke_adapter);
		mPokemonListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		// 〇 トレーナーリストビューのクリックイベントを取得
		mTrainerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Log.d("DEBUG_DATA", "onItemClick");

			String msg = position + "番目のアイテムがクリックされました";
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

			// ・ダイアログ設定
			AlertDialog.Builder alertDlg = new AlertDialog.Builder(MainActivity.this);
			alertDlg.setTitle("ポケモン");

			// 2回目起動時に、親のビューが設定されてしまっているので削除する。
			ViewGroup parent2 = (ViewGroup)mPokemonListView.getParent();
			if ( parent2 != null ) {
				parent2.removeView(mPokemonListView);
			}
			// ビューを設定
			alertDlg.setView(mPokemonListView);

			alertDlg.setPositiveButton(
					"OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// OK ボタンクリック処理
						}
					});
			// 表示
			alertDlg.create().show();

			}
		});


		// 〇 ポケモンリストビューのクリックイベントを取得
		mPokemonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d("DEBUG_DATA", "onItemClick");

				String msg = position + "番目のアイテムがクリックされました";
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

			}
		});
	}

	// 〇 トレイナーアダプター
	public class TrainerAdapter extends ArrayAdapter<Trainer> {

		private LayoutInflater layoutInflater;
		public TrainerAdapter(Context c, int id, ArrayList<Trainer> trainers) {
			super(c, id, trainers);
			this.layoutInflater = (LayoutInflater) c.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE
			);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = layoutInflater.inflate(
						R.layout.list_item,
						parent,
						false
				);
			}

			Trainer trainer = (Trainer) getItem(position);

			((ImageView) convertView.findViewById(R.id.icon))
					.setImageBitmap(trainer.getIcon());
			((TextView) convertView.findViewById(R.id.name))
					.setText(trainer.getName());
			((TextView) convertView.findViewById(R.id.comment))
					.setText(trainer.getComment());

			return convertView;
		}
	}

	////////////////
	/// ・ポケモンアダプター
	public class PokemonAdapter extends ArrayAdapter<Pokemon> {

		private LayoutInflater layoutInflater;
		public PokemonAdapter(Context c, int id, ArrayList<Pokemon> pokemons) {
			super(c, id, pokemons);
			this.layoutInflater = (LayoutInflater) c.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE
			);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = layoutInflater.inflate(
						R.layout.list_item,
						parent,
						false
				);
			}

			Pokemon pokemon = (Pokemon) getItem(position);

			((ImageView) convertView.findViewById(R.id.icon))
					.setImageBitmap(pokemon.getIcon());
			((TextView) convertView.findViewById(R.id.name))
					.setText(pokemon.getName());
			((TextView) convertView.findViewById(R.id.comment))
					.setText(pokemon.getComment());

			return convertView;
		}
	}
}
