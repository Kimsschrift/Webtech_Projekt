package htw_berlin.webtech.dto;

import htw_berlin.webtech.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminDto {
    private Long id;
    private String adminCode;

    public AdminDto(Admin admin){
        this.id = admin.getId();
        this.adminCode = admin.getAdminCode();
    }
}