package com.konsola.konsola_prj___1;

import com.zinga.mymov.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MyMovies extends ListActivity {

	private MovieAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = getListView();
        
        Button goToBottom = (Button) getLayoutInflater().inflate(R.layout.list_header, null);
        Button backToTop = (Button) getLayoutInflater().inflate(R.layout.list_footer, null);

        goToBottom.setCompoundDrawablesWithIntrinsicBounds(getResources()
                .getDrawable(android.R.drawable.ic_menu_more), null, null,
                null);  
        
        backToTop.setCompoundDrawablesWithIntrinsicBounds(getResources()
                .getDrawable(android.R.drawable.ic_menu_close_clear_cancel), null, null,
                null);  
        listView.addHeaderView(goToBottom,false,true);
        listView.addFooterView(backToTop,false,true);
        
        
        
        
        this.adapter = new MovieAdapter(this);
        listView.setAdapter(this.adapter);
        listView.setItemsCanFocus(false);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	System.out.println("pos: " + (position - 1));
    	this.adapter.toggleMovie(position - 1);
    	this.adapter.notifyDataSetChanged();
    }
    
    public void backToTop(View view){
    	getListView().setSelection(0);
    }
    
    public void goToBottom(View view){
    	getListView().setSelection(adapter.getCount()-1);
    }
    
}
