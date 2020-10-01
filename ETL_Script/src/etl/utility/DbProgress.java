package etl.utility;

import java.util.TimerTask;

/**
 * La classe estende la classe TimerTask.
 * L'obiettivo è creare un oggetto che mostrerà
 * ad ogni intervallo di tempo un messaggio attraverso
 * un'Information Box.
 */
public class DbProgress extends TimerTask {

    @Override
    public void run() {
        Utility.showInformationBox("Exporting Data", "Exporting Data still running. Please, Wait!");
    }
}
