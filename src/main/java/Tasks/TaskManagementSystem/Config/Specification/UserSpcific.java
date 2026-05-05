package Tasks.TaskManagementSystem.Config.Specification;

import Tasks.TaskManagementSystem.Model.Users.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpcific implements Specification<User> {

    private String username;
   public UserSpcific(String username){
       this.username=username;
   }

    @Override
    public @Nullable Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
       List<Predicate> predicateList=new ArrayList<>();
       if((username!=null)&&(!username.isEmpty())){
           predicateList.add(criteriaBuilder.equal(root.get("username"),username));
       }
       if(predicateList.isEmpty()){
         return  criteriaBuilder.conjunction();
       }
        return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
    }
}
