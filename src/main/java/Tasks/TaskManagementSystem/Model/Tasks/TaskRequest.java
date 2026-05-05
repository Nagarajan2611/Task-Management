package Tasks.TaskManagementSystem.Model.Tasks;

import jakarta.validation.constraints.NotBlank;

public class TaskRequest {
    @NotBlank
    private String tittle;
    @NotBlank
    private String description;
    private String status;
    private Long userid;


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
