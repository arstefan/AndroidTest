package com.zinga.homecontrol;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

/* pojedynczy ekran aplikacji z kt�rej mo�na przeprowadza� interakcje. z regu�y jest widoczne jedno activity.
*	Activity mog� by� odpalane na stosie. MO�na je przykry� innym activity 
*
* Ka�de activity ma sw�j cykl �ycia
*
*
*/

public class HomeControlActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_control);
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_control, menu);
        return true;
    }
    
}
