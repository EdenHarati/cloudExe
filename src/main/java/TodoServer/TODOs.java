package TodoServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TODOs {

    public static final List<TODO> TODOsList = new ArrayList<>();

    public static void addNewTODO(TODO newTodo) {
        newTodo.setStatus("PENDING");
        TODOsList.add(newTodo);
    }
    public static boolean checkTitle(String TODOtitle) {
        return TODOsList.stream().anyMatch(todo -> todo.getTitle().equals(TODOtitle));
    }

    public static int checkIfTODOExist(int id){
        int i = 0;
        int result = -1;

        for (i = 0; i < TODOsList.size(); i++)
        {
            if(TODOsList.get(i).getId() == id)
            {
                result = i;
            }
        }
        return result;
    }

    public static void updateStatus(int id, String status){
        int i = 0;
        for (i = 0; i < TODOsList.size(); i++) {
            if (TODOsList.get(i).getId() == id) {
                TODOsList.get(i).setStatus(status);
            }
        }
    }

    public static int calculateTOTOsNum(String status) {
        int result = 0;
        int i = 0;

        if(status.equals(TodoServerController.ALL_STATUS))
        {
            result = TODOsList.size();
        } else {
            for (i = 0; i < TODOsList.size(); i++)
            {
                if(TODOsList.get(i).getStatus().equals(status))
                {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean checkTimestamp(long TODOtimestamp) {
        Date date = new Date(TODOtimestamp);
        Date today = new Date();

        return date.before(today);
    }

}
