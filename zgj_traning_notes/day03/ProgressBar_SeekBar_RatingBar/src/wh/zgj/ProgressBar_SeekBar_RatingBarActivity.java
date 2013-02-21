package wh.zgj;


import android.app.Activity;

import android.os.Bundle;
 
import android.widget.ProgressBar;
 
import android.widget.RatingBar;
 
import android.widget.SeekBar;
 
import android.widget.TextView;
 
import android.widget.Toast;
 
 
public class ProgressBar_SeekBar_RatingBarActivity extends Activity {
 
	private final float MAX = 100f;
	 
	private final int RATING = 5;
	 
	 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);		 
		setContentView(R.layout.bar);
		 
		SeekBar seek = (SeekBar)findViewById(R.id.seek);	 
		seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		 
			@Override 
			public void onStopTrackingTouch(SeekBar seekBar) {//每次 点击完 Seekbar时，执行
			 
				Toast.makeText(ProgressBar_SeekBar_RatingBarActivity.this, "StopTouch", Toast.LENGTH_SHORT).show();
		 
			}
		 
			@Override 
			public void onStartTrackingTouch(SeekBar seekBar) {//每次点击Seekbar时，执行
			 
				Toast.makeText(ProgressBar_SeekBar_RatingBarActivity.this, "StartTouch", Toast.LENGTH_SHORT).show();
			 
			}
		 
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				 
				if(fromUser){
				 
					TextView textView = (TextView)findViewById(R.id.textprogress);
					 
					textView.setText(String.format(getString(R.string.progress), progress+"%"));
					 
					 
					ProgressBar pBar = (ProgressBar)findViewById(R.id.progress);
					 
					pBar.setProgress(progress);
					 
					 
					RatingBar rBar = (RatingBar)findViewById(R.id.rating);
					 
					rBar.setRating(progress/MAX*RATING);
				 
				}
			 
			} });
		
		RatingBar rBar = (RatingBar)findViewById(R.id.rating);	 
		rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() { 
		 
			@Override 
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
			 
				if(fromUser){
				 
					TextView textView = (TextView)findViewById(R.id.textprogress);
					 
					textView.setText(String.format(getString(R.string.progress), (int)(rating*MAX/RATING)+"%"));
					 
					 
					ProgressBar pBar = (ProgressBar)findViewById(R.id.progress);
					 
					pBar.setProgress((int) (rating*MAX/RATING));
					 
					 
					SeekBar sBar = (SeekBar)findViewById(R.id.seek);
					 
					sBar.setProgress((int) (rating*MAX/RATING));
				 
				}
				 
			} });
	}
}