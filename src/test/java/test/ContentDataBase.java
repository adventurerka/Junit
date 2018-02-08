package test;

import java.util.ArrayList;
import java.util.List;

public class ContentDataBase {

    public static List<Object[]> fileContent(){
        List mylist = new ArrayList<Object[]>();
        mylist.add(new Object[]{"1112233"});
        mylist.add(new Object[]{"APPLE!!!"});
        mylist.add(new Object[]{"ORANGE!!!"});
        return mylist;
    }

}
