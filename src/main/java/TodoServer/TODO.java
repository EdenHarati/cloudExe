package TodoServer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Id: a unique id which is a simple integer counter that starts from 1 (that is, the id of the TODO is 1)
 * Title: short title describing what’s the essence of this TODO
 * Content: the actual content describing what this TODO stands for
 * Due date: a timestamp denoting the target time for this TODO to be fulfilled
 * Status: the status of the TODO: PENDING (when it is created and before the due date) | LATE (when it is not performed yet, and we are past the due date) | DONE (when the TODO item processing is over)
 * **/
public class TODO {
    public int id;
    private String title;
    private String content;
    private long dueDate;
    private String status;

   public TODO(String title, String content, long dueDate) {
       this.id = TodoServerController.STATIC_ID_COUNTER;
       this.title = title;
       this.content = content;
       this.dueDate = dueDate;
       this.status = TodoServerController.PENDING_STATUS;
   }

   public TODO(){

   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
