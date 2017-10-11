package wesave.farabi.com.wesave.Utility;

import java.util.ArrayList;
import java.util.List;

import wesave.farabi.com.wesave.Data.CallForDonation;

/**
 * Created by GSC on 06/10/2017.
 */

public class MyArrayUtils {

    public static List<CallForDonation> filterbylocation(List<CallForDonation> calllist,String loc) {
 List<CallForDonation> tList = new ArrayList<>();
        if (loc.equals("")){
 return calllist;
        } else {
            for (CallForDonation c : calllist) {

                if (!c.getLocation().toUpperCase().contains(loc.toUpperCase())) {
                   tList.add(c);

                }

            }
            calllist.removeAll(tList);
            return calllist ;
        }
    }
}
