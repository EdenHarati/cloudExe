package TodoServer;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoServerController {

    public static int STATIC_ID_COUNTER = 1;
    public static String ALL_STATUS = "ALL"; // all statuses
    public static String PENDING_STATUS = "PENDING"; // when it is created and before the due date
    public static String LATE_STATUS = "LATE"; // when it is not performed yet, and we are past the due date
    public static String DONE_STATUS = "DONE"; // when the TO-DO item processing is over

    public static List<TODO> todosList = new ArrayList<TODO>();

    @GetMapping("/todo/health")
    @ResponseBody
    public String health() {
        return "OK";
    }


    @PostMapping("/todo")
    @ResponseBody
    public ResponseEntity<String> creatNewTODO(@RequestBody TODO givenTODO) {
        JSONObject response = new JSONObject();

        if (TODOs.checkTitle(givenTODO.getTitle())) { // Title is not valid
            response.put("errorMessage", "Error: TODO with the title [" + givenTODO.getTitle() + "] already exists in the system");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response.toString());
        }

        if ((TODOs.checkTimestamp(givenTODO.getDueDate()))) { // Timestamp is not valid
            response.put("errorMessage", "Error: Can't create new TODO that its due date is in the past");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response.toString());
        }

        TODOs.addNewTODO(givenTODO);
        response.put("result", STATIC_ID_COUNTER);
        STATIC_ID_COUNTER++;
        return ResponseEntity.ok().body(response.toString());
    }

    @GetMapping("/todo/size")
    @ResponseBody
    public ResponseEntity<String> getTodoSize(@RequestParam String status) {
        JSONObject response = new JSONObject();
        if ((!(status.equals(ALL_STATUS)) && !(status.equals(PENDING_STATUS)) && !(status.equals(DONE_STATUS)) && !(status.equals(LATE_STATUS))) || status == null) {
            return ResponseEntity.badRequest().build();
        }
        int number = TODOs.calculateTOTOsNum(status);
        response.put("result", number);
        return ResponseEntity.ok().body(response.toString());
    }

    @PutMapping("/todo")
    @ResponseBody
    public ResponseEntity<String> updateTODOStatus(@RequestParam int id, @RequestParam String status) {
        JSONObject response = new JSONObject();

        if ((!(status.equals(ALL_STATUS)) && !(status.equals(PENDING_STATUS)) && !(status.equals(DONE_STATUS)) && !(status.equals(LATE_STATUS))) || status == null) {
            return ResponseEntity.badRequest().build();
        }
        int index = TODOs.checkIfTODOExist(id);
        if (index > 0) {
            response.put("result", TODOs.TODOsList.get(index).getStatus());
            TODOs.updateStatus(id, status);
            return ResponseEntity.ok().body(response.toString());
        } else {
            response.put("errorMessage", "Error: no such TODO with id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
        }
    }
}
