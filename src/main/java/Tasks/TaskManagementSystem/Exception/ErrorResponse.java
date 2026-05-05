package Tasks.TaskManagementSystem.Exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorResponse {

    private String errormsg;
    private String errorcode;
    private String errordesc;
    private LocalDateTime date;

    public ErrorResponse(String errormsg, String errordesc, String errorcode) {
        this.errormsg = errormsg;
        this.errordesc = errordesc;
        this.errorcode = errorcode;
        this.date= LocalDateTime.now();
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrordesc() {
        return errordesc;
    }

    public void setErrordesc(String errordesc) {
        this.errordesc = errordesc;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = LocalDateTime.now();
    }
}
