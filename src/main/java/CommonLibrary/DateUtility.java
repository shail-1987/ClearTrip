package CommonLibrary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {

    public String GetTravelDate(int cnt){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Using today's date
        c.add(Calendar.DATE, cnt); // Adding 5 days
        String output = sdf.format(c.getTime());
        return output;

    }


}
