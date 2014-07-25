package com.konsola.konsola_prj___1;

import java.util.HashMap;
import java.util.Set;

import com.zinga.mymov.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class MovieAdapter extends ArrayAdapter<String>{

	private HashMap<Integer,Boolean> movieCollection = new HashMap<Integer, Boolean>();

	public MovieAdapter(Context context) {
		//super(context, resource);
		// TODO Auto-generated constructor stub
		
		super(context, R.layout.movie_item, android.R.id.text1,context.getResources().getStringArray(R.array.spaces));
	}
	
	public void toggleMovie(int position){
		
		System.out.println("Clicked position: " + position);
		if(!isInCollection(position)){
			movieCollection.put(position,true);
		} else {
			movieCollection.put(position, false);
		}
		
		/*Set<Integer> i = movieCollection.keySet();
		for(Integer ii: i){
			System.out.println("ii: " + ii + " - " + movieCollection.get(ii));
		}*/
		
	}
	
	public boolean isInCollection(int position){
		return movieCollection.get(position) == Boolean.TRUE;
	}
	
	/* metoda zwraca widok dla ka¿dego elementu i jest wykonywana za ka¿dym razem kiedy trzeba wyœwietliæ element listy 
	 * ViewHolder design pattern = umo¿liwia dostêp do ka¿dego elemntu listy bez potrzeby  angazowania cennych cykli procesora.
	 * Dziêki wzorcoi unikamy czêstych odwo³añ findViewById() podczas przewijania ListView.
	 * 
	 * 
	 * 
	 * */
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View listItem = super.getView(position, convertView, parent);
		CheckedTextView checkMark = null;
		ViewHolderItem holder = (ViewHolderItem) listItem.getTag();
		if(holder != null){
			checkMark = holder.checkMark;
		} else {
			checkMark = (CheckedTextView) listItem.findViewById(android.R.id.text1);
			holder = new ViewHolderItem(checkMark);
			listItem.setTag(holder);
		}
		
		checkMark.setChecked(isInCollection(position));
		return listItem;
	}
	
	
	private class ViewHolderItem{
		protected final CheckedTextView checkMark;
		
		public ViewHolderItem(CheckedTextView checkMark){
			this.checkMark = checkMark;
		}
	}
}


 